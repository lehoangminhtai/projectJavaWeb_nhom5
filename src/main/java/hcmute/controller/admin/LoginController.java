package hcmute.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import hcmute.entity.User;
import hcmute.service.IUserService;
import jakarta.servlet.http.HttpSession;
@Controller
public class LoginController {
	@Autowired
    IUserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage() {
        return "admin/alohcmute/login"; // Trả về trang login.jsp khi GET request đến /login
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView authenticateUser(@RequestParam("username") String username,
            @RequestParam("password") String password, HttpSession session) {
        boolean isAuthenticated = userService.authenticate(username, password);

        ModelAndView modelAndView = new ModelAndView();
        if (isAuthenticated) {
            User user = getUserByUsername(username); // Lấy thông tin từ cơ sở dữ liệu
            session.setAttribute("user", user);
            modelAndView.setViewName("redirect:/admin/alohcmute"); // Chuyển hướng đến trang chủ sau khi đăng nhập thành công
        } else {
            modelAndView.setViewName("admin/alohcmute/login"); // Trở lại trang đăng nhập với thông báo lỗi
            modelAndView.addObject("error", "Invalid username or password");
        }


        return modelAndView;
    }
    private User getUserByUsername(String username) {
        // Thay thế đoạn này bằng cách lấy thông tin người dùng từ cơ sở dữ liệu
        // Đây là ví dụ giả định, bạn cần thay thế nó bằng truy vấn thực tế
        User user = new User();
        user.setUsername(username);
        // Set các thông tin khác của người dùng
        return user;
    }
}
