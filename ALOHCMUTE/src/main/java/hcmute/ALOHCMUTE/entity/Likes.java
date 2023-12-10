package hcmute.ALOHCMUTE.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Likes implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int likeid;
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "userid")
	private User userid;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "postid")
	private Post postid;

	@Override
	public String toString() {
		return "Likes [likeid=" + likeid + ", userid=" + userid + ", postid=" + postid + "]";
	}
	
}
