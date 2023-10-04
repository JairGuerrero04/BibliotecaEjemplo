
package com.Egg.Biblioteca.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author jairg
 */
@Controller
@RequestMapping("/") //esto significa que hace directamente el llamado a esta clase

public class PortalControlador {
    
    @GetMapping("/")
    public String index(){
        
        return "index.html";
    }
}
