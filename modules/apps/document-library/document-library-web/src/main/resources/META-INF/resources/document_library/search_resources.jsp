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

<%@ include file="/document_library/init.jsp" %>

<%
long repositoryId = ParamUtil.getLong(request, "repositoryId");

if (repositoryId == 0) {
	repositoryId = scopeGroupId;
}

long searchRepositoryId = ParamUtil.getLong(request, "searchRepositoryId");

if (searchRepositoryId == 0) {
	searchRepositoryId = scopeGroupId;
}

long folderId = ParamUtil.getLong(request, "folderId");

long searchFolderId = ParamUtil.getLong(request, "searchFolderId");

Folder folder = null;

if (searchFolderId > 0) {
	folder = DLAppServiceUtil.getFolder(searchFolderId);
}

String keywords = ParamUtil.getString(request, "keywords");

DLAdminDisplayContext dlAdminDisplayContext = (DLAdminDisplayContext)request.getAttribute(DLAdminDisplayContext.class.getName());
DLPortletInstanceSettingsHelper dlPortletInstanceSettingsHelper = new DLPortletInstanceSettingsHelper(dlRequestHelper);
DLViewEntriesDisplayContext dlViewEntriesDisplayContext = new DLViewEntriesDisplayContext(liferayPortletRequest, liferayPortletResponse);

EntriesChecker entriesChecker = new EntriesChecker(liferayPortletResponse);

entriesChecker.setCssClass("entry-selector");
entriesChecker.setRememberCheckBoxStateURLRegex("^(?!.*" + liferayPortletResponse.getNamespace() + "redirect).*(folderId=" + String.valueOf(folderId) + ")");
%>

<c:if test='<%= dlAdminDisplayContext.isSearch() && ParamUtil.getBoolean(request, "showSearchInfo") %>'>
	<liferay-util:include page="/document_library/search_info.jsp" servletContext="<%= application %>" />
</c:if>

<aui:input name="repositoryId" type="hidden" value="<%= repositoryId %>" />
<aui:input name="searchRepositoryId" type="hidden" value="<%= searchRepositoryId %>" />

<liferay-util:buffer
	var="searchResults"
>

	<%
	SearchContainer<RepositoryEntry> dlSearchContainer = dlAdminDisplayContext.getSearchContainer();
	%>

	<div class="document-container" id="<portlet:namespace />entriesContainer">
		<liferay-ui:search-container
			emptyResultsMessage='<%= LanguageUtil.format(request, "no-documents-were-found-that-matched-the-keywords-x", HtmlUtil.escape(keywords), false) %>'
			id="entries"
			searchContainer="<%= dlSearchContainer %>"
			total="<%= dlSearchContainer.getTotal() %>"
		>
			<liferay-ui:search-container-row
				className="Object"
				modelVar="result"
			>
				<%@ include file="/document_library/cast_result.jspf" %>

				<c:choose>
					<c:when test="<%= (fileEntry != null) && DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.VIEW) %>">

						<%
						FileVersion latestFileVersion = fileEntry.getFileVersion();

						if ((user.getUserId() == fileEntry.getUserId()) || permissionChecker.isContentReviewer(user.getCompanyId(), scopeGroupId) || DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.UPDATE)) {
							latestFileVersion = fileEntry.getLatestFileVersion();
						}

						if ((dlSearchContainer.getRowChecker() == null) && (DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.DELETE) || DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.UPDATE))) {
							dlSearchContainer.setRowChecker(entriesChecker);
						}

						String thumbnailSrc = DLURLHelperUtil.getThumbnailSrc(fileEntry, latestFileVersion, themeDisplay);

						row.setData(
							HashMapBuilder.<String, Object>put(
								"actions", StringUtil.merge(dlViewEntriesDisplayContext.getAvailableActions(fileEntry))
							).put(
								"draggable", dlViewEntriesDisplayContext.isDraggable(fileEntry)
							).put(
								"title", HtmlUtil.unescape(fileEntry.getTitle())
							).build());

						DLViewFileVersionDisplayContext dlViewFileVersionDisplayContext = null;

						if (fileShortcut == null) {
							dlViewFileVersionDisplayContext = dlDisplayContextProvider.getDLViewFileVersionDisplayContext(request, response, fileEntry.getFileVersion());

							row.setPrimaryKey(String.valueOf(fileEntry.getFileEntryId()));
						}
						else {
							dlViewFileVersionDisplayContext = dlDisplayContextProvider.getDLViewFileVersionDisplayContext(request, response, fileShortcut);

							row.setPrimaryKey(String.valueOf(fileShortcut.getFileShortcutId()));
						}
						%>

						<c:choose>
							<c:when test="<%= dlViewEntriesDisplayContext.isDescriptiveDisplayStyle() %>">
								<c:choose>
									<c:when test="<%= Validator.isNotNull(thumbnailSrc) %>">
										<liferay-ui:search-container-column-image
											src="<%= thumbnailSrc %>"
											toggleRowChecker="<%= true %>"
										/>
									</c:when>
									<c:when test="<%= Validator.isNotNull(latestFileVersion.getExtension()) %>">
										<liferay-ui:search-container-column-text>
											<liferay-document-library:mime-type-sticker
												fileVersion="<%= fileEntry.getFileVersion() %>"
											/>
										</liferay-ui:search-container-column-text>
									</c:when>
									<c:otherwise>
										<liferay-ui:search-container-column-icon
											icon="documents-and-media"
											toggleRowChecker="<%= true %>"
										/>
									</c:otherwise>
								</c:choose>

								<liferay-ui:search-container-column-jsp
									colspan="<%= 2 %>"
									path="/document_library/view_file_entry_descriptive.jsp"
								/>

								<c:if test="<%= dlPortletInstanceSettingsHelper.isShowActions() %>">
									<liferay-ui:search-container-column-jsp
										path="/document_library/file_entry_action.jsp"
									/>
								</c:if>
							</c:when>
							<c:when test="<%= dlViewEntriesDisplayContext.isIconDisplayStyle() %>">
								<liferay-ui:search-container-column-text>
									<div class="card-type-asset entry-display-style file-card form-check form-check-card form-check-top-left">
										<div class="card">
											<div class="aspect-ratio card-item-first">
												<div class="custom-checkbox custom-control">
													<label>

														<%
														RowChecker rowChecker = searchContainer.getRowChecker();

														rowChecker.setCssClass("custom-control-input");
														%>

														<%= rowChecker.getRowCheckBox(request, row) %>

														<span class="custom-control-label"></span>

														<c:choose>
															<c:when test="<%= dlViewFileVersionDisplayContext.hasCustomThumbnail() %>">

																<%
																dlViewFileVersionDisplayContext.renderCustomThumbnail(request, PipingServletResponseFactory.createPipingServletResponse(pageContext));
																%>

															</c:when>
															<c:when test="<%= Validator.isNull(thumbnailSrc) %>">
																<aui:icon cssClass="aspect-ratio-item-center-middle aspect-ratio-item-fluid card-type-asset-icon" image="documents-and-media" markupView="lexicon" />
															</c:when>
															<c:otherwise>
																<img alt="" class="aspect-ratio-item-center-middle aspect-ratio-item-fluid" src="<%= thumbnailSrc %>" />
															</c:otherwise>
														</c:choose>

														<liferay-document-library:mime-type-sticker
															cssClass="sticker-bottom-left"
															fileVersion="<%= latestFileVersion %>"
														/>
													</label>
												</div>
											</div>

											<div class="card-body">
												<div class="card-row">
													<div class="autofit-col autofit-col-expand">
														<aui:a cssClass="card-title text-truncate" href="<%= dlViewEntriesDisplayContext.getViewFileEntryURL(fileEntry) %>" title="<%= HtmlUtil.escapeAttribute(latestFileVersion.getTitle()) %>">
															<%= latestFileVersion.getTitle() %>
														</aui:a>

														<div class="card-subtitle text-truncate">
															<%= LanguageUtil.format(request, "modified-x-ago-by-x", new String[] {LanguageUtil.getTimeDescription(locale, System.currentTimeMillis() - fileEntry.getModifiedDate().getTime(), true), HtmlUtil.escape(latestFileVersion.getUserName())}, false) %>
														</div>

														<div class="card-detail">
															<liferay-portal-workflow:status
																showStatusLabel="<%= false %>"
																status="<%= latestFileVersion.getStatus() %>"
															/>

															<c:choose>
																<c:when test="<%= fileShortcut != null %>">
																	<span class="inline-item inline-item-after state-icon">
																		<aui:icon image="shortcut" markupView="lexicon" message="shortcut" />
																	</span>
																</c:when>
																<c:when test="<%= fileEntry.hasLock() || fileEntry.isCheckedOut() %>">
																	<span class="inline-item inline-item-after state-icon">
																		<aui:icon image="lock" markupView="lexicon" message="locked" />
																	</span>
																</c:when>
															</c:choose>

															<c:if test="<%= dlViewFileVersionDisplayContext.isShared() %>">
																<span class="inline-item inline-item-after lfr-portal-tooltip state-icon" title="<%= LanguageUtil.get(request, "shared") %>">
																	<aui:icon image="users" markupView="lexicon" message="shared" />
																</span>
															</c:if>
														</div>
													</div>

													<c:if test="<%= dlPortletInstanceSettingsHelper.isShowActions() %>">
														<div class="autofit-col">
															<clay:dropdown-actions
																aria-label='<%= LanguageUtil.get(request, "actions") %>'
																dropdownItems="<%= dlViewFileVersionDisplayContext.getActionDropdownItems() %>"
																propsTransformer="document_library/js/DLFileEntryDropdownPropsTransformer"
															/>
														</div>
													</c:if>
												</div>
											</div>
										</div>
									</div>
								</liferay-ui:search-container-column-text>
							</c:when>
							<c:otherwise>

								<%
								for (String curEntryColumn : dlViewEntriesDisplayContext.getEntryColumns()) {
								%>

									<c:choose>
										<c:when test='<%= curEntryColumn.equals("name") %>'>
											<liferay-ui:search-container-column-text
												cssClass="table-cell-expand table-cell-minw-200"
												name="title"
											>
												<div class="autofit-row">
													<div class="autofit-col">
														<liferay-document-library:mime-type-sticker
															cssClass="sticker-secondary"
															fileVersion="<%= latestFileVersion %>"
														/>
													</div>

													<div class="autofit-col autofit-col-expand">
														<div class="table-title">
															<aui:a href="<%= dlViewEntriesDisplayContext.getViewFileEntryURL(fileEntry) %>"><%= HtmlUtil.unescape(latestFileVersion.getTitle()) %></aui:a>
														</div>
													</div>
												</div>

												<c:if test="<%= fileEntry.hasLock() || fileEntry.isCheckedOut() %>">
													<span class="inline-item inline-item-after state-icon">
														<aui:icon image="lock" markupView="lexicon" message="locked" />
													</span>
												</c:if>

												<c:if test="<%= dlViewFileVersionDisplayContext.isShared() %>">
													<span class="inline-item inline-item-after lfr-portal-tooltip state-icon" title="<%= LanguageUtil.get(request, "shared") %>">
														<aui:icon image="users" markupView="lexicon" message="shared" />
													</span>
												</c:if>

												<c:if test="<%= fileShortcut != null %>">
													<span class="inline-item inline-item-after state-icon">
														<aui:icon image="shortcut" markupView="lexicon" message="shortcut" />
													</span>
												</c:if>
											</liferay-ui:search-container-column-text>
										</c:when>
										<c:when test='<%= curEntryColumn.equals("description") %>'>
											<liferay-ui:search-container-column-text
												cssClass="table-cell-expand table-cell-minw-200"
												name="description"
												value="<%= StringUtil.shorten(fileEntry.getDescription(), 100) %>"
											/>
										</c:when>
										<c:when test='<%= curEntryColumn.equals("document-type") %>'>
											<c:choose>
												<c:when test="<%= latestFileVersion.getModel() instanceof DLFileVersion %>">

													<%
													DLFileVersion latestDLFileVersion = (DLFileVersion)latestFileVersion.getModel();

													DLFileEntryType dlFileEntryType = latestDLFileVersion.getDLFileEntryType();
													%>

													<liferay-ui:search-container-column-text
														cssClass="table-cell-expand-smaller table-cell-minw-150"
														name="document-type"
														value="<%= HtmlUtil.escape(dlFileEntryType.getName(locale)) %>"
													/>
												</c:when>
												<c:otherwise>
													<liferay-ui:search-container-column-text
														cssClass="table-cell-expand-smaller table-cell-minw-150"
														name="document-type"
														value="--"
													/>
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:when test='<%= curEntryColumn.equals("size") %>'>
											<liferay-ui:search-container-column-text
												cssClass="table-cell-expand-smallest"
												name="size"
												value="<%= LanguageUtil.formatStorageSize(latestFileVersion.getSize(), locale) %>"
											/>
										</c:when>
										<c:when test='<%= curEntryColumn.equals("status") %>'>
											<liferay-ui:search-container-column-status
												cssClass="table-cell-expand-smallest"
												name="status"
												status="<%= latestFileVersion.getStatus() %>"
											/>
										</c:when>
										<c:when test='<%= curEntryColumn.equals("downloads") %>'>
											<c:if test="<%= ViewCountManagerUtil.isViewCountEnabled(PortalUtil.getClassNameId(DLFileEntryConstants.getClassName())) %>">
												<liferay-ui:search-container-column-text
													cssClass="table-cell-expand-smallest"
													name="downloads"
													value="<%= String.valueOf(fileEntry.getReadCount()) %>"
												/>
											</c:if>
										</c:when>
										<c:when test='<%= curEntryColumn.equals("create-date") %>'>
											<liferay-ui:search-container-column-date
												cssClass="table-cell-expand-smallest table-cell-ws-nowrap"
												name="create-date"
												value="<%= fileEntry.getCreateDate() %>"
											/>
										</c:when>
										<c:when test='<%= curEntryColumn.equals("modified-date") %>'>
											<liferay-ui:search-container-column-date
												cssClass="table-cell-expand-smallest table-cell-ws-nowrap"
												name="modified-date"
												value="<%= fileEntry.getModifiedDate() %>"
											/>
										</c:when>
										<c:when test='<%= curEntryColumn.equals("action") %>'>
											<c:if test="<%= dlPortletInstanceSettingsHelper.isShowActions() %>">
												<liferay-ui:search-container-column-jsp
													path="/document_library/file_entry_action.jsp"
												/>
											</c:if>
										</c:when>
									</c:choose>

								<%
								}
								%>

							</c:otherwise>
						</c:choose>
					</c:when>
					<c:when test="<%= (curFolder != null) && DLFolderPermission.contains(permissionChecker, curFolder, ActionKeys.VIEW) %>">

						<%
						if ((dlSearchContainer.getRowChecker() == null) && (DLFolderPermission.contains(permissionChecker, curFolder, ActionKeys.DELETE) || DLFolderPermission.contains(permissionChecker, curFolder, ActionKeys.UPDATE))) {
							dlSearchContainer.setRowChecker(entriesChecker);
						}

						row.setData(
							HashMapBuilder.<String, Object>put(
								"actions", StringUtil.merge(dlViewEntriesDisplayContext.getAvailableActions(curFolder))
							).put(
								"draggable", dlViewEntriesDisplayContext.isDraggable(curFolder)
							).put(
								"folder", true
							).put(
								"folder-id", curFolder.getFolderId()
							).put(
								"title", HtmlUtil.unescape(curFolder.getName())
							).build());

						row.setPrimaryKey(String.valueOf(curFolder.getPrimaryKey()));
						%>

						<c:choose>
							<c:when test="<%= dlViewEntriesDisplayContext.isDescriptiveDisplayStyle() %>">
								<liferay-ui:search-container-column-icon
									icon='<%= curFolder.isMountPoint() ? "repository" : "folder" %>'
									toggleRowChecker="<%= true %>"
								/>

								<liferay-ui:search-container-column-jsp
									colspan="<%= 2 %>"
									path="/document_library/view_folder_descriptive.jsp"
								/>

								<c:if test="<%= dlPortletInstanceSettingsHelper.isShowActions() %>">
									<liferay-ui:search-container-column-jsp
										path="/document_library/folder_action.jsp"
									/>
								</c:if>
							</c:when>
							<c:when test="<%= dlViewEntriesDisplayContext.isIconDisplayStyle() %>">

								<%
								row.setCssClass("card-page-item card-page-item-directory");

								String viewFolderURL = PortletURLBuilder.createRenderURL(
									liferayPortletResponse
								).setMVCRenderCommandName(
									"/document_library/view_folder"
								).setRedirect(
									currentURL
								).setParameter(
									"folderId", curFolder.getFolderId()
								).buildString();

								request.setAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW, row);
								%>

								<liferay-ui:search-container-column-text
									colspan="<%= 2 %>"
								>
									<clay:horizontal-card
										horizontalCard="<%= new FolderHorizontalCard(dlPortletInstanceSettingsHelper, dlTrashHelper, curFolder, request, renderResponse, searchContainer.getRowChecker(), viewFolderURL) %>"
										propsTransformer="document_library/js/DLFolderDropdownPropsTransformer"
									/>
								</liferay-ui:search-container-column-text>
							</c:when>
							<c:otherwise>

								<%
								for (String curEntryColumn : dlViewEntriesDisplayContext.getEntryColumns()) {
								%>

									<c:choose>
										<c:when test='<%= curEntryColumn.equals("name") %>'>
											<liferay-ui:search-container-column-text
												cssClass="table-cell-expand table-cell-minw-200"
												name="name"
											>
												<div class="autofit-row">
													<div class="autofit-col">
														<clay:sticker
															cssClass="sticker-document"
															displayType="secondary"
															icon='<%= curFolder.isMountPoint() ? "repository" : "folder" %>'
														/>
													</div>

													<div class="autofit-col autofit-col-expand">
														<div class="table-title">
															<aui:a
																href='<%=
																	PortletURLBuilder.createRenderURL(
																		liferayPortletResponse
																	).setMVCRenderCommandName(
																		"/document_library/view_folder"
																	).setRedirect(
																		currentURL
																	).setParameter(
																		"folderId", curFolder.getFolderId()
																	).buildString()
																%>'
															>
																<%= HtmlUtil.unescape(curFolder.getName()) %>
															</aui:a>
														</div>
													</div>
												</div>
											</liferay-ui:search-container-column-text>
										</c:when>
										<c:when test='<%= curEntryColumn.equals("description") %>'>
											<liferay-ui:search-container-column-text
												cssClass="table-cell-expand table-cell-minw-200"
												name="description"
												value="<%= StringUtil.shorten(curFolder.getDescription(), 100) %>"
											/>
										</c:when>
										<c:when test='<%= curEntryColumn.equals("document-type") %>'>
											<liferay-ui:search-container-column-text
												cssClass="table-cell-expand-smaller"
												name="document-type"
												value="--"
											/>
										</c:when>
										<c:when test='<%= curEntryColumn.equals("size") %>'>
											<liferay-ui:search-container-column-text
												cssClass="table-cell-expand-smallest"
												name="size"
												value="--"
											/>
										</c:when>
										<c:when test='<%= curEntryColumn.equals("status") %>'>
											<liferay-ui:search-container-column-text
												cssClass="table-cell-expand-smallest"
												name="status"
												value="--"
											/>
										</c:when>
										<c:when test='<%= curEntryColumn.equals("downloads") %>'>
											<liferay-ui:search-container-column-text
												cssClass="table-cell-expand-smallest"
												name="downloads"
												value="--"
											/>
										</c:when>
										<c:when test='<%= curEntryColumn.equals("create-date") %>'>
											<liferay-ui:search-container-column-date
												cssClass="table-cell-expand-smallest table-cell-ws-nowrap"
												name="create-date"
												value="<%= curFolder.getCreateDate() %>"
											/>
										</c:when>
										<c:when test='<%= curEntryColumn.equals("modified-date") %>'>
											<liferay-ui:search-container-column-date
												cssClass="table-cell-expand-smallest table-cell-ws-nowrap"
												name="modified-date"
												value="<%= curFolder.getModifiedDate() %>"
											/>
										</c:when>
										<c:when test='<%= curEntryColumn.equals("action") %>'>
											<c:if test="<%= dlPortletInstanceSettingsHelper.isShowActions() %>">
												<liferay-ui:search-container-column-jsp
													path="/document_library/folder_action.jsp"
												/>
											</c:if>
										</c:when>
									</c:choose>

								<%
								}
								%>

							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<liferay-ui:search-container-column-icon
							icon="minus-circle"
						/>
					</c:otherwise>
				</c:choose>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				displayStyle="<%= dlViewEntriesDisplayContext.getDisplayStyle() %>"
				markupView="lexicon"
				resultRowSplitter="<%= new DLResultRowSplitter() %>"
			/>
		</liferay-ui:search-container>
	</div>
</liferay-util:buffer>

<div class="repository-search-results" data-repositoryId="<%= searchRepositoryId %>" id="<portlet:namespace />searchResultsContainer<%= searchRepositoryId %>">
	<%= searchResults %>
</div>

<%
request.setAttribute("view.jsp-folderId", String.valueOf(folderId));
%>