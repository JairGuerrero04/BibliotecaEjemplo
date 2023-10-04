
package com.Egg.Biblioteca.servicios;

import com.Egg.Biblioteca.entidades.Autor;
import com.Egg.Biblioteca.entidades.Editorial;
import com.Egg.Biblioteca.excepciones.MiException;
import com.Egg.Biblioteca.repositories.EditorialRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jairg
 */
@Service
public class EditorialServicios {
    
    //ESTO DE AUTOWIRED ES PARA SUSTITUIR EL PERSIST DE JPA
    @Autowired
    EditorialRepositorio editorialRepositorio;
    
    public void crearEditorial(String nombre) throws MiException{ 
        validar(nombre);
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorialRepositorio.save(editorial); //ES UN PERSIST ()
    }
    
    public List<Editorial> listarEditoriales(){
        List<Editorial> editoriales = new ArrayList();
        editoriales = editorialRepositorio.findAll(); //Encuentra todos los datos que estan guardados en la tabla libro
        return editoriales;
    }
    
    public void modificarEditorial(String nombre, String id){
         Optional<Editorial> respuesta = editorialRepositorio.findById(id);
         if(respuesta.isPresent())
         {
             Editorial editorial = respuesta.get();
             editorial.setNombre(nombre);
             this.editorialRepositorio.save(editorial);
         }
     }
    
    private void validar(String nombre) throws MiException{
       if(nombre.isEmpty() || nombre==null)
       {
           throw new MiException("La editorial no puede ser nulo o estar vacío");
       }
     }
}
