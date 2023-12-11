package hcmute.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_like") // Đổi tên bảng thành "user_like"
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Likes implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int likeid;
	
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "userid")
	private User userid;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "postid")
	private Post postid;

	@Override
	public String toString() {
		return "Likes [likeid=" + likeid + ", userid=" + userid + ", postid=" + postid + "]";
	}
}
