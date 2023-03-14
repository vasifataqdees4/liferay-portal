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

import {ClayCheckbox} from '@clayui/form';
import React from 'react';

export default function FragmentServiceConfiguration({
	namespace,
	propagateChanges,
	propagateContributedFragmentChanges,
}) {
	return (
		<>
			<ClayCheckbox
				defaultChecked={propagateContributedFragmentChanges}
				label={Liferay.Language.get(
					'propagate-contributed-fragment-changes-automatically'
				)}
				name={`${namespace}propagateContributedFragmentChanges`}
			/>

			<div aria-hidden="true" className="form-feedback-group mb-3">
				<div className="form-text text-weight-normal">
					{Liferay.Language.get(
						'propagate-contributed-fragment-changes-automatically-description'
					)}
				</div>
			</div>

			<ClayCheckbox
				defaultChecked={propagateChanges}
				label={Liferay.Language.get(
					'propagate-fragment-changes-automatically'
				)}
				name={`${namespace}propagateChanges`}
			/>

			<div aria-hidden="true" className="form-feedback-group">
				<div className="form-text text-weight-normal">
					{Liferay.Language.get(
						'propagate-fragment-changes-automatically-description'
					)}
				</div>
			</div>
		</>
	);
}
