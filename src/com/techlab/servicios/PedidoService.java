/**
 * Proyecto: TechLab - Sistema de gestión de productos y pedidos
 * Desarrollador: Pablo De Lillo
 *
 * Antes de confirmar una compra, reviso que alcance el stock para todos los productos.
 */
package com.techlab.servicios;
import com.techlab.excepciones.StockInsuficienteException;
import com.techlab.pedidos.LineaPedido;import com.techlab.pedidos.Pedido;
import com.techlab.productos.Producto;
import java.util.ArrayList;import java.util.List;import java.util.LinkedHashMap;import java.util.Map;
public class PedidoService {
 private final List<Pedido> pedidos=new ArrayList<>();
 public Pedido crearPedido(List<LineaPedido> lineas) throws StockInsuficienteException {
  if(lineas==null||lineas.isEmpty())throw new IllegalArgumentException("Pedido vacío.");
  // Si se repite un producto en el pedido, sumo sus cantidades antes de validar.
  Map<Producto,Integer> cantidades=new LinkedHashMap<>();
  for(LineaPedido l:lineas){Producto p=l.getProducto();long suma=(long)cantidades.getOrDefault(p,0)+l.getCantidad();if(suma>Integer.MAX_VALUE)throw new StockInsuficienteException("Cantidad demasiado grande para "+p.getNombre());cantidades.put(p,(int)suma);}
  // Primero verifico todo; recién después descuento el stock.
  for(Map.Entry<Producto,Integer> e:cantidades.entrySet())if(e.getValue()>e.getKey().getStock())throw new StockInsuficienteException("Stock insuficiente para "+e.getKey().getNombre()+". Disponible: "+e.getKey().getStock()+", solicitado: "+e.getValue());
  Pedido pedido=new Pedido(lineas);
  for(Map.Entry<Producto,Integer> e:cantidades.entrySet())e.getKey().setStock(e.getKey().getStock()-e.getValue());
  pedidos.add(pedido);return pedido;
 }
 public List<Pedido> getPedidos(){return java.util.Collections.unmodifiableList(pedidos);}
 public void listarPedidos(){if(pedidos.isEmpty())System.out.println("No hay pedidos realizados.");else for(Pedido p:pedidos)System.out.println(p);}
}
