package hcmute.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hcmute.entity.GroupChat;
import hcmute.entity.User;
import hcmute.repositoty.GroupChatRepository;

@Service
public class GroupChatServiceImpl implements IGroupChatService{
	
	
	GroupChatRepository groupChatRepository;
	
	IUserService userService;
	
	@Autowired
	public GroupChatServiceImpl(GroupChatRepository groupChatRepository, IUserService userService) {
		this.groupChatRepository = groupChatRepository;
		this.userService = userService;
	}
	
	@Override
	public GroupChat createGroupChat(GroupChat groupChat) {
		return groupChatRepository.save(groupChat);
	}

	@Override
	public void addMember(Long groupId, Long userId) {
		GroupChat groupChat = groupChatRepository.findById(groupId).orElse(null);
        if (groupChat != null) {
            // Giả sử bạn có phương thức để tìm User bằng userId
        	Optional<User> optUser = userService.findById(userId);
            User user = optUser.get();
            groupChat.getMembers().add(user);
            groupChatRepository.save(groupChat);
        }
	}

	@Override
	public void removeMember(Long groupId, Long userId) {
		GroupChat groupChat = groupChatRepository.findById(groupId).orElse(null);
        if (groupChat != null) {
        	Optional<User> optUser = userService.findById(userId);
            User user = optUser.get();
            groupChat.getMembers().remove(user);
            groupChatRepository.save(groupChat);
        }
	}

	@Override
	public List<GroupChat> findAllGroups() {
		return groupChatRepository.findAll();
	}

	@Override
	public Optional<GroupChat> findById(Long id) {
		return groupChatRepository.findById(id);
	}
	
}
