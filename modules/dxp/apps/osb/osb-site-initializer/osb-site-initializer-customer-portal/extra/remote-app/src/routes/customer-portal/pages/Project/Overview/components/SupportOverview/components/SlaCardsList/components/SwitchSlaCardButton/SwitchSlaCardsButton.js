/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import {ClayButtonWithIcon} from '@clayui/button';
import React from 'react';

const SwitchSlaCardsButton = ({handleClick}) => (
	<ClayButtonWithIcon
		className="bg-white d-none position-absolute rounded-circle shadow-lg"
		displayType="primary"
		onClick={handleClick}
		outline
		symbol="angle-right"
	/>
);
export default SwitchSlaCardsButton;
