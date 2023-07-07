/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SchoolShorts;

import SchoolShorts.formbeans.Answer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import SchoolShorts.formbeans.TimesTable2Bean;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

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


    @ModelAttribute("allAnswers")
    public List<Answer> populateTypes() {
        return Arrays.asList(Answer.ALL);
    }    
    
    @GetMapping("gettimestableForm")
    public String onGetTimesTable(Model model) {
        TimesTable2Bean ttbean = new TimesTable2Bean();
        ttbean.setSequence("2,3,4,5,10");
        ttbean.setAnswer(Answer.forName("Yes"));
        model.addAttribute("ttbean", ttbean);
        return "timestable";
    }        

    
    @GetMapping("processtimestableForm")
    public String onProcessTimesTable( Model model, @RequestParam(name="sequence", required=true, defaultValue="2,3,4,5,10") String sequence,
                                                    @RequestParam(name="answer", required=true, defaultValue="true") String answers) 
                                    throws UnsupportedEncodingException   
    {
        
        String fpath = "../../ServletTimesTable2";
        fpath = fpath + "?sequence="+ URLEncoder.encode(sequence, "UTF-8") + "&answer=" + URLEncoder.encode(String.valueOf(answers), "UTF-8");
        model.addAttribute("filePathForJavaScript", fpath);
        return "forward:showPDFDocument";
        //return new ModelAndView("redirect:showPDFDocument", model);
    }        

    
    @GetMapping("showPDFDocument")
    public String onShowPDFDocument( Model model,
            @RequestParam(name="filePathForJavaScript", required=true,
             defaultValue="https://pdfjs.express/static/web/my-pdf-file.pdf")
                    String filePathForJavaScript) 
    {      
        return "pdfViewer";
    }        
    
    
    
/*    
    
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
