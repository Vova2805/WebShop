package com.webshop.controller.ui;

import com.webshop.anotation.ActiveUser;
import com.webshop.controller.base.CommonController;
import com.webshop.model.classes.Advertisement;
import com.webshop.model.classes.Category;
import com.webshop.model.classes.Customer;
import com.webshop.model.classes.Product;
import com.webshop.service.interfaces.IAdvertisementService;
import com.webshop.service.interfaces.ICategoryService;
import com.webshop.service.interfaces.ICustomerService;
import com.webshop.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class HomeController extends CommonController {

    @Autowired
    private IAdvertisementService advertisementService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IProductService productService;

    @Autowired
    private ICustomerService customerService;

    @RequestMapping("*")
    public String fallbackMethod() {
        return "error";
    }

    @RequestMapping(value = "")
    public String toHomePage() {
        return "redirect:webshop";
    }

    @RequestMapping(value = "webshop", method = RequestMethod.GET)
    public String homePage(@ActiveUser User user, ModelMap modelMap, HttpServletRequest request, HttpSession session) {
        if (user != null) {
            Customer customer = customerService.getCustomer(user.getUsername()).get(0);
            updateSession(customer, session);
        }
        List<Advertisement> advertisements = advertisementService.getAll();

        List<Category> categoriesCuted = categoryService.getAll(5, 0);
        Map<Category, List<Product>> map = categoryService.getTopCategoryProductsMap(categoriesCuted, 20);
        Map<String, List<Product>> popularMap = productService.getPopularNewestMap(12);

        boolean showBottom = false;
        for (List<Product> goods : popularMap.values()) {
            if (goods.size() > 0) {
                showBottom = true;
                break;
            }
        }
        modelMap.addAttribute("advertisements", advertisements);
        modelMap.addAttribute("categories", categoryService.getAll());
        modelMap.addAttribute("categoriesCuted", categoriesCuted);
        modelMap.addAttribute("categoriesProductMap", map);
        modelMap.addAttribute("popularNewestProductMap", popularMap);
        modelMap.addAttribute("showBottom", showBottom);
        return "home";
    }

    @RequestMapping(value = "webshop/updateMenu", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView updateMenu(@ActiveUser User user, HttpSession session) {
        ModelAndView modelAndView = getModel("shop/layout/headerOption");
        if (user != null) {
            Customer customer = customerService.getCustomer(user.getUsername()).get(0);
            updateSession(customer, session);
        }
        return modelAndView;
    }
}
