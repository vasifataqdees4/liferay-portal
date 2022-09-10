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

import ClayLayout from '@clayui/layout';
import ProjectList from './components/ProjectsList';
import SearchHeader from './components/SearchHeader';
import useHasManyProjects from './hooks/useHasManyProjects';
import useKoroneikiAccounts from './hooks/useKoroneikiAccounts';

import './app.scss';

const THRESHOLD_COUNT = 4;

const Home = () => {
	const {data, fetchMore, fetching, loading, search} = useKoroneikiAccounts();
	const koroneikiAccounts = data?.c?.koroneikiAccounts;

	const hasManyProjects = useHasManyProjects(
		koroneikiAccounts?.totalCount,
		THRESHOLD_COUNT
	);

	return (
		<ClayLayout.ContainerFluid size={hasManyProjects ? 'md' : 'xl'}>
			<ClayLayout.Row>
				<ClayLayout.Col>
					{hasManyProjects && (
						<SearchHeader
							count={koroneikiAccounts?.totalCount}
							loading={loading}
							onSearchSubmit={(term) => search(term)}
						/>
					)}

					<ProjectList
						compressed={hasManyProjects}
						fetching={fetching}
						koroneikiAccounts={koroneikiAccounts}
						loading={loading}
						maxCardsLoading={THRESHOLD_COUNT}
						onIntersect={(currentPage) =>
							fetchMore({
								variables: {
									page: currentPage + 1,
								},
							})
						}
					/>
				</ClayLayout.Col>
			</ClayLayout.Row>
		</ClayLayout.ContainerFluid>
	);
};

export default Home;
