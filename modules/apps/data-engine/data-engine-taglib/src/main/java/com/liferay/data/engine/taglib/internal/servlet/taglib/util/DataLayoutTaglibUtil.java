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

package com.liferay.data.engine.taglib.internal.servlet.taglib.util;

import com.liferay.data.engine.rest.dto.v2_0.DataDefinition;
import com.liferay.data.engine.rest.resource.v2_0.DataDefinitionResource;
import com.liferay.osgi.util.service.Snapshot;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Gabriel Albuquerque
 * @author Leonardo Barros
 */
public class DataLayoutTaglibUtil {

	public static DataDefinition getDataDefinition(
			long dataDefinitionId, HttpServletRequest httpServletRequest)
		throws Exception {

		DataDefinitionResource dataDefinitionResource =
			_getDataDefinitionResource(httpServletRequest);

		return dataDefinitionResource.getDataDefinition(dataDefinitionId);
	}

	public static JSONArray getFieldTypesJSONArray(
			HttpServletRequest httpServletRequest, Set<String> scopes,
			boolean searchableFieldsDisabled)
		throws Exception {

		JSONArray fieldTypesJSONArray = JSONFactoryUtil.createJSONArray();

		DataDefinitionResource dataDefinitionResource =
			_getDataDefinitionResource(httpServletRequest);

		try {
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray(
				dataDefinitionResource.
					getDataDefinitionDataDefinitionFieldFieldTypes());

			if (SetUtil.isEmpty(scopes)) {
				return jsonArray;
			}

			for (JSONObject jsonObject : (Iterable<JSONObject>)jsonArray) {
				if (ListUtil.exists(
						Arrays.asList(
							StringUtil.split(jsonObject.getString("scope"))),
						scopes::contains)) {

					fieldTypesJSONArray.put(jsonObject);

					if (searchableFieldsDisabled) {
						_setFieldIndexTypeNone(
							jsonObject.getJSONObject("settingsContext"));
					}
				}
			}

			return fieldTypesJSONArray;
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}

			return fieldTypesJSONArray;
		}
	}

	private static DataDefinitionResource _getDataDefinitionResource(
			HttpServletRequest httpServletRequest)
		throws Exception {

		DataDefinitionResource.Factory dataDefinitionResourceFactory =
			_dataDefinitionResourceFactorySnapshot.get();

		DataDefinitionResource.Builder dataDefinitionResourceBuilder =
			dataDefinitionResourceFactory.create();

		return dataDefinitionResourceBuilder.httpServletRequest(
			httpServletRequest
		).user(
			PortalUtil.getUser(httpServletRequest)
		).build();
	}

	private static void _setFieldIndexTypeNone(JSONObject jsonObject) {
		for (JSONObject pageJSONObject :
				(Iterable<JSONObject>)jsonObject.getJSONArray("pages")) {

			for (JSONObject rowJSONObject :
					(Iterable<JSONObject>)pageJSONObject.getJSONArray("rows")) {

				for (JSONObject columnJSONObject :
						(Iterable<JSONObject>)rowJSONObject.getJSONArray(
							"columns")) {

					for (JSONObject fieldJSONObject :
							(Iterable<JSONObject>)columnJSONObject.getJSONArray(
								"fields")) {

						if (Objects.equals(
								fieldJSONObject.getString("fieldName"),
								"indexType")) {

							fieldJSONObject.put("value", "none");

							return;
						}
					}
				}
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DataLayoutTaglibUtil.class);

	private static final Snapshot<DataDefinitionResource.Factory>
		_dataDefinitionResourceFactorySnapshot = new Snapshot<>(
			DataLayoutTaglibUtil.class, DataDefinitionResource.Factory.class);

}