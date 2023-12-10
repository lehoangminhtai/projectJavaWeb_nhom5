package hcmute.ALOHCMUTE.services;

import java.util.List;

import hcmute.ALOHCMUTE.entity.Post;
import hcmute.ALOHCMUTE.entity.User;

public interface ILikeService {

	int countLikesByPostId(Post postid);

	int countLikesByUserId(User userid);

	List<Post> topLike();

}
