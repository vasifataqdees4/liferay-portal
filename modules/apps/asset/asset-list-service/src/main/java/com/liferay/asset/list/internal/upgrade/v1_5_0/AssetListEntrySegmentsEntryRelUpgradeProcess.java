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

package com.liferay.asset.list.internal.upgrade.v1_5_0;

import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.UpgradeProcessFactory;
import com.liferay.portal.kernel.upgrade.UpgradeStep;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Yurena Cabrera
 */
public class AssetListEntrySegmentsEntryRelUpgradeProcess
	extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try (PreparedStatement preparedStatement1 = connection.prepareStatement(
			"select alEntrySegmentsEntryRelId, assetListEntryId from " +
			"AssetListEntrySegmentsEntryRel order by assetListEntryId ASC, " +
			"createDate DESC");
			 PreparedStatement preparedStatement2 =
				 AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					 connection,
					 "update AssetListEntrySegmentsEntryRel set priority = ? " +
					 "where alEntrySegmentsEntryRelId = ?");

			ResultSet resultSet = preparedStatement1.executeQuery()) {

			long previousAssetListEntryId = -1;
			long priority = 0;

			while (resultSet.next()) {
				long alEntrySegmentsEntryRelId = resultSet.getLong(
					"alEntrySegmentsEntryRelId");

				long assetListEntryId = resultSet.getLong("assetListEntryId");

				if (assetListEntryId != previousAssetListEntryId) {
					priority = 0;
				}

				preparedStatement2.setLong(1, priority);
				preparedStatement2.setLong(2, alEntrySegmentsEntryRelId);

				preparedStatement2.addBatch();

				previousAssetListEntryId = assetListEntryId;
				priority++;
			}

			preparedStatement2.executeBatch();
		}
	}

	@Override
	protected UpgradeStep[] getPreUpgradeSteps() {
		return new UpgradeStep[] {
			UpgradeProcessFactory.addColumns(
				"AssetListEntrySegmentsEntryRel", "priority INTEGER")
		};
	}

}