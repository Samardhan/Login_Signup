package in.samar.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
//@Table(name="student_enq_entity")
public class StudentEnqEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer enqId;
	private String studentName;
	private Long studentNumber;
	private String classMode;
	private String courseName;
	private String enqStatus;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate createDate;

	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDate updateDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UsersDetailsEntity user;
}
