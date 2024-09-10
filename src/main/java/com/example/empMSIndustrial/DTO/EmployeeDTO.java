package com.example.empMSIndustrial.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class EmployeeDTO {
    private int empID;
    private String empName;
    private String empAddr;
    private String empMNum;

    public int getEmpID() {
        return empID;
    }
}
