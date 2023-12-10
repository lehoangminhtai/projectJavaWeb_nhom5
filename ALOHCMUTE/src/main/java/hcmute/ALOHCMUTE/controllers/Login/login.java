package hcmute.ALOHCMUTE.controllers.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hcmute.ALOHCMUTE.entity.User;
import hcmute.ALOHCMUTE.services.IUserService;

@Controller
@RequestMapping("/login")
public class login {

	@Autowired
	IUserService userService;
	
	@RequestMapping("")
   public String userProfile(Model model) {
       // Lấy thông tin người dùng từ UserService hoặc UserRepository
      
       return "/admin/login/login";
   }
	
	@RequestMapping("signup")
	public String SignUp() {
		return "/admin/login/signup";
	}

   @PostMapping("login")
   public String userPost(@RequestParam("username") String username,Model model) {
	   try {
       User currentUser = userService.getCurrentUser(username);
       model.addAttribute("user", currentUser);
       return "redirect:/posts/post?userid=" + currentUser.getUserid();
	   }
	   catch (Exception e){
		   model.addAttribute("errorMessage", "Bạn chưa có tài khoản, vui lòng tạo tài khoản.");
		    return "admin/login/error"; 
	   }
   }
  
}
