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

    @GetMapping("helloworld")
    public String onGet( Model model, @RequestParam(name="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return "helloworld";
    }
    
    @GetMapping(value={"/", "home"})
    public String onGetIndex( Model model) {
        //model.addAttribute("name", name);
//        if (true) 
//          throw new RuntimeException("Test Exception");
        return "index_page";
    }    


    @GetMapping("login")
    public String onGetLogin( Model model) {
        //model.addAttribute("name", name);
        return "loginForm";
    }    
   
    @GetMapping("register")
    public String onGetRegister( Model model) {
        //model.addAttribute("name", name);
        return "registrationForm";
    }        

    
/*    
    @GetMapping("/timestable")
    public String onGetTimesTable( Model model) {
        //model.addAttribute("name", name);
        return "timestable";
    }        
    
    @GetMapping("/randomtimestable")
    public String onGetRandomTimesTable( Model model) {
        //model.addAttribute("name", name);
        return "randomtimestable";
    }        
    
    @GetMapping("/drawingpage")
    public String onGetDrawingPage( Model model) {
        //model.addAttribute("name", name);
        return "drawingpage";
    }        
*/

}
