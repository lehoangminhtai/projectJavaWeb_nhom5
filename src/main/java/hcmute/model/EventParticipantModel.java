package hcmute.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
