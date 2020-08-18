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

package com.liferay.commerce.model.impl;

import com.liferay.commerce.model.CommerceOrderNote;
import com.liferay.commerce.model.CommerceOrderNoteModel;
import com.liferay.commerce.model.CommerceOrderNoteSoap;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
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
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the CommerceOrderNote service. Represents a row in the &quot;CommerceOrderNote&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommerceOrderNoteModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceOrderNoteImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceOrderNoteImpl
 * @generated
 */
@JSON(strict = true)
public class CommerceOrderNoteModelImpl
	extends BaseModelImpl<CommerceOrderNote> implements CommerceOrderNoteModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce order note model instance should use the <code>CommerceOrderNote</code> interface instead.
	 */
	public static final String TABLE_NAME = "CommerceOrderNote";

	public static final Object[][] TABLE_COLUMNS = {
		{"externalReferenceCode", Types.VARCHAR},
		{"commerceOrderNoteId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"commerceOrderId", Types.BIGINT},
		{"content", Types.VARCHAR}, {"restricted", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("externalReferenceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("commerceOrderNoteId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("commerceOrderId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("content", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("restricted", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CommerceOrderNote (externalReferenceCode VARCHAR(75) null,commerceOrderNoteId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,commerceOrderId LONG,content STRING null,restricted BOOLEAN)";

	public static final String TABLE_SQL_DROP = "drop table CommerceOrderNote";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceOrderNote.createDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CommerceOrderNote.createDate DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean ENTITY_CACHE_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean FINDER_CACHE_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean COLUMN_BITMASK_ENABLED = true;

	public static final long COMMERCEORDERID_COLUMN_BITMASK = 1L;

	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	public static final long EXTERNALREFERENCECODE_COLUMN_BITMASK = 4L;

	public static final long RESTRICTED_COLUMN_BITMASK = 8L;

	public static final long CREATEDATE_COLUMN_BITMASK = 16L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static CommerceOrderNote toModel(CommerceOrderNoteSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		CommerceOrderNote model = new CommerceOrderNoteImpl();

		model.setExternalReferenceCode(soapModel.getExternalReferenceCode());
		model.setCommerceOrderNoteId(soapModel.getCommerceOrderNoteId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCommerceOrderId(soapModel.getCommerceOrderId());
		model.setContent(soapModel.getContent());
		model.setRestricted(soapModel.isRestricted());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static List<CommerceOrderNote> toModels(
		CommerceOrderNoteSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<CommerceOrderNote> models = new ArrayList<CommerceOrderNote>(
			soapModels.length);

		for (CommerceOrderNoteSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.model.CommerceOrderNote"));

	public CommerceOrderNoteModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceOrderNoteId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceOrderNoteId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceOrderNoteId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceOrderNote.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceOrderNote.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceOrderNote, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommerceOrderNote, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceOrderNote, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CommerceOrderNote)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceOrderNote, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceOrderNote, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceOrderNote)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceOrderNote, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceOrderNote, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CommerceOrderNote>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CommerceOrderNote.class.getClassLoader(), CommerceOrderNote.class,
			ModelWrapper.class);

		try {
			Constructor<CommerceOrderNote> constructor =
				(Constructor<CommerceOrderNote>)proxyClass.getConstructor(
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

	private static final Map<String, Function<CommerceOrderNote, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<CommerceOrderNote, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommerceOrderNote, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<CommerceOrderNote, Object>>();
		Map<String, BiConsumer<CommerceOrderNote, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<CommerceOrderNote, ?>>();

		attributeGetterFunctions.put(
			"externalReferenceCode",
			CommerceOrderNote::getExternalReferenceCode);
		attributeSetterBiConsumers.put(
			"externalReferenceCode",
			(BiConsumer<CommerceOrderNote, String>)
				CommerceOrderNote::setExternalReferenceCode);
		attributeGetterFunctions.put(
			"commerceOrderNoteId", CommerceOrderNote::getCommerceOrderNoteId);
		attributeSetterBiConsumers.put(
			"commerceOrderNoteId",
			(BiConsumer<CommerceOrderNote, Long>)
				CommerceOrderNote::setCommerceOrderNoteId);
		attributeGetterFunctions.put("groupId", CommerceOrderNote::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<CommerceOrderNote, Long>)CommerceOrderNote::setGroupId);
		attributeGetterFunctions.put(
			"companyId", CommerceOrderNote::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<CommerceOrderNote, Long>)
				CommerceOrderNote::setCompanyId);
		attributeGetterFunctions.put("userId", CommerceOrderNote::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<CommerceOrderNote, Long>)CommerceOrderNote::setUserId);
		attributeGetterFunctions.put(
			"userName", CommerceOrderNote::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<CommerceOrderNote, String>)
				CommerceOrderNote::setUserName);
		attributeGetterFunctions.put(
			"createDate", CommerceOrderNote::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<CommerceOrderNote, Date>)
				CommerceOrderNote::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", CommerceOrderNote::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<CommerceOrderNote, Date>)
				CommerceOrderNote::setModifiedDate);
		attributeGetterFunctions.put(
			"commerceOrderId", CommerceOrderNote::getCommerceOrderId);
		attributeSetterBiConsumers.put(
			"commerceOrderId",
			(BiConsumer<CommerceOrderNote, Long>)
				CommerceOrderNote::setCommerceOrderId);
		attributeGetterFunctions.put("content", CommerceOrderNote::getContent);
		attributeSetterBiConsumers.put(
			"content",
			(BiConsumer<CommerceOrderNote, String>)
				CommerceOrderNote::setContent);
		attributeGetterFunctions.put(
			"restricted", CommerceOrderNote::getRestricted);
		attributeSetterBiConsumers.put(
			"restricted",
			(BiConsumer<CommerceOrderNote, Boolean>)
				CommerceOrderNote::setRestricted);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
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
		_columnBitmask |= EXTERNALREFERENCECODE_COLUMN_BITMASK;

		if (_originalExternalReferenceCode == null) {
			_originalExternalReferenceCode = _externalReferenceCode;
		}

		_externalReferenceCode = externalReferenceCode;
	}

	public String getOriginalExternalReferenceCode() {
		return GetterUtil.getString(_originalExternalReferenceCode);
	}

	@JSON
	@Override
	public long getCommerceOrderNoteId() {
		return _commerceOrderNoteId;
	}

	@Override
	public void setCommerceOrderNoteId(long commerceOrderNoteId) {
		_commerceOrderNoteId = commerceOrderNoteId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
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
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
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

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public long getCommerceOrderId() {
		return _commerceOrderId;
	}

	@Override
	public void setCommerceOrderId(long commerceOrderId) {
		_columnBitmask |= COMMERCEORDERID_COLUMN_BITMASK;

		if (!_setOriginalCommerceOrderId) {
			_setOriginalCommerceOrderId = true;

			_originalCommerceOrderId = _commerceOrderId;
		}

		_commerceOrderId = commerceOrderId;
	}

	public long getOriginalCommerceOrderId() {
		return _originalCommerceOrderId;
	}

	@JSON
	@Override
	public String getContent() {
		if (_content == null) {
			return "";
		}
		else {
			return _content;
		}
	}

	@Override
	public void setContent(String content) {
		_content = content;
	}

	@JSON
	@Override
	public boolean getRestricted() {
		return _restricted;
	}

	@JSON
	@Override
	public boolean isRestricted() {
		return _restricted;
	}

	@Override
	public void setRestricted(boolean restricted) {
		_columnBitmask |= RESTRICTED_COLUMN_BITMASK;

		if (!_setOriginalRestricted) {
			_setOriginalRestricted = true;

			_originalRestricted = _restricted;
		}

		_restricted = restricted;
	}

	public boolean getOriginalRestricted() {
		return _originalRestricted;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), CommerceOrderNote.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceOrderNote toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceOrderNote>
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
		CommerceOrderNoteImpl commerceOrderNoteImpl =
			new CommerceOrderNoteImpl();

		commerceOrderNoteImpl.setExternalReferenceCode(
			getExternalReferenceCode());
		commerceOrderNoteImpl.setCommerceOrderNoteId(getCommerceOrderNoteId());
		commerceOrderNoteImpl.setGroupId(getGroupId());
		commerceOrderNoteImpl.setCompanyId(getCompanyId());
		commerceOrderNoteImpl.setUserId(getUserId());
		commerceOrderNoteImpl.setUserName(getUserName());
		commerceOrderNoteImpl.setCreateDate(getCreateDate());
		commerceOrderNoteImpl.setModifiedDate(getModifiedDate());
		commerceOrderNoteImpl.setCommerceOrderId(getCommerceOrderId());
		commerceOrderNoteImpl.setContent(getContent());
		commerceOrderNoteImpl.setRestricted(isRestricted());

		commerceOrderNoteImpl.resetOriginalValues();

		return commerceOrderNoteImpl;
	}

	@Override
	public int compareTo(CommerceOrderNote commerceOrderNote) {
		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), commerceOrderNote.getCreateDate());

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

		if (!(object instanceof CommerceOrderNote)) {
			return false;
		}

		CommerceOrderNote commerceOrderNote = (CommerceOrderNote)object;

		long primaryKey = commerceOrderNote.getPrimaryKey();

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
		return ENTITY_CACHE_ENABLED;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		_originalExternalReferenceCode = _externalReferenceCode;

		_originalCompanyId = _companyId;

		_setOriginalCompanyId = false;

		_setModifiedDate = false;
		_originalCommerceOrderId = _commerceOrderId;

		_setOriginalCommerceOrderId = false;

		_originalRestricted = _restricted;

		_setOriginalRestricted = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<CommerceOrderNote> toCacheModel() {
		CommerceOrderNoteCacheModel commerceOrderNoteCacheModel =
			new CommerceOrderNoteCacheModel();

		commerceOrderNoteCacheModel.externalReferenceCode =
			getExternalReferenceCode();

		String externalReferenceCode =
			commerceOrderNoteCacheModel.externalReferenceCode;

		if ((externalReferenceCode != null) &&
			(externalReferenceCode.length() == 0)) {

			commerceOrderNoteCacheModel.externalReferenceCode = null;
		}

		commerceOrderNoteCacheModel.commerceOrderNoteId =
			getCommerceOrderNoteId();

		commerceOrderNoteCacheModel.groupId = getGroupId();

		commerceOrderNoteCacheModel.companyId = getCompanyId();

		commerceOrderNoteCacheModel.userId = getUserId();

		commerceOrderNoteCacheModel.userName = getUserName();

		String userName = commerceOrderNoteCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceOrderNoteCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceOrderNoteCacheModel.createDate = createDate.getTime();
		}
		else {
			commerceOrderNoteCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceOrderNoteCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			commerceOrderNoteCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceOrderNoteCacheModel.commerceOrderId = getCommerceOrderId();

		commerceOrderNoteCacheModel.content = getContent();

		String content = commerceOrderNoteCacheModel.content;

		if ((content != null) && (content.length() == 0)) {
			commerceOrderNoteCacheModel.content = null;
		}

		commerceOrderNoteCacheModel.restricted = isRestricted();

		return commerceOrderNoteCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceOrderNote, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommerceOrderNote, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceOrderNote, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((CommerceOrderNote)this));
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
		Map<String, Function<CommerceOrderNote, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CommerceOrderNote, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceOrderNote, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((CommerceOrderNote)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, CommerceOrderNote>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private String _externalReferenceCode;
	private String _originalExternalReferenceCode;
	private long _commerceOrderNoteId;
	private long _groupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _commerceOrderId;
	private long _originalCommerceOrderId;
	private boolean _setOriginalCommerceOrderId;
	private String _content;
	private boolean _restricted;
	private boolean _originalRestricted;
	private boolean _setOriginalRestricted;
	private long _columnBitmask;
	private CommerceOrderNote _escapedModel;

}