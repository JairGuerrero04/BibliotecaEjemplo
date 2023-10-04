
package com.Egg.Biblioteca.controladores;

import com.Egg.Biblioteca.excepciones.MiException;
import com.Egg.Biblioteca.servicios.EditorialServicios;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author jairg
 */
@Controller
@RequestMapping("/editorial")
public class EditorialControlador {
    
    @Autowired
    EditorialServicios editorialServicio;
    
    @GetMapping("/registrar")
    public String registrar(){
        return "editorial_form.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String editorial){ //DEBE CONCORDAR EL NOMBRE DE LA VARIABLE COOOON EL name=" " DEL HTML DE EDITORIAL, CON LOS INPUT EN EL METODO POST
        System.out.println("Nombre: "+ editorial);
        try
        {
            editorialServicio.crearEditorial(editorial);
        }
        catch (MiException ex){
            
            Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "editorial_form.html";
        }
        
        return "index.html";
    }
    
}
