/** TechLab - Desarrollador: Pablo De Lillo. */
package com.techlab.repositorios;
import com.techlab.interfaces.Identificable;
import java.util.*;
public class Repositorio<T extends Identificable> {
 private final Map<Integer,T> datos=new LinkedHashMap<>();
 public void guardar(T elemento) {if(elemento==null)throw new IllegalArgumentException("Elemento nulo.");datos.put(elemento.getId(),elemento);}
 public Optional<T> buscar(int id){return Optional.ofNullable(datos.get(id));}
 public List<T> listar(){return Collections.unmodifiableList(new ArrayList<>(datos.values()));}
 public boolean eliminar(int id){return datos.remove(id)!=null;}
 public boolean existe(int id){return datos.containsKey(id);}
}
