/** TechLab - Desarrollador: Pablo De Lillo. */
package com.techlab.servicios;
import com.techlab.productos.Categoria;
import com.techlab.repositorios.Repositorio;
import com.techlab.utilidades.Secuencias;
import java.util.List;
public class CategoriaService {
 private final Repositorio<Categoria> repo=new Repositorio<>();
 public Categoria crear(String nombre,String descripcion){Categoria c=new Categoria(Secuencias.siguienteCategoria(),nombre,descripcion);repo.guardar(c);return c;}
 public void guardar(Categoria c){repo.guardar(c);}
 public Categoria buscar(int id){return repo.buscar(id).orElseThrow(()->new IllegalArgumentException("Categoría no encontrada: "+id));}
 public List<Categoria> listar(){return repo.listar();}
 public void actualizar(int id,String nombre,String descripcion){Categoria c=buscar(id);c.setNombre(nombre);c.setDescripcion(descripcion);}
 public void eliminar(int id,List<com.techlab.productos.Producto> productos){buscar(id);for(var p:productos)if(p.getCategoria().getId()==id)throw new IllegalArgumentException("No se puede eliminar una categoría con productos asociados.");repo.eliminar(id);}
}
