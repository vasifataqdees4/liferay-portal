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

package com.liferay.object.rest.internal.manager.v1_0;

import com.liferay.osgi.util.configuration.ConfigurationFactoryUtil;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;

import java.util.Map;

import org.osgi.framework.Constants;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.ComponentFactory;
import org.osgi.service.component.ComponentInstance;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Feliphe Marinho
 */
@Component(
	configurationPid = "com.liferay.object.rest.internal.configuration.FunctionObjectEntryManagerConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE, service = {}
)
public class FunctionObjectEntryManagerFactory {

	@Activate
	protected void activate(
			ComponentContext componentContext, Map<String, Object> properties)
		throws Exception {

		String externalReferenceCode =
			ConfigurationFactoryUtil.getExternalReferenceCode(properties);

		_componentInstance = _componentFactory.newInstance(
			HashMapDictionaryBuilder.<String, Object>put(
				"storage.type", "function#" + externalReferenceCode
			).putAll(
				properties
			).remove(
				Constants.SERVICE_PID
			).build());
	}

	@Deactivate
	protected void deactivate() {
		if (_componentInstance != null) {
			_componentInstance.dispose();
		}
	}

	@Reference(
		target = "(component.factory=com.liferay.object.rest.internal.manager.v1_0.FunctionObjectEntryManager)"
	)
	private ComponentFactory _componentFactory;

	private ComponentInstance _componentInstance;

}