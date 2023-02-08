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

import {useQuery} from 'react-query';

import fetchProducts from '../queries/fetchProducts';

const getProducts = (_, languageId, page) => {
	const queryValues = {
		params: {
			_,
			languageId,
			page,
			pageSize: 10,
		},
	};

	return fetchProducts(queryValues, languageId);
};

export default function useProducts(languageId, page) {
	return useQuery(['Products', languageId, page, 10], getProducts, {
		keepPreviousData: true,
	});
}
