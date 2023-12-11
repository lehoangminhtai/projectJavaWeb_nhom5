package hcmute.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hcmute.entity.GroupMessage;
import hcmute.repositoty.GroupMessageRepository;

@Service
public class GroupMessageServiceImpl implements IGroupMessageService{
	@Autowired
	GroupMessageRepository groupMessageRepository;

	public GroupMessageServiceImpl(GroupMessageRepository groupMessageRepository) {
		this.groupMessageRepository = groupMessageRepository;
	}

	@Override
	public GroupMessage sendMessage(GroupMessage message) {
		return groupMessageRepository.save(message);
	}

	@Override
	public List<GroupMessage> getMessagesByGroup(Long groupId) {
		return groupMessageRepository.findByGroupChatGroupId(groupId);
	}
	
}
