package pl.coderslab.filehosting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SettingsController {
    @RequestMapping("/settings")
    public String settings() {
        return "redirect:/space";
    }
}
