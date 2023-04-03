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

package com.liferay.osb.faro.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.osb.faro.model.FaroNotification;
import com.liferay.osb.faro.model.FaroNotificationModel;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
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
 * The base model implementation for the FaroNotification service. Represents a row in the &quot;OSBFaro_FaroNotification&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>FaroNotificationModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link FaroNotificationImpl}.
 * </p>
 *
 * @author Matthew Kong
 * @see FaroNotificationImpl
 * @generated
 */
public class FaroNotificationModelImpl
	extends BaseModelImpl<FaroNotification> implements FaroNotificationModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a faro notification model instance should use the <code>FaroNotification</code> interface instead.
	 */
	public static final String TABLE_NAME = "OSBFaro_FaroNotification";

	public static final Object[][] TABLE_COLUMNS = {
		{"faroNotificationId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"createTime", Types.BIGINT},
		{"modifiedTime", Types.BIGINT}, {"ownerId", Types.BIGINT},
		{"scope", Types.VARCHAR}, {"read_", Types.BOOLEAN},
		{"type_", Types.VARCHAR}, {"subtype", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("faroNotificationId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createTime", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("modifiedTime", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ownerId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("scope", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("read_", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("subtype", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table OSBFaro_FaroNotification (faroNotificationId LONG not null primary key,groupId LONG,userId LONG,createTime LONG,modifiedTime LONG,ownerId LONG,scope VARCHAR(75) null,read_ BOOLEAN,type_ VARCHAR(75) null,subtype VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP =
		"drop table OSBFaro_FaroNotification";

	public static final String ORDER_BY_JPQL =
		" ORDER BY faroNotification.faroNotificationId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY OSBFaro_FaroNotification.faroNotificationId ASC";

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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CREATETIME_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long OWNERID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long READ_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SUBTYPE_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long TYPE_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long FARONOTIFICATIONID_COLUMN_BITMASK = 64L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.osb.faro.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.osb.faro.model.FaroNotification"));

	public FaroNotificationModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _faroNotificationId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setFaroNotificationId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _faroNotificationId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return FaroNotification.class;
	}

	@Override
	public String getModelClassName() {
		return FaroNotification.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<FaroNotification, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<FaroNotification, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<FaroNotification, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((FaroNotification)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<FaroNotification, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<FaroNotification, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(FaroNotification)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<FaroNotification, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<FaroNotification, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<FaroNotification, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<FaroNotification, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<FaroNotification, Object>>();

			attributeGetterFunctions.put(
				"faroNotificationId", FaroNotification::getFaroNotificationId);
			attributeGetterFunctions.put(
				"groupId", FaroNotification::getGroupId);
			attributeGetterFunctions.put("userId", FaroNotification::getUserId);
			attributeGetterFunctions.put(
				"createTime", FaroNotification::getCreateTime);
			attributeGetterFunctions.put(
				"modifiedTime", FaroNotification::getModifiedTime);
			attributeGetterFunctions.put(
				"ownerId", FaroNotification::getOwnerId);
			attributeGetterFunctions.put("scope", FaroNotification::getScope);
			attributeGetterFunctions.put("read", FaroNotification::getRead);
			attributeGetterFunctions.put("type", FaroNotification::getType);
			attributeGetterFunctions.put(
				"subtype", FaroNotification::getSubtype);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map<String, BiConsumer<FaroNotification, Object>>
			_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<FaroNotification, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<FaroNotification, ?>>();

			attributeSetterBiConsumers.put(
				"faroNotificationId",
				(BiConsumer<FaroNotification, Long>)
					FaroNotification::setFaroNotificationId);
			attributeSetterBiConsumers.put(
				"groupId",
				(BiConsumer<FaroNotification, Long>)
					FaroNotification::setGroupId);
			attributeSetterBiConsumers.put(
				"userId",
				(BiConsumer<FaroNotification, Long>)
					FaroNotification::setUserId);
			attributeSetterBiConsumers.put(
				"createTime",
				(BiConsumer<FaroNotification, Long>)
					FaroNotification::setCreateTime);
			attributeSetterBiConsumers.put(
				"modifiedTime",
				(BiConsumer<FaroNotification, Long>)
					FaroNotification::setModifiedTime);
			attributeSetterBiConsumers.put(
				"ownerId",
				(BiConsumer<FaroNotification, Long>)
					FaroNotification::setOwnerId);
			attributeSetterBiConsumers.put(
				"scope",
				(BiConsumer<FaroNotification, String>)
					FaroNotification::setScope);
			attributeSetterBiConsumers.put(
				"read",
				(BiConsumer<FaroNotification, Boolean>)
					FaroNotification::setRead);
			attributeSetterBiConsumers.put(
				"type",
				(BiConsumer<FaroNotification, String>)
					FaroNotification::setType);
			attributeSetterBiConsumers.put(
				"subtype",
				(BiConsumer<FaroNotification, String>)
					FaroNotification::setSubtype);

			_attributeSetterBiConsumers = Collections.unmodifiableMap(
				(Map)attributeSetterBiConsumers);
		}

	}

	@Override
	public long getFaroNotificationId() {
		return _faroNotificationId;
	}

	@Override
	public void setFaroNotificationId(long faroNotificationId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_faroNotificationId = faroNotificationId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_groupId = groupId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalGroupId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("groupId"));
	}

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

	@Override
	public long getCreateTime() {
		return _createTime;
	}

	@Override
	public void setCreateTime(long createTime) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_createTime = createTime;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCreateTime() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("createTime"));
	}

	@Override
	public long getModifiedTime() {
		return _modifiedTime;
	}

	@Override
	public void setModifiedTime(long modifiedTime) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_modifiedTime = modifiedTime;
	}

	@Override
	public long getOwnerId() {
		return _ownerId;
	}

	@Override
	public void setOwnerId(long ownerId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_ownerId = ownerId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalOwnerId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("ownerId"));
	}

	@Override
	public String getScope() {
		if (_scope == null) {
			return "";
		}
		else {
			return _scope;
		}
	}

	@Override
	public void setScope(String scope) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_scope = scope;
	}

	@Override
	public boolean getRead() {
		return _read;
	}

	@Override
	public boolean isRead() {
		return _read;
	}

	@Override
	public void setRead(boolean read) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_read = read;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public boolean getOriginalRead() {
		return GetterUtil.getBoolean(
			this.<Boolean>getColumnOriginalValue("read_"));
	}

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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalType() {
		return getColumnOriginalValue("type_");
	}

	@Override
	public String getSubtype() {
		if (_subtype == null) {
			return "";
		}
		else {
			return _subtype;
		}
	}

	@Override
	public void setSubtype(String subtype) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_subtype = subtype;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalSubtype() {
		return getColumnOriginalValue("subtype");
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
			0, FaroNotification.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public FaroNotification toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, FaroNotification>
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
		FaroNotificationImpl faroNotificationImpl = new FaroNotificationImpl();

		faroNotificationImpl.setFaroNotificationId(getFaroNotificationId());
		faroNotificationImpl.setGroupId(getGroupId());
		faroNotificationImpl.setUserId(getUserId());
		faroNotificationImpl.setCreateTime(getCreateTime());
		faroNotificationImpl.setModifiedTime(getModifiedTime());
		faroNotificationImpl.setOwnerId(getOwnerId());
		faroNotificationImpl.setScope(getScope());
		faroNotificationImpl.setRead(isRead());
		faroNotificationImpl.setType(getType());
		faroNotificationImpl.setSubtype(getSubtype());

		faroNotificationImpl.resetOriginalValues();

		return faroNotificationImpl;
	}

	@Override
	public FaroNotification cloneWithOriginalValues() {
		FaroNotificationImpl faroNotificationImpl = new FaroNotificationImpl();

		faroNotificationImpl.setFaroNotificationId(
			this.<Long>getColumnOriginalValue("faroNotificationId"));
		faroNotificationImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		faroNotificationImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		faroNotificationImpl.setCreateTime(
			this.<Long>getColumnOriginalValue("createTime"));
		faroNotificationImpl.setModifiedTime(
			this.<Long>getColumnOriginalValue("modifiedTime"));
		faroNotificationImpl.setOwnerId(
			this.<Long>getColumnOriginalValue("ownerId"));
		faroNotificationImpl.setScope(
			this.<String>getColumnOriginalValue("scope"));
		faroNotificationImpl.setRead(
			this.<Boolean>getColumnOriginalValue("read_"));
		faroNotificationImpl.setType(
			this.<String>getColumnOriginalValue("type_"));
		faroNotificationImpl.setSubtype(
			this.<String>getColumnOriginalValue("subtype"));

		return faroNotificationImpl;
	}

	@Override
	public int compareTo(FaroNotification faroNotification) {
		long primaryKey = faroNotification.getPrimaryKey();

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

		if (!(object instanceof FaroNotification)) {
			return false;
		}

		FaroNotification faroNotification = (FaroNotification)object;

		long primaryKey = faroNotification.getPrimaryKey();

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
		_columnOriginalValues = Collections.emptyMap();

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<FaroNotification> toCacheModel() {
		FaroNotificationCacheModel faroNotificationCacheModel =
			new FaroNotificationCacheModel();

		faroNotificationCacheModel.faroNotificationId = getFaroNotificationId();

		faroNotificationCacheModel.groupId = getGroupId();

		faroNotificationCacheModel.userId = getUserId();

		faroNotificationCacheModel.createTime = getCreateTime();

		faroNotificationCacheModel.modifiedTime = getModifiedTime();

		faroNotificationCacheModel.ownerId = getOwnerId();

		faroNotificationCacheModel.scope = getScope();

		String scope = faroNotificationCacheModel.scope;

		if ((scope != null) && (scope.length() == 0)) {
			faroNotificationCacheModel.scope = null;
		}

		faroNotificationCacheModel.read = isRead();

		faroNotificationCacheModel.type = getType();

		String type = faroNotificationCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			faroNotificationCacheModel.type = null;
		}

		faroNotificationCacheModel.subtype = getSubtype();

		String subtype = faroNotificationCacheModel.subtype;

		if ((subtype != null) && (subtype.length() == 0)) {
			faroNotificationCacheModel.subtype = null;
		}

		return faroNotificationCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<FaroNotification, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<FaroNotification, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<FaroNotification, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(FaroNotification)this);

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

		private static final Function<InvocationHandler, FaroNotification>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					FaroNotification.class, ModelWrapper.class);

	}

	private long _faroNotificationId;
	private long _groupId;
	private long _userId;
	private long _createTime;
	private long _modifiedTime;
	private long _ownerId;
	private String _scope;
	private boolean _read;
	private String _type;
	private String _subtype;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<FaroNotification, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((FaroNotification)this);
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

		_columnOriginalValues.put("faroNotificationId", _faroNotificationId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("createTime", _createTime);
		_columnOriginalValues.put("modifiedTime", _modifiedTime);
		_columnOriginalValues.put("ownerId", _ownerId);
		_columnOriginalValues.put("scope", _scope);
		_columnOriginalValues.put("read_", _read);
		_columnOriginalValues.put("type_", _type);
		_columnOriginalValues.put("subtype", _subtype);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("read_", "read");
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

		columnBitmasks.put("faroNotificationId", 1L);

		columnBitmasks.put("groupId", 2L);

		columnBitmasks.put("userId", 4L);

		columnBitmasks.put("createTime", 8L);

		columnBitmasks.put("modifiedTime", 16L);

		columnBitmasks.put("ownerId", 32L);

		columnBitmasks.put("scope", 64L);

		columnBitmasks.put("read_", 128L);

		columnBitmasks.put("type_", 256L);

		columnBitmasks.put("subtype", 512L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private FaroNotification _escapedModel;

}