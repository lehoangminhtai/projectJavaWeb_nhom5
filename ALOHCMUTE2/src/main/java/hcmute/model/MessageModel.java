package hcmute.model;

import java.time.LocalDateTime;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageModel {
	private Long messageId;

    private Long sender;

    private Long receiver;

    private String content;

    private LocalDateTime timestamp;

    private Boolean isEdit = false;
}
