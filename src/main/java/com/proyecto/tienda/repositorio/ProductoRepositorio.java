
package com.proyecto.tienda.repositorio;

import com.proyecto.tienda.entidades.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductoRepositorio extends JpaRepository<Producto, Long>{
    @Query("SELECT p FROM Producto p WHERE" + 
            " CONCAT(p.codigo,p.nombre,p.marca)"+
            " LIKE %?1%")
    public List<Producto> findAll(String palabraClave);
}
