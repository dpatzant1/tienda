/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.tienda.servicio;

import com.proyecto.tienda.entidades.Producto;
import com.proyecto.tienda.repositorio.ProductoRepositorio;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author patza
 */
@Service
public class ProductoServicio {
    @Autowired
    private ProductoRepositorio productoRepositorio;
    
    public List<Producto> listAll(String palabraClave){
        if(palabraClave != null){
            return productoRepositorio.findAll(palabraClave);
        }
        return productoRepositorio.findAll();
    }
    
    @Transactional()
    public List<Producto> findAll(){
        return productoRepositorio.findAll();
    }
    
    public void save(Producto producto){
        productoRepositorio.save(producto);
    }
    
    public Producto get(Long id){
        return productoRepositorio.findById(id).get();
    }
    
    public void delete(Long id){
        productoRepositorio.deleteById(id);
    }
}
