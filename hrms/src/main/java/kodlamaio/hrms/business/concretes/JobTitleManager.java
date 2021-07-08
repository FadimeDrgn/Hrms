package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobTitleService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobTitleDao;
import kodlamaio.hrms.entities.concretes.JobTitle;

@Service("JobTitleManager")
public class JobTitleManager implements JobTitleService {

	private JobTitleDao jobTitleDao;

	@Autowired
	public JobTitleManager(JobTitleDao jobTitleDao) {
		super();
		this.jobTitleDao = jobTitleDao;
	}

	@Override
	public DataResult<List<JobTitle>> getAll() {
		return new SuccessDataResult<List<JobTitle>>(this.jobTitleDao.findAll(), "Data listelendi");
	}

	@Override
	public Result add(JobTitle jobTitle) {
		if(!this.checkTitle(jobTitle.getTitle())) {
			return new ErrorResult("Job Title Error! Başka başlık giriniz, bu başlık mevcut");
		}
		
		this.jobTitleDao.save(jobTitle);
		return new SuccessResult("Başlık eklendi.");
	}

	private boolean checkTitle(String title) {
		if(this.jobTitleDao.findByTitle(title) == null) {
			return true;
		}
		return false;
	}

	
}
