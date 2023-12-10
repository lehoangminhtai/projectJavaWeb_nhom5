package hcmute.model;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostModel {
	private Long postId;

    private UserModel user;

    private String content;

    private String imageUrl;

    private LocalDateTime timestamp;

    private MultipartFile imageFile;

    private Boolean isEdit = false;
}
