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

package com.liferay.layout.content.page.editor.web.internal.model.listener;

import com.liferay.layout.page.template.model.LayoutPageTemplateStructure;
import com.liferay.layout.service.LayoutClassedModelUsageLocalService;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.util.Portal;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(service = ModelListener.class)
public class LayoutPageTemplateStructureModelListener
	extends BaseModelListener<LayoutPageTemplateStructure> {

	@Override
	public void onBeforeRemove(
			LayoutPageTemplateStructure layoutPageTemplateStructure)
		throws ModelListenerException {

		_layoutClassedModelUsageLocalService.deleteLayoutClassedModelUsages(
			String.valueOf(
				layoutPageTemplateStructure.getLayoutPageTemplateStructureId()),
			_getLayoutPageTemplateStructureClassNameId(),
			layoutPageTemplateStructure.getPlid());
	}

	private long _getLayoutPageTemplateStructureClassNameId() {
		if (_layoutPageTemplateStructureNameId != null) {
			return _layoutPageTemplateStructureNameId;
		}

		_layoutPageTemplateStructureNameId = _portal.getClassNameId(
			LayoutPageTemplateStructure.class.getName());

		return _layoutPageTemplateStructureNameId;
	}

	@Reference
	private LayoutClassedModelUsageLocalService
		_layoutClassedModelUsageLocalService;

	private Long _layoutPageTemplateStructureNameId;

	@Reference
	private Portal _portal;

}