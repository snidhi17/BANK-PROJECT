package bank.project.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private int customerid;
    private String password;
    private String customername;
    private String customeraddress;
    private String customerstatus;
    private String username;
    private long customercontact;
    private int failedattempts;

}
