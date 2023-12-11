package hcmute.repositoty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hcmute.entity.GroupMessage;

@Repository
public interface GroupMessageRepository extends JpaRepository<GroupMessage, Long>{
	List<GroupMessage> findByGroupChatGroupId(Long groupId);
}
