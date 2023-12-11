package hcmute.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hcmute.entity.User;
import hcmute.service.IUserService;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	@Autowired
	IUserService userService;
	
	@GetMapping("")
    public String showRegisterForm() {
        return "admin/alohcmute/register"; // Trả về trang đăng ký
    }

    @PostMapping("")
    public String register(User user) {
        userService.registerNewUser(user);
        return "redirect:/login"; // Chuyển hướng người dùng đến trang đăng nhập sau khi đăng ký thành công
    }
}
