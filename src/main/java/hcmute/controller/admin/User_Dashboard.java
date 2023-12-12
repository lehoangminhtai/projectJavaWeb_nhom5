package hcmute.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hcmute.entity.Post;
import hcmute.entity.User;
import hcmute.service.ICommentService;
import hcmute.service.ILikeService;
import hcmute.service.IPostService;
import hcmute.service.IUserService;


@Controller
@RequestMapping("user/dashboard")
public class User_Dashboard {
	@Autowired
	ICommentService commentService;
	
	@Autowired
	ILikeService likeService;
	
	@Autowired
	IPostService postService;
	
	@Autowired
	IUserService userService;
	
	@GetMapping("{userid}")
	public String list(ModelMap model,@PathVariable("userid") Long userid, @RequestParam int countFriend) {
		User user = userService.getCurrentUserById(userid);
		model.addAttribute("user", user);
		
		int numPost = postService.countPostByUserId(user);
		model.addAttribute("numPost", numPost);
		
		int numLike = likeService.countLikesByUserId(user);
		model.addAttribute("numLike", numLike);
		
		List<Post> posts = postService.findAllPostByUserId(user);
		model.addAttribute("posts", posts);
		
		Map<Post, Integer> commentCountMap = new HashMap<>();
		Map<Post, Integer> likeCountMap = new HashMap<>();
		 for (Post post : posts) {
		       
		      int numberCmt = commentService.countCommentsByPostId(post);
		      commentCountMap.put(post, numberCmt);
		      
		      int numberLike = likeService.countLikesByPostId(post);
		      likeCountMap.put(post, numberLike);
		 }
		 
		model.addAttribute("cmtcount", commentCountMap);
		model.addAttribute("likecount", likeCountMap);
		model.addAttribute("countFriends", countFriend);
		return "user/dashboard/DashBoard";
	}
}
