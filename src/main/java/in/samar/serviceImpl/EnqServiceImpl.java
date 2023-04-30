package in.samar.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.samar.binding.DashBoardResponse;
import in.samar.binding.EnqForm;
import in.samar.binding.EnqSearchCriteria;
import in.samar.entity.CourseEntity;
import in.samar.entity.EnqEntity;
import in.samar.entity.StudentEnqEntity;
import in.samar.entity.UsersDetailsEntity;
import in.samar.repo.CourseRepo;
import in.samar.repo.EnqStatusRepo;
import in.samar.repo.StudentEnqRepo;
import in.samar.repo.UserDetailsRepo;
import in.samar.service.EnqService;

@Service
public class EnqServiceImpl implements EnqService {

	@Autowired
	private StudentEnqRepo srepo;

	@Autowired
	private UserDetailsRepo urepo;

	@Autowired
	private CourseRepo crepo;

	@Autowired
	private EnqStatusRepo erepo;

	@Autowired
	private HttpSession session;

	@Override
	public List<String> course() {

		List<CourseEntity> all = crepo.findAll();

		List<String> cnames = new ArrayList<>();

		for (CourseEntity e : all) {

			cnames.add(e.getCourseName());
		}

		System.out.println(all);

		return cnames;
	}

	@Override
	public List<String> enq() {

		List<EnqEntity> all = erepo.findAll();

		List<String> snames = new ArrayList<>();

		for (EnqEntity s : all) {

			snames.add(s.getEnqStatus());
		}

		return snames;
	}

	@Override
	public DashBoardResponse dashboard(Integer userId) {

		DashBoardResponse resp = new DashBoardResponse();

		Optional<UsersDetailsEntity> id = urepo.findById(userId);

		if (id.isPresent()) {

			UsersDetailsEntity details = id.get();

			List<StudentEnqEntity> enq = details.getStuEnq();

			Integer all = enq.size();

			Integer enroll = enq.stream().filter(e -> e.getEnqStatus().equals("Enrolled")).collect(Collectors.toList())
					.size();

			Integer status = enq.stream().filter(e -> e.getEnqStatus().equals("Lost")).collect(Collectors.toList())
					.size();

			resp.setEnrolledCount(enroll);
			resp.setLostCount(status);
			resp.setTotalEnq(all);

		}

		return resp;
	}

	@Override
	public String upsertEnq(EnqForm enq) {

		String es = null;

		StudentEnqEntity see = new StudentEnqEntity();
		BeanUtils.copyProperties(enq, see);

		Integer userId = (Integer) session.getAttribute("userId");

		UsersDetailsEntity ude = urepo.findById(userId).get();
		see.setUser(ude);

		srepo.save(see);

		es = "saved";

		return es;
	}

	@Override
	public List<StudentEnqEntity> getEnq(Integer uid, EnqSearchCriteria enqSearch) {

		Optional<UsersDetailsEntity> user = urepo.findById(uid);

		if (user.isPresent()) {

			UsersDetailsEntity ude = user.get();

			List<StudentEnqEntity> stuEnq = ude.getStuEnq();

//			System.out.println(stuEnq.size());

			return stuEnq;

		}

		return null;
	}

	@Override
	public EnqForm editEnq(Integer id) {

		Optional<StudentEnqEntity> entity = srepo.findById(id);

		EnqForm form = new EnqForm();

		BeanUtils.copyProperties(entity.get(), form);

		return form;
	}

	@Override
	public List<StudentEnqEntity> getFilterData(EnqSearchCriteria criteria, Integer uid) {
		Optional<UsersDetailsEntity> user = urepo.findById(uid);

		if (user.isPresent()) {

			UsersDetailsEntity ude = user.get();

			List<StudentEnqEntity> stuEnq = ude.getStuEnq();

			if (criteria.getCourseNames() != null && !criteria.getCourseNames().equals("")) {

				stuEnq=stuEnq.stream().filter(e -> e.getCourseName().equals(criteria.getCourseNames()))
						.collect(Collectors.toList());
			}
			if (criteria.getStatusNames() != null && !criteria.getStatusNames().equals("")) {
				stuEnq= stuEnq.stream().filter(e -> e.getEnqStatus().equals(criteria.getStatusNames()))
						.collect(Collectors.toList());
			}
			if (criteria.getClassMode() != null && !criteria.getClassMode().equals("")) {
				stuEnq=stuEnq.stream().filter(e -> e.getClassMode().equals(criteria.getClassMode())).collect(Collectors.toList());
			}

			return stuEnq;

		}
		return null;
	}
}
