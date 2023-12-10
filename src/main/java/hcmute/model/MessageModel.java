package hcmute.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageModel {
	private Long messageId;

    private UserModel sender;

    private UserModel receiver;

    private String content;

    private LocalDateTime timestamp;

    private Boolean isEdit = false;
}
