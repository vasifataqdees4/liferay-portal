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

package com.liferay.osb.faro.contacts.model.impl;

import com.liferay.osb.faro.contacts.model.ContactsLayoutTemplate;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ContactsLayoutTemplate in entity cache.
 *
 * @author Shinn Lok
 * @generated
 */
public class ContactsLayoutTemplateCacheModel
	implements CacheModel<ContactsLayoutTemplate>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ContactsLayoutTemplateCacheModel)) {
			return false;
		}

		ContactsLayoutTemplateCacheModel contactsLayoutTemplateCacheModel =
			(ContactsLayoutTemplateCacheModel)object;

		if (contactsLayoutTemplateId ==
				contactsLayoutTemplateCacheModel.contactsLayoutTemplateId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, contactsLayoutTemplateId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{contactsLayoutTemplateId=");
		sb.append(contactsLayoutTemplateId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createTime=");
		sb.append(createTime);
		sb.append(", modifiedTime=");
		sb.append(modifiedTime);
		sb.append(", headerContactsCardTemplateIds=");
		sb.append(headerContactsCardTemplateIds);
		sb.append(", name=");
		sb.append(name);
		sb.append(", settings=");
		sb.append(settings);
		sb.append(", type=");
		sb.append(type);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ContactsLayoutTemplate toEntityModel() {
		ContactsLayoutTemplateImpl contactsLayoutTemplateImpl =
			new ContactsLayoutTemplateImpl();

		contactsLayoutTemplateImpl.setContactsLayoutTemplateId(
			contactsLayoutTemplateId);
		contactsLayoutTemplateImpl.setGroupId(groupId);
		contactsLayoutTemplateImpl.setUserId(userId);

		if (userName == null) {
			contactsLayoutTemplateImpl.setUserName("");
		}
		else {
			contactsLayoutTemplateImpl.setUserName(userName);
		}

		contactsLayoutTemplateImpl.setCreateTime(createTime);
		contactsLayoutTemplateImpl.setModifiedTime(modifiedTime);

		if (headerContactsCardTemplateIds == null) {
			contactsLayoutTemplateImpl.setHeaderContactsCardTemplateIds("");
		}
		else {
			contactsLayoutTemplateImpl.setHeaderContactsCardTemplateIds(
				headerContactsCardTemplateIds);
		}

		if (name == null) {
			contactsLayoutTemplateImpl.setName("");
		}
		else {
			contactsLayoutTemplateImpl.setName(name);
		}

		if (settings == null) {
			contactsLayoutTemplateImpl.setSettings("");
		}
		else {
			contactsLayoutTemplateImpl.setSettings(settings);
		}

		contactsLayoutTemplateImpl.setType(type);

		contactsLayoutTemplateImpl.resetOriginalValues();

		return contactsLayoutTemplateImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		contactsLayoutTemplateId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();

		createTime = objectInput.readLong();

		modifiedTime = objectInput.readLong();
		headerContactsCardTemplateIds = objectInput.readUTF();
		name = objectInput.readUTF();
		settings = objectInput.readUTF();

		type = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(contactsLayoutTemplateId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createTime);

		objectOutput.writeLong(modifiedTime);

		if (headerContactsCardTemplateIds == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(headerContactsCardTemplateIds);
		}

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (settings == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(settings);
		}

		objectOutput.writeInt(type);
	}

	public long contactsLayoutTemplateId;
	public long groupId;
	public long userId;
	public String userName;
	public long createTime;
	public long modifiedTime;
	public String headerContactsCardTemplateIds;
	public String name;
	public String settings;
	public int type;

}