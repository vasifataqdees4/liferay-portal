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

package com.liferay.dynamic.data.mapping.internal;

import com.liferay.dynamic.data.mapping.exception.StructureDuplicateElementException;
import com.liferay.dynamic.data.mapping.exception.StructureNameException;
import com.liferay.dynamic.data.mapping.kernel.DDMForm;
import com.liferay.dynamic.data.mapping.kernel.DDMStructure;
import com.liferay.dynamic.data.mapping.kernel.DDMStructureManager;
import com.liferay.dynamic.data.mapping.kernel.NoSuchStructureException;
import com.liferay.dynamic.data.mapping.kernel.RequiredStructureException;
import com.liferay.dynamic.data.mapping.kernel.StructureDefinitionException;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.util.DDM;
import com.liferay.dynamic.data.mapping.util.DDMBeanTranslator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Leonardo Barros
 */
@Component(service = DDMStructureManager.class)
public class DDMStructureManagerImpl implements DDMStructureManager {

	@Override
	public DDMStructure addStructure(
			long userId, long groupId, String parentStructureKey,
			long classNameId, String structureKey, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, DDMForm ddmForm,
			String storageType, int type, ServiceContext serviceContext)
		throws PortalException {

		try {
			com.liferay.dynamic.data.mapping.model.DDMForm translatedDDMForm =
				_ddmBeanTranslator.translate(ddmForm);

			com.liferay.dynamic.data.mapping.model.DDMStructure ddmStructure =
				_ddmStructureLocalService.addStructure(
					userId, groupId, parentStructureKey, classNameId,
					structureKey, nameMap, descriptionMap, translatedDDMForm,
					_ddm.getDefaultDDMFormLayout(translatedDDMForm),
					storageType, type, serviceContext);

			return new DDMStructureImpl(ddmStructure);
		}
		catch (PortalException portalException) {
			throw translate(portalException);
		}
	}

	@Override
	public void deleteStructure(long structureId) throws PortalException {
		try {
			_ddmStructureLocalService.deleteStructure(structureId);
		}
		catch (PortalException portalException) {
			throw translate(portalException);
		}
	}

	@Override
	public DDMStructure fetchStructure(long structureId) {
		com.liferay.dynamic.data.mapping.model.DDMStructure ddmStructure =
			_ddmStructureLocalService.fetchDDMStructure(structureId);

		if (ddmStructure == null) {
			return null;
		}

		return new DDMStructureImpl(ddmStructure);
	}

	@Override
	public DDMStructure fetchStructure(
		long groupId, long classNameId, String structureKey) {

		com.liferay.dynamic.data.mapping.model.DDMStructure ddmStructure =
			_ddmStructureLocalService.fetchStructure(
				groupId, classNameId, structureKey);

		if (ddmStructure == null) {
			return null;
		}

		return new DDMStructureImpl(ddmStructure);
	}

	@Override
	public List<DDMStructure> getClassStructures(
		long companyId, long classNameId) {

		List<DDMStructure> ddmStructures = new ArrayList<>();

		List<com.liferay.dynamic.data.mapping.model.DDMStructure> structures =
			_ddmStructureLocalService.getClassStructures(
				companyId, classNameId);

		for (com.liferay.dynamic.data.mapping.model.DDMStructure structure :
				structures) {

			ddmStructures.add(new DDMStructureImpl(structure));
		}

		return ddmStructures;
	}

	@Override
	public DDMStructure updateStructure(
			long userId, long structureId, long parentStructureId,
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			DDMForm ddmForm, ServiceContext serviceContext)
		throws PortalException {

		try {
			com.liferay.dynamic.data.mapping.model.DDMForm copyDDMForm =
				_ddmBeanTranslator.translate(ddmForm);

			com.liferay.dynamic.data.mapping.model.DDMStructure ddmStructure =
				_ddmStructureLocalService.updateStructure(
					userId, structureId, parentStructureId, nameMap,
					descriptionMap, copyDDMForm,
					_ddm.getDefaultDDMFormLayout(copyDDMForm), serviceContext);

			return new DDMStructureImpl(ddmStructure);
		}
		catch (PortalException portalException) {
			throw translate(portalException);
		}
	}

	@Override
	public void updateStructureKey(long structureId, String structureKey)
		throws PortalException {

		com.liferay.dynamic.data.mapping.model.DDMStructure ddmStructure =
			_ddmStructureLocalService.getDDMStructure(structureId);

		ddmStructure.setStructureKey(structureKey);

		_ddmStructureLocalService.updateDDMStructure(ddmStructure);
	}

	protected PortalException translate(PortalException portalException) {
		if (portalException instanceof
				com.liferay.dynamic.data.mapping.exception.
					NoSuchStructureException) {

			return new NoSuchStructureException(
				portalException.getMessage(), portalException.getCause());
		}
		else if (portalException instanceof
					com.liferay.dynamic.data.mapping.exception.
						RequiredStructureException) {

			return new RequiredStructureException(
				portalException.getMessage(), portalException.getCause());
		}
		else if (portalException instanceof
					com.liferay.dynamic.data.mapping.exception.
						StructureDefinitionException) {

			return new StructureDefinitionException(
				portalException.getMessage(), portalException.getCause());
		}
		else if (portalException instanceof
					StructureDuplicateElementException) {

			return new StructureDuplicateElementException(
				portalException.getMessage(), portalException.getCause());
		}
		else if (portalException instanceof StructureNameException) {
			return new StructureNameException(
				portalException.getMessage(), portalException.getCause());
		}

		return portalException;
	}

	@Reference
	private DDM _ddm;

	@Reference
	private DDMBeanTranslator _ddmBeanTranslator;

	@Reference
	private DDMStructureLocalService _ddmStructureLocalService;

}