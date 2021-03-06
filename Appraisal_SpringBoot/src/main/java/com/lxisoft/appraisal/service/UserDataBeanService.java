package com.lxisoft.appraisal.service;

import com.lxisoft.appraisal.domain.Appraisal;
import com.lxisoft.appraisal.domain.User;
import com.lxisoft.appraisal.domain.UserDataBean;
import com.lxisoft.appraisal.domain.UserExtra;
import com.lxisoft.appraisal.repository.UserDataBeanRepository;
import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link UserDataBean}.
 */
@Service
@Transactional
public class UserDataBeanService {
	@Autowired
	UserExtraService userExService;
	@Autowired
	AppraisalService appraisalService;

    private final Logger log = LoggerFactory.getLogger(UserDataBeanService.class);

    private final UserDataBeanRepository userDataBeanRepository;

    public UserDataBeanService(UserDataBeanRepository userDataBeanRepository) {
        this.userDataBeanRepository = userDataBeanRepository;
    }

    /**
     * Save a userDataBean.
     *
     * @param userDataBean the entity to save.
     * @return the persisted entity.
     */
    public UserDataBean save(UserDataBean userDataBean) {
        log.debug("Request to save UserDataBean : {}", userDataBean);
        return userDataBeanRepository.save(userDataBean);
    }

    /**
     * Get all the userDataBeans.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<UserDataBean> findAll() {
        log.debug("Request to get all UserDataBeans");
        return userDataBeanRepository.findAll();
    }

    /**
     * Get one userDataBean by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<UserDataBean> findOne(Long id) {
        log.debug("Request to get UserDataBean : {}", id);
        return userDataBeanRepository.findById(id);
    }

    /**
     * Delete the userDataBean by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete UserDataBean : {}", id);
        userDataBeanRepository.deleteById(id);
    }
    public List<UserDataBean> getAllUserDataBeans()
	{
		List<UserExtra> users=userExService.getAllExtraUsers();
		List<UserDataBean> list=new ArrayList<UserDataBean>();
		for(UserExtra userEx:users)
		{
			User user=userExService.findByid(userEx.getId()).get();
			appraisalService.setAppraisal(userEx.getId());
			Appraisal appraisal=appraisalService.getOneAppraisal(userEx.getId());
			UserDataBean bean=new UserDataBean(user.getFirstName(),user.getLastName(),userEx.getCompany(),
					userEx.getPosition(),user.getEmail(),appraisal.getAttendance(),appraisal.getPunctuality(),
					appraisal.getMeetingTargets(),appraisal.getCompanyPolicy(),appraisal.getCodeQuality());
			list.add(bean);

		}
		return list;

	}
    /**
	 * get user data beans between two date
	 */
	public List<UserDataBean> findOneUserDataBeanByDate(Long id, LocalDate first, LocalDate second)
	{
		List<UserDataBean> list=new ArrayList<UserDataBean>();
		User user=userExService.findByid(id).get();
		UserExtra userEx=userExService.findExtraByid(id).get();
		appraisalService.setAppraisalByDate(id,first,second);
		Appraisal appraisal=appraisalService.getOneAppraisal(id);
		UserDataBean bean=new UserDataBean(user.getFirstName(),user.getLastName(),userEx.getCompany(),
				userEx.getPosition(),user.getEmail(),appraisal.getAttendance(),appraisal.getPunctuality(),
				appraisal.getMeetingTargets(),appraisal.getCompanyPolicy(),appraisal.getCodeQuality());
		list.add(bean);
		return list;
	}
	public List<UserDataBean> findAllUserDataBeanByDate(LocalDate first, LocalDate second)
	{
		List<UserExtra> users=userExService.getAllExtraUsers();
		List<UserDataBean> list=new ArrayList<UserDataBean>();
		for(UserExtra u:users)
		{

			User user=userExService.findByid(u.getId()).get();
			UserExtra userEx=userExService.findExtraByid(u.getId()).get();
			appraisalService.setAppraisalByDate(u.getId(),first,second);
			Appraisal appraisal=appraisalService.getOneAppraisal(u.getId());
			UserDataBean bean=new UserDataBean(user.getFirstName(),user.getLastName(),userEx.getCompany(),
					userEx.getPosition(),user.getEmail(),appraisal.getAttendance(),appraisal.getPunctuality(),
					appraisal.getMeetingTargets(),appraisal.getCompanyPolicy(),appraisal.getCodeQuality());
			list.add(bean);

		}
		return list;
	}

}
