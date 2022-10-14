package service;

import model.Customer;
import java.util.Collection;
import java.util.HashMap;

public class CustomerService {
    private final static CustomerService customerService= new CustomerService();
    private CustomerService(){}
    public static CustomerService getCustomerService(){
        return customerService;
    }
    HashMap<String,Customer> customerHashMap= new HashMap<>();
    public void addCustomer(String firstName,String lastName,String email){
        customerHashMap.put(email,new Customer(firstName,lastName,email));
    }
    public Customer getCustomer(String email){
       return customerHashMap.get(email);
    }
    public Collection<Customer> getCustomers(){
        return customerHashMap.values();
    }
}
