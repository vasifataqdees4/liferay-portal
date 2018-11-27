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

package com.liferay.asset.list.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.asset.list.exception.NoSuchEntryAssetEntryRelException;
import com.liferay.asset.list.model.AssetListEntryAssetEntryRel;
import com.liferay.asset.list.model.impl.AssetListEntryAssetEntryRelImpl;
import com.liferay.asset.list.model.impl.AssetListEntryAssetEntryRelModelImpl;
import com.liferay.asset.list.service.persistence.AssetListEntryAssetEntryRelPersistence;

import com.liferay.petra.string.StringBundler;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the asset list entry asset entry rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetListEntryAssetEntryRelPersistence
 * @see com.liferay.asset.list.service.persistence.AssetListEntryAssetEntryRelUtil
 * @generated
 */
@ProviderType
public class AssetListEntryAssetEntryRelPersistenceImpl
	extends BasePersistenceImpl<AssetListEntryAssetEntryRel>
	implements AssetListEntryAssetEntryRelPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AssetListEntryAssetEntryRelUtil} to access the asset list entry asset entry rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AssetListEntryAssetEntryRelImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AssetListEntryAssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetListEntryAssetEntryRelModelImpl.FINDER_CACHE_ENABLED,
			AssetListEntryAssetEntryRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AssetListEntryAssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetListEntryAssetEntryRelModelImpl.FINDER_CACHE_ENABLED,
			AssetListEntryAssetEntryRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AssetListEntryAssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetListEntryAssetEntryRelModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(AssetListEntryAssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetListEntryAssetEntryRelModelImpl.FINDER_CACHE_ENABLED,
			AssetListEntryAssetEntryRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(AssetListEntryAssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetListEntryAssetEntryRelModelImpl.FINDER_CACHE_ENABLED,
			AssetListEntryAssetEntryRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			AssetListEntryAssetEntryRelModelImpl.UUID_COLUMN_BITMASK |
			AssetListEntryAssetEntryRelModelImpl.POSITION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(AssetListEntryAssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetListEntryAssetEntryRelModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUuid", new String[] { String.class.getName() });

	/**
	 * Returns all the asset list entry asset entry rels where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching asset list entry asset entry rels
	 */
	@Override
	public List<AssetListEntryAssetEntryRel> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset list entry asset entry rels where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetListEntryAssetEntryRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @return the range of matching asset list entry asset entry rels
	 */
	@Override
	public List<AssetListEntryAssetEntryRel> findByUuid(String uuid, int start,
		int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset list entry asset entry rels where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetListEntryAssetEntryRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset list entry asset entry rels
	 */
	@Override
	public List<AssetListEntryAssetEntryRel> findByUuid(String uuid, int start,
		int end,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the asset list entry asset entry rels where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetListEntryAssetEntryRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset list entry asset entry rels
	 */
	@Override
	public List<AssetListEntryAssetEntryRel> findByUuid(String uuid, int start,
		int end,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator,
		boolean retrieveFromCache) {
		uuid = Objects.toString(uuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<AssetListEntryAssetEntryRel> list = null;

		if (retrieveFromCache) {
			list = (List<AssetListEntryAssetEntryRel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetListEntryAssetEntryRel assetListEntryAssetEntryRel : list) {
					if (!uuid.equals(assetListEntryAssetEntryRel.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ASSETLISTENTRYASSETENTRYREL_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetListEntryAssetEntryRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<AssetListEntryAssetEntryRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetListEntryAssetEntryRel>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first asset list entry asset entry rel in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a matching asset list entry asset entry rel could not be found
	 */
	@Override
	public AssetListEntryAssetEntryRel findByUuid_First(String uuid,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator)
		throws NoSuchEntryAssetEntryRelException {
		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel = fetchByUuid_First(uuid,
				orderByComparator);

		if (assetListEntryAssetEntryRel != null) {
			return assetListEntryAssetEntryRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchEntryAssetEntryRelException(msg.toString());
	}

	/**
	 * Returns the first asset list entry asset entry rel in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry asset entry rel, or <code>null</code> if a matching asset list entry asset entry rel could not be found
	 */
	@Override
	public AssetListEntryAssetEntryRel fetchByUuid_First(String uuid,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator) {
		List<AssetListEntryAssetEntryRel> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset list entry asset entry rel in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a matching asset list entry asset entry rel could not be found
	 */
	@Override
	public AssetListEntryAssetEntryRel findByUuid_Last(String uuid,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator)
		throws NoSuchEntryAssetEntryRelException {
		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel = fetchByUuid_Last(uuid,
				orderByComparator);

		if (assetListEntryAssetEntryRel != null) {
			return assetListEntryAssetEntryRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchEntryAssetEntryRelException(msg.toString());
	}

	/**
	 * Returns the last asset list entry asset entry rel in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry asset entry rel, or <code>null</code> if a matching asset list entry asset entry rel could not be found
	 */
	@Override
	public AssetListEntryAssetEntryRel fetchByUuid_Last(String uuid,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<AssetListEntryAssetEntryRel> list = findByUuid(uuid, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset list entry asset entry rels before and after the current asset list entry asset entry rel in the ordered set where uuid = &#63;.
	 *
	 * @param assetListEntryAssetEntryRelId the primary key of the current asset list entry asset entry rel
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a asset list entry asset entry rel with the primary key could not be found
	 */
	@Override
	public AssetListEntryAssetEntryRel[] findByUuid_PrevAndNext(
		long assetListEntryAssetEntryRelId, String uuid,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator)
		throws NoSuchEntryAssetEntryRelException {
		uuid = Objects.toString(uuid, "");

		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel = findByPrimaryKey(assetListEntryAssetEntryRelId);

		Session session = null;

		try {
			session = openSession();

			AssetListEntryAssetEntryRel[] array = new AssetListEntryAssetEntryRelImpl[3];

			array[0] = getByUuid_PrevAndNext(session,
					assetListEntryAssetEntryRel, uuid, orderByComparator, true);

			array[1] = assetListEntryAssetEntryRel;

			array[2] = getByUuid_PrevAndNext(session,
					assetListEntryAssetEntryRel, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetListEntryAssetEntryRel getByUuid_PrevAndNext(
		Session session,
		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel, String uuid,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETLISTENTRYASSETENTRYREL_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetListEntryAssetEntryRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetListEntryAssetEntryRel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetListEntryAssetEntryRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset list entry asset entry rels where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (AssetListEntryAssetEntryRel assetListEntryAssetEntryRel : findByUuid(
				uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetListEntryAssetEntryRel);
		}
	}

	/**
	 * Returns the number of asset list entry asset entry rels where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching asset list entry asset entry rels
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSETLISTENTRYASSETENTRYREL_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "assetListEntryAssetEntryRel.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "assetListEntryAssetEntryRel.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(assetListEntryAssetEntryRel.uuid IS NULL OR assetListEntryAssetEntryRel.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(AssetListEntryAssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetListEntryAssetEntryRelModelImpl.FINDER_CACHE_ENABLED,
			AssetListEntryAssetEntryRelImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			AssetListEntryAssetEntryRelModelImpl.UUID_COLUMN_BITMASK |
			AssetListEntryAssetEntryRelModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(AssetListEntryAssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetListEntryAssetEntryRelModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the asset list entry asset entry rel where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchEntryAssetEntryRelException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a matching asset list entry asset entry rel could not be found
	 */
	@Override
	public AssetListEntryAssetEntryRel findByUUID_G(String uuid, long groupId)
		throws NoSuchEntryAssetEntryRelException {
		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel = fetchByUUID_G(uuid,
				groupId);

		if (assetListEntryAssetEntryRel == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchEntryAssetEntryRelException(msg.toString());
		}

		return assetListEntryAssetEntryRel;
	}

	/**
	 * Returns the asset list entry asset entry rel where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching asset list entry asset entry rel, or <code>null</code> if a matching asset list entry asset entry rel could not be found
	 */
	@Override
	public AssetListEntryAssetEntryRel fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the asset list entry asset entry rel where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching asset list entry asset entry rel, or <code>null</code> if a matching asset list entry asset entry rel could not be found
	 */
	@Override
	public AssetListEntryAssetEntryRel fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof AssetListEntryAssetEntryRel) {
			AssetListEntryAssetEntryRel assetListEntryAssetEntryRel = (AssetListEntryAssetEntryRel)result;

			if (!Objects.equals(uuid, assetListEntryAssetEntryRel.getUuid()) ||
					(groupId != assetListEntryAssetEntryRel.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_ASSETLISTENTRYASSETENTRYREL_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<AssetListEntryAssetEntryRel> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					AssetListEntryAssetEntryRel assetListEntryAssetEntryRel = list.get(0);

					result = assetListEntryAssetEntryRel;

					cacheResult(assetListEntryAssetEntryRel);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (AssetListEntryAssetEntryRel)result;
		}
	}

	/**
	 * Removes the asset list entry asset entry rel where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the asset list entry asset entry rel that was removed
	 */
	@Override
	public AssetListEntryAssetEntryRel removeByUUID_G(String uuid, long groupId)
		throws NoSuchEntryAssetEntryRelException {
		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel = findByUUID_G(uuid,
				groupId);

		return remove(assetListEntryAssetEntryRel);
	}

	/**
	 * Returns the number of asset list entry asset entry rels where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching asset list entry asset entry rels
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETLISTENTRYASSETENTRYREL_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "assetListEntryAssetEntryRel.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "assetListEntryAssetEntryRel.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(assetListEntryAssetEntryRel.uuid IS NULL OR assetListEntryAssetEntryRel.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "assetListEntryAssetEntryRel.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(AssetListEntryAssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetListEntryAssetEntryRelModelImpl.FINDER_CACHE_ENABLED,
			AssetListEntryAssetEntryRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(AssetListEntryAssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetListEntryAssetEntryRelModelImpl.FINDER_CACHE_ENABLED,
			AssetListEntryAssetEntryRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			AssetListEntryAssetEntryRelModelImpl.UUID_COLUMN_BITMASK |
			AssetListEntryAssetEntryRelModelImpl.COMPANYID_COLUMN_BITMASK |
			AssetListEntryAssetEntryRelModelImpl.POSITION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(AssetListEntryAssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetListEntryAssetEntryRelModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the asset list entry asset entry rels where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching asset list entry asset entry rels
	 */
	@Override
	public List<AssetListEntryAssetEntryRel> findByUuid_C(String uuid,
		long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset list entry asset entry rels where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetListEntryAssetEntryRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @return the range of matching asset list entry asset entry rels
	 */
	@Override
	public List<AssetListEntryAssetEntryRel> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset list entry asset entry rels where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetListEntryAssetEntryRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset list entry asset entry rels
	 */
	@Override
	public List<AssetListEntryAssetEntryRel> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the asset list entry asset entry rels where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetListEntryAssetEntryRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset list entry asset entry rels
	 */
	@Override
	public List<AssetListEntryAssetEntryRel> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator,
		boolean retrieveFromCache) {
		uuid = Objects.toString(uuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<AssetListEntryAssetEntryRel> list = null;

		if (retrieveFromCache) {
			list = (List<AssetListEntryAssetEntryRel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetListEntryAssetEntryRel assetListEntryAssetEntryRel : list) {
					if (!uuid.equals(assetListEntryAssetEntryRel.getUuid()) ||
							(companyId != assetListEntryAssetEntryRel.getCompanyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ASSETLISTENTRYASSETENTRYREL_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetListEntryAssetEntryRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				if (!pagination) {
					list = (List<AssetListEntryAssetEntryRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetListEntryAssetEntryRel>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first asset list entry asset entry rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a matching asset list entry asset entry rel could not be found
	 */
	@Override
	public AssetListEntryAssetEntryRel findByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator)
		throws NoSuchEntryAssetEntryRelException {
		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (assetListEntryAssetEntryRel != null) {
			return assetListEntryAssetEntryRel;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchEntryAssetEntryRelException(msg.toString());
	}

	/**
	 * Returns the first asset list entry asset entry rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry asset entry rel, or <code>null</code> if a matching asset list entry asset entry rel could not be found
	 */
	@Override
	public AssetListEntryAssetEntryRel fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator) {
		List<AssetListEntryAssetEntryRel> list = findByUuid_C(uuid, companyId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset list entry asset entry rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a matching asset list entry asset entry rel could not be found
	 */
	@Override
	public AssetListEntryAssetEntryRel findByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator)
		throws NoSuchEntryAssetEntryRelException {
		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (assetListEntryAssetEntryRel != null) {
			return assetListEntryAssetEntryRel;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchEntryAssetEntryRelException(msg.toString());
	}

	/**
	 * Returns the last asset list entry asset entry rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry asset entry rel, or <code>null</code> if a matching asset list entry asset entry rel could not be found
	 */
	@Override
	public AssetListEntryAssetEntryRel fetchByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<AssetListEntryAssetEntryRel> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset list entry asset entry rels before and after the current asset list entry asset entry rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param assetListEntryAssetEntryRelId the primary key of the current asset list entry asset entry rel
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a asset list entry asset entry rel with the primary key could not be found
	 */
	@Override
	public AssetListEntryAssetEntryRel[] findByUuid_C_PrevAndNext(
		long assetListEntryAssetEntryRelId, String uuid, long companyId,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator)
		throws NoSuchEntryAssetEntryRelException {
		uuid = Objects.toString(uuid, "");

		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel = findByPrimaryKey(assetListEntryAssetEntryRelId);

		Session session = null;

		try {
			session = openSession();

			AssetListEntryAssetEntryRel[] array = new AssetListEntryAssetEntryRelImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session,
					assetListEntryAssetEntryRel, uuid, companyId,
					orderByComparator, true);

			array[1] = assetListEntryAssetEntryRel;

			array[2] = getByUuid_C_PrevAndNext(session,
					assetListEntryAssetEntryRel, uuid, companyId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetListEntryAssetEntryRel getByUuid_C_PrevAndNext(
		Session session,
		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel, String uuid,
		long companyId,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ASSETLISTENTRYASSETENTRYREL_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetListEntryAssetEntryRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetListEntryAssetEntryRel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetListEntryAssetEntryRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset list entry asset entry rels where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (AssetListEntryAssetEntryRel assetListEntryAssetEntryRel : findByUuid_C(
				uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetListEntryAssetEntryRel);
		}
	}

	/**
	 * Returns the number of asset list entry asset entry rels where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching asset list entry asset entry rels
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETLISTENTRYASSETENTRYREL_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "assetListEntryAssetEntryRel.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "assetListEntryAssetEntryRel.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(assetListEntryAssetEntryRel.uuid IS NULL OR assetListEntryAssetEntryRel.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "assetListEntryAssetEntryRel.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSETLISTENTRYID =
		new FinderPath(AssetListEntryAssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetListEntryAssetEntryRelModelImpl.FINDER_CACHE_ENABLED,
			AssetListEntryAssetEntryRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAssetListEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETLISTENTRYID =
		new FinderPath(AssetListEntryAssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetListEntryAssetEntryRelModelImpl.FINDER_CACHE_ENABLED,
			AssetListEntryAssetEntryRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByAssetListEntryId", new String[] { Long.class.getName() },
			AssetListEntryAssetEntryRelModelImpl.ASSETLISTENTRYID_COLUMN_BITMASK |
			AssetListEntryAssetEntryRelModelImpl.POSITION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ASSETLISTENTRYID = new FinderPath(AssetListEntryAssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetListEntryAssetEntryRelModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByAssetListEntryId", new String[] { Long.class.getName() });

	/**
	 * Returns all the asset list entry asset entry rels where assetListEntryId = &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @return the matching asset list entry asset entry rels
	 */
	@Override
	public List<AssetListEntryAssetEntryRel> findByAssetListEntryId(
		long assetListEntryId) {
		return findByAssetListEntryId(assetListEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset list entry asset entry rels where assetListEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetListEntryAssetEntryRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @return the range of matching asset list entry asset entry rels
	 */
	@Override
	public List<AssetListEntryAssetEntryRel> findByAssetListEntryId(
		long assetListEntryId, int start, int end) {
		return findByAssetListEntryId(assetListEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset list entry asset entry rels where assetListEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetListEntryAssetEntryRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset list entry asset entry rels
	 */
	@Override
	public List<AssetListEntryAssetEntryRel> findByAssetListEntryId(
		long assetListEntryId, int start, int end,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator) {
		return findByAssetListEntryId(assetListEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the asset list entry asset entry rels where assetListEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetListEntryAssetEntryRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset list entry asset entry rels
	 */
	@Override
	public List<AssetListEntryAssetEntryRel> findByAssetListEntryId(
		long assetListEntryId, int start, int end,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETLISTENTRYID;
			finderArgs = new Object[] { assetListEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSETLISTENTRYID;
			finderArgs = new Object[] {
					assetListEntryId,
					
					start, end, orderByComparator
				};
		}

		List<AssetListEntryAssetEntryRel> list = null;

		if (retrieveFromCache) {
			list = (List<AssetListEntryAssetEntryRel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetListEntryAssetEntryRel assetListEntryAssetEntryRel : list) {
					if ((assetListEntryId != assetListEntryAssetEntryRel.getAssetListEntryId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ASSETLISTENTRYASSETENTRYREL_WHERE);

			query.append(_FINDER_COLUMN_ASSETLISTENTRYID_ASSETLISTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetListEntryAssetEntryRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetListEntryId);

				if (!pagination) {
					list = (List<AssetListEntryAssetEntryRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetListEntryAssetEntryRel>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first asset list entry asset entry rel in the ordered set where assetListEntryId = &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a matching asset list entry asset entry rel could not be found
	 */
	@Override
	public AssetListEntryAssetEntryRel findByAssetListEntryId_First(
		long assetListEntryId,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator)
		throws NoSuchEntryAssetEntryRelException {
		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel = fetchByAssetListEntryId_First(assetListEntryId,
				orderByComparator);

		if (assetListEntryAssetEntryRel != null) {
			return assetListEntryAssetEntryRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetListEntryId=");
		msg.append(assetListEntryId);

		msg.append("}");

		throw new NoSuchEntryAssetEntryRelException(msg.toString());
	}

	/**
	 * Returns the first asset list entry asset entry rel in the ordered set where assetListEntryId = &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry asset entry rel, or <code>null</code> if a matching asset list entry asset entry rel could not be found
	 */
	@Override
	public AssetListEntryAssetEntryRel fetchByAssetListEntryId_First(
		long assetListEntryId,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator) {
		List<AssetListEntryAssetEntryRel> list = findByAssetListEntryId(assetListEntryId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset list entry asset entry rel in the ordered set where assetListEntryId = &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a matching asset list entry asset entry rel could not be found
	 */
	@Override
	public AssetListEntryAssetEntryRel findByAssetListEntryId_Last(
		long assetListEntryId,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator)
		throws NoSuchEntryAssetEntryRelException {
		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel = fetchByAssetListEntryId_Last(assetListEntryId,
				orderByComparator);

		if (assetListEntryAssetEntryRel != null) {
			return assetListEntryAssetEntryRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetListEntryId=");
		msg.append(assetListEntryId);

		msg.append("}");

		throw new NoSuchEntryAssetEntryRelException(msg.toString());
	}

	/**
	 * Returns the last asset list entry asset entry rel in the ordered set where assetListEntryId = &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry asset entry rel, or <code>null</code> if a matching asset list entry asset entry rel could not be found
	 */
	@Override
	public AssetListEntryAssetEntryRel fetchByAssetListEntryId_Last(
		long assetListEntryId,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator) {
		int count = countByAssetListEntryId(assetListEntryId);

		if (count == 0) {
			return null;
		}

		List<AssetListEntryAssetEntryRel> list = findByAssetListEntryId(assetListEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset list entry asset entry rels before and after the current asset list entry asset entry rel in the ordered set where assetListEntryId = &#63;.
	 *
	 * @param assetListEntryAssetEntryRelId the primary key of the current asset list entry asset entry rel
	 * @param assetListEntryId the asset list entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a asset list entry asset entry rel with the primary key could not be found
	 */
	@Override
	public AssetListEntryAssetEntryRel[] findByAssetListEntryId_PrevAndNext(
		long assetListEntryAssetEntryRelId, long assetListEntryId,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator)
		throws NoSuchEntryAssetEntryRelException {
		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel = findByPrimaryKey(assetListEntryAssetEntryRelId);

		Session session = null;

		try {
			session = openSession();

			AssetListEntryAssetEntryRel[] array = new AssetListEntryAssetEntryRelImpl[3];

			array[0] = getByAssetListEntryId_PrevAndNext(session,
					assetListEntryAssetEntryRel, assetListEntryId,
					orderByComparator, true);

			array[1] = assetListEntryAssetEntryRel;

			array[2] = getByAssetListEntryId_PrevAndNext(session,
					assetListEntryAssetEntryRel, assetListEntryId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetListEntryAssetEntryRel getByAssetListEntryId_PrevAndNext(
		Session session,
		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel,
		long assetListEntryId,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETLISTENTRYASSETENTRYREL_WHERE);

		query.append(_FINDER_COLUMN_ASSETLISTENTRYID_ASSETLISTENTRYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetListEntryAssetEntryRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(assetListEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetListEntryAssetEntryRel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetListEntryAssetEntryRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset list entry asset entry rels where assetListEntryId = &#63; from the database.
	 *
	 * @param assetListEntryId the asset list entry ID
	 */
	@Override
	public void removeByAssetListEntryId(long assetListEntryId) {
		for (AssetListEntryAssetEntryRel assetListEntryAssetEntryRel : findByAssetListEntryId(
				assetListEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetListEntryAssetEntryRel);
		}
	}

	/**
	 * Returns the number of asset list entry asset entry rels where assetListEntryId = &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @return the number of matching asset list entry asset entry rels
	 */
	@Override
	public int countByAssetListEntryId(long assetListEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ASSETLISTENTRYID;

		Object[] finderArgs = new Object[] { assetListEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSETLISTENTRYASSETENTRYREL_WHERE);

			query.append(_FINDER_COLUMN_ASSETLISTENTRYID_ASSETLISTENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetListEntryId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_ASSETLISTENTRYID_ASSETLISTENTRYID_2 =
		"assetListEntryAssetEntryRel.assetListEntryId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_A_P = new FinderPath(AssetListEntryAssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetListEntryAssetEntryRelModelImpl.FINDER_CACHE_ENABLED,
			AssetListEntryAssetEntryRelImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByA_P",
			new String[] { Long.class.getName(), Integer.class.getName() },
			AssetListEntryAssetEntryRelModelImpl.ASSETLISTENTRYID_COLUMN_BITMASK |
			AssetListEntryAssetEntryRelModelImpl.POSITION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_A_P = new FinderPath(AssetListEntryAssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetListEntryAssetEntryRelModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByA_P",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns the asset list entry asset entry rel where assetListEntryId = &#63; and position = &#63; or throws a {@link NoSuchEntryAssetEntryRelException} if it could not be found.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param position the position
	 * @return the matching asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a matching asset list entry asset entry rel could not be found
	 */
	@Override
	public AssetListEntryAssetEntryRel findByA_P(long assetListEntryId,
		int position) throws NoSuchEntryAssetEntryRelException {
		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel = fetchByA_P(assetListEntryId,
				position);

		if (assetListEntryAssetEntryRel == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("assetListEntryId=");
			msg.append(assetListEntryId);

			msg.append(", position=");
			msg.append(position);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchEntryAssetEntryRelException(msg.toString());
		}

		return assetListEntryAssetEntryRel;
	}

	/**
	 * Returns the asset list entry asset entry rel where assetListEntryId = &#63; and position = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param position the position
	 * @return the matching asset list entry asset entry rel, or <code>null</code> if a matching asset list entry asset entry rel could not be found
	 */
	@Override
	public AssetListEntryAssetEntryRel fetchByA_P(long assetListEntryId,
		int position) {
		return fetchByA_P(assetListEntryId, position, true);
	}

	/**
	 * Returns the asset list entry asset entry rel where assetListEntryId = &#63; and position = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param position the position
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching asset list entry asset entry rel, or <code>null</code> if a matching asset list entry asset entry rel could not be found
	 */
	@Override
	public AssetListEntryAssetEntryRel fetchByA_P(long assetListEntryId,
		int position, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { assetListEntryId, position };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_A_P,
					finderArgs, this);
		}

		if (result instanceof AssetListEntryAssetEntryRel) {
			AssetListEntryAssetEntryRel assetListEntryAssetEntryRel = (AssetListEntryAssetEntryRel)result;

			if ((assetListEntryId != assetListEntryAssetEntryRel.getAssetListEntryId()) ||
					(position != assetListEntryAssetEntryRel.getPosition())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_ASSETLISTENTRYASSETENTRYREL_WHERE);

			query.append(_FINDER_COLUMN_A_P_ASSETLISTENTRYID_2);

			query.append(_FINDER_COLUMN_A_P_POSITION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetListEntryId);

				qPos.add(position);

				List<AssetListEntryAssetEntryRel> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_A_P, finderArgs,
						list);
				}
				else {
					AssetListEntryAssetEntryRel assetListEntryAssetEntryRel = list.get(0);

					result = assetListEntryAssetEntryRel;

					cacheResult(assetListEntryAssetEntryRel);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_A_P, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (AssetListEntryAssetEntryRel)result;
		}
	}

	/**
	 * Removes the asset list entry asset entry rel where assetListEntryId = &#63; and position = &#63; from the database.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param position the position
	 * @return the asset list entry asset entry rel that was removed
	 */
	@Override
	public AssetListEntryAssetEntryRel removeByA_P(long assetListEntryId,
		int position) throws NoSuchEntryAssetEntryRelException {
		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel = findByA_P(assetListEntryId,
				position);

		return remove(assetListEntryAssetEntryRel);
	}

	/**
	 * Returns the number of asset list entry asset entry rels where assetListEntryId = &#63; and position = &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param position the position
	 * @return the number of matching asset list entry asset entry rels
	 */
	@Override
	public int countByA_P(long assetListEntryId, int position) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_A_P;

		Object[] finderArgs = new Object[] { assetListEntryId, position };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETLISTENTRYASSETENTRYREL_WHERE);

			query.append(_FINDER_COLUMN_A_P_ASSETLISTENTRYID_2);

			query.append(_FINDER_COLUMN_A_P_POSITION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetListEntryId);

				qPos.add(position);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_A_P_ASSETLISTENTRYID_2 = "assetListEntryAssetEntryRel.assetListEntryId = ? AND ";
	private static final String _FINDER_COLUMN_A_P_POSITION_2 = "assetListEntryAssetEntryRel.position = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_A_GTP = new FinderPath(AssetListEntryAssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetListEntryAssetEntryRelModelImpl.FINDER_CACHE_ENABLED,
			AssetListEntryAssetEntryRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByA_GtP",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_A_GTP = new FinderPath(AssetListEntryAssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetListEntryAssetEntryRelModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByA_GtP",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the asset list entry asset entry rels where assetListEntryId = &#63; and position &gt; &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param position the position
	 * @return the matching asset list entry asset entry rels
	 */
	@Override
	public List<AssetListEntryAssetEntryRel> findByA_GtP(
		long assetListEntryId, int position) {
		return findByA_GtP(assetListEntryId, position, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset list entry asset entry rels where assetListEntryId = &#63; and position &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetListEntryAssetEntryRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param position the position
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @return the range of matching asset list entry asset entry rels
	 */
	@Override
	public List<AssetListEntryAssetEntryRel> findByA_GtP(
		long assetListEntryId, int position, int start, int end) {
		return findByA_GtP(assetListEntryId, position, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset list entry asset entry rels where assetListEntryId = &#63; and position &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetListEntryAssetEntryRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param position the position
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset list entry asset entry rels
	 */
	@Override
	public List<AssetListEntryAssetEntryRel> findByA_GtP(
		long assetListEntryId, int position, int start, int end,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator) {
		return findByA_GtP(assetListEntryId, position, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the asset list entry asset entry rels where assetListEntryId = &#63; and position &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetListEntryAssetEntryRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param position the position
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset list entry asset entry rels
	 */
	@Override
	public List<AssetListEntryAssetEntryRel> findByA_GtP(
		long assetListEntryId, int position, int start, int end,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_A_GTP;
		finderArgs = new Object[] {
				assetListEntryId, position,
				
				start, end, orderByComparator
			};

		List<AssetListEntryAssetEntryRel> list = null;

		if (retrieveFromCache) {
			list = (List<AssetListEntryAssetEntryRel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetListEntryAssetEntryRel assetListEntryAssetEntryRel : list) {
					if ((assetListEntryId != assetListEntryAssetEntryRel.getAssetListEntryId()) ||
							(position >= assetListEntryAssetEntryRel.getPosition())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ASSETLISTENTRYASSETENTRYREL_WHERE);

			query.append(_FINDER_COLUMN_A_GTP_ASSETLISTENTRYID_2);

			query.append(_FINDER_COLUMN_A_GTP_POSITION_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetListEntryAssetEntryRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetListEntryId);

				qPos.add(position);

				if (!pagination) {
					list = (List<AssetListEntryAssetEntryRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetListEntryAssetEntryRel>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first asset list entry asset entry rel in the ordered set where assetListEntryId = &#63; and position &gt; &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param position the position
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a matching asset list entry asset entry rel could not be found
	 */
	@Override
	public AssetListEntryAssetEntryRel findByA_GtP_First(
		long assetListEntryId, int position,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator)
		throws NoSuchEntryAssetEntryRelException {
		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel = fetchByA_GtP_First(assetListEntryId,
				position, orderByComparator);

		if (assetListEntryAssetEntryRel != null) {
			return assetListEntryAssetEntryRel;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetListEntryId=");
		msg.append(assetListEntryId);

		msg.append(", position=");
		msg.append(position);

		msg.append("}");

		throw new NoSuchEntryAssetEntryRelException(msg.toString());
	}

	/**
	 * Returns the first asset list entry asset entry rel in the ordered set where assetListEntryId = &#63; and position &gt; &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param position the position
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list entry asset entry rel, or <code>null</code> if a matching asset list entry asset entry rel could not be found
	 */
	@Override
	public AssetListEntryAssetEntryRel fetchByA_GtP_First(
		long assetListEntryId, int position,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator) {
		List<AssetListEntryAssetEntryRel> list = findByA_GtP(assetListEntryId,
				position, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset list entry asset entry rel in the ordered set where assetListEntryId = &#63; and position &gt; &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param position the position
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a matching asset list entry asset entry rel could not be found
	 */
	@Override
	public AssetListEntryAssetEntryRel findByA_GtP_Last(long assetListEntryId,
		int position,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator)
		throws NoSuchEntryAssetEntryRelException {
		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel = fetchByA_GtP_Last(assetListEntryId,
				position, orderByComparator);

		if (assetListEntryAssetEntryRel != null) {
			return assetListEntryAssetEntryRel;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetListEntryId=");
		msg.append(assetListEntryId);

		msg.append(", position=");
		msg.append(position);

		msg.append("}");

		throw new NoSuchEntryAssetEntryRelException(msg.toString());
	}

	/**
	 * Returns the last asset list entry asset entry rel in the ordered set where assetListEntryId = &#63; and position &gt; &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param position the position
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list entry asset entry rel, or <code>null</code> if a matching asset list entry asset entry rel could not be found
	 */
	@Override
	public AssetListEntryAssetEntryRel fetchByA_GtP_Last(
		long assetListEntryId, int position,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator) {
		int count = countByA_GtP(assetListEntryId, position);

		if (count == 0) {
			return null;
		}

		List<AssetListEntryAssetEntryRel> list = findByA_GtP(assetListEntryId,
				position, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset list entry asset entry rels before and after the current asset list entry asset entry rel in the ordered set where assetListEntryId = &#63; and position &gt; &#63;.
	 *
	 * @param assetListEntryAssetEntryRelId the primary key of the current asset list entry asset entry rel
	 * @param assetListEntryId the asset list entry ID
	 * @param position the position
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a asset list entry asset entry rel with the primary key could not be found
	 */
	@Override
	public AssetListEntryAssetEntryRel[] findByA_GtP_PrevAndNext(
		long assetListEntryAssetEntryRelId, long assetListEntryId,
		int position,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator)
		throws NoSuchEntryAssetEntryRelException {
		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel = findByPrimaryKey(assetListEntryAssetEntryRelId);

		Session session = null;

		try {
			session = openSession();

			AssetListEntryAssetEntryRel[] array = new AssetListEntryAssetEntryRelImpl[3];

			array[0] = getByA_GtP_PrevAndNext(session,
					assetListEntryAssetEntryRel, assetListEntryId, position,
					orderByComparator, true);

			array[1] = assetListEntryAssetEntryRel;

			array[2] = getByA_GtP_PrevAndNext(session,
					assetListEntryAssetEntryRel, assetListEntryId, position,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetListEntryAssetEntryRel getByA_GtP_PrevAndNext(
		Session session,
		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel,
		long assetListEntryId, int position,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ASSETLISTENTRYASSETENTRYREL_WHERE);

		query.append(_FINDER_COLUMN_A_GTP_ASSETLISTENTRYID_2);

		query.append(_FINDER_COLUMN_A_GTP_POSITION_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AssetListEntryAssetEntryRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(assetListEntryId);

		qPos.add(position);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetListEntryAssetEntryRel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetListEntryAssetEntryRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset list entry asset entry rels where assetListEntryId = &#63; and position &gt; &#63; from the database.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param position the position
	 */
	@Override
	public void removeByA_GtP(long assetListEntryId, int position) {
		for (AssetListEntryAssetEntryRel assetListEntryAssetEntryRel : findByA_GtP(
				assetListEntryId, position, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(assetListEntryAssetEntryRel);
		}
	}

	/**
	 * Returns the number of asset list entry asset entry rels where assetListEntryId = &#63; and position &gt; &#63;.
	 *
	 * @param assetListEntryId the asset list entry ID
	 * @param position the position
	 * @return the number of matching asset list entry asset entry rels
	 */
	@Override
	public int countByA_GtP(long assetListEntryId, int position) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_A_GTP;

		Object[] finderArgs = new Object[] { assetListEntryId, position };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETLISTENTRYASSETENTRYREL_WHERE);

			query.append(_FINDER_COLUMN_A_GTP_ASSETLISTENTRYID_2);

			query.append(_FINDER_COLUMN_A_GTP_POSITION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetListEntryId);

				qPos.add(position);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_A_GTP_ASSETLISTENTRYID_2 = "assetListEntryAssetEntryRel.assetListEntryId = ? AND ";
	private static final String _FINDER_COLUMN_A_GTP_POSITION_2 = "assetListEntryAssetEntryRel.position > ?";

	public AssetListEntryAssetEntryRelPersistenceImpl() {
		setModelClass(AssetListEntryAssetEntryRel.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the asset list entry asset entry rel in the entity cache if it is enabled.
	 *
	 * @param assetListEntryAssetEntryRel the asset list entry asset entry rel
	 */
	@Override
	public void cacheResult(
		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel) {
		entityCache.putResult(AssetListEntryAssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetListEntryAssetEntryRelImpl.class,
			assetListEntryAssetEntryRel.getPrimaryKey(),
			assetListEntryAssetEntryRel);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				assetListEntryAssetEntryRel.getUuid(),
				assetListEntryAssetEntryRel.getGroupId()
			}, assetListEntryAssetEntryRel);

		finderCache.putResult(FINDER_PATH_FETCH_BY_A_P,
			new Object[] {
				assetListEntryAssetEntryRel.getAssetListEntryId(),
				assetListEntryAssetEntryRel.getPosition()
			}, assetListEntryAssetEntryRel);

		assetListEntryAssetEntryRel.resetOriginalValues();
	}

	/**
	 * Caches the asset list entry asset entry rels in the entity cache if it is enabled.
	 *
	 * @param assetListEntryAssetEntryRels the asset list entry asset entry rels
	 */
	@Override
	public void cacheResult(
		List<AssetListEntryAssetEntryRel> assetListEntryAssetEntryRels) {
		for (AssetListEntryAssetEntryRel assetListEntryAssetEntryRel : assetListEntryAssetEntryRels) {
			if (entityCache.getResult(
						AssetListEntryAssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
						AssetListEntryAssetEntryRelImpl.class,
						assetListEntryAssetEntryRel.getPrimaryKey()) == null) {
				cacheResult(assetListEntryAssetEntryRel);
			}
			else {
				assetListEntryAssetEntryRel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all asset list entry asset entry rels.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AssetListEntryAssetEntryRelImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the asset list entry asset entry rel.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel) {
		entityCache.removeResult(AssetListEntryAssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetListEntryAssetEntryRelImpl.class,
			assetListEntryAssetEntryRel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((AssetListEntryAssetEntryRelModelImpl)assetListEntryAssetEntryRel,
			true);
	}

	@Override
	public void clearCache(
		List<AssetListEntryAssetEntryRel> assetListEntryAssetEntryRels) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AssetListEntryAssetEntryRel assetListEntryAssetEntryRel : assetListEntryAssetEntryRels) {
			entityCache.removeResult(AssetListEntryAssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
				AssetListEntryAssetEntryRelImpl.class,
				assetListEntryAssetEntryRel.getPrimaryKey());

			clearUniqueFindersCache((AssetListEntryAssetEntryRelModelImpl)assetListEntryAssetEntryRel,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		AssetListEntryAssetEntryRelModelImpl assetListEntryAssetEntryRelModelImpl) {
		Object[] args = new Object[] {
				assetListEntryAssetEntryRelModelImpl.getUuid(),
				assetListEntryAssetEntryRelModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			assetListEntryAssetEntryRelModelImpl, false);

		args = new Object[] {
				assetListEntryAssetEntryRelModelImpl.getAssetListEntryId(),
				assetListEntryAssetEntryRelModelImpl.getPosition()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_A_P, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_A_P, args,
			assetListEntryAssetEntryRelModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		AssetListEntryAssetEntryRelModelImpl assetListEntryAssetEntryRelModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					assetListEntryAssetEntryRelModelImpl.getUuid(),
					assetListEntryAssetEntryRelModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((assetListEntryAssetEntryRelModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					assetListEntryAssetEntryRelModelImpl.getOriginalUuid(),
					assetListEntryAssetEntryRelModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					assetListEntryAssetEntryRelModelImpl.getAssetListEntryId(),
					assetListEntryAssetEntryRelModelImpl.getPosition()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_A_P, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_A_P, args);
		}

		if ((assetListEntryAssetEntryRelModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_A_P.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					assetListEntryAssetEntryRelModelImpl.getOriginalAssetListEntryId(),
					assetListEntryAssetEntryRelModelImpl.getOriginalPosition()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_A_P, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_A_P, args);
		}
	}

	/**
	 * Creates a new asset list entry asset entry rel with the primary key. Does not add the asset list entry asset entry rel to the database.
	 *
	 * @param assetListEntryAssetEntryRelId the primary key for the new asset list entry asset entry rel
	 * @return the new asset list entry asset entry rel
	 */
	@Override
	public AssetListEntryAssetEntryRel create(
		long assetListEntryAssetEntryRelId) {
		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel = new AssetListEntryAssetEntryRelImpl();

		assetListEntryAssetEntryRel.setNew(true);
		assetListEntryAssetEntryRel.setPrimaryKey(assetListEntryAssetEntryRelId);

		String uuid = PortalUUIDUtil.generate();

		assetListEntryAssetEntryRel.setUuid(uuid);

		assetListEntryAssetEntryRel.setCompanyId(companyProvider.getCompanyId());

		return assetListEntryAssetEntryRel;
	}

	/**
	 * Removes the asset list entry asset entry rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetListEntryAssetEntryRelId the primary key of the asset list entry asset entry rel
	 * @return the asset list entry asset entry rel that was removed
	 * @throws NoSuchEntryAssetEntryRelException if a asset list entry asset entry rel with the primary key could not be found
	 */
	@Override
	public AssetListEntryAssetEntryRel remove(
		long assetListEntryAssetEntryRelId)
		throws NoSuchEntryAssetEntryRelException {
		return remove((Serializable)assetListEntryAssetEntryRelId);
	}

	/**
	 * Removes the asset list entry asset entry rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the asset list entry asset entry rel
	 * @return the asset list entry asset entry rel that was removed
	 * @throws NoSuchEntryAssetEntryRelException if a asset list entry asset entry rel with the primary key could not be found
	 */
	@Override
	public AssetListEntryAssetEntryRel remove(Serializable primaryKey)
		throws NoSuchEntryAssetEntryRelException {
		Session session = null;

		try {
			session = openSession();

			AssetListEntryAssetEntryRel assetListEntryAssetEntryRel = (AssetListEntryAssetEntryRel)session.get(AssetListEntryAssetEntryRelImpl.class,
					primaryKey);

			if (assetListEntryAssetEntryRel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEntryAssetEntryRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(assetListEntryAssetEntryRel);
		}
		catch (NoSuchEntryAssetEntryRelException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected AssetListEntryAssetEntryRel removeImpl(
		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(assetListEntryAssetEntryRel)) {
				assetListEntryAssetEntryRel = (AssetListEntryAssetEntryRel)session.get(AssetListEntryAssetEntryRelImpl.class,
						assetListEntryAssetEntryRel.getPrimaryKeyObj());
			}

			if (assetListEntryAssetEntryRel != null) {
				session.delete(assetListEntryAssetEntryRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (assetListEntryAssetEntryRel != null) {
			clearCache(assetListEntryAssetEntryRel);
		}

		return assetListEntryAssetEntryRel;
	}

	@Override
	public AssetListEntryAssetEntryRel updateImpl(
		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel) {
		boolean isNew = assetListEntryAssetEntryRel.isNew();

		if (!(assetListEntryAssetEntryRel instanceof AssetListEntryAssetEntryRelModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(assetListEntryAssetEntryRel.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(assetListEntryAssetEntryRel);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in assetListEntryAssetEntryRel proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom AssetListEntryAssetEntryRel implementation " +
				assetListEntryAssetEntryRel.getClass());
		}

		AssetListEntryAssetEntryRelModelImpl assetListEntryAssetEntryRelModelImpl =
			(AssetListEntryAssetEntryRelModelImpl)assetListEntryAssetEntryRel;

		if (Validator.isNull(assetListEntryAssetEntryRel.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			assetListEntryAssetEntryRel.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (assetListEntryAssetEntryRel.getCreateDate() == null)) {
			if (serviceContext == null) {
				assetListEntryAssetEntryRel.setCreateDate(now);
			}
			else {
				assetListEntryAssetEntryRel.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!assetListEntryAssetEntryRelModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				assetListEntryAssetEntryRel.setModifiedDate(now);
			}
			else {
				assetListEntryAssetEntryRel.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (assetListEntryAssetEntryRel.isNew()) {
				session.save(assetListEntryAssetEntryRel);

				assetListEntryAssetEntryRel.setNew(false);
			}
			else {
				assetListEntryAssetEntryRel = (AssetListEntryAssetEntryRel)session.merge(assetListEntryAssetEntryRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!AssetListEntryAssetEntryRelModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					assetListEntryAssetEntryRelModelImpl.getUuid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					assetListEntryAssetEntryRelModelImpl.getUuid(),
					assetListEntryAssetEntryRelModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					assetListEntryAssetEntryRelModelImpl.getAssetListEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ASSETLISTENTRYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETLISTENTRYID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((assetListEntryAssetEntryRelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetListEntryAssetEntryRelModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] {
						assetListEntryAssetEntryRelModelImpl.getUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((assetListEntryAssetEntryRelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetListEntryAssetEntryRelModelImpl.getOriginalUuid(),
						assetListEntryAssetEntryRelModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						assetListEntryAssetEntryRelModelImpl.getUuid(),
						assetListEntryAssetEntryRelModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((assetListEntryAssetEntryRelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETLISTENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetListEntryAssetEntryRelModelImpl.getOriginalAssetListEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ASSETLISTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETLISTENTRYID,
					args);

				args = new Object[] {
						assetListEntryAssetEntryRelModelImpl.getAssetListEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ASSETLISTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETLISTENTRYID,
					args);
			}
		}

		entityCache.putResult(AssetListEntryAssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetListEntryAssetEntryRelImpl.class,
			assetListEntryAssetEntryRel.getPrimaryKey(),
			assetListEntryAssetEntryRel, false);

		clearUniqueFindersCache(assetListEntryAssetEntryRelModelImpl, false);
		cacheUniqueFindersCache(assetListEntryAssetEntryRelModelImpl);

		assetListEntryAssetEntryRel.resetOriginalValues();

		return assetListEntryAssetEntryRel;
	}

	/**
	 * Returns the asset list entry asset entry rel with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset list entry asset entry rel
	 * @return the asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a asset list entry asset entry rel with the primary key could not be found
	 */
	@Override
	public AssetListEntryAssetEntryRel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEntryAssetEntryRelException {
		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel = fetchByPrimaryKey(primaryKey);

		if (assetListEntryAssetEntryRel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEntryAssetEntryRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return assetListEntryAssetEntryRel;
	}

	/**
	 * Returns the asset list entry asset entry rel with the primary key or throws a {@link NoSuchEntryAssetEntryRelException} if it could not be found.
	 *
	 * @param assetListEntryAssetEntryRelId the primary key of the asset list entry asset entry rel
	 * @return the asset list entry asset entry rel
	 * @throws NoSuchEntryAssetEntryRelException if a asset list entry asset entry rel with the primary key could not be found
	 */
	@Override
	public AssetListEntryAssetEntryRel findByPrimaryKey(
		long assetListEntryAssetEntryRelId)
		throws NoSuchEntryAssetEntryRelException {
		return findByPrimaryKey((Serializable)assetListEntryAssetEntryRelId);
	}

	/**
	 * Returns the asset list entry asset entry rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset list entry asset entry rel
	 * @return the asset list entry asset entry rel, or <code>null</code> if a asset list entry asset entry rel with the primary key could not be found
	 */
	@Override
	public AssetListEntryAssetEntryRel fetchByPrimaryKey(
		Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AssetListEntryAssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
				AssetListEntryAssetEntryRelImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AssetListEntryAssetEntryRel assetListEntryAssetEntryRel = (AssetListEntryAssetEntryRel)serializable;

		if (assetListEntryAssetEntryRel == null) {
			Session session = null;

			try {
				session = openSession();

				assetListEntryAssetEntryRel = (AssetListEntryAssetEntryRel)session.get(AssetListEntryAssetEntryRelImpl.class,
						primaryKey);

				if (assetListEntryAssetEntryRel != null) {
					cacheResult(assetListEntryAssetEntryRel);
				}
				else {
					entityCache.putResult(AssetListEntryAssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
						AssetListEntryAssetEntryRelImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AssetListEntryAssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
					AssetListEntryAssetEntryRelImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return assetListEntryAssetEntryRel;
	}

	/**
	 * Returns the asset list entry asset entry rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assetListEntryAssetEntryRelId the primary key of the asset list entry asset entry rel
	 * @return the asset list entry asset entry rel, or <code>null</code> if a asset list entry asset entry rel with the primary key could not be found
	 */
	@Override
	public AssetListEntryAssetEntryRel fetchByPrimaryKey(
		long assetListEntryAssetEntryRelId) {
		return fetchByPrimaryKey((Serializable)assetListEntryAssetEntryRelId);
	}

	@Override
	public Map<Serializable, AssetListEntryAssetEntryRel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AssetListEntryAssetEntryRel> map = new HashMap<Serializable, AssetListEntryAssetEntryRel>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AssetListEntryAssetEntryRel assetListEntryAssetEntryRel = fetchByPrimaryKey(primaryKey);

			if (assetListEntryAssetEntryRel != null) {
				map.put(primaryKey, assetListEntryAssetEntryRel);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AssetListEntryAssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
					AssetListEntryAssetEntryRelImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey,
						(AssetListEntryAssetEntryRel)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ASSETLISTENTRYASSETENTRYREL_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(",");
		}

		query.setIndex(query.index() - 1);

		query.append(")");

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (AssetListEntryAssetEntryRel assetListEntryAssetEntryRel : (List<AssetListEntryAssetEntryRel>)q.list()) {
				map.put(assetListEntryAssetEntryRel.getPrimaryKeyObj(),
					assetListEntryAssetEntryRel);

				cacheResult(assetListEntryAssetEntryRel);

				uncachedPrimaryKeys.remove(assetListEntryAssetEntryRel.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AssetListEntryAssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
					AssetListEntryAssetEntryRelImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the asset list entry asset entry rels.
	 *
	 * @return the asset list entry asset entry rels
	 */
	@Override
	public List<AssetListEntryAssetEntryRel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset list entry asset entry rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetListEntryAssetEntryRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @return the range of asset list entry asset entry rels
	 */
	@Override
	public List<AssetListEntryAssetEntryRel> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset list entry asset entry rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetListEntryAssetEntryRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of asset list entry asset entry rels
	 */
	@Override
	public List<AssetListEntryAssetEntryRel> findAll(int start, int end,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the asset list entry asset entry rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetListEntryAssetEntryRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset list entry asset entry rels
	 * @param end the upper bound of the range of asset list entry asset entry rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of asset list entry asset entry rels
	 */
	@Override
	public List<AssetListEntryAssetEntryRel> findAll(int start, int end,
		OrderByComparator<AssetListEntryAssetEntryRel> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<AssetListEntryAssetEntryRel> list = null;

		if (retrieveFromCache) {
			list = (List<AssetListEntryAssetEntryRel>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ASSETLISTENTRYASSETENTRYREL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ASSETLISTENTRYASSETENTRYREL;

				if (pagination) {
					sql = sql.concat(AssetListEntryAssetEntryRelModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AssetListEntryAssetEntryRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetListEntryAssetEntryRel>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the asset list entry asset entry rels from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AssetListEntryAssetEntryRel assetListEntryAssetEntryRel : findAll()) {
			remove(assetListEntryAssetEntryRel);
		}
	}

	/**
	 * Returns the number of asset list entry asset entry rels.
	 *
	 * @return the number of asset list entry asset entry rels
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ASSETLISTENTRYASSETENTRYREL);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return AssetListEntryAssetEntryRelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the asset list entry asset entry rel persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AssetListEntryAssetEntryRelImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_ASSETLISTENTRYASSETENTRYREL = "SELECT assetListEntryAssetEntryRel FROM AssetListEntryAssetEntryRel assetListEntryAssetEntryRel";
	private static final String _SQL_SELECT_ASSETLISTENTRYASSETENTRYREL_WHERE_PKS_IN =
		"SELECT assetListEntryAssetEntryRel FROM AssetListEntryAssetEntryRel assetListEntryAssetEntryRel WHERE assetListEntryAssetEntryRelId IN (";
	private static final String _SQL_SELECT_ASSETLISTENTRYASSETENTRYREL_WHERE = "SELECT assetListEntryAssetEntryRel FROM AssetListEntryAssetEntryRel assetListEntryAssetEntryRel WHERE ";
	private static final String _SQL_COUNT_ASSETLISTENTRYASSETENTRYREL = "SELECT COUNT(assetListEntryAssetEntryRel) FROM AssetListEntryAssetEntryRel assetListEntryAssetEntryRel";
	private static final String _SQL_COUNT_ASSETLISTENTRYASSETENTRYREL_WHERE = "SELECT COUNT(assetListEntryAssetEntryRel) FROM AssetListEntryAssetEntryRel assetListEntryAssetEntryRel WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "assetListEntryAssetEntryRel.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AssetListEntryAssetEntryRel exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AssetListEntryAssetEntryRel exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(AssetListEntryAssetEntryRelPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}