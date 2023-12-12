package hcmute.model;
import java.time.LocalDateTime;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventModel {
	private Long eventId;

    private UserModel user;

    private String eventName;

    private String description;

    private String location;

    private LocalDateTime dateTime;

    private String imageUrl;

    private Boolean isEdit = false;
}
