<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
JournalEditDDMStructuresDisplayContext journalEditDDMStructuresDisplayContext = new JournalEditDDMStructuresDisplayContext(request, liferayPortletResponse);

DDMStructure ddmStructure = journalEditDDMStructuresDisplayContext.getDDMStructure();

DDMForm ddmForm = null;

if (ddmStructure != null) {
	ddmForm = ddmStructure.getDDMForm();
}
%>

<aui:model-context bean="<%= ddmStructure %>" model="<%= DDMStructure.class %>" />

<aui:input name="storageType" type="hidden" value="<%= journalEditDDMStructuresDisplayContext.getStorageType() %>" />

<c:if test="<%= journalEditDDMStructuresDisplayContext.isShowStructureKeyInput() %>">
	<aui:input disabled="<%= journalEditDDMStructuresDisplayContext.isStructureKeyInputDisabled() %>" name="structureKey" />
</c:if>

<aui:input activeLanguageIds="<%= journalEditDDMStructuresDisplayContext.getAvailableLanguageIds() %>" defaultLanguageId="<%= (ddmForm == null) ? LocaleUtil.toLanguageId(LocaleUtil.getSiteDefault()): LocaleUtil.toLanguageId(ddmForm.getDefaultLocale()) %>" languagesDropdownDirection="downleft" localized="<%= true %>" name="description" type="text" />

<c:if test="<%= ddmStructure != null %>">
	<portlet:resourceURL id="/journal/get_ddm_structure" var="getDDMStructureURL">
		<portlet:param name="ddmStructureId" value="<%= String.valueOf(journalEditDDMStructuresDisplayContext.getDDMStructureId()) %>" />
	</portlet:resourceURL>

	<aui:input name="url" type="resource" value="<%= getDDMStructureURL %>" />

	<%
	Portlet portlet = PortletLocalServiceUtil.getPortletById(portletDisplay.getId());
	%>

	<aui:input name="webDavURL" type="resource" value="<%= ddmStructure.getWebDavURL(themeDisplay, WebDAVUtil.getStorageToken(portlet)) %>" />
</c:if>