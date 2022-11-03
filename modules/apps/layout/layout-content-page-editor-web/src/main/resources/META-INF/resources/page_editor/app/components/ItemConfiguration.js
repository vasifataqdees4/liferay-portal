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

import ClayAlert from '@clayui/alert';
import ClayTabs from '@clayui/tabs';
import PropTypes from 'prop-types';
import React, {useEffect, useMemo, useState} from 'react';

import {useId} from '../../core/hooks/useId';
import {
	PANELS,
	selectPanels,
} from '../../plugins/browser/components/page-structure/selectors/selectPanels';
import {useCollectionActiveItemContext} from '../contexts/CollectionActiveItemContext';
import {CollectionItemContext} from '../contexts/CollectionItemContext';
import {useSelector, useSelectorCallback} from '../contexts/StoreContext';
import selectCanViewItemConfiguration from '../selectors/selectCanViewItemConfiguration';
import {deepEqual} from '../utils/checkDeepEqual';

export default function ItemConfiguration({activeItemId, activeItemType}) {
	const collectionContext = useCollectionActiveItemContext();

	const canViewItemConfiguration = useSelector(
		selectCanViewItemConfiguration
	);

	const [activePanel, setActivePanel] = useState({});

	return canViewItemConfiguration ? (
		<CollectionItemContext.Provider value={collectionContext}>
			<ItemConfigurationContent
				activeItemId={activeItemId}
				activeItemType={activeItemType}
				activePanel={activePanel}
				setActivePanel={setActivePanel}
			/>
		</CollectionItemContext.Provider>
	) : null;
}

function ItemConfigurationContent({
	activeItemId,
	activeItemType,
	activePanel,
	setActivePanel,
}) {
	const tabIdPrefix = useId();
	const panelIdPrefix = useId();

	const {activeItem, panelsIds} = useSelectorCallback(
		(state) => selectPanels(activeItemId, activeItemType, state),
		[activeItemId, activeItemType],
		deepEqual
	);

	const panels = useMemo(
		() =>
			Object.entries(panelsIds)
				.filter(([, show]) => show)
				.map(([key]) => ({...PANELS[key], panelId: key}))
				.sort((panelA, panelB) => panelB.priority - panelA.priority),
		[panelsIds]
	);

	useEffect(() => {
		if (
			!panels.length ||
			panels.some((panel) => panel.panelId === activePanel.id)
		) {
			return;
		}

		let nextActivePanelId = activePanel.id;
		let nextActivePanelType = activePanel.type;

		const sameTypePanel = panels.find(
			(panel) => panel.type === activePanel.type
		);

		if (sameTypePanel) {
			nextActivePanelId = sameTypePanel.panelId;
		}
		else {
			nextActivePanelId = panels[0]?.panelId;
			nextActivePanelType = panels[0]?.type;
		}

		setActivePanel({id: nextActivePanelId, type: nextActivePanelType});
	}, [panels, activePanel, setActivePanel]);

	return (
		<div className="page-editor__page-structure__item-configuration">
			{!panels.length ? (
				<ClayAlert
					className="m-3"
					displayType="info"
					title={Liferay.Language.get('info')}
				>
					{Liferay.Language.get(
						'this-page-element-does-not-have-any-available-configurations'
					)}
				</ClayAlert>
			) : (
				<>
					<ClayTabs
						activation="automatic"
						active={panels.findIndex(
							(panel) => panel.panelId === activePanel.id
						)}
						className="flex-nowrap pt-2 px-3"
						onActiveChange={(activeIndex) => {
							const panel = panels[activeIndex];

							setActivePanel({
								id: panel.panelId,
								type: panel.type || null,
							});
						}}
					>
						{panels.map((panel) => (
							<ClayTabs.Item
								innerProps={{
									'aria-controls': `${panelIdPrefix}-${panel.panelId}`,
									'id': `${tabIdPrefix}-${panel.panelId}`,
								}}
								key={panel.panelId}
							>
								<span
									className="c-inner page-editor__page-structure__item-configuration-tab text-truncate"
									data-tooltip-align="top"
									tabIndex="-1"
									title={panel.label}
								>
									{panel.label}
								</span>
							</ClayTabs.Item>
						))}
					</ClayTabs>

					<ClayTabs.Content
						activeIndex={panels.findIndex(
							(panel) => panel.panelId === activePanel.id
						)}
					>
						{panels.map((panel) => (
							<ClayTabs.TabPane
								aria-labelledby={`${tabIdPrefix}-${panel.panelId}`}
								className="pb-3 pt-4 px-3"
								id={`${panelIdPrefix}-${panel.panelId}`}
								key={panel.panelId}
							>
								{panel.panelId === activePanel.id && (
									<ItemConfigurationComponent
										Component={panel.component}
										item={activeItem}
									/>
								)}
							</ClayTabs.TabPane>
						))}
					</ClayTabs.Content>
				</>
			)}
		</div>
	);
}

class ItemConfigurationComponent extends React.Component {
	static getDerivedStateFromError(error) {
		return {error};
	}

	constructor(props) {
		super(props);

		this.state = {
			error: null,
		};
	}

	render() {
		const {Component, item} = this.props;

		return this.state.error ? (
			<ClayAlert
				displayType="danger"
				title={Liferay.Language.get('error')}
			>
				{Liferay.Language.get(
					'an-unexpected-error-occurred-while-rendering-this-item'
				)}
			</ClayAlert>
		) : (
			<Component item={item} key={item.itemId} />
		);
	}
}

ItemConfigurationComponent.propTypes = {
	Component: PropTypes.func.isRequired,
	item: PropTypes.object.isRequired,
};
