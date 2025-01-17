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

package com.liferay.headless.delivery.internal.dto.v1_0.mapper.util;

import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Eudaldo Alonso
 */
public class LocalizedValueUtil {

	public static List<String> getAvailableLanguageIds() {
		return TransformUtil.transform(
			LanguageUtil.getAvailableLocales(), LanguageUtil::getLanguageId);
	}

	public static Map<String, String> toLocalizedValues(JSONObject jsonObject) {
		return new HashMap<String, String>() {
			{
				List<String> availableLanguageIds = getAvailableLanguageIds();

				for (String key : jsonObject.keySet()) {
					if (availableLanguageIds.contains(key)) {
						put(key, jsonObject.getString(key));
					}
				}
			}
		};
	}

}