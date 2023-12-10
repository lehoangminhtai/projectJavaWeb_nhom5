package hcmute.ALOHCMUTE.controllers.dashborad;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hcmute.ALOHCMUTE.entity.Post;
import hcmute.ALOHCMUTE.entity.User;
import hcmute.ALOHCMUTE.services.ICommentService;
import hcmute.ALOHCMUTE.services.ILikeService;
import hcmute.ALOHCMUTE.services.IPostService;
import hcmute.ALOHCMUTE.services.IUserService;

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
		
		Map<Integer, String> userNameMap_toplike = new HashMap<>();
		Map<Integer, String> userNameMap_topcmt = new HashMap<>();
		
		Map<Post, Integer> commentCountMap = new HashMap<>();
		Map<Post, Integer> likeCountMap = new HashMap<>();
		
		 for (Post post : topPostLike) {
			 int userId = post.getUserid().getUserid();
			 User userName = userService.getCurrentUserById(userId);
			 
			 String name = userName.getUserName();
			 userNameMap_toplike.put(userId, name);
			 
			 int numberLike = likeService.countLikesByPostId(post);
		     likeCountMap.put(post, numberLike);
		 }
		 
		 for (Post post : topPostCmt) {
			 int userId = post.getUserid().getUserid();
			 User userName = userService.getCurrentUserById(userId);
			 
			 String name = userName.getUserName();
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
