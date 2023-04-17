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
import com.liferay.osb.faro.model.FaroChannel;
import com.liferay.osb.faro.model.FaroChannelModel;
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
 * The base model implementation for the FaroChannel service. Represents a row in the &quot;OSBFaro_FaroChannel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>FaroChannelModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link FaroChannelImpl}.
 * </p>
 *
 * @author Matthew Kong
 * @see FaroChannelImpl
 * @generated
 */
public class FaroChannelModelImpl
	extends BaseModelImpl<FaroChannel> implements FaroChannelModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a faro channel model instance should use the <code>FaroChannel</code> interface instead.
	 */
	public static final String TABLE_NAME = "OSBFaro_FaroChannel";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"faroChannelId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"createTime", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"modifiedTime", Types.BIGINT},
		{"channelId", Types.VARCHAR}, {"name", Types.VARCHAR},
		{"permissionType", Types.INTEGER}, {"workspaceGroupId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("faroChannelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createTime", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("modifiedTime", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("channelId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("permissionType", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("workspaceGroupId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table OSBFaro_FaroChannel (mvccVersion LONG default 0 not null,faroChannelId LONG not null primary key,groupId LONG,companyId LONG,createTime LONG,userId LONG,userName VARCHAR(75) null,modifiedTime LONG,channelId VARCHAR(75) null,name VARCHAR(75) null,permissionType INTEGER,workspaceGroupId LONG)";

	public static final String TABLE_SQL_DROP =
		"drop table OSBFaro_FaroChannel";

	public static final String ORDER_BY_JPQL =
		" ORDER BY faroChannel.faroChannelId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY OSBFaro_FaroChannel.faroChannelId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CHANNELID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long WORKSPACEGROUPID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long FAROCHANNELID_COLUMN_BITMASK = 16L;

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

	public FaroChannelModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _faroChannelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setFaroChannelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _faroChannelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return FaroChannel.class;
	}

	@Override
	public String getModelClassName() {
		return FaroChannel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<FaroChannel, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<FaroChannel, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<FaroChannel, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((FaroChannel)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<FaroChannel, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<FaroChannel, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(FaroChannel)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<FaroChannel, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<FaroChannel, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<FaroChannel, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<FaroChannel, Object>>
				attributeGetterFunctions =
					new LinkedHashMap<String, Function<FaroChannel, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", FaroChannel::getMvccVersion);
			attributeGetterFunctions.put(
				"faroChannelId", FaroChannel::getFaroChannelId);
			attributeGetterFunctions.put("groupId", FaroChannel::getGroupId);
			attributeGetterFunctions.put(
				"companyId", FaroChannel::getCompanyId);
			attributeGetterFunctions.put(
				"createTime", FaroChannel::getCreateTime);
			attributeGetterFunctions.put("userId", FaroChannel::getUserId);
			attributeGetterFunctions.put("userName", FaroChannel::getUserName);
			attributeGetterFunctions.put(
				"modifiedTime", FaroChannel::getModifiedTime);
			attributeGetterFunctions.put(
				"channelId", FaroChannel::getChannelId);
			attributeGetterFunctions.put("name", FaroChannel::getName);
			attributeGetterFunctions.put(
				"permissionType", FaroChannel::getPermissionType);
			attributeGetterFunctions.put(
				"workspaceGroupId", FaroChannel::getWorkspaceGroupId);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map<String, BiConsumer<FaroChannel, Object>>
			_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<FaroChannel, ?>> attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<FaroChannel, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<FaroChannel, Long>)FaroChannel::setMvccVersion);
			attributeSetterBiConsumers.put(
				"faroChannelId",
				(BiConsumer<FaroChannel, Long>)FaroChannel::setFaroChannelId);
			attributeSetterBiConsumers.put(
				"groupId",
				(BiConsumer<FaroChannel, Long>)FaroChannel::setGroupId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<FaroChannel, Long>)FaroChannel::setCompanyId);
			attributeSetterBiConsumers.put(
				"createTime",
				(BiConsumer<FaroChannel, Long>)FaroChannel::setCreateTime);
			attributeSetterBiConsumers.put(
				"userId",
				(BiConsumer<FaroChannel, Long>)FaroChannel::setUserId);
			attributeSetterBiConsumers.put(
				"userName",
				(BiConsumer<FaroChannel, String>)FaroChannel::setUserName);
			attributeSetterBiConsumers.put(
				"modifiedTime",
				(BiConsumer<FaroChannel, Long>)FaroChannel::setModifiedTime);
			attributeSetterBiConsumers.put(
				"channelId",
				(BiConsumer<FaroChannel, String>)FaroChannel::setChannelId);
			attributeSetterBiConsumers.put(
				"name", (BiConsumer<FaroChannel, String>)FaroChannel::setName);
			attributeSetterBiConsumers.put(
				"permissionType",
				(BiConsumer<FaroChannel, Integer>)
					FaroChannel::setPermissionType);
			attributeSetterBiConsumers.put(
				"workspaceGroupId",
				(BiConsumer<FaroChannel, Long>)
					FaroChannel::setWorkspaceGroupId);

			_attributeSetterBiConsumers = Collections.unmodifiableMap(
				(Map)attributeSetterBiConsumers);
		}

	}

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

	@Override
	public long getFaroChannelId() {
		return _faroChannelId;
	}

	@Override
	public void setFaroChannelId(long faroChannelId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_faroChannelId = faroChannelId;
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalUserId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("userId"));
	}

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
	public String getChannelId() {
		if (_channelId == null) {
			return "";
		}
		else {
			return _channelId;
		}
	}

	@Override
	public void setChannelId(String channelId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_channelId = channelId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalChannelId() {
		return getColumnOriginalValue("channelId");
	}

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

	@Override
	public int getPermissionType() {
		return _permissionType;
	}

	@Override
	public void setPermissionType(int permissionType) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_permissionType = permissionType;
	}

	@Override
	public long getWorkspaceGroupId() {
		return _workspaceGroupId;
	}

	@Override
	public void setWorkspaceGroupId(long workspaceGroupId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_workspaceGroupId = workspaceGroupId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalWorkspaceGroupId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("workspaceGroupId"));
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
			getCompanyId(), FaroChannel.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public FaroChannel toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, FaroChannel>
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
		FaroChannelImpl faroChannelImpl = new FaroChannelImpl();

		faroChannelImpl.setMvccVersion(getMvccVersion());
		faroChannelImpl.setFaroChannelId(getFaroChannelId());
		faroChannelImpl.setGroupId(getGroupId());
		faroChannelImpl.setCompanyId(getCompanyId());
		faroChannelImpl.setCreateTime(getCreateTime());
		faroChannelImpl.setUserId(getUserId());
		faroChannelImpl.setUserName(getUserName());
		faroChannelImpl.setModifiedTime(getModifiedTime());
		faroChannelImpl.setChannelId(getChannelId());
		faroChannelImpl.setName(getName());
		faroChannelImpl.setPermissionType(getPermissionType());
		faroChannelImpl.setWorkspaceGroupId(getWorkspaceGroupId());

		faroChannelImpl.resetOriginalValues();

		return faroChannelImpl;
	}

	@Override
	public FaroChannel cloneWithOriginalValues() {
		FaroChannelImpl faroChannelImpl = new FaroChannelImpl();

		faroChannelImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		faroChannelImpl.setFaroChannelId(
			this.<Long>getColumnOriginalValue("faroChannelId"));
		faroChannelImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		faroChannelImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		faroChannelImpl.setCreateTime(
			this.<Long>getColumnOriginalValue("createTime"));
		faroChannelImpl.setUserId(this.<Long>getColumnOriginalValue("userId"));
		faroChannelImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		faroChannelImpl.setModifiedTime(
			this.<Long>getColumnOriginalValue("modifiedTime"));
		faroChannelImpl.setChannelId(
			this.<String>getColumnOriginalValue("channelId"));
		faroChannelImpl.setName(this.<String>getColumnOriginalValue("name"));
		faroChannelImpl.setPermissionType(
			this.<Integer>getColumnOriginalValue("permissionType"));
		faroChannelImpl.setWorkspaceGroupId(
			this.<Long>getColumnOriginalValue("workspaceGroupId"));

		return faroChannelImpl;
	}

	@Override
	public int compareTo(FaroChannel faroChannel) {
		long primaryKey = faroChannel.getPrimaryKey();

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

		if (!(object instanceof FaroChannel)) {
			return false;
		}

		FaroChannel faroChannel = (FaroChannel)object;

		long primaryKey = faroChannel.getPrimaryKey();

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

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<FaroChannel> toCacheModel() {
		FaroChannelCacheModel faroChannelCacheModel =
			new FaroChannelCacheModel();

		faroChannelCacheModel.mvccVersion = getMvccVersion();

		faroChannelCacheModel.faroChannelId = getFaroChannelId();

		faroChannelCacheModel.groupId = getGroupId();

		faroChannelCacheModel.companyId = getCompanyId();

		faroChannelCacheModel.createTime = getCreateTime();

		faroChannelCacheModel.userId = getUserId();

		faroChannelCacheModel.userName = getUserName();

		String userName = faroChannelCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			faroChannelCacheModel.userName = null;
		}

		faroChannelCacheModel.modifiedTime = getModifiedTime();

		faroChannelCacheModel.channelId = getChannelId();

		String channelId = faroChannelCacheModel.channelId;

		if ((channelId != null) && (channelId.length() == 0)) {
			faroChannelCacheModel.channelId = null;
		}

		faroChannelCacheModel.name = getName();

		String name = faroChannelCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			faroChannelCacheModel.name = null;
		}

		faroChannelCacheModel.permissionType = getPermissionType();

		faroChannelCacheModel.workspaceGroupId = getWorkspaceGroupId();

		return faroChannelCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<FaroChannel, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<FaroChannel, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<FaroChannel, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((FaroChannel)this);

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

		private static final Function<InvocationHandler, FaroChannel>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					FaroChannel.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _faroChannelId;
	private long _groupId;
	private long _companyId;
	private long _createTime;
	private long _userId;
	private String _userName;
	private long _modifiedTime;
	private String _channelId;
	private String _name;
	private int _permissionType;
	private long _workspaceGroupId;

	public <T> T getColumnValue(String columnName) {
		Function<FaroChannel, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((FaroChannel)this);
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
		_columnOriginalValues.put("faroChannelId", _faroChannelId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("createTime", _createTime);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("modifiedTime", _modifiedTime);
		_columnOriginalValues.put("channelId", _channelId);
		_columnOriginalValues.put("name", _name);
		_columnOriginalValues.put("permissionType", _permissionType);
		_columnOriginalValues.put("workspaceGroupId", _workspaceGroupId);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("faroChannelId", 2L);

		columnBitmasks.put("groupId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("createTime", 16L);

		columnBitmasks.put("userId", 32L);

		columnBitmasks.put("userName", 64L);

		columnBitmasks.put("modifiedTime", 128L);

		columnBitmasks.put("channelId", 256L);

		columnBitmasks.put("name", 512L);

		columnBitmasks.put("permissionType", 1024L);

		columnBitmasks.put("workspaceGroupId", 2048L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private FaroChannel _escapedModel;

}