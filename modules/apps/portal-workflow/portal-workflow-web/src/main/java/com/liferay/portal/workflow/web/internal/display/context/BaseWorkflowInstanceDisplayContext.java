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

package com.liferay.portal.workflow.web.internal.display.context;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.DefaultWorkflowNode;
import com.liferay.portal.kernel.workflow.WorkflowInstance;
import com.liferay.portal.kernel.workflow.WorkflowNode;
import com.liferay.portal.workflow.web.internal.display.context.helper.WorkflowInstanceRequestHelper;

import java.text.Format;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Marcellus Tavares
 */
public abstract class BaseWorkflowInstanceDisplayContext {

	public BaseWorkflowInstanceDisplayContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse) {

		this.liferayPortletRequest = liferayPortletRequest;
		this.liferayPortletResponse = liferayPortletResponse;

		httpServletRequest = PortalUtil.getHttpServletRequest(
			liferayPortletRequest);

		portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(
			httpServletRequest);

		ThemeDisplay themeDisplay =
			(ThemeDisplay)liferayPortletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(
			themeDisplay.getLocale(), themeDisplay.getTimeZone());

		workflowInstanceRequestHelper = new WorkflowInstanceRequestHelper(
			httpServletRequest);
	}

	public String getStatus(WorkflowInstance workflowInstance) {
		List<WorkflowNode> currentWorkflowNodes =
			workflowInstance.getCurrentWorkflowNodes();

		if (currentWorkflowNodes.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(2 * currentWorkflowNodes.size());

		for (WorkflowNode currentWorkflowNode : currentWorkflowNodes) {
			DefaultWorkflowNode defaultWorkflowNode =
				(DefaultWorkflowNode)currentWorkflowNode;

			sb.append(
				defaultWorkflowNode.getLabel(
					workflowInstanceRequestHelper.getLocale()));

			sb.append(StringPool.COMMA_AND_SPACE);
		}

		sb.setIndex(sb.index() - 1);

		return sb.toString();
	}

	protected final Format dateFormatDateTime;
	protected final HttpServletRequest httpServletRequest;
	protected final LiferayPortletRequest liferayPortletRequest;
	protected final LiferayPortletResponse liferayPortletResponse;
	protected final PortalPreferences portalPreferences;
	protected final WorkflowInstanceRequestHelper workflowInstanceRequestHelper;

}