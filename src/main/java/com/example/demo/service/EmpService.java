package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.EmpData;
import com.example.demo.repo.EmpRepo;

@Service
public class EmpService {
	
	@Autowired
	private EmpRepo empRepo;
	
	public EmpData save(EmpData empData) {
		System.out.println(empData);
		return empRepo.save(empData);
	}
	
	 public EmpData getEmployeeById(Long id) {
	        return empRepo.findById(id).orElse(null);
	    }

	    public EmpData updateEmployee(EmpData employee) {
	        return empRepo.save(employee);
	    }
}
