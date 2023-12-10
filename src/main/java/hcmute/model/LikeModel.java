package hcmute.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeModel {
	private Long likeId;

    private PostModel post;

    private UserModel user;

    private LocalDateTime timestamp;

    private Boolean isEdit = false;
}
