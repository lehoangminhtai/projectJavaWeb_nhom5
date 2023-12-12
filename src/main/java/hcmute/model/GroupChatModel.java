package hcmute.model;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupChatModel {
	private Long groupId;
    private String groupName;
    private Set<Long> memberIds; // ID của các thành viên
}
