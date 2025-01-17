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

package com.liferay.object.field.util;

import com.liferay.object.constants.ObjectFieldConstants;
import com.liferay.object.model.ObjectField;
import com.liferay.object.model.ObjectFieldSetting;
import com.liferay.object.service.ObjectFieldLocalServiceUtil;
import com.liferay.portal.vulcan.util.LocalizedMapUtil;

import java.util.Collections;
import java.util.List;

/**
 * @author Guilherme Camacho
 */
public class ObjectFieldUtil {

	public static ObjectField addCustomObjectField(ObjectField objectField)
		throws Exception {

		return ObjectFieldLocalServiceUtil.addCustomObjectField(
			objectField.getExternalReferenceCode(), objectField.getUserId(),
			objectField.getListTypeDefinitionId(),
			objectField.getObjectDefinitionId(), objectField.getBusinessType(),
			objectField.getDBType(), objectField.isIndexed(),
			objectField.isIndexedAsKeyword(),
			objectField.getIndexedLanguageId(), objectField.getLabelMap(),
			objectField.isLocalized(), objectField.getName(),
			objectField.getReadOnly(),
			objectField.getReadOnlyConditionExpression(),
			objectField.isRequired(), objectField.isState(),
			objectField.getObjectFieldSettings());
	}

	public static ObjectField createObjectField(
		long listTypeDefinitionId, String businessType, String dbColumnName,
		String dbType, boolean indexed, boolean indexedAsKeyword,
		String indexedLanguageId, String label, String name, boolean required,
		boolean system) {

		return createObjectField(
			businessType, dbColumnName, dbType, indexed, indexedAsKeyword,
			indexedLanguageId, label, listTypeDefinitionId, name,
			Collections.emptyList(), ObjectFieldConstants.READ_ONLY_FALSE, null,
			required, system);
	}

	public static ObjectField createObjectField(
		String businessType, String dbType, boolean indexed,
		boolean indexedAsKeyword, String indexedLanguageId, String label,
		String name, boolean required) {

		return createObjectField(
			0, businessType, null, dbType, indexed, indexedAsKeyword,
			indexedLanguageId, label, name, required, false);
	}

	public static ObjectField createObjectField(
		String businessType, String dbType, boolean indexed,
		boolean indexedAsKeyword, String indexedLanguageId, String label,
		String name, List<ObjectFieldSetting> objectFieldSettings,
		boolean required) {

		return createObjectField(
			businessType, null, dbType, indexed, indexedAsKeyword,
			indexedLanguageId, label, 0, name, objectFieldSettings,
			ObjectFieldConstants.READ_ONLY_FALSE, null, required, false);
	}

	public static ObjectField createObjectField(
		String businessType, String dbType, String name) {

		return createObjectField(businessType, dbType, name, name, false);
	}

	public static ObjectField createObjectField(
		String businessType, String dbColumnName, String dbType,
		boolean indexed, boolean indexedAsKeyword, String indexedLanguageId,
		String label, long listTypeDefinitionId, String name,
		List<ObjectFieldSetting> objectFieldSettings, String readOnly,
		String readOnlyConditionExpression, boolean required, boolean system) {

		ObjectField objectField = ObjectFieldLocalServiceUtil.createObjectField(
			0);

		objectField.setListTypeDefinitionId(listTypeDefinitionId);
		objectField.setBusinessType(businessType);
		objectField.setDBColumnName(dbColumnName);
		objectField.setDBType(dbType);
		objectField.setIndexed(indexed);
		objectField.setIndexedAsKeyword(indexedAsKeyword);
		objectField.setIndexedLanguageId(indexedLanguageId);
		objectField.setLabelMap(LocalizedMapUtil.getLocalizedMap(label));
		objectField.setName(name);
		objectField.setObjectFieldSettings(objectFieldSettings);
		objectField.setReadOnly(readOnly);
		objectField.setReadOnlyConditionExpression(readOnlyConditionExpression);
		objectField.setRequired(required);
		objectField.setSystem(system);

		return objectField;
	}

	public static ObjectField createObjectField(
		String businessType, String dbType, String name,
		List<ObjectFieldSetting> objectFieldSettings) {

		return createObjectField(
			businessType, null, dbType, false, false, null, name, 0, name,
			objectFieldSettings, ObjectFieldConstants.READ_ONLY_FALSE, null,
			false, false);
	}

	public static ObjectField createObjectField(
		String businessType, String dbType, String label, String name) {

		return createObjectField(businessType, dbType, label, name, false);
	}

	public static ObjectField createObjectField(
		String businessType, String dbType, String label, String name,
		boolean required) {

		return createObjectField(
			0, businessType, null, dbType, false, false, null, label, name,
			required, false);
	}

	public static ObjectField createObjectField(
		String businessType, String dbType, String label, String name,
		List<ObjectFieldSetting> objectFieldSettings) {

		return createObjectField(
			businessType, null, dbType, false, false, null, label, 0, name,
			objectFieldSettings, ObjectFieldConstants.READ_ONLY_FALSE, null,
			false, false);
	}

}