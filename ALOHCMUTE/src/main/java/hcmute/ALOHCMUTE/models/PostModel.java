package hcmute.ALOHCMUTE.models;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import hcmute.ALOHCMUTE.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostModel {

	private Long postid;
	private User userid;
	private String content;
	private String media;
	private int access_modifier;
	private Date datePost;
	private Boolean isEdit = false;
	
	private Boolean isLike = false;
	private MultipartFile imageFile;
}
