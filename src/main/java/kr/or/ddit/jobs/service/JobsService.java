package kr.or.ddit.jobs.service;

import java.util.List;

import kr.or.ddit.jobs.dao.JobsDao;
import kr.or.ddit.jobs.dao.JobsDaoI;
import kr.or.ddit.jobs.model.JobsVO;

public class JobsService implements JobsServiceI{

	@Override
	public List<JobsVO> getAllJobs() {
		JobsDaoI jobDao = new JobsDao();
		return jobDao.getAllJobs();
	}

}
