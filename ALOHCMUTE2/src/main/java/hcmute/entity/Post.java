package hcmute.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PostID")
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @Column(name = "Content", nullable = false, columnDefinition = "nvarchar(1000)")
    private String content;

    @Column(name = "ImageUrl", columnDefinition = "nvarchar(255)")
    private String imageUrl;

    @Column(name = "Timestamp", nullable = false)
    private LocalDateTime timestamp;
    
}
