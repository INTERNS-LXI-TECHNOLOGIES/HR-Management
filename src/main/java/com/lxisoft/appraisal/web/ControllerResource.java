package com.lxisoft.appraisal.web;

import java.io.IOException;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com.lxisoft.appraisal.domain.Authority;
import com.lxisoft.appraisal.domain.Git;
import com.lxisoft.appraisal.domain.Hackathon;
import com.lxisoft.appraisal.domain.LateArrival;
import com.lxisoft.appraisal.domain.ReportStatus;
import com.lxisoft.appraisal.domain.User;
import com.lxisoft.appraisal.domain.UserExtra;
import com.lxisoft.appraisal.domain.Leave;
import com.lxisoft.appraisal.repository.AuthorityRepository;
import com.lxisoft.appraisal.service.AppraisalService;
import com.lxisoft.appraisal.service.GitService;
import com.lxisoft.appraisal.service.HackathonService;
import com.lxisoft.appraisal.service.JasperService;
import com.lxisoft.appraisal.service.LateArrivalService;
import com.lxisoft.appraisal.service.LeaveService;
import com.lxisoft.appraisal.service.ReportStatusService;
import com.lxisoft.appraisal.service.UserExtraService;
import com.lxisoft.appraisal.service.dto.UserExtraDTO;

import net.sf.jasperreports.engine.JRException;
/**
 * ControllerResource controller
 */

@Controller
public class ControllerResource {
	@Autowired
	UserExtraService userService;
	@Autowired
	LeaveService leaveSer;
	@Autowired
	LateArrivalService lateServ;
	@Autowired
	GitService gitServ;
	@Autowired
	HackathonService hackServ;
	@Autowired
	ReportStatusService reportServ;
	@Autowired
	AuthorityRepository authorityRepository;
	@Autowired
	JasperService jasperService;
	@Autowired
	AppraisalService appraisalService;
	

    private final Logger log = LoggerFactory.getLogger(ControllerResource.class);
    @RequestMapping(value="/")
	public ModelAndView index()
	{
    	ModelAndView mv=new ModelAndView(); 
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		boolean isAdmin=authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		boolean isUser=authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_USER"));
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = authentication.getName();
		System.out.println("usernaem:////////////////////"+username+ " is admin: "+ isAdmin+" user: "+isUser);
		if(isAdmin)
		{
			mv.addObject("username",username);
			mv.setViewName("redirect:/viewuser");
			return mv;
		}
		else if(isUser)
		{
			Optional<User> user=userService.getUserByusername(username);
			mv.addObject("id",user.get().getId());
			mv.setViewName("redirect:/userDetails");
			return mv;
		}
		else 
		{
			mv.setViewName("/login");
			return mv;
		}

	}

    @GetMapping(value= "/login")
    public String login() {
    	
        return "login";
    }
    @RequestMapping("/viewuser")
	public ModelAndView viewUsers(HttpServletRequest request, HttpServletResponse response)
	{
    	ModelAndView mv= new ModelAndView("viewAllUsers");
    	try {
			List<User> users = null;
			List<UserExtra> userEx = null;
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
			
			System.out.println("user:...."+username);
			Optional<User> user=userService.getUserByusername(username);
			
			Optional<UserExtra> u=userService.findExtraByid(user.get().getId());
			if(u.get().getCompany().equalsIgnoreCase("Lxisoft"))
			{
				users=(ArrayList<User>) userService.getAllUsers();
				userEx=userService.getAllExtraUsers();
			}
			else
			{
				userEx=userService.findByCompany(u.get().getCompany());
				users=(ArrayList<User>) userService.getUsersFromUserExtra(userEx);
			}
			
			for (UserExtra us:userEx) 
			{
				us.setImageContentType(Base64.getEncoder().encodeToString(us.getImage()));
			}
			List <UserExtraDTO> dto=getAllUser(users,userEx);
			mv.addObject("list",dto);
		}
		catch (Exception e)
		{
			mv.setViewName("redirect:/logout-success");
		}
		return mv;
	}
    @RequestMapping("/userDetails") 
	 public ModelAndView userDetail(@RequestParam Long id,ModelAndView model) 
	 {
		 ModelAndView mv= new ModelAndView("userDetail"); 
		 Optional <User> user = userService.findByid(id);
		 Optional <UserExtra> userEx = userService.findExtraByid(id);
		 List<Leave> leave = leaveSer.findLeave(id);
		 List<LateArrival> late =lateServ.findLate(id);
		 List<LocalDateTime> time=new ArrayList<LocalDateTime>();
		 for(int i=0;i<late.size();i++)
		 {
			 Instant in=late.get(i).getReachedTime();
			 LocalDateTime t= LocalDateTime.ofInstant(in,ZoneId.systemDefault());
			 time.add(t);
		 }
		 UserExtraDTO dto=getUser(user.get(),userEx.get());
		 mv.addObject("employee",dto);
		 LocalDate first=userEx.get().getJoiningDate();
		 LocalDate second= LocalDate.now();
		 long days= ChronoUnit.DAYS.between(first,second);
		 long total=(days*7);
		 List<Leave> auth=new ArrayList<Leave>();
		 List<Leave> unauth=new ArrayList<Leave>();
		 for(int i=0;i<leave.size();i++)
		 {
			 if(leave.get(i).getType().equals("Authorized"))
			 {
				 auth.add(leave.get(i));
			 }
			 if(leave.get(i).getType().equals("NonAuthorized"))
			 {
				 unauth.add(leave.get(i));
			 }
		 }
		 List<LateArrival> a=new ArrayList<LateArrival>();
		 List<LateArrival> un=new ArrayList<LateArrival>();
		 for(int i=0;i<late.size();i++)
		 {
			 if(late.get(i).getType().equals("Authorized"))
			 {
				 a.add(late.get(i));
			 }
			 if(late.get(i).getType().equals("NonAuthorized"))
			 {
				 un.add(late.get(i));
			 }
		 }
		 if(!userEx.get().getImageContentType().isEmpty())
		 {
			 String image=Base64.getEncoder().encodeToString(userEx.get().getImage());
			 mv.addObject("image",image);
		 }		
		 int l=((auth.size())+(unauth.size()));
		 long absence=l*7;
		 long workedHour=(total-absence);
		 
		 List<ReportStatus> status=reportServ.findReport(id);
		 List<ReportStatus> unreportdays=new ArrayList<ReportStatus>();
		 for(int i=0;i<status.size();i++)
		 {
			unreportdays.add(status.get(i));
		 }

		 List<Git> git=gitServ.findGit(userService.findExtraByid(id).get().getId());
		 List<Hackathon> hack=hackServ.findHack(userService.findExtraByid(id).get().getId());
		 if(git.size()!=0) 
		 {
			Iterator it=git.iterator();
			while (it.hasNext())
			{
				Git object = (Git)it.next();
				Long mar= object.getMark();
				 mv.addObject("git",mar);
			 }	
		}		
		if(hack.size()!=0)
		{
			Iterator i=hack.iterator();
			while (i.hasNext())
			{
				Hackathon object = (Hackathon)i.next();
				Long mark = object.getMark();
				mv.addObject("hack",mark);
			 }	
		}	
	 
		 mv.addObject("auth",auth);
		 mv.addObject("unauth",unauth);
		 mv.addObject("a",a);
		 mv.addObject("un",un);
		 mv.addObject("time",time);
		 mv.addObject("day",days);
		 mv.addObject("total",total);
		 mv.addObject("workedHour",workedHour);
		 mv.addObject("unreportdays",unreportdays);
		 return mv ;  
	 }
    public List<UserExtraDTO> getAllUser(List<User> users,List<UserExtra> userextra)
    {
    	List <UserExtraDTO> dto=new ArrayList<UserExtraDTO>();
		for(int i=0;i<users.size();i++)
		{
			for(int j=0;j<userextra.size();j++)
			{
				if(i==j)
				{
					UserExtraDTO u=new UserExtraDTO(users.get(i),userextra.get(j));
					dto.add(u);
				}
			}
		}
		return dto;
    }
    public UserExtraDTO getUser(User user,UserExtra ex)
    {
    	UserExtraDTO u=new UserExtraDTO(user,ex);
    	return u;
    }
   
    @GetMapping(value= "/add")
    public String add(Model model) {
    	
    	model.addAttribute("user",new User());
    
    	return "addUser";
    }
    @PostMapping("/addU")
	public ModelAndView addUser(@Valid @ModelAttribute  User user,BindingResult bindingResult, HttpServletRequest request,
			@RequestParam (name="picture") MultipartFile file, RedirectAttributes re)
	{
		ModelAndView mv;
		UserExtra us=new UserExtra();
		
		if (bindingResult.hasErrors()) 
		{
			mv=new ModelAndView("addUser");
			
			if(file.isEmpty())
			{
				re.addFlashAttribute("message","select a file to upload");
			}
		}	
		try
		{
			byte[] bytes=file.getBytes();
			us.setImage(bytes);
			us.setImageContentType(file.getContentType());
			
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}		
		Set<Authority> authorities = new HashSet<>();
		String auth=request.getParameter("authority");
		authorities.add(new Authority(auth));
				
		user.setAuthorities(authorities);
		BCryptPasswordEncoder encode=new BCryptPasswordEncoder(); 
		user.setPassword(encode.encode(user.getPassword()));
			
		us.setCompany(request.getParameter("company"));
		us.setPosition(request.getParameter("position"));
		us.setJoiningDate(LocalDate.parse(request.getParameter("joinDate")));
		us.setDob(LocalDate.parse(request.getParameter("dob")));
		us.setUser(user);
		
		mv=new ModelAndView("redirect:/");
		try{
			userService.createUser(user,us);
		}catch(Exception e)
		{
			mv=new ModelAndView("addUser");
		}
		return mv;
	}
    @RequestMapping("/logout-success")

	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {  
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
        if (auth != null){      
           new SecurityContextLogoutHandler().logout(request, response, auth);  
        }  
         return "redirect:/login";  
     } 
    @RequestMapping("/status")
	public String status()
	{
		
		return "status";
	}
	@RequestMapping(value = "/statusform",method = RequestMethod.GET)
	public @ResponseBody List<User> getName(@RequestParam("firstName") String firstName)
	{

		return simulateSearchResult(firstName);

	}

	private List<User> simulateSearchResult(String firstName)
	{

		ArrayList<User> user=(ArrayList<User>) userService.getAllUsers();
		ArrayList<User> result = new ArrayList<User>();
		for (User u : user) {
			if (u.getFirstName().contains(firstName)) {
				result.add(u);
			}
		}

		return result;
	}	
	public List<UserExtraDTO> getSpecificUser(Set<UserExtra> list)	
	{
		List<UserExtraDTO> users=new ArrayList<UserExtraDTO>();
		
		Iterator<UserExtra> it=list.iterator();
		while(it.hasNext())
		{
			UserExtra userEx=it.next();
			Optional<User> user=userService.findByid(userEx.getId());
			users.add(new UserExtraDTO(user.get(),userEx));
		}
		
		
		
		return users;
	}
	public LocalDate ToLocalDate(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	@RequestMapping("/leave")
	public ModelAndView Leave()
	{
		Set<UserExtra> list=new HashSet<UserExtra>();
		LocalDate localDate = LocalDate.now();
		List<Leave> l=leaveSer.findByDate(localDate);
		for(Leave u:l)
		{
				list.add(u.getUserExtra());
		}
		
		ModelAndView mv= new ModelAndView("Leave");
		List<UserExtraDTO> dto=getSpecificUser(list);
		mv.addObject("leavelist",dto);	
		return mv;
		
	}
	@RequestMapping("/setLeave")
	public ModelAndView setLeave(@RequestParam String name,@RequestParam (name="subject",required=false, defaultValue="NonAuthorized")String subject)
	{
		Long id=null;
		ArrayList<User> user=(ArrayList<User>) userService.getAllUsers();
		ArrayList<UserExtra> userextra=(ArrayList<UserExtra>) userService.getAllExtraUsers();
		Leave leave=new Leave();
//		Clock cl = Clock.systemUTC(); 
		 ZoneId zid = ZoneId.of("delhi"); 
		LocalDate localDate = LocalDate.now(zid);		
		ModelAndView mv= new ModelAndView("redirect:/leave");
		
		List<Leave> l=leaveSer.findByDate(localDate);
		
		for(int i=0;i<user.size();i++)
		{
			String m=user.get(i).getFirstName();
			if(name.contains(m))
			{
				id=user.get(i).getId();
			}
		}
		boolean isExist = false;
		for(Leave u:l)
		{
			
			if(id.equals(u.getUserExtra().getId()))
				isExist=true;
		}
		if(isExist)
		{
			
		}
		else
		{
			for(int j=0;j<userextra.size();j++)
			{

				if(id.equals(userextra.get(j).getId()))
				{
					UserExtra u=userextra.get(j);
					leave.setDate(localDate);
					leave.setUserExtra(u);
					leave.setType(subject);
					leaveSer.setLeave(leave);
	

				}

			}
		}
		return mv;
	}
	@RequestMapping("/evaluation")
	public String evaluation()
	{
		return "evaluation";
	}
	@RequestMapping("/setTest")
	public String setTest(@RequestParam String name,Long num,Long hack )
	{
		ArrayList<User> user=(ArrayList<User>) userService.getAllUsers();
		ArrayList<UserExtra> userextra=(ArrayList<UserExtra>) userService.getAllExtraUsers();
		for(int i=0;i<user.size();i++)
		{
			Git git=new Git();
			Hackathon hack1 = new Hackathon();
			String m=user.get(i).getFirstName();
			Long id=user.get(i).getId();
			LocalDate local=LocalDate.now();
			if(name.contains(m))
			{
				for(int j=0;j<userextra.size();j++)
				{
					if(id.equals(userextra.get(j).getId()))
					{
						git.setUserExtra(userextra.get(j));
						git.setDate(local);
						git.setMark(num);
						gitServ.setGit(git);
						hack1.setUserExtra(userextra.get(j));
						hack1.setDate(local);
						hack1.setMark(hack);
						hackServ.setHackathon(hack1);
					}
				}
			}
		}
		return "evaluation";
	}
	@RequestMapping("/reportStatus")
	public String statusPage(Model model) 
	{
		model.addAttribute("status",new ReportStatus());
		return "reportStatus";
	}

	@RequestMapping("/setReport")
	public String setReport(@RequestParam String name,String subject,String t)
	{
		ArrayList<User> user=(ArrayList<User>) userService.getAllUsers();
		ArrayList<UserExtra> userextra=(ArrayList<UserExtra>) userService.getAllExtraUsers();
		for(int i=0;i<user.size();i++)
		{
			ReportStatus status=new ReportStatus();
			String m=user.get(i).getFirstName();
			LocalDate localDate = LocalDate.now();
			LocalTime localtime = LocalTime.parse(t);
			
			Instant instant=LocalDateTime.of(localDate,localtime).atZone(ZoneId.systemDefault()).toInstant();
			if(name.contains(m))
			{
				Long id=user.get(i).getId();
				for(int j=0;j<userextra.size();j++)
				{
					if(id.equals(userextra.get(j).getId()))
					{
						status.setReportingTime(instant);
						status.setUserExtra(userextra.get(j));
						status.setType(subject);
						reportServ.setReport(status);
					}
				}
			}
		}
		return "reportStatus";
	}
	@RequestMapping("/lateArrival")
	public String LateArrival()
	{
		return "lateArrival";
	}
	@RequestMapping("/setLate")
	public String setLate(@RequestParam String name,String subject,String ltime)
	{
		ArrayList<User> user=(ArrayList<User>) userService.getAllUsers();
		ArrayList<UserExtra> userextra=(ArrayList<UserExtra>) userService.getAllExtraUsers();
		LateArrival late=new LateArrival();
		ModelAndView mv= new ModelAndView();
		for(int i=0;i<user.size();i++)
		{
			String m=user.get(i).getFirstName();
			User u=user.get(i);
			LocalDate localDate = LocalDate.now();
			LocalTime localtime = LocalTime.parse(ltime);
			Long id=user.get(i).getId();
			Instant instant=LocalDateTime.of(localDate,localtime).atZone(ZoneId.systemDefault()).toInstant();
			if(name.contains(m))
			{
				for(int j=0;j<userextra.size();j++)
				{
					if(id.equals(userextra.get(j).getId()))
					{
						late.setUserExtra(userextra.get(j));
						late.setType(subject);
						late.setReachedTime(instant);
						lateServ.setLate(late);
					}
				}
			}
		}
		return "lateArrival";
	}
	@RequestMapping("/deleteUser")
	public String deleteUser(@RequestParam (name="id") Long id)
	{
		userService.deleteUser(id);
		 return "redirect:/";  
	}
	@RequestMapping("/filter")
	public String filter(@RequestParam (name="company")String company,@RequestParam (name="position")String position, Model model)
	{
		ArrayList<UserExtra> users;
		if(!company.equalsIgnoreCase("--select--"))
			{
				users=userService.findByCompany(company);
			}
		else users=(ArrayList<UserExtra>) userService.getAllExtraUsers();
		if(!position.equalsIgnoreCase("--select--"))
		{
			users=userService.findByPosition(users,position);
		}
		ArrayList<User> user=(ArrayList<User>) userService.getUsersFromUserExtra(users);
		
		for(UserExtra userEx:users)
		{
			if(!userEx.getImageContentType().isEmpty())
			 {
				 String image=Base64.getEncoder().encodeToString(userEx.getImage());
				userEx.setImageContentType(image);
			 }	
		}
		List <UserExtraDTO> dto=getAllUser(user,users);
		model.addAttribute("list",dto);
		return "viewAllUsers";
	}
	@RequestMapping("/editUser")
	public ModelAndView editUser(@RequestParam (name="id") Long id)
	{
		ModelAndView mv=new ModelAndView("editUserPage");
		Optional<User> user=userService.findByid(id);
		Optional<UserExtra> userEx=userService.findExtraByid(id);
		mv.addObject("image",Base64.getEncoder().encodeToString(userEx.get().getImage()));
		mv.addObject("user",user.get());
//		mv.addObject("userex",userEx.get());
		String date=userEx.get().getDob().toString();
		String join=userEx.get().getJoiningDate().toString();
		mv.addObject("date",date);
		mv.addObject("join",join);
		
		
		return mv;
	}
	@PostMapping("/edit")
	public String edit(@ModelAttribute @Valid User formUser,BindingResult bindingResult,@RequestParam (name="name") String roleName,
			@RequestParam (name="date") String date , @RequestParam (name="join") String join, @RequestParam (name="company") String company,
			@RequestParam (name="image")MultipartFile file, @RequestParam (name="position") String position)
	{
		long id=formUser.getId();
		Optional<User> user=userService.findByid(id);
		Optional<UserExtra> userEx=userService.findExtraByid(id);
		
//		if (bindingResult.hasErrors()) {
//			return "editUserPage";
//			}
		if(!file.isEmpty()) {
			try {
				userEx.get().setImage(file.getBytes());
				userEx.get().setImageContentType(file.getContentType());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		user.get().setFirstName(formUser.getFirstName());
		user.get().setLastName(formUser.getLastName());
		user.get().setEmail(formUser.getEmail());
		userEx.get().setDob(LocalDate.parse(date));
		userEx.get().setJoiningDate(LocalDate.parse(join));
		userEx.get().setCompany(company);
		userEx.get().setPosition(position);
		
		
		userService.createUser(user.get(),userEx.get());
		
		return "redirect:/"; 
	}
	@RequestMapping("/appraisalResult")
	public String appraisalResult(@RequestParam long id, Model model)
	{
		return "Appraisal";
	}
	@RequestMapping("/getAppraisalResult")
	public String getAppraisalResult(@RequestParam long id, Model model)
	{
		long attendance=userService.getAttendance(id);
		
		long punctuality=userService.getPunctuality(id);
		
		long meetingTargets=userService.getTargets(id);
		
		long companyPolicy=userService.getcompanyPolicy(id);
		
		long codeQuality=userService.getCodeQuality(id);
		
		model.addAttribute("attendance",attendance);
		model.addAttribute("punctuality",punctuality);
		model.addAttribute("meetingTargets",meetingTargets);
		model.addAttribute("companyPolicy",companyPolicy);
		model.addAttribute("codeQuality",codeQuality);
		
//		System.out.println(attendance+"  "+punctuality+ " "+punctuality+"  "+companyPolicy+" "+codeQuality);
		return "AppraisalReport";
	}
	@GetMapping("/pdf")
	public ResponseEntity<byte[]>  getPdf()
	{
		byte[] pdfContents=null;
		try {
			pdfContents=jasperService.getReportAsPdfUsingDatabase();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String fileName="Appraisal.pdf";
		headers.add("content dis-position","attachment: filename="+fileName);
		ResponseEntity<byte[]> response=new ResponseEntity<byte[]>(pdfContents,headers,HttpStatus.OK);
		return response;
	}
	@GetMapping("/report")
	public ResponseEntity<byte[]> report()
	{
		appraisalService.setDetails();
		System.out.println("asdfghjkl,,,,,,,,,,,,,,,,,,,,"+appraisalService.getDetails());
		byte[] pdfContents=null;
		try {
			pdfContents=jasperService.getReportAsPdfUsingJavaBeans();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String fileName="Appraisal.pdf";
		headers.add("content dis-position","attachment: filename="+fileName);
		ResponseEntity<byte[]> response=new ResponseEntity<byte[]>(pdfContents,headers,HttpStatus.OK);
		return response;
	}
	
}
