/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.entity;

import com.misuosi.mshop.db.Entity;
import com.misuosi.mshop.db.annotation.Id;

/**
 * Description	 : 实体类 UnionpayPaySetting
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class UnionpayPaySetting extends Entity {

	@Id
	private Integer upseId;

	public Integer getUpseId() {
		return upseId;
	}

	public void setUpseId(Integer upseId) {
		this.upseId = upseId;
	}

}

