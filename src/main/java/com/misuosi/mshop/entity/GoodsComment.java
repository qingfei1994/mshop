/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.entity;

import com.misuosi.mshop.db.Entity;
import com.misuosi.mshop.db.annotation.Id;

import java.sql.Timestamp;

/**
 * Description	 : 实体类 GoodsComment
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class GoodsComment extends Entity {

	@Id
	private Integer gocoId;
	private Integer goorId;
	private Byte gocoSatisfaction;
	private String gocoCommentContent;
	private String gocoReplyContent;
	private Timestamp gocoCommentTime;
	private Timestamp gocoReplyTime;
	private Byte gocoShowStatus;

	public Integer getGocoId() {
		return gocoId;
	}

	public void setGocoId(Integer gocoId) {
		this.gocoId = gocoId;
	}

	public Integer getGoorId() {
		return goorId;
	}

	public void setGoorId(Integer goorId) {
		this.goorId = goorId;
	}

	public Byte getGocoSatisfaction() {
		return gocoSatisfaction;
	}

	public void setGocoSatisfaction(Byte gocoSatisfaction) {
		this.gocoSatisfaction = gocoSatisfaction;
	}

	public String getGocoCommentContent() {
		return gocoCommentContent;
	}

	public void setGocoCommentContent(String gocoCommentContent) {
		this.gocoCommentContent = gocoCommentContent;
	}

	public String getGocoReplyContent() {
		return gocoReplyContent;
	}

	public void setGocoReplyContent(String gocoReplyContent) {
		this.gocoReplyContent = gocoReplyContent;
	}

	public Timestamp getGocoCommentTime() {
		return gocoCommentTime;
	}

	public void setGocoCommentTime(Timestamp gocoCommentTime) {
		this.gocoCommentTime = gocoCommentTime;
	}

	public Timestamp getGocoReplyTime() {
		return gocoReplyTime;
	}

	public void setGocoReplyTime(Timestamp gocoReplyTime) {
		this.gocoReplyTime = gocoReplyTime;
	}

	public Byte getGocoShowStatus() {
		return gocoShowStatus;
	}

	public void setGocoShowStatus(Byte gocoShowStatus) {
		this.gocoShowStatus = gocoShowStatus;
	}

}

