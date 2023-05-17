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

package com.liferay.commerce.product.model.impl;

import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.model.CommerceChannelModel;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.sql.Blob;
import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the CommerceChannel service. Represents a row in the &quot;CommerceChannel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommerceChannelModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceChannelImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceChannelImpl
 * @generated
 */
@JSON(strict = true)
public class CommerceChannelModelImpl
	extends BaseModelImpl<CommerceChannel> implements CommerceChannelModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce channel model instance should use the <code>CommerceChannel</code> interface instead.
	 */
	public static final String TABLE_NAME = "CommerceChannel";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"uuid_", Types.VARCHAR}, {"externalReferenceCode", Types.VARCHAR},
		{"commerceChannelId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"accountEntryId", Types.BIGINT}, {"siteGroupId", Types.BIGINT},
		{"name", Types.VARCHAR}, {"type_", Types.VARCHAR},
		{"typeSettings", Types.VARCHAR},
		{"commerceCurrencyCode", Types.VARCHAR},
		{"priceDisplayType", Types.VARCHAR},
		{"discountsTargetNetPrice", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("externalReferenceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("commerceChannelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("accountEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("siteGroupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("typeSettings", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("commerceCurrencyCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("priceDisplayType", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("discountsTargetNetPrice", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CommerceChannel (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,uuid_ VARCHAR(75) null,externalReferenceCode VARCHAR(75) null,commerceChannelId LONG not null,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,accountEntryId LONG,siteGroupId LONG,name VARCHAR(75) null,type_ VARCHAR(75) null,typeSettings VARCHAR(75) null,commerceCurrencyCode VARCHAR(75) null,priceDisplayType VARCHAR(75) null,discountsTargetNetPrice BOOLEAN,primary key (commerceChannelId, ctCollectionId))";

	public static final String TABLE_SQL_DROP = "drop table CommerceChannel";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceChannel.createDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CommerceChannel.createDate DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long EXTERNALREFERENCECODE_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SITEGROUPID_COLUMN_BITMASK = 4L;

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
	public static final long CREATEDATE_COLUMN_BITMASK = 16L;

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

	public CommerceChannelModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceChannelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceChannelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceChannelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceChannel.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceChannel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceChannel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommerceChannel, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceChannel, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CommerceChannel)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceChannel, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceChannel, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceChannel)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceChannel, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceChannel, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<CommerceChannel, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<CommerceChannel, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<CommerceChannel, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", CommerceChannel::getMvccVersion);
			attributeGetterFunctions.put(
				"ctCollectionId", CommerceChannel::getCtCollectionId);
			attributeGetterFunctions.put("uuid", CommerceChannel::getUuid);
			attributeGetterFunctions.put(
				"externalReferenceCode",
				CommerceChannel::getExternalReferenceCode);
			attributeGetterFunctions.put(
				"commerceChannelId", CommerceChannel::getCommerceChannelId);
			attributeGetterFunctions.put(
				"companyId", CommerceChannel::getCompanyId);
			attributeGetterFunctions.put("userId", CommerceChannel::getUserId);
			attributeGetterFunctions.put(
				"userName", CommerceChannel::getUserName);
			attributeGetterFunctions.put(
				"createDate", CommerceChannel::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", CommerceChannel::getModifiedDate);
			attributeGetterFunctions.put(
				"accountEntryId", CommerceChannel::getAccountEntryId);
			attributeGetterFunctions.put(
				"siteGroupId", CommerceChannel::getSiteGroupId);
			attributeGetterFunctions.put("name", CommerceChannel::getName);
			attributeGetterFunctions.put("type", CommerceChannel::getType);
			attributeGetterFunctions.put(
				"typeSettings", CommerceChannel::getTypeSettings);
			attributeGetterFunctions.put(
				"commerceCurrencyCode",
				CommerceChannel::getCommerceCurrencyCode);
			attributeGetterFunctions.put(
				"priceDisplayType", CommerceChannel::getPriceDisplayType);
			attributeGetterFunctions.put(
				"discountsTargetNetPrice",
				CommerceChannel::getDiscountsTargetNetPrice);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map<String, BiConsumer<CommerceChannel, Object>>
			_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<CommerceChannel, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap<String, BiConsumer<CommerceChannel, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<CommerceChannel, Long>)
					CommerceChannel::setMvccVersion);
			attributeSetterBiConsumers.put(
				"ctCollectionId",
				(BiConsumer<CommerceChannel, Long>)
					CommerceChannel::setCtCollectionId);
			attributeSetterBiConsumers.put(
				"uuid",
				(BiConsumer<CommerceChannel, String>)CommerceChannel::setUuid);
			attributeSetterBiConsumers.put(
				"externalReferenceCode",
				(BiConsumer<CommerceChannel, String>)
					CommerceChannel::setExternalReferenceCode);
			attributeSetterBiConsumers.put(
				"commerceChannelId",
				(BiConsumer<CommerceChannel, Long>)
					CommerceChannel::setCommerceChannelId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<CommerceChannel, Long>)
					CommerceChannel::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId",
				(BiConsumer<CommerceChannel, Long>)CommerceChannel::setUserId);
			attributeSetterBiConsumers.put(
				"userName",
				(BiConsumer<CommerceChannel, String>)
					CommerceChannel::setUserName);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<CommerceChannel, Date>)
					CommerceChannel::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<CommerceChannel, Date>)
					CommerceChannel::setModifiedDate);
			attributeSetterBiConsumers.put(
				"accountEntryId",
				(BiConsumer<CommerceChannel, Long>)
					CommerceChannel::setAccountEntryId);
			attributeSetterBiConsumers.put(
				"siteGroupId",
				(BiConsumer<CommerceChannel, Long>)
					CommerceChannel::setSiteGroupId);
			attributeSetterBiConsumers.put(
				"name",
				(BiConsumer<CommerceChannel, String>)CommerceChannel::setName);
			attributeSetterBiConsumers.put(
				"type",
				(BiConsumer<CommerceChannel, String>)CommerceChannel::setType);
			attributeSetterBiConsumers.put(
				"typeSettings",
				(BiConsumer<CommerceChannel, String>)
					CommerceChannel::setTypeSettings);
			attributeSetterBiConsumers.put(
				"commerceCurrencyCode",
				(BiConsumer<CommerceChannel, String>)
					CommerceChannel::setCommerceCurrencyCode);
			attributeSetterBiConsumers.put(
				"priceDisplayType",
				(BiConsumer<CommerceChannel, String>)
					CommerceChannel::setPriceDisplayType);
			attributeSetterBiConsumers.put(
				"discountsTargetNetPrice",
				(BiConsumer<CommerceChannel, Boolean>)
					CommerceChannel::setDiscountsTargetNetPrice);

			_attributeSetterBiConsumers = Collections.unmodifiableMap(
				(Map)attributeSetterBiConsumers);
		}

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
	public long getCtCollectionId() {
		return _ctCollectionId;
	}

	@Override
	public void setCtCollectionId(long ctCollectionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_ctCollectionId = ctCollectionId;
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
	public String getExternalReferenceCode() {
		if (_externalReferenceCode == null) {
			return "";
		}
		else {
			return _externalReferenceCode;
		}
	}

	@Override
	public void setExternalReferenceCode(String externalReferenceCode) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_externalReferenceCode = externalReferenceCode;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalExternalReferenceCode() {
		return getColumnOriginalValue("externalReferenceCode");
	}

	@JSON
	@Override
	public long getCommerceChannelId() {
		return _commerceChannelId;
	}

	@Override
	public void setCommerceChannelId(long commerceChannelId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceChannelId = commerceChannelId;
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
	public long getAccountEntryId() {
		return _accountEntryId;
	}

	@Override
	public void setAccountEntryId(long accountEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_accountEntryId = accountEntryId;
	}

	@JSON
	@Override
	public long getSiteGroupId() {
		return _siteGroupId;
	}

	@Override
	public void setSiteGroupId(long siteGroupId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_siteGroupId = siteGroupId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalSiteGroupId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("siteGroupId"));
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
	public void setName(String name) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_name = name;
	}

	@JSON
	@Override
	public String getType() {
		if (_type == null) {
			return "";
		}
		else {
			return _type;
		}
	}

	@Override
	public void setType(String type) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_type = type;
	}

	@JSON
	@Override
	public String getTypeSettings() {
		if (_typeSettings == null) {
			return "";
		}
		else {
			return _typeSettings;
		}
	}

	@Override
	public void setTypeSettings(String typeSettings) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_typeSettings = typeSettings;
	}

	@JSON
	@Override
	public String getCommerceCurrencyCode() {
		if (_commerceCurrencyCode == null) {
			return "";
		}
		else {
			return _commerceCurrencyCode;
		}
	}

	@Override
	public void setCommerceCurrencyCode(String commerceCurrencyCode) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceCurrencyCode = commerceCurrencyCode;
	}

	@JSON
	@Override
	public String getPriceDisplayType() {
		if (_priceDisplayType == null) {
			return "";
		}
		else {
			return _priceDisplayType;
		}
	}

	@Override
	public void setPriceDisplayType(String priceDisplayType) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_priceDisplayType = priceDisplayType;
	}

	@JSON
	@Override
	public boolean getDiscountsTargetNetPrice() {
		return _discountsTargetNetPrice;
	}

	@JSON
	@Override
	public boolean isDiscountsTargetNetPrice() {
		return _discountsTargetNetPrice;
	}

	@Override
	public void setDiscountsTargetNetPrice(boolean discountsTargetNetPrice) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_discountsTargetNetPrice = discountsTargetNetPrice;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(CommerceChannel.class.getName()));
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
			getCompanyId(), CommerceChannel.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceChannel toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceChannel>
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
		CommerceChannelImpl commerceChannelImpl = new CommerceChannelImpl();

		commerceChannelImpl.setMvccVersion(getMvccVersion());
		commerceChannelImpl.setCtCollectionId(getCtCollectionId());
		commerceChannelImpl.setUuid(getUuid());
		commerceChannelImpl.setExternalReferenceCode(
			getExternalReferenceCode());
		commerceChannelImpl.setCommerceChannelId(getCommerceChannelId());
		commerceChannelImpl.setCompanyId(getCompanyId());
		commerceChannelImpl.setUserId(getUserId());
		commerceChannelImpl.setUserName(getUserName());
		commerceChannelImpl.setCreateDate(getCreateDate());
		commerceChannelImpl.setModifiedDate(getModifiedDate());
		commerceChannelImpl.setAccountEntryId(getAccountEntryId());
		commerceChannelImpl.setSiteGroupId(getSiteGroupId());
		commerceChannelImpl.setName(getName());
		commerceChannelImpl.setType(getType());
		commerceChannelImpl.setTypeSettings(getTypeSettings());
		commerceChannelImpl.setCommerceCurrencyCode(getCommerceCurrencyCode());
		commerceChannelImpl.setPriceDisplayType(getPriceDisplayType());
		commerceChannelImpl.setDiscountsTargetNetPrice(
			isDiscountsTargetNetPrice());

		commerceChannelImpl.resetOriginalValues();

		return commerceChannelImpl;
	}

	@Override
	public CommerceChannel cloneWithOriginalValues() {
		CommerceChannelImpl commerceChannelImpl = new CommerceChannelImpl();

		commerceChannelImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		commerceChannelImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		commerceChannelImpl.setUuid(
			this.<String>getColumnOriginalValue("uuid_"));
		commerceChannelImpl.setExternalReferenceCode(
			this.<String>getColumnOriginalValue("externalReferenceCode"));
		commerceChannelImpl.setCommerceChannelId(
			this.<Long>getColumnOriginalValue("commerceChannelId"));
		commerceChannelImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		commerceChannelImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		commerceChannelImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		commerceChannelImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		commerceChannelImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		commerceChannelImpl.setAccountEntryId(
			this.<Long>getColumnOriginalValue("accountEntryId"));
		commerceChannelImpl.setSiteGroupId(
			this.<Long>getColumnOriginalValue("siteGroupId"));
		commerceChannelImpl.setName(
			this.<String>getColumnOriginalValue("name"));
		commerceChannelImpl.setType(
			this.<String>getColumnOriginalValue("type_"));
		commerceChannelImpl.setTypeSettings(
			this.<String>getColumnOriginalValue("typeSettings"));
		commerceChannelImpl.setCommerceCurrencyCode(
			this.<String>getColumnOriginalValue("commerceCurrencyCode"));
		commerceChannelImpl.setPriceDisplayType(
			this.<String>getColumnOriginalValue("priceDisplayType"));
		commerceChannelImpl.setDiscountsTargetNetPrice(
			this.<Boolean>getColumnOriginalValue("discountsTargetNetPrice"));

		return commerceChannelImpl;
	}

	@Override
	public int compareTo(CommerceChannel commerceChannel) {
		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), commerceChannel.getCreateDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CommerceChannel)) {
			return false;
		}

		CommerceChannel commerceChannel = (CommerceChannel)object;

		long primaryKey = commerceChannel.getPrimaryKey();

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
	public CacheModel<CommerceChannel> toCacheModel() {
		CommerceChannelCacheModel commerceChannelCacheModel =
			new CommerceChannelCacheModel();

		commerceChannelCacheModel.mvccVersion = getMvccVersion();

		commerceChannelCacheModel.ctCollectionId = getCtCollectionId();

		commerceChannelCacheModel.uuid = getUuid();

		String uuid = commerceChannelCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			commerceChannelCacheModel.uuid = null;
		}

		commerceChannelCacheModel.externalReferenceCode =
			getExternalReferenceCode();

		String externalReferenceCode =
			commerceChannelCacheModel.externalReferenceCode;

		if ((externalReferenceCode != null) &&
			(externalReferenceCode.length() == 0)) {

			commerceChannelCacheModel.externalReferenceCode = null;
		}

		commerceChannelCacheModel.commerceChannelId = getCommerceChannelId();

		commerceChannelCacheModel.companyId = getCompanyId();

		commerceChannelCacheModel.userId = getUserId();

		commerceChannelCacheModel.userName = getUserName();

		String userName = commerceChannelCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceChannelCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceChannelCacheModel.createDate = createDate.getTime();
		}
		else {
			commerceChannelCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceChannelCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			commerceChannelCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceChannelCacheModel.accountEntryId = getAccountEntryId();

		commerceChannelCacheModel.siteGroupId = getSiteGroupId();

		commerceChannelCacheModel.name = getName();

		String name = commerceChannelCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			commerceChannelCacheModel.name = null;
		}

		commerceChannelCacheModel.type = getType();

		String type = commerceChannelCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			commerceChannelCacheModel.type = null;
		}

		commerceChannelCacheModel.typeSettings = getTypeSettings();

		String typeSettings = commerceChannelCacheModel.typeSettings;

		if ((typeSettings != null) && (typeSettings.length() == 0)) {
			commerceChannelCacheModel.typeSettings = null;
		}

		commerceChannelCacheModel.commerceCurrencyCode =
			getCommerceCurrencyCode();

		String commerceCurrencyCode =
			commerceChannelCacheModel.commerceCurrencyCode;

		if ((commerceCurrencyCode != null) &&
			(commerceCurrencyCode.length() == 0)) {

			commerceChannelCacheModel.commerceCurrencyCode = null;
		}

		commerceChannelCacheModel.priceDisplayType = getPriceDisplayType();

		String priceDisplayType = commerceChannelCacheModel.priceDisplayType;

		if ((priceDisplayType != null) && (priceDisplayType.length() == 0)) {
			commerceChannelCacheModel.priceDisplayType = null;
		}

		commerceChannelCacheModel.discountsTargetNetPrice =
			isDiscountsTargetNetPrice();

		return commerceChannelCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceChannel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommerceChannel, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceChannel, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((CommerceChannel)this);

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

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, CommerceChannel>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					CommerceChannel.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private String _uuid;
	private String _externalReferenceCode;
	private long _commerceChannelId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _accountEntryId;
	private long _siteGroupId;
	private String _name;
	private String _type;
	private String _typeSettings;
	private String _commerceCurrencyCode;
	private String _priceDisplayType;
	private boolean _discountsTargetNetPrice;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<CommerceChannel, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CommerceChannel)this);
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
		_columnOriginalValues.put("ctCollectionId", _ctCollectionId);
		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put(
			"externalReferenceCode", _externalReferenceCode);
		_columnOriginalValues.put("commerceChannelId", _commerceChannelId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("accountEntryId", _accountEntryId);
		_columnOriginalValues.put("siteGroupId", _siteGroupId);
		_columnOriginalValues.put("name", _name);
		_columnOriginalValues.put("type_", _type);
		_columnOriginalValues.put("typeSettings", _typeSettings);
		_columnOriginalValues.put(
			"commerceCurrencyCode", _commerceCurrencyCode);
		_columnOriginalValues.put("priceDisplayType", _priceDisplayType);
		_columnOriginalValues.put(
			"discountsTargetNetPrice", _discountsTargetNetPrice);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");
		attributeNames.put("type_", "type");

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

		columnBitmasks.put("ctCollectionId", 2L);

		columnBitmasks.put("uuid_", 4L);

		columnBitmasks.put("externalReferenceCode", 8L);

		columnBitmasks.put("commerceChannelId", 16L);

		columnBitmasks.put("companyId", 32L);

		columnBitmasks.put("userId", 64L);

		columnBitmasks.put("userName", 128L);

		columnBitmasks.put("createDate", 256L);

		columnBitmasks.put("modifiedDate", 512L);

		columnBitmasks.put("accountEntryId", 1024L);

		columnBitmasks.put("siteGroupId", 2048L);

		columnBitmasks.put("name", 4096L);

		columnBitmasks.put("type_", 8192L);

		columnBitmasks.put("typeSettings", 16384L);

		columnBitmasks.put("commerceCurrencyCode", 32768L);

		columnBitmasks.put("priceDisplayType", 65536L);

		columnBitmasks.put("discountsTargetNetPrice", 131072L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CommerceChannel _escapedModel;

}