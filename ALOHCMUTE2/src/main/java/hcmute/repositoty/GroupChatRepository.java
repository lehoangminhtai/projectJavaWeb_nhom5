package hcmute.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hcmute.entity.GroupChat;

@Repository
public interface GroupChatRepository extends JpaRepository<GroupChat, Long>{

}
