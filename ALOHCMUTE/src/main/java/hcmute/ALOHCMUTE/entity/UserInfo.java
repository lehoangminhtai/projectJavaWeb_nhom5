package hcmute.ALOHCMUTE.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@OneToOne
	@MapsId
	@JsonIgnore
	@JoinColumn (name = "userid")
	private User user;
	
	@Column(name = "study_at", columnDefinition = "varchar(255)")
	private String study_at;
	
	@Column(name = "working_at", columnDefinition = "varchar(255)")
	private String working_at;
	
	@Column(name = "favorite", columnDefinition = "varchar(255)")
	private String favorite;
	
	@Temporal(TemporalType.DATE)
	private Date birthday;

	@Override
	public String toString() {
		return "UserInfo [user=" + user + ", study_at=" + study_at + ", working_at=" + working_at + ", favorite="
				+ favorite + ", birthday=" + birthday + "]";
	}
	
	
}
