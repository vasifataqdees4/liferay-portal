/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.search.tuning.rankings.web.internal.application.list;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.search.engine.SearchEngineInformation;
import com.liferay.portal.search.tuning.rankings.web.internal.constants.ResultRankingsPortletKeys;
import com.liferay.portal.search.tuning.web.application.list.constants.SearchTuningPanelCategoryKeys;

import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Filipe Oshiro
 */
@Component(
	property = {
		"panel.app.order:Integer=400",
		"panel.category.key=" + SearchTuningPanelCategoryKeys.CONTROL_PANEL_SEARCH_TUNING
	},
	service = PanelApp.class
)
public class ResultRankingsPanelApp extends BasePanelApp {

	@Override
	public Portlet getPortlet() {
		return _portlet;
	}

	@Override
	public String getPortletId() {
		return ResultRankingsPortletKeys.RESULT_RANKINGS;
	}

	@Override
	public boolean isShow(PermissionChecker permissionChecker, Group group)
		throws PortalException {

		if (Objects.equals(searchEngineInformation.getVendorString(), "Solr")) {
			return false;
		}

		return super.isShow(permissionChecker, group);
	}

	@Reference
	protected SearchEngineInformation searchEngineInformation;

	@Reference(
		target = "(javax.portlet.name=" + ResultRankingsPortletKeys.RESULT_RANKINGS + ")"
	)
	private Portlet _portlet;

}