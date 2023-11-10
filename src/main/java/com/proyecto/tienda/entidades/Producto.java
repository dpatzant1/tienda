
package com.proyecto.tienda.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="producto")
public class Producto implements Serializable{
    
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    private Long id;
    
    @NotEmpty
    @Column(nullable = false, length=60)
    private String codigo;
    @NotEmpty
    @Column(nullable = false, length=60)
    private String nombre;
    @NotEmpty
    @Column(nullable = false, length=60)
    private String marca;
    @NotNull
    @Column(nullable = false)
    private float precio;
    @NotNull
    @Column(nullable = false)
    private int cantidad;

    public Producto(Long id, String codigo, String nombre, String marca, float precio, int cantidad) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public Producto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
