package in.samar.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
public class StudentEnqEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer enqId;
	private String studentName;
	private Long studentPhone;
	private String classMode;
	private String courseName;

	@CreationTimestamp
	private LocalDate createDate;

	@UpdateTimestamp
	private LocalDate updateDate;

	
//	private UsersDetailsEntity uid;
}
