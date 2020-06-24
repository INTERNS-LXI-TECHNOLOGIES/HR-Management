package com.lxisoft.appraisal.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.lxisoft.appraisal.config.Constants;
import com.lxisoft.appraisal.domain.UserExtra;
import com.lxisoft.appraisal.repository.UserExtraRepository;
import com.lxisoft.appraisal.domain.Appraisal;
import com.lxisoft.appraisal.domain.Git;
import com.lxisoft.appraisal.domain.Hackathon;
import com.lxisoft.appraisal.domain.LateArrival;
import com.lxisoft.appraisal.domain.Leave;
import com.lxisoft.appraisal.domain.ReportStatus;
import com.lxisoft.appraisal.domain.User;
import com.lxisoft.appraisal.domain.UserDataBean;
import com.lxisoft.appraisal.service.AppraisalService;
import com.lxisoft.appraisal.service.GitService;
import com.lxisoft.appraisal.service.HackathonService;
import com.lxisoft.appraisal.service.JasperService;
import com.lxisoft.appraisal.service.LateArrivalService;
import com.lxisoft.appraisal.service.LeaveService;
import com.lxisoft.appraisal.service.ReportStatusService;
import com.lxisoft.appraisal.service.RestService;
import com.lxisoft.appraisal.service.UserDataBeanService;
import com.lxisoft.appraisal.service.UserExtraService;
import com.lxisoft.appraisal.service.UserService;
import com.lxisoft.appraisal.service.dto.UserDTO;
import com.lxisoft.appraisal.service.dto.UserExtraDTO;
import com.lxisoft.appraisal.service.dto.UserViewDTO;

import io.github.jhipster.web.util.ResponseUtil;
import net.sf.jasperreports.engine.JRException;

import java.util.stream.Collectors;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.io.IOException;
import javax.ws.rs.Consumes;

/**
 * AppraisalControllerResource controller
 */
@RestController
@RequestMapping("/api/appraisal-controller-resource")
@CrossOrigin("*")
public class AppraisalControllerResource {
    @Autowired
    UserResource userRes;
    @Autowired
    UserService userService;
    @Autowired
    UserExtraRepository userExtraRepository;
    @Autowired
    RestService restService;
    @Autowired
    UserExtraService userexService;
    @Autowired
    AppraisalService appraisalService;
    @Autowired
    JasperService jasperService;
    @Autowired
    UserDataBeanService userDataBeanService;

    public static List<UserDataBean> reportList = null;

    private final Logger log = LoggerFactory.getLogger(AppraisalControllerResource.class);

    /**
     * to get all user info.
     *
     * @return - list of user
     */
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUser() {
        Pageable pageable = null;
        return userRes.getAllUsers(pageable);
    }

    /**
     * for adding user
     *
     * @param userDTO
     * @return
     */
    @Consumes("multipart/form-data")
    @PostMapping("/addUser")
    public boolean addUser(@ModelAttribute UserViewDTO userDTO) {
        boolean isUsed = false;
        log.info("get value from server ----------:{}", userDTO.getEmail());
        log.info("get file from server ----------:{}", userDTO.getImage().getContentType());
        try {
            isUsed = restService.addUser(userDTO);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return isUsed;
    }

    /**
     * to get single user details
     *
     * @param id
     * @return
     */
    @GetMapping("/user-extras/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<UserExtraDTO> getUserExtra(@PathVariable Long id) {
        Optional<User> user = userexService.findByid(id);
        log.debug("REST request to get UserExtra : {}", id);
        Optional<UserExtra> userExtra = userExtraRepository.findById(id);
        log.debug("REST  get UserExtra : {}", userExtra);
        UserExtraDTO u = new UserExtraDTO(user.get(), userExtra.get());
        Optional<UserExtraDTO> dto = Optional.of(u);

        appraisalService.setAppraisal(id);
        return ResponseUtil.wrapOrNotFound(dto);
    }

    @GetMapping("/image/{id}")
    public String getUserImage(@PathVariable Long id) {
        Optional<UserExtra> userExtra = userExtraRepository.findById(id);
        log.debug("REST  get UserExtra : {}", userExtra);
        byte[] encoded = Base64.getEncoder().encode(userExtra.get().getImage());
        return (new String(encoded));
    }

    /**
     * for get all  Users
     *
     * @return
     */
    @GetMapping("/userName")
    public ArrayList<User> userNames()
    {

    	return userService.getAllUsers();
    }


    /**
     * to edit user
     *
     * @param userView
     * @return
     */
    @Consumes("multipart/form-data")
    @PostMapping("/editUser")
    public boolean editUser(@ModelAttribute UserViewDTO userView) {
        log.info("get model from server ----------:{}", userView);
        try {
            restService.editUser(userView);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }

    @GetMapping("/appraisal/{id}")
    public Appraisal getAppraisal(@PathVariable Long id) {
        log.info("get id from server ----------:{}", id);
        appraisalService.setAppraisal(id);
        Appraisal details = appraisalService.getOneAppraisal(id);
        return details;
    }

    @GetMapping("/sortAppraisal/{id}/{start}/{end}")
    public Appraisal sortAppraisal(@PathVariable("id") Long id, @PathVariable("start") String start,
            @PathVariable("end") String end) {
        log.info("get id from server ----------:{}", id);
        appraisalService.setAppraisalByDate(id, LocalDate.parse(start), LocalDate.parse(end));
        Appraisal details = appraisalService.getOneAppraisal(id);
        return details;
    }

    @GetMapping("/getPdf/{id}/{start}/{end}/{joinDate}/{unSort}")
    public ResponseEntity<byte[]> getPdf(@PathVariable("id") long id, @PathVariable("start") String start,
            @PathVariable("end") String end, @PathVariable("joinDate") String joinDate,
            @PathVariable("unSort") boolean sort)

    {
        if (sort)
            start = joinDate;
        String[] time = end.split("T", 2);
        end = time[0];
        Appraisal appraisal = null;
        try {
            appraisal = appraisalService.getOneAppraisal(id);
        } catch (Exception e) {

        }
        long attVal = appraisal.getAttendance();
        long punVal = appraisal.getPunctuality();
        long codeVal = appraisal.getCodeQuality();
        long policyVal = appraisal.getCompanyPolicy();
        long targetVal = appraisal.getMeetingTargets();
        String att = restService.getComment(attVal);
        String pun = restService.getComment(punVal);
        String code = restService.getComment(codeVal);
        String policy = restService.getComment(policyVal);
        String target = restService.getComment(targetVal);

        String month = "Random Days";
        byte[] pdfContents = null;
        try {
            pdfContents = jasperService.getReportAsPdfUsingDatabase(id, att, pun, code, policy, target, start, end,
                    month);
        } catch (JRException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String fileName = "Appraisal.pdf";
        headers.add("content dis-position", "attachment: filename=" + fileName);
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(pdfContents, headers, HttpStatus.OK);
        return response;
    }

    @GetMapping("/report/{sort}/{start}/{end}")
    public ResponseEntity<byte[]> report(@PathVariable("sort") boolean sort,
            @PathVariable(name = "start", required = false) String start,
            @PathVariable(name = "end", required = false) String end) {

        String month = null;
        if (sort) {
            month = "Till Date";
            reportList = userDataBeanService.getAllUserDataBeans();
        } else {
            month = "from:" + start + " to:" + end;
            // LocalDate first = LocalDate.parse(start);
            // LocalDate second = LocalDate.parse(end);
            // reportList = userDataBeanService.findAllUserDataBeanByDate(first, second);
        }
        byte[] pdfContents = null;
        try {
            pdfContents = jasperService.getReportAsPdfUsingJavaBeans(reportList, month);
        } catch (JRException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String fileName = "Appraisal.pdf";
        headers.add("content dis-position", "attachment: filename=" + fileName);
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(pdfContents, headers, HttpStatus.OK);
        return response;
    }

    @RequestMapping("/getReportBetweenTwoDate/{start}/{end}")
    public List<UserDataBean> reportByDate(@PathVariable("start") String start, @PathVariable("end") String end)
	{


		 LocalDate first=LocalDate.parse(start);
		 LocalDate second=LocalDate.parse(end);
		 return userDataBeanService.findAllUserDataBeanByDate(first,second);

    }
     /**
     * to get user full work status
     *
     * @param id
     * @return - full status of user
     */
    @GetMapping("/status/{id}")
    public List<Integer> getWorkStatus(@PathVariable Long id) {
        List<Integer> status = restService.getUserStatus(id);
        return status;
    }

    @GetMapping("/sortBydate/{id}/{start}/{end}")
    public List<Integer> sortBydate(@PathVariable Long id,@PathVariable("start") String start, @PathVariable("end") String end)
     {
        LocalDate first=LocalDate.parse(start);
        LocalDate second=LocalDate.parse(end);
        List<Integer> status = restService.getBydate(id,first,second);

        return status;
    }

}
