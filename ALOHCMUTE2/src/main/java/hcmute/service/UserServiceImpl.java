package hcmute.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import hcmute.repositoty.UserRepository;
import jakarta.transaction.Transactional;
import hcmute.entity.User;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
    public boolean authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @SuppressWarnings("deprecation")
	@Transactional
    @Override
    public <S extends User> S save(S entity) {
        if (entity.getUserId() == null) {
            return userRepository.save(entity);
        } else {
            Optional<User> opt = findById(entity.getUserId());
            if (opt.isPresent()) {
                User existingUser = opt.get();
                if (StringUtils.isEmpty(entity.getAvatar())) {
                    entity.setAvatar(existingUser.getAvatar());
                }
                // Các trường khác không cần thiết kiểm tra vì BeanUtils.copyProperties đã làm điều đó
            }
            return userRepository.save(entity);
        }
    }

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public List<User> findAll(Sort sort) {
		return userRepository.findAll(sort);
	}

	@Override
	public List<User> findAllById(Iterable<Long> ids) {
		return userRepository.findAllById(ids);
	}

	@Override
	public <S extends User> Optional<S> findOne(Example<S> example) {
		return userRepository.findOne(example);
	}

	@Override
	public long count() {
		return userRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public void delete(User entity) {
		userRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		userRepository.deleteAll();
	}

	@Override
	public User getCurrentUser(String username) {
		 return userRepository.getUserByUsername(username);
	 }
	
	@Override
	public User getCurrentUserById(Long userid) {
		 return userRepository.getUserByUserID(userid);	 
	 }

	@Override
	public User registerNewUser(User user) {
		return userRepository.save(user);
	}
    
    

}
