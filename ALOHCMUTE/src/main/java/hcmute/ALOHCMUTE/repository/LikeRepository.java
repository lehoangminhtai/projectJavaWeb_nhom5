package hcmute.ALOHCMUTE.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hcmute.ALOHCMUTE.entity.Likes;
import hcmute.ALOHCMUTE.entity.Post;
import hcmute.ALOHCMUTE.entity.User;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Integer>{
	@Query("SELECT COUNT(l) FROM Likes l WHERE l.postid = :postid")
	public int countLikesByPostId(@Param("postid") Post postid);
	
	@Query("SELECT COUNT(l) FROM Likes l WHERE l.userid = :userid")
	public int countLikesByUserId(@Param("userid") User userid);
	
	@Query("SELECT l.postid, COUNT(l) AS likeCount FROM Likes l GROUP BY l.postid ORDER BY likeCount DESC")
	public List<Post> topLike();
}
