package hcmute.model;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventParticipantModel {
	private Long participantId;

    private EventModel event;

    private UserModel user;

    private String status;

    private LocalDateTime timestamp;

    private Boolean isEdit = false;
}
