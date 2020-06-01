package com.mx.avon.pruebaAvon.model;

public class MessageDTO {
	
	private String id;
	private boolean success;
    private Integer code;
    private MessageAlexisCedilloDTO messageAlexisCedilloDTO;
    
    
    
	public MessageAlexisCedilloDTO getMessageAlexisCedilloDTO() {
		return messageAlexisCedilloDTO;
	}
	public void setMessageAlexisCedilloDTO(MessageAlexisCedilloDTO messageAlexisCedilloDTO) {
		this.messageAlexisCedilloDTO = messageAlexisCedilloDTO;
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
