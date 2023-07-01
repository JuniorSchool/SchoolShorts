/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SchoolShorts;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author hamma
 */
@Controller
public class CountingTablesController {
    
  
    @GetMapping("countingtable")
    public String onGetCountingTable( Model model) {
        return "countingtable";
    }        


}
