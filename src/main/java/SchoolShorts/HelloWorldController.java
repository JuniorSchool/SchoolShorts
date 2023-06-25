/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SchoolShorts;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author hamma
 */
@Controller
public class HelloWorldController {

    @GetMapping("/helloworld")
    public String onGet( Model model, @RequestParam(name="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return "helloworld";
    }
}
