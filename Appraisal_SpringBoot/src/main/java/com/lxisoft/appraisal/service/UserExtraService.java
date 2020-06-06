package com.lxisoft.appraisal.service;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.lxisoft.appraisal.domain.Appraisal;
import com.lxisoft.appraisal.domain.Authority;
import com.lxisoft.appraisal.domain.Git;
import com.lxisoft.appraisal.domain.Hackathon;
import com.lxisoft.appraisal.domain.LateArrival;
import com.lxisoft.appraisal.domain.Leave;
import com.lxisoft.appraisal.domain.ReportStatus;
import com.lxisoft.appraisal.domain.User;
import com.lxisoft.appraisal.domain.UserExtra;
import com.lxisoft.appraisal.repository.LeaveRepository;
import com.lxisoft.appraisal.repository.UserExtraRepository;
import com.lxisoft.appraisal.repository.UserRepository;
import com.lxisoft.appraisal.service.dto.UserExtraDTO;
/**
 * Service Implementation for managing {@link UserExtra,User}.
 */
@Service
@Transactional
public class UserExtraService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	UserExtraRepository userExtraRepository;
	@Autowired
	LeaveService leaveService;
	@Autowired
	LateArrivalService lateServ;
	@Autowired
	GitService gitServ;
	@Autowired
	HackathonService hackServ;
	@Autowired
	ReportStatusService statusServ;


    private final Logger log = LoggerFactory.getLogger(UserExtraService.class);
    /**
     * for creating user
     * @param user
     * @param us
     */
    public void createUser(User user,UserExtra us)
    {
        try {
    	 userRepository.save(user);
         userExtraRepository.save(us);
         userRepository.flush();
         userExtraRepository.flush();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * find userExtra by id
     * @param id
     * @return UserExtra
     */
    public  Optional<UserExtra> findExtraByid(Long id)
	{
		 Optional<UserExtra> extra=userExtraRepository.findById(id);
		 return extra;
	}
    /**
     * for getting all User
     * @return list of user
     */
    public List<User> getAllUsers()
    {
    	List<User> list=userRepository.findAll();
    	return list;
    }
    /**
     * for getting all UserExtra
     * @return list of UserExtra
     */
    public List<UserExtra> getAllExtraUsers()
    {
    	List<UserExtra> list=userExtraRepository.findAll();
    	return list;
    }
    /**
     * for getting user by username
     * @param login
     * @return User
     */
	public Optional<User> getUserByusername(String login) {

		return userRepository.findOneByLogin(login);
	}
	/**
	 * find user by id
	 * @param id
	 * @return User
	 */
	public Optional<User> findByid(Long id) {

		return userRepository.findById(id);
	}
	/**
	 * for deleting user
	 * @param id
	 */
	public void deleteUser(Long id)
	{
		userExtraRepository.deleteById(id);
		userRepository.deleteById(id);

	}
	/**
	 * find userExtra by company
	 * @param company
	 * @return List of UserExtra
	 */
	public ArrayList<UserExtra> findByCompany(String company)
	{
		ArrayList<UserExtra> users=(ArrayList<UserExtra>)getAllExtraUsers();
		for(Iterator<UserExtra> u = users.iterator(); u.hasNext();)
		{
			UserExtra use= u.next();
			if(!use.getCompany().equalsIgnoreCase(company))
				u.remove(	);
		}
		return users;
	}
	/**
	 * finding userExtra by company.
	 * @param users
	 * @param position
	 * @return List of UserExtra
	 */
	public ArrayList<UserExtra> findByPosition(ArrayList<UserExtra> users, String position)
	{
		for(Iterator<UserExtra> u = users.iterator(); u.hasNext();)
		{
			UserExtra use= u.next();
			if(!use.getPosition().equalsIgnoreCase(position))
				u.remove(	);
		}
		return users;
	}
	public List<LateArrival> getLateArrivalDetail(long id,LocalDate start,LocalDate end)
	{
		 List<LateArrival> lateAll=lateServ.findAllLate(id);
		 List<LateArrival> late = new ArrayList<LateArrival>();
		 for(int i=0;i<lateAll.size();i++)
		 {
			 Instant insta =lateAll.get(i).getReachedTime();
			 LocalDate localdate = insta.atZone(ZoneId.systemDefault()).toLocalDate();
			 boolean isTrue=localdate.isAfter(start) && localdate.isBefore(end);
			if(isTrue==true)
			{
				late.add(lateAll.get(i));
			}
		 }
		 return late;
	}
	public List<ReportStatus> getReportStatusDetail(long id,LocalDate start,LocalDate end)
	{
		 List<ReportStatus> status=statusServ.findAllReport(id);
		 List<ReportStatus> unreportdays=new ArrayList<ReportStatus>();
		 for(int i=0;i<status.size();i++)
		 {
			 Instant insta =status.get(i).getReportingTime();
			 LocalDate localdate = insta.atZone(ZoneId.systemDefault()).toLocalDate();
			 boolean isTrue=localdate.isAfter(start) && localdate.isBefore(end);
			if(isTrue==true)
			{
				unreportdays.add(status.get(i));
			}
		 }
		 return unreportdays;
	}
	/**
	 * get attendence between two date
	 * @param id
	 * @param one
	 * @param two
	 * @return
	 */
	public long getAttendanceByDate(long id,LocalDate one,LocalDate two)
	{
		Optional<User> u=findByid(id);
		Optional <UserExtra> ex =findExtraByid(id);
		UserExtraDTO user=new UserExtraDTO(u.get(),ex.get());
		long days= ChronoUnit.DAYS.between(one,two);
		long total=(days*7);
		long leaveCount=(ex.get().getLeaves()).size();
		long worked=total-(leaveCount*7);
		int attendance=((int)((worked*5/total)));

		return attendance;
	}
	/**
	 * get overall attendence out of 5
	 * @param id
	 * @return Attendence
	 */
	public long getAttendance(long id)
	{
		Optional<User> u=findByid(id);
		Optional <UserExtra> ex =findExtraByid(id);
		UserExtraDTO user=new UserExtraDTO(u.get(),ex.get());
		LocalDate first=user.getJoiningDate();
		LocalDate second= LocalDate.now();
		long days= ChronoUnit.DAYS.between(first,second);
		long total=(days*7);
		long leaveCount=(ex.get().getLeaves()).size();
		long worked=total-(leaveCount*7);
		int attendance=((int)((worked*5/total)));

		return attendance;
    }
	/**
	 * get punctuality between two date out of 5.
	 * @param id
	 * @param one
	 * @param two
	 * @return punctuality value in long
	 */
	public long getPunctualityByDate(long id,LocalDate one,LocalDate two)
	{
		Optional<UserExtra> userEx=findExtraByid(id);
		Optional<User> user=findByid(id);
		long days= ChronoUnit.DAYS.between(one,two);
		long total=(days*7);
		List<Leave> leave=leaveService.findLeavesOfUserBetween(userEx.get(),one,two);
		long leaveCount=leave.size();
		long worked=total-(leaveCount*7);
		List<LateArrival> late=getLateArrivalDetail(userEx.get().getId(),one,two);
		int lates=late.size();
		int punctuality=(int) ((worked-lates)*5 /total);
		return punctuality;
	}
	/**
	 * getting over all punctuality details out of 5.
	 * @param id
	 * @return punctuality value
	 */
	public long getPunctuality(long id)
	{
		Optional<UserExtra> userEx=findExtraByid(id);
		Optional<User> user=findByid(id);
		LocalDate first=userEx.get().getJoiningDate();
		LocalDate second= LocalDate.now();
		long days= ChronoUnit.DAYS.between(first,second);
		long total=(days*7);
		long leaveCount=(userEx.get().getLeaves()).size();
		long worked=total-(leaveCount*7);
		int lates=(userEx.get().getLateArrivals()).size();

		int punctuality=(int) ((worked-lates)*5 /total);
		return punctuality;
	}
	/**
	 * get meeting target details between two date out of 5.
	 * @param id
	 * @return target value
	 */
	public long getTargetsByDate(long id,LocalDate one,LocalDate two)
	{
		Optional<UserExtra> userEx=findExtraByid(id);
		Optional<User> user=findByid(id);
		long days= ChronoUnit.DAYS.between(one,two);
		long total=(days*7);
		List<Leave> leave=leaveService.findLeavesOfUserBetween(userEx.get(),one,two);
		long leaveCount=leave.size();
		long worked=total-(leaveCount*7);
		List<LateArrival> lateArrival=getLateArrivalDetail(userEx.get().getId(),one,two);
		int lates=lateArrival.size();
		long minutes= 0;
		LocalTime time=LocalTime.parse("06:30");
		for (LateArrival late:lateArrival)
		{
			 LocalTime local=LocalTime.from(late.getReachedTime().atZone(ZoneId.of("GMT+3")));
		 System.out.println(time+"  :  "+local+" : "+late.getReachedTime());
			minutes+=ChronoUnit.MINUTES.between(time,local);
		 }
		int hours=(int) (minutes/60);
		int target=(int) ((worked-hours)*5/total);
		System.out.println(target);
		return target;
	}
	/**
	 * get overall meeting target details out of 5.
	 * @param id
	 * @return target value
	 */
	public long getTargets(long id)
	{
		Optional<UserExtra> userEx=findExtraByid(id);
		Optional<User> user=findByid(id);
		LocalDate first=userEx.get().getJoiningDate();
		LocalDate second= LocalDate.now();
		long days= ChronoUnit.DAYS.between(first,second);
		long total=(days*7);
	    long leaveCount=(userEx.get().getLeaves()).size();
		long worked=total-(leaveCount*7);
		int lates=(userEx.get().getLateArrivals()).size();
		Set<LateArrival> lates1= userEx.get().getLateArrivals();
		long minutes= 0;
		LocalTime time=LocalTime.parse("06:30");
		for (LateArrival late:lates1)
		{
			 LocalTime local=LocalTime.from(late.getReachedTime().atZone(ZoneId.of("GMT+3")));
//			 System.out.println(time+"  :  "+local+" : "+late.getReachedTime());
			minutes+=ChronoUnit.MINUTES.between(time,local);
		 }


		int hours=(int) (minutes/60);
		int target=(int) ((worked-hours)*5/total);
//		System.out.println(target);
		return target;
	}
	/**
	 * get companyPolicy between two date out of 5
	 * @param id
	 * @param one
	 * @param two
	 * @return companyPolicy
	 */
	public long getcompanyPolicyByDate(long id,LocalDate one,LocalDate two)
	{
		Optional<UserExtra> userEx=findExtraByid(id);
		Optional<User> user=findByid(id);
		long days= ChronoUnit.DAYS.between(one,two);
		long total=(days*7);
		int count=0;


//		System.out.println("leaves between:::::::::::::::::::"+leaveService.findLeavesOfUserBetween(userEx.get(),second,first));
		 List<Leave> leaves=leaveService.findLeavesOfUserBetween(userEx.get(),one,two);
		 for (Leave l:leaves)
		 {
			if (l.getType().contentEquals("NonAuthorized")) {
				count++;

			}
		 }
		 List<LateArrival> lateArrival=getLateArrivalDetail(userEx.get().getId(),one,two);
		 for (LateArrival l:lateArrival)
		 {
			 if (l.getType().contentEquals("NonAuthorized"))
				 count++;
		 }
		 List<ReportStatus> status= getReportStatusDetail(userEx.get().getId(),one,two);
		 for(ReportStatus s:status)
		 {
			 if(s.getType().contentEquals("NonAuthorized"))
				 count++;
		 }

		 int policy=(int) (count*5/total);
		 System.out.println(count+" l "+policy);

		return 5-policy;
	}
	/**
	 * get company policy out of 5
	 * @param id
	 * @return companyPolicy
	 */
	public long getcompanyPolicy(long id)
	{
		Optional<UserExtra> userEx=findExtraByid(id);
		Optional<User> user=findByid(id);
		LocalDate first=userEx.get().getJoiningDate();
		LocalDate second= LocalDate.now();
		long days= ChronoUnit.DAYS.between(first,second);
		long total=(days*7);
		int count=0;


//		System.out.println("leaves between:::::::::::::::::::"+leaveService.findLeavesOfUserBetween(userEx.get(),second,first));
		 List<Leave> leaves= leaveService.findLeave(id);
		 if(!leaves.isEmpty()) {
		 for (Leave l:leaves)
		 {
			if (l.getType().contentEquals("NonAuthorized")) {
				count++;

			}
		 }
		 }
		 List<LateArrival> lates1= lateServ.findLate(id);
		 if(!lates1.isEmpty()) {
		 for (LateArrival l:lates1)
			 {
				 if (l.getType().contentEquals("NonAuthorized"))
					 count++;
			 }
		 }
		 Set<ReportStatus> status=userEx.get().getReportStatuses();
		if(!status.isEmpty()){
			for(ReportStatus s:status)
			 {
				 if(s.getType().contentEquals("NonAuthorized"))
					 count++;
			 }
		}
		 int policy=(int) (count*5/total);
		 System.out.println(count+" l "+policy);

		return 5-policy;
	}
	/**
	 * get codeQuality between two date out of 5
	 * @param id
	 * @param one
	 * @param two
	 * @return codeQuality
	 */
	public long getCodeQualityByDate(long id,LocalDate one,LocalDate two)
	{
		Optional<UserExtra> userEx=findExtraByid(id);
		Optional<User> user=findByid(id);
		Long mark = (long) 0;
		List<Git> gits=gitServ.findGitOfUserBetween(userEx.get(), one,two);
		if(!gits.isEmpty())
		{
			Iterator<Git> it=gits.iterator();
			while (it.hasNext())
			{
				Git object = (Git)it.next();
				mark=object.getMark();

			}
		}
		Long hackmark = (long) 0;
		List<Hackathon> hack=hackServ.findHackathonOfUserBetween(userEx.get(), one,two);
		if(!userEx.get().getHackathons().isEmpty())
		{
			Iterator<Hackathon> it=hack.iterator();
			while (it.hasNext())
			{
				Hackathon object = (Hackathon) it.next();
				hackmark=object.getMark();
			}
		}
		int quality=(int) (mark+hackmark*5/25)/2;
		return quality;
	}
	/**
	 * get codeQuality out of 5
	 * @param id
	 * @return
	 */
	public long getCodeQuality(long id)
	{
		Optional<UserExtra> userEx=findExtraByid(id);
		Optional<User> user=findByid(id);
		Long mark = (long) 0;
		if(!userEx.get().getGits().isEmpty())
		{
			Set<Git> git=(userEx.get().getGits());
			Iterator it=git.iterator();
			while (it.hasNext()) {
				Git object = (Git)it.next();
				mark=object.getMark();

			}
		}
		Long hackmark = (long) 0;
		if(!userEx.get().getHackathons().isEmpty())
		{
			Set<Hackathon> hack=(userEx.get().getHackathons());
			Iterator it=hack.iterator();
			while (it.hasNext()) {
				Hackathon object = (Hackathon) it.next();
				hackmark=object.getMark();
			}
		}
		int quality=(int) (mark+hackmark*5/25)/2;
		return quality;
	}
	/**
	 * for getting list of User from list of UserExtra
	 * @param userEx
	 * @return List: list of user
	 */
	public ArrayList<User> getUsersFromUserExtra(List<UserExtra> userEx)
	{
		ArrayList<User> users=new ArrayList<User>();
		for(UserExtra u:userEx)
		{
			users.add(findByid(u.getId()).get());
		}
		return users;
	}
	public void makeAsAdmin(Long id) {
		UserExtra userEx=findExtraByid(id).get();
		User user=findByid(id).get();
		Set<Authority> authorities = new HashSet<>();
		authorities.add(new Authority("ROLE_ADMIN"));
		user.clearAuthority();
		user.setAuthorities(authorities);
		try {
			createUser(user,userEx);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void makeAsUser(Long id) {
		UserExtra userEx=findExtraByid(id).get();
		User user=findByid(id).get();
		Set<Authority> authorities = new HashSet<>();
		authorities.add(new Authority("ROLE_USER"));
		user.clearAuthority();
		user.setAuthorities(authorities);
		try {
			createUser(user,userEx);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


}
