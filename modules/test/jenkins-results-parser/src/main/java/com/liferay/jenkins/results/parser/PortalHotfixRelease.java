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

package com.liferay.jenkins.results.parser;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

/**
 * @author Michael Hashimoto
 */
public class PortalHotfixRelease {

	public PortalHotfixRelease(JSONObject jsonObject) {
		_portalHotfixReleaseURL = _getURL(
			jsonObject.getString("portalHotfixURL"));

		if (_portalHotfixReleaseURL == null) {
			throw new RuntimeException("Unable to get Portal Hotfix URL");
		}

		URL portalReleaseURL = _getURL(
			jsonObject.optString("portalReleaseURL"));

		if (portalReleaseURL != null) {
			_portalRelease = new PortalRelease(portalReleaseURL);
		}
		else {
			_portalRelease = null;
		}

		URL portalFixpackURL = _getURL(
			jsonObject.optString("portalFixpackURL"));

		if (portalFixpackURL != null) {
			_portalFixpackRelease = new PortalFixpackRelease(portalFixpackURL);
		}
		else {
			_portalFixpackRelease = null;
		}
	}

	public PortalHotfixRelease(
		URL portalHotfixReleaseURL, PortalFixpackRelease portalFixpackRelease,
		PortalRelease portalRelease) {

		_portalHotfixReleaseURL = portalHotfixReleaseURL;
		_portalFixpackRelease = portalFixpackRelease;
		_portalRelease = portalRelease;
	}

	public JSONObject getJSONObject() {
		JSONObject jsonObject = new JSONObject();

		PortalFixpackRelease portalFixpackRelease = getPortalFixpackRelease();

		if (portalFixpackRelease != null) {
			jsonObject.put(
				"portalFixpackURL", portalFixpackRelease.getPortalFixpackURL());
		}

		jsonObject.put("portalHotfixURL", getPortalHotfixReleaseURL());

		PortalRelease portalRelease = getPortalRelease();

		if (portalRelease != null) {
			jsonObject.put(
				"portalReleaseURL", portalRelease.getPortalBundleTomcatURL());
		}

		return jsonObject;
	}

	public PortalFixpackRelease getPortalFixpackRelease() {
		return _portalFixpackRelease;
	}

	public String getPortalHotfixReleaseName() {
		String portalHotfixReleaseURLString = String.valueOf(
			_portalHotfixReleaseURL);

		Matcher matcher = _hotfixURLPattern.matcher(
			portalHotfixReleaseURLString);

		if (!matcher.find()) {
			return null;
		}

		return matcher.group("hotfixName");
	}

	public URL getPortalHotfixReleaseURL() {
		return _portalHotfixReleaseURL;
	}

	public String getPortalHotfixReleaseVersion() {
		String portalHotfixReleaseURLString = String.valueOf(
			_portalHotfixReleaseURL);

		Matcher matcher = _hotfixURLPattern.matcher(
			portalHotfixReleaseURLString);

		if (!matcher.find()) {
			return null;
		}

		return matcher.group("hotfixVersion");
	}

	public PortalRelease getPortalRelease() {
		return _portalRelease;
	}

	private URL _getURL(String urlString) {
		try {
			return new URL(urlString);
		}
		catch (MalformedURLException malformedURLException) {
			return null;
		}
	}

	private static final Pattern _hotfixURLPattern = Pattern.compile(
		"https?://.+/(?<hotfixName>liferay-(hotfix|security-de|security-dxp)-" +
			"(?<hotfixVersion>\\d+)(-\\d{6}-\\d)?-\\d{4})");

	private final PortalFixpackRelease _portalFixpackRelease;
	private final URL _portalHotfixReleaseURL;
	private final PortalRelease _portalRelease;

}