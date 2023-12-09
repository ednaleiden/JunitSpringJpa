package com.productosTest;


import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductoTest {


    @Autowired
    private ProductoRepositorio repositorio;

    @Test
    @Rollback(value = false)
    public void testProducto() throws Exception {
        Producto producto = new Producto("Televisor 23", 300);
        Producto productoGuardar = repositorio.save(producto);

        assertNotNull(productoGuardar);

    }

    @Test
    public void testBuscarProductoPorNombre() throws Exception {
        String nombre = "Televisor";
        Producto producto = repositorio.findByNombre(nombre);

        Assertions.assertThat(producto.getNombre()).isEqualTo(nombre);
    }

    @Test
    public void testBuscarProductoPorNombreNoExistente() throws Exception {
        String nombre = "Televisor 11";
        Producto producto = repositorio.findByNombre(nombre);

        assertNull(producto);
    }
    @Test
    @Rollback(value = false)
    public void testActualizarProducto() throws Exception {
        String nombre = "Televisor 11";
        Producto producto = new Producto(nombre,102);
        producto.setId(3);

        repositorio.save(producto);

        Producto productoActualizado = repositorio.findByNombre(nombre);
        Assertions.assertThat(productoActualizado.getNombre()).isEqualTo(nombre);
    }

    @Test
    public void testListarProductos(){
        List<Producto> productos = (List<Producto>)repositorio.findAll();

        for (Producto producto : productos){
            System.out.println(producto);
        }

        Assertions.assertThat(productos.size()).isGreaterThan(0);

    }

    @Test
    @Rollback(value = false)
    public void testEliminarProducto() throws Exception {

       Integer id = 5;

       boolean esExisteAntesDeEliminar = repositorio.findById(id).isPresent();
       repositorio.deleteById(id);
       boolean noExisteDespuesDeEliminar = repositorio.findById(id).isPresent();

       assertTrue(esExisteAntesDeEliminar);
       assertFalse(noExisteDespuesDeEliminar);


    }



}
