/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.entity;

import com.misuosi.mshop.db.Entity;
import com.misuosi.mshop.db.annotation.Id;

/**
 * Description	 : 实体类 AlipayPaySetting
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class AlipayPaySetting extends Entity {

	@Id
	private Integer apseId;

	public Integer getApseId() {
		return apseId;
	}

	public void setApseId(Integer apseId) {
		this.apseId = apseId;
	}

}

