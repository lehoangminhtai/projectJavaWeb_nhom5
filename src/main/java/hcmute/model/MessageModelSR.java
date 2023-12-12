package hcmute.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageModelSR {
	private Long messageId;

    private Long sender;

    private Long receiver;

    private String content;

    private LocalDateTime timestamp;

    private Boolean isEdit = false;
}
