package com.Egg.Biblioteca.repositories;

import com.Egg.Biblioteca.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//SIRVEN COMO INTERFACES ENTRE EL MODELO DE OBJETOS Y LA BASE DE DATOS
//crean, modifican, eliminan y modifican los objetos de la BD

//Maneja un repositorio de tipo entidad libro y la llave primaria es de tipo Long
public interface LibroRepositorio extends JpaRepository<Libro, Long> {
    @Query("SELECT l FROM Libro l WHERE l.titulo = :titulo")
    public Libro buscarPorTitulo(@Param("titulo") String titulo);

    @Query("SELECT l FROM Libro l WHERE l.autor.nombre = :nombre")
    public Libro buscarPorAutor(@Param("nombre") String nombre);
}
