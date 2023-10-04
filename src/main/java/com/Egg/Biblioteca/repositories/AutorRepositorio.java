
package com.Egg.Biblioteca.repositories;

import com.Egg.Biblioteca.entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AutorRepositorio extends JpaRepository<Autor, String> {
    
    //CON EL REPOSITORIO PUEDO GUADAR, MODIFICAR, ELIMINAR Y CREAR NUEVOS OBJETOS QUE SE GUARDAN EN LA BASE DE DATOS
}
