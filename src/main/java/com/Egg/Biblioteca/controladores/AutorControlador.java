
package com.Egg.Biblioteca.controladores;

import com.Egg.Biblioteca.entidades.Autor;
import com.Egg.Biblioteca.excepciones.MiException;
import com.Egg.Biblioteca.servicios.AutorServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author jairg
 */
@Controller
@RequestMapping("/autor") //localhost:8081/autor
public class AutorControlador {
    
    @Autowired
    private AutorServicio autorServicio;
 
    
    @GetMapping("/registrar") //localhost:8081/autor/registrar
    public String registrar(){
        return "autor_form.html";
    }
    
    @PostMapping("/registro") //SE USA ESTE EN FORMULARIOOOOS EN HTML
    public String registro(@RequestParam String nombre){ //EL STRING nombre debe coincidir con el name="" del input del html
        System.out.println("Nombre: "+ nombre);
        try
        {
            autorServicio.crearAutor(nombre);
        }
        catch (MiException ex){
            Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "autor_form.html";
    }
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        
        List<Autor> autores = this.autorServicio.listarAutores();
        
        modelo.addAttribute("autores", autores);
        
        return "autor_list.html";
    }
    
    @GetMapping("/modificar/{id}") //{id} quiere decir que se envío en la URL el valor de los id que envien, STRING ID es una variable de tipo pad
    public String modificar(@PathVariable String id, ModelMap modelo){ 
        
        modelo.put("autor", this.autorServicio.getOne(id));
        
        return "autor_modificar.html";
    }
}
