package com.kdyzm.gentleman.intelligentcontroller.api.consts;

public enum ReturnCode{

	success(0),
	
    fail(1);

    private Integer value;
    
	ReturnCode(int value)
	{
		this.value = value;
	}
	
	public Integer getValue()
	{
		return this.value;
	}
}
