package hcmute.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hcmute.entity.User;
import hcmute.repositoty.SearchRepository;

@Service
public class SearchServiceImpl implements ISearchService{

	@Autowired
    SearchRepository searchRepository;
	
	public SearchServiceImpl(SearchRepository searchRepository) {
		this.searchRepository = searchRepository;
	}

	@Override
	public List<User> searchUsersByFullName(String fullName) {
		return searchRepository.findByFullNameContaining(fullName);
	}

	@Override
	public List<User> findUsersByUserId(Long userId) {
		return searchRepository.findByUserId(userId);
	}


	
	

}
