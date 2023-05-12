package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.EmpData;
import com.example.demo.repo.EmpRepo;
import com.example.demo.service.EmpService;

@RestController
@RequestMapping("/emp")
@CrossOrigin()
public class EmpContoller {
	
	@Autowired
	EmpService empService;
	@Autowired
	EmpRepo empRepo;
	
	@PostMapping("/savedata")
	public EmpData SaveData(@RequestBody EmpData data) {
		
		return empService.save(data);
		
	}
	
	@GetMapping("/viewdata")
	 public List<EmpData> getAllEmployees() {
        return empRepo.findAll();
    }
	
	 @DeleteMapping("/delete/{id}")
	    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") long id) {
	        try {
	            empRepo.deleteById(id);
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	 
	 @PostMapping("/update/{id}")
	    public ResponseEntity<EmpData> updateEmployee(@PathVariable("id") Long id, @RequestBody EmpData employee) {
		 EmpData existingEmployee = empService.getEmployeeById(id);
	        if (existingEmployee != null) {
	            existingEmployee.setEmp_name(employee.getEmp_name());
	            existingEmployee.setEmp_age(employee.getEmp_age());
	            existingEmployee.setEmp_date(employee.getEmp_date());

	            EmpData updatedEmployee = empService.updateEmployee(existingEmployee);
	            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	
	
}
