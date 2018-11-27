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

package com.liferay.wiki.uad.exporter;

import com.liferay.petra.string.StringBundler;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;

import com.liferay.user.associated.data.exporter.DynamicQueryUADExporter;

import com.liferay.wiki.model.WikiNode;
import com.liferay.wiki.service.WikiNodeLocalService;
import com.liferay.wiki.uad.constants.WikiUADConstants;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the wiki node UAD exporter.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom service methods should be put in
 * {@link WikiNodeUADExporter}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BaseWikiNodeUADExporter extends DynamicQueryUADExporter<WikiNode> {
	@Override
	public Class<WikiNode> getTypeClass() {
		return WikiNode.class;
	}

	@Override
	protected ActionableDynamicQuery doGetActionableDynamicQuery() {
		return wikiNodeLocalService.getActionableDynamicQuery();
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return WikiUADConstants.USER_ID_FIELD_NAMES_WIKI_NODE;
	}

	@Override
	protected String toXmlString(WikiNode wikiNode) {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("com.liferay.wiki.model.WikiNode");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>nodeId</column-name><column-value><![CDATA[");
		sb.append(wikiNode.getNodeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUserId</column-name><column-value><![CDATA[");
		sb.append(wikiNode.getStatusByUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUserName</column-name><column-value><![CDATA[");
		sb.append(wikiNode.getStatusByUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(wikiNode.getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(wikiNode.getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(wikiNode.getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(wikiNode.getDescription());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	@Reference
	protected WikiNodeLocalService wikiNodeLocalService;
}