package hcmute.ALOHCMUTE.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hcmute.ALOHCMUTE.entity.Comments;
import hcmute.ALOHCMUTE.repository.CommentRepository;

@Service
public class CommentServiceimpl implements ICommentService{
	@Autowired 
	CommentRepository commentRepository;

	public CommentServiceimpl(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}
	
	@Override
	public Optional<Comments> findById(int id) {
		return commentRepository.findById(id);
	}

	@Override
	public <S extends Comments> S save(S entity) {
		if(entity.getCmtid()==null) {
			return commentRepository.save(entity);
		}
		return commentRepository.save(entity);	
	}

	@Override
	public void deleteById(Integer id) {
		commentRepository.deleteById(id);
	}
	
}
