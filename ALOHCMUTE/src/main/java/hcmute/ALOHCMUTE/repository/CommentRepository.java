package hcmute.ALOHCMUTE.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hcmute.ALOHCMUTE.entity.Comments;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {

}
