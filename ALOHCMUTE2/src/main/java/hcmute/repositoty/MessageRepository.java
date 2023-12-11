package hcmute.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hcmute.entity.Message;
import java.util.List;


@Repository
public interface MessageRepository extends  JpaRepository<Message, Long>{
	List<Message> findBySenderAndReceiver(Long sender, Long receiver);
}
