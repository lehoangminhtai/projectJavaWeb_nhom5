package hcmute.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hcmute.entity.Comments;
import hcmute.entity.Post;
import hcmute.entity.User;
import hcmute.repositoty.CommentRepository;
import hcmute.repositoty.PostRepository;
import io.micrometer.common.util.StringUtils;


@Service
public class PostServiceImpl implements IPostService{
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	CommentRepository commentRepository;

	public PostServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {
		this.postRepository = postRepository;
		this.commentRepository = commentRepository;
	}

	
	@Override
	public void deletePost(Long postid) {
		postRepository.deletePost(postid);
	}


	@Override
	public List<Post> findAll() {
		return postRepository.findAll();
	}

	@Override
	public <S extends Post> S save(S entity) {
		if (entity.getPostid() == null) {
			return postRepository.save(entity);
		}
		else {
			Optional<Post> opt = findById(entity.getPostid());
			if (opt.isPresent()) {
				if (StringUtils.isEmpty(entity.getMedia())) {
					entity.setMedia(opt.get().getMedia());
					entity.setPostDate(opt.get().getPostDate());
				} else {
					// lấy lại images cũ
					entity.setMedia(entity.getMedia());
					entity.setPostDate(opt.get().getPostDate());
					//entity.setPostDate(entity.getPostDate());
				}
			}
			return postRepository.save(entity);
		}
	}

	@Override
	public Optional<Post> findById(Long id) {
		return postRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		postRepository.deleteById(id);
		
	}

	@Override
	public List<Comments> getCommentsByPostId(Post postid) {
		return postRepository.getCommentsByPostId(postid);
	}

	@Override
	public Optional<Comments> getCommentsByCmtId(Long cmtid) {
		return postRepository.getCommentsByCmtId(cmtid);
	}

	@Override
	public List<Post> findAllByOrderByPostDateDesc() {
		return postRepository.findAllByOrderByPostDateDesc();
	}

	@Override
	public long countPost() {
		return postRepository.count();
	}

	@Override
	public int countPostByUserId(User userid) {
		return postRepository.countPostByUserId(userid);
	}

	@Override
	public long countCmt() {
		return commentRepository.count();
	}

	@Override
	public List<Post> findAllPostByUserId(User userid) {
		return postRepository.findAllPostByUserId(userid);
	}
	
	
}
