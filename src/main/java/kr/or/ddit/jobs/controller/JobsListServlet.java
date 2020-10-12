package kr.or.ddit.jobs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.jobs.model.JobsVO;
import kr.or.ddit.jobs.service.JobsService;
import kr.or.ddit.jobs.service.JobsServiceI;

@WebServlet("/jobsList")
public class JobsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(JobsListServlet.class);
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		JobsServiceI jobsService = new JobsService();
		List<JobsVO> jobsList = jobsService.getAllJobs();
		/*sfor(int i = 0; i<jobsList.size(); i++) {
			String job_id = jobsList.get(i).getJob_id();
			String job_title = jobsList.get(i).getJob_title();
			logger.debug("jobId : {}, jobTitle : {} ", job_id, job_title);
		}*/
		
			logger.debug("jobs : {} ", jobsList);
			
		request.setAttribute("jobsList", jobsList);
		
		request.getRequestDispatcher("/jobsList.jsp").forward(request, response);
	}


}
