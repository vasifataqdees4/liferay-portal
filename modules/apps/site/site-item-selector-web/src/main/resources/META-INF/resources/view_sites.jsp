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
SitesItemSelectorViewDisplayContext sitesItemSelectorViewDisplayContext = (SitesItemSelectorViewDisplayContext)request.getAttribute(SitesItemSelectorWebKeys.SITES_ITEM_SELECTOR_DISPLAY_CONTEXT);
GroupURLProvider groupURLProvider = (GroupURLProvider)request.getAttribute(SiteWebKeys.GROUP_URL_PROVIDER);

String displayStyle = sitesItemSelectorViewDisplayContext.getDisplayStyle();

GroupItemSelectorCriterion groupItemSelectorCriterion = sitesItemSelectorViewDisplayContext.getGroupItemSelectorCriterion();

String target = ParamUtil.getString(request, "target", groupItemSelectorCriterion.getTarget());
%>

<clay:management-toolbar
	managementToolbarDisplayContext="<%= new SitesItemSelectorViewManagementToolbarDisplayContext(request, liferayPortletRequest, liferayPortletResponse, sitesItemSelectorViewDisplayContext) %>"
/>

<aui:form action="<%= sitesItemSelectorViewDisplayContext.getPortletURL() %>" cssClass="container-fluid container-fluid-max-xl" method="post" name="selectGroupFm">
	<c:if test="<%= sitesItemSelectorViewDisplayContext.isShowChildSitesLink() %>">
		<div id="breadcrumb">
			<liferay-site-navigation:breadcrumb
				breadcrumbEntries="<%= BreadcrumbEntriesUtil.getBreadcrumbEntries(request, false, false, false, true, true) %>"
			/>
		</div>
	</c:if>

	<liferay-ui:search-container
		searchContainer="<%= sitesItemSelectorViewDisplayContext.getGroupSearch() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.model.Group"
			escapedModel="<%= true %>"
			keyProperty="groupId"
			modelVar="group"
			rowIdProperty="friendlyURL"
			rowVar="row"
		>

			<%
			List<Group> childGroups = GroupServiceUtil.getGroups(group.getCompanyId(), group.getGroupId(), true);

			Map<String, Object> data = HashMapBuilder.<String, Object>put(
				"groupdescriptivename", group.getDescriptiveName(locale)
			).put(
				"groupid", group.getGroupId()
			).put(
				"groupscopelabel", LanguageUtil.get(resourceBundle, group.getScopeLabel(themeDisplay))
			).put(
				"grouptarget", target
			).put(
				"grouptype", LanguageUtil.get(resourceBundle, group.getTypeLabel())
			).put(
				"url", groupURLProvider.getGroupURL(group, liferayPortletRequest)
			).put(
				"uuid", group.getUuid()
			).build();

			String childGroupsHREF = null;

			if (!childGroups.isEmpty()) {
				childGroupsHREF = PortletURLBuilder.create(
					sitesItemSelectorViewDisplayContext.getPortletURL()
				).setParameter(
					"groupId", group.getGroupId()
				).buildString();
			}
			%>

			<c:choose>
				<c:when test='<%= displayStyle.equals("descriptive") %>'>
					<c:choose>
						<c:when test="<%= Validator.isNotNull(group.getLogoURL(themeDisplay, false)) %>">
							<liferay-ui:search-container-column-image
								src="<%= group.getLogoURL(themeDisplay, false) %>"
							/>
						</c:when>
						<c:otherwise>
							<liferay-ui:search-container-column-icon
								icon="<%= group.getIconCssClass() %>"
							/>
						</c:otherwise>
					</c:choose>

					<liferay-ui:search-container-column-text
						colspan="<%= 2 %>"
					>
						<h5>
							<c:choose>
								<c:when test="<%= group.isActive() %>">
									<aui:a cssClass="selector-button" data="<%= data %>" href="javascript:void(0);">
										<%= HtmlUtil.escape(sitesItemSelectorViewDisplayContext.getGroupName(group)) %>
									</aui:a>
								</c:when>
								<c:otherwise>
									<span class="disabled selector-button text-muted">
										<liferay-ui:message arguments="<%= HtmlUtil.escape(sitesItemSelectorViewDisplayContext.getGroupName(group)) %>" key="x-inactive" />
									</span>
								</c:otherwise>
							</c:choose>

							<c:if test="<%= groupItemSelectorCriterion.isAllowNavigation() && group.isActive() %>">
								<aui:a href="<%= groupURLProvider.getGroupURL(group, liferayPortletRequest) %>" target="_blank" />
							</c:if>
						</h5>

						<h6 class="text-default">
							<span><liferay-ui:message key="<%= group.getScopeLabel(themeDisplay) %>" /></span>
						</h6>

						<c:if test="<%= sitesItemSelectorViewDisplayContext.isShowChildSitesLink() %>">
							<h6>
								<aui:a cssClass='<%= !childGroups.isEmpty() ? "text-default" : "disabled text-muted" %>' href="<%= childGroupsHREF %>">
									<liferay-ui:message arguments="<%= String.valueOf(childGroups.size()) %>" key="x-child-sites" />
								</aui:a>
							</h6>
						</c:if>
					</liferay-ui:search-container-column-text>
				</c:when>
				<c:when test='<%= displayStyle.equals("icon") %>'>

					<%
					row.setCssClass("card-page-item card-page-item-directory " + row.getCssClass());

					Map<String, Object> linkData = HashMapBuilder.<String, Object>put(
						"prevent-selection", true
					).build();

					SiteVerticalCard siteVerticalCard = new SiteVerticalCard(group, liferayPortletRequest);
					%>

					<liferay-ui:search-container-column-text>
						<div class="card card-horizontal">
							<div class="card-body">
								<clay:content-row
									cssClass="card-row"
								>
									<clay:content-col>
										<clay:sticker
											displayType="secondary"
										>
											<c:choose>
												<c:when test="<%= Validator.isNotNull(siteVerticalCard.getImageSrc()) %>">
													<span class="sticker-overlay">
														<img alt="" class="sticker-img" src="<%= siteVerticalCard.getImageSrc() %>" />
													</span>
												</c:when>
												<c:otherwise>
													<clay:icon
														symbol="<%= group.getIconCssClass() %>"
													/>
												</c:otherwise>
											</c:choose>
										</clay:sticker>
									</clay:content-col>

									<clay:content-col
										expand="<%= true %>"
										gutters="<%= true %>"
									>
										<c:choose>
											<c:when test="<%= group.isActive() %>">

												<%
												boolean hasURL = true;

												if (data.get("url") == null) {
													hasURL = false;
												}
												%>

												<aui:a cssClass='<%= hasURL ? "card-title selector-button text-truncate" : "disabled text-muted" %>' data="<%= data %>" href='<%= hasURL ? "javascript:void(0);" : StringPool.BLANK %>'>
													<%= HtmlUtil.escape(sitesItemSelectorViewDisplayContext.getGroupName(group)) %>
												</aui:a>
											</c:when>
											<c:otherwise>
												<span class="card-title disabled selector-button text-muted text-truncate">
													<liferay-ui:message arguments="<%= siteVerticalCard.getTitle() %>" key="x-inactive" />
												</span>
											</c:otherwise>
										</c:choose>

										<c:if test="<%= sitesItemSelectorViewDisplayContext.isShowChildSitesLink() %>">
											<aui:a cssClass='<%= "card-subtitle text-truncate " + (!childGroups.isEmpty() ? "text-default" : "text-muted") %>' data="<%= linkData %>" href="<%= childGroupsHREF %>" title="<%= siteVerticalCard.getSubtitle() %>">
												<%= siteVerticalCard.getSubtitle() %>
											</aui:a>
										</c:if>
									</clay:content-col>

									<c:if test="<%= groupItemSelectorCriterion.isAllowNavigation() && group.isActive() %>">
										<clay:content-col>
											<clay:link
												aria-label='<%= LanguageUtil.format(request, "x-opens-new-window", siteVerticalCard.getTitle(), false) %>'
												borderless="<%= true %>"
												cssClass="lfr-portal-tooltip"
												displayType="secondary"
												href="<%= siteVerticalCard.getHref() %>"
												icon="shortcut"
												monospaced="<%= true %>"
												small="<%= true %>"
												target="_blank"
												title='<%= LanguageUtil.get(request, "opens-new-window") %>'
												type="button"
											/>
										</clay:content-col>
									</c:if>
								</clay:content-row>
							</div>
						</div>
					</liferay-ui:search-container-column-text>
				</c:when>
				<c:when test='<%= displayStyle.equals("list") %>'>
					<liferay-ui:search-container-column-text
						name="name"
						truncate="<%= true %>"
					>
						<c:choose>
							<c:when test="<%= group.isActive() %>">
								<aui:a cssClass="selector-button" data="<%= data %>" href="javascript:void(0);">
									<%= HtmlUtil.escape(sitesItemSelectorViewDisplayContext.getGroupName(group)) %>
								</aui:a>
							</c:when>
							<c:otherwise>
								<span class="disabled selector-button text-muted">
									<liferay-ui:message arguments="<%= HtmlUtil.escape(sitesItemSelectorViewDisplayContext.getGroupName(group)) %>" key="x-inactive" />
								</span>
							</c:otherwise>
						</c:choose>

						<c:if test="<%= groupItemSelectorCriterion.isAllowNavigation() && group.isActive() %>">
							<aui:a href="<%= groupURLProvider.getGroupURL(group, liferayPortletRequest) %>" target="_blank" />
						</c:if>
					</liferay-ui:search-container-column-text>

					<c:if test="<%= sitesItemSelectorViewDisplayContext.isShowChildSitesLink() %>">
						<liferay-ui:search-container-column-text
							name="child-sites"
							truncate="<%= true %>"
						>
							<aui:a cssClass='<%= !childGroups.isEmpty() ? "text-default" : "disabled text-muted" %>' href="<%= childGroupsHREF %>">
								<liferay-ui:message arguments="<%= String.valueOf(childGroups.size()) %>" key="x-child-sites" />
							</aui:a>
						</liferay-ui:search-container-column-text>
					</c:if>

					<liferay-ui:search-container-column-text
						name="type"
						value="<%= LanguageUtil.get(request, group.getScopeLabel(themeDisplay)) %>"
					/>
				</c:when>
			</c:choose>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="<%= displayStyle %>"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>