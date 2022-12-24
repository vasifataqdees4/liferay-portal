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

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.json.JSONObjectImpl;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.tools.GitException;
import com.liferay.source.formatter.SourceFormatterArgs;
import com.liferay.source.formatter.check.util.SourceUtil;
import com.liferay.source.formatter.processor.SourceProcessor;
import com.liferay.source.formatter.upgrade.GradleBuildFile;
import com.liferay.source.formatter.upgrade.GradleDependency;
import com.liferay.source.formatter.util.FileUtil;

import java.io.File;
import java.io.StringReader;

import java.net.URL;
import java.net.URLConnection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.apache.maven.artifact.versioning.InvalidVersionSpecificationException;
import org.apache.maven.artifact.versioning.VersionRange;

import org.dom4j.Document;
import org.dom4j.Element;

/**
 * @author Qi Zhang
 */
public class LibraryVulnerabilitiesCheck extends BaseFileCheck {

	@Override
	public boolean isLiferaySourceCheck() {
		return true;
	}

	@Override
	protected String doProcess(
			String fileName, String absolutePath, String content)
		throws Exception {

		if (fileName.endsWith(".gradle")) {
			_checkGradleLibraryVulnerabilities(fileName, absolutePath, content);
		}
		else if (fileName.endsWith(".json")) {
			_checkJsonLibraryVulnerabilities(fileName, absolutePath, content);
		}
		else if (fileName.endsWith(".properties")) {
			_checkPropertiesLibraryVulnerabilities(
				fileName, absolutePath, content);
		}
		else if (fileName.endsWith("ivy.xml")) {
			_checkIvyXmlLibraryVulnerabilities(fileName, absolutePath, content);
		}
		else if (fileName.endsWith("pom.xml")) {
			_checkPomXmlLibraryVulnerabilities(fileName, absolutePath, content);
		}

		return content;
	}

	private void _checkGradleLibraryVulnerabilities(
			String fileName, String absolutePath, String content)
		throws Exception {

		GradleBuildFile gradleBuildFile = new GradleBuildFile(content);

		List<GradleDependency> gradleDependencies =
			gradleBuildFile.getGradleDependencies();

		gradleDependencies.addAll(gradleBuildFile.getBuildScriptDependencies());

		Iterator<GradleDependency> iterator = gradleDependencies.iterator();

		while (iterator.hasNext()) {
			GradleDependency gradleDependency = iterator.next();

			String gradleDependencyGroup = gradleDependency.getGroup();
			String gradleDependencyName = gradleDependency.getName();
			String gradleDependencyVersion = gradleDependency.getVersion();

			if (Validator.isNull(gradleDependencyGroup) ||
				Validator.isNull(gradleDependencyName) ||
				Validator.isNull(gradleDependencyVersion)) {

				continue;
			}

			_checkVulnerabilities(
				fileName, absolutePath,
				gradleDependencyGroup + StringPool.COLON + gradleDependencyName,
				gradleDependencyVersion, SecurityAdvisoryEcosystemEnum.MAVEN);
		}
	}

	private void _checkIvyXmlLibraryVulnerabilities(
			String fileName, String absolutePath, String content)
		throws Exception {

		if (Validator.isNull(content)) {
			return;
		}

		Document document = SourceUtil.readXML(content);

		Element rootElement = document.getRootElement();

		for (Element dependenciesElement :
				(List<Element>)rootElement.elements("dependencies")) {

			for (Element dependencyElement :
					(List<Element>)dependenciesElement.elements("dependency")) {

				String name = dependencyElement.attributeValue("name");
				String org = dependencyElement.attributeValue("org");
				String rev = dependencyElement.attributeValue("rev");

				if (Validator.isNull(name) || Validator.isNull(org) ||
					Validator.isNull(rev)) {

					continue;
				}

				_checkVulnerabilities(
					fileName, absolutePath, org + StringPool.COLON + name, rev,
					SecurityAdvisoryEcosystemEnum.MAVEN);
			}
		}
	}

	private void _checkJsonLibraryVulnerabilities(
			String fileName, String absolutePath, String content)
		throws Exception {

		if (Validator.isNull(content)) {
			return;
		}

		try {
			JSONObject contentJSONObject = new JSONObjectImpl(content);

			_checkVersionInJsonFile(
				fileName, absolutePath,
				contentJSONObject.getJSONObject("dependencies"));
			_checkVersionInJsonFile(
				fileName, absolutePath,
				contentJSONObject.getJSONObject("devDependencies"));
		}
		catch (JSONException jsonException) {
			if (_log.isDebugEnabled()) {
				_log.debug(jsonException);
			}
		}
	}

	private void _checkPomXmlLibraryVulnerabilities(
			String fileName, String absolutePath, String content)
		throws Exception {

		if (Validator.isNull(content)) {
			return;
		}

		Document document = SourceUtil.readXML(content);

		Element rootElement = document.getRootElement();

		for (Element dependenciesElement :
				(List<Element>)rootElement.elements("dependencies")) {

			for (Element dependencyElement :
					(List<Element>)dependenciesElement.elements("dependency")) {

				Element artifactIdElement = dependencyElement.element(
					"artifactId");
				Element groupIdElement = dependencyElement.element("groupId");
				Element versionElement = dependencyElement.element("version");

				if ((artifactIdElement == null) || (groupIdElement == null) ||
					(versionElement == null)) {

					continue;
				}

				String version = versionElement.getText();

				if (version.startsWith(StringPool.DOLLAR)) {
					continue;
				}

				_checkVulnerabilities(
					fileName, absolutePath,
					groupIdElement.getText() + StringPool.COLON +
						artifactIdElement.getText(),
					version, SecurityAdvisoryEcosystemEnum.MAVEN);
			}
		}

		for (Element buildsElement :
				(List<Element>)rootElement.elements("build")) {

			for (Element pluginsElement :
					(List<Element>)buildsElement.elements("plugins")) {

				for (Element pluginElement :
						(List<Element>)pluginsElement.elements("plugin")) {

					Element artifactIdElement = pluginElement.element(
						"artifactId");
					Element groupIdElement = pluginElement.element("groupId");
					Element versionElement = pluginElement.element("version");

					if ((artifactIdElement == null) ||
						(groupIdElement == null) || (versionElement == null)) {

						continue;
					}

					String version = versionElement.getText();

					if (version.startsWith(StringPool.DOLLAR)) {
						continue;
					}

					_checkVulnerabilities(
						fileName, absolutePath,
						groupIdElement.getText() + StringPool.COLON +
							artifactIdElement.getText(),
						version, SecurityAdvisoryEcosystemEnum.MAVEN);
				}
			}
		}
	}

	private void _checkPropertiesLibraryVulnerabilities(
			String fileName, String absolutePath, String content)
		throws Exception {

		Properties properties = new Properties();

		properties.load(new StringReader(content));

		Enumeration<String> enumeration =
			(Enumeration<String>)properties.propertyNames();

		while (enumeration.hasMoreElements()) {
			String key = enumeration.nextElement();

			String value = properties.getProperty(key);

			if (Validator.isNull(value)) {
				continue;
			}

			String[] dependency = value.split(StringPool.COLON);

			if (dependency.length < 3) {
				continue;
			}

			_checkVulnerabilities(
				fileName, absolutePath,
				dependency[0] + StringPool.COLON + dependency[1], dependency[2],
				SecurityAdvisoryEcosystemEnum.MAVEN);
		}
	}

	private void _checkVersionInJsonFile(
			String fileName, String absolutePath, JSONObject jsonObject)
		throws Exception {

		if (jsonObject == null) {
			return;
		}

		for (String dependencyName : jsonObject.keySet()) {
			String version = jsonObject.getString(dependencyName);

			if (version.startsWith("^") || version.startsWith("~") ||
				version.startsWith("*")) {

				continue;
			}

			_checkVulnerabilities(
				fileName, absolutePath, dependencyName, version,
				SecurityAdvisoryEcosystemEnum.NPM);
		}
	}

	private void _checkVulnerabilities(
		String fileName, String packageName,
		SecurityAdvisoryEcosystemEnum securityAdvisoryEcosystemEnum,
		DefaultArtifactVersion version) {

		List<SecurityVulnerabilityNode> securityVulnerabilityNodes =
			_cachedVulnerableVersionMap.get(
				securityAdvisoryEcosystemEnum + ":" + packageName);

		for (SecurityVulnerabilityNode securityVulnerabilityNode :
				securityVulnerabilityNodes) {

			VersionRange versionRange =
				securityVulnerabilityNode.getVersionRange();

			if (versionRange.containsVersion(version)) {
				addMessage(
					fileName,
					StringBundler.concat(
						"Library '", packageName, ":", version.toString(),
						"' contains known vulnerabilities(",
						securityVulnerabilityNode.getSummary(), ", ",
						securityVulnerabilityNode.getPermalink(), ")"));

				return;
			}
		}
	}

	private void _checkVulnerabilities(
			String fileName, String absolutePath, String packageName,
			String version,
			SecurityAdvisoryEcosystemEnum securityAdvisoryEcosystemEnum)
		throws Exception {

		if (!version.matches("(\\d|v).+")) {
			return;
		}

		String cachedLibraryVulnerabilitiesContent =
			_getCachedLibraryVulnerabilitiesContent();

		int x = cachedLibraryVulnerabilitiesContent.indexOf(
			StringBundler.concat(
				securityAdvisoryEcosystemEnum, StringPool.COMMA, packageName,
				StringPool.COMMA, version));

		if (x != -1) {
			String cachedLibraryVulnerabilities;

			int y = cachedLibraryVulnerabilitiesContent.indexOf(
				StringPool.NEW_LINE, x);

			if (y != -1) {
				cachedLibraryVulnerabilities =
					cachedLibraryVulnerabilitiesContent.substring(x, y);
			}
			else {
				cachedLibraryVulnerabilities =
					cachedLibraryVulnerabilitiesContent.substring(x);
			}

			String[] parts = StringUtil.split(cachedLibraryVulnerabilities);

			if (parts.length > 3) {
				addMessage(
					fileName,
					StringBundler.concat(
						"Library '", packageName, ":", version,
						"' contains known vulnerabilities(", parts[3], ", ",
						parts[4], ")"));
			}

			return;
		}

		if (!_cachedVulnerableVersionMap.containsKey(
				securityAdvisoryEcosystemEnum + ":" + packageName)) {

			_generateVulnerableVersionMap(
				packageName, securityAdvisoryEcosystemEnum,
				getAttributeValues(_SEVERITIES, absolutePath));
		}

		_checkVulnerabilities(
			fileName, packageName, securityAdvisoryEcosystemEnum,
			new DefaultArtifactVersion(version));
	}

	private void _generateVulnerableVersionMap(
			String packageName,
			SecurityAdvisoryEcosystemEnum securityAdvisoryEcosystemEnum,
			List<String> severities)
		throws Exception {

		if (_cachedVulnerableVersionMap.containsKey(
				securityAdvisoryEcosystemEnum + ":" + packageName)) {

			return;
		}

		if (Validator.isNull(_githubAccessToken)) {
			SourceProcessor sourceProcessor = getSourceProcessor();

			SourceFormatterArgs sourceFormatterArgs =
				sourceProcessor.getSourceFormatterArgs();

			if (sourceFormatterArgs.isFormatCurrentBranch() &&
				sourceFormatterArgs.isUseCiGithubAccessToken()) {

				_githubAccessToken = _getCiGithubAccessToken();
			}
			else {
				_githubAccessToken = _getLocalGithubAccessToken();
			}
		}

		_cachedVulnerableVersionMap.put(
			securityAdvisoryEcosystemEnum + ":" + packageName,
			_getSecurityVulnerabilityNodes(
				packageName, null, securityAdvisoryEcosystemEnum, severities,
				_githubAccessToken));
	}

	private synchronized String _getCachedLibraryVulnerabilitiesContent()
		throws Exception {

		if (Validator.isNotNull(_cachedLibraryVulnerabilitiesContent)) {
			return _cachedLibraryVulnerabilitiesContent;
		}

		_cachedLibraryVulnerabilitiesContent = StringPool.BLANK;

		File file = new File(
			System.getProperty("user.home") +
				"/cachedLibraryVulnerabilities.text");

		if (!file.exists()) {
			return _cachedLibraryVulnerabilitiesContent;
		}

		_cachedLibraryVulnerabilitiesContent = FileUtil.read(file);

		return _cachedLibraryVulnerabilitiesContent;
	}

	private synchronized String _getCiGithubAccessToken() throws Exception {
		if (_githubAccessToken != null) {
			return _githubAccessToken;
		}

		URL urlObject = new URL(_CI_PROPERTIES_URL);

		URLConnection urlConnection = urlObject.openConnection();

		urlConnection.connect();

		Properties properties = new Properties();

		properties.load(urlConnection.getInputStream());

		_githubAccessToken = properties.getProperty("github.access.token");

		if (Validator.isNull(_githubAccessToken)) {
			throw new Exception(
				"Can not find ci access token in " + _GITHUB_TOKEN_FILE_PATH);
		}

		return _githubAccessToken;
	}

	private synchronized String _getLocalGithubAccessToken() throws Exception {
		if (_githubAccessToken != null) {
			return _githubAccessToken;
		}

		File file = new File(_GITHUB_TOKEN_FILE_PATH);

		if (!file.exists()) {
			throw new Exception(_GITHUB_TOKEN_FILE_PATH + " does not exist");
		}

		_githubAccessToken = FileUtil.read(file);

		if (Validator.isNull(_githubAccessToken)) {
			throw new Exception(
				"Can not find ci access token, place the github token in " +
					_GITHUB_TOKEN_FILE_PATH);
		}

		return _githubAccessToken;
	}

	private List<SecurityVulnerabilityNode> _getSecurityVulnerabilityNodes(
			String packageName, String cursor,
			SecurityAdvisoryEcosystemEnum securityAdvisoryEcosystemEnum,
			List<String> severities, String githubToken)
		throws Exception {

		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

		try (CloseableHttpClient closeableHttpClient =
				httpClientBuilder.build()) {

			String queryArguments = StringBundler.concat(
				"first: 100, package:\\\"", packageName, "\\\", ecosystem: ",
				securityAdvisoryEcosystemEnum.name(), ", severities: ",
				severities);

			if (Validator.isNotNull(cursor)) {
				queryArguments += "after: \\\"" + cursor + "\\\"";
			}

			String resultArguments =
				"{nodes { advisory {summary, permalink} package {name} " +
					"severity vulnerableVersionRange } pageInfo {endCursor " +
						"hasNextPage } totalCount }";

			HttpPost httpPost = new HttpPost("https://api.github.com/graphql");

			httpPost.setEntity(
				new StringEntity(
					StringBundler.concat(
						"{\"query\": \"{ securityVulnerabilities(",
						queryArguments, ") ", resultArguments, "}\" }"),
					ContentType.APPLICATION_JSON));
			httpPost.addHeader("Authorization", "bearer " + githubToken);
			httpPost.addHeader(
				"Content-Type",
				"application/json; charset=utf-8; application/graphql");

			CloseableHttpResponse closeableHttpResponse =
				closeableHttpClient.execute(httpPost);

			StatusLine statusLine = closeableHttpResponse.getStatusLine();

			if (statusLine.getStatusCode() != HttpStatus.SC_OK) {
				throw new GitException(
					"Unable to access GitHub GraphQL API, check the github " +
						"token in " + _GITHUB_TOKEN_FILE_PATH);
			}

			JSONObject jsonObject = new JSONObjectImpl(
				EntityUtils.toString(
					closeableHttpResponse.getEntity(), "UTF-8"));

			JSONObject dataJSONObject = jsonObject.getJSONObject("data");

			JSONObject securityVulnerabilitiesJSONObject =
				dataJSONObject.getJSONObject("securityVulnerabilities");

			int totalCount = securityVulnerabilitiesJSONObject.getInt(
				"totalCount");

			if (totalCount == 0) {
				return Collections.emptyList();
			}

			List<SecurityVulnerabilityNode> securityVulnerabilityNodes =
				new ArrayList<>();

			JSONArray nodesJSONArray =
				securityVulnerabilitiesJSONObject.getJSONArray("nodes");

			Iterator<JSONObject> iterator = nodesJSONArray.iterator();

			while (iterator.hasNext()) {
				JSONObject nodeJSONObject = iterator.next();

				SecurityVulnerabilityNode securityVulnerabilityNode =
					new SecurityVulnerabilityNode();

				JSONObject advisoryJSONObject = nodeJSONObject.getJSONObject(
					"advisory");

				securityVulnerabilityNode.setPermalink(
					advisoryJSONObject.getString("permalink"));
				securityVulnerabilityNode.setSummary(
					advisoryJSONObject.getString("summary"));

				securityVulnerabilityNode.setVersionRange(
					nodeJSONObject.getString("vulnerableVersionRange"));

				securityVulnerabilityNodes.add(securityVulnerabilityNode);
			}

			JSONObject pageInfoJSONObject =
				securityVulnerabilitiesJSONObject.getJSONObject("pageInfo");

			if (pageInfoJSONObject.getBoolean("hasNextPage")) {
				securityVulnerabilityNodes.addAll(
					_getSecurityVulnerabilityNodes(
						packageName, pageInfoJSONObject.getString("endCursor"),
						securityAdvisoryEcosystemEnum, severities,
						githubToken));
			}

			if (!securityVulnerabilityNodes.isEmpty()) {
				return securityVulnerabilityNodes;
			}
		}

		return Collections.emptyList();
	}

	private static final String _CI_PROPERTIES_URL =
		"http://mirrors.lax.liferay.com/github.com/liferay/liferay-jenkins-" +
			"ee/commands/build.properties";

	private static final String _GITHUB_TOKEN_FILE_PATH =
		System.getProperty("user.home") + "/github.token";

	private static final String _SEVERITIES = "severities";

	private static final Log _log = LogFactoryUtil.getLog(
		LibraryVulnerabilitiesCheck.class);

	private String _cachedLibraryVulnerabilitiesContent;
	private final Map<String, List<SecurityVulnerabilityNode>>
		_cachedVulnerableVersionMap = new ConcurrentHashMap<>();
	private String _githubAccessToken;

	private static class SecurityVulnerabilityNode {

		public String getPermalink() {
			return _permalink;
		}

		public String getSummary() {
			return _summary;
		}

		public VersionRange getVersionRange() {
			return _versionRange;
		}

		public void setPermalink(String permalink) {
			_permalink = permalink;
		}

		public void setSummary(String summary) {
			_summary = summary;
		}

		public void setVersionRange(String vulnerableVersionRange)
			throws InvalidVersionSpecificationException {

			if (!vulnerableVersionRange.contains(StringPool.COMMA)) {
				String[] versionArray = vulnerableVersionRange.split(
					StringPool.SPACE, 2);

				if (versionArray[0].equals(StringPool.EQUAL)) {
					_versionRange = VersionRange.createFromVersion(
						versionArray[1]);
				}
				else if (versionArray[0].equals(StringPool.LESS_THAN)) {
					_versionRange = VersionRange.createFromVersionSpec(
						"(," + versionArray[1] + ")");
				}
				else if (versionArray[0].equals(
							StringPool.LESS_THAN_OR_EQUAL)) {

					_versionRange = VersionRange.createFromVersionSpec(
						"(," + versionArray[1] + "]");
				}
				else if (versionArray[0].equals(StringPool.GREATER_THAN)) {
					_versionRange = VersionRange.createFromVersionSpec(
						"(" + versionArray[1] + ",)");
				}
				else if (versionArray[0].equals(
							StringPool.GREATER_THAN_OR_EQUAL)) {

					_versionRange = VersionRange.createFromVersionSpec(
						"[" + versionArray[1] + ",)");
				}
			}
			else {
				vulnerableVersionRange = vulnerableVersionRange.replaceAll(
					"([=<>]+.+?, )([=<>]+)(.+)", "$1$3$2");

				vulnerableVersionRange = StringUtil.replace(
					vulnerableVersionRange,
					new String[] {
						StringPool.GREATER_THAN_OR_EQUAL,
						StringPool.GREATER_THAN, StringPool.LESS_THAN_OR_EQUAL,
						StringPool.LESS_THAN
					},
					new String[] {
						StringPool.OPEN_BRACKET, StringPool.OPEN_PARENTHESIS,
						StringPool.CLOSE_BRACKET, StringPool.CLOSE_PARENTHESIS
					});

				_versionRange = VersionRange.createFromVersionSpec(
					vulnerableVersionRange);
			}
		}

		private String _permalink;
		private String _summary;
		private VersionRange _versionRange;

	}

	private enum SecurityAdvisoryEcosystemEnum {

		MAVEN, NPM

	}

}