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

import {useRef} from 'react';

import useFormActions from '../../../hooks/useFormActions';
import useFormModal from '../../../hooks/useFormModal';
import useModalContext from '../../../hooks/useModalContext';
import useMutate from '../../../hooks/useMutate';
import i18n from '../../../i18n';
import {Liferay} from '../../../services/liferay';
import {TestraySubTask} from '../../../services/rest';
import {testraySubTaskImpl} from '../../../services/rest/TestraySubtask';
import {Action} from '../../../types';
import {SubTaskStatuses} from '../../../util/statuses';
import {UserListView} from '../../Manage/User';

const useSubtasksActions = () => {
	const {form} = useFormActions();
	const {updateItemFromList} = useMutate();
	const {onOpenModal, state} = useModalContext();
	const {modal: completeModal} = useFormModal();

	const actionsRef = useRef([
		{
			action: (subtask, mutate) => {
				testraySubTaskImpl.assignToMe(subtask).then(() => {
					updateItemFromList(
						mutate,
						0,
						{},
						{
							revalidate: true,
						}
					);
				});
			},
			hidden: ({dueStatus}) =>
				dueStatus?.key === SubTaskStatuses.IN_ANALYSIS,
			icon: 'user',
			name: i18n.sub('assign-to-me-and-x', 'begin-analysis'),
		},
		{
			action: (subtask, mutate) =>
				onOpenModal({
					body: (
						<UserListView
							listViewProps={{
								managementToolbarProps: {
									addButton: undefined,
									display: {columns: false},
								},
							}}
							tableProps={{
								onClickRow: (user) => {
									testraySubTaskImpl
										.assignTo(subtask, user.id)
										.then(() =>
											updateItemFromList(
												mutate,
												subtask.id,
												{user},
												{revalidate: true}
											)
										)
										.then(form.onSuccess)
										.catch(form.onError)
										.finally(state.onClose);
								},
							}}
						/>
					),
					size: 'lg',
					title: i18n.translate('users'),
				}),
			icon: 'user',
			name: ({dueStatus}) => {
				if (dueStatus.key === SubTaskStatuses.IN_ANALYSIS) {
					return i18n.translate('assign');
				}

				if (dueStatus.key === SubTaskStatuses.OPEN) {
					return i18n.translate('assign-and-begin-analysis');
				}
			},
		},
		{
			action: (subtask) => completeModal.open(subtask),
			hidden: ({dueStatus, user}) =>
				user?.id !== Number(Liferay.ThemeDisplay.getUserId()) ||
				dueStatus.key !== SubTaskStatuses.IN_ANALYSIS,
			icon: 'polls',
			name: i18n.sub('complete-x', ''),
		},
	] as Action<TestraySubTask>[]);

	return {
		actions: actionsRef.current,
		completeModal,
		form,
	};
};

export default useSubtasksActions;
