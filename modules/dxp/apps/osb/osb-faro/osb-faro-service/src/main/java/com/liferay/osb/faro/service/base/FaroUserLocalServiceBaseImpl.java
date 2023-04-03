/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.faro.service.base;

import com.liferay.osb.faro.model.FaroUser;
import com.liferay.osb.faro.service.FaroUserLocalService;
import com.liferay.osb.faro.service.FaroUserLocalServiceUtil;
import com.liferay.osb.faro.service.persistence.FaroPreferencesPersistence;
import com.liferay.osb.faro.service.persistence.FaroProjectFinder;
import com.liferay.osb.faro.service.persistence.FaroProjectPersistence;
import com.liferay.osb.faro.service.persistence.FaroUserFinder;
import com.liferay.osb.faro.service.persistence.FaroUserPersistence;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.GroupPersistence;
import com.liferay.portal.kernel.service.persistence.RolePersistence;
import com.liferay.portal.kernel.service.persistence.UserGroupRolePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the faro user local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.osb.faro.service.impl.FaroUserLocalServiceImpl}.
 * </p>
 *
 * @author Matthew Kong
 * @see com.liferay.osb.faro.service.impl.FaroUserLocalServiceImpl
 * @generated
 */
public abstract class FaroUserLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements FaroUserLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>FaroUserLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>FaroUserLocalServiceUtil</code>.
	 */

	/**
	 * Adds the faro user to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FaroUserLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param faroUser the faro user
	 * @return the faro user that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public FaroUser addFaroUser(FaroUser faroUser) {
		faroUser.setNew(true);

		return faroUserPersistence.update(faroUser);
	}

	/**
	 * Creates a new faro user with the primary key. Does not add the faro user to the database.
	 *
	 * @param faroUserId the primary key for the new faro user
	 * @return the new faro user
	 */
	@Override
	@Transactional(enabled = false)
	public FaroUser createFaroUser(long faroUserId) {
		return faroUserPersistence.create(faroUserId);
	}

	/**
	 * Deletes the faro user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FaroUserLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param faroUserId the primary key of the faro user
	 * @return the faro user that was removed
	 * @throws PortalException if a faro user with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public FaroUser deleteFaroUser(long faroUserId) throws PortalException {
		return faroUserPersistence.remove(faroUserId);
	}

	/**
	 * Deletes the faro user from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FaroUserLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param faroUser the faro user
	 * @return the faro user that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public FaroUser deleteFaroUser(FaroUser faroUser) {
		return faroUserPersistence.remove(faroUser);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return faroUserPersistence.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(DSLQuery dslQuery) {
		Long count = dslQuery(dslQuery);

		return count.intValue();
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			FaroUser.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return faroUserPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.faro.model.impl.FaroUserModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return faroUserPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.faro.model.impl.FaroUserModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return faroUserPersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return faroUserPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return faroUserPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public FaroUser fetchFaroUser(long faroUserId) {
		return faroUserPersistence.fetchByPrimaryKey(faroUserId);
	}

	/**
	 * Returns the faro user with the primary key.
	 *
	 * @param faroUserId the primary key of the faro user
	 * @return the faro user
	 * @throws PortalException if a faro user with the primary key could not be found
	 */
	@Override
	public FaroUser getFaroUser(long faroUserId) throws PortalException {
		return faroUserPersistence.findByPrimaryKey(faroUserId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(faroUserLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(FaroUser.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("faroUserId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			faroUserLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(FaroUser.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("faroUserId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(faroUserLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(FaroUser.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("faroUserId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return faroUserPersistence.create(((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		if (_log.isWarnEnabled()) {
			_log.warn(
				"Implement FaroUserLocalServiceImpl#deleteFaroUser(FaroUser) to avoid orphaned data");
		}

		return faroUserLocalService.deleteFaroUser((FaroUser)persistedModel);
	}

	@Override
	public BasePersistence<FaroUser> getBasePersistence() {
		return faroUserPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return faroUserPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the faro users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.faro.model.impl.FaroUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of faro users
	 * @param end the upper bound of the range of faro users (not inclusive)
	 * @return the range of faro users
	 */
	@Override
	public List<FaroUser> getFaroUsers(int start, int end) {
		return faroUserPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of faro users.
	 *
	 * @return the number of faro users
	 */
	@Override
	public int getFaroUsersCount() {
		return faroUserPersistence.countAll();
	}

	/**
	 * Updates the faro user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FaroUserLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param faroUser the faro user
	 * @return the faro user that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public FaroUser updateFaroUser(FaroUser faroUser) {
		return faroUserPersistence.update(faroUser);
	}

	/**
	 * Returns the faro user local service.
	 *
	 * @return the faro user local service
	 */
	public FaroUserLocalService getFaroUserLocalService() {
		return faroUserLocalService;
	}

	/**
	 * Sets the faro user local service.
	 *
	 * @param faroUserLocalService the faro user local service
	 */
	public void setFaroUserLocalService(
		FaroUserLocalService faroUserLocalService) {

		this.faroUserLocalService = faroUserLocalService;
	}

	/**
	 * Returns the faro user persistence.
	 *
	 * @return the faro user persistence
	 */
	public FaroUserPersistence getFaroUserPersistence() {
		return faroUserPersistence;
	}

	/**
	 * Sets the faro user persistence.
	 *
	 * @param faroUserPersistence the faro user persistence
	 */
	public void setFaroUserPersistence(
		FaroUserPersistence faroUserPersistence) {

		this.faroUserPersistence = faroUserPersistence;
	}

	/**
	 * Returns the faro user finder.
	 *
	 * @return the faro user finder
	 */
	public FaroUserFinder getFaroUserFinder() {
		return faroUserFinder;
	}

	/**
	 * Sets the faro user finder.
	 *
	 * @param faroUserFinder the faro user finder
	 */
	public void setFaroUserFinder(FaroUserFinder faroUserFinder) {
		this.faroUserFinder = faroUserFinder;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService
		getCounterLocalService() {

		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService
			counterLocalService) {

		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the faro preferences local service.
	 *
	 * @return the faro preferences local service
	 */
	public com.liferay.osb.faro.service.FaroPreferencesLocalService
		getFaroPreferencesLocalService() {

		return faroPreferencesLocalService;
	}

	/**
	 * Sets the faro preferences local service.
	 *
	 * @param faroPreferencesLocalService the faro preferences local service
	 */
	public void setFaroPreferencesLocalService(
		com.liferay.osb.faro.service.FaroPreferencesLocalService
			faroPreferencesLocalService) {

		this.faroPreferencesLocalService = faroPreferencesLocalService;
	}

	/**
	 * Returns the faro preferences persistence.
	 *
	 * @return the faro preferences persistence
	 */
	public FaroPreferencesPersistence getFaroPreferencesPersistence() {
		return faroPreferencesPersistence;
	}

	/**
	 * Sets the faro preferences persistence.
	 *
	 * @param faroPreferencesPersistence the faro preferences persistence
	 */
	public void setFaroPreferencesPersistence(
		FaroPreferencesPersistence faroPreferencesPersistence) {

		this.faroPreferencesPersistence = faroPreferencesPersistence;
	}

	/**
	 * Returns the faro project local service.
	 *
	 * @return the faro project local service
	 */
	public com.liferay.osb.faro.service.FaroProjectLocalService
		getFaroProjectLocalService() {

		return faroProjectLocalService;
	}

	/**
	 * Sets the faro project local service.
	 *
	 * @param faroProjectLocalService the faro project local service
	 */
	public void setFaroProjectLocalService(
		com.liferay.osb.faro.service.FaroProjectLocalService
			faroProjectLocalService) {

		this.faroProjectLocalService = faroProjectLocalService;
	}

	/**
	 * Returns the faro project persistence.
	 *
	 * @return the faro project persistence
	 */
	public FaroProjectPersistence getFaroProjectPersistence() {
		return faroProjectPersistence;
	}

	/**
	 * Sets the faro project persistence.
	 *
	 * @param faroProjectPersistence the faro project persistence
	 */
	public void setFaroProjectPersistence(
		FaroProjectPersistence faroProjectPersistence) {

		this.faroProjectPersistence = faroProjectPersistence;
	}

	/**
	 * Returns the faro project finder.
	 *
	 * @return the faro project finder
	 */
	public FaroProjectFinder getFaroProjectFinder() {
		return faroProjectFinder;
	}

	/**
	 * Sets the faro project finder.
	 *
	 * @param faroProjectFinder the faro project finder
	 */
	public void setFaroProjectFinder(FaroProjectFinder faroProjectFinder) {
		this.faroProjectFinder = faroProjectFinder;
	}

	/**
	 * Returns the group local service.
	 *
	 * @return the group local service
	 */
	public com.liferay.portal.kernel.service.GroupLocalService
		getGroupLocalService() {

		return groupLocalService;
	}

	/**
	 * Sets the group local service.
	 *
	 * @param groupLocalService the group local service
	 */
	public void setGroupLocalService(
		com.liferay.portal.kernel.service.GroupLocalService groupLocalService) {

		this.groupLocalService = groupLocalService;
	}

	/**
	 * Returns the group persistence.
	 *
	 * @return the group persistence
	 */
	public GroupPersistence getGroupPersistence() {
		return groupPersistence;
	}

	/**
	 * Sets the group persistence.
	 *
	 * @param groupPersistence the group persistence
	 */
	public void setGroupPersistence(GroupPersistence groupPersistence) {
		this.groupPersistence = groupPersistence;
	}

	/**
	 * Returns the role local service.
	 *
	 * @return the role local service
	 */
	public com.liferay.portal.kernel.service.RoleLocalService
		getRoleLocalService() {

		return roleLocalService;
	}

	/**
	 * Sets the role local service.
	 *
	 * @param roleLocalService the role local service
	 */
	public void setRoleLocalService(
		com.liferay.portal.kernel.service.RoleLocalService roleLocalService) {

		this.roleLocalService = roleLocalService;
	}

	/**
	 * Returns the role persistence.
	 *
	 * @return the role persistence
	 */
	public RolePersistence getRolePersistence() {
		return rolePersistence;
	}

	/**
	 * Sets the role persistence.
	 *
	 * @param rolePersistence the role persistence
	 */
	public void setRolePersistence(RolePersistence rolePersistence) {
		this.rolePersistence = rolePersistence;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService
		getUserLocalService() {

		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {

		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Returns the user group role local service.
	 *
	 * @return the user group role local service
	 */
	public com.liferay.portal.kernel.service.UserGroupRoleLocalService
		getUserGroupRoleLocalService() {

		return userGroupRoleLocalService;
	}

	/**
	 * Sets the user group role local service.
	 *
	 * @param userGroupRoleLocalService the user group role local service
	 */
	public void setUserGroupRoleLocalService(
		com.liferay.portal.kernel.service.UserGroupRoleLocalService
			userGroupRoleLocalService) {

		this.userGroupRoleLocalService = userGroupRoleLocalService;
	}

	/**
	 * Returns the user group role persistence.
	 *
	 * @return the user group role persistence
	 */
	public UserGroupRolePersistence getUserGroupRolePersistence() {
		return userGroupRolePersistence;
	}

	/**
	 * Sets the user group role persistence.
	 *
	 * @param userGroupRolePersistence the user group role persistence
	 */
	public void setUserGroupRolePersistence(
		UserGroupRolePersistence userGroupRolePersistence) {

		this.userGroupRolePersistence = userGroupRolePersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register(
			"com.liferay.osb.faro.model.FaroUser", faroUserLocalService);

		_setLocalServiceUtilService(faroUserLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.osb.faro.model.FaroUser");

		_setLocalServiceUtilService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return FaroUserLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return FaroUser.class;
	}

	protected String getModelClassName() {
		return FaroUser.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = faroUserPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	private void _setLocalServiceUtilService(
		FaroUserLocalService faroUserLocalService) {

		try {
			Field field = FaroUserLocalServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, faroUserLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@BeanReference(type = FaroUserLocalService.class)
	protected FaroUserLocalService faroUserLocalService;

	@BeanReference(type = FaroUserPersistence.class)
	protected FaroUserPersistence faroUserPersistence;

	@BeanReference(type = FaroUserFinder.class)
	protected FaroUserFinder faroUserFinder;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@BeanReference(
		type = com.liferay.osb.faro.service.FaroPreferencesLocalService.class
	)
	protected com.liferay.osb.faro.service.FaroPreferencesLocalService
		faroPreferencesLocalService;

	@BeanReference(type = FaroPreferencesPersistence.class)
	protected FaroPreferencesPersistence faroPreferencesPersistence;

	@BeanReference(
		type = com.liferay.osb.faro.service.FaroProjectLocalService.class
	)
	protected com.liferay.osb.faro.service.FaroProjectLocalService
		faroProjectLocalService;

	@BeanReference(type = FaroProjectPersistence.class)
	protected FaroProjectPersistence faroProjectPersistence;

	@BeanReference(type = FaroProjectFinder.class)
	protected FaroProjectFinder faroProjectFinder;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.GroupLocalService.class
	)
	protected com.liferay.portal.kernel.service.GroupLocalService
		groupLocalService;

	@ServiceReference(type = GroupPersistence.class)
	protected GroupPersistence groupPersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.RoleLocalService.class
	)
	protected com.liferay.portal.kernel.service.RoleLocalService
		roleLocalService;

	@ServiceReference(type = RolePersistence.class)
	protected RolePersistence rolePersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.UserLocalService.class
	)
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.UserGroupRoleLocalService.class
	)
	protected com.liferay.portal.kernel.service.UserGroupRoleLocalService
		userGroupRoleLocalService;

	@ServiceReference(type = UserGroupRolePersistence.class)
	protected UserGroupRolePersistence userGroupRolePersistence;

	private static final Log _log = LogFactoryUtil.getLog(
		FaroUserLocalServiceBaseImpl.class);

	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}