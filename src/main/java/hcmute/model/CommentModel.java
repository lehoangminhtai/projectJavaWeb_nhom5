package hcmute.model;

import java.util.Date;

import hcmute.entity.Post;
import hcmute.entity.User;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentModel {
	private Long cmtid;
	private User userid;
	private Post postid;
	private Date cmtDate;
	private String content;
	
	private Boolean isEdit= false;
}
