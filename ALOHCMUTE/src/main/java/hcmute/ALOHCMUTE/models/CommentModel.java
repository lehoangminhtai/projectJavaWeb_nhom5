package hcmute.ALOHCMUTE.models;



import java.util.Date;

import hcmute.ALOHCMUTE.entity.Post;
import hcmute.ALOHCMUTE.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
