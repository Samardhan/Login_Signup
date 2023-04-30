package in.samar.binding;

import javax.persistence.Id;

import lombok.Data;

@Data
public class EnqForm {

	@Id
	private Integer enqId;
	private String studentName;
	private Long studentNumber;
	private String classMode;
	private String courseName;
	private String enqStatus;
	
}
