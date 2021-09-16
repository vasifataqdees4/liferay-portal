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

import ClayButton from '@clayui/button';
import {ClayIconSpriteContext} from '@clayui/icon';
import ClayTabs from '@clayui/tabs';
import React, {useContext, useEffect, useState} from 'react';

import {TabsVisitor} from '../../utils/visitor';
import SidePanelContent from '../SidePanelContent';
import LayoutScreen from './LayoutScreen/LayoutScreen';
import LayoutContext, {LayoutContextProvider, TYPES} from './context';
import {
	TObjectField,
	TObjectLayoutColumn,
	TObjectLayoutTab,
	TObjectRelationship,
} from './types';

const TABS = [
	{
		Component: LayoutScreen,
		label: Liferay.Language.get('layout'),
	},
];

const HEADERS = new Headers({
	Accept: 'application/json',
	'Content-Type': 'application/json',
});

type TNormalizeObjectFields = ({
	objectFields,
	objectLayoutTabs,
}: {
	objectFields: TObjectField[];
	objectLayoutTabs: TObjectLayoutTab[];
}) => TObjectField[];

const normalizeObjectFields: TNormalizeObjectFields = ({
	objectFields,
	objectLayoutTabs,
}) => {
	const visitor = new TabsVisitor(objectLayoutTabs);
	const objectFieldIds = objectFields.map(({id}) => id);

	const normalizedObjectFields = [...objectFields];

	visitor.mapTabs(({objectFieldId}: TObjectLayoutColumn) => {
		const objectFieldIndex = objectFieldIds.indexOf(objectFieldId);

		normalizedObjectFields[objectFieldIndex].inLayout = true;
	});

	return normalizedObjectFields;
};

type TNormalizeObjectRelationships = ({
	objectLayoutTabs,
	objectRelationships,
}: {
	objectLayoutTabs: TObjectLayoutTab[];
	objectRelationships: TObjectRelationship[];
}) => TObjectRelationship[];

const normalizeObjectRelationships: TNormalizeObjectRelationships = ({
	objectLayoutTabs,
	objectRelationships,
}) => {
	const objectRelationshipIds = objectRelationships.map(({id}) => id);

	const normalizedObjectRelationships = [...objectRelationships];

	objectLayoutTabs.forEach(({objectRelationshipId}) => {
		if (objectRelationshipId) {
			const objectRelationshipIndex = objectRelationshipIds.indexOf(
				objectRelationshipId
			);

			normalizedObjectRelationships[
				objectRelationshipIndex
			].inLayout = true;
		}
	});

	return normalizedObjectRelationships;
};

const Layout: React.FC<React.HTMLAttributes<HTMLElement>> = () => {
	const [{objectLayout, objectLayoutId}, dispatch] = useContext(
		LayoutContext
	);
	const [activeIndex, setActiveIndex] = useState<number>(0);

	useEffect(() => {
		const makeFetch = async () => {
			const objectLayoutResponse = await Liferay.Util.fetch(
				`/o/object-admin/v1.0/object-layouts/${objectLayoutId}`,
				{
					headers: HEADERS,
					method: 'GET',
				}
			);

			const {
				defaultObjectLayout,
				name,
				objectDefinitionId,
				objectLayoutTabs,
			} = await objectLayoutResponse.json();

			const objectFieldsResponse = await Liferay.Util.fetch(
				`/o/object-admin/v1.0/object-definitions/${objectDefinitionId}/object-fields`,
				{
					headers: HEADERS,
					method: 'GET',
				}
			);

			const objectRelationshipsResponse = await Liferay.Util.fetch(
				`/o/object-admin/v1.0/object-definitions/${objectDefinitionId}/object-relationships`,
				{
					headers: HEADERS,
					method: 'GET',
				}
			);

			dispatch({
				payload: {
					objectLayout: {
						defaultObjectLayout,
						name,
						objectLayoutTabs,
					},
				},
				type: TYPES.ADD_OBJECT_LAYOUT,
			});

			const {
				items: objectFields,
			}: {items: TObjectField[]} = await objectFieldsResponse.json();

			dispatch({
				payload: {
					objectFields: normalizeObjectFields({
						objectFields,
						objectLayoutTabs,
					}),
				},
				type: TYPES.ADD_OBJECT_FIELDS,
			});

			const {
				items: objectRelationships,
			}: {
				items: TObjectRelationship[];
			} = await objectRelationshipsResponse.json();

			dispatch({
				payload: {
					objectRelationships: normalizeObjectRelationships({
						objectLayoutTabs,
						objectRelationships,
					}),
				},
				type: TYPES.ADD_OBJECT_RELATIONSHIPS,
			});
		};

		makeFetch();
	}, [objectLayoutId, dispatch]);

	const saveObjectLayout = async () => {
		const response = await Liferay.Util.fetch(
			`/o/object-admin/v1.0/object-layouts/${objectLayoutId}`,
			{
				body: JSON.stringify(objectLayout),
				headers: HEADERS,
				method: 'PUT',
			}
		);

		if (response.status === 401) {
			window.location.reload();
		}
		else if (response.ok) {
			Liferay.Util.openToast({
				message: Liferay.Language.get(
					'the-object-layout-was-updated-successfully'
				),
				type: 'success',
			});

			setTimeout(() => {
				const parentWindow = Liferay.Util.getOpener();
				parentWindow.Liferay.fire('close-side-panel');
			}, 1500);
		}
		else {
			const {
				title = Liferay.Language.get('an-error-occurred'),
			} = await response.json();

			Liferay.Util.openToast({
				message: title,
				type: 'danger',
			});
		}
	};

	return (
		<>
			<ClayTabs className="side-panel-iframe__tabs">
				{TABS.map(({label}, index) => (
					<ClayTabs.Item
						active={activeIndex === index}
						key={index}
						onClick={() => setActiveIndex(index)}
					>
						{label}
					</ClayTabs.Item>
				))}
			</ClayTabs>

			<SidePanelContent>
				<SidePanelContent.Body>
					<ClayTabs.Content activeIndex={activeIndex} fade>
						{TABS.map(({Component}, index) => (
							<ClayTabs.TabPane key={index}>
								<Component />
							</ClayTabs.TabPane>
						))}
					</ClayTabs.Content>
				</SidePanelContent.Body>

				<SidePanelContent.Footer>
					<ClayButton.Group spaced>
						<ClayButton
							className="btn-cancel"
							displayType="secondary"
						>
							{Liferay.Language.get('cancel')}
						</ClayButton>
						<ClayButton onClick={() => saveObjectLayout()}>
							{Liferay.Language.get('save')}
						</ClayButton>
					</ClayButton.Group>
				</SidePanelContent.Footer>
			</SidePanelContent>
		</>
	);
};

interface ILayoutWrapperProps extends React.HTMLAttributes<HTMLElement> {
	objectLayoutId: string;
	spritemap: string;
}

const LayoutWrapper: React.FC<ILayoutWrapperProps> = ({
	objectLayoutId,
	spritemap,
}) => {
	return (
		<ClayIconSpriteContext.Provider value={spritemap}>
			<LayoutContextProvider value={{objectLayoutId}}>
				<Layout />
			</LayoutContextProvider>
		</ClayIconSpriteContext.Provider>
	);
};

export default LayoutWrapper;
