package com.mx.avon.pruebaAvon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.avon.pruebaAvon.model.Message;

public interface IMessageReposiroty extends JpaRepository<Message, Long> {

}
