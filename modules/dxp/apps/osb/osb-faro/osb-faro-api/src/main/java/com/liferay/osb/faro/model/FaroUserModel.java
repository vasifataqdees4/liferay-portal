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

package com.liferay.osb.faro.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the FaroUser service. Represents a row in the &quot;OSBFaro_FaroUser&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.osb.faro.model.impl.FaroUserModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.osb.faro.model.impl.FaroUserImpl</code>.
 * </p>
 *
 * @author Matthew Kong
 * @see FaroUser
 * @generated
 */
@ProviderType
public interface FaroUserModel
	extends BaseModel<FaroUser>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a faro user model instance should use the {@link FaroUser} interface instead.
	 */

	/**
	 * Returns the primary key of this faro user.
	 *
	 * @return the primary key of this faro user
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this faro user.
	 *
	 * @param primaryKey the primary key of this faro user
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this faro user.
	 *
	 * @return the mvcc version of this faro user
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this faro user.
	 *
	 * @param mvccVersion the mvcc version of this faro user
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the faro user ID of this faro user.
	 *
	 * @return the faro user ID of this faro user
	 */
	public long getFaroUserId();

	/**
	 * Sets the faro user ID of this faro user.
	 *
	 * @param faroUserId the faro user ID of this faro user
	 */
	public void setFaroUserId(long faroUserId);

	/**
	 * Returns the faro user uuid of this faro user.
	 *
	 * @return the faro user uuid of this faro user
	 */
	public String getFaroUserUuid();

	/**
	 * Sets the faro user uuid of this faro user.
	 *
	 * @param faroUserUuid the faro user uuid of this faro user
	 */
	public void setFaroUserUuid(String faroUserUuid);

	/**
	 * Returns the group ID of this faro user.
	 *
	 * @return the group ID of this faro user
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this faro user.
	 *
	 * @param groupId the group ID of this faro user
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this faro user.
	 *
	 * @return the company ID of this faro user
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this faro user.
	 *
	 * @param companyId the company ID of this faro user
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the create time of this faro user.
	 *
	 * @return the create time of this faro user
	 */
	public long getCreateTime();

	/**
	 * Sets the create time of this faro user.
	 *
	 * @param createTime the create time of this faro user
	 */
	public void setCreateTime(long createTime);

	/**
	 * Returns the user ID of this faro user.
	 *
	 * @return the user ID of this faro user
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this faro user.
	 *
	 * @param userId the user ID of this faro user
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this faro user.
	 *
	 * @return the user uuid of this faro user
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this faro user.
	 *
	 * @param userUuid the user uuid of this faro user
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this faro user.
	 *
	 * @return the user name of this faro user
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this faro user.
	 *
	 * @param userName the user name of this faro user
	 */
	public void setUserName(String userName);

	/**
	 * Returns the modified time of this faro user.
	 *
	 * @return the modified time of this faro user
	 */
	public long getModifiedTime();

	/**
	 * Sets the modified time of this faro user.
	 *
	 * @param modifiedTime the modified time of this faro user
	 */
	public void setModifiedTime(long modifiedTime);

	/**
	 * Returns the live user ID of this faro user.
	 *
	 * @return the live user ID of this faro user
	 */
	public long getLiveUserId();

	/**
	 * Sets the live user ID of this faro user.
	 *
	 * @param liveUserId the live user ID of this faro user
	 */
	public void setLiveUserId(long liveUserId);

	/**
	 * Returns the live user uuid of this faro user.
	 *
	 * @return the live user uuid of this faro user
	 */
	public String getLiveUserUuid();

	/**
	 * Sets the live user uuid of this faro user.
	 *
	 * @param liveUserUuid the live user uuid of this faro user
	 */
	public void setLiveUserUuid(String liveUserUuid);

	/**
	 * Returns the role ID of this faro user.
	 *
	 * @return the role ID of this faro user
	 */
	public long getRoleId();

	/**
	 * Sets the role ID of this faro user.
	 *
	 * @param roleId the role ID of this faro user
	 */
	public void setRoleId(long roleId);

	/**
	 * Returns the email address of this faro user.
	 *
	 * @return the email address of this faro user
	 */
	@AutoEscape
	public String getEmailAddress();

	/**
	 * Sets the email address of this faro user.
	 *
	 * @param emailAddress the email address of this faro user
	 */
	public void setEmailAddress(String emailAddress);

	/**
	 * Returns the key of this faro user.
	 *
	 * @return the key of this faro user
	 */
	@AutoEscape
	public String getKey();

	/**
	 * Sets the key of this faro user.
	 *
	 * @param key the key of this faro user
	 */
	public void setKey(String key);

	/**
	 * Returns the status of this faro user.
	 *
	 * @return the status of this faro user
	 */
	public int getStatus();

	/**
	 * Sets the status of this faro user.
	 *
	 * @param status the status of this faro user
	 */
	public void setStatus(int status);

	@Override
	public FaroUser cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}