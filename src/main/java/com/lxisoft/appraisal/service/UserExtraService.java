package com.lxisoft.appraisal.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

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

@Service
@Transactional
public class UserExtraService {
	
	@Autowired 
	UserRepository userRepository;
	@Autowired
	 UserExtraRepository userExtraRepository;
	@Autowired
	LeaveService leaveService;
	

    private final Logger log = LoggerFactory.getLogger(UserExtraService.class);
    public void createUser(User user,UserExtra us)
    {
    	 userRepository.save(user);
         userExtraRepository.save(us);
    }
    
    public  Optional<UserExtra> findExtraByid(Long id)
	{
		 Optional<UserExtra> extra=userExtraRepository.findById(id);
		 return extra;
	}
    public List<User> getAllUsers()
    {
    	List<User> list=userRepository.findAll();
    	return list;
    }
    public List<UserExtra> getAllExtraUsers()
    {
    	List<UserExtra> list=userExtraRepository.findAll();
    	return list;
    }
	public Optional<User> getUserByusername(String login) {
		
		return userRepository.findOneByLogin(login);
	}
	public Optional<User> findByid(Long id) {
		
		return userRepository.findById(id);
	}
	
	public void deleteUser(Long id) 
	{
		userExtraRepository.deleteById(id);
		userRepository.deleteById(id);
		
	}
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

	
	public long getAttendance(long id)
	{
		LocalDate date = LocalDate.now();
		LocalDate endDate= date.minusDays(30); 
		String da=date.toString();
		String te=endDate.toString();
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
	public long getPunctuality(long id) {
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

	public long getTargets(long id) {
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

	public long getcompanyPolicy(long id) {
		Optional<UserExtra> userEx=findExtraByid(id);
		Optional<User> user=findByid(id);
		LocalDate first=userEx.get().getJoiningDate();
		 LocalDate second= LocalDate.now();
		long days= ChronoUnit.DAYS.between(first,second);
		 long total=(days*7);
		 int count=0;
		 

		System.out.println("leaves between:::::::::::::::::::"+leaveService.findLeavesOfUserBetween(userEx.get(),second,first));
		 Set<Leave> leaves= userEx.get().getLeaves();
		 for (Leave l:leaves)
		 {
			if (l.getType().contentEquals("NonAuthorized")) {
				count++;
				
			}
		 }
		 
		 Set<LateArrival> lates1= userEx.get().getLateArrivals();
		 for (LateArrival l:lates1)
		 {
			 if (l.getType().contentEquals("NonAuthorized"))
				 count++;
		 }
		 Set<ReportStatus> status=userEx.get().getReportStatuses();
		 for(ReportStatus s:status)
		 {
			 if(s.getType().contentEquals("NonAuthorized"))
				 count++;
		 }
		 
		 int policy=(int) (count*5/total);
		 System.out.println(count+" l "+policy);
		
		return 5-policy;
	}

	public long getCodeQuality(long id) {
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

	public ArrayList<User> getUsersFromUserExtra(List<UserExtra> userEx) 
	{
		ArrayList<User> users=new ArrayList<User>();
		for(UserExtra u:userEx)
		{
			users.add(findByid(u.getId()).get());
		}
		return users;
	}
}
