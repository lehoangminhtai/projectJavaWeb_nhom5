package hcmute.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
