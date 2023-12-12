package hcmute.controller.admin;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hcmute.entity.GroupChat;
import hcmute.entity.GroupMessage;
import hcmute.entity.User;
import hcmute.service.IGroupChatService;
import hcmute.service.IGroupMessageService;
import hcmute.service.IUserService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/alohcmute/groupmessage")
public class GroupMessageController {
	@Autowired
	IGroupMessageService groupMessageService;
	
	@Autowired
	IGroupChatService groupChatService;
	
	@Autowired
	IUserService userService;
	@PostMapping("/send")
    public String sendMessage(@ModelAttribute GroupMessage message, @RequestParam("groupChatId") Long groupChatId, HttpSession session) {
		GroupChat groupChat = groupChatService.findById(groupChatId).orElse(null);
	    message.setGroupChat(groupChat);
	    Long sender;
        if (session.getAttribute("user") != null) {
            User loggedInUser = (User) session.getAttribute("user");
            User userFromDatabase = userService.findByUsername(loggedInUser.getUsername());
            sender = userFromDatabase.getUserId();
        } else {
            return "admin/alohcmute/login";
        }
        Optional<User> optUser = userService.findById(sender);
        User user = optUser.get();
        message.setSender(user);
        
        message.setTimestamp(LocalDateTime.now());
		groupMessageService.sendMessage(message);
        return "redirect:/admin/alohcmute/groupmessage/view/" + message.getGroupChat().getGroupId();
    }

    @GetMapping("/view/{groupId}")
    public String viewMessages(@PathVariable Long groupId, Model model) {
    	List<GroupMessage> grmessage = groupMessageService.getMessagesByGroup(groupId);
        model.addAttribute("messages", grmessage);
        model.addAttribute("groupId", groupId);
        return "admin/alohcmute/groupmessage_list"; // JSP page to display messages in a group
    }
}
