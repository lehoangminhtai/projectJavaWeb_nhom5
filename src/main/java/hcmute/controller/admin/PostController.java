package hcmute.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import hcmute.entity.Comments;
import hcmute.entity.Post;
import hcmute.entity.User;
import hcmute.model.CommentModel;
import hcmute.model.PostModel;
import hcmute.service.ICommentService;
import hcmute.service.ILikeService;
import hcmute.service.IPostService;
import hcmute.service.IStorageService;
import hcmute.service.IUserService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("posts/post")
public class PostController {
	@Autowired
	ICommentService commentService;
	 
	@Autowired
	IPostService postService;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IStorageService storageService;
	
	@Autowired
	ILikeService likeService;
	
	@GetMapping("check-like/{postid}/{userid}")
    public ResponseEntity<Boolean> checkLike(@PathVariable Post postid, @PathVariable User userid) {
		boolean isLiked = likeService.existsByUserIdAndPostId(userid, postid);
        return new ResponseEntity<>(isLiked, HttpStatus.OK);
    }
	
	@DeleteMapping("{postid}/{userid}/like")
    public ResponseEntity<Void> deleteLike(@PathVariable Post postid, @PathVariable User userid) {
		likeService.deleteLike(userid, postid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@PostMapping("{postid}/{userid}/like")
    public ResponseEntity<Boolean> likePost(@PathVariable("postid") Post postid, @PathVariable("userid") User userid) {
		likeService.insertLike(userid, postid);
		return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@RequestMapping("")
	public String list(ModelMap model,@RequestParam("userid") int userid, Model model1) {
	
	//=============================	
		List<Post> posts = postService.findAllByOrderByPostDateDesc();

	    // Create a map to store user names by user ID
	    Map<Long, String> userNameMap = new HashMap<>();

	    Map<Long, Integer> likeCountMap = new HashMap<>();
	    for (Post post : posts) {
	        Long postId = post.getPostid();
	        int likeCount = likeService.countLikesByPostId(post);
	        likeCountMap.put(postId, likeCount);
	    }
	    
	    // Fetch usernames for each post's userid
	    for (Post post : posts) {
	    	Long userId = post.getUserid().getUserId();
	        User userName = userService.getCurrentUserById(userId);
	       // DOI FULL NAME Ơ DAYYYYYYYY
	        String name = userName.getUsername();
	        userNameMap.put(userId, name);
	    }

	    // Add posts and username map to the model
	    model.addAttribute("post", posts);
	    model.addAttribute("user", userNameMap);
		
	//===================================
		model.addAttribute("userid", userid);
		model.addAttribute("likeCount", likeCountMap);

		return "admin/post/list";
	}
	
	@GetMapping("exit/{userid}")
	public String userPost(ModelMap model,@PathVariable("userid") int userid) {
		return "redirect:/admin/alohcmute";
	   }
	
	@PostMapping("comment/{postid}/{currentUser}/exit/{userid}")
	public String userCmt(ModelMap model,@PathVariable("userid") int userid) {
		return "redirect:/admin/alohcmute";
	   }
	
	@GetMapping("add/{userid}")
	public String add(ModelMap model,@PathVariable("userid") int userid) {
		PostModel postModel = new PostModel();
		postModel.setIsEdit(false);
		model.addAttribute("post", postModel);
		model.addAttribute("userid", userid);
		return "admin/post/EditPost";
	}
	

	
	@GetMapping("comment/{postid}/{currentUser}/{userid}")
	public ModelAndView Comment(ModelMap model,
			@PathVariable("userid") Long userid,  @PathVariable("postid") Long postid,
			@PathVariable("currentUser") Long currentUser) {
		Optional<Post> optPost = postService.findById(postid);
		
		PostModel postModel = new PostModel();
		
		User user = userService.getCurrentUserById(userid);
		User userOwnPost = userService.getCurrentUserById(currentUser);
		if (optPost.isPresent()) {
			Post entity = optPost.get();
			List<Comments> comments = postService.getCommentsByPostId(entity);
			
			Map<Long, String> userNameMap = new HashMap<>();
			
			    // Fetch usernames for each post's userid
			    for (Comments comment : comments) {
			    	Long userId = comment.getUserid().getUserId();
			        User userName = userService.getCurrentUserById(userId);
			       // DOI FULL NAME Ơ DAYYYYYYYY
			        String name = userName.getUsername();
			        userNameMap.put(userId, name);
			    }

			    // Add posts and username map to the model
			    model.addAttribute("user", userNameMap);
			
			BeanUtils.copyProperties(entity, postModel);
			postModel.setUserid(user);
			model.addAttribute("post", postModel);
		//	model.addAttribute("username", user.getUserName());
			model.addAttribute("ownername", userOwnPost.getUsername());
			model.addAttribute("userid", userid);
			
			model.addAttribute("comment", comments);
			return new ModelAndView("admin/post/Comment", model);
		}
		
		model.addAttribute("message", "Post is not existed.");
		return new ModelAndView("forward:/posts/post", model);
	}
	
//	@PostMapping("cmtSave")
//	public ModelAndView cmtSave(ModelMap model, @Valid @ModelAttribute("cmtModel") CommentModel cmtModel, BindingResult result) {
//		
//		if(result.hasErrors()) {
//			return new ModelAndView("admin/post/Comment");
//		}
//		Comments entity = new Comments();
//		BeanUtils.copyProperties(cmtModel, entity);
//		
//
//		
//		commentService.save(entity);
//		return new ModelAndView("forward:/posts/post", model);
//		
//	}
	@RequestMapping(value = "cmtSave", method = RequestMethod.POST)
	public ModelAndView cmtSave(ModelMap model, @Valid @ModelAttribute("comment") CommentModel cmtModel, BindingResult result) {
		
		if(result.hasErrors()) {
			return new ModelAndView("forward:/posts/post", model);
			//return new ResponseEntity<>("Validation errors", HttpStatus.BAD_REQUEST);
		}
		Comments entity = new Comments();
		BeanUtils.copyProperties(cmtModel, entity);
		commentService.save(entity);
		if(cmtModel.getCmtid()!=null) {
			commentService.save(entity);
		} else {
			commentService.save(entity);
			return new ModelAndView("forward:/admin/alohcmute", model);
			//return new ResponseEntity<String>("Comment saved successfully",HttpStatus.OK);
		}
		//return new ResponseEntity<String>("Comment saved successfully",HttpStatus.OK);
		return new ModelAndView("forward:/admin/alohcmute", model);
	}
	
//	@GetMapping("editCmt/{cmtid}/{userid}")
//	public ResponseEntity<Void> editCmt(ModelMap model, @PathVariable("cmtid") Long cmtid,  @PathVariable("userid") int userid ) {
//		
//		
//		Optional<Comments> optCmt = commentService.findById(cmtid);
//		CommentModel commentModel = new CommentModel();
//		PostModel postModel = new PostModel();
//		
//		User user = userService.getCurrentUserById(userid);
//		if (optCmt.isPresent()) {
//			Comments entity = optCmt.get();
//			Post post = entity.getPostid();
//			BeanUtils.copyProperties(entity, commentModel);
//			BeanUtils.copyProperties(post, postModel);
//			commentModel.setIsEdit(true);
//			commentModel.setUserid(user);
//			model.addAttribute("cmtModel", commentModel);
//			model.addAttribute("user", user);
//			model.addAttribute("cmtid", cmtid);
//			model.addAttribute("post", postModel);
//			model.addAttribute("cmtDate", commentModel.getCmtDate());
//			//commentService.deleteById(cmtid);
//			return new ResponseEntity<>(HttpStatus.OK);
//		}
//		
//		model.addAttribute("message", "Comment is not existed.");
//		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	}
	
	@GetMapping("editCmt/{cmtid}/{userid}")
	public ModelAndView editCmt(ModelMap model, @PathVariable("cmtid") Long cmtid,  @PathVariable("userid") Long userid ) {
		
		Optional<Comments> optCmt = commentService.findById(cmtid);
		CommentModel commentModel = new CommentModel();
		PostModel postModel = new PostModel();
		
		User user = userService.getCurrentUserById(userid);
		if (optCmt.isPresent()) {
			Comments entity = optCmt.get();
			Post post = entity.getPostid();
			BeanUtils.copyProperties(entity, commentModel);
			BeanUtils.copyProperties(post, postModel);
			commentModel.setIsEdit(true);
			commentModel.setUserid(user);
			model.addAttribute("cmtModel", commentModel);
			model.addAttribute("user", user);
			//model.addAttribute("content", commentModel.getContent());
			model.addAttribute("post", postModel);
			//commentService.deleteById(cmtid);
			return new ModelAndView("admin/post/EditComment", model);
		}
		
		model.addAttribute("message", "Comment is not existed.");
		return new ModelAndView("forward:/posts/post", model);
	}
	
	@DeleteMapping("cmtdelete/{cmtid}/{userid}")
	public ResponseEntity<Void> deleteComment(@PathVariable("cmtid") Long cmtid, @PathVariable("userid") Long userid) {
		User user = userService.getCurrentUserById(userid);
		
	    Optional<Comments> optCmt = commentService.findById(cmtid);

	    if (optCmt.isPresent()) {
	        Comments cmt = optCmt.get();
	        if (cmt.getUserid() == user) {
	        	commentService.deleteById(cmtid);
	        	return new ResponseEntity<>(HttpStatus.OK);
	        }
	    }
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
	
	@GetMapping("edit/{postid}/{userid}")
	public ModelAndView edit(ModelMap model, @PathVariable("postid") Long postid,  @PathVariable("userid") Long userid ) {
		
		Optional<Post> optPost = postService.findById(postid);
		PostModel postModel = new PostModel();
		User user = userService.getCurrentUserById(userid);
		if (optPost.isPresent()) {
			Post entity = optPost.get();
			
			BeanUtils.copyProperties(entity, postModel);
			postModel.setIsEdit(true);
			postModel.setUserid(user);
			model.addAttribute("post", postModel);
			model.addAttribute("user", user);
			return new ModelAndView("admin/post/EditPost", model);
		}
		
		model.addAttribute("message", "Post is not existed.");
		return new ModelAndView("forward:/posts/post", model);
	}
	

	
	
	@GetMapping("delete/{postid}/{userid}")
	public ModelAndView delete(ModelMap model, @PathVariable("postid") Long postid, @PathVariable("userid") Long userid) {
	    User user = userService.getCurrentUserById(userid);
	    Optional<Post> optPost = postService.findById(postid);

	    if (optPost.isPresent()) {
	        Post post = optPost.get();
	        if (post.getUserid() == user) { // Kiểm tra xem người dùng có phải là chủ sở hữu của bài viết hay không
	            postService.deleteById(postid);
	            model.addAttribute("message", "Post is deleted.");
	        } else {
	            model.addAttribute("message", "You are not authorized to delete this post.");
	        }
	    } else {
	        model.addAttribute("message", "Post is not existed.");
	    }

	    return new ModelAndView("redirect:/admin/alohcmute");
	}
	
	
	
	
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("post") PostModel postModel, BindingResult result) {
		if(result.hasErrors()) {
			return new ModelAndView("admin/post/EditPost");
		}
		
		Post entity = new Post();
		BeanUtils.copyProperties(postModel, entity);	
		if(!postModel.getImageFile().isEmpty()) {
			UUID uuid = UUID.randomUUID();
			String uuString = uuid.toString();
			
			entity.setMedia(storageService.getStorageFilename(postModel.getImageFile(), uuString));
			storageService.store(postModel.getImageFile(), entity.getMedia());
		}
		
		User userId = postModel.getUserid();
		if(postModel.getPostid()!= null)
			postService.save(entity);
		else {
			postService.save(entity);
			return new ModelAndView("redirect:/admin/alohcmute");
	}
		String message = "";
		if(postModel.getIsEdit() == true) {
			message = "Post is Edit.";
		} else {
			message = "Post is Save.";
		}
		
		model.addAttribute("message", message);
		return new ModelAndView("redirect:/admin/alohcmute");
	}
	
	
	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serverFile(@PathVariable String filename){
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment;filename=\"" + file.getFilename() + "\"").body(file);
	}
}
