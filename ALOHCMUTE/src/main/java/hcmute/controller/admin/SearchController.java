package hcmute.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import hcmute.entity.User;
import hcmute.service.ISearchService;
import hcmute.service.IUserService;

@Controller
@RequestMapping("admin/alohcmute")
public class SearchController {

	@Autowired(required = true)
	ISearchService searchService;
	
	@Autowired(required = true)
	IUserService userService;
	
	@GetMapping("/find_friends")
	public String list(@RequestParam("fullName") String fullName, ModelMap model) {
		// Gọi hàm searchUsersByFullName() trong service
		List<User> searchResults = searchService.searchUsersByFullName(fullName);
		// Chuyển dữ liệu từ list lên biến users
		model.addAttribute("users", searchResults);
		return "admin/alohcmute/find_friends";
	}
	@GetMapping("/find_friendsById/{user1}")
	public String listrequest(@PathVariable("user1") Long user1, ModelMap model) {
		// Gọi hàm searchUsersByFullName() trong service
		List<User> searchResults = searchService.findUsersByUserId(user1);
		// Chuyển dữ liệu từ list lên biến users
		model.addAttribute("users", searchResults);
		return "admin/alohcmute/info_request";
	}
	
	
}
