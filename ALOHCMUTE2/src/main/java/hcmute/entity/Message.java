package hcmute.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializable{

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
