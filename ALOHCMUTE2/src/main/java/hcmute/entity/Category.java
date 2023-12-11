package hcmute.entity;
import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data 
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryId;
	@Column(name = "categoryName", columnDefinition = "nvarchar(200)")
	private String categoryName;
	private String icon;
}
