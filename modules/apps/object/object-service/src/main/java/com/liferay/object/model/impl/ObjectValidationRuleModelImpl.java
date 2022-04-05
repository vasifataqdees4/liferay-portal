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

package com.liferay.object.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.object.model.ObjectValidationRule;
import com.liferay.object.model.ObjectValidationRuleModel;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Blob;
import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the ObjectValidationRule service. Represents a row in the &quot;ObjectValidationRule&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>ObjectValidationRuleModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ObjectValidationRuleImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see ObjectValidationRuleImpl
 * @generated
 */
@JSON(strict = true)
public class ObjectValidationRuleModelImpl
	extends BaseModelImpl<ObjectValidationRule>
	implements ObjectValidationRuleModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a object validation rule model instance should use the <code>ObjectValidationRule</code> interface instead.
	 */
	public static final String TABLE_NAME = "ObjectValidationRule";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"objectValidationRuleId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"objectDefinitionId", Types.BIGINT}, {"active_", Types.BOOLEAN},
		{"engine", Types.VARCHAR}, {"errorLabel", Types.VARCHAR},
		{"name", Types.VARCHAR}, {"script", Types.CLOB}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("objectValidationRuleId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("objectDefinitionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("active_", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("engine", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("errorLabel", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("script", Types.CLOB);
	}

	public static final String TABLE_SQL_CREATE =
		"create table ObjectValidationRule (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,objectValidationRuleId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,objectDefinitionId LONG,active_ BOOLEAN,engine VARCHAR(75) null,errorLabel STRING null,name STRING null,script TEXT null)";

	public static final String TABLE_SQL_DROP =
		"drop table ObjectValidationRule";

	public static final String ORDER_BY_JPQL =
		" ORDER BY objectValidationRule.objectValidationRuleId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY ObjectValidationRule.objectValidationRuleId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ACTIVE_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long OBJECTDEFINITIONID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long OBJECTVALIDATIONRULEID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
	}

	public ObjectValidationRuleModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _objectValidationRuleId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setObjectValidationRuleId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _objectValidationRuleId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ObjectValidationRule.class;
	}

	@Override
	public String getModelClassName() {
		return ObjectValidationRule.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<ObjectValidationRule, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<ObjectValidationRule, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ObjectValidationRule, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((ObjectValidationRule)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<ObjectValidationRule, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<ObjectValidationRule, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(ObjectValidationRule)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<ObjectValidationRule, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<ObjectValidationRule, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, ObjectValidationRule>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			ObjectValidationRule.class.getClassLoader(),
			ObjectValidationRule.class, ModelWrapper.class);

		try {
			Constructor<ObjectValidationRule> constructor =
				(Constructor<ObjectValidationRule>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private static final Map<String, Function<ObjectValidationRule, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<ObjectValidationRule, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<ObjectValidationRule, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<ObjectValidationRule, Object>>();
		Map<String, BiConsumer<ObjectValidationRule, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<ObjectValidationRule, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", ObjectValidationRule::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<ObjectValidationRule, Long>)
				ObjectValidationRule::setMvccVersion);
		attributeGetterFunctions.put("uuid", ObjectValidationRule::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<ObjectValidationRule, String>)
				ObjectValidationRule::setUuid);
		attributeGetterFunctions.put(
			"objectValidationRuleId",
			ObjectValidationRule::getObjectValidationRuleId);
		attributeSetterBiConsumers.put(
			"objectValidationRuleId",
			(BiConsumer<ObjectValidationRule, Long>)
				ObjectValidationRule::setObjectValidationRuleId);
		attributeGetterFunctions.put(
			"companyId", ObjectValidationRule::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<ObjectValidationRule, Long>)
				ObjectValidationRule::setCompanyId);
		attributeGetterFunctions.put("userId", ObjectValidationRule::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<ObjectValidationRule, Long>)
				ObjectValidationRule::setUserId);
		attributeGetterFunctions.put(
			"userName", ObjectValidationRule::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<ObjectValidationRule, String>)
				ObjectValidationRule::setUserName);
		attributeGetterFunctions.put(
			"createDate", ObjectValidationRule::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<ObjectValidationRule, Date>)
				ObjectValidationRule::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", ObjectValidationRule::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<ObjectValidationRule, Date>)
				ObjectValidationRule::setModifiedDate);
		attributeGetterFunctions.put(
			"objectDefinitionId", ObjectValidationRule::getObjectDefinitionId);
		attributeSetterBiConsumers.put(
			"objectDefinitionId",
			(BiConsumer<ObjectValidationRule, Long>)
				ObjectValidationRule::setObjectDefinitionId);
		attributeGetterFunctions.put("active", ObjectValidationRule::getActive);
		attributeSetterBiConsumers.put(
			"active",
			(BiConsumer<ObjectValidationRule, Boolean>)
				ObjectValidationRule::setActive);
		attributeGetterFunctions.put("engine", ObjectValidationRule::getEngine);
		attributeSetterBiConsumers.put(
			"engine",
			(BiConsumer<ObjectValidationRule, String>)
				ObjectValidationRule::setEngine);
		attributeGetterFunctions.put(
			"errorLabel", ObjectValidationRule::getErrorLabel);
		attributeSetterBiConsumers.put(
			"errorLabel",
			(BiConsumer<ObjectValidationRule, String>)
				ObjectValidationRule::setErrorLabel);
		attributeGetterFunctions.put("name", ObjectValidationRule::getName);
		attributeSetterBiConsumers.put(
			"name",
			(BiConsumer<ObjectValidationRule, String>)
				ObjectValidationRule::setName);
		attributeGetterFunctions.put("script", ObjectValidationRule::getScript);
		attributeSetterBiConsumers.put(
			"script",
			(BiConsumer<ObjectValidationRule, String>)
				ObjectValidationRule::setScript);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_mvccVersion = mvccVersion;
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_uuid = uuid;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalUuid() {
		return getColumnOriginalValue("uuid_");
	}

	@JSON
	@Override
	public long getObjectValidationRuleId() {
		return _objectValidationRuleId;
	}

	@Override
	public void setObjectValidationRuleId(long objectValidationRuleId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_objectValidationRuleId = objectValidationRuleId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_companyId = companyId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCompanyId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("companyId"));
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public long getObjectDefinitionId() {
		return _objectDefinitionId;
	}

	@Override
	public void setObjectDefinitionId(long objectDefinitionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_objectDefinitionId = objectDefinitionId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalObjectDefinitionId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("objectDefinitionId"));
	}

	@JSON
	@Override
	public boolean getActive() {
		return _active;
	}

	@JSON
	@Override
	public boolean isActive() {
		return _active;
	}

	@Override
	public void setActive(boolean active) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_active = active;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public boolean getOriginalActive() {
		return GetterUtil.getBoolean(
			this.<Boolean>getColumnOriginalValue("active_"));
	}

	@JSON
	@Override
	public String getEngine() {
		if (_engine == null) {
			return "";
		}
		else {
			return _engine;
		}
	}

	@Override
	public void setEngine(String engine) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_engine = engine;
	}

	@JSON
	@Override
	public String getErrorLabel() {
		if (_errorLabel == null) {
			return "";
		}
		else {
			return _errorLabel;
		}
	}

	@Override
	public String getErrorLabel(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getErrorLabel(languageId);
	}

	@Override
	public String getErrorLabel(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getErrorLabel(languageId, useDefault);
	}

	@Override
	public String getErrorLabel(String languageId) {
		return LocalizationUtil.getLocalization(getErrorLabel(), languageId);
	}

	@Override
	public String getErrorLabel(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(
			getErrorLabel(), languageId, useDefault);
	}

	@Override
	public String getErrorLabelCurrentLanguageId() {
		return _errorLabelCurrentLanguageId;
	}

	@JSON
	@Override
	public String getErrorLabelCurrentValue() {
		Locale locale = getLocale(_errorLabelCurrentLanguageId);

		return getErrorLabel(locale);
	}

	@Override
	public Map<Locale, String> getErrorLabelMap() {
		return LocalizationUtil.getLocalizationMap(getErrorLabel());
	}

	@Override
	public void setErrorLabel(String errorLabel) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_errorLabel = errorLabel;
	}

	@Override
	public void setErrorLabel(String errorLabel, Locale locale) {
		setErrorLabel(errorLabel, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setErrorLabel(
		String errorLabel, Locale locale, Locale defaultLocale) {

		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(errorLabel)) {
			setErrorLabel(
				LocalizationUtil.updateLocalization(
					getErrorLabel(), "ErrorLabel", errorLabel, languageId,
					defaultLanguageId));
		}
		else {
			setErrorLabel(
				LocalizationUtil.removeLocalization(
					getErrorLabel(), "ErrorLabel", languageId));
		}
	}

	@Override
	public void setErrorLabelCurrentLanguageId(String languageId) {
		_errorLabelCurrentLanguageId = languageId;
	}

	@Override
	public void setErrorLabelMap(Map<Locale, String> errorLabelMap) {
		setErrorLabelMap(errorLabelMap, LocaleUtil.getDefault());
	}

	@Override
	public void setErrorLabelMap(
		Map<Locale, String> errorLabelMap, Locale defaultLocale) {

		if (errorLabelMap == null) {
			return;
		}

		setErrorLabel(
			LocalizationUtil.updateLocalization(
				errorLabelMap, getErrorLabel(), "ErrorLabel",
				LocaleUtil.toLanguageId(defaultLocale)));
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public String getName(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId);
	}

	@Override
	public String getName(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId, useDefault);
	}

	@Override
	public String getName(String languageId) {
		return LocalizationUtil.getLocalization(getName(), languageId);
	}

	@Override
	public String getName(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(
			getName(), languageId, useDefault);
	}

	@Override
	public String getNameCurrentLanguageId() {
		return _nameCurrentLanguageId;
	}

	@JSON
	@Override
	public String getNameCurrentValue() {
		Locale locale = getLocale(_nameCurrentLanguageId);

		return getName(locale);
	}

	@Override
	public Map<Locale, String> getNameMap() {
		return LocalizationUtil.getLocalizationMap(getName());
	}

	@Override
	public void setName(String name) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_name = name;
	}

	@Override
	public void setName(String name, Locale locale) {
		setName(name, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setName(String name, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(name)) {
			setName(
				LocalizationUtil.updateLocalization(
					getName(), "Name", name, languageId, defaultLanguageId));
		}
		else {
			setName(
				LocalizationUtil.removeLocalization(
					getName(), "Name", languageId));
		}
	}

	@Override
	public void setNameCurrentLanguageId(String languageId) {
		_nameCurrentLanguageId = languageId;
	}

	@Override
	public void setNameMap(Map<Locale, String> nameMap) {
		setNameMap(nameMap, LocaleUtil.getDefault());
	}

	@Override
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale) {
		if (nameMap == null) {
			return;
		}

		setName(
			LocalizationUtil.updateLocalization(
				nameMap, getName(), "Name",
				LocaleUtil.toLanguageId(defaultLocale)));
	}

	@JSON
	@Override
	public String getScript() {
		if (_script == null) {
			return "";
		}
		else {
			return _script;
		}
	}

	@Override
	public void setScript(String script) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_script = script;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(ObjectValidationRule.class.getName()));
	}

	public long getColumnBitmask() {
		if (_columnBitmask > 0) {
			return _columnBitmask;
		}

		if ((_columnOriginalValues == null) ||
			(_columnOriginalValues == Collections.EMPTY_MAP)) {

			return 0;
		}

		for (Map.Entry<String, Object> entry :
				_columnOriginalValues.entrySet()) {

			if (!Objects.equals(
					entry.getValue(), getColumnValue(entry.getKey()))) {

				_columnBitmask |= _columnBitmasks.get(entry.getKey());
			}
		}

		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), ObjectValidationRule.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> errorLabelMap = getErrorLabelMap();

		for (Map.Entry<Locale, String> entry : errorLabelMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> nameMap = getNameMap();

		for (Map.Entry<Locale, String> entry : nameMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		return availableLanguageIds.toArray(
			new String[availableLanguageIds.size()]);
	}

	@Override
	public String getDefaultLanguageId() {
		String xml = getErrorLabel();

		if (xml == null) {
			return "";
		}

		Locale defaultLocale = LocaleUtil.getDefault();

		return LocalizationUtil.getDefaultLanguageId(xml, defaultLocale);
	}

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException {
		Locale defaultLocale = LocaleUtil.fromLanguageId(
			getDefaultLanguageId());

		Locale[] availableLocales = LocaleUtil.fromLanguageIds(
			getAvailableLanguageIds());

		Locale defaultImportLocale = LocalizationUtil.getDefaultImportLocale(
			ObjectValidationRule.class.getName(), getPrimaryKey(),
			defaultLocale, availableLocales);

		prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {

		Locale defaultLocale = LocaleUtil.getDefault();

		String modelDefaultLanguageId = getDefaultLanguageId();

		String errorLabel = getErrorLabel(defaultLocale);

		if (Validator.isNull(errorLabel)) {
			setErrorLabel(getErrorLabel(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setErrorLabel(
				getErrorLabel(defaultLocale), defaultLocale, defaultLocale);
		}

		String name = getName(defaultLocale);

		if (Validator.isNull(name)) {
			setName(getName(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setName(getName(defaultLocale), defaultLocale, defaultLocale);
		}
	}

	@Override
	public ObjectValidationRule toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, ObjectValidationRule>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ObjectValidationRuleImpl objectValidationRuleImpl =
			new ObjectValidationRuleImpl();

		objectValidationRuleImpl.setMvccVersion(getMvccVersion());
		objectValidationRuleImpl.setUuid(getUuid());
		objectValidationRuleImpl.setObjectValidationRuleId(
			getObjectValidationRuleId());
		objectValidationRuleImpl.setCompanyId(getCompanyId());
		objectValidationRuleImpl.setUserId(getUserId());
		objectValidationRuleImpl.setUserName(getUserName());
		objectValidationRuleImpl.setCreateDate(getCreateDate());
		objectValidationRuleImpl.setModifiedDate(getModifiedDate());
		objectValidationRuleImpl.setObjectDefinitionId(getObjectDefinitionId());
		objectValidationRuleImpl.setActive(isActive());
		objectValidationRuleImpl.setEngine(getEngine());
		objectValidationRuleImpl.setErrorLabel(getErrorLabel());
		objectValidationRuleImpl.setName(getName());
		objectValidationRuleImpl.setScript(getScript());

		objectValidationRuleImpl.resetOriginalValues();

		return objectValidationRuleImpl;
	}

	@Override
	public ObjectValidationRule cloneWithOriginalValues() {
		ObjectValidationRuleImpl objectValidationRuleImpl =
			new ObjectValidationRuleImpl();

		objectValidationRuleImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		objectValidationRuleImpl.setUuid(
			this.<String>getColumnOriginalValue("uuid_"));
		objectValidationRuleImpl.setObjectValidationRuleId(
			this.<Long>getColumnOriginalValue("objectValidationRuleId"));
		objectValidationRuleImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		objectValidationRuleImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		objectValidationRuleImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		objectValidationRuleImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		objectValidationRuleImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		objectValidationRuleImpl.setObjectDefinitionId(
			this.<Long>getColumnOriginalValue("objectDefinitionId"));
		objectValidationRuleImpl.setActive(
			this.<Boolean>getColumnOriginalValue("active_"));
		objectValidationRuleImpl.setEngine(
			this.<String>getColumnOriginalValue("engine"));
		objectValidationRuleImpl.setErrorLabel(
			this.<String>getColumnOriginalValue("errorLabel"));
		objectValidationRuleImpl.setName(
			this.<String>getColumnOriginalValue("name"));
		objectValidationRuleImpl.setScript(
			this.<String>getColumnOriginalValue("script"));

		return objectValidationRuleImpl;
	}

	@Override
	public int compareTo(ObjectValidationRule objectValidationRule) {
		long primaryKey = objectValidationRule.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ObjectValidationRule)) {
			return false;
		}

		ObjectValidationRule objectValidationRule =
			(ObjectValidationRule)object;

		long primaryKey = objectValidationRule.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isEntityCacheEnabled() {
		return true;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return true;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_setModifiedDate = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<ObjectValidationRule> toCacheModel() {
		ObjectValidationRuleCacheModel objectValidationRuleCacheModel =
			new ObjectValidationRuleCacheModel();

		objectValidationRuleCacheModel.mvccVersion = getMvccVersion();

		objectValidationRuleCacheModel.uuid = getUuid();

		String uuid = objectValidationRuleCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			objectValidationRuleCacheModel.uuid = null;
		}

		objectValidationRuleCacheModel.objectValidationRuleId =
			getObjectValidationRuleId();

		objectValidationRuleCacheModel.companyId = getCompanyId();

		objectValidationRuleCacheModel.userId = getUserId();

		objectValidationRuleCacheModel.userName = getUserName();

		String userName = objectValidationRuleCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			objectValidationRuleCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			objectValidationRuleCacheModel.createDate = createDate.getTime();
		}
		else {
			objectValidationRuleCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			objectValidationRuleCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			objectValidationRuleCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		objectValidationRuleCacheModel.objectDefinitionId =
			getObjectDefinitionId();

		objectValidationRuleCacheModel.active = isActive();

		objectValidationRuleCacheModel.engine = getEngine();

		String engine = objectValidationRuleCacheModel.engine;

		if ((engine != null) && (engine.length() == 0)) {
			objectValidationRuleCacheModel.engine = null;
		}

		objectValidationRuleCacheModel.errorLabel = getErrorLabel();

		String errorLabel = objectValidationRuleCacheModel.errorLabel;

		if ((errorLabel != null) && (errorLabel.length() == 0)) {
			objectValidationRuleCacheModel.errorLabel = null;
		}

		objectValidationRuleCacheModel.name = getName();

		String name = objectValidationRuleCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			objectValidationRuleCacheModel.name = null;
		}

		objectValidationRuleCacheModel.script = getScript();

		String script = objectValidationRuleCacheModel.script;

		if ((script != null) && (script.length() == 0)) {
			objectValidationRuleCacheModel.script = null;
		}

		return objectValidationRuleCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<ObjectValidationRule, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<ObjectValidationRule, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ObjectValidationRule, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(ObjectValidationRule)this);

			if (value == null) {
				sb.append("null");
			}
			else if (value instanceof Blob || value instanceof Date ||
					 value instanceof Map || value instanceof String) {

				sb.append(
					"\"" + StringUtil.replace(value.toString(), "\"", "'") +
						"\"");
			}
			else {
				sb.append(value);
			}

			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<ObjectValidationRule, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<ObjectValidationRule, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ObjectValidationRule, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply((ObjectValidationRule)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, ObjectValidationRule>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private String _uuid;
	private long _objectValidationRuleId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _objectDefinitionId;
	private boolean _active;
	private String _engine;
	private String _errorLabel;
	private String _errorLabelCurrentLanguageId;
	private String _name;
	private String _nameCurrentLanguageId;
	private String _script;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<ObjectValidationRule, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((ObjectValidationRule)this);
	}

	public <T> T getColumnOriginalValue(String columnName) {
		if (_columnOriginalValues == null) {
			return null;
		}

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		return (T)_columnOriginalValues.get(columnName);
	}

	private void _setColumnOriginalValues() {
		_columnOriginalValues = new HashMap<String, Object>();

		_columnOriginalValues.put("mvccVersion", _mvccVersion);
		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put(
			"objectValidationRuleId", _objectValidationRuleId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("objectDefinitionId", _objectDefinitionId);
		_columnOriginalValues.put("active_", _active);
		_columnOriginalValues.put("engine", _engine);
		_columnOriginalValues.put("errorLabel", _errorLabel);
		_columnOriginalValues.put("name", _name);
		_columnOriginalValues.put("script", _script);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");
		attributeNames.put("active_", "active");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("uuid_", 2L);

		columnBitmasks.put("objectValidationRuleId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("userId", 16L);

		columnBitmasks.put("userName", 32L);

		columnBitmasks.put("createDate", 64L);

		columnBitmasks.put("modifiedDate", 128L);

		columnBitmasks.put("objectDefinitionId", 256L);

		columnBitmasks.put("active_", 512L);

		columnBitmasks.put("engine", 1024L);

		columnBitmasks.put("errorLabel", 2048L);

		columnBitmasks.put("name", 4096L);

		columnBitmasks.put("script", 8192L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private ObjectValidationRule _escapedModel;

}