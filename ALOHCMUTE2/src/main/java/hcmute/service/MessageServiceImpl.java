package hcmute.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hcmute.entity.Message;
import hcmute.entity.MessageSR;
import hcmute.repositoty.MessageRepository;

@Service
public class MessageServiceImpl implements IMessageService{

	@Autowired
	MessageRepository messageRepository;

	public MessageServiceImpl(MessageRepository messageSRRepository) {
		this.messageRepository = messageSRRepository;
	}

	@Override
	public List<Message> findMessagesBySenderAndReceiver(Long sender, Long receiver) {
		return messageRepository.findBySenderAndReceiver(sender, receiver);
	}

	@Override
	public void saveMessage(Message message) {
		messageRepository.save(message);
		
	}

	
	
}
