package com.coinf.controller;

import com.coinf.dto.UserRegistrationDTO;
import com.coinf.exception.EmailExistsException;
import com.coinf.exception.InvalidActivationCodeException;
import com.coinf.service.UserService;
import com.coinf.validation.UserRegValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {

    @Value("${client.application.url}")
    private String clientAppUrl;

    @Autowired
    private UserService userService;

    @InitBinder("userRegDto")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new UserRegValidator());
    }

    @ExceptionHandler(value = InvalidActivationCodeException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ModelAndView onInvalidActivationCodeException(Exception e) {

        ModelAndView mav = new ModelAndView();
        mav.addObject("errorTitle", "Invalid Activation Code");
        mav.addObject("errorMessage", e.getMessage() + ". Please contact the server admin.");
        mav.setViewName("error");

        return mav;
    }

    @GetMapping("/register")
    public String getRegistrationPage(Model model) {
        model.addAttribute("userRegDto", new UserRegistrationDTO());
        return "register";
    }

    @PostMapping("/register")
    public String handleRegistration(@ModelAttribute(value = "userRegDto") @Valid UserRegistrationDTO userRegDto,
                                     BindingResult result) {
        if (!result.hasErrors()) {
            try {
                userService.register(userRegDto);
                return "redirect:/login?registered";
            } catch (EmailExistsException e) {
                result.rejectValue("email", "message.regError");
            }
        }
        return "register";
    }

    @GetMapping(value = "/activation/{activation}")
    public String handleActivation(@PathVariable(value = "activation") String givenActivation) {

        boolean validActivation = userService.activate(givenActivation);

        if (!validActivation) {
            throw new InvalidActivationCodeException("Invalid activation code: " + givenActivation);
        }
        return "redirect:/login?activated";
    }

    @GetMapping("/failed")
    public String getFailed(Model model) {
        model.addAttribute("clientAppUrl", clientAppUrl);
        return "failed";
    }

    @GetMapping("/about")
    public String getAbout() {
        return "about";
    }

}
