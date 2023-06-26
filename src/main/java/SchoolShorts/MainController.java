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
public class MainController {

    @GetMapping("/helloworld")
    public String onGet( Model model, @RequestParam(name="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return "helloworld";
    }
    
    @GetMapping("/")
    public String onGetIndex( Model model) {
        //model.addAttribute("name", name);
        return "index_page";
    }    

    @GetMapping("/baseLayout")
    public String onGetBaseLayout( Model model) {
        //model.addAttribute("name", name);
        return "baseLayout";
    }    

    
}
