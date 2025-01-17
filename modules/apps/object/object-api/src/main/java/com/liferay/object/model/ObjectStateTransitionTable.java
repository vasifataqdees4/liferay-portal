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

package com.liferay.object.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;ObjectStateTransition&quot; database table.
 *
 * @author Marco Leo
 * @see ObjectStateTransition
 * @generated
 */
public class ObjectStateTransitionTable
	extends BaseTable<ObjectStateTransitionTable> {

	public static final ObjectStateTransitionTable INSTANCE =
		new ObjectStateTransitionTable();

	public final Column<ObjectStateTransitionTable, Long> mvccVersion =
		createColumn(
			"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<ObjectStateTransitionTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ObjectStateTransitionTable, Long>
		objectStateTransitionId = createColumn(
			"objectStateTransitionId", Long.class, Types.BIGINT,
			Column.FLAG_PRIMARY);
	public final Column<ObjectStateTransitionTable, Long> companyId =
		createColumn(
			"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ObjectStateTransitionTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ObjectStateTransitionTable, String> userName =
		createColumn(
			"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ObjectStateTransitionTable, Date> createDate =
		createColumn(
			"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ObjectStateTransitionTable, Date> modifiedDate =
		createColumn(
			"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ObjectStateTransitionTable, Long> objectStateFlowId =
		createColumn(
			"objectStateFlowId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ObjectStateTransitionTable, Long> sourceObjectStateId =
		createColumn(
			"sourceObjectStateId", Long.class, Types.BIGINT,
			Column.FLAG_DEFAULT);
	public final Column<ObjectStateTransitionTable, Long> targetObjectStateId =
		createColumn(
			"targetObjectStateId", Long.class, Types.BIGINT,
			Column.FLAG_DEFAULT);

	private ObjectStateTransitionTable() {
		super("ObjectStateTransition", ObjectStateTransitionTable::new);
	}

}