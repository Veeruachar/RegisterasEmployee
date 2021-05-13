package com.Asos.Demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {
	@Autowired
	EmployeeRepo repo;
	@RequestMapping("/")
	
	public ModelAndView home() {
		ModelAndView mv=new ModelAndView("index");
		return mv;
	}
	
	@PostMapping("/reg")
	@ResponseBody
	public ModelAndView addemp(Employee emp) {
		ModelAndView mv=new ModelAndView("update");
		if(emp.getPass().equals(emp.getCpass())) {
			repo.save(emp);
			mv.addObject("empob", emp);
		}
		else {
			mv.addObject("empob", "Failed to save details because password and confirm password are not same");
		}
		
		return mv;
	}
	
	@GetMapping("/getemp")
	@ResponseBody
	public Employee findByName(@RequestParam int id) {
		Employee emp1=repo.findById(id).orElse(null);
		return emp1;
	}
	
	@GetMapping("/getallemp")
	public List<Employee> findall(){
		List<Employee> l=new ArrayList<>();
		l=repo.findAll();
		return l;
	}
	
	@RequestMapping("/updateemppage")
	public ModelAndView updateview() {
		ModelAndView mv=new ModelAndView("update");
		return mv;
	}
	@PutMapping("/updateempdata")
	public Employee updateemp(Employee emp) {
		repo.save(emp);
		return emp;
	}
	@DeleteMapping("/delemp")
	public String deleteByName(@RequestParam int id) {
		Employee emp1=repo.getOne(id);
		repo.delete(emp1);
		return "deleted";
	}
	
}
