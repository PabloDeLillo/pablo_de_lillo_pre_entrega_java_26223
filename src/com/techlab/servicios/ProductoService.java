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
 private final com.techlab.repositorios.Repositorio<Producto> repo=new com.techlab.repositorios.Repositorio<>();
 public void agregarProducto(Producto p){if(p==null)throw new IllegalArgumentException("Producto nulo");repo.guardar(p);}
 public List<Producto> getProductos(){return repo.listar();}
 public Producto buscarPorId(int id) throws ProductoNoEncontradoException {for(Producto p:repo.listar())if(p.getId()==id)return p;throw new ProductoNoEncontradoException("No existe un producto con ID "+id);}
 public List<Producto> buscarPorNombre(String nombre) throws ProductoNoEncontradoException {List<Producto> encontrados=new ArrayList<>();for(Producto p:repo.listar())if(p.getNombre().toLowerCase().contains(nombre.trim().toLowerCase()))encontrados.add(p);if(encontrados.isEmpty())throw new ProductoNoEncontradoException("No se encontró el producto: "+nombre);return encontrados;}
 public boolean eliminarPorId(int id) throws ProductoNoEncontradoException {return repo.eliminar(buscarPorId(id).getId());}
 public void listarProductos(){if(repo.listar().isEmpty())System.out.println("No hay productos registrados.");else {System.out.println("--- PRODUCTOS ---");for(Producto p:repo.listar())System.out.println(p);}}
}
