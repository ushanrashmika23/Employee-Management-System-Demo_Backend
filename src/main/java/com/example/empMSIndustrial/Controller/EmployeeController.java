package com.example.empMSIndustrial.Controller;

import com.example.empMSIndustrial.DTO.EmployeeDTO;
import com.example.empMSIndustrial.DTO.ResponseDTO;
import com.example.empMSIndustrial.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeDTO employeeDTO;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/empSave")
    public ResponseEntity saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        try {
            String res = employeeService.saveEmployee(employeeDTO);

            if (res.equals("00")) {
                responseDTO.setResponse("00", "Success", employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else if (res.equals("06")) {
                responseDTO.setResponse("06", "User Already Exist", employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            } else {
                responseDTO.setResponse("10", "Fail", employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_GATEWAY);
            }
        } catch (Exception e) {
            responseDTO.setResponse("10", "Fail", e);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/empUpdate")
    public ResponseEntity updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        try {
            String res = employeeService.updateEmployee(employeeDTO);
            if (res.equals("00")) {
                responseDTO.setResponse("00", "Success", employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else if (res.equals("01")) {
                responseDTO.setResponse("01", "User Not Found", employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            } else {
                responseDTO.setResponse("10", "Fail", employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_GATEWAY);
            }
        } catch (Exception e) {
            responseDTO.setResponse("10", "Fail", e);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/empGetAll")
    public ResponseEntity getAllEmployee() {
        try {
            responseDTO.setResponse("00", "All Employees", employeeService.getAllEmployees());
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            responseDTO.setResponse("10", "Fail", e);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/empSearch/{empID}")
    public ResponseEntity searchEmployee(@PathVariable int empID) {
        try {
            EmployeeDTO empData = employeeService.searchEmployee(empID);
            if (empData != null) {
                responseDTO.setResponse("00", "Success", empData);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setResponse("01", "Not Found @ " + empID, null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responseDTO.setResponse("10", "Fail", e);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/empDelete/{empID}")
    public ResponseEntity deleteEmployee(@PathVariable int empID) {
        try {
            EmployeeDTO empData = employeeService.searchEmployee(empID);
            String res = employeeService.deleteEmployee(empID);
            if (res.equals("00")) {
                responseDTO.setResponse("00", "Emp Deleted", empData);
                return new ResponseEntity(responseDTO, HttpStatus.OK);
            } else {
                responseDTO.setResponse("01", "Not Found @ " + empID, null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responseDTO.setResponse("10", "Fail", e);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
