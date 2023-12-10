package hcmute.ALOHCMUTE.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;
	
	@Column(name = "username", columnDefinition = "varchar(255)")
	private String userName;
	
	@Column(name = "password", columnDefinition = "varchar(255)")
	private String password;
	
	@Column(name = "email", columnDefinition = "varchar(255)")
	private String email;
	
	@Column(name = "fullname", columnDefinition = "varchar(255)")
	private String fullname;
	
	@Column(name = "avatar", columnDefinition = "varchar(255)")
	private String avatar;

	@JsonIgnore
	@OneToOne(mappedBy = "user")
	@PrimaryKeyJoinColumn
	private UserInfo userinfo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "userid", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<UserRela> userRela;
	
	@JsonIgnore
	@OneToMany(mappedBy = "userid", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Post> postid;
	
	@JsonIgnore
	@OneToMany(mappedBy = "userid", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Likes> likes;

	@Override
	public String toString() {
		return "" + userid + "";
	}
}
