package hcmute.service;

import java.util.List;

import hcmute.entity.GroupMessage;

public interface IGroupMessageService {
	GroupMessage sendMessage(GroupMessage message);
    List<GroupMessage> getMessagesByGroup(Long groupId);
}
