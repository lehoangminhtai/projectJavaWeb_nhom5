package hcmute.controller.admin;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import hcmute.entity.Friendship;
import hcmute.entity.User;
import hcmute.model.CategoryModel;
import hcmute.model.FriendshipModel;
import hcmute.model.UserModel;
import hcmute.service.IFriendshipService;
import hcmute.service.ISearchService;
import hcmute.service.IStorageService;
import hcmute.service.IUserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/alohcmute/profile")
public class UserController {
	@Autowired
	IUserService userService;

	@Autowired
	IStorageService storageService;

	@Autowired
	IFriendshipService friendshipService;

	@Autowired
	ISearchService searchService;

	@GetMapping("")
	public ModelAndView showUserProfile(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();

		if (session.getAttribute("user") != null) {
			User loggedInUser = (User) session.getAttribute("user");
			User userFromDatabase = userService.findByUsername(loggedInUser.getUsername());

			modelAndView.addObject("user", userFromDatabase);
			modelAndView.setViewName("admin/alohcmute/profile");
		} else {
			modelAndView.setViewName("redirect:/login");
		}

		return modelAndView;
	}

	@GetMapping("addfriends/{userId}")
	public String add(ModelMap model, @PathVariable("userId") Long userId, HttpSession session) {

		if (session.getAttribute("user") != null) {
			User loggedInUser = (User) session.getAttribute("user");
			User userFromDatabase = userService.findByUsername(loggedInUser.getUsername());
			Long UserID1 = userFromDatabase.getUserId();
			Long UserID2 = userId;
			List<Friendship> checkFriendShip1 = friendshipService.findFriendshipsByUser1AndUser2(UserID1, UserID2);
			List<Friendship> checkFriendShip2 = friendshipService.findFriendshipsByUser1AndUser2(UserID2, UserID1);
			if (!checkFriendShip1.isEmpty() || !checkFriendShip2.isEmpty()) {
				model.addAttribute("message", "Failed to send friend request!");
				return "admin/alohcmute/find_friends";
			} else {
				String status = "Pending";
				LocalDateTime timestamp = LocalDateTime.now();
				FriendshipModel fsModel = new FriendshipModel();
				fsModel.setUser1(UserID1);
				fsModel.setUser2(UserID2);
				fsModel.setStatus(status);
				fsModel.setTimestamp(timestamp);
				fsModel.setIsEdit(false);

				Friendship entity = new Friendship();

				BeanUtils.copyProperties(fsModel, entity);

				friendshipService.save(entity);
				model.addAttribute("message", "Friend request sent successfully!");
			}

		} else {
			return "admin/alohcmute/login";
		}

		return "admin/alohcmute/find_friends";

	}

	@GetMapping("acceptfriend/{userId}")
	public String accept(ModelMap model, @PathVariable("userId") Long userId, HttpSession session) {

		if (session.getAttribute("user") != null) {
			Long UserID1 = userId;
			User loggedInUser = (User) session.getAttribute("user");
			User userFromDatabase = userService.findByUsername(loggedInUser.getUsername());
			Long UserID2 = userFromDatabase.getUserId();
			List<Friendship> fships = friendshipService.findFriendshipsByUser1AndUser2(UserID1, UserID2);
			for (Friendship friendship : fships) {
				friendship.setStatus("Accepted");
				friendshipService.save(friendship);
			}
		} else {
			return "admin/alohcmute/login";
		}

		return "admin/alohcmute/info_request";

	}

	@GetMapping("listfriends")
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
		} else {
			return "admin/alohcmute/login";
		}

		model.addAttribute("listfriends", user);
		return "admin/alohcmute/list";

	}

	@GetMapping("rejectedfriend/{userId}")
	public String rejected(ModelMap model, @PathVariable("userId") Long userId, HttpSession session) {

		if (session.getAttribute("user") != null) {
			Long UserID1 = userId;
			User loggedInUser = (User) session.getAttribute("user");
			User userFromDatabase = userService.findByUsername(loggedInUser.getUsername());
			Long UserID2 = userFromDatabase.getUserId();
			List<Friendship> fships = friendshipService.findFriendshipsByUser1AndUser2(UserID1, UserID2);
			for (Friendship friendship : fships) {
				friendship.setStatus("Rejected");
				friendshipService.save(friendship);
			}
		} else {
			return "admin/alohcmute/login";
		}

		return "admin/alohcmute/info_request";

	}

	@GetMapping("friends/{username}")
	public ModelAndView showUserProfile(@PathVariable("username") String username) {
		ModelAndView modelAndView = new ModelAndView();

		if (username != null) {
			User userFromDatabase = userService.findByUsername(username);

			modelAndView.addObject("user", userFromDatabase);
			modelAndView.setViewName("admin/alohcmute/profile_friend");
		} else {
			modelAndView.setViewName("redirect:/login");
		}

		return modelAndView;
	}

	@GetMapping("FriendRequestNotification")
	public String listFriendRequest(HttpSession session, ModelMap model) {
		if (session.getAttribute("user") != null) {
			User loggedInUser = (User) session.getAttribute("user");
			User userFromDatabase = userService.findByUsername(loggedInUser.getUsername());
			Long UserID2 = userFromDatabase.getUserId();
			List<Friendship> fship = friendshipService.getFriendshipsByUser2(UserID2);
			model.addAttribute("friends", fship);

		} else {
			return "admin/alohcmute/login";
		}
		return "admin/alohcmute/accept_friend";
	}

	@GetMapping("edit/{userId}")
	public ModelAndView edit(ModelMap model, @PathVariable("userId") Long userId) {
		Optional<User> optUser = userService.findById(userId);
		UserModel userModel = new UserModel();

		if (optUser.isPresent()) {
			User entity = optUser.get();
			BeanUtils.copyProperties(entity, userModel);
			userModel.setIsEdit(true);
			model.addAttribute("user", userModel);
			return new ModelAndView("admin/alohcmute/profile_edit", model);
		}

		model.addAttribute("message", "User is not existed!!!!");
		return new ModelAndView("forward:/admin/alohcmute/profile", model);
	}

	@RequestMapping(value = "/UpdateUser", method = RequestMethod.POST)
	public ModelAndView UpdateUser(ModelMap model, @Valid @ModelAttribute("user") UserModel userModel,
			BindingResult result) {

		// Kiểm tra lỗi hợp lệ
		if (result.hasErrors()) {
			return new ModelAndView("admin/alohcmute/profile_edit");
		}

		// Logic cập nhật người dùng ở đây
		User entity = new User();
		BeanUtils.copyProperties(userModel, entity);

		if (!userModel.getAvatarFile().isEmpty()) {
			UUID uuid = UUID.randomUUID();
			String uuString = uuid.toString();
			entity.setAvatar(storageService.getStorageFilename(userModel.getAvatarFile(), uuString));
			storageService.store(userModel.getAvatarFile(), entity.getAvatar());
			userService.save(entity);
			return new ModelAndView("redirect:/admin/alohcmute/profile");
		}

		userService.save(entity);
		String message = userModel.getIsEdit() ? "User is Edited!!!!!!!!" : "User is saved!!!!!!!!";
		model.addAttribute("message", message);

		return new ModelAndView("forward:/admin/alohcmute/profile", model);
	}

	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serverFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

}
