package hcmute.service;

import java.util.List;
import java.util.Optional;

import hcmute.entity.GroupChat;

public interface IGroupChatService {
	GroupChat createGroupChat(GroupChat groupChat);
    void addMember(Long groupId, Long userId);
    void removeMember(Long groupId, Long userId);
    List<GroupChat> findAllGroups();
	Optional<GroupChat> findById(Long id);
}
