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

package com.liferay.headless.commerce.admin.pricing.internal.odata.entity.v2_0;

import com.liferay.portal.odata.entity.CollectionEntityField;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.odata.entity.IntegerEntityField;

import java.util.Map;

/**
 * @author Alessio Antonio Rendina
 */
public class DiscountEntityModel implements EntityModel {

	public DiscountEntityModel() {
		_entityFieldsMap = EntityModel.toEntityFieldsMap(
			new CollectionEntityField(
				new IntegerEntityField(
					"accountId", locale -> "commerceAccountId")),
			new CollectionEntityField(
				new IntegerEntityField(
					"accountGroupId", locale -> "commerceAccountGroupIds")),
			new CollectionEntityField(
				new IntegerEntityField(
					"channelId", locale -> "commerceChannelId")),
			new CollectionEntityField(
				new IntegerEntityField(
					"orderTypeId", locale -> "commerceOrderTypeId")));
	}

	@Override
	public Map<String, EntityField> getEntityFieldsMap() {
		return _entityFieldsMap;
	}

	private final Map<String, EntityField> _entityFieldsMap;

}