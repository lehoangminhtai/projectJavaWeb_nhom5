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

import hcmute.ALOHCMUTE.entity.Post;
import hcmute.ALOHCMUTE.entity.User;
import hcmute.ALOHCMUTE.models.PostModel;
import hcmute.ALOHCMUTE.services.ILikesService;
import hcmute.ALOHCMUTE.services.IPostService;
import hcmute.ALOHCMUTE.services.IStorageService;
import hcmute.ALOHCMUTE.services.IUserService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("posts/post")
public class PostController {
	
	@Autowired
	IPostService postService;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IStorageService storageService;
	
	@Autowired
	ILikesService likeService;
	
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
		List<Post> posts = postService.findAll();

	    // Create a map to store user names by user ID
	    Map<Integer, String> userNameMap = new HashMap<>();
	    
	    Map<Long, Integer> likeCountMap = new HashMap<>();
	    for (Post post : posts) {
	        Long postId = post.getPostid();
	        int likeCount = likeService.countLikesByPostId(post);
	        likeCountMap.put(postId, likeCount);
	    }
	    
	    // Fetch usernames for each post's userid
	    for (Post post : posts) {
	        int userId = post.getUserid().getUserid();
	        User userName = userService.getCurrentUserById(userId);

	        // DOI FULL NAME O DAYYYYYYYY
	        String name = userName.getUserName();
	        userNameMap.put(userId, name);
	    }

	    // Add posts and username map to the model
	    model.addAttribute("post", posts);
	    model.addAttribute("user", userNameMap);
		
	    //===================================
		model.addAttribute("userid", userid);
		model.addAttribute("likeCount", likeCountMap);
		
		return "admin/post/list2";
	}
	
	@GetMapping("add")
	public String add(ModelMap model,@RequestParam("userid") String userid, Model model1) {
		PostModel postModel = new PostModel();
		postModel.setIsEdit(false);
		postModel.setIsLike(false);
		model.addAttribute("post", postModel);
		model1.addAttribute("userid", userid);
		return "admin/post/EditPost";
		
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
