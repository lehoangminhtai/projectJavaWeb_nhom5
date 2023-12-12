package hcmute.controller.admin;

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
import hcmute.service.IGroupChatService;

@Controller
@RequestMapping("/admin/alohcmute/groupchat")
public class GroupChatController {
	@Autowired
	IGroupChatService groupChatService;
	@PostMapping("/create")
    public String createGroupChat(@ModelAttribute GroupChat groupChat, Model model) {
        groupChatService.createGroupChat(groupChat);
        return "redirect:/admin/alohcmute/groupchat/all";
    }
	
	@RequestMapping("/createGC")
    public String createGroupChatForm() {
        
        return "/admin/alohcmute/createGC";
    }

    @GetMapping("/all")
    public String viewAllGroups(Model model) {
        model.addAttribute("groups", groupChatService.findAllGroups());
        return "/admin/alohcmute/groupchat_list"; // JSP page to display all groups
    }

    @PostMapping("/addMember")
    public String addMemberToGroup(@RequestParam Long groupId, @RequestParam Long userId) {
        groupChatService.addMember(groupId, userId);
        return "redirect:/admin/alohcmute/groupchat/view/" + groupId;
    }

    @PostMapping("/removeMember")
    public String removeMemberFromGroup(@RequestParam Long groupId, @RequestParam Long userId) {
        groupChatService.removeMember(groupId, userId);
        return "redirect:/admin/alohcmute/groupchat/view/" + groupId;
    }

    @GetMapping("/view/{groupId}")
    public String viewGroup(@PathVariable Long groupId, Model model) {
    	Optional<GroupChat> optGroupChat = groupChatService.findById(groupId);
        GroupChat groupChat = optGroupChat.get();
        model.addAttribute("group", groupChat);
        return "/admin/alohcmute/groupchat_view"; // JSP page to display a single group
    }
}
