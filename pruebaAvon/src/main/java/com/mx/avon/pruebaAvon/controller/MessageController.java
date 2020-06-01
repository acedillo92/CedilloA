package com.mx.avon.pruebaAvon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mx.avon.pruebaAvon.model.MessageAlexisCedilloDTO;
import com.mx.avon.pruebaAvon.model.MessageDTO;
import com.mx.avon.pruebaAvon.service.IMessageService;
import com.mx.avon.pruebaAvon.utils.ClientUtils;

@RestController
public class MessageController {
	
	@Autowired
	IMessageService imesageService;
	
	@PostMapping(path = "/sendMessage", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?>  sendMessage (@RequestBody MessageAlexisCedilloDTO rq){
		ResponseEntity<MessageDTO> response = null;
		try {
			MessageDTO meResp = imesageService.sendMessageService(rq);
			return response.ok(meResp);
		}catch (Exception e) {
			// TODO: handle exception
						System.out.println(e.getMessage());
						return response.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
		
		
	}

}
