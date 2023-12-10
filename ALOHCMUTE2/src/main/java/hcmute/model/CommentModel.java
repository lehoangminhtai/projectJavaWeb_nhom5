package hcmute.model;

import java.time.LocalDateTime;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentModel {
	private Long commentId;

    private PostModel post;

    private UserModel user;

    private String content;

    private LocalDateTime timestamp;

    private Boolean isEdit = false;
}
