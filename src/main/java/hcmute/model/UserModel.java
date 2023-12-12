package hcmute.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import hcmute.entity.Likes;
import hcmute.entity.Post;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
	private Long userId;

	@NotEmpty
	@Length(min = 5)
	private String username;

	@NotEmpty
	@Length(min = 5)
	private String password;

	@Email
	@NotEmpty
	private String email;

	private String avatar;

	private String fullName;

	private LocalDate dateOfBirth;

	private String gender;

	private String bio;

	private String phoneNumber;

	private String address;

	private String relationshipStatus;

	private String job;

	private String education;
	
    private List<Post> posts;
    
    private List<Likes> userLikes;

	private MultipartFile avatarFile;

	private Boolean isEdit = false;
}
