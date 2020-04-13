package com.lxisoft.appraisal.service;

import com.lxisoft.appraisal.domain.UserDataBean;
import com.lxisoft.appraisal.repository.UserDataBeanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link UserDataBean}.
 */
@Service
@Transactional
public class UserDataBeanService {

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
}