package hcmute.ALOHCMUTE.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hcmute.ALOHCMUTE.entity.Comments;
import hcmute.ALOHCMUTE.entity.Post;
import hcmute.ALOHCMUTE.repository.CommentRepository;

@Service
public class CommentServiceimpl implements ICommentService{
	@Autowired 
	CommentRepository commentRepository;

	public CommentServiceimpl(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}
	
	
	@Override
	public List<Post> topCmt() {
		return commentRepository.topCmt();
	}




	@Override
	public long count() {
		return commentRepository.count();
	}



	@Override
	public Optional<Comments> findById(int id) {
		return commentRepository.findById(id);
	}

	@Override
	public <S extends Comments> S save(S entity) {
//		if(entity.getCmtid()==null) {
//		return commentRepository.save(entity);
//		}
		return commentRepository.save(entity);	
	}

	@Override
	public int countCommentsByPostId(Post postid) {
		return commentRepository.countCommentsByPostId(postid);
	}

	
	
	
}
