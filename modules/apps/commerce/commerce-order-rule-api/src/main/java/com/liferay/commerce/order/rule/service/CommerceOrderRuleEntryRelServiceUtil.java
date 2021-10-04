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

package com.liferay.commerce.order.rule.service;

import com.liferay.commerce.order.rule.model.CommerceOrderRuleEntryRel;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * Provides the remote service utility for CommerceOrderRuleEntryRel. This utility wraps
 * <code>com.liferay.commerce.order.rule.service.impl.CommerceOrderRuleEntryRelServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Luca Pellizzon
 * @see CommerceOrderRuleEntryRelService
 * @generated
 */
public class CommerceOrderRuleEntryRelServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.order.rule.service.impl.CommerceOrderRuleEntryRelServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static CommerceOrderRuleEntryRel addCommerceOrderRuleEntryRel(
			String className, long classPK, long commerceOrderRuleEntryId)
		throws PortalException {

		return getService().addCommerceOrderRuleEntryRel(
			className, classPK, commerceOrderRuleEntryId);
	}

	public static void deleteCommerceOrderRuleEntryRel(
			long commerceOrderRuleEntryRelId)
		throws PortalException {

		getService().deleteCommerceOrderRuleEntryRel(
			commerceOrderRuleEntryRelId);
	}

	public static void
			deleteCommerceOrderRuleEntryRelsByCommerceOrderRuleEntryId(
				long commerceOrderRuleEntryId)
		throws PortalException {

		getService().deleteCommerceOrderRuleEntryRelsByCommerceOrderRuleEntryId(
			commerceOrderRuleEntryId);
	}

	public static CommerceOrderRuleEntryRel fetchCommerceOrderRuleEntryRel(
			String className, long classPK, long commerceOrderRuleEntryId)
		throws PortalException {

		return getService().fetchCommerceOrderRuleEntryRel(
			className, classPK, commerceOrderRuleEntryId);
	}

	public static List<CommerceOrderRuleEntryRel>
			getAccountEntryCommerceOrderRuleEntryRels(
				long commerceOrderRuleEntryId, String keywords, int start,
				int end)
		throws PortalException {

		return getService().getAccountEntryCommerceOrderRuleEntryRels(
			commerceOrderRuleEntryId, keywords, start, end);
	}

	public static int getAccountEntryCommerceOrderRuleEntryRelsCount(
			long commerceOrderRuleEntryId, String keywords)
		throws PortalException {

		return getService().getAccountEntryCommerceOrderRuleEntryRelsCount(
			commerceOrderRuleEntryId, keywords);
	}

	public static List<CommerceOrderRuleEntryRel>
			getAccountGroupCommerceOrderRuleEntryRels(
				long commerceOrderRuleEntryId, String keywords, int start,
				int end)
		throws PortalException {

		return getService().getAccountGroupCommerceOrderRuleEntryRels(
			commerceOrderRuleEntryId, keywords, start, end);
	}

	public static int getAccountGroupCommerceOrderRuleEntryRelsCount(
			long commerceOrderRuleEntryId, String keywords)
		throws PortalException {

		return getService().getAccountGroupCommerceOrderRuleEntryRelsCount(
			commerceOrderRuleEntryId, keywords);
	}

	public static List<CommerceOrderRuleEntryRel>
			getCommerceChannelCommerceOrderRuleEntryRels(
				long commerceOrderRuleEntryId, String keywords, int start,
				int end)
		throws PortalException {

		return getService().getCommerceChannelCommerceOrderRuleEntryRels(
			commerceOrderRuleEntryId, keywords, start, end);
	}

	public static int getCommerceChannelCommerceOrderRuleEntryRelsCount(
			long commerceOrderRuleEntryId, String keywords)
		throws PortalException {

		return getService().getCommerceChannelCommerceOrderRuleEntryRelsCount(
			commerceOrderRuleEntryId, keywords);
	}

	public static CommerceOrderRuleEntryRel getCommerceOrderRuleEntryRel(
			long commerceOrderRuleEntryRelId)
		throws PortalException {

		return getService().getCommerceOrderRuleEntryRel(
			commerceOrderRuleEntryRelId);
	}

	public static List<CommerceOrderRuleEntryRel> getCommerceOrderRuleEntryRels(
			long commerceOrderRuleEntryId)
		throws PortalException {

		return getService().getCommerceOrderRuleEntryRels(
			commerceOrderRuleEntryId);
	}

	public static List<CommerceOrderRuleEntryRel> getCommerceOrderRuleEntryRels(
			long commerceOrderRuleEntryId, int start, int end,
			OrderByComparator<CommerceOrderRuleEntryRel> orderByComparator)
		throws PortalException {

		return getService().getCommerceOrderRuleEntryRels(
			commerceOrderRuleEntryId, start, end, orderByComparator);
	}

	public static int getCommerceOrderRuleEntryRelsCount(
			long commerceOrderRuleEntryId)
		throws PortalException {

		return getService().getCommerceOrderRuleEntryRelsCount(
			commerceOrderRuleEntryId);
	}

	public static List<CommerceOrderRuleEntryRel>
			getCommerceOrderTypeCommerceOrderRuleEntryRels(
				long commerceOrderRuleEntryId, String keywords, int start,
				int end)
		throws PortalException {

		return getService().getCommerceOrderTypeCommerceOrderRuleEntryRels(
			commerceOrderRuleEntryId, keywords, start, end);
	}

	public static int getCommerceOrderTypeCommerceOrderRuleEntryRelsCount(
			long commerceOrderRuleEntryId, String keywords)
		throws PortalException {

		return getService().getCommerceOrderTypeCommerceOrderRuleEntryRelsCount(
			commerceOrderRuleEntryId, keywords);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CommerceOrderRuleEntryRelService getService() {
		return _service;
	}

	private static volatile CommerceOrderRuleEntryRelService _service;

}