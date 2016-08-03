package com.webshop.controller.ui;

import com.webshop.controller.base.CommonController;
import com.webshop.exeption.UserAlreadyExistsException;
import com.webshop.model.classes.Customer;
import com.webshop.service.interfaces.ICategoryService;
import com.webshop.service.interfaces.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LoginController extends CommonController {

    @Autowired
    private ICustomerService ICustomerService;
    @Autowired
    private ICategoryService categoryService;

    /**
     * Init modelAndView
     *
     * @param error
     * @param logout
     * @return
     */
    private ModelAndView loginInit(String error, String logout) {
        ModelAndView model = getModel("login");
        if (error != null) {
            model.addObject("error", "Invalid username or password!");
        }

        if (logout != null) {
            model.addObject("logout", "Successful logout!");
        }
        model.addObject("categories", categoryService.getAll());
        return model;
    }

    /**
     * Get login block
     *
     * @param error
     * @param logout
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = loginInit(error, logout);
        model.addObject("block", "login");
        return model;
    }

    /**
     * Get signup block
     *
     * @param error
     * @param logout
     * @return
     */
    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public ModelAndView register(@RequestParam(value = "error", required = false) String error,
                                 @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = loginInit(error, logout);
        model.addObject("block", "sign up");
        return model;
    }

    /**
     * Change blocks
     *
     * @param block
     * @param model
     * @return
     */
    @RequestMapping(value = "/login/changeBlock", params = {"block"}, method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView changeBlock(@RequestParam(value = "block") String block, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        if (block.equals("login"))
            modelAndView.setViewName("shop/partial/loginPartial");
        else
            modelAndView.setViewName("shop/partial/registerPartial");
        model.addAttribute("categories", categoryService.getAll());
        return modelAndView;
    }

    /**
     * Register new user
     *
     * @return
     */
    @RequestMapping(value = "registration", method = RequestMethod.GET)
    public Customer customer() {
        return new Customer();
    }

    /**
     * Adding new customer - role USER
     *
     * @param customer
     * @param result
     * @return
     */
    @RequestMapping(value = "registerCustomer", method = RequestMethod.POST)
    public ModelAndView createUser(@ModelAttribute("customer") @Valid Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("registration", "error", "Some errors in your fields");
        }
        try {
            customer.setEnabled(true);
            ICustomerService.registerNewCustomer(customer);
            return new ModelAndView("login", "success", "Successfully registered as " + '"' + customer.getCustomerName() + '"');
        } catch (UserAlreadyExistsException e) {
            return new ModelAndView("registration", "error", "User with the same name already exists");
        }
    }
}
