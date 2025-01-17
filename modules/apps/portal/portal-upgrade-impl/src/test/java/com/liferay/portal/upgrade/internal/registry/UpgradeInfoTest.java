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

package com.liferay.portal.upgrade.internal.registry;

import com.liferay.portal.kernel.upgrade.UpgradeStep;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Manuel de la Peña
 */
public class UpgradeInfoTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testEqualsReturnsFalseComparingDifferentClass() {
		UpgradeStep upgradeStep = new TestUpgradeStep();

		UpgradeInfo upgradeInfo = new UpgradeInfo(
			"1.0.0", "2.0.0", 0, upgradeStep);

		Assert.assertNotEquals(upgradeInfo, "");
	}

	@Test
	public void testEqualsReturnsFalseComparingDifferentSchemaVersion() {
		UpgradeInfo upgradeInfo1 = new UpgradeInfo(
			"1.0.0", "2.0.0", 0, new TestUpgradeStep());
		UpgradeInfo upgradeInfo2 = new UpgradeInfo(
			"2.0.0", "3.0.0", 0, new TestUpgradeStep());

		Assert.assertNotEquals(upgradeInfo1, upgradeInfo2);
	}

	@Test
	public void testEqualsReturnsFalseComparingSameSchemaVersion() {
		UpgradeInfo upgradeInfo1 = new UpgradeInfo(
			"1.0.0", "2.0.0", 0, new TestUpgradeStep());
		UpgradeInfo upgradeInfo2 = new UpgradeInfo(
			"1.0.0", "2.0.0", 0, new TestUpgradeStep());

		Assert.assertNotEquals(upgradeInfo1, upgradeInfo2);
	}

	@Test
	public void testEqualsReturnsTrueComparingSameInstance() {
		UpgradeInfo upgradeInfo = new UpgradeInfo(
			"1.0.0", "2.0.0", 0, new TestUpgradeStep());

		Assert.assertEquals(upgradeInfo, upgradeInfo);
	}

	@Test
	public void testEqualsReturnsTrueComparingSameSchemaVersion() {
		UpgradeStep upgradeStep = new TestUpgradeStep();

		UpgradeInfo upgradeInfo1 = new UpgradeInfo(
			"1.0.0", "2.0.0", 0, upgradeStep);
		UpgradeInfo upgradeInfo2 = new UpgradeInfo(
			"1.0.0", "2.0.0", 0, upgradeStep);

		Assert.assertEquals(upgradeInfo1, upgradeInfo2);
	}

	private static class TestUpgradeStep implements UpgradeStep {

		@Override
		public void upgrade() {
		}

	}

}