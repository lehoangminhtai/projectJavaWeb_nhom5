package hcmute.ALOHCMUTE.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comments implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cmtid;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid")
	private User userid;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "postid")
	private Post postid;
	
	@Temporal(TemporalType.TIMESTAMP)
	@PrePersist
	private void prePersist() {
		cmtDate = new Date();
    }
	@DateTimeFormat(pattern = "YYYY-MM-DD hh:mi:ss")
	private Date cmtDate;
	
	@Column(name = "content", columnDefinition = "nvarchar(1000)")
	private String content;

	@Override
	public String toString() {
		return "Comments [cmtid=" + cmtid + ", userid=" + userid + ", postid=" + postid + ", cmtDate=" + cmtDate
				+ ", content=" + content + "]";
	}
	
	
}
