
package com.proyecto.tienda.util.reportes;


import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.proyecto.tienda.entidades.Producto;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.io.IOException;
import java.util.List;

public class ProductoExporterPDF {
    private List<Producto> listaProductos;

    public ProductoExporterPDF(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }
    
    private void escribirCabeceraDeLaTabla(PdfPTable tabla){
        PdfPCell celda = new PdfPCell();
        
        celda.setBackgroundColor(Color.darkGray);
        celda.setPadding(5);
        
        Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
        
        fuente.setColor(Color.WHITE);
        
        celda.setPhrase(new Phrase("Código",fuente));
        tabla.addCell(celda);
        celda.setPhrase(new Phrase("Nombre",fuente));
        tabla.addCell(celda);
        celda.setPhrase(new Phrase("Marca",fuente));
        tabla.addCell(celda);
        celda.setPhrase(new Phrase("Precio",fuente));
        tabla.addCell(celda);
        celda.setPhrase(new Phrase("Cantidad",fuente));
        tabla.addCell(celda);
    }
    
    private void escribirDatosDeLaTabla(PdfPTable tabla){
        for(Producto producto : listaProductos){
            tabla.addCell(producto.getCodigo());
            tabla.addCell(producto.getNombre());
            tabla.addCell(producto.getMarca());
            tabla.addCell(String.valueOf(producto.getPrecio()));
            tabla.addCell(String.valueOf(producto.getCantidad()));
        }
    }
    
    public void exportar(HttpServletResponse response) throws IOException{
        Document documento = new Document(PageSize.A4);
        PdfWriter.getInstance(documento, response.getOutputStream());
        
        documento.open();
        
        Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuente.setColor(Color.BLACK);
        fuente.setSize(18);
        
        Paragraph titulo = new Paragraph("Lista de Productos", fuente);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        documento.add(titulo);
        
        PdfPTable tabla = new PdfPTable(5);
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(15);
        tabla.setWidths(new float[] {1.3f,2.3f,2.1f,2.1f,1f});
        tabla.setWidthPercentage(110);
        
        escribirCabeceraDeLaTabla(tabla);
        escribirDatosDeLaTabla(tabla);
        
        documento.add(tabla);
        documento.close();
    }
}
