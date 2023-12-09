package com.productosTest;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepositorio extends CrudRepository<Producto, Integer> {

    public  Producto findByNombre(String nombre);
}
