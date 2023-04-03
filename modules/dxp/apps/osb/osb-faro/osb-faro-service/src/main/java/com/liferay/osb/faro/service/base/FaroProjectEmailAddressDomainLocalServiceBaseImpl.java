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

import com.liferay.osb.faro.model.FaroProjectEmailAddressDomain;
import com.liferay.osb.faro.service.FaroProjectEmailAddressDomainLocalService;
import com.liferay.osb.faro.service.FaroProjectEmailAddressDomainLocalServiceUtil;
import com.liferay.osb.faro.service.persistence.FaroProjectEmailAddressDomainPersistence;
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
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the faro project email address domain local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.osb.faro.service.impl.FaroProjectEmailAddressDomainLocalServiceImpl}.
 * </p>
 *
 * @author Matthew Kong
 * @see com.liferay.osb.faro.service.impl.FaroProjectEmailAddressDomainLocalServiceImpl
 * @generated
 */
public abstract class FaroProjectEmailAddressDomainLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements FaroProjectEmailAddressDomainLocalService,
			   IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>FaroProjectEmailAddressDomainLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>FaroProjectEmailAddressDomainLocalServiceUtil</code>.
	 */

	/**
	 * Adds the faro project email address domain to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FaroProjectEmailAddressDomainLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param faroProjectEmailAddressDomain the faro project email address domain
	 * @return the faro project email address domain that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public FaroProjectEmailAddressDomain addFaroProjectEmailAddressDomain(
		FaroProjectEmailAddressDomain faroProjectEmailAddressDomain) {

		faroProjectEmailAddressDomain.setNew(true);

		return faroProjectEmailAddressDomainPersistence.update(
			faroProjectEmailAddressDomain);
	}

	/**
	 * Creates a new faro project email address domain with the primary key. Does not add the faro project email address domain to the database.
	 *
	 * @param faroProjectEmailAddressDomainId the primary key for the new faro project email address domain
	 * @return the new faro project email address domain
	 */
	@Override
	@Transactional(enabled = false)
	public FaroProjectEmailAddressDomain createFaroProjectEmailAddressDomain(
		long faroProjectEmailAddressDomainId) {

		return faroProjectEmailAddressDomainPersistence.create(
			faroProjectEmailAddressDomainId);
	}

	/**
	 * Deletes the faro project email address domain with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FaroProjectEmailAddressDomainLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param faroProjectEmailAddressDomainId the primary key of the faro project email address domain
	 * @return the faro project email address domain that was removed
	 * @throws PortalException if a faro project email address domain with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public FaroProjectEmailAddressDomain deleteFaroProjectEmailAddressDomain(
			long faroProjectEmailAddressDomainId)
		throws PortalException {

		return faroProjectEmailAddressDomainPersistence.remove(
			faroProjectEmailAddressDomainId);
	}

	/**
	 * Deletes the faro project email address domain from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FaroProjectEmailAddressDomainLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param faroProjectEmailAddressDomain the faro project email address domain
	 * @return the faro project email address domain that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public FaroProjectEmailAddressDomain deleteFaroProjectEmailAddressDomain(
		FaroProjectEmailAddressDomain faroProjectEmailAddressDomain) {

		return faroProjectEmailAddressDomainPersistence.remove(
			faroProjectEmailAddressDomain);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return faroProjectEmailAddressDomainPersistence.dslQuery(dslQuery);
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
			FaroProjectEmailAddressDomain.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return faroProjectEmailAddressDomainPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.faro.model.impl.FaroProjectEmailAddressDomainModelImpl</code>.
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

		return faroProjectEmailAddressDomainPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.faro.model.impl.FaroProjectEmailAddressDomainModelImpl</code>.
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

		return faroProjectEmailAddressDomainPersistence.findWithDynamicQuery(
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
		return faroProjectEmailAddressDomainPersistence.countWithDynamicQuery(
			dynamicQuery);
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

		return faroProjectEmailAddressDomainPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public FaroProjectEmailAddressDomain fetchFaroProjectEmailAddressDomain(
		long faroProjectEmailAddressDomainId) {

		return faroProjectEmailAddressDomainPersistence.fetchByPrimaryKey(
			faroProjectEmailAddressDomainId);
	}

	/**
	 * Returns the faro project email address domain with the primary key.
	 *
	 * @param faroProjectEmailAddressDomainId the primary key of the faro project email address domain
	 * @return the faro project email address domain
	 * @throws PortalException if a faro project email address domain with the primary key could not be found
	 */
	@Override
	public FaroProjectEmailAddressDomain getFaroProjectEmailAddressDomain(
			long faroProjectEmailAddressDomainId)
		throws PortalException {

		return faroProjectEmailAddressDomainPersistence.findByPrimaryKey(
			faroProjectEmailAddressDomainId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			faroProjectEmailAddressDomainLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(
			FaroProjectEmailAddressDomain.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"faroProjectEmailAddressDomainId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			faroProjectEmailAddressDomainLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			FaroProjectEmailAddressDomain.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"faroProjectEmailAddressDomainId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			faroProjectEmailAddressDomainLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(
			FaroProjectEmailAddressDomain.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"faroProjectEmailAddressDomainId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return faroProjectEmailAddressDomainPersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		if (_log.isWarnEnabled()) {
			_log.warn(
				"Implement FaroProjectEmailAddressDomainLocalServiceImpl#deleteFaroProjectEmailAddressDomain(FaroProjectEmailAddressDomain) to avoid orphaned data");
		}

		return faroProjectEmailAddressDomainLocalService.
			deleteFaroProjectEmailAddressDomain(
				(FaroProjectEmailAddressDomain)persistedModel);
	}

	@Override
	public BasePersistence<FaroProjectEmailAddressDomain> getBasePersistence() {
		return faroProjectEmailAddressDomainPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return faroProjectEmailAddressDomainPersistence.findByPrimaryKey(
			primaryKeyObj);
	}

	/**
	 * Returns a range of all the faro project email address domains.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.faro.model.impl.FaroProjectEmailAddressDomainModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of faro project email address domains
	 * @param end the upper bound of the range of faro project email address domains (not inclusive)
	 * @return the range of faro project email address domains
	 */
	@Override
	public List<FaroProjectEmailAddressDomain>
		getFaroProjectEmailAddressDomains(int start, int end) {

		return faroProjectEmailAddressDomainPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of faro project email address domains.
	 *
	 * @return the number of faro project email address domains
	 */
	@Override
	public int getFaroProjectEmailAddressDomainsCount() {
		return faroProjectEmailAddressDomainPersistence.countAll();
	}

	/**
	 * Updates the faro project email address domain in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FaroProjectEmailAddressDomainLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param faroProjectEmailAddressDomain the faro project email address domain
	 * @return the faro project email address domain that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public FaroProjectEmailAddressDomain updateFaroProjectEmailAddressDomain(
		FaroProjectEmailAddressDomain faroProjectEmailAddressDomain) {

		return faroProjectEmailAddressDomainPersistence.update(
			faroProjectEmailAddressDomain);
	}

	/**
	 * Returns the faro project email address domain local service.
	 *
	 * @return the faro project email address domain local service
	 */
	public FaroProjectEmailAddressDomainLocalService
		getFaroProjectEmailAddressDomainLocalService() {

		return faroProjectEmailAddressDomainLocalService;
	}

	/**
	 * Sets the faro project email address domain local service.
	 *
	 * @param faroProjectEmailAddressDomainLocalService the faro project email address domain local service
	 */
	public void setFaroProjectEmailAddressDomainLocalService(
		FaroProjectEmailAddressDomainLocalService
			faroProjectEmailAddressDomainLocalService) {

		this.faroProjectEmailAddressDomainLocalService =
			faroProjectEmailAddressDomainLocalService;
	}

	/**
	 * Returns the faro project email address domain persistence.
	 *
	 * @return the faro project email address domain persistence
	 */
	public FaroProjectEmailAddressDomainPersistence
		getFaroProjectEmailAddressDomainPersistence() {

		return faroProjectEmailAddressDomainPersistence;
	}

	/**
	 * Sets the faro project email address domain persistence.
	 *
	 * @param faroProjectEmailAddressDomainPersistence the faro project email address domain persistence
	 */
	public void setFaroProjectEmailAddressDomainPersistence(
		FaroProjectEmailAddressDomainPersistence
			faroProjectEmailAddressDomainPersistence) {

		this.faroProjectEmailAddressDomainPersistence =
			faroProjectEmailAddressDomainPersistence;
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

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register(
			"com.liferay.osb.faro.model.FaroProjectEmailAddressDomain",
			faroProjectEmailAddressDomainLocalService);

		_setLocalServiceUtilService(faroProjectEmailAddressDomainLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.osb.faro.model.FaroProjectEmailAddressDomain");

		_setLocalServiceUtilService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return FaroProjectEmailAddressDomainLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return FaroProjectEmailAddressDomain.class;
	}

	protected String getModelClassName() {
		return FaroProjectEmailAddressDomain.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				faroProjectEmailAddressDomainPersistence.getDataSource();

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
		FaroProjectEmailAddressDomainLocalService
			faroProjectEmailAddressDomainLocalService) {

		try {
			Field field =
				FaroProjectEmailAddressDomainLocalServiceUtil.class.
					getDeclaredField("_service");

			field.setAccessible(true);

			field.set(null, faroProjectEmailAddressDomainLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@BeanReference(type = FaroProjectEmailAddressDomainLocalService.class)
	protected FaroProjectEmailAddressDomainLocalService
		faroProjectEmailAddressDomainLocalService;

	@BeanReference(type = FaroProjectEmailAddressDomainPersistence.class)
	protected FaroProjectEmailAddressDomainPersistence
		faroProjectEmailAddressDomainPersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		FaroProjectEmailAddressDomainLocalServiceBaseImpl.class);

	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}