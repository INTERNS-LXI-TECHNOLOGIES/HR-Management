package com.lxisoft.Appraisal.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.lxisoft.Appraisal.repository.UserRepository;
import com.lxisoft.Appraisal.repository.LateRepository;
import com.lxisoft.Appraisal.repository.LeaveRepository;
import com.lxisoft.Appraisal.repository.reportRepository;
import com.lxisoft.Appraisal.repository.Gitrepository;
import com.lxisoft.Appraisal.repository.Hackathonrepository;
import com.lxisoft.Appraisal.config.UserPrincipal;


import com.lxisoft.Appraisal.model.User;
import com.lxisoft.Appraisal.model.Role;
import com.lxisoft.Appraisal.model.LateArrival;
import com.lxisoft.Appraisal.model.Leave;
import com.lxisoft.Appraisal.model.reportStatus;
import com.lxisoft.Appraisal.model.Gitmark;
import com.lxisoft.Appraisal.model.Hackathon;
@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository repo;
	@Autowired
	private LeaveRepository repos;
	@Autowired
	private LateRepository repol;
	@Autowired
	private reportRepository rep;
	@Autowired
	private Gitrepository repot;
	@Autowired
	private Hackathonrepository reph;
	@Override
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		
		User user=repo.findByUsername(username);
		if(user==null)
			throw new UsernameNotFoundException("user not found");
		
		return new UserPrincipal(user);
	}
	
	private Collection < ? extends GrantedAuthority > mapRolesToAuthorities(Collection < Role > roles) 
	{
        return roles.stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toList());
    }
	

	public void addUser(User user) 
	{
		repo.save(user);
	}

	public List<User> getAllUsers() {
		 List<User> list=repo.findAll();

//		 Date ldate = null;
//		try {
//			ldate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-12");
//		} catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}  
//
//		 Date fdate = null;
//		try {
//			fdate = new SimpleDateFormat("yyyy-MM-dd").parse("2015-02-25");
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
//		List<User> listss=repo.getLeavesFromUserBetween(fdate,ldate);
//		 System.out.println(listss);
		return list;
	}
	public List<Leave> getAllLeave() {
		 List<Leave> list=repos.findAll();
		return list;
	}
	public  Optional <User> findByid(Long id)
	{
		 Optional <User> em=repo.findById(id);
		 return em;
	}	
	public void setLeave(Leave leave)
	{
		repos.save(leave);
	}
	public void setLate(LateArrival late)
	{

		repol.save(late);
	}
	public void setReport(reportStatus status)
	{
		rep.save(status);
	}
	public void setGit(Gitmark git)
	{
		repot.save(git);
	}
	public  List<Leave> findLeave(Long id)
	{
		List<Leave> em=repos.findByUserId(id);

		 return em;
	}	
	public  List<LateArrival> findLate(Long id)
	{

		List<LateArrival> em=repol.findByUserId(id);
		 
		 return em;
	}
	public  List<Gitmark> findGit(Long id)
	{

		List<Gitmark> git =repot.findByUserId(id);
		 
		 return git;
	}
	public  List<Hackathon> findHack(Long id)
	{

		List<Hackathon> hack =reph.findByUserId(id);		 
		 return hack;
	}
	public void setHackathon(Hackathon hack1)
	{
		reph.save(hack1);
	}
	public long getGitmark(long id)
	{
		long gitmark=6;
		return gitmark;
	}
	public List<reportStatus> findReport(Long id)
	{

		List<reportStatus> status=rep.findByUserId(id);
		 
		 return status;
	}
	public User getUserByusername(String username) {
		
		return repo.findByUsername(username);
	}

	public ArrayList<User> findByCompany(String company) {
		ArrayList<User> users=(ArrayList<User>) getAllUsers();
		for(Iterator<User> u = users.iterator(); u.hasNext();)
		{
			User use= u.next();
			if(!use.getCompany().equalsIgnoreCase(company))
				u.remove(	);
		}
		return users;
	}

	public void deleteUser(Long id) {
		repo.deleteById(id);
		
	}

	public void updateUser(@Valid User user) {
	long id=user.getId();
	
	repo.save(user);
	
	}

	public ArrayList<User> findByPosition(ArrayList<User> users, String position) {
		for(Iterator<User> u = users.iterator(); u.hasNext();)
		{
			User use= u.next();
			if(!use.getPosition().equalsIgnoreCase(position))
				u.remove(	);
		}
		return users;
	}

	public long getAttendance(long id) {
//		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		LocalDate date = LocalDate.now();
		LocalDate endDate= date.minusDays(30); 
		String da=date.toString();
		String te=endDate.toString();
//		System.out.println(da+"  .."+te + " // "+repo.getLeavesFromUserBetween("2015-09-12","2021-09-13"));
		
		Optional<User> user=findByid(id);
		LocalDate first=user.get().getJoiningDate();
		 LocalDate second= LocalDate.now();
		 long days= ChronoUnit.DAYS.between(first,second);
		 long total=(days*7);
		 long leaveCount=(user.get().getLeave()).size();
		 long worked=total-(leaveCount*7);
		 int attendance=((int)((worked*5/total)));
		
		return attendance;
		
	}

	public long getPunctuality(long id) {
		Optional<User> user=findByid(id);
		LocalDate first=user.get().getJoiningDate();
		 LocalDate second= LocalDate.now();
		long days= ChronoUnit.DAYS.between(first,second);
		 long total=(days*7);
		 long leaveCount=(user.get().getLeave()).size();
		 long worked=total-(leaveCount*7);
		 int lates=(user.get().getLateArrival()).size();
		 
		int punctuality=(int) ((worked-lates)*5 /total);
		return punctuality;
	}

	public long getTargets(long id) {
		Optional<User> user=findByid(id);
		LocalDate first=user.get().getJoiningDate();
		 LocalDate second= LocalDate.now();
		long days= ChronoUnit.DAYS.between(first,second);
		 long total=(days*7);
		 long leaveCount=(user.get().getLeave()).size();
		 long worked=total-(leaveCount*7);
		 int lates=(user.get().getLateArrival()).size();
		 List<LateArrival> lates1=user.get().getLateArrival();
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
		Optional<User> user=findByid(id);
		LocalDate first=user.get().getJoiningDate();
		 LocalDate second= LocalDate.now();
		long days= ChronoUnit.DAYS.between(first,second);
		 long total=(days*7);
		 int count=0;
		 
		 Set<Leave> leaves= user.get().getLeave();
		 for (Leave l:leaves)
		 {
			if (l.getType().contentEquals("NonAuthorized")) {
				count++;
				
			}
		 }
		 
		 List<LateArrival> lates1=user.get().getLateArrival();
		 for (LateArrival l:lates1)
		 {
			 if (l.getType().contentEquals("NonAuthorized"))
				 count++;
		 }
		 List<reportStatus> status=user.get().getReportStatus();
		 for(reportStatus s:status)
		 {
			 if(s.getType().contentEquals("NonAuthorized"))
				 count++;
		 }
		 
		 int policy=(int) (count*5/total);
		 System.out.println(count+" l "+policy);
		
		return 5-policy;
	}

	public long getCodeQuality(long id) {
		Optional<User> user=findByid(id);
		Long mark = (long) 0;
		if(!user.get().getGitMark().isEmpty()) mark=(user.get().getGitMark().get(((user.get().getGitMark()).size()-1)).getGitMark());
		Long hackmark = (long) 0;
		if(!user.get().getHackathon().isEmpty()) hackmark= (user.get().getHackathon().get(((user.get().getHackathon()).size()-1)).getHackathon());
		int quality=(int) (mark+hackmark*5/25)/2;
		return quality;
	}

}
