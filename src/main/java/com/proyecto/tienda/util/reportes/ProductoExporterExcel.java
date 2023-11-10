
package com.proyecto.tienda.util.reportes;

import com.proyecto.tienda.entidades.Producto;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ProductoExporterExcel {
    private XSSFWorkbook libro;
    private XSSFSheet hoja;
    
    private List<Producto> listaProductos;

    public ProductoExporterExcel(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
        libro = new XSSFWorkbook();
        hoja = libro.createSheet("Productos");
    }
    
    private void escribirCabeceraDeTabla(){
        Row fila = hoja.createRow(0);
        
        CellStyle estilo = libro.createCellStyle();
        XSSFFont fuente = libro.createFont();
        fuente.setBold(true);
        fuente.setFontHeight(16);
        estilo.setFont(fuente);
        
        Cell celda = fila.createCell(0);
        celda.setCellValue("Código");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(1);
        celda.setCellValue("Nombre");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(2);
        celda.setCellValue("Marca");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(3);
        celda.setCellValue("Precio");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(4);
        celda.setCellValue("Cantidad");
        celda.setCellStyle(estilo);
    }
    
    private void escribirDatosDeLaTabla(){
        int numeroFilas=1;
        
        CellStyle estilo = libro.createCellStyle();
        XSSFFont fuente = libro.createFont();
        fuente.setFontHeight(14);
        estilo.setFont(fuente);
        
        for(Producto productos : listaProductos){
            Row fila = hoja.createRow(numeroFilas ++);
            
            Cell celda = fila.createCell(0);
            celda.setCellValue(productos.getCodigo());
            hoja.autoSizeColumn(0);
            celda.setCellStyle(estilo);
            
            celda = fila.createCell(1);
            celda.setCellValue(productos.getNombre());
            hoja.autoSizeColumn(1);
            celda.setCellStyle(estilo);
            
            celda = fila.createCell(2);
            celda.setCellValue(productos.getMarca());
            hoja.autoSizeColumn(2);
            celda.setCellStyle(estilo);
            
            celda = fila.createCell(3);
            celda.setCellValue(productos.getPrecio());
            hoja.autoSizeColumn(3);
            celda.setCellStyle(estilo);
            
            celda = fila.createCell(4);
            celda.setCellValue(productos.getCantidad());
            hoja.autoSizeColumn(4);
            celda.setCellStyle(estilo);
            
        }
    }
    
    public void exportar(HttpServletResponse response) throws IOException{
        escribirCabeceraDeTabla();
        escribirDatosDeLaTabla();
        
        ServletOutputStream outPutStream = response.getOutputStream();
        libro.write(outPutStream);
        
        libro.close();
        outPutStream.close();
    }
    
}
