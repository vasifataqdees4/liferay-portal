/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.source.formatter.check;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.io.unsync.UnsyncBufferedReader;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Qi Zhang
 */
public class LibraryVersionCheck extends BaseFileCheck {

	@Override
	protected String doProcess(
			String fileName, String absolutePath, String content)
		throws IOException {

		if (fileName.endsWith(".properties")) {
			_propertiesLibraryVersionCheck(fileName, content);
		}
		else if (fileName.endsWith(".xml")) {
			_xmlLibraryVersionCheck(fileName, content);
		}
		else if (fileName.endsWith(".gradle")) {
			_gradleLibraryVersionCheck(fileName, content);
		}

		return content;
	}

	private void _checkIsContainVulnerabilities(
		String fileName, String packageName, String version,
		CloseableHttpClient httpClient) {

		_checkIsContainVulnerabilities(
			fileName, packageName, version, null, httpClient);
	}

	private void _checkIsContainVulnerabilities(
		String fileName, String packageName, String version, String cursor,
		CloseableHttpClient httpClient) {

		HttpPost httpPost = new HttpPost("https://api.github.com/graphql");

		httpPost.addHeader(
			"Authorization", "bearer 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");
		httpPost.addHeader(
			"Content-Type",
			"application/json; charset=utf-8; application/graphql");

		String queryArguments =
			"first: 50, package:\\\"" + packageName +
				"\\\", severities: [CRITICAL]";

		if (Validator.isNotNull(cursor)) {
			queryArguments += "after: \\\"" + cursor + "\\\"";
		}

		String resultArguments =
			"{nodes { advisory {summary} package {name} severity " +
				"vulnerableVersionRange } pageInfo {endCursor hasNextPage } " +
					"totalCount }";

		String query = StringBundler.concat(
			"{\"query\": \"{ securityVulnerabilities(", queryArguments, ") ",
			resultArguments, "}\" }");

		StringEntity stringEntity = new StringEntity(
			query, ContentType.APPLICATION_JSON);

		httpPost.setEntity(stringEntity);

		try {
			HttpResponse httpResponse = httpClient.execute(httpPost);

			StatusLine statusLine = httpResponse.getStatusLine();

			if (statusLine.getStatusCode() == 200) {
				JSONObject jsonObject = new JSONObject(
					EntityUtils.toString(httpResponse.getEntity(), "UTF-8"));

				JSONObject dataJSONObject = jsonObject.getJSONObject("data");

				JSONObject securityVulnerabilitiesJSONObject =
					dataJSONObject.getJSONObject("securityVulnerabilities");

				int totalCount = securityVulnerabilitiesJSONObject.getInt(
					"totalCount");

				if (totalCount == 0) {
					return;
				}

				JSONArray nodesJSONArray =
					securityVulnerabilitiesJSONObject.getJSONArray("nodes");

				for (Object tmpObject : nodesJSONArray) {
					JSONObject tmpJSONObject = (JSONObject)tmpObject;

					String vulnerableVersionRange = tmpJSONObject.getString(
						"vulnerableVersionRange");

					String[] vulnerableVersionRangeArray =
						vulnerableVersionRange.split(StringPool.COMMA);

					if (_compareVersion(version, vulnerableVersionRangeArray)) {
						String summary = tmpJSONObject.getJSONObject(
							"advisory"
						).getString(
							"summary"
						);

						addMessage(
							fileName,
							StringBundler.concat(
								"Library '", packageName, "' ", version,
								" contain vulnerabilities by '", summary, "'"));

						return;
					}
				}

				JSONObject pageInfoJSONObject =
					securityVulnerabilitiesJSONObject.getJSONObject("pageInfo");

				if (pageInfoJSONObject.getBoolean("hasNextPage")) {
					_checkIsContainVulnerabilities(
						fileName, packageName, version,
						pageInfoJSONObject.getString("endCursor"), httpClient);
				}
			}
		}
		catch (IOException ioException) {
			throw new RuntimeException(ioException);
		}
	}

	private boolean _compareVersion(
		String version, String[] vulnerableVersionArray) {

		boolean result = false;

		int count = 1;

		for (String vulnerableVersion : vulnerableVersionArray) {
			vulnerableVersion = StringUtil.trim(vulnerableVersion);

			char element = vulnerableVersion.charAt(0);

			StringBundler operatorSB = new StringBundler();

			int i = 0;

			while (element != CharPool.SPACE) {
				operatorSB.append(element);
				i++;
				element = vulnerableVersion.charAt(i);
			}

			String operator = operatorSB.toString();
			String vulnerableVersionNumber = vulnerableVersion.substring(i + 1);

			boolean tmpResult = false;

			if (StringUtil.equals(StringPool.LESS_THAN, operator)) {
				tmpResult = version.compareTo(vulnerableVersionNumber) < 0;
			}
			else if (StringUtil.equals(
						StringPool.LESS_THAN_OR_EQUAL, operator)) {

				tmpResult = version.compareTo(vulnerableVersionNumber) <= 0;
			}
			else if (StringUtil.equals(StringPool.GREATER_THAN, operator)) {
				tmpResult = version.compareTo(vulnerableVersionNumber) > 0;
			}
			else if (StringUtil.equals(
						StringPool.GREATER_THAN_OR_EQUAL, operator)) {

				tmpResult = version.compareTo(vulnerableVersionNumber) >= 0;
			}
			else if (StringUtil.equals(StringPool.EQUAL, operator)) {
				tmpResult = version.compareTo(vulnerableVersionNumber) == 0;
			}

			if (count == 1) {
				result = tmpResult;
				count++;
			}
			else {
				result = result && tmpResult;
			}
		}

		return result;
	}

	private String _getContentByPattern(String content, Pattern pattern) {
		Matcher matcher = pattern.matcher(content);

		if (matcher.find()) {
			return matcher.group(1);
		}

		return null;
	}

	private void _gradleLibraryVersionCheck(String fileName, String content)
		throws IOException {

		int x = content.indexOf("dependencies {");

		if (x == -1) {
			return;
		}

		String dependenciesStatement;

		int y = content.indexOf("}", x);

		while (true) {
			if (y == -1) {
				return;
			}

			dependenciesStatement = content.substring(x, y + 1);

			int level = getLevel(
				dependenciesStatement, StringPool.OPEN_CURLY_BRACE,
				StringPool.CLOSE_CURLY_BRACE);

			if (level == 0) {
				break;
			}

			y++;
		}

		CloseableHttpClient httpClient = HttpClients.createDefault();

		try (UnsyncBufferedReader unsyncBufferedReader =
				new UnsyncBufferedReader(
					new UnsyncStringReader(dependenciesStatement))) {

			String line = StringPool.BLANK;

			while ((line = unsyncBufferedReader.readLine()) != null) {
				line = StringUtil.trim(line);

				if (Validator.isNull(line) ||
					!line.matches(
						"(compile|compileInclude|compileOnly|classpath) .+")) {

					continue;
				}

				String group = _getContentByPattern(line, _gradleGroupPattern);
				String name = _getContentByPattern(line, _gradleNamePattern);
				String version = _getContentByPattern(
					line, _gradleVersionPattern);

				if (Validator.isNull(group) || Validator.isNull(name) ||
					Validator.isNull(version)) {

					x++;

					continue;
				}

				_checkIsContainVulnerabilities(
					fileName, group + StringPool.COLON + name, version,
					httpClient);
			}
		}
		finally {
			try {
				httpClient.close();
			}
			catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}

	private void _propertiesLibraryVersionCheck(String fileName, String content)
		throws IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();

		try (UnsyncBufferedReader unsyncBufferedReader =
				new UnsyncBufferedReader(new UnsyncStringReader(content))) {

			String line = StringPool.BLANK;

			while ((line = unsyncBufferedReader.readLine()) != null) {
				if (Validator.isNull(line)) {
					continue;
				}

				int equalIndex = line.indexOf(StringPool.EQUAL);

				if (equalIndex == -1) {
					continue;
				}

				String libraryAndVersion = line.substring(equalIndex + 1);

				int colonIndex = libraryAndVersion.lastIndexOf(
					StringPool.COLON);

				if (colonIndex == -1) {
					continue;
				}

				_checkIsContainVulnerabilities(
					fileName, libraryAndVersion.substring(0, colonIndex),
					libraryAndVersion.substring(colonIndex + 1), httpClient);
			}
		}
		finally {
			try {
				httpClient.close();
			}
			catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}

	private void _xmlLibraryVersionCheck(String fileName, String content) {
		int x = -1;

		CloseableHttpClient httpClient = HttpClients.createDefault();

		while (true) {
			x = content.indexOf("<dependency", x);

			if (x == -1) {
				break;
			}

			char nextChar = content.charAt(x + "<dependency".length());

			String group;
			String artifact;
			String version;

			if (nextChar == CharPool.GREATER_THAN) {
				int closeTagIndex = content.indexOf("</dependency>", x);

				if (closeTagIndex == -1) {
					x++;

					continue;
				}

				String dependencyTagStatement = content.substring(
					x, closeTagIndex);

				group = _getContentByPattern(
					dependencyTagStatement, _xmlGroupIdPattern);
				artifact = _getContentByPattern(
					dependencyTagStatement, _xmlArtifactIdPattern);
				version = _getContentByPattern(
					dependencyTagStatement, _xmlVersionPattern1);
			}
			else if ((nextChar == CharPool.SPACE) ||
					 (nextChar == CharPool.NEW_LINE)) {

				int closeGreaterThanIndex = content.indexOf("/>", x);

				if (closeGreaterThanIndex == -1) {
					x++;

					continue;
				}

				String dependencyStatement = content.substring(
					x, closeGreaterThanIndex);

				group = _getContentByPattern(
					dependencyStatement, _xmlOrgPattern);
				artifact = _getContentByPattern(
					dependencyStatement, _xmlNamePattern);
				version = _getContentByPattern(
					dependencyStatement, _xmlVersionPattern2);
			}
			else {
				x++;

				continue;
			}

			if (Validator.isNull(group) || Validator.isNull(artifact) ||
				Validator.isNull(version)) {

				x++;

				continue;
			}

			_checkIsContainVulnerabilities(
				fileName, group + StringPool.COLON + artifact, version,
				httpClient);

			x++;
		}

		try {
			httpClient.close();
		}
		catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	private static final Pattern _gradleGroupPattern = Pattern.compile(
		"group: \"([^,\n\\\\)]+)\"");
	private static final Pattern _gradleNamePattern = Pattern.compile(
		"name: \"([^,\n\\\\)]+)\"");
	private static final Pattern _gradleVersionPattern = Pattern.compile(
		"version: \"([^,\n\\\\)]+)\"");
	private static final Pattern _xmlArtifactIdPattern = Pattern.compile(
		"<artifactId>(.+)</artifactId>");
	private static final Pattern _xmlGroupIdPattern = Pattern.compile(
		"<groupId>(.+)</groupId>");
	private static final Pattern _xmlNamePattern = Pattern.compile(
		"name=\"([^ /\n]+)\"");
	private static final Pattern _xmlOrgPattern = Pattern.compile(
		"org=\"([^ /\n]+)\"");
	private static final Pattern _xmlVersionPattern1 = Pattern.compile(
		"<version>(.+)</version>");
	private static final Pattern _xmlVersionPattern2 = Pattern.compile(
		"rev=\"([^ /\n]+)\"");

}