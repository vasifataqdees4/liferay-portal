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

package com.liferay.object.rest.internal.dto.v1_0.converter;

import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetTagLocalService;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.document.library.util.DLURLHelper;
import com.liferay.list.type.model.ListTypeEntry;
import com.liferay.list.type.service.ListTypeEntryLocalService;
import com.liferay.object.constants.ObjectActionKeys;
import com.liferay.object.constants.ObjectFieldConstants;
import com.liferay.object.constants.ObjectFieldSettingConstants;
import com.liferay.object.constants.ObjectRelationshipConstants;
import com.liferay.object.entry.util.ObjectEntryValuesUtil;
import com.liferay.object.field.setting.util.ObjectFieldSettingUtil;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectField;
import com.liferay.object.model.ObjectRelationship;
import com.liferay.object.related.models.ObjectRelatedModelsProvider;
import com.liferay.object.related.models.ObjectRelatedModelsProviderRegistry;
import com.liferay.object.rest.dto.v1_0.AuditEvent;
import com.liferay.object.rest.dto.v1_0.AuditFieldChange;
import com.liferay.object.rest.dto.v1_0.FileEntry;
import com.liferay.object.rest.dto.v1_0.ListEntry;
import com.liferay.object.rest.dto.v1_0.ObjectEntry;
import com.liferay.object.rest.dto.v1_0.Status;
import com.liferay.object.rest.dto.v1_0.TaxonomyCategoryBrief;
import com.liferay.object.rest.dto.v1_0.util.CreatorUtil;
import com.liferay.object.rest.dto.v1_0.util.LinkUtil;
import com.liferay.object.rest.internal.dto.v1_0.util.TaxonomyCategoryBriefUtil;
import com.liferay.object.rest.internal.util.DTOConverterUtil;
import com.liferay.object.scope.ObjectScopeProvider;
import com.liferay.object.scope.ObjectScopeProviderRegistry;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectEntryService;
import com.liferay.object.service.ObjectFieldLocalService;
import com.liferay.object.service.ObjectRelationshipLocalService;
import com.liferay.object.system.SystemObjectDefinitionManager;
import com.liferay.object.system.SystemObjectDefinitionManagerRegistry;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.feature.flag.FeatureFlagManagerUtil;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.language.LanguageResources;
import com.liferay.portal.security.audit.event.generators.constants.EventTypes;
import com.liferay.portal.security.audit.storage.service.AuditEventLocalService;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;
import com.liferay.portal.vulcan.dto.converter.DTOConverterRegistry;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.fields.NestedFieldsContext;
import com.liferay.portal.vulcan.fields.NestedFieldsContextThreadLocal;
import com.liferay.portal.vulcan.util.LocalizedMapUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Javier de Arcos
 */
@Component(
	property = "dto.class.name=com.liferay.object.model.ObjectEntry",
	service = DTOConverter.class
)
public class ObjectEntryDTOConverter
	implements DTOConverter<com.liferay.object.model.ObjectEntry, ObjectEntry> {

	@Override
	public String getContentType() {
		return ObjectEntry.class.getSimpleName();
	}

	@Override
	public ObjectEntry toDTO(
			DTOConverterContext dtoConverterContext,
			com.liferay.object.model.ObjectEntry objectEntry)
		throws Exception {

		NestedFieldsContext nestedFieldsContext =
			NestedFieldsContextThreadLocal.getNestedFieldsContext();

		return _toDTO(
			dtoConverterContext, _getNestedFieldNames(nestedFieldsContext),
			_getNestedFieldsDepth(nestedFieldsContext), objectEntry);
	}

	private void _addNestedFields(
			DTOConverterContext dtoConverterContext, Map<String, Object> map,
			List<String> nestedFieldNames, int nestedFieldsDepth,
			String objectFieldName, ObjectRelationship objectRelationship,
			long primaryKey)
		throws Exception {

		if (ListUtil.isEmpty(nestedFieldNames)) {
			return;
		}

		Object value = null;

		ObjectDefinition objectDefinition =
			_objectDefinitionLocalService.getObjectDefinition(
				objectRelationship.getObjectDefinitionId1());

		if (objectDefinition.isUnmodifiableSystemObject()) {
			if (FeatureFlagManagerUtil.isEnabled("LPS-172094")) {
				SystemObjectDefinitionManager systemObjectDefinitionManager =
					_systemObjectDefinitionManagerRegistry.
						getSystemObjectDefinitionManager(
							objectDefinition.getName());

				value = DTOConverterUtil.toDTO(
					systemObjectDefinitionManager.
						getBaseModelByExternalReferenceCode(
							systemObjectDefinitionManager.
								getExternalReferenceCode(primaryKey),
							objectDefinition.getCompanyId()),
					_dtoConverterRegistry, systemObjectDefinitionManager,
					dtoConverterContext.getUser());
			}
			else {
				value = _objectEntryLocalService.getSystemModelAttributes(
					objectDefinition, primaryKey);
			}
		}
		else {
			value = _toDTO(
				_getDTOConverterContext(dtoConverterContext, primaryKey),
				nestedFieldNames, nestedFieldsDepth - 1,
				_objectEntryLocalService.getObjectEntry(primaryKey));
		}

		String objectFieldNameNestedField = StringUtil.replaceLast(
			objectFieldName.substring(
				objectFieldName.lastIndexOf(StringPool.UNDERLINE) + 1),
			"Id", "");

		for (String nestedFieldName : nestedFieldNames) {
			if (nestedFieldName.contains(objectFieldNameNestedField)) {
				map.put(
					StringUtil.replaceLast(objectFieldName, "Id", ""), value);
			}

			if (nestedFieldName.equals(objectRelationship.getName())) {
				map.put(nestedFieldName, value);
			}
		}
	}

	private void _addObjectRelationshipNames(
		Map<String, Object> map, ObjectField objectField,
		String objectFieldName, ObjectRelationship objectRelationship,
		long primaryKey, Map<String, Serializable> values) {

		String objectRelationshipERCObjectFieldName =
			ObjectFieldSettingUtil.getValue(
				ObjectFieldSettingConstants.
					NAME_OBJECT_RELATIONSHIP_ERC_OBJECT_FIELD_NAME,
				objectField);

		String relatedObjectEntryERC = GetterUtil.getString(
			values.get(objectRelationshipERCObjectFieldName));

		if (map.get(objectRelationship.getName()) == null) {
			map.put(
				objectRelationship.getName() + "ERC", relatedObjectEntryERC);
		}

		map.put(objectFieldName, primaryKey);

		map.put(objectRelationshipERCObjectFieldName, relatedObjectEntryERC);
	}

	private DTOConverterContext _getDTOConverterContext(
		DTOConverterContext dtoConverterContext, long objectEntryId) {

		UriInfo uriInfo = dtoConverterContext.getUriInfo();

		return new DefaultDTOConverterContext(
			dtoConverterContext.isAcceptAllLanguages(), null,
			dtoConverterContext.getDTOConverterRegistry(),
			dtoConverterContext.getHttpServletRequest(), objectEntryId,
			dtoConverterContext.getLocale(), uriInfo,
			dtoConverterContext.getUser());
	}

	private ListEntry _getListEntry(
		DTOConverterContext dtoConverterContext, String key,
		long listTypeDefinitionId) {

		ListTypeEntry listTypeEntry =
			_listTypeEntryLocalService.fetchListTypeEntry(
				listTypeDefinitionId, key);

		if (listTypeEntry == null) {
			return null;
		}

		return new ListEntry() {
			{
				key = listTypeEntry.getKey();
				name = listTypeEntry.getName(dtoConverterContext.getLocale());
				name_i18n = LocalizedMapUtil.getI18nMap(
					dtoConverterContext.isAcceptAllLanguages(),
					listTypeEntry.getNameMap());
			}
		};
	}

	private List<String> _getNestedFieldNames(
		NestedFieldsContext nestedFieldsContext) {

		if (nestedFieldsContext == null) {
			return new ArrayList<>();
		}

		return nestedFieldsContext.getFieldNames();
	}

	private int _getNestedFieldsDepth(NestedFieldsContext nestedFieldsContext) {
		if (nestedFieldsContext == null) {
			return _NESTED_FIELDS_DEFAULT_DEPTH;
		}

		return nestedFieldsContext.getDepth();
	}

	private ObjectDefinition _getObjectDefinition(
			DTOConverterContext dtoConverterContext,
			com.liferay.object.model.ObjectEntry objectEntry)
		throws Exception {

		ObjectDefinition objectDefinition =
			(ObjectDefinition)dtoConverterContext.getAttribute(
				"objectDefinition");

		if (objectDefinition == null) {
			objectDefinition =
				_objectDefinitionLocalService.getObjectDefinition(
					objectEntry.getObjectDefinitionId());
		}

		return objectDefinition;
	}

	private String _getScopeKey(
		ObjectDefinition objectDefinition,
		com.liferay.object.model.ObjectEntry objectEntry) {

		ObjectScopeProvider objectScopeProvider =
			_objectScopeProviderRegistry.getObjectScopeProvider(
				objectDefinition.getScope());

		if (objectScopeProvider.isGroupAware()) {
			Group group = _groupLocalService.fetchGroup(
				objectEntry.getGroupId());

			if (group == null) {
				return null;
			}

			return group.getGroupKey();
		}

		return null;
	}

	private AuditEvent[] _toAuditEvents(
			DTOConverterContext dtoConverterContext,
			List<String> nestedFieldNames, ObjectDefinition objectDefinition,
			com.liferay.object.model.ObjectEntry objectEntry)
		throws Exception {

		if (!objectDefinition.isEnableObjectEntryHistory() ||
			!nestedFieldNames.contains("auditEvents") ||
			!_objectEntryService.hasModelResourcePermission(
				objectDefinition.getObjectDefinitionId(),
				objectEntry.getObjectEntryId(),
				ObjectActionKeys.OBJECT_ENTRY_HISTORY)) {

			return null;
		}

		return TransformUtil.transformToArray(
			_auditEventLocalService.getAuditEvents(
				0, 0, null, null, null, null, null,
				String.valueOf(objectEntry.getObjectEntryId()), null, null,
				null, 0, null, false, QueryUtil.ALL_POS, QueryUtil.ALL_POS),
			auditEvent -> new AuditEvent() {
				{
					auditFieldChanges = _toAuditFieldChanges(
						auditEvent.getAdditionalInfo(),
						auditEvent.getEventType());
					creator = CreatorUtil.toCreator(
						_portal, dtoConverterContext.getUriInfo(),
						_userLocalService.fetchUser(auditEvent.getUserId()));
					dateCreated = auditEvent.getCreateDate();
					eventType = auditEvent.getEventType();
				}
			},
			AuditEvent.class);
	}

	private AuditFieldChange[] _toAuditFieldChanges(
			String additionalInfo, String eventType)
		throws Exception {

		JSONObject jsonObject = _jsonFactory.createJSONObject(additionalInfo);

		if (StringUtil.equals(eventType, EventTypes.ADD)) {
			Map<String, Object> map = jsonObject.toMap();

			return TransformUtil.transformToArray(
				map.keySet(),
				key -> new AuditFieldChange() {
					{
						name = key;
						newValue = map.get(key);
					}
				},
				AuditFieldChange.class);
		}

		return JSONUtil.toArray(
			jsonObject.getJSONArray("attributes"),
			attributeJSONObject -> new AuditFieldChange() {
				{
					name = attributeJSONObject.getString("name");
					newValue = attributeJSONObject.get("newValue");
					oldValue = attributeJSONObject.get("oldValue");
				}
			},
			AuditFieldChange.class);
	}

	private ObjectEntry _toDTO(
			DTOConverterContext dtoConverterContext,
			List<String> nestedFieldNames, int nestedFieldsDepth,
			com.liferay.object.model.ObjectEntry objectEntry)
		throws Exception {

		ObjectDefinition objectDefinition = _getObjectDefinition(
			dtoConverterContext, objectEntry);

		return new ObjectEntry() {
			{
				actions = dtoConverterContext.getActions();
				auditEvents = _toAuditEvents(
					dtoConverterContext, nestedFieldNames, objectDefinition,
					objectEntry);
				creator = CreatorUtil.toCreator(
					_portal, dtoConverterContext.getUriInfo(),
					_userLocalService.fetchUser(objectEntry.getUserId()));
				dateCreated = objectEntry.getCreateDate();
				dateModified = objectEntry.getModifiedDate();
				externalReferenceCode = objectEntry.getExternalReferenceCode();
				id = objectEntry.getObjectEntryId();

				if (objectDefinition.isEnableCategorization()) {
					keywords = ListUtil.toArray(
						_assetTagLocalService.getTags(
							objectDefinition.getClassName(),
							objectEntry.getObjectEntryId()),
						AssetTag.NAME_ACCESSOR);
				}

				properties = _toProperties(
					dtoConverterContext, nestedFieldNames, nestedFieldsDepth,
					objectDefinition, objectEntry);
				scopeKey = _getScopeKey(objectDefinition, objectEntry);
				status = new Status() {
					{
						code = objectEntry.getStatus();
						label = WorkflowConstants.getStatusLabel(
							objectEntry.getStatus());
						label_i18n = _language.get(
							LanguageResources.getResourceBundle(
								dtoConverterContext.getLocale()),
							WorkflowConstants.getStatusLabel(
								objectEntry.getStatus()));
					}
				};

				setTaxonomyCategoryBriefs(
					() -> {
						if (!objectDefinition.isEnableCategorization()) {
							return null;
						}

						return TransformUtil.transformToArray(
							_assetCategoryLocalService.getCategories(
								objectDefinition.getClassName(),
								objectEntry.getObjectEntryId()),
							assetCategory ->
								TaxonomyCategoryBriefUtil.
									toTaxonomyCategoryBrief(
										assetCategory, dtoConverterContext),
							TaxonomyCategoryBrief.class);
					});
			}
		};
	}

	private ObjectEntry[] _toObjectEntries(
		DTOConverterContext dtoConverterContext, List<String> nestedFieldNames,
		int nestedFieldsDepth,
		List<com.liferay.object.model.ObjectEntry> objectEntries) {

		return TransformUtil.transformToArray(
			objectEntries,
			objectEntry -> {
				try {
					return _toDTO(
						_getDTOConverterContext(
							dtoConverterContext,
							objectEntry.getObjectEntryId()),
						nestedFieldNames, nestedFieldsDepth - 1, objectEntry);
				}
				catch (Exception exception) {
					if (_log.isWarnEnabled()) {
						_log.warn(exception);
					}

					return null;
				}
			},
			ObjectEntry.class);
	}

	private Map<String, Object> _toProperties(
			DTOConverterContext dtoConverterContext,
			List<String> nestedFieldNames, int nestedFieldsDepth,
			ObjectDefinition objectDefinition,
			com.liferay.object.model.ObjectEntry objectEntry)
		throws Exception {

		Map<String, Object> map = new HashMap<>();

		Map<String, Serializable> values = objectEntry.getValues();

		List<ObjectField> objectFields =
			_objectFieldLocalService.getObjectFields(
				objectDefinition.getObjectDefinitionId(), false);

		for (ObjectField objectField : objectFields) {
			String objectFieldName = objectField.getName();

			Serializable serializable = values.get(objectFieldName);

			if (StringUtil.equals(
					objectField.getBusinessType(),
					ObjectFieldConstants.BUSINESS_TYPE_MULTISELECT_PICKLIST)) {

				if (objectField.getListTypeDefinitionId() == 0) {
					continue;
				}

				map.put(
					objectFieldName,
					TransformUtil.transformToList(
						StringUtil.split(
							(String)serializable, StringPool.COMMA_AND_SPACE),
						key -> _getListEntry(
							dtoConverterContext, key,
							objectField.getListTypeDefinitionId())));
			}
			else if (StringUtil.equals(
						objectField.getBusinessType(),
						ObjectFieldConstants.BUSINESS_TYPE_PICKLIST)) {

				if (objectField.getListTypeDefinitionId() == 0) {
					continue;
				}

				ListEntry listEntry = _getListEntry(
					dtoConverterContext, (String)serializable,
					objectField.getListTypeDefinitionId());

				if (listEntry != null) {
					map.put(objectFieldName, listEntry);
				}
			}
			else if (Objects.equals(
						objectField.getBusinessType(),
						ObjectFieldConstants.BUSINESS_TYPE_ATTACHMENT)) {

				long fileEntryId = GetterUtil.getLong(
					values.get(objectField.getName()));

				if (fileEntryId == 0) {
					continue;
				}

				DLFileEntry dlFileEntry =
					_dLFileEntryLocalService.fetchDLFileEntry(fileEntryId);

				FileEntry fileEntry = new FileEntry();

				if (dlFileEntry != null) {
					fileEntry.setId(dlFileEntry.getFileEntryId());
					fileEntry.setLink(
						LinkUtil.toLink(
							_dlAppService, dlFileEntry, _dlURLHelper,
							objectDefinition.getExternalReferenceCode(),
							objectEntry.getExternalReferenceCode(), _portal));
					fileEntry.setName(dlFileEntry.getFileName());
				}

				map.put(objectFieldName, fileEntry);
			}
			else if (Objects.equals(
						objectField.getBusinessType(),
						ObjectFieldConstants.BUSINESS_TYPE_RICH_TEXT)) {

				map.put(objectFieldName, serializable);
				map.put(
					objectFieldName + "RawText",
					ObjectEntryValuesUtil.getValueString(objectField, values));
			}
			else if (Objects.equals(
						objectField.getRelationshipType(),
						ObjectRelationshipConstants.TYPE_ONE_TO_MANY)) {

				long primaryKey = GetterUtil.getLong(serializable);

				ObjectRelationship objectRelationship =
					_objectRelationshipLocalService.
						fetchObjectRelationshipByObjectFieldId2(
							objectField.getObjectFieldId());

				if ((nestedFieldsDepth > 0) && (primaryKey > 0)) {
					_addNestedFields(
						dtoConverterContext, map, nestedFieldNames,
						nestedFieldsDepth, objectFieldName, objectRelationship,
						primaryKey);
				}

				_addObjectRelationshipNames(
					map, objectField, objectFieldName, objectRelationship,
					primaryKey, values);
			}
			else {
				map.put(objectFieldName, serializable);
			}
		}

		values.remove(objectDefinition.getPKObjectFieldName());

		if ((nestedFieldsDepth == 0) || ListUtil.isEmpty(nestedFieldNames)) {
			return map;
		}

		List<ObjectRelationship> objectRelationships =
			_objectRelationshipLocalService.getObjectRelationships(
				objectDefinition.getObjectDefinitionId());

		for (ObjectRelationship objectRelationship : objectRelationships) {
			if (!nestedFieldNames.contains(objectRelationship.getName())) {
				continue;
			}

			if (Objects.equals(
					objectRelationship.getType(),
					ObjectRelationshipConstants.TYPE_MANY_TO_MANY) ||
				Objects.equals(
					objectRelationship.getType(),
					ObjectRelationshipConstants.TYPE_ONE_TO_MANY)) {

				ObjectDefinition relatedObjectDefinition =
					_objectDefinitionLocalService.getObjectDefinition(
						objectRelationship.getObjectDefinitionId2());

				ObjectRelatedModelsProvider objectRelatedModelsProvider =
					_objectRelatedModelsProviderRegistry.
						getObjectRelatedModelsProvider(
							relatedObjectDefinition.getClassName(),
							relatedObjectDefinition.getCompanyId(),
							objectRelationship.getType());

				map.put(
					objectRelationship.getName(),
					_toObjectEntries(
						dtoConverterContext, nestedFieldNames,
						nestedFieldsDepth,
						objectRelatedModelsProvider.getRelatedModels(
							objectEntry.getGroupId(),
							objectRelationship.getObjectRelationshipId(),
							objectEntry.getObjectEntryId(), QueryUtil.ALL_POS,
							QueryUtil.ALL_POS)));
			}
		}

		return map;
	}

	private static final int _NESTED_FIELDS_DEFAULT_DEPTH = 1;

	private static final Log _log = LogFactoryUtil.getLog(
		ObjectEntryDTOConverter.class);

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private AssetTagLocalService _assetTagLocalService;

	@Reference
	private AuditEventLocalService _auditEventLocalService;

	@Reference
	private DLAppService _dlAppService;

	@Reference
	private DLFileEntryLocalService _dLFileEntryLocalService;

	@Reference
	private DLURLHelper _dlURLHelper;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private Language _language;

	@Reference
	private ListTypeEntryLocalService _listTypeEntryLocalService;

	@Reference
	private ObjectDefinitionLocalService _objectDefinitionLocalService;

	@Reference
	private ObjectEntryLocalService _objectEntryLocalService;

	@Reference
	private ObjectEntryService _objectEntryService;

	@Reference
	private ObjectFieldLocalService _objectFieldLocalService;

	@Reference
	private ObjectRelatedModelsProviderRegistry
		_objectRelatedModelsProviderRegistry;

	@Reference
	private ObjectRelationshipLocalService _objectRelationshipLocalService;

	@Reference
	private ObjectScopeProviderRegistry _objectScopeProviderRegistry;

	@Reference
	private Portal _portal;

	@Reference
	private SystemObjectDefinitionManagerRegistry
		_systemObjectDefinitionManagerRegistry;

	@Reference
	private UserLocalService _userLocalService;

}