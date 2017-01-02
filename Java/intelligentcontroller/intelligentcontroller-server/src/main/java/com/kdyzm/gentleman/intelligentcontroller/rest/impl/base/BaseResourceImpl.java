package com.kdyzm.gentleman.intelligentcontroller.rest.impl.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kdyzm.gentleman.intelligentcontroller.api.consts.ReturnCode;
import com.kdyzm.gentleman.intelligentcontroller.model.response.ResponseModel;

public abstract class BaseResourceImpl {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	protected <T> ResponseModel<T> setSuccessMsg(T t) {
		ResponseModel<T> data = new ResponseModel<>();
		data.setData(t);
		data.setStatus(ReturnCode.success.getValue());
		data.setMsg("成功");
		return data;
	}

	protected <T> ResponseModel<T> setFaildMsg(T t) {
		ResponseModel<T> data = new ResponseModel<>();
		data.setData(t);
		data.setStatus(ReturnCode.fail.getValue());
		data.setMsg("失败");
		return data;
	}

}
