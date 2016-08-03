package com.webshop.controller.ui;

import com.webshop.anotation.ActiveUser;
import com.webshop.controller.base.CommonController;
import com.webshop.model.classes.*;
import com.webshop.service.interfaces.ICustomerService;
import com.webshop.service.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class CustomerController extends CommonController {

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IOrderService orderService;

    /**
     * Initialize base viewModel
     *
     * @return
     */
    private ModelAndView initModelAndView() {
        ModelAndView modelAndView = getModel("customer/profile");
        return modelAndView;
    }

    /**
     * Main gate to get customer profile. Changing visual content according to parameters
     *
     * @param user
     * @param session
     * @param block
     * @param request
     * @return
     */
    @Secured(value = "IS_AUTHENTICATED_FULLY")
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile(@ActiveUser User user, HttpSession session, @RequestParam(value = "block", required = false) String block, HttpServletRequest request) {

        if (customerService.getCustomer(user.getUsername()).size() == 1) {
            if (user != null) {
                Customer customer = customerService.getCustomer(user.getUsername()).get(0);
                updateSession(customer, session);
                if (customer != null) {

                    if (block != null) {
                        switch (block) {
                            case "wishes":
                                return accountWishlist(customer, request);
                            case "cart":
                                return accountCart(customer, request);
                            case "orders":
                                return accountOrders(customer, request);
                            case "comparisons":
                                return accountComparisons(customer, request);
                            case "comments":
                                return accountComments(customer, request);
                            case "reviews":
                                return accountReviews(customer, request);
                        }
                    }
                    return accountPersonalData(customer, request);
                }
            }
        }
        return new ModelAndView("login");
    }

    //region Partial Views

    /**
     * Each function is matched to side menu option
     *
     * @param customer
     * @param request
     * @return
     */
    private ModelAndView accountPersonalData(Customer customer, HttpServletRequest request) {
        ModelAndView modelAndView = initModelAndView();
        Address address = customer.getAddressByAddressId();
        if (address == null) {
            address = new Address();
        }
        modelAndView.addObject("address", address);
        modelAndView.addObject("page", "personalDataPartial.jsp");
        return modelAndView;
    }

    private ModelAndView accountWishlist(Customer customer, HttpServletRequest request) {
        ModelAndView modelAndView = initModelAndView();
        List<Wish> wishList = customer.getWishesByCustomerId();
        modelAndView.addObject("wishes", wishList);
        modelAndView.addObject("page", "wishlistPartial.jsp");
        return modelAndView;
    }

    private ModelAndView accountCart(Customer customer, HttpServletRequest request) {
        ModelAndView modelAndView = initModelAndView();
        List<Cart> carts = customer.getCartsByCustomerId();
        carts.forEach(cart -> {
            cart.getProductByProductId().initializeImages();
        });
        Double total = 0.0;
        for (Cart cart : carts) {
            total += cart.getProductAmount() * cart.getProductByProductId().getPrice();
        }
        modelAndView.addObject("carts", carts);
        modelAndView.addObject("total", total);
        modelAndView.addObject("page", "cartPartial.jsp");
        return modelAndView;
    }

    private ModelAndView accountOrders(Customer customer, HttpServletRequest request) {
        ModelAndView modelAndView = initModelAndView();
        List<ProductOrder> orders = customer.getProductOrdersByCustomerId();
        modelAndView.addObject("activeUser", customer);
        modelAndView.addObject("orders", orders);
        modelAndView.addObject("page", "ordersPartial.jsp");

        return modelAndView;
    }

    private ModelAndView accountComments(Customer customer, HttpServletRequest request) {
        ModelAndView modelAndView = initModelAndView();
        List<Comment> comments = customer.getCommentsByCustomerId();
        modelAndView.addObject("comments", comments);
        modelAndView.addObject("page", "commentsPartial.jsp");
        return modelAndView;
    }

    private ModelAndView accountReviews(Customer customer, HttpServletRequest request) {
        ModelAndView modelAndView = initModelAndView();
        List<ViewedProduct> viewedProducts = customer.getViewedProductsByCustomerId();
        modelAndView.addObject("viewedProducts", viewedProducts);
        modelAndView.addObject("page", "reviewsPartial.jsp");
        return modelAndView;
    }

    private ModelAndView accountComparisons(Customer customer, HttpServletRequest request) {
        ModelAndView modelAndView = initModelAndView();
        List<Comparison> comparisons = customer.getComparisonsByCustomerId();
        modelAndView.addObject("comparisons", comparisons);
        modelAndView.addObject("page", "comparisonsPartial.jsp");

        return modelAndView;
    }

    //endregion

    /**
     * Checkout order based on existing cart elements
     *
     * @param user
     * @param session
     * @return
     */
    @Secured(value = "IS_AUTHENTICATED_FULLY")
    @RequestMapping(value = "/profile/checkout", method = RequestMethod.GET)
    @ResponseBody
    private ModelAndView accountCheckout(@ActiveUser User user, HttpSession session) {

        if (user != null) {
            ModelAndView modelAndView = initModelAndView();
            updateSession(user, session);
            Customer customer = getActiveUser(session);
            modelAndView.addObject("carts", customer.getCartsByCustomerId());
            double total = 0.0;
            for (Cart cart : customer.getCartsByCustomerId()) {
                total += cart.getProductByProductId().getPrice() * cart.getProductAmount();
            }
            modelAndView.addObject("total", total);
            modelAndView.addObject("address", customer.getAddressByAddressId());
            modelAndView.addObject("page", "checkoutPartial.jsp");
            return modelAndView;
        }
        return new ModelAndView("login");
    }

    /**
     * Get invoice according to submitted order
     *
     * @param user
     * @param orderId
     * @param session
     * @return
     */
    @Secured(value = "IS_AUTHENTICATED_FULLY")
    @RequestMapping(value = "/profile/invoice", method = RequestMethod.GET)
    @ResponseBody
    private ModelAndView orderInvoice(@ActiveUser User user, @RequestParam(value = "orderId", required = false) Integer orderId, HttpSession session) {

        if (user != null) {
            updateSession(user, session);
            ModelAndView modelAndView = initModelAndView();
            Customer customer = getActiveUser(session);
            ProductOrder productOrder = null;
            if (orderId == null) {
                productOrder = customer.getProductOrdersByCustomerId().get(customer.getProductOrdersByCustomerId().size() - 1);
            } else {
                Optional<ProductOrder> optional = customer.getProductOrdersByCustomerId().stream().filter(productOrder1 -> {
                    return productOrder1.getProductOrderId() == orderId;
                }).findFirst();
                if (optional.isPresent()) {
                    productOrder = optional.get();
                }
            }
            modelAndView.addObject("page", "invoicePartial.jsp");
            modelAndView.addObject("productOrder", productOrder);
            return modelAndView;
        }
        return new ModelAndView("login");
    }

    /**
     * Print invoice
     *
     * @param user
     * @param orderId
     * @param session
     * @return
     */
    @RequestMapping(value = "/profile/invoice/print", method = RequestMethod.GET)
    @ResponseBody
    private ModelAndView orderInvoicePrinted(@ActiveUser User user, @RequestParam(value = "orderId", required = false) Integer orderId, HttpSession session) {

        ModelAndView modelAndView = orderInvoice(user, orderId, session);
        if (user != null) {
            modelAndView.setViewName("customer/invoicePrint");
        }
        return modelAndView;
    }
}
