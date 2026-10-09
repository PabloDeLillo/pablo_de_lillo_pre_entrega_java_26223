/**
 * Proyecto: TechLab - Sistema de gestión de productos y pedidos
 * Desarrollador: Pablo De Lillo
 *
 * Acá concentro las operaciones de alta, búsqueda, modificación y baja de productos.
 */
package com.techlab.servicios;
import com.techlab.productos.Producto;
import com.techlab.excepciones.ProductoNoEncontradoException;
import java.util.ArrayList;import java.util.Collections;import java.util.List;
public class ProductoService {
 private final List<Producto> productos=new ArrayList<>();
 public void agregarProducto(Producto p){if(p==null)throw new IllegalArgumentException("Producto nulo");productos.add(p);}
 public List<Producto> getProductos(){return Collections.unmodifiableList(productos);}
 public Producto buscarPorId(int id) throws ProductoNoEncontradoException {for(Producto p:productos)if(p.getId()==id)return p;throw new ProductoNoEncontradoException("No existe un producto con ID "+id);}
 public List<Producto> buscarPorNombre(String nombre) throws ProductoNoEncontradoException {List<Producto> encontrados=new ArrayList<>();for(Producto p:productos)if(p.getNombre().toLowerCase().contains(nombre.trim().toLowerCase()))encontrados.add(p);if(encontrados.isEmpty())throw new ProductoNoEncontradoException("No se encontró el producto: "+nombre);return encontrados;}
 public boolean eliminarPorId(int id) throws ProductoNoEncontradoException {return productos.remove(buscarPorId(id));}
 public void listarProductos(){if(productos.isEmpty())System.out.println("No hay productos registrados.");else {System.out.println("--- PRODUCTOS ---");for(Producto p:productos)System.out.println(p);}}
}
