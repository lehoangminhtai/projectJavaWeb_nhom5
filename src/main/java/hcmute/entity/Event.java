package hcmute.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
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
public class Event implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EventID")
    private Long eventId;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @Column(name = "EventName", nullable = false, columnDefinition = "nvarchar(255)")
    private String eventName;

    @Column(name = "Description", columnDefinition = "nvarchar(1000)")
    private String description;

    @Column(name = "Location", columnDefinition = "nvarchar(255)")
    private String location;

    @Column(name = "DateTime")
    private LocalDateTime dateTime;

    @Column(name = "ImageURL", columnDefinition = "varchar(255)")
    private String imageUrl;
}
