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

package com.liferay.oauth.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for OAuthApplication. This utility wraps
 * <code>com.liferay.oauth.service.impl.OAuthApplicationLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ivica Cardic
 * @see OAuthApplicationLocalService
 * @generated
 */
public class OAuthApplicationLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.oauth.service.impl.OAuthApplicationLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 #addOAuthApplication(long, String, String, String, int
	 boolean, String, String, ServiceContext)}
	 */
	@Deprecated
	public static com.liferay.oauth.model.OAuthApplication addOAuthApplication(
			long userId, String name, String description, int accessLevel,
			boolean shareableAccessToken, String callbackURI, String websiteURL,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addOAuthApplication(
			userId, name, description, accessLevel, shareableAccessToken,
			callbackURI, websiteURL, serviceContext);
	}

	public static com.liferay.oauth.model.OAuthApplication addOAuthApplication(
			long userId, String name, String description, String token,
			int accessLevel, boolean shareableAccessToken, String callbackURI,
			String websiteURL,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addOAuthApplication(
			userId, name, description, token, accessLevel, shareableAccessToken,
			callbackURI, websiteURL, serviceContext);
	}

	/**
	 * Adds the o auth application to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OAuthApplicationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param oAuthApplication the o auth application
	 * @return the o auth application that was added
	 */
	public static com.liferay.oauth.model.OAuthApplication addOAuthApplication(
		com.liferay.oauth.model.OAuthApplication oAuthApplication) {

		return getService().addOAuthApplication(oAuthApplication);
	}

	/**
	 * Creates a new o auth application with the primary key. Does not add the o auth application to the database.
	 *
	 * @param oAuthApplicationId the primary key for the new o auth application
	 * @return the new o auth application
	 */
	public static com.liferay.oauth.model.OAuthApplication
		createOAuthApplication(long oAuthApplicationId) {

		return getService().createOAuthApplication(oAuthApplicationId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			createPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	public static void deleteLogo(long oAuthApplicationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteLogo(oAuthApplicationId);
	}

	/**
	 * Deletes the o auth application with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OAuthApplicationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param oAuthApplicationId the primary key of the o auth application
	 * @return the o auth application that was removed
	 * @throws PortalException if a o auth application with the primary key could not be found
	 */
	public static com.liferay.oauth.model.OAuthApplication
			deleteOAuthApplication(long oAuthApplicationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteOAuthApplication(oAuthApplicationId);
	}

	/**
	 * Deletes the o auth application from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OAuthApplicationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param oAuthApplication the o auth application
	 * @return the o auth application that was removed
	 * @throws PortalException
	 */
	public static com.liferay.oauth.model.OAuthApplication
			deleteOAuthApplication(
				com.liferay.oauth.model.OAuthApplication oAuthApplication)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteOAuthApplication(oAuthApplication);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static <T> T dslQuery(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return getService().dslQuery(dslQuery);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.oauth.model.impl.OAuthApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.oauth.model.impl.OAuthApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.oauth.model.OAuthApplication
		fetchOAuthApplication(long oAuthApplicationId) {

		return getService().fetchOAuthApplication(oAuthApplicationId);
	}

	public static com.liferay.oauth.model.OAuthApplication
		fetchOAuthApplication(String consumerKey) {

		return getService().fetchOAuthApplication(consumerKey);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the o auth application with the primary key.
	 *
	 * @param oAuthApplicationId the primary key of the o auth application
	 * @return the o auth application
	 * @throws PortalException if a o auth application with the primary key could not be found
	 */
	public static com.liferay.oauth.model.OAuthApplication getOAuthApplication(
			long oAuthApplicationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getOAuthApplication(oAuthApplicationId);
	}

	public static com.liferay.oauth.model.OAuthApplication getOAuthApplication(
			String consumerKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getOAuthApplication(consumerKey);
	}

	/**
	 * Returns a range of all the o auth applications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.oauth.model.impl.OAuthApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of o auth applications
	 * @param end the upper bound of the range of o auth applications (not inclusive)
	 * @return the range of o auth applications
	 */
	public static java.util.List<com.liferay.oauth.model.OAuthApplication>
		getOAuthApplications(int start, int end) {

		return getService().getOAuthApplications(start, end);
	}

	public static java.util.List<com.liferay.oauth.model.OAuthApplication>
		getOAuthApplications(
			long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.oauth.model.OAuthApplication> orderByComparator) {

		return getService().getOAuthApplications(
			companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of o auth applications.
	 *
	 * @return the number of o auth applications
	 */
	public static int getOAuthApplicationsCount() {
		return getService().getOAuthApplicationsCount();
	}

	public static int getOAuthApplicationsCount(long companyId) {
		return getService().getOAuthApplicationsCount(companyId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static java.util.List<com.liferay.oauth.model.OAuthApplication>
		search(
			long companyId, String keywords,
			java.util.LinkedHashMap<String, Object> params, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.oauth.model.OAuthApplication> orderByComparator) {

		return getService().search(
			companyId, keywords, params, start, end, orderByComparator);
	}

	public static int searchCount(
		long companyId, String keywords,
		java.util.LinkedHashMap<String, Object> params) {

		return getService().searchCount(companyId, keywords, params);
	}

	public static com.liferay.oauth.model.OAuthApplication updateLogo(
			long oAuthApplicationId, java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateLogo(oAuthApplicationId, inputStream);
	}

	public static com.liferay.oauth.model.OAuthApplication
			updateOAuthApplication(
				long oAuthApplicationId, String name, String description,
				boolean shareableAccessToken, String callbackURI,
				String websiteURL,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateOAuthApplication(
			oAuthApplicationId, name, description, shareableAccessToken,
			callbackURI, websiteURL, serviceContext);
	}

	/**
	 * Updates the o auth application in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OAuthApplicationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param oAuthApplication the o auth application
	 * @return the o auth application that was updated
	 */
	public static com.liferay.oauth.model.OAuthApplication
		updateOAuthApplication(
			com.liferay.oauth.model.OAuthApplication oAuthApplication) {

		return getService().updateOAuthApplication(oAuthApplication);
	}

	public static OAuthApplicationLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<OAuthApplicationLocalService, OAuthApplicationLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			OAuthApplicationLocalService.class);

		ServiceTracker
			<OAuthApplicationLocalService, OAuthApplicationLocalService>
				serviceTracker =
					new ServiceTracker
						<OAuthApplicationLocalService,
						 OAuthApplicationLocalService>(
							 bundle.getBundleContext(),
							 OAuthApplicationLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}