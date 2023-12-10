//package hcmute.ALOHCMUTE.controllers.User;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import hcmute.ALOHCMUTE.entity.User;
//import hcmute.ALOHCMUTE.services.IUserService;
//
//@Controller
//@RequestMapping("users/user")
//public class UserController {
//	@Autowired
//	IUserService userService;
//	
//	@RequestMapping("")
//	public String list(ModelMap model) {
//		//goi ham findAll trong service
//		List<User> list = userService.findAll();
//		//chuyen du lieu tu list len bien user
//		model.addAttribute("user", list);
//		return "admin/categories/list";
//	}
//	
//}
