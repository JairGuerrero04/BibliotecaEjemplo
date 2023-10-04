
package com.Egg.Biblioteca.servicios;

import com.Egg.Biblioteca.entidades.Autor;
import com.Egg.Biblioteca.entidades.Editorial;
import com.Egg.Biblioteca.entidades.Libro;
import com.Egg.Biblioteca.excepciones.MiException;
import com.Egg.Biblioteca.repositories.AutorRepositorio;
import com.Egg.Biblioteca.repositories.EditorialRepositorio;
import com.Egg.Biblioteca.repositories.LibroRepositorio;
import java.util.ArrayList;
import java.util.Date;
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
public class LibroServicio {
    
    @Autowired //es para que no tengas que crear el objeto porque son INTERFACES
    private LibroRepositorio libroRepositorio;
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    
    //Transaccion establece que si el metodo se ejecuta sin tener excepciones, se ejecuta para hacer los cambios
    //si lanza una excepcion no hace nada en la BD y no se modifica nada
    //LOS METODOS CREAR SE DEBEN USAR LAS TRANSACCIONES PORQUE MODIFICAN LA BASE DE DATOS, CUANDO 
    
    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiException{
        
        validar(isbn,titulo, idAutor, idEditorial, ejemplares);
        
        Autor autor = autorRepositorio.findById(idAutor).get();
        Editorial editorial = editorialRepositorio.findById(idEditorial).get();
        //LLEGAR DE UN FORMULARIO
        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        
        libro.setAlta(new Date());
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libroRepositorio.save(libro); //GUARDO EL LIBRO CREADO EN LA BASE DE DATOS
    }
    
    
    public List<Libro> listarLibros(){
        List<Libro> libros = new ArrayList();
        libros = libroRepositorio.findAll(); //Encuentra todos los datos que estan guardados en la tabla libro
        return libros;
    }
    
    
    public void modificarLibro(Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares) throws MiException{ //LE PIDO LOS DATOS QUE QUIERO MODIFICAR
        validar(isbn,titulo, idAutor, idEditorial, ejemplares);
        
        Optional<Libro> respuesta = libroRepositorio.findById(isbn);
        //Este optinal sirve para saber si existe el objeto que buscamos con true o false
        //si esta presente se puede obtener la info con .get()
        
        Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);
        Autor autor = new Autor();
        Editorial editorial = new Editorial();
        if(respuestaAutor.isPresent())
        {
            autor = respuestaAutor.get();
        }
        if(respuestaEditorial.isPresent())
        {
            editorial = respuestaEditorial.get();
        }
        
        if(respuesta.isPresent()){
            Libro libro = respuesta.get();
            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setEjemplares(ejemplares);
            
            this.libroRepositorio.save(libro);
        }
    }
    
    
    private void validar(Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares) throws MiException{
        if(isbn == null)
        {
            throw new MiException("El isbn no puede ser nulo");
        }
        if(titulo.isEmpty() || titulo==null)
        {
            throw new MiException("El titulo no puede ser nulo o estar vacío");
        }
        if(ejemplares == null)
        {
            throw new MiException("Ejemplares no pueden ser nulos");
        }
        if(idAutor.isEmpty() || idAutor==null)
        {
            throw new MiException("El ID del autor no puede ser nulo o estar vacío");
        }
        if(idEditorial.isEmpty() || idEditorial==null)
        {
            throw new MiException("El ID de la editorial no puede ser nulo o estar vacío");
        }
    }
    
   
}
