package hcmute.model;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupMessageModel {
	private Long messageId;
    private Long senderId;
    private Long groupId;
    private String content;
    private LocalDateTime timestamp;
}
