
package com.Egg.Biblioteca.repositories;

import com.Egg.Biblioteca.entidades.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EditorialRepositorio extends JpaRepository<Editorial, String> {
}
