package hcmute.controller.admin;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hcmute.entity.Message;
import hcmute.entity.User;
import hcmute.service.IMessageService;
//import hcmute.service.IMessageService;
import hcmute.service.IUserService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/alohcmute/profile")
public class MessageController {

	@Autowired
    IMessageService messageService;

    @Autowired
    IUserService userService;

    @GetMapping("message/{userId}")
    public String messages(ModelMap model, HttpSession session, @PathVariable("userId") Long userId) {

        Long sender;
        if (session.getAttribute("user") != null) {
            User loggedInUser = (User) session.getAttribute("user");
            User userFromDatabase = userService.findByUsername(loggedInUser.getUsername());
            sender = userFromDatabase.getUserId();
        } else {
            return "admin/alohcmute/login";
        }

        List<Message> messages1 = messageService.findMessagesBySenderAndReceiver(sender, userId);
        List<Message> messages2 = messageService.findMessagesBySenderAndReceiver(userId, sender);

        for(Message messageToken: messages2) {
        	messages1.add(messageToken);
        }
        Collections.sort(messages1, (m1, m2) -> m1.getTimestamp().compareTo(m2.getTimestamp()));

        model.addAttribute("messages", messages1);
        Optional<User> usersender = userService.findById(sender);
        Optional<User> userreceiver = userService.findById(userId);
        User usersender1 = usersender.get();
        User userreceiver1 = userreceiver.get();
        model.addAttribute("senderId", sender);
        model.addAttribute("receiverId", userId);
        model.addAttribute("sender", usersender1);
        model.addAttribute("receiver", userreceiver1);

        return "admin/alohcmute/message";
    }

    @PostMapping("/message/send")
    public String sendMessage(
            @RequestParam Long receiverId,
            @RequestParam String messageContent,
            Principal principal,
            ModelMap model, HttpSession session
    ) {
        Long senderId;
        if (session.getAttribute("user") != null) {
            User loggedInUser = (User) session.getAttribute("user");
            User userFromDatabase = userService.findByUsername(loggedInUser.getUsername());
            senderId = userFromDatabase.getUserId();
        } else {
            return "admin/alohcmute/login";
        }
        
        Message newMessage = new Message();
        newMessage.setSender(senderId);
        newMessage.setReceiver(receiverId);
        newMessage.setContent(messageContent);
        newMessage.setTimestamp(LocalDateTime.now());

        messageService.saveMessage(newMessage);

        return "redirect:/admin/alohcmute/profile/message/" + receiverId;
    }
}
