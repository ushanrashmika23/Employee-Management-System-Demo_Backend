package com.example.empMSIndustrial.DTO;

import com.example.empMSIndustrial.util.VarList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDTO {
    private String code;
    private String status;
    private String message;
    private Object content;
    public void setResponse(String code,String message,Object content){
        this.code=code;
        this.message=message;
        this.content=content;
        switch(this.code){
            case "00":
                this.status= "RSP_SUCCESS";
                break;
            case "01":
                this.status= "RSP_NO_DATA_FOUND";
                break;

            case "05":
                this.status= "RSP_ERROR";
                break;
            case "06":
                this.status= "RSP_DUPLICATE";
                break;
            case "10":
                this.status= "RSP_FAIL";
                break;
        }
    }
}
