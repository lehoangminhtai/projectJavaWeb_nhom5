package hcmute.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipModel {
	private Long friendshipId;

    private Long user1;

    private Long user2;

    private String status;

    private LocalDateTime timestamp;

    private Boolean isEdit = false;
}
