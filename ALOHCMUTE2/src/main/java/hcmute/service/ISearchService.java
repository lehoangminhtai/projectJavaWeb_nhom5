package hcmute.service;

import java.util.List;
import java.util.Optional;

import hcmute.entity.User;

public interface  ISearchService {
	List<User> searchUsersByFullName(String fullName);
	List<User> findUsersByUserId(Long userId);
}
