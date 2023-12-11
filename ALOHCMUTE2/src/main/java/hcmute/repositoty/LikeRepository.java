package hcmute.repositoty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hcmute.entity.Likes;
import hcmute.entity.Post;
import hcmute.entity.User;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Integer>{
	@Query("SELECT COUNT(l) FROM Likes l WHERE l.postid = :postid")
	public int countLikesByPostId(@Param("postid") Post postid);
	
	@Query("SELECT COUNT(l) FROM Likes l WHERE l.userid = :userid")
	public int countLikesByUserId(@Param("userid") User userid);
	
	@Query("SELECT l.postid, COUNT(l) AS likeCount FROM Likes l GROUP BY l.postid ORDER BY likeCount DESC")
	public List<Post> topLike();

	@Query(value = "DELETE FROM Likes WHERE userid = :userid AND postid = :postid", nativeQuery = true)
    void delLike(@Param("userid") Long userid, @Param("postid") Long postid);
	
	@Query("SELECT CASE WHEN COUNT(l) > 0 THEN TRUE ELSE FALSE END FROM Likes l WHERE l.userid = :userid AND l.postid = :postid")
	boolean existsByUserIdAndPostId(@Param("userid") User userid, @Param("postid") Post postid);
}
