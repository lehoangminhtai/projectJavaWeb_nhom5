package hcmute.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
