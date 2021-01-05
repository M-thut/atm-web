package th.ac.ku.atm.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import th.ac.ku.atm.model.Customer;
import th.ac.ku.atm.service.CustomerService;

@Controller
@RequestMapping("/customer") // -> อะไรที่เป็น customer มาเข้า class นี้หมดเลย
public class CustomerController {

    // Responsible for handle user request
    //      Step 1 : Update model for template
    //      Step 2 : Choose HTML template

    private CustomerService customerService;

    //...Dependency Injection
    //...You can don't use @Autowired because Spring Framework is auto generate

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    // Handle user request
    @GetMapping
    public String getCustomerPage(Model model) {

        // Step 1 : Update model for template
        model.addAttribute("allCustomers", customerService.getCustomers());

        // Step 2 : Choose HTML template
        return "customer";     //customer.html template
    }

    @PostMapping
    public String registerCustomer(@ModelAttribute Customer customer, Model model) {

        customerService.createCustomer(customer);

        model.addAttribute("allCustomers", customerService.getCustomers());
        return "redirect:customer";
    }
}