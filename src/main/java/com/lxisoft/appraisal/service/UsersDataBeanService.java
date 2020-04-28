package com.lxisoft.appraisal.service;

import com.lxisoft.appraisal.domain.Appraisal;
import com.lxisoft.appraisal.domain.User;
import com.lxisoft.appraisal.domain.UserExtra;
import com.lxisoft.appraisal.domain.UsersDataBean;
import com.lxisoft.appraisal.repository.UsersDataBeanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link UsersDataBean}.
 */
@Service
@Transactional
public class UsersDataBeanService {
	
	@Autowired
	UserExtraService userExService;
	@Autowired
	AppraisalService appraisalService;

    private final Logger log = LoggerFactory.getLogger(UsersDataBeanService.class);

    private final UsersDataBeanRepository usersDataBeanRepository;

    public UsersDataBeanService(UsersDataBeanRepository usersDataBeanRepository) {
        this.usersDataBeanRepository = usersDataBeanRepository;
    }

    /**
     * Save a usersDataBean.
     *
     * @param usersDataBean the entity to save.
     * @return the persisted entity.
     */
    public UsersDataBean save(UsersDataBean usersDataBean) {
        log.debug("Request to save UsersDataBean : {}", usersDataBean);
        return usersDataBeanRepository.save(usersDataBean);
    }

    /**
     * Get all the usersDataBeans.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<UsersDataBean> findAll() {
        log.debug("Request to get all UsersDataBeans");
        return usersDataBeanRepository.findAll();
    }

    /**
     * Get one usersDataBean by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<UsersDataBean> findOne(Long id) {
        log.debug("Request to get UsersDataBean : {}", id);
        return usersDataBeanRepository.findById(id);
    }

    /**
     * Delete the usersDataBean by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete UsersDataBean : {}", id);
        usersDataBeanRepository.deleteById(id);
    }
    /**
     * 
     * @param id
     * @param first
     * @param second
     * @return
     */
    public List<UsersDataBean> findOneUserDataBeanByDate(Long id, LocalDate first, LocalDate second)
	{
		List<UsersDataBean> list=new ArrayList<UsersDataBean>();
		User user=userExService.findByid(id).get();
		UserExtra userEx=userExService.findExtraByid(id).get();
		appraisalService.setAppraisalByDate(id,first,second);
		Appraisal appraisal=appraisalService.getOneAppraisal(id);
		UsersDataBean bean=new UsersDataBean(id,user.getFirstName(),user.getLastName(),userEx.getCompany(),
				userEx.getPosition(),user.getEmail(),appraisal.getAttendance(),appraisal.getPunctuality(),
				appraisal.getMeetingTargets(),appraisal.getCompanyPolicy(),appraisal.getCodeQuality(),userEx.getImage(),userEx.getImageContentType());
		list.add(bean);
		return list;
	}
}
