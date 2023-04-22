package in.samar.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import in.samar.binding.DashBoardResponse;
import in.samar.binding.EnqForm;
import in.samar.binding.EnqSearchCriteria;
import in.samar.entity.CourseEntity;
import in.samar.entity.EnqEntity;
import in.samar.service.EnqService;

@Service
public class EnqServiceImpl implements EnqService{

	@Override
	public List<CourseEntity> course() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EnqEntity> enq() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DashBoardResponse dashboard(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String upsertEnq(EnqForm enq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EnqForm> getEnq(Integer userId, EnqSearchCriteria enqSearch) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnqForm editEnq(Integer enqId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
