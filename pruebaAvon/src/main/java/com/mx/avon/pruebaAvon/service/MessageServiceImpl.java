package com.mx.avon.pruebaAvon.service;

import javax.json.JsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mx.avon.pruebaAvon.model.Message;
import com.mx.avon.pruebaAvon.model.MessageAlexisCedillo;
import com.mx.avon.pruebaAvon.model.MessageAlexisCedilloDTO;
import com.mx.avon.pruebaAvon.model.MessageDTO;
import com.mx.avon.pruebaAvon.repository.IMessageAlexisCedilloRespository;
import com.mx.avon.pruebaAvon.repository.IMessageReposiroty;
import com.mx.avon.pruebaAvon.utils.ClientUtils;

@Service
public class MessageServiceImpl implements IMessageService {
	
	@Autowired
	ClientUtils clientUtils;
	
	@Autowired
	IMessageAlexisCedilloRespository iMessageAlexisCedilloRespository;
	
	@Autowired
	IMessageReposiroty iMessageReposiroty;

	@Override
	public MessageDTO sendMessageService(MessageAlexisCedilloDTO rq) {
		// TODO Auto-generated method stub
		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonObj = mapper.writeValueAsString(rq);
			clientUtils.paramaeterValidate(jsonObj);
			JsonObject object = clientUtils.getClient(rq);
			boolean success = object.getBoolean("success");
			Integer code = object.getInt("code");
			String id = object.getString("id");
			
			MessageAlexisCedillo msac = new MessageAlexisCedillo();
			msac.setMessage(rq.getMessage());
			msac.setNumber(rq.getNumber());
			msac.setPassword(rq.getPassword());
			msac.setUsername(rq.getUsername());
			
			msac = iMessageAlexisCedilloRespository.save(msac);
			
			Message ms = new Message();
			ms.setCode(code);
			ms.setId(id);
			ms.setIdMess(msac);
			ms.setSuccess(success);
			
			ms = iMessageReposiroty.save(ms);
			
			MessageDTO msDTO = new MessageDTO();
			msDTO.setCode(code);
			msDTO.setId(id);
			msDTO.setMessageAlexisCedilloDTO(rq);
			msDTO.setSuccess(success);
			
			return msDTO;
			
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
	}

}
