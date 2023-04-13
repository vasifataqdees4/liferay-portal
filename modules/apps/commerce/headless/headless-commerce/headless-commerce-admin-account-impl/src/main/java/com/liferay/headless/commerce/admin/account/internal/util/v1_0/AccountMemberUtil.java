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

package com.liferay.headless.commerce.admin.account.internal.util.v1_0;

import com.liferay.account.constants.AccountActionKeys;
import com.liferay.account.model.AccountEntry;
import com.liferay.account.model.AccountEntryUserRel;
import com.liferay.account.service.AccountEntryUserRelService;
import com.liferay.commerce.account.util.CommerceAccountHelper;
import com.liferay.headless.commerce.admin.account.dto.v1_0.AccountMember;
import com.liferay.headless.commerce.admin.account.dto.v1_0.AccountRole;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Validator;

import java.util.Arrays;

/**
 * @author Alessio Antonio Rendina
 */
public class AccountMemberUtil {

	public static AccountEntryUserRel addAccountEntryUserRel(
			ModelResourcePermission<AccountEntry>
				accountEntryModelResourcePermission,
			AccountEntryUserRelService accountEntryUserRelService,
			AccountMember accountMember, AccountEntry accountEntry,
			CommerceAccountHelper commerceAccountHelper, User user,
			ServiceContext serviceContext)
		throws PortalException {

		long[] roleIds = null;

		AccountRole[] accountRoles = accountMember.getAccountRoles();

		if (accountRoles != null) {
			roleIds = TransformUtil.transformToLongArray(
				Arrays.asList(accountRoles), AccountRole::getRoleId);
		}

		accountEntryModelResourcePermission.check(
			PermissionThreadLocal.getPermissionChecker(),
			accountEntry.getAccountEntryId(), AccountActionKeys.ASSIGN_USERS);

		commerceAccountHelper.addAccountEntryUserRel(
			accountEntry.getAccountEntryId(), user.getUserId(), roleIds,
			serviceContext);

		return accountEntryUserRelService.getAccountEntryUserRel(
			accountEntry.getAccountEntryId(), user.getUserId());
	}

	public static User getUser(
			UserLocalService userLocalService, AccountMember accountMember,
			long companyId)
		throws PortalException {

		User user = null;

		if (Validator.isNotNull(accountMember.getEmail())) {
			user = userLocalService.getUserByEmailAddress(
				companyId, accountMember.getEmail());
		}
		else if (Validator.isNotNull(
					accountMember.getUserExternalReferenceCode())) {

			user = userLocalService.fetchUserByExternalReferenceCode(
				accountMember.getUserExternalReferenceCode(), companyId);

			if (user == null) {
				throw new NoSuchUserException(
					"Unable to get user with external reference code " +
						accountMember.getUserExternalReferenceCode());
			}
		}
		else {
			user = userLocalService.getUser(accountMember.getUserId());
		}

		return user;
	}

}