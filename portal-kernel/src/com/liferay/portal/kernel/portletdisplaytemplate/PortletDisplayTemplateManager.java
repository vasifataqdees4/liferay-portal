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

package com.liferay.portal.kernel.portletdisplaytemplate;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Leonardo Barros
 */
@ProviderType
public interface PortletDisplayTemplateManager {

	public static final String DISPLAY_STYLE_PREFIX = "ddmTemplate_";

	public static final String ENTRIES = "entries";

	public String renderDDMTemplate(
			long classNameId, Map<String, Object> contextObjects,
			String ddmTemplateKey, List<?> entries, long groupId,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, boolean useDefault)
		throws Exception;

}