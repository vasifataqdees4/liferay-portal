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

package com.liferay.oauth2.provider.token.endpoint.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.oauth2.provider.internal.test.TestAuthorizationGrant;
import com.liferay.portal.kernel.util.Validator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Arthur Chan
 */
@RunWith(Arquillian.class)
public abstract class BaseAuthorizationGrantTestCase
	extends BaseTokenEndpointTestCase {

	@Test
	public void testClientAuthentications() {
		Assert.assertTrue(
			Validator.isNotNull(
				getAccessToken(
					getAuthorizationGrant(TEST_CLIENT_ID_1),
					testClientAuthentications.get(TEST_CLIENT_ID_1))));
		Assert.assertTrue(
			Validator.isNotNull(
				getAccessToken(
					getAuthorizationGrant(TEST_CLIENT_ID_2),
					testClientAuthentications.get(TEST_CLIENT_ID_2))));
		Assert.assertTrue(
			Validator.isNotNull(
				getAccessToken(
					getAuthorizationGrant(TEST_CLIENT_ID_3),
					testClientAuthentications.get(TEST_CLIENT_ID_3))));
	}

	protected abstract TestAuthorizationGrant getAuthorizationGrant(
		String clientId);

}