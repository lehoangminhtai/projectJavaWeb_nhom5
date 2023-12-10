package hcmute.model;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

	private MultipartFile avatarFile;

	private Boolean isEdit = false;
}
