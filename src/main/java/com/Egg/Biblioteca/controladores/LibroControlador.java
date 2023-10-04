
package com.Egg.Biblioteca.controladores;

import com.Egg.Biblioteca.entidades.Autor;
import com.Egg.Biblioteca.entidades.Editorial;
import com.Egg.Biblioteca.entidades.Libro;
import com.Egg.Biblioteca.excepciones.MiException;
import com.Egg.Biblioteca.servicios.AutorServicio;
import com.Egg.Biblioteca.servicios.EditorialServicios;
import com.Egg.Biblioteca.servicios.LibroServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author jairg
 */
@Controller
@RequestMapping("/libro")
public class LibroControlador {
    
    @Autowired
    private LibroServicio libroServicio;
    
    @Autowired
    private AutorServicio autorServicio;
    
    @Autowired
    EditorialServicios editorialServicio;
    
    
    
    @GetMapping("/registrar") //localhost:8081/libro/registrar
    public String registrar(ModelMap modelo){
        List<Autor> autores = autorServicio.listarAutores();
        List<Editorial> editoriales = editorialServicio.listarEditoriales();
        
        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales",editoriales);
        return "libro_form.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam(required=false) Long isbn, @RequestParam String titulo, @RequestParam(required=false) Integer ejemplares,
            @RequestParam String idAutor, @RequestParam String idEditorial, ModelMap modelo) {
        
        //DEBE CONCORDAR EL NOMBRE DE LA VARIABLE COOOON EL name=" " DEL HTML DE EDITORIAL, CON LOS INPUT EN EL METODO POST
        //MODELMAP SIRVE PARA: INSERTEMOS TODA LA INFO QUE SE MUESTRE EN LA INTERFAZ DE USUARIO
        
        try
        {
            libroServicio.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
            modelo.put("exito","El libro fue cargado correctamente");
        }
        catch(MiException ex){
            //Logger.getLogger(LibroControlador.class.getName()).log(Level.SEVERE, null, ex);
            List<Autor> autores = autorServicio.listarAutores();
            List<Editorial> editoriales = editorialServicio.listarEditoriales();
            modelo.addAttribute("autores", autores);
            modelo.addAttribute("editoriales", editoriales);
            
            modelo.put("error",ex.getMessage());
            return "libro_form.html";
        }
        return "index.html";
    }
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        List<Libro> libros = libroServicio.listarLibros();
        
        modelo.addAttribute("libros", libros);
        
        return "libro_list.html";
    }
    
}
