package hcmute.ALOHCMUTE.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hcmute.ALOHCMUTE.entity.Likes;
import hcmute.ALOHCMUTE.entity.Post;
import hcmute.ALOHCMUTE.entity.User;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Integer> {

	@Query("SELECT COUNT(l) FROM Likes l WHERE l.postid = :postid")
	public int countLikesByPostId(@Param("postid") Post postid);
	
	@Query("SELECT l FROM Likes l WHERE l.userid = :userid AND l.postid = :postid")
    Likes findByUserIdAndPostId(@Param("userid") int userid, @Param("postid") Long postid);

//	@Query(value = "INSERT INTO Likes (userid, postid) VALUES (:userid, :postid)", nativeQuery = true)
//	void insertLike(@Param("userid") int userid, @Param("postid") Long postid);

//	@Query(value = "INSERT INTO Likes (userid, postid) VALUES (:userid, :postid)", nativeQuery = true)
//	void insertLike(@Param("userid") User userid, @Param("postid") Post postid);
	
//	@Query(value = "DELETE FROM Likes WHERE userid = :userid AND postid = :postid", nativeQuery = true)
//  void deleteLike(@Param("userid") int userid, @Param("postid") Long postid);
	
	@Query(value = "DELETE FROM Likes WHERE userid = :userid AND postid = :postid", nativeQuery = true)
    void delLike(@Param("userid") int userid, @Param("postid") Long postid);
	
	@Query("SELECT CASE WHEN COUNT(l) > 0 THEN TRUE ELSE FALSE END FROM Likes l WHERE l.userid = :userid AND l.postid = :postid")
	boolean existsByUserIdAndPostId(@Param("userid") User userid, @Param("postid") Post postid);
}
