package hcmute.model;


import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import hcmute.entity.User;
import lombok.*;

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
	private Boolean isLiked = false;
	private MultipartFile imageFile;	
}
