package com.empfunction.model;

import java.io.Serializable;

public class FuncVO implements Serializable {
	private Integer funcid;
	private String funcName;

	public Integer getFuncid() {
		return funcid;
	}

	public void setFuncid(Integer funcid) {
		this.funcid = funcid;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
}
