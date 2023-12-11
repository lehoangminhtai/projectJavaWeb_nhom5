package hcmute.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeModel {
	private int likeid;
    private UserModel user;
    private PostModel post;

    @Override
    public String toString() {
        return "LikeModel [likeid=" + likeid + ", user=" + user + ", post=" + post + "]";
    }
}
