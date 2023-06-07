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

package com.liferay.document.library.kernel.store;

import com.liferay.petra.function.UnsafeRunnable;
import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Adolfo Pérez
 */
public enum StoreArea {

	DELETED("_deleted"), LIVE(StringPool.BLANK), NEW("_new");

	public static String getCurrentStoreAreaPath(
		long companyId, long repositoryId, String... path) {

		StoreArea storeArea = _storeAreaThreadLocal.get();

		return storeArea.getPath(companyId, repositoryId, path);
	}

	public static <E extends Exception> String[] mergeWithStoreAreas(
			UnsafeSupplier<String[], E> unsafeSupplier,
			StoreArea... storeAreas)
		throws E {

		Exception exception1 = null;
		int failureCount = 0;

		List<String> list = new ArrayList<>();

		for (StoreArea storeArea : storeAreas) {
			try {
				String[] strings = StoreArea.withStoreArea(
					storeArea, unsafeSupplier);

				if (strings != null) {
					Collections.addAll(list, strings);
				}
			}
			catch (Exception exception2) {
				failureCount++;

				if (exception1 == null) {
					exception1 = exception2;
				}
			}
		}

		if (failureCount == storeAreas.length) {
			return ReflectionUtil.throwException(exception1);
		}

		return list.toArray(new String[0]);
	}

	public static <T extends Exception> void runWithStoreAreas(
			UnsafeRunnable<T> unsafeRunnable, StoreArea... storeAreas)
		throws T {

		Exception exception1 = null;
		int failureCount = 0;

		for (StoreArea storeArea : storeAreas) {
			try {
				StoreArea.withStoreArea(storeArea, unsafeRunnable);
			}
			catch (Exception exception2) {
				failureCount++;

				if (exception1 == null) {
					exception1 = exception2;
				}
			}
		}

		if (failureCount == storeAreas.length) {
			ReflectionUtil.throwException(exception1);
		}
	}

	public static <T, E extends Exception> T tryGetWithStoreAreas(
			UnsafeSupplier<T, E> unsafeSupplier, Predicate<T> predicate,
			T defaultValue, StoreArea... storeAreas)
		throws E {

		Exception exception1 = null;

		for (StoreArea storeArea : storeAreas) {
			try {
				T result = StoreArea.withStoreArea(storeArea, unsafeSupplier);

				if (predicate.test(result)) {
					return result;
				}
			}
			catch (Exception exception2) {
				if (exception1 == null) {
					exception1 = exception2;
				}
			}
		}

		if (exception1 != null) {
			return ReflectionUtil.throwException(exception1);
		}

		return defaultValue;
	}

	public static StoreArea tryRunWithStoreAreas(
		Predicate<StoreArea> predicate, StoreArea... storeAreas) {

		Exception exception1 = null;

		for (StoreArea storeArea : storeAreas) {
			try {
				if (predicate.test(storeArea)) {
					return storeArea;
				}
			}
			catch (Exception exception2) {
				if (exception1 == null) {
					exception1 = exception2;
				}
			}
		}

		if (exception1 != null) {
			return ReflectionUtil.throwException(exception1);
		}

		return null;
	}

	public static <T extends Throwable> void withStoreArea(
			StoreArea storeArea, UnsafeRunnable<T> unsafeRunnable)
		throws T {

		StoreArea.withStoreArea(
			storeArea,
			() -> {
				unsafeRunnable.run();

				return null;
			});
	}

	public static <T, E extends Throwable> T withStoreArea(
			StoreArea storeArea, UnsafeSupplier<T, E> unsafeSupplier)
		throws E {

		StoreArea oldStoreArea = _storeAreaThreadLocal.get();

		try {
			_storeAreaThreadLocal.set(storeArea);

			return unsafeSupplier.get();
		}
		finally {
			_storeAreaThreadLocal.set(oldStoreArea);
		}
	}

	public String getPath(long companyId) {
		return StringBundler.concat(
			_namespace, StringPool.SLASH, String.valueOf(companyId));
	}

	public String getPath(long companyId, long repositoryId, String... path) {
		StringBundler sb = new StringBundler(
			5 + (ArrayUtil.getLength(path) * 2));

		sb.append(_namespace);
		sb.append(StringPool.SLASH);
		sb.append(String.valueOf(companyId));
		sb.append(StringPool.SLASH);
		sb.append(String.valueOf(repositoryId));

		if (ArrayUtil.isNotEmpty(path)) {
			sb.append(StringPool.SLASH);
			sb.append(StringUtil.merge(path, StringPool.SLASH));
		}

		return sb.toString();
	}

	private StoreArea(String namespace) {
		_namespace = namespace;
	}

	private static final ThreadLocal<StoreArea> _storeAreaThreadLocal =
		new CentralizedThreadLocal<>(
			StoreArea.class.getName() + "._storeAreaThreadLocal", () -> LIVE);

	private final String _namespace;

}