/** TechLab - Desarrollador: Pablo De Lillo. */
package com.techlab.servicios;

import com.techlab.productos.Articulo;
import com.techlab.productos.Categoria;
import com.techlab.repositorios.Repositorio;
import com.techlab.utilidades.Secuencias;
import java.util.List;

public class CategoriaService {
    private final Repositorio<Categoria> repo = new Repositorio<>();

    public Categoria crear(String nombre, String descripcion) {
        Categoria categoria = new Categoria(Secuencias.siguienteCategoria(), nombre, descripcion);
        repo.guardar(categoria);
        return categoria;
    }

    public void guardar(Categoria categoria) { repo.guardar(categoria); }

    public Categoria buscar(int id) {
        return repo.buscar(id).orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada: " + id));
    }

    public List<Categoria> listar() { return repo.listar(); }

    public void actualizar(int id, String nombre, String descripcion) {
        Categoria categoria = buscar(id);
        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);
    }

    public void eliminar(int id, List<Articulo> articulos) {
        buscar(id);
        for (Articulo articulo : articulos) {
            if (articulo.getCategoria().getId() == id) {
                throw new IllegalArgumentException("No se puede eliminar una categoría con artículos asociados.");
            }
        }
        repo.eliminar(id);
    }
}
