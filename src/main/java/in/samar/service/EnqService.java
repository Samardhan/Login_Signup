package in.samar.service;

import java.util.List;

import in.samar.binding.DashBoardResponse;
import in.samar.binding.EnqForm;
import in.samar.binding.EnqSearchCriteria;
import in.samar.entity.StudentEnqEntity;

public interface EnqService {

	public List<String> course();
	
	public List<String> enq();
	
	public DashBoardResponse dashboard(Integer userId);
	
	public String upsertEnq(EnqForm enq);
	
	public List<StudentEnqEntity> getEnq (Integer uid,EnqSearchCriteria enqSearch);
	
	public EnqForm editEnq (Integer id);
	
	public List<StudentEnqEntity> getFilterData(EnqSearchCriteria criteria,Integer uid);
	
	
	
	
}
