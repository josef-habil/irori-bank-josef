package mapper;

import com.example.iroribankjosef.api.customer.dto.CustomerRequest;
import com.example.iroribankjosef.api.customer.dto.CustomerResponse;
import com.example.iroribankjosef.api.customer.dto.CustomerResponse;
import com.example.iroribankjosef.domain.customer.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

public class CustomerMapper {

    public static Customer toEntity(CustomerRequest customerRequest){
        if(customerRequest==null) return null;
        Customer customer = new Customer();
        customer.setName(customerRequest.getName());
        customer.setEmail(customerRequest.getEmail());
        return customer;
    }

    public static CustomerResponse toResponse(Customer customer){
        if(customer == null) return null;
        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getEmail()
        );
    }




}
