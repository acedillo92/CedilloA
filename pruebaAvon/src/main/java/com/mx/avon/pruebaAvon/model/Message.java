package com.mx.avon.pruebaAvon.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Message {
	
	@Id
	private String id;
	private boolean success;
    private Integer code;
    @ManyToOne
    @JoinColumn(name="idmess")
    private MessageAlexisCedillo idMess;
    
    
    
	public MessageAlexisCedillo getIdMess() {
		return idMess;
	}
	public void setIdMess(MessageAlexisCedillo idMess) {
		this.idMess = idMess;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
    
    
}
