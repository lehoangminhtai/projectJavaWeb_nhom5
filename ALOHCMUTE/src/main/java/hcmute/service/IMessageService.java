package hcmute.service;

import java.util.List;

import hcmute.entity.Message;


public interface IMessageService {
	List<Message> findMessagesBySenderAndReceiver(Long sender, Long receiver);
	void saveMessage(Message message);
}
