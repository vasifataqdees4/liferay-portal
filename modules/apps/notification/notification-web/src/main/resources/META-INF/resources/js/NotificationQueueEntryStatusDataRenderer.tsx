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

import classNames from 'classnames';
import React from 'react';

export function NotificationQueueEntryStatusDataRenderer({value}: IProps) {
	const getStatusInfo = (status: number) => {
		switch (status) {
			case 0:
				return {
					className: 'label-danger',
					label: Liferay.Language.get('failed'),
				};
			case 1:
				return {
					className: 'label-success',
					label: Liferay.Language.get('sent'),
				};
			case 2:
				return {
					className: 'label-warning',
					label: Liferay.Language.get('unsent'),
				};
			default:
				return null;
		}
	};

	const statusInfo =
		typeof value === 'number' && Liferay.FeatureFlags['LPS-159052']
			? getStatusInfo(value)
			: null;

	return statusInfo ? (
		<strong className={`label ${statusInfo.className}`}>
			{statusInfo.label}
		</strong>
	) : (
		<strong
			className={classNames('label', {
				'label-danger': !value,
				'label-success': value,
			})}
		>
			{value
				? Liferay.Language.get('sent')
				: Liferay.Language.get('unsent')}
		</strong>
	);
}

interface IProps {
	value: boolean | number;
}
