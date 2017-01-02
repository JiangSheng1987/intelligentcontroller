package com.kdyzm.gentleman.intelligentcontroller.model.response;

import com.kdyzm.gentleman.intelligentcontroller.api.consts.ReturnCode;

public class ResponseModel<T> {
	private Integer status = ReturnCode.success.getValue();
	private String msg = "";
	private T data;

	public ResponseModel() {
		super();
	}

	public ResponseModel(Integer status, String msg, T data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
