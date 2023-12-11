package hcmute.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import hcmute.entity.Friendship;
import hcmute.entity.User;
import hcmute.service.IFriendshipService;
import hcmute.service.ISearchService;
import hcmute.service.IUserService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/alohcmute/listfriends/all")
public class FriendController {
	@Autowired
	IUserService userService;
	
	@Autowired
	IFriendshipService friendshipService;
	
	@Autowired
	ISearchService searchService;
	
	@RequestMapping("")
	public String listfriends(ModelMap model, HttpSession session) {
		List<User> user = new ArrayList<User>();
		if (session.getAttribute("user") != null) {
			String status = "Accepted";
			User loggedInUser = (User) session.getAttribute("user");
			User userFromDatabase = userService.findByUsername(loggedInUser.getUsername());
			Long UserID2 = userFromDatabase.getUserId();
			List<Friendship> fships = friendshipService.getFriendshipList2(UserID2, status);
			for (Friendship friendship : fships) {
				List<User> user1 = searchService.findUsersByUserId(friendship.getUser1());
				for (User US : user1) {
					user.add(US);
				}
			}
			List<Friendship> fships2 = friendshipService.getFriendshipList1(UserID2, status);
			for (Friendship friendship : fships2) {
				List<User> user2 = searchService.findUsersByUserId(friendship.getUser2());
				for (User US : user2) {
					user.add(US);
				}
			}
		} else {
			return "admin/alohcmute/login";
		}

		model.addAttribute("listfriends", user);
		return "admin/alohcmute/list_friends";
	}
	
	@GetMapping("remove/{userId}")
    public ModelAndView removeFriend(@PathVariable("userId") Long userId, HttpSession session) {
		Long userId2 = null;
		if (session.getAttribute("user") != null) {
			User loggedInUser = (User) session.getAttribute("user");
			User userFromDatabase = userService.findByUsername(loggedInUser.getUsername());
			userId2 = userFromDatabase.getUserId();
		}
		
        friendshipService.removeFriend(userId, userId2);
        friendshipService.removeFriend(userId2, userId);
        
        return new ModelAndView("forward:/admin/alohcmute/listfriends/all");
    }
}
