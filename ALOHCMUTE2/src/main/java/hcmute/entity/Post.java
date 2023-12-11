package hcmute.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postid;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "userid")
	private User userid;
	
	//content là nội dung văn bản 
	@Column(name = "content", columnDefinition = "nvarchar(3000)")
	private String content;
	
	@Column(name = "media", columnDefinition = "varchar(255)")
	private String media;
	
	//chế độ đăng 0:public; 1:friends; 2: private
	@Column(name = "access_modifier")
	private int access_modifier;
	
	@Temporal(TemporalType.TIMESTAMP)
	@PrePersist
	private void prePersist() {
        postDate = new Date();
    }
	@DateTimeFormat(pattern = "YYYY-MM-DD hh:mi:ss")
	private Date postDate;
	
	@JsonIgnore
	@OneToMany(mappedBy = "postid", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<Comments> comments;
	
	@JsonIgnore
	@OneToMany(mappedBy = "postid", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<Likes> likePost;
	
	@Override
	public String toString() {
		return "Post [postid=" + postid + ", userid=" + (userid != null ? userid.getUserId() : null) + ", content=" + content + ", media=" + media
				+ ", access_modifier=" + access_modifier + ", postDate=" + postDate + "]";
	}
    
}
