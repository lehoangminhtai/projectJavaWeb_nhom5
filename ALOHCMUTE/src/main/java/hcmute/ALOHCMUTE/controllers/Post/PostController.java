package hcmute.ALOHCMUTE.controllers.Post;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import hcmute.ALOHCMUTE.entity.Comments;
import hcmute.ALOHCMUTE.entity.Post;
import hcmute.ALOHCMUTE.entity.User;
import hcmute.ALOHCMUTE.models.CommentModel;
import hcmute.ALOHCMUTE.models.PostModel;
import hcmute.ALOHCMUTE.services.ICommentService;
import hcmute.ALOHCMUTE.services.IPostService;
import hcmute.ALOHCMUTE.services.IStorageService;
import hcmute.ALOHCMUTE.services.IUserService;
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
	
	@RequestMapping("")
	public String list(ModelMap model,@RequestParam("userid") int userid, Model model1) {
	//	List<Post> list = postService.findAll();
		
	//	User user = userService.getCurrentUserById(userid);

	//	List<User> userList = new ArrayList<>();
		
	//=============================	
		List<Post> posts = postService.findAllByOrderByPostDateDesc();

	    // Create a map to store user names by user ID
	    Map<Integer, String> userNameMap = new HashMap<>();

	    // Fetch usernames for each post's userid
	    for (Post post : posts) {
	        int userId = post.getUserid().getUserid();
	        User userName = userService.getCurrentUserById(userId);
	       // DOI FULL NAME Ơ DAYYYYYYYY
	        String name = userName.getUserName();
	        userNameMap.put(userId, name);
	    }

	    // Add posts and username map to the model
	    model.addAttribute("post", posts);
	    model.addAttribute("user", userNameMap);
		
		
		
	//===================================	
	//	model.addAttribute("post", list);
	//	model.addAttribute("user", userList);
		model.addAttribute("userid", userid);

		return "admin/post/list2";
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
			@PathVariable("userid") int userid,  @PathVariable("postid") Long postid,
			@PathVariable("currentUser") int currentUser) {
		Optional<Post> optPost = postService.findById(postid);
		
		PostModel postModel = new PostModel();
		
		User user = userService.getCurrentUserById(userid);
		User userOwnPost = userService.getCurrentUserById(currentUser);
		if (optPost.isPresent()) {
			Post entity = optPost.get();
			List<Comments> comments = postService.getCommentsByPostId(entity);
			
			Map<Integer, String> userNameMap = new HashMap<>();
			
			    // Fetch usernames for each post's userid
			    for (Comments comment : comments) {
			        int userId = comment.getUserid().getUserid();
			        User userName = userService.getCurrentUserById(userId);
			       // DOI FULL NAME Ơ DAYYYYYYYY
			        String name = userName.getUserName();
			        userNameMap.put(userId, name);
			    }

			    // Add posts and username map to the model
			    model.addAttribute("user", userNameMap);
			
			BeanUtils.copyProperties(entity, postModel);
			postModel.setUserid(user);
			model.addAttribute("post", postModel);
		//	model.addAttribute("username", user.getUserName());
			model.addAttribute("ownername", userOwnPost.getUserName());
			model.addAttribute("userid", userid);
			
			model.addAttribute("comment", comments);
			return new ModelAndView("admin/post/Comment", model);
		}
		
		model.addAttribute("message", "Post is not existed.");
		return new ModelAndView("forward:/posts/post", model);
	}
	
	@PostMapping("cmtSave")
	public ModelAndView cmtSave(ModelMap model, @Valid @ModelAttribute("cmtModel") CommentModel cmtModel, BindingResult result) {
		
		if(result.hasErrors()) {
			return new ModelAndView("admin/post/Comment");
		}
		Comments entity = new Comments();
		BeanUtils.copyProperties(cmtModel, entity);
		
//		if(cmtModel.getUserid()!=null) {
//			commentService.save(entity);
//			return new ModelAndView("forward:/posts/post", model);
//		}else {
//			commentService.save(entity);
//			return new ModelAndView("forward:/posts/post", model);
//		}
		
		commentService.save(entity);
		return new ModelAndView("forward:/posts/post", model);
		
	}
	
	@GetMapping("editCmt/{cmtid}/{userid}")
	public ModelAndView editCmt(ModelMap model, @PathVariable("cmtid") int cmtid,  @PathVariable("userid") int userid ) {
		
		
		Optional<Comments> optCmt = commentService.findById(cmtid);
		
		//Optional<Comments> optCmt = postService.getCommentsByCmtId(cmtid);
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
			model.addAttribute("cmtid", cmtid);
			model.addAttribute("post", postModel);
			model.addAttribute("cmtDate", commentModel.getCmtDate());
			commentService.deleteById(cmtid);
			return new ModelAndView("admin/post/EditComment", model);
		}
		
		model.addAttribute("message", "Comment is not existed.");
		return new ModelAndView("forward:/posts/post", model);
	}
	
	@DeleteMapping("cmtdelete/{cmtid}/{userid}")
	public ResponseEntity<Void> deleteComment(@PathVariable("cmtid") int cmtid, @PathVariable("userid") int userid) {
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
	public ModelAndView edit(ModelMap model, @PathVariable("postid") Long postid,  @PathVariable("userid") int userid ) {
		
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
	public ModelAndView delete(ModelMap model, @PathVariable("postid") Long postid, @PathVariable("userid") int userid) {
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

	    return new ModelAndView("redirect:/posts/post?userid=" + user.getUserid(), model);
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
			return new ModelAndView("redirect:/posts/post?userid=" + userId, model);
	}
		String message = "";
		if(postModel.getIsEdit() == true) {
			message = "Post is Edit.";
		} else {
			message = "Post is Save.";
		}
		
		model.addAttribute("message", message);
		return new ModelAndView("redirect:/posts/post?userid=" + userId, model);
	}
	
	
	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serverFile(@PathVariable String filename){
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment;filename=\"" + file.getFilename() + "\"").body(file);
	}
}
