package com.example.empMSIndustrial.Services;

import com.example.empMSIndustrial.DTO.EmployeeDTO;
import com.example.empMSIndustrial.Entity.Employee;
import com.example.empMSIndustrial.Repo.EmployeeRepo;
import com.example.empMSIndustrial.util.VarList;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private EmployeeDTO employeeDTO;
    @Autowired
    private  ModelMapper modelMapper;

    public String saveEmployee(EmployeeDTO employeeDTO) {
        if (employeeRepo.existsById(employeeDTO.getEmpID())) {
            return VarList.RSP_DUPLICATE;
        } else {
            employeeRepo.save(modelMapper.map(employeeDTO, Employee.class));
            return VarList.RSP_SUCCESS;
        }
    }
    public String updateEmployee(EmployeeDTO employeeDTO) {
        if (employeeRepo.existsById(employeeDTO.getEmpID())) {
            employeeRepo.save(modelMapper.map(employeeDTO, Employee.class));
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
    public List<EmployeeDTO> getAllEmployees(){
        List<Employee> employees= employeeRepo.findAll();
        return modelMapper.map(employees, new TypeToken<ArrayList<EmployeeDTO>>(){}.getType());
    }
    public EmployeeDTO searchEmployee(int empID){
        if(employeeRepo.existsById(empID)){
            return modelMapper.map(employeeRepo.findById(empID),EmployeeDTO.class);
        }else{
            return null;
        }
    }
    public String deleteEmployee(int empID){
        if(employeeRepo.existsById(empID)){
            employeeRepo.deleteById(empID);
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}
