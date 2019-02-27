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

package com.liferay.portal.kernel.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for Layout. This utility wraps
 * <code>com.liferay.portal.service.impl.LayoutLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutLocalService
 * @generated
 */
@ProviderType
public class LayoutLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portal.service.impl.LayoutLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the layout to the database. Also notifies the appropriate model listeners.
	 *
	 * @param layout the layout
	 * @return the layout that was added
	 */
	public static com.liferay.portal.kernel.model.Layout addLayout(
		com.liferay.portal.kernel.model.Layout layout) {

		return getService().addLayout(layout);
	}

	/**
	 * Adds a layout with additional parameters.
	 *
	 * <p>
	 * This method handles the creation of the layout including its resources,
	 * metadata, and internal data structures. It is not necessary to make
	 * subsequent calls to any methods to setup default groups, resources, ...
	 * etc.
	 * </p>
	 *
	 * @param userId the primary key of the user
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param parentLayoutId the layout ID of the parent layout (optionally
	 {@link LayoutConstants#DEFAULT_PARENT_LAYOUT_ID})
	 * @param nameMap the layout's locales and localized names
	 * @param titleMap the layout's locales and localized titles
	 * @param descriptionMap the layout's locales and localized descriptions
	 * @param keywordsMap the layout's locales and localized keywords
	 * @param robotsMap the layout's locales and localized robots
	 * @param type the layout's type (optionally {@link
	 LayoutConstants#TYPE_PORTLET}). The possible types can be found
	 in {@link LayoutConstants}.
	 * @param typeSettings the settings to load the unicode properties object.
	 See {@link UnicodeProperties #fastLoad(String)}.
	 * @param hidden whether the layout is hidden
	 * @param system whether the layout is of system type
	 * @param friendlyURLMap the layout's locales and localized friendly URLs.
	 To see how the URL is normalized when accessed, see {@link
	 com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil#normalize(
	 String)}.
	 * @param referrerClassNameId the referrer entity class name ID
	 * @param referrerClassPK the referrer entity primary key
	 * @param publishDate the date when draft was last published
	 * @param serviceContext the service context to be applied. Must set the
	 UUID for the layout. Can set the creation date, modification
	 date, and expando bridge attributes for the layout. For layouts
	 that belong to a layout set prototype, an attribute named
	 <code>layoutUpdateable</code> can be set to specify whether site
	 administrators can modify this page within their site. For
	 layouts that are created from a layout prototype, attributes
	 named <code>layoutPrototypeUuid</code> and
	 <code>layoutPrototypeLinkedEnabled</code> can be specified to
	 provide the unique identifier of the source prototype and a
	 boolean to determine whether a link to it should be enabled to
	 activate propagation of changes made to the linked page in the
	 prototype.
	 * @return the layout
	 * @throws PortalException if a portal exception occurred
	 */
	public static com.liferay.portal.kernel.model.Layout addLayout(
			long userId, long groupId, boolean privateLayout,
			long parentLayoutId,
			java.util.Map<java.util.Locale, String> nameMap,
			java.util.Map<java.util.Locale, String> titleMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			java.util.Map<java.util.Locale, String> keywordsMap,
			java.util.Map<java.util.Locale, String> robotsMap, String type,
			String typeSettings, boolean hidden, boolean system,
			java.util.Map<java.util.Locale, String> friendlyURLMap,
			long referrerClassNameId, long referrerClassPK,
			java.util.Date publishDate, ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addLayout(
			userId, groupId, privateLayout, parentLayoutId, nameMap, titleMap,
			descriptionMap, keywordsMap, robotsMap, type, typeSettings, hidden,
			system, friendlyURLMap, referrerClassNameId, referrerClassPK,
			publishDate, serviceContext);
	}

	/**
	 * Adds a layout with additional parameters.
	 *
	 * <p>
	 * This method handles the creation of the layout including its resources,
	 * metadata, and internal data structures. It is not necessary to make
	 * subsequent calls to any methods to setup default groups, resources, ...
	 * etc.
	 * </p>
	 *
	 * @param userId the primary key of the user
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param parentLayoutId the layout ID of the parent layout (optionally
	 {@link LayoutConstants#DEFAULT_PARENT_LAYOUT_ID})
	 * @param nameMap the layout's locales and localized names
	 * @param titleMap the layout's locales and localized titles
	 * @param descriptionMap the layout's locales and localized descriptions
	 * @param keywordsMap the layout's locales and localized keywords
	 * @param robotsMap the layout's locales and localized robots
	 * @param type the layout's type (optionally {@link
	 LayoutConstants#TYPE_PORTLET}). The possible types can be found
	 in {@link LayoutConstants}.
	 * @param typeSettings the settings to load the unicode properties object.
	 See {@link UnicodeProperties #fastLoad(String)}.
	 * @param hidden whether the layout is hidden
	 * @param system whether the layout is of system type
	 * @param friendlyURLMap the layout's locales and localized friendly URLs.
	 To see how the URL is normalized when accessed, see {@link
	 com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil#normalize(
	 String)}.
	 * @param serviceContext the service context to be applied. Must set the
	 UUID for the layout. Can set the creation date, modification
	 date, and expando bridge attributes for the layout. For layouts
	 that belong to a layout set prototype, an attribute named
	 <code>layoutUpdateable</code> can be set to specify whether site
	 administrators can modify this page within their site. For
	 layouts that are created from a layout prototype, attributes
	 named <code>layoutPrototypeUuid</code> and
	 <code>layoutPrototypeLinkedEnabled</code> can be specified to
	 provide the unique identifier of the source prototype and a
	 boolean to determine whether a link to it should be enabled to
	 activate propagation of changes made to the linked page in the
	 prototype.
	 * @return the layout
	 * @throws PortalException if a portal exception occurred
	 */
	public static com.liferay.portal.kernel.model.Layout addLayout(
			long userId, long groupId, boolean privateLayout,
			long parentLayoutId,
			java.util.Map<java.util.Locale, String> nameMap,
			java.util.Map<java.util.Locale, String> titleMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			java.util.Map<java.util.Locale, String> keywordsMap,
			java.util.Map<java.util.Locale, String> robotsMap, String type,
			String typeSettings, boolean hidden, boolean system,
			java.util.Map<java.util.Locale, String> friendlyURLMap,
			ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addLayout(
			userId, groupId, privateLayout, parentLayoutId, nameMap, titleMap,
			descriptionMap, keywordsMap, robotsMap, type, typeSettings, hidden,
			system, friendlyURLMap, serviceContext);
	}

	/**
	 * Adds a layout with additional parameters.
	 *
	 * <p>
	 * This method handles the creation of the layout including its resources,
	 * metadata, and internal data structures. It is not necessary to make
	 * subsequent calls to any methods to setup default groups, resources, ...
	 * etc.
	 * </p>
	 *
	 * @param userId the primary key of the user
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param parentLayoutId the layout ID of the parent layout (optionally
	 {@link LayoutConstants#DEFAULT_PARENT_LAYOUT_ID})
	 * @param nameMap the layout's locales and localized names
	 * @param titleMap the layout's locales and localized titles
	 * @param descriptionMap the layout's locales and localized descriptions
	 * @param keywordsMap the layout's locales and localized keywords
	 * @param robotsMap the layout's locales and localized robots
	 * @param type the layout's type (optionally {@link
	 LayoutConstants#TYPE_PORTLET}). The possible types can be found
	 in {@link LayoutConstants}.
	 * @param typeSettings the settings to load the unicode properties object.
	 See {@link UnicodeProperties #fastLoad(String)}.
	 * @param hidden whether the layout is hidden
	 * @param friendlyURLMap the layout's locales and localized friendly URLs.
	 To see how the URL is normalized when accessed, see {@link
	 com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil#normalize(
	 String)}.
	 * @param serviceContext the service context to be applied. Must set the
	 UUID for the layout. Can set the creation date, modification
	 date, and expando bridge attributes for the layout. For layouts
	 that belong to a layout set prototype, an attribute named
	 <code>layoutUpdateable</code> can be set to specify whether site
	 administrators can modify this page within their site. For
	 layouts that are created from a layout prototype, attributes
	 named <code>layoutPrototypeUuid</code> and
	 <code>layoutPrototypeLinkedEnabled</code> can be specified to
	 provide the unique identifier of the source prototype and a
	 boolean to determine whether a link to it should be enabled to
	 activate propagation of changes made to the linked page in the
	 prototype.
	 * @return the layout
	 * @throws PortalException if a portal exception occurred
	 */
	public static com.liferay.portal.kernel.model.Layout addLayout(
			long userId, long groupId, boolean privateLayout,
			long parentLayoutId,
			java.util.Map<java.util.Locale, String> nameMap,
			java.util.Map<java.util.Locale, String> titleMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			java.util.Map<java.util.Locale, String> keywordsMap,
			java.util.Map<java.util.Locale, String> robotsMap, String type,
			String typeSettings, boolean hidden,
			java.util.Map<java.util.Locale, String> friendlyURLMap,
			ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addLayout(
			userId, groupId, privateLayout, parentLayoutId, nameMap, titleMap,
			descriptionMap, keywordsMap, robotsMap, type, typeSettings, hidden,
			friendlyURLMap, serviceContext);
	}

	/**
	 * Adds a layout with single entry maps for name, title, and description to
	 * the default locale.
	 *
	 * <p>
	 * This method handles the creation of the layout including its resources,
	 * metadata, and internal data structures. It is not necessary to make
	 * subsequent calls to any methods to setup default groups, resources, ...
	 * etc.
	 * </p>
	 *
	 * @param userId the primary key of the user
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param parentLayoutId the layout ID of the parent layout (optionally
	 {@link LayoutConstants#DEFAULT_PARENT_LAYOUT_ID}). The possible
	 values can be found in {@link LayoutConstants}.
	 * @param name the layout's name (optionally {@link
	 PropsValues#DEFAULT_USER_PRIVATE_LAYOUT_NAME} or {@link
	 PropsValues#DEFAULT_USER_PUBLIC_LAYOUT_NAME}). The default values
	 can be overridden in <code>portal-ext.properties</code> by
	 specifying new values for the corresponding properties defined in
	 {@link PropsValues}
	 * @param title the layout's title
	 * @param description the layout's description
	 * @param type the layout's type (optionally {@link
	 LayoutConstants#TYPE_PORTLET}). The possible types can be found
	 in {@link LayoutConstants}.
	 * @param hidden whether the layout is hidden
	 * @param system whether the layout is of system type
	 * @param friendlyURL the friendly URL of the layout (optionally {@link
	 PropsValues#DEFAULT_USER_PRIVATE_LAYOUT_FRIENDLY_URL} or {@link
	 PropsValues#DEFAULT_USER_PUBLIC_LAYOUT_FRIENDLY_URL}). The
	 default values can be overridden in
	 <code>portal-ext.properties</code> by specifying new values for
	 the corresponding properties defined in {@link PropsValues}. To
	 see how the URL is normalized when accessed, see {@link
	 com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil#normalize(
	 String)}.
	 * @param serviceContext the service context to be applied. Must set the
	 UUID for the layout. Can set the creation date and modification
	 date for the layout. For layouts that belong to a layout set
	 prototype, an attribute named <code>layoutUpdateable</code> can
	 be set to specify whether site administrators can modify this
	 page within their site.
	 * @return the layout
	 * @throws PortalException if a portal exception occurred
	 */
	public static com.liferay.portal.kernel.model.Layout addLayout(
			long userId, long groupId, boolean privateLayout,
			long parentLayoutId, String name, String title, String description,
			String type, boolean hidden, boolean system, String friendlyURL,
			ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addLayout(
			userId, groupId, privateLayout, parentLayoutId, name, title,
			description, type, hidden, system, friendlyURL, serviceContext);
	}

	/**
	 * Adds a layout with single entry maps for name, title, and description to
	 * the default locale.
	 *
	 * <p>
	 * This method handles the creation of the layout including its resources,
	 * metadata, and internal data structures. It is not necessary to make
	 * subsequent calls to any methods to setup default groups, resources, ...
	 * etc.
	 * </p>
	 *
	 * @param userId the primary key of the user
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param parentLayoutId the layout ID of the parent layout (optionally
	 {@link LayoutConstants#DEFAULT_PARENT_LAYOUT_ID}). The possible
	 values can be found in {@link LayoutConstants}.
	 * @param name the layout's name (optionally {@link
	 PropsValues#DEFAULT_USER_PRIVATE_LAYOUT_NAME} or {@link
	 PropsValues#DEFAULT_USER_PUBLIC_LAYOUT_NAME}). The default values
	 can be overridden in <code>portal-ext.properties</code> by
	 specifying new values for the corresponding properties defined in
	 {@link PropsValues}
	 * @param title the layout's title
	 * @param description the layout's description
	 * @param type the layout's type (optionally {@link
	 LayoutConstants#TYPE_PORTLET}). The possible types can be found
	 in {@link LayoutConstants}.
	 * @param hidden whether the layout is hidden
	 * @param friendlyURL the friendly URL of the layout (optionally {@link
	 PropsValues#DEFAULT_USER_PRIVATE_LAYOUT_FRIENDLY_URL} or {@link
	 PropsValues#DEFAULT_USER_PUBLIC_LAYOUT_FRIENDLY_URL}). The
	 default values can be overridden in
	 <code>portal-ext.properties</code> by specifying new values for
	 the corresponding properties defined in {@link PropsValues}. To
	 see how the URL is normalized when accessed, see {@link
	 com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil#normalize(
	 String)}.
	 * @param serviceContext the service context to be applied. Must set the
	 UUID for the layout. Can set the creation date and modification
	 date for the layout. For layouts that belong to a layout set
	 prototype, an attribute named <code>layoutUpdateable</code> can
	 be set to specify whether site administrators can modify this
	 page within their site.
	 * @return the layout
	 * @throws PortalException if a portal exception occurred
	 */
	public static com.liferay.portal.kernel.model.Layout addLayout(
			long userId, long groupId, boolean privateLayout,
			long parentLayoutId, String name, String title, String description,
			String type, boolean hidden, String friendlyURL,
			ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addLayout(
			userId, groupId, privateLayout, parentLayoutId, name, title,
			description, type, hidden, friendlyURL, serviceContext);
	}

	/**
	 * Creates a new layout with the primary key. Does not add the layout to the database.
	 *
	 * @param plid the primary key for the new layout
	 * @return the new layout
	 */
	public static com.liferay.portal.kernel.model.Layout createLayout(
		long plid) {

		return getService().createLayout(plid);
	}

	/**
	 * Deletes the layout from the database. Also notifies the appropriate model listeners.
	 *
	 * @param layout the layout
	 * @return the layout that was removed
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.Layout deleteLayout(
			com.liferay.portal.kernel.model.Layout layout)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteLayout(layout);
	}

	/**
	 * Deletes the layout, its child layouts, and its associated resources.
	 *
	 * @param layout the layout
	 * @param updateLayoutSet whether the layout set's page counter needs to be
	 updated
	 * @param serviceContext the service context to be applied
	 * @throws PortalException if a portal exception occurred
	 */
	public static void deleteLayout(
			com.liferay.portal.kernel.model.Layout layout,
			boolean updateLayoutSet, ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteLayout(layout, updateLayoutSet, serviceContext);
	}

	/**
	 * Deletes the layout with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param plid the primary key of the layout
	 * @return the layout that was removed
	 * @throws PortalException if a layout with the primary key could not be found
	 */
	public static com.liferay.portal.kernel.model.Layout deleteLayout(long plid)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteLayout(plid);
	}

	/**
	 * Deletes the layout with the layout ID, also deleting the layout's child
	 * layouts, and associated resources.
	 *
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param layoutId the layout ID of the layout
	 * @param serviceContext the service context to be applied
	 * @throws PortalException if a portal exception occurred
	 */
	public static void deleteLayout(
			long groupId, boolean privateLayout, long layoutId,
			ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteLayout(
			groupId, privateLayout, layoutId, serviceContext);
	}

	/**
	 * Deletes the layout with the plid, also deleting the layout's child
	 * layouts, and associated resources.
	 *
	 * @param plid the primary key of the layout
	 * @param serviceContext the service context to be applied
	 * @throws PortalException if a portal exception occurred
	 */
	public static void deleteLayout(long plid, ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteLayout(plid, serviceContext);
	}

	/**
	 * Deletes the group's private or non-private layouts, also deleting the
	 * layouts' child layouts, and associated resources.
	 *
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param serviceContext the service context to be applied. The parent
	 layout set's page count will be updated by default, unless an
	 attribute named <code>updatePageCount</code> is set to
	 <code>false</code>.
	 * @throws PortalException if a portal exception occurred
	 */
	public static void deleteLayouts(
			long groupId, boolean privateLayout, ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteLayouts(groupId, privateLayout, serviceContext);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.LayoutModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.LayoutModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	/**
	 * Exports layouts with the layout IDs and criteria as a byte array.
	 *
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param layoutIds the layout IDs of the layouts to be exported
	 * @param parameterMap the mapping of parameters indicating which
	 information to export. For information on the keys used in
	 the map see {@link
	 com.liferay.exportimport.kernel.lar.PortletDataHandlerKeys}.
	 * @param startDate the export's start date
	 * @param endDate the export's end date
	 * @return the layouts as a byte array
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public static byte[] exportLayouts(
			long groupId, boolean privateLayout, long[] layoutIds,
			java.util.Map<String, String[]> parameterMap,
			java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().exportLayouts(
			groupId, privateLayout, layoutIds, parameterMap, startDate,
			endDate);
	}

	/**
	 * Exports all layouts that match the criteria as a byte array.
	 *
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param parameterMap the mapping of parameters indicating which
	 information to export. For information on the keys used in
	 the map see {@link
	 com.liferay.exportimport.kernel.lar.PortletDataHandlerKeys}.
	 * @param startDate the export's start date
	 * @param endDate the export's end date
	 * @return the layout as a byte array
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public static byte[] exportLayouts(
			long groupId, boolean privateLayout,
			java.util.Map<String, String[]> parameterMap,
			java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().exportLayouts(
			groupId, privateLayout, parameterMap, startDate, endDate);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 com.liferay.exportimport.kernel.service.ExportImportLocalService#exportLayoutsAsFile(
	 ExportImportConfiguration)}
	 */
	@Deprecated
	public static java.io.File exportLayoutsAsFile(
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().exportLayoutsAsFile(exportImportConfiguration);
	}

	/**
	 * Exports the layouts that match the layout IDs and criteria as a file.
	 *
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param layoutIds the layout IDs of the layouts to be exported
	 (optionally <code>null</code>)
	 * @param parameterMap the mapping of parameters indicating which
	 information to export. For information on the keys used in
	 the map see {@link
	 com.liferay.exportimport.kernel.lar.PortletDataHandlerKeys}.
	 * @param startDate the export's start date
	 * @param endDate the export's end date
	 * @return the layouts as a File
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public static java.io.File exportLayoutsAsFile(
			long groupId, boolean privateLayout, long[] layoutIds,
			java.util.Map<String, String[]> parameterMap,
			java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().exportLayoutsAsFile(
			groupId, privateLayout, layoutIds, parameterMap, startDate,
			endDate);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 com.liferay.exportimport.kernel.service.ExportImportLocalService#exportLayoutsAsFileInBackground(
	 long, ExportImportConfiguration)}
	 */
	@Deprecated
	public static long exportLayoutsAsFileInBackground(
			long userId,
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().exportLayoutsAsFileInBackground(
			userId, exportImportConfiguration);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 com.liferay.exportimport.kernel.service.ExportImportLocalService#exportLayoutsAsFileInBackground(
	 long, long)}
	 */
	@Deprecated
	public static long exportLayoutsAsFileInBackground(
			long userId, long exportImportConfigurationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().exportLayoutsAsFileInBackground(
			userId, exportImportConfigurationId);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public static long exportLayoutsAsFileInBackground(
			long userId, String taskName, long groupId, boolean privateLayout,
			long[] layoutIds, java.util.Map<String, String[]> parameterMap,
			java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().exportLayoutsAsFileInBackground(
			userId, taskName, groupId, privateLayout, layoutIds, parameterMap,
			startDate, endDate);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public static long exportLayoutsAsFileInBackground(
			long userId, String taskName, long groupId, boolean privateLayout,
			long[] layoutIds, java.util.Map<String, String[]> parameterMap,
			java.util.Date startDate, java.util.Date endDate, String fileName)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().exportLayoutsAsFileInBackground(
			userId, taskName, groupId, privateLayout, layoutIds, parameterMap,
			startDate, endDate, fileName);
	}

	/**
	 * Exports the portlet information (categories, permissions, ... etc.) as a
	 * byte array.
	 *
	 * @param plid the primary key of the layout
	 * @param groupId the primary key of the group
	 * @param portletId the primary key of the portlet
	 * @param parameterMap the mapping of parameters indicating which
	 information to export. For information on the keys used in
	 the map see {@link
	 com.liferay.exportimport.kernel.lar.PortletDataHandlerKeys}.
	 * @param startDate the export's start date
	 * @param endDate the export's end date
	 * @return the portlet information as a byte array
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public static byte[] exportPortletInfo(
			long plid, long groupId, String portletId,
			java.util.Map<String, String[]> parameterMap,
			java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().exportPortletInfo(
			plid, groupId, portletId, parameterMap, startDate, endDate);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public static byte[] exportPortletInfo(
			long companyId, String portletId,
			java.util.Map<String, String[]> parameterMap,
			java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().exportPortletInfo(
			companyId, portletId, parameterMap, startDate, endDate);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 com.liferay.exportimport.kernel.service.ExportImportLocalService#exportPortletInfoAsFile(
	 ExportImportConfiguration)}}
	 */
	@Deprecated
	public static java.io.File exportPortletInfoAsFile(
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().exportPortletInfoAsFile(exportImportConfiguration);
	}

	/**
	 * Exports the portlet information (categories, permissions, ... etc.) as a
	 * file.
	 *
	 * @param plid the primary key of the layout
	 * @param groupId the primary key of the group
	 * @param portletId the primary key of the portlet
	 * @param parameterMap the mapping of parameters indicating which
	 information to export. For information on the keys used in
	 the map see {@link
	 com.liferay.exportimport.kernel.lar.PortletDataHandlerKeys}.
	 * @param startDate the export's start date
	 * @param endDate the export's end date
	 * @return the portlet information as a file
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public static java.io.File exportPortletInfoAsFile(
			long plid, long groupId, String portletId,
			java.util.Map<String, String[]> parameterMap,
			java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().exportPortletInfoAsFile(
			plid, groupId, portletId, parameterMap, startDate, endDate);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public static java.io.File exportPortletInfoAsFile(
			long companyId, String portletId,
			java.util.Map<String, String[]> parameterMap,
			java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().exportPortletInfoAsFile(
			companyId, portletId, parameterMap, startDate, endDate);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 com.liferay.exportimport.kernel.service.ExportImportLocalService#exportPortletInfoAsFileInBackground(
	 long, ExportImportConfiguration)}}
	 */
	@Deprecated
	public static long exportPortletInfoAsFileInBackground(
			long userId,
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().exportPortletInfoAsFileInBackground(
			userId, exportImportConfiguration);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 com.liferay.exportimport.kernel.service.ExportImportLocalService#exportPortletInfoAsFileInBackground(
	 long, long)}}
	 */
	@Deprecated
	public static long exportPortletInfoAsFileInBackground(
			long userId, long exportImportConfigurationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().exportPortletInfoAsFileInBackground(
			userId, exportImportConfigurationId);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public static long exportPortletInfoAsFileInBackground(
			long userId, String taskName, long plid, long groupId,
			String portletId, java.util.Map<String, String[]> parameterMap,
			java.util.Date startDate, java.util.Date endDate, String fileName)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().exportPortletInfoAsFileInBackground(
			userId, taskName, plid, groupId, portletId, parameterMap, startDate,
			endDate, fileName);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public static long exportPortletInfoAsFileInBackground(
			long userId, String taskName, String portletId,
			java.util.Map<String, String[]> parameterMap,
			java.util.Date startDate, java.util.Date endDate, String fileName)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().exportPortletInfoAsFileInBackground(
			userId, taskName, portletId, parameterMap, startDate, endDate,
			fileName);
	}

	public static com.liferay.portal.kernel.model.Layout fetchDefaultLayout(
		long groupId, boolean privateLayout) {

		return getService().fetchDefaultLayout(groupId, privateLayout);
	}

	public static com.liferay.portal.kernel.model.Layout fetchFirstLayout(
		long groupId, boolean privateLayout, long parentLayoutId) {

		return getService().fetchFirstLayout(
			groupId, privateLayout, parentLayoutId);
	}

	public static com.liferay.portal.kernel.model.Layout fetchLayout(
		long plid) {

		return getService().fetchLayout(plid);
	}

	public static com.liferay.portal.kernel.model.Layout fetchLayout(
		long groupId, boolean privateLayout, long layoutId) {

		return getService().fetchLayout(groupId, privateLayout, layoutId);
	}

	public static com.liferay.portal.kernel.model.Layout fetchLayout(
		String uuid, long groupId, boolean privateLayout) {

		return getService().fetchLayout(uuid, groupId, privateLayout);
	}

	public static com.liferay.portal.kernel.model.Layout
		fetchLayoutByFriendlyURL(
			long groupId, boolean privateLayout, String friendlyURL) {

		return getService().fetchLayoutByFriendlyURL(
			groupId, privateLayout, friendlyURL);
	}

	public static com.liferay.portal.kernel.model.Layout
			fetchLayoutByIconImageId(boolean privateLayout, long iconImageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().fetchLayoutByIconImageId(
			privateLayout, iconImageId);
	}

	/**
	 * Returns the layout matching the UUID, group, and privacy.
	 *
	 * @param uuid the layout's UUID
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @return the matching layout, or <code>null</code> if a matching layout could not be found
	 */
	public static com.liferay.portal.kernel.model.Layout
		fetchLayoutByUuidAndGroupId(
			String uuid, long groupId, boolean privateLayout) {

		return getService().fetchLayoutByUuidAndGroupId(
			uuid, groupId, privateLayout);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the primary key of the default layout for the group.
	 *
	 * @param groupId the primary key of the group
	 * @return the primary key of the default layout for the group (optionally
	 {@link LayoutConstants#DEFAULT_PLID})
	 */
	public static long getDefaultPlid(long groupId) {
		return getService().getDefaultPlid(groupId);
	}

	/**
	 * Returns primary key of the matching default layout for the group
	 *
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @return the primary key of the default layout for the group; {@link
	 LayoutConstants#DEFAULT_PLID}) otherwise
	 */
	public static long getDefaultPlid(long groupId, boolean privateLayout) {
		return getService().getDefaultPlid(groupId, privateLayout);
	}

	/**
	 * Returns primary key of the default portlet layout for the group
	 *
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param portletId the primary key of the portlet
	 * @return the primary key of the default portlet layout for the group;
	 {@link LayoutConstants#DEFAULT_PLID} otherwise
	 * @throws PortalException
	 */
	public static long getDefaultPlid(
			long groupId, boolean privateLayout, String portletId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getDefaultPlid(groupId, privateLayout, portletId);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	/**
	 * Returns the layout for the friendly URL.
	 *
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param friendlyURL the friendly URL of the layout
	 * @return the layout for the friendly URL
	 * @throws PortalException if a portal exception occurred
	 */
	public static com.liferay.portal.kernel.model.Layout getFriendlyURLLayout(
			long groupId, boolean privateLayout, String friendlyURL)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getFriendlyURLLayout(
			groupId, privateLayout, friendlyURL);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the layout with the primary key.
	 *
	 * @param plid the primary key of the layout
	 * @return the layout
	 * @throws PortalException if a layout with the primary key could not be found
	 */
	public static com.liferay.portal.kernel.model.Layout getLayout(long plid)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLayout(plid);
	}

	/**
	 * Returns the layout matching the layout ID, group, and privacy; throws a
	 * {@link NoSuchLayoutException} otherwise.
	 *
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param layoutId the layout ID of the layout
	 * @return the matching layout
	 * @throws PortalException if a portal exception occurred
	 */
	public static com.liferay.portal.kernel.model.Layout getLayout(
			long groupId, boolean privateLayout, long layoutId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLayout(groupId, privateLayout, layoutId);
	}

	/**
	 * Returns the layout for the icon image; throws a {@link
	 * NoSuchLayoutException} otherwise.
	 *
	 * @param iconImageId the primary key of the icon image
	 * @return Returns the layout for the icon image
	 * @throws PortalException if a portal exception occurred
	 */
	public static com.liferay.portal.kernel.model.Layout getLayoutByIconImageId(
			long iconImageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLayoutByIconImageId(iconImageId);
	}

	/**
	 * Returns the layout matching the UUID, group, and privacy.
	 *
	 * @param uuid the layout's UUID
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @return the matching layout
	 * @throws PortalException if a matching layout could not be found
	 */
	public static com.liferay.portal.kernel.model.Layout
			getLayoutByUuidAndGroupId(
				String uuid, long groupId, boolean privateLayout)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLayoutByUuidAndGroupId(
			uuid, groupId, privateLayout);
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 #getLayoutChildLayouts(List)}
	 */
	@Deprecated
	public static java.util.Map
		<Long, java.util.List<com.liferay.portal.kernel.model.Layout>>
			getLayoutChildLayouts(
				com.liferay.portal.kernel.model.LayoutSet layoutSet,
				java.util.List<com.liferay.portal.kernel.model.Layout>
					parentLayouts) {

		return getService().getLayoutChildLayouts(layoutSet, parentLayouts);
	}

	public static java.util.Map
		<Long, java.util.List<com.liferay.portal.kernel.model.Layout>>
			getLayoutChildLayouts(
				java.util.List<com.liferay.portal.kernel.model.Layout>
					parentLayouts) {

		return getService().getLayoutChildLayouts(parentLayouts);
	}

	/**
	 * Returns a range of all the layouts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.LayoutModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of layouts
	 * @param end the upper bound of the range of layouts (not inclusive)
	 * @return the range of layouts
	 */
	public static java.util.List<com.liferay.portal.kernel.model.Layout>
		getLayouts(int start, int end) {

		return getService().getLayouts(start, end);
	}

	public static java.util.List<com.liferay.portal.kernel.model.Layout>
		getLayouts(long companyId) {

		return getService().getLayouts(companyId);
	}

	/**
	 * Returns all the layouts belonging to the group.
	 *
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @return the matching layouts, or <code>null</code> if no matches were
	 found
	 */
	public static java.util.List<com.liferay.portal.kernel.model.Layout>
		getLayouts(long groupId, boolean privateLayout) {

		return getService().getLayouts(groupId, privateLayout);
	}

	/**
	 * Returns a range of all the layouts belonging to the group.
	 *
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param start the lower bound of the range of layouts
	 * @param end the upper bound of the range of layouts (not inclusive)
	 * @param obc the comparator to order the layouts
	 * @return the matching layouts, or <code>null</code> if no matches were
	 found
	 */
	public static java.util.List<com.liferay.portal.kernel.model.Layout>
		getLayouts(
			long groupId, boolean privateLayout, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.portal.kernel.model.Layout> obc) {

		return getService().getLayouts(groupId, privateLayout, start, end, obc);
	}

	/**
	 * Returns all the layouts belonging to the group that are children of the
	 * parent layout.
	 *
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param parentLayoutId the layout ID of the parent layout
	 * @return the matching layouts, or <code>null</code> if no matches were
	 found
	 */
	public static java.util.List<com.liferay.portal.kernel.model.Layout>
		getLayouts(long groupId, boolean privateLayout, long parentLayoutId) {

		return getService().getLayouts(groupId, privateLayout, parentLayoutId);
	}

	/**
	 * Returns a range of all the layouts belonging to the group that are
	 * children of the parent layout.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param parentLayoutId the layout ID of the parent layout
	 * @param incomplete whether the layout is incomplete
	 * @param start the lower bound of the range of layouts
	 * @param end the upper bound of the range of layouts (not inclusive)
	 * @return the matching layouts, or <code>null</code> if no matches were
	 found
	 */
	public static java.util.List<com.liferay.portal.kernel.model.Layout>
		getLayouts(
			long groupId, boolean privateLayout, long parentLayoutId,
			boolean incomplete, int start, int end) {

		return getService().getLayouts(
			groupId, privateLayout, parentLayoutId, incomplete, start, end);
	}

	/**
	 * Returns a range of all the layouts belonging to the group that are
	 * children of the parent layout.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param parentLayoutId the layout ID of the parent layout
	 * @param start the lower bound of the range of layouts
	 * @param end the upper bound of the range of layouts (not inclusive)
	 * @param obc the comparator to order the layouts
	 * @return the matching layouts, or <code>null</code> if no matches were
	 found
	 */
	public static java.util.List<com.liferay.portal.kernel.model.Layout>
		getLayouts(
			long groupId, boolean privateLayout, long parentLayoutId,
			boolean incomplete, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.portal.kernel.model.Layout> obc) {

		return getService().getLayouts(
			groupId, privateLayout, parentLayoutId, incomplete, start, end,
			obc);
	}

	/**
	 * Returns all the layouts that match the layout IDs and belong to the
	 * group.
	 *
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param layoutIds the layout IDs of the layouts
	 * @return the matching layouts, or an empty list if no matches were found
	 * @throws PortalException if a portal exception occurred
	 */
	public static java.util.List<com.liferay.portal.kernel.model.Layout>
			getLayouts(long groupId, boolean privateLayout, long[] layoutIds)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLayouts(groupId, privateLayout, layoutIds);
	}

	/**
	 * Returns all the layouts that match the type and belong to the group.
	 *
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param type the type of the layouts (optionally {@link
	 LayoutConstants#TYPE_PORTLET})
	 * @return the matching layouts, or <code>null</code> if no matches were
	 found
	 */
	public static java.util.List<com.liferay.portal.kernel.model.Layout>
			getLayouts(long groupId, boolean privateLayout, String type)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLayouts(groupId, privateLayout, type);
	}

	/**
	 * Returns a range of all the layouts belonging to the group.
	 *
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param keywords keywords
	 * @param types layout types
	 * @param start the lower bound of the range of layouts
	 * @param end the upper bound of the range of layouts (not inclusive)
	 * @param obc the comparator to order the layouts
	 * @return the matching layouts, or <code>null</code> if no matches were
	 found
	 */
	public static java.util.List<com.liferay.portal.kernel.model.Layout>
			getLayouts(
				long groupId, boolean privateLayout, String keywords,
				String[] types, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.portal.kernel.model.Layout> obc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLayouts(
			groupId, privateLayout, keywords, types, start, end, obc);
	}

	/**
	 * Returns a range of all the layouts belonging to the group.
	 *
	 * @param groupId the primary key of the group
	 * @param start the lower bound of the range of layouts
	 * @param end the upper bound of the range of layouts (not inclusive)
	 * @param obc the comparator to order the layouts
	 * @return the matching layouts, or <code>null</code> if no matches were
	 found
	 */
	public static java.util.List<com.liferay.portal.kernel.model.Layout>
		getLayouts(
			long groupId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.portal.kernel.model.Layout> obc) {

		return getService().getLayouts(groupId, start, end, obc);
	}

	public static java.util.List<com.liferay.portal.kernel.model.Layout>
		getLayouts(
			long groupId, long leftPlid, long rightPlid, boolean privateLayout,
			int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.portal.kernel.model.Layout> obc) {

		return getService().getLayouts(
			groupId, leftPlid, rightPlid, privateLayout, start, end, obc);
	}

	/**
	 * Returns the layout references for all the layouts that belong to the
	 * company and belong to the portlet that matches the preferences.
	 *
	 * @param companyId the primary key of the company
	 * @param portletId the primary key of the portlet
	 * @param preferencesKey the portlet's preference key
	 * @param preferencesValue the portlet's preference value
	 * @return the layout references of the matching layouts
	 */
	public static com.liferay.portal.kernel.model.LayoutReference[] getLayouts(
		long companyId, String portletId, String preferencesKey,
		String preferencesValue) {

		return getService().getLayouts(
			companyId, portletId, preferencesKey, preferencesValue);
	}

	/**
	 * Returns a range of all the layouts belonging to the group.
	 *
	 * @param groupId the primary key of the group
	 * @param keywords keywords
	 * @param types layout types
	 * @param start the lower bound of the range of layouts
	 * @param end the upper bound of the range of layouts (not inclusive)
	 * @param obc the comparator to order the layouts
	 * @return the matching layouts, or <code>null</code> if no matches were
	 found
	 */
	public static java.util.List<com.liferay.portal.kernel.model.Layout>
			getLayouts(
				long groupId, String keywords, String[] types, int start,
				int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.portal.kernel.model.Layout> obc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLayouts(
			groupId, keywords, types, start, end, obc);
	}

	public static java.util.List<com.liferay.portal.kernel.model.Layout>
		getLayoutsByLayoutPrototypeUuid(String layoutPrototypeUuid) {

		return getService().getLayoutsByLayoutPrototypeUuid(
			layoutPrototypeUuid);
	}

	public static int getLayoutsByLayoutPrototypeUuidCount(
		String layoutPrototypeUuid) {

		return getService().getLayoutsByLayoutPrototypeUuidCount(
			layoutPrototypeUuid);
	}

	/**
	 * Returns all the layouts matching the UUID and company.
	 *
	 * @param uuid the UUID of the layouts
	 * @param companyId the primary key of the company
	 * @return the matching layouts, or an empty list if no matches were found
	 */
	public static java.util.List<com.liferay.portal.kernel.model.Layout>
		getLayoutsByUuidAndCompanyId(String uuid, long companyId) {

		return getService().getLayoutsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of layouts matching the UUID and company.
	 *
	 * @param uuid the UUID of the layouts
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of layouts
	 * @param end the upper bound of the range of layouts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching layouts, or an empty list if no matches were found
	 */
	public static java.util.List<com.liferay.portal.kernel.model.Layout>
		getLayoutsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.portal.kernel.model.Layout> orderByComparator) {

		return getService().getLayoutsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of layouts.
	 *
	 * @return the number of layouts
	 */
	public static int getLayoutsCount() {
		return getService().getLayoutsCount();
	}

	public static int getLayoutsCount(
			com.liferay.portal.kernel.model.Group group, boolean privateLayout)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLayoutsCount(group, privateLayout);
	}

	public static int getLayoutsCount(
			com.liferay.portal.kernel.model.Group group, boolean privateLayout,
			boolean includeUserGroups)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLayoutsCount(
			group, privateLayout, includeUserGroups);
	}

	public static int getLayoutsCount(
		com.liferay.portal.kernel.model.Group group, boolean privateLayout,
		long parentLayoutId) {

		return getService().getLayoutsCount(
			group, privateLayout, parentLayoutId);
	}

	public static int getLayoutsCount(
		com.liferay.portal.kernel.model.Group group, boolean privateLayout,
		long[] layoutIds) {

		return getService().getLayoutsCount(group, privateLayout, layoutIds);
	}

	public static int getLayoutsCount(
			com.liferay.portal.kernel.model.Group group, boolean privateLayout,
			String keywords, String[] types)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLayoutsCount(
			group, privateLayout, keywords, types);
	}

	public static int getLayoutsCount(long groupId) {
		return getService().getLayoutsCount(groupId);
	}

	public static int getLayoutsCount(
		long groupId, long leftPlid, long rightPlid, boolean privateLayout) {

		return getService().getLayoutsCount(
			groupId, leftPlid, rightPlid, privateLayout);
	}

	public static int getLayoutsCount(
			long groupId, String keywords, String[] types)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLayoutsCount(groupId, keywords, types);
	}

	public static int getLayoutsCount(
			com.liferay.portal.kernel.model.User user, boolean privateLayout)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLayoutsCount(user, privateLayout);
	}

	public static int getLayoutsCount(
			com.liferay.portal.kernel.model.User user, boolean privateLayout,
			boolean includeUserGroups)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLayoutsCount(
			user, privateLayout, includeUserGroups);
	}

	/**
	 * Returns the layout ID to use for the next layout.
	 *
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @return the layout ID to use for the next layout
	 */
	public static long getNextLayoutId(long groupId, boolean privateLayout) {
		return getService().getNextLayoutId(groupId, privateLayout);
	}

	/**
	 * Returns all the layouts without resource permissions
	 *
	 * @param roleId the primary key of the role
	 * @return all the layouts without resource permissions
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public static java.util.List<com.liferay.portal.kernel.model.Layout>
		getNoPermissionLayouts(long roleId) {

		return getService().getNoPermissionLayouts(roleId);
	}

	/**
	 * Returns all the layouts whose friendly URLs are <code>null</code>
	 *
	 * @return all the layouts whose friendly URLs are <code>null</code>
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public static java.util.List<com.liferay.portal.kernel.model.Layout>
		getNullFriendlyURLLayouts() {

		return getService().getNullFriendlyURLLayouts();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.Layout getParentLayout(
			com.liferay.portal.kernel.model.Layout layout)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getParentLayout(layout);
	}

	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static java.util.List<com.liferay.portal.kernel.model.Layout>
			getScopeGroupLayouts(long parentGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getScopeGroupLayouts(parentGroupId);
	}

	/**
	 * Returns all the layouts within scope of the group.
	 *
	 * @param parentGroupId the primary key of the group's parent group
	 * @param privateLayout whether the layout is private to the group
	 * @return the layouts within scope of the group
	 * @throws PortalException if a portal exception occurred
	 */
	public static java.util.List<com.liferay.portal.kernel.model.Layout>
			getScopeGroupLayouts(long parentGroupId, boolean privateLayout)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getScopeGroupLayouts(parentGroupId, privateLayout);
	}

	/**
	 * Returns <code>true</code> if there is a matching layout with the UUID,
	 * group, and privacy.
	 *
	 * @param uuid the layout's UUID
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @return <code>true</code> if the layout is found; <code>false</code>
	 otherwise
	 * @throws PortalException if a portal exception occurred
	 */
	public static boolean hasLayout(
			String uuid, long groupId, boolean privateLayout)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().hasLayout(uuid, groupId, privateLayout);
	}

	public static boolean hasLayouts(
			com.liferay.portal.kernel.model.Group group)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().hasLayouts(group);
	}

	public static boolean hasLayouts(
			com.liferay.portal.kernel.model.Group group, boolean privateLayout)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().hasLayouts(group, privateLayout);
	}

	public static boolean hasLayouts(
			com.liferay.portal.kernel.model.Group group, boolean privateLayout,
			boolean includeUserGroups)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().hasLayouts(group, privateLayout, includeUserGroups);
	}

	/**
	 * Returns <code>true</code> if the group has any layouts;
	 * <code>false</code> otherwise.
	 *
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param parentLayoutId the layout ID of the parent layout
	 * @return <code>true</code> if the group has any layouts;
	 <code>false</code> otherwise
	 */
	public static boolean hasLayouts(
		long groupId, boolean privateLayout, long parentLayoutId) {

		return getService().hasLayouts(groupId, privateLayout, parentLayoutId);
	}

	public static boolean hasLayouts(
			com.liferay.portal.kernel.model.User user, boolean privateLayout)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().hasLayouts(user, privateLayout);
	}

	public static boolean hasLayouts(
			com.liferay.portal.kernel.model.User user, boolean privateLayout,
			boolean includeUserGroups)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().hasLayouts(user, privateLayout, includeUserGroups);
	}

	public static boolean hasLayoutSetPrototypeLayout(
			long layoutSetPrototypeId, String layoutUuid)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().hasLayoutSetPrototypeLayout(
			layoutSetPrototypeId, layoutUuid);
	}

	public static boolean hasLayoutSetPrototypeLayout(
			String layoutSetPrototypeUuid, long companyId, String layoutUuid)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().hasLayoutSetPrototypeLayout(
			layoutSetPrototypeUuid, companyId, layoutUuid);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 com.liferay.exportimport.kernel.service.ExportImportLocalService#importLayouts(
	 ExportImportConfiguration, File)}}
	 */
	@Deprecated
	public static void importLayouts(
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration,
			java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().importLayouts(exportImportConfiguration, file);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 com.liferay.exportimport.kernel.service.ExportImportLocalService#importLayouts(
	 ExportImportConfiguration, InputStream)}}
	 */
	@Deprecated
	public static void importLayouts(
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration,
			java.io.InputStream is)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().importLayouts(exportImportConfiguration, is);
	}

	/**
	 * Imports the layouts from the byte array.
	 *
	 * @param userId the primary key of the user
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param parameterMap the mapping of parameters indicating which
	 information will be imported. For information on the keys
	 used in the map see {@link
	 com.liferay.exportimport.kernel.lar.PortletDataHandlerKeys}.
	 * @param bytes the byte array with the data
	 * @throws PortalException
	 * @see com.liferay.exportimport.kernel.lar.LayoutImporter
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public static void importLayouts(
			long userId, long groupId, boolean privateLayout,
			java.util.Map<String, String[]> parameterMap, byte[] bytes)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().importLayouts(
			userId, groupId, privateLayout, parameterMap, bytes);
	}

	/**
	 * Imports the layouts from the file.
	 *
	 * @param userId the primary key of the user
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param parameterMap the mapping of parameters indicating which
	 information will be imported. For information on the keys
	 used in the map see {@link
	 com.liferay.exportimport.kernel.lar.PortletDataHandlerKeys}.
	 * @param file the LAR file with the data
	 * @throws PortalException
	 * @see com.liferay.exportimport.kernel.lar.LayoutImporter
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public static void importLayouts(
			long userId, long groupId, boolean privateLayout,
			java.util.Map<String, String[]> parameterMap, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().importLayouts(
			userId, groupId, privateLayout, parameterMap, file);
	}

	/**
	 * Imports the layouts from the input stream.
	 *
	 * @param userId the primary key of the user
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param parameterMap the mapping of parameters indicating which
	 information will be imported. For information on the keys
	 used in the map see {@link
	 com.liferay.exportimport.kernel.lar.PortletDataHandlerKeys}.
	 * @param is the input stream
	 * @throws PortalException
	 * @see com.liferay.exportimport.kernel.lar.LayoutImporter
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public static void importLayouts(
			long userId, long groupId, boolean privateLayout,
			java.util.Map<String, String[]> parameterMap,
			java.io.InputStream is)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().importLayouts(
			userId, groupId, privateLayout, parameterMap, is);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 com.liferay.exportimport.kernel.service.ExportImportLocalService#importLayoutsDataDeletions(
	 ExportImportConfiguration, File)}
	 */
	@Deprecated
	public static void importLayoutsDataDeletions(
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration,
			java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().importLayoutsDataDeletions(
			exportImportConfiguration, file);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 com.liferay.exportimport.kernel.service.ExportImportLocalService#importLayoutsInBackground(
	 long, ExportImportConfiguration, File)}
	 */
	@Deprecated
	public static long importLayoutsInBackground(
			long userId,
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration,
			java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().importLayoutsInBackground(
			userId, exportImportConfiguration, file);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 com.liferay.exportimport.kernel.service.ExportImportLocalService#importLayoutsInBackground(
	 long, long, File)}
	 */
	@Deprecated
	public static long importLayoutsInBackground(
			long userId, long exportImportConfigurationId, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().importLayoutsInBackground(
			userId, exportImportConfigurationId, file);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public static long importLayoutsInBackground(
			long userId, String taskName, long groupId, boolean privateLayout,
			java.util.Map<String, String[]> parameterMap, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().importLayoutsInBackground(
			userId, taskName, groupId, privateLayout, parameterMap, file);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public static long importLayoutsInBackground(
			long userId, String taskName, long groupId, boolean privateLayout,
			java.util.Map<String, String[]> parameterMap,
			java.io.InputStream is)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().importLayoutsInBackground(
			userId, taskName, groupId, privateLayout, parameterMap, is);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 com.liferay.exportimport.kernel.service.ExportImportLocalService#importPortletDataDeletions(
	 ExportImportConfiguration, File)}
	 */
	@Deprecated
	public static void importPortletDataDeletions(
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration,
			java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().importPortletDataDeletions(
			exportImportConfiguration, file);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 com.liferay.exportimport.kernel.service.ExportImportLocalService#importPortletInfo(
	 ExportImportConfiguration, File)}
	 */
	@Deprecated
	public static void importPortletInfo(
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration,
			java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().importPortletInfo(exportImportConfiguration, file);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 com.liferay.exportimport.kernel.service.ExportImportLocalService#importPortletInfo(
	 ExportImportConfiguration, InputStream)}
	 */
	@Deprecated
	public static void importPortletInfo(
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration,
			java.io.InputStream is)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().importPortletInfo(exportImportConfiguration, is);
	}

	/**
	 * Imports the portlet information (categories, permissions, ... etc.) from
	 * the file.
	 *
	 * @param userId the primary key of the user
	 * @param plid the primary key of the target layout
	 * @param groupId the primary key of the target group
	 * @param portletId the primary key of the portlet
	 * @param parameterMap the mapping of parameters indicating which
	 information will be imported. For information on the keys
	 used in the map see {@link
	 com.liferay.exportimport.kernel.lar.PortletDataHandlerKeys}.
	 * @param file the LAR file with the data
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public static void importPortletInfo(
			long userId, long plid, long groupId, String portletId,
			java.util.Map<String, String[]> parameterMap, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().importPortletInfo(
			userId, plid, groupId, portletId, parameterMap, file);
	}

	/**
	 * Imports the portlet information (categories, permissions, ... etc.) from
	 * the input stream.
	 *
	 * @param userId the primary key of the user
	 * @param plid the primary key of the layout
	 * @param groupId the primary key of the group
	 * @param portletId the primary key of the portlet
	 * @param parameterMap the mapping of parameters indicating which
	 information will be imported. For information on the keys
	 used in the map see {@link
	 com.liferay.exportimport.kernel.lar.PortletDataHandlerKeys}.
	 * @param is the input stream
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public static void importPortletInfo(
			long userId, long plid, long groupId, String portletId,
			java.util.Map<String, String[]> parameterMap,
			java.io.InputStream is)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().importPortletInfo(
			userId, plid, groupId, portletId, parameterMap, is);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public static void importPortletInfo(
			long userId, String portletId,
			java.util.Map<String, String[]> parameterMap, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().importPortletInfo(userId, portletId, parameterMap, file);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public static void importPortletInfo(
			long userId, String portletId,
			java.util.Map<String, String[]> parameterMap,
			java.io.InputStream is)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().importPortletInfo(userId, portletId, parameterMap, is);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 com.liferay.exportimport.kernel.service.ExportImportLocalService#importPortletInfoInBackground(
	 long, ExportImportConfiguration, File)}
	 */
	@Deprecated
	public static long importPortletInfoInBackground(
			long userId,
			com.liferay.exportimport.kernel.model.ExportImportConfiguration
				exportImportConfiguration,
			java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().importPortletInfoInBackground(
			userId, exportImportConfiguration, file);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 com.liferay.exportimport.kernel.service.ExportImportLocalService#importPortletInfoInBackground(
	 long, long, File)}
	 */
	@Deprecated
	public static long importPortletInfoInBackground(
			long userId, long exportImportConfigurationId, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().importPortletInfoInBackground(
			userId, exportImportConfigurationId, file);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public static long importPortletInfoInBackground(
			long userId, String taskName, long plid, long groupId,
			String portletId, java.util.Map<String, String[]> parameterMap,
			java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().importPortletInfoInBackground(
			userId, taskName, plid, groupId, portletId, parameterMap, file);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public static long importPortletInfoInBackground(
			long userId, String taskName, long plid, long groupId,
			String portletId, java.util.Map<String, String[]> parameterMap,
			java.io.InputStream is)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().importPortletInfoInBackground(
			userId, taskName, plid, groupId, portletId, parameterMap, is);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public static long importPortletInfoInBackground(
			long userId, String taskName, String portletId,
			java.util.Map<String, String[]> parameterMap, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().importPortletInfoInBackground(
			userId, taskName, portletId, parameterMap, file);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public static long importPortletInfoInBackground(
			long userId, String taskName, String portletId,
			java.util.Map<String, String[]> parameterMap,
			java.io.InputStream is)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().importPortletInfoInBackground(
			userId, taskName, portletId, parameterMap, is);
	}

	/**
	 * Sets the layouts for the group, replacing and prioritizing all layouts of
	 * the parent layout.
	 *
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param parentLayoutId the layout ID of the parent layout
	 * @param layoutIds the layout IDs of the layouts
	 * @param serviceContext the service context to be applied
	 * @throws PortalException if a portal exception occurred
	 */
	public static void setLayouts(
			long groupId, boolean privateLayout, long parentLayoutId,
			long[] layoutIds, ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().setLayouts(
			groupId, privateLayout, parentLayoutId, layoutIds, serviceContext);
	}

	public static void updateAsset(
			long userId, com.liferay.portal.kernel.model.Layout layout,
			long[] assetCategoryIds, String[] assetTagNames)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().updateAsset(
			userId, layout, assetCategoryIds, assetTagNames);
	}

	/**
	 * Updates the friendly URL of the layout.
	 *
	 * @param userId the primary key of the user
	 * @param plid the primary key of the layout
	 * @param friendlyURL the friendly URL to be assigned
	 * @param languageId the primary key of the language
	 * @return the updated layout
	 * @throws PortalException if a portal exception occurred
	 */
	public static com.liferay.portal.kernel.model.Layout updateFriendlyURL(
			long userId, long plid, String friendlyURL, String languageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateFriendlyURL(
			userId, plid, friendlyURL, languageId);
	}

	/**
	 * Updates the friendly URL of the layout.
	 *
	 * @param plid the primary key of the layout
	 * @param friendlyURL the friendly URL to be assigned
	 * @param languageId the primary key of the language
	 * @return the updated layout
	 * @throws PortalException if a portal exception occurred
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 #updateFriendlyURL(long, long, String, String)}
	 */
	@Deprecated
	public static com.liferay.portal.kernel.model.Layout updateFriendlyURL(
			long plid, String friendlyURL, String languageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateFriendlyURL(plid, friendlyURL, languageId);
	}

	public static com.liferay.portal.kernel.model.Layout updateIconImage(
			long plid, byte[] bytes)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateIconImage(plid, bytes);
	}

	/**
	 * Updates the layout in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param layout the layout
	 * @return the layout that was updated
	 */
	public static com.liferay.portal.kernel.model.Layout updateLayout(
		com.liferay.portal.kernel.model.Layout layout) {

		return getService().updateLayout(layout);
	}

	public static com.liferay.portal.kernel.model.Layout updateLayout(
		com.liferay.portal.kernel.model.Layout layout, boolean rebuildTree) {

		return getService().updateLayout(layout, rebuildTree);
	}

	/**
	 * Updates the layout replacing its draft publish date.
	 *
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param layoutId the layout ID of the layout
	 * @param publishDate the date when draft was last published
	 * @return the updated layout
	 * @throws PortalException if a portal exception occurred
	 */
	public static com.liferay.portal.kernel.model.Layout updateLayout(
			long groupId, boolean privateLayout, long layoutId,
			java.util.Date publishDate)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateLayout(
			groupId, privateLayout, layoutId, publishDate);
	}

	/**
	 * Updates the layout replacing its referrer entity class name ID and
	 * primary key.
	 *
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param layoutId the layout ID of the layout
	 * @param referrerClassNameId the referrer entity class name ID
	 * @param referrerClassPK the referrer entity primary key
	 * @return the updated layout
	 * @throws PortalException if a portal exception occurred
	 */
	public static com.liferay.portal.kernel.model.Layout updateLayout(
			long groupId, boolean privateLayout, long layoutId,
			long referrerClassNameId, long referrerClassPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateLayout(
			groupId, privateLayout, layoutId, referrerClassNameId,
			referrerClassPK);
	}

	/**
	 * Updates the layout.
	 *
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param layoutId the layout ID of the layout
	 * @param parentLayoutId the layout ID of the layout's new parent layout
	 * @param nameMap the locales and localized names to merge (optionally
	 <code>null</code>)
	 * @param titleMap the locales and localized titles to merge (optionally
	 <code>null</code>)
	 * @param descriptionMap the locales and localized descriptions to merge
	 (optionally <code>null</code>)
	 * @param keywordsMap the locales and localized keywords to merge
	 (optionally <code>null</code>)
	 * @param robotsMap the locales and localized robots to merge (optionally
	 <code>null</code>)
	 * @param type the layout's new type (optionally {@link
	 LayoutConstants#TYPE_PORTLET})
	 * @param hidden whether the layout is hidden
	 * @param friendlyURLMap the layout's locales and localized friendly URLs.
	 To see how the URL is normalized when accessed, see {@link
	 com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil#normalize(
	 String)}.
	 * @param iconImage whether the icon image will be updated
	 * @param iconBytes the byte array of the layout's new icon image
	 * @param serviceContext the service context to be applied. Can set the
	 modification date and expando bridge attributes for the layout.
	 For layouts that are linked to a layout prototype, attributes
	 named <code>layoutPrototypeUuid</code> and
	 <code>layoutPrototypeLinkedEnabled</code> can be specified to
	 provide the unique identifier of the source prototype and a
	 boolean to determine whether a link to it should be enabled to
	 activate propagation of changes made to the linked page in the
	 prototype.
	 * @return the updated layout
	 * @throws PortalException if a portal exception occurred
	 */
	public static com.liferay.portal.kernel.model.Layout updateLayout(
			long groupId, boolean privateLayout, long layoutId,
			long parentLayoutId,
			java.util.Map<java.util.Locale, String> nameMap,
			java.util.Map<java.util.Locale, String> titleMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			java.util.Map<java.util.Locale, String> keywordsMap,
			java.util.Map<java.util.Locale, String> robotsMap, String type,
			boolean hidden,
			java.util.Map<java.util.Locale, String> friendlyURLMap,
			boolean iconImage, byte[] iconBytes, ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateLayout(
			groupId, privateLayout, layoutId, parentLayoutId, nameMap, titleMap,
			descriptionMap, keywordsMap, robotsMap, type, hidden,
			friendlyURLMap, iconImage, iconBytes, serviceContext);
	}

	/**
	 * Updates the layout replacing its type settings.
	 *
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param layoutId the layout ID of the layout
	 * @param typeSettings the settings to load the unicode properties object.
	 See {@link UnicodeProperties #fastLoad(String)}.
	 * @return the updated layout
	 * @throws PortalException if a portal exception occurred
	 */
	public static com.liferay.portal.kernel.model.Layout updateLayout(
			long groupId, boolean privateLayout, long layoutId,
			String typeSettings)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateLayout(
			groupId, privateLayout, layoutId, typeSettings);
	}

	/**
	 * Updates the look and feel of the layout.
	 *
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param layoutId the layout ID of the layout
	 * @param themeId the primary key of the layout's new theme
	 * @param colorSchemeId the primary key of the layout's new color scheme
	 * @param css the layout's new CSS
	 * @return the updated layout
	 * @throws PortalException if a portal exception occurred
	 */
	public static com.liferay.portal.kernel.model.Layout updateLookAndFeel(
			long groupId, boolean privateLayout, long layoutId, String themeId,
			String colorSchemeId, String css)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateLookAndFeel(
			groupId, privateLayout, layoutId, themeId, colorSchemeId, css);
	}

	/**
	 * Updates the name of the layout.
	 *
	 * @param layout the layout to be updated
	 * @param name the layout's new name
	 * @param languageId the primary key of the language. For more information
	 see {@link Locale}.
	 * @return the updated layout
	 * @throws PortalException if a portal exception occurred
	 */
	public static com.liferay.portal.kernel.model.Layout updateName(
			com.liferay.portal.kernel.model.Layout layout, String name,
			String languageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateName(layout, name, languageId);
	}

	/**
	 * Updates the name of the layout matching the group, layout ID, and
	 * privacy.
	 *
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param layoutId the layout ID of the layout
	 * @param name the layout's new name
	 * @param languageId the primary key of the language. For more information
	 see {@link Locale}.
	 * @return the updated layout
	 * @throws PortalException if a portal exception occurred
	 */
	public static com.liferay.portal.kernel.model.Layout updateName(
			long groupId, boolean privateLayout, long layoutId, String name,
			String languageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateName(
			groupId, privateLayout, layoutId, name, languageId);
	}

	/**
	 * Updates the name of the layout matching the primary key.
	 *
	 * @param plid the primary key of the layout
	 * @param name the name to be assigned
	 * @param languageId the primary key of the language. For more information
	 see {@link Locale}.
	 * @return the updated layout
	 * @throws PortalException if a portal exception occurred
	 */
	public static com.liferay.portal.kernel.model.Layout updateName(
			long plid, String name, String languageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateName(plid, name, languageId);
	}

	/**
	 * Updates the parent layout ID of the layout matching the group, layout ID,
	 * and privacy.
	 *
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param layoutId the layout ID of the layout
	 * @param parentLayoutId the layout ID to be assigned to the parent
	 layout
	 * @return the matching layout
	 * @throws PortalException if a portal exception occurred
	 */
	public static com.liferay.portal.kernel.model.Layout updateParentLayoutId(
			long groupId, boolean privateLayout, long layoutId,
			long parentLayoutId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateParentLayoutId(
			groupId, privateLayout, layoutId, parentLayoutId);
	}

	/**
	 * Updates the parent layout ID of the layout matching the primary key. If a
	 * layout matching the parent primary key is found, the layout ID of that
	 * layout is assigned, otherwise {@link
	 * LayoutConstants#DEFAULT_PARENT_LAYOUT_ID} is assigned.
	 *
	 * @param plid the primary key of the layout
	 * @param parentPlid the primary key of the parent layout
	 * @return the layout matching the primary key
	 * @throws PortalException if a portal exception occurred
	 */
	public static com.liferay.portal.kernel.model.Layout updateParentLayoutId(
			long plid, long parentPlid)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateParentLayoutId(plid, parentPlid);
	}

	/**
	 * Updates the parent layout ID and priority of the layout.
	 *
	 * @param plid the primary key of the layout
	 * @param parentPlid the primary key of the parent layout
	 * @param priority the layout's new priority
	 * @return the layout matching the primary key
	 * @throws PortalException if a portal exception occurred
	 */
	public static com.liferay.portal.kernel.model.Layout
			updateParentLayoutIdAndPriority(
				long plid, long parentPlid, int priority)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateParentLayoutIdAndPriority(
			plid, parentPlid, priority);
	}

	/**
	 * Updates the priorities of the layouts.
	 *
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @throws PortalException
	 */
	public static void updatePriorities(long groupId, boolean privateLayout)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().updatePriorities(groupId, privateLayout);
	}

	/**
	 * Updates the priority of the layout.
	 *
	 * @param layout the layout to be updated
	 * @param priority the layout's new priority
	 * @return the updated layout
	 * @throws PortalException if a portal exception occurred
	 */
	public static com.liferay.portal.kernel.model.Layout updatePriority(
			com.liferay.portal.kernel.model.Layout layout, int priority)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updatePriority(layout, priority);
	}

	/**
	 * Updates the priority of the layout matching the group, layout ID, and
	 * privacy.
	 *
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param layoutId the layout ID of the layout
	 * @param priority the layout's new priority
	 * @return the updated layout
	 * @throws PortalException if a portal exception occurred
	 */
	public static com.liferay.portal.kernel.model.Layout updatePriority(
			long groupId, boolean privateLayout, long layoutId, int priority)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updatePriority(
			groupId, privateLayout, layoutId, priority);
	}

	/**
	 * Updates the priority of the layout matching the group, layout ID, and
	 * privacy, setting the layout's priority based on the priorities of the
	 * next and previous layouts.
	 *
	 * @param groupId the primary key of the group
	 * @param privateLayout whether the layout is private to the group
	 * @param layoutId the layout ID of the layout
	 * @param nextLayoutId the layout ID of the next layout
	 * @param previousLayoutId the layout ID of the previous layout
	 * @return the updated layout
	 * @throws PortalException if a portal exception occurred
	 */
	public static com.liferay.portal.kernel.model.Layout updatePriority(
			long groupId, boolean privateLayout, long layoutId,
			long nextLayoutId, long previousLayoutId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updatePriority(
			groupId, privateLayout, layoutId, nextLayoutId, previousLayoutId);
	}

	/**
	 * Updates the priority of the layout matching the primary key.
	 *
	 * @param plid the primary key of the layout
	 * @param priority the layout's new priority
	 * @return the updated layout
	 * @throws PortalException if a portal exception occurred
	 */
	public static com.liferay.portal.kernel.model.Layout updatePriority(
			long plid, int priority)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updatePriority(plid, priority);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 com.liferay.exportimport.kernel.service.ExportImportLocalService#validateImportLayoutsFile(
	 ExportImportConfiguration, File)}
	 */
	@Deprecated
	public static com.liferay.exportimport.kernel.lar.MissingReferences
			validateImportLayoutsFile(
				com.liferay.exportimport.kernel.model.ExportImportConfiguration
					exportImportConfiguration,
				java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().validateImportLayoutsFile(
			exportImportConfiguration, file);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 com.liferay.exportimport.kernel.service.ExportImportLocalService#validateImportLayoutsFile(
	 ExportImportConfiguration, InputStream)}
	 */
	@Deprecated
	public static com.liferay.exportimport.kernel.lar.MissingReferences
			validateImportLayoutsFile(
				com.liferay.exportimport.kernel.model.ExportImportConfiguration
					exportImportConfiguration,
				java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().validateImportLayoutsFile(
			exportImportConfiguration, inputStream);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public static com.liferay.exportimport.kernel.lar.MissingReferences
			validateImportLayoutsFile(
				long userId, long groupId, boolean privateLayout,
				java.util.Map<String, String[]> parameterMap, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().validateImportLayoutsFile(
			userId, groupId, privateLayout, parameterMap, file);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public static com.liferay.exportimport.kernel.lar.MissingReferences
			validateImportLayoutsFile(
				long userId, long groupId, boolean privateLayout,
				java.util.Map<String, String[]> parameterMap,
				java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().validateImportLayoutsFile(
			userId, groupId, privateLayout, parameterMap, inputStream);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 com.liferay.exportimport.kernel.service.ExportImportLocalService#validateImportPortletInfo(
	 ExportImportConfiguration, File)}
	 */
	@Deprecated
	public static com.liferay.exportimport.kernel.lar.MissingReferences
			validateImportPortletInfo(
				com.liferay.exportimport.kernel.model.ExportImportConfiguration
					exportImportConfiguration,
				java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().validateImportPortletInfo(
			exportImportConfiguration, file);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 com.liferay.exportimport.kernel.service.ExportImportLocalService#validateImportPortletInfo(
	 ExportImportConfiguration, InputStream)}
	 */
	@Deprecated
	public static com.liferay.exportimport.kernel.lar.MissingReferences
			validateImportPortletInfo(
				com.liferay.exportimport.kernel.model.ExportImportConfiguration
					exportImportConfiguration,
				java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().validateImportPortletInfo(
			exportImportConfiguration, inputStream);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public static com.liferay.exportimport.kernel.lar.MissingReferences
			validateImportPortletInfo(
				long userId, long plid, long groupId, String portletId,
				java.util.Map<String, String[]> parameterMap, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().validateImportPortletInfo(
			userId, plid, groupId, portletId, parameterMap, file);
	}

	/**
	 * @throws PortalException
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public static com.liferay.exportimport.kernel.lar.MissingReferences
			validateImportPortletInfo(
				long userId, long plid, long groupId, String portletId,
				java.util.Map<String, String[]> parameterMap,
				java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().validateImportPortletInfo(
			userId, plid, groupId, portletId, parameterMap, inputStream);
	}

	public static LayoutLocalService getService() {
		if (_service == null) {
			_service = (LayoutLocalService)PortalBeanLocatorUtil.locate(
				LayoutLocalService.class.getName());

			ReferenceRegistry.registerReference(
				LayoutLocalServiceUtil.class, "_service");
		}

		return _service;
	}

	private static LayoutLocalService _service;

}