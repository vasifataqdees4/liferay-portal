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

import ServiceProvider from 'commerce-frontend-js/ServiceProvider/index';
import itemFinder from 'commerce-frontend-js/components/item_finder/entry';
import {FDS_UPDATE_DISPLAY} from 'commerce-frontend-js/utilities/eventsDefinitions';
import {openToast} from 'frontend-js-web';

export default function ({
	commerceOrderTypeId,
	dataSetId,
	orderTypeExternalReferenceCode,
	rootPortletId,
}) {
	const CommerceOrderTypeChannelsResource = ServiceProvider.AdminOrderAPI(
		'v1'
	);

	function selectItem(channel) {
		const channelData = {
			channelExternalReferenceCode: channel.externalReferenceCode,
			channelId: channel.id,
			orderTypeExternalReferenceCode,
			orderTypeId: commerceOrderTypeId,
		};

		return CommerceOrderTypeChannelsResource.addOrderTypeChannel(
			commerceOrderTypeId,
			channelData
		)
			.then(() => {
				Liferay.fire(FDS_UPDATE_DISPLAY, {
					id: dataSetId,
				});
			})
			.catch((error) => {
				openToast({
					message: error.title,
					type: 'danger',
				});
			});
	}

	itemFinder('itemFinder', 'item-finder-root-channel', {
		apiUrl: '/o/headless-commerce-admin-channel/v1.0/channels',
		getSelectedItems: () => Promise.resolve([]),
		inputPlaceholder: Liferay.Language.get('find-a-channel'),
		itemCreation: false,
		itemSelectedMessage: Liferay.Language.get('channel-selected'),
		itemsKey: 'id',
		linkedDataSetsId: [dataSetId],
		onItemSelected: selectItem,
		pageSize: 10,
		panelHeaderLabel: Liferay.Language.get('add-channels'),
		portletId: rootPortletId,
		schema: [
			{
				fieldName: 'name',
			},
		],
		titleLabel: Liferay.Language.get('add-existing-channel'),
	});
}
