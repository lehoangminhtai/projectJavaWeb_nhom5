package hcmute.ALOHCMUTE.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hcmute.ALOHCMUTE.entity.Comments;
import hcmute.ALOHCMUTE.repository.CommentRepository;
import io.micrometer.common.util.StringUtils;

@Service
public class CommentServiceimpl implements ICommentService{
	@Autowired 
	CommentRepository commentRepository;

	public CommentServiceimpl(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}
	
	@Override
	public Optional<Comments> findById(Long id) {
		return commentRepository.findById(id);
	}

	@Override
	public <S extends Comments> S save(S entity) {
		if(entity.getCmtid()==null) {
			return commentRepository.save(entity);
		} else {
			Optional<Comments> opt = findById(entity.getCmtid());
			if(opt.isPresent()) {
				if(StringUtils.isEmpty(entity.getCmtDate().toString())) {
					entity.setCmtDate(opt.get().getCmtDate());
				} else {
					entity.setCmtDate(opt.get().getCmtDate());
				}
			}
			return commentRepository.save(entity);
		}
		
	}

	@Override
	public void deleteById(Long id) {
		commentRepository.deleteById(id);
	}
	
}
