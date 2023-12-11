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
public class MessageSR implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MessageID")
    private Long messageId;

	@Column(name = "SenderID", nullable = false)
	private Long sender;
	
	@Column(name = "ReceiverID", nullable = false)
	private Long receiver;

    @Column(name = "Content", nullable = false, columnDefinition = "nvarchar(1000)")
    private String content;

    @Column(name = "Timestamp", nullable = false)
    private LocalDateTime timestamp;
}
