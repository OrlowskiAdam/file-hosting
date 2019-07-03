package pl.coderslab.filehosting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DiskController {
    @RequestMapping("/disk/{id}")
    @ResponseBody
    public String disk(@PathVariable Long id) {
        return "w";
    }
}
