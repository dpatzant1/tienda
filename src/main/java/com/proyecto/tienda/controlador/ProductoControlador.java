
package com.proyecto.tienda.controlador;

import com.proyecto.tienda.entidades.Producto;
import com.proyecto.tienda.servicio.ProductoServicio;
import com.proyecto.tienda.util.reportes.ProductoExporterExcel;
import com.proyecto.tienda.util.reportes.ProductoExporterPDF;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductoControlador {
    
    @Autowired
    private ProductoServicio productoServicio;
    
    @GetMapping("/")
    @RequestMapping("/")
    public String verPaginaDeInicio(Model modelo,@Param("palabraClave") String palabraClave){
        
        List<Producto> listaProductos = productoServicio.listAll(palabraClave);
       
        modelo.addAttribute("listaProductos", listaProductos);
        modelo.addAttribute("palabraClave",palabraClave);
        return "Index";
    }
    
    @RequestMapping("/nuevo")
    public String mostrarFormularioDeRegistrarProducto(Model modelo){
        Producto producto = new Producto();
        modelo.addAttribute("producto", producto);
        return "nuevo_producto";
    }
    
    @RequestMapping(value ="/guardar",method = RequestMethod.POST)
    public String guardarProducto(@ModelAttribute("producto") Producto producto){
        productoServicio.save(producto);
        return "redirect:/";
    }
    
    @RequestMapping("/editar/{id}")
    public ModelAndView mostrarFormularioDeEditarProducto(@PathVariable(name = "id")Long id){
        ModelAndView modelo = new ModelAndView("editar_producto");
        Producto producto = productoServicio.get(id);
        modelo.addObject("producto", producto);
        return modelo;
    }
    
    @RequestMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable(name = "id")Long id){
        productoServicio.delete(id);
        return "redirect:/";
    }
    
    @GetMapping("/exportarPDF")
    public void exportarListadoDeProductosPDF(HttpServletResponse response) throws IOException{
        response.setContentType("application/pdf");
        
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());
        
        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Productos_" + fechaActual + ".pdf";
        
        response.setHeader(cabecera, valor);
        
        List<Producto> productos = productoServicio.findAll();
        
        ProductoExporterPDF exporter = new ProductoExporterPDF(productos);
        exporter.exportar(response); 
    }
    
    @GetMapping("/exportarExcel")
    public void exportarListadoDeProductosExcel(HttpServletResponse response) throws IOException{
        response.setContentType("application/octet-stream");
        
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());
        
        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Productos_" + fechaActual + ".xlsx";
        
        response.setHeader(cabecera, valor);
        
        List<Producto> productos = productoServicio.findAll();
        
        ProductoExporterExcel exporter = new ProductoExporterExcel(productos);
        exporter.exportar(response); 
    }
}
