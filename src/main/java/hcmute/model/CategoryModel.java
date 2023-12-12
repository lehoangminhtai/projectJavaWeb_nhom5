package hcmute.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryModel {
	private Long categoryId;
	//validate
	@NotEmpty
	@Length(min=5)
	private String categoryName;
	private String icon;
	
	
	private MultipartFile imageFile;
	private Boolean isEdit = false; //true: update //false: insert
}
