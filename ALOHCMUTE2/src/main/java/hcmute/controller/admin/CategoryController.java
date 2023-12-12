package hcmute.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import java.util.stream.IntStream;

import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;

import org.springframework.util.StringUtils;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import hcmute.entity.Category;
import hcmute.entity.Friendship;
import hcmute.entity.Post;
import hcmute.entity.User;
import hcmute.model.CategoryModel;
import hcmute.service.ICategoryService;
import hcmute.service.ICommentService;
import hcmute.service.IFriendshipService;
import hcmute.service.ILikeService;
import hcmute.service.IPostService;
import hcmute.service.ISearchService;
import hcmute.service.IStorageService;
import hcmute.service.IUserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller

@RequestMapping("admin/alohcmute")
public class CategoryController {
	@Autowired(required = true)

	ICategoryService categoryService;

	@Autowired
	IStorageService storageService;

	@Autowired
	IUserService userService;

	@Autowired
	IFriendshipService friendshipService;

	@Autowired
	ISearchService searchService;

	@Autowired
	IPostService postService;

	@Autowired
	ICommentService commentService;

	@Autowired
	ILikeService likeService;

	@GetMapping("add")

	public String add(ModelMap model) {

		CategoryModel cateModel = new CategoryModel();

		cateModel.setIsEdit(false);

		// chuyển dữ liệu từ model vào biến category để đưa lên view

		model.addAttribute("category", cateModel);

		return "admin/alohcmute/addOrEdit";

	}

	@PostMapping("saveOrUpdate")

	public ModelAndView saveOrUpdate(ModelMap model,

			@Valid @ModelAttribute("category") CategoryModel cateMdoel, BindingResult result) {

		if (result.hasErrors()) {

			return new ModelAndView("admin/alohcmute/addOrEdit");

		}

		Category entity = new Category();

		// copy từ Model sang Entity

		BeanUtils.copyProperties(cateMdoel, entity);
		if (!cateMdoel.getImageFile().isEmpty()) {
			UUID uuid = UUID.randomUUID();
			String uuString = uuid.toString();

			entity.setIcon(storageService.getStorageFilename(cateMdoel.getImageFile(), uuString));
			storageService.store(cateMdoel.getImageFile(), entity.getIcon());
		}

		// gọi hàm save trong service

		categoryService.save(entity);

		// đưa thông báo về cho biến message

		String message = "";

		if (cateMdoel.getIsEdit() == true) {

			message = "Category is Edited!!!!!!!!";

		} else {

			message = "Category is saved!!!!!!!!";

		}

		model.addAttribute("message", message);

		// redirect về URL controller

		return new ModelAndView("forward:/admin/alohcmute", model);

	}

	@RequestMapping("")

	public String list(ModelMap model, HttpSession session) {

		// gọi hàm findAll() trong service

		List<Category> list = categoryService.findAll();

		// chuyển dữ liệu từ list lên biến categories

		model.addAttribute("categories", list);

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

		int numbersOfFriend = user.size();
		model.addAttribute("countFriend", numbersOfFriend);
		model.addAttribute("listfriends", user);

		List<User> usersuggestion = userService.findAll();
		List<User> usersToKeep = new ArrayList<>();
		for (User usertest : usersuggestion) {
			Long UserID1 = usertest.getUserId();
			Long UserID2;
			if (session.getAttribute("user") != null) {
				User loggedInUser = (User) session.getAttribute("user");
				User userFromDatabase = userService.findByUsername(loggedInUser.getUsername());
				UserID2 = userFromDatabase.getUserId();
			} else {
				return "admin/alohcmute/login";
			}
			List<Friendship> checkFriendShip1 = friendshipService.findFriendshipsByUser1AndUser2(UserID1, UserID2);
			List<Friendship> checkFriendShip2 = friendshipService.findFriendshipsByUser1AndUser2(UserID2, UserID1);
			if (checkFriendShip1.isEmpty() && checkFriendShip2.isEmpty()) {
				usersToKeep.add(usertest);
			}
		}
		usersuggestion = usersToKeep;

		Long userid;
		if (session.getAttribute("user") != null) {
			User loggedInUser = (User) session.getAttribute("user");
			User userFromDatabase = userService.findByUsername(loggedInUser.getUsername());
			userid = userFromDatabase.getUserId();

		} else {
			return "admin/alohcmute/login";
		}

		List<Post> posts = postService.findAllByOrderByPostDateDesc();

		// Create a map to store user names by user ID
		Map<Long, String> userNameMap = new HashMap<>();
		Map<Long, String> userImageMap = new HashMap<>();
		Map<Long, Integer> likeCountMap = new HashMap<>();
		for (Post post : posts) {
			Long postId = post.getPostid();
			int likeCount = likeService.countLikesByPostId(post);
			likeCountMap.put(postId, likeCount);
		}

		// Fetch usernames for each post's userid
		for (Post post : posts) {
			Long userId = post.getUserid().getUserId();
			Optional<User> optUser = userService.findById(userId);
			User userName = optUser.get();
			User userImage = optUser.get();
			// DOI FULL NAME Ơ DAYYYYYYYY
			String name = userName.getFullName();
			String image = userImage.getAvatar();
			userNameMap.put(userId, name);
			userImageMap.put(userId, image);
		}

		
		// Add posts and username map to the model
		
		model.addAttribute("post", posts);
		model.addAttribute("user", userNameMap);
		model.addAttribute("user2", userImageMap);

		// ===================================
		model.addAttribute("userid", userid);
		model.addAttribute("likeCount", likeCountMap);
		model.addAttribute("usersuggestion", usersuggestion);
		return "admin/alohcmute/list";

	}

	@GetMapping("edit/{categoryId}")

	public ModelAndView edit(ModelMap model, @PathVariable("categoryId") Long categoryId) {

		Optional<Category> optCategory = categoryService.findById(categoryId);

		CategoryModel cateModel = new CategoryModel();

		// kiểm tra sự tồn tại của category

		if (optCategory.isPresent()) {

			Category entity = optCategory.get();

			// copy từ entity sang cateModel

			BeanUtils.copyProperties(entity, cateModel);

			cateModel.setIsEdit(true);

			// đẩy dữ liệu ra view

			model.addAttribute("category", cateModel);

			return new ModelAndView("admin/alohcmute/addOrEdit", model);

		}

		model.addAttribute("message", "Category is not existed!!!!");

		return new ModelAndView("forward:/admin/alohcmute", model);

	}

	@GetMapping("delete/{categoryId}")

	public ModelAndView delet(ModelMap model, @PathVariable("categoryId") Long categoryId) {

		categoryService.deleteById(categoryId);

		model.addAttribute("message", "Category is deleted!!!!");

		return new ModelAndView("forward:/admin/alohcmute", model);

	}

	@GetMapping("search")

	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {

		List<Category> list = null;

		// có nội dung truyền về không, name là tùy chọn khi required=false

		if (StringUtils.hasText(name)) {

			list = categoryService.findByCategoryNameContaining(name);

		} else {

			list = categoryService.findAll();

		}

		model.addAttribute("categories", list);

		return "admin/alohcmute/search";

	}

	@RequestMapping("searchpaginated")

	public String search(ModelMap model,

			@RequestParam(name = "categoryName", required = false) String name,

			@RequestParam("page") Optional<Integer> page,

			@RequestParam("size") Optional<Integer> size) {

		int count = (int) categoryService.count();

		int currentPage = page.orElse(1);

		int pageSize = size.orElse(3);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("categoryName"));

		Page<Category> resultPage = null;

		if (StringUtils.hasText(name)) {

			resultPage = categoryService.findByCategoryNameContaining(name, pageable);

			model.addAttribute("name", name);

		} else {

			resultPage = categoryService.findAll(pageable);

		}

		int totalPages = resultPage.getTotalPages();

		if (totalPages > 0) {

			int start = Math.max(1, currentPage - 2);

			int end = Math.min(currentPage + 2, totalPages);

			if (totalPages > count) {

				if (end == totalPages)
					start = end - count;

				else if (start == 1)
					end = start + count;

			}

			List<Integer> pageNumbers = IntStream.rangeClosed(start, end)

					.boxed()

					.collect(Collectors.toList());

			model.addAttribute("pageNumbers", pageNumbers);

		}

		model.addAttribute("categoryPage", resultPage);

		return "admin/alohcmute/searchpaginated";

	}
	
	@GetMapping("delete/{postid}/{userid}")
	public ModelAndView deletePost(ModelMap model, @PathVariable("postid") Long postid, @PathVariable("userid") Long userid) {
	    User user = userService.getCurrentUserById(userid);
	    Optional<Post> optPost = postService.findById(postid);
	    postService.deleteById(postid);
	    if (optPost.isPresent()) {
	        Post post = optPost.get();
	        if (post.getUserid() == user) { // Kiểm tra xem người dùng có phải là chủ sở hữu của bài viết hay không
	        	postService.deletePost(postid);
	            model.addAttribute("message", "Post is deleted.");
	        } else {
	            model.addAttribute("message", "You are not authorized to delete this post.");
	        }
	    } else {
	        model.addAttribute("message", "Post is not existed.");
	    }

	    return new ModelAndView("redirect:/admin/alohcmute");
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
