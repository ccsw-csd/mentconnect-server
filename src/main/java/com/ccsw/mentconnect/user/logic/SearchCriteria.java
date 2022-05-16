package com.ccsw.mentconnect.user.logic;

public class SearchCriteria {
	private String operation;
	private Object datos;
	
	private String key;
	
	
	
	public SearchCriteria(String operation, Object datos,
			String key) {
		
		this.operation = operation;
		
		this.datos = datos;
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public Object getDatos() {
		return datos;
	}
	public void setDatos(Object datos) {
		this.datos = datos;
	}
	
	
	
}
