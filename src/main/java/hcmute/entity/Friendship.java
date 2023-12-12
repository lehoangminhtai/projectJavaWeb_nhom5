package hcmute.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Friendship implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FriendshipID")
    private Long friendshipId;
	
	@Column(name = "UserID1", nullable = false)
    private Long user1;
	
	@Column(name = "UserID2", nullable = false)
    private Long user2;

    @Column(name = "Status", columnDefinition = "nvarchar(255)")
    private String status;

    @Column(name = "Timestamp", nullable = false)
    private LocalDateTime timestamp;
}
