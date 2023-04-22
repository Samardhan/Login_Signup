package in.samar.service;

import java.util.List;

import in.samar.binding.DashBoardResponse;
import in.samar.binding.EnqForm;
import in.samar.binding.EnqSearchCriteria;
import in.samar.entity.CourseEntity;
import in.samar.entity.EnqEntity;

public interface EnqService {

	public List<CourseEntity> course();
	
	public List<EnqEntity> enq();
	
	public DashBoardResponse dashboard(Integer userId);
	
	public String upsertEnq(EnqForm enq);
	
	public List<EnqForm> getEnq (Integer userId , EnqSearchCriteria enqSearch);
	
	public EnqForm editEnq (Integer enqId);
	
}
