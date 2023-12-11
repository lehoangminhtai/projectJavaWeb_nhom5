package hcmute.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import hcmute.entity.Post;
import hcmute.entity.User;
import hcmute.service.ICommentService;
import hcmute.service.ILikeService;
import hcmute.service.IPostService;
import hcmute.service.IUserService;


@Controller
@RequestMapping("admin/dashboard")
public class Admin_Dashboard {
	@Autowired
	IPostService postService;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	ICommentService commentService;
	
	@Autowired
	ILikeService likeService;
	
	@RequestMapping("")
	public String list(ModelMap model) {
		Long numUser = userService.count();
		model.addAttribute("numUser", numUser);
		
		Long numPost = postService.countPost();
		model.addAttribute("numPost", numPost);
		
		List<Post> topPostLike = likeService.topLike();
		List<Post> topPostCmt = commentService.topCmt();
		
		model.addAttribute("postLike", topPostLike);
		model.addAttribute("postCmt", topPostCmt);
		
		Map<Long, String> userNameMap_toplike = new HashMap<>();
		Map<Long, String> userNameMap_topcmt = new HashMap<>();
		
		Map<Post, Integer> commentCountMap = new HashMap<>();
		Map<Post, Integer> likeCountMap = new HashMap<>();
		
		 for (Post post : topPostLike) {
			 Long userId = post.getUserid().getUserId();
			 User userName = userService.getCurrentUserById(userId);
			 
			 String name = userName.getUsername();
			 userNameMap_toplike.put(userId, name);
			 
			 int numberLike = likeService.countLikesByPostId(post);
		     likeCountMap.put(post, numberLike);
		 }
		 
		 for (Post post : topPostCmt) {
			 Long userId = post.getUserid().getUserId();
			 User userName = userService.getCurrentUserById(userId);
			 
			 String name = userName.getUsername();
			 userNameMap_topcmt.put(userId, name);
			 
			 int numberCmt = commentService.countCommentsByPostId(post);
		     commentCountMap.put(post, numberCmt);
		 }
		 
		 model.addAttribute("cmtcount", commentCountMap);
		model.addAttribute("likecount", likeCountMap);
		 
		 model.addAttribute("userTopLike", userNameMap_toplike);
		 model.addAttribute("userTopCmt", userNameMap_topcmt);
		 
		return "admin/dashboard/DashBoard";
		
		
	}
}
