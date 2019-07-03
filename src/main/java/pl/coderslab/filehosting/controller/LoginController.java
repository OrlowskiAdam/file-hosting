package pl.coderslab.filehosting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.filehosting.entity.User;
import pl.coderslab.filehosting.repository.UserRepository;
import pl.coderslab.filehosting.validation.UserRegisterValidationGroup;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.groups.Default;

@Controller
public class LoginController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "login/register";
    }

    @PostMapping("/register")
    public String saveRegistrationForm(@Validated({UserRegisterValidationGroup.class, Default.class}) User user, BindingResult result, @RequestParam String password2) {
        if (result.hasErrors()) {
            return "login/register";
        }

        User existingUser = userRepository.findFirstByLogin(user.getLogin());
        if (existingUser != null) {
            result.addError(new FieldError("user", "login", "Login already exists"));
            return "login/register";
        }

        existingUser = userRepository.findFirstByEmail(user.getEmail());
        if (existingUser != null) {
            result.addError(new FieldError("user", "email",
                    "Email already exists"));
            return "login/register";
        }

        if (!user.getPassword().equals(password2)) {
            result.addError(new FieldError("user", "password",
                    "Passwords do not match"));
            return "login/register";
        }

        userRepository.save(user);
        return "redirect:login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login/login";
    }

    @PostMapping("/login")
    public String login(@Valid User user, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            return "login/login";
        }

        boolean loggedIn = true;
        User existingUser = userRepository.findFirstByLogin(user.getLogin());
        if (existingUser == null) {
            loggedIn = false;
        } else if (!user.getPassword().equals(existingUser.getPassword())) {
            loggedIn = false;
        }

        if (!loggedIn) {
            result.addError(new FieldError("user", "password",
                    "Incorrect login or password"));
            return "login/login";
        }

        session.setAttribute("user", existingUser);
        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
