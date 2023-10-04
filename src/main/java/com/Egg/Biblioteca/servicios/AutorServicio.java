
package com.Egg.Biblioteca.servicios;

import com.Egg.Biblioteca.entidades.Autor;
import com.Egg.Biblioteca.entidades.Libro;
import com.Egg.Biblioteca.excepciones.MiException;
import com.Egg.Biblioteca.repositories.AutorRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServicio {
    
    @Autowired
    AutorRepositorio autorRepositorio;
    
    public void crearAutor(String nombre) throws MiException{
        
        validar(nombre);
        Autor autor = new Autor();
        autor.setNombre(nombre);
        
        autorRepositorio.save(autor); //USO DE LOS METODOS QUE GUARDA LA ENTIDAD
    }
    
     public List<Autor> listarAutores(){
        List<Autor> autores = new ArrayList();
        autores = autorRepositorio.findAll(); //Encuentra todos los datos que estan guardados en la tabla libro
        return autores;
    }
     
     public void modificarAutor(String nombre, String id){
         Optional<Autor> respuesta = autorRepositorio.findById(id);
         if(respuesta.isPresent())
         {
             Autor autor = respuesta.get();
             autor.setNombre(nombre);
             this.autorRepositorio.save(autor);
         }
     }
     
     private void validar(String nombre) throws MiException{
       if(nombre.isEmpty() || nombre==null)
       {
           throw new MiException("El titulo no puede ser nulo o estar vacío");
       }
     }
     
     
     public Autor getOne(String id){
         return this.autorRepositorio.getOne(id);
     }
}
