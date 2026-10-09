# TechLab - Gestión de productos, categorías y pedidos

**Desarrollador:** Pablo De Lillo

Aplicación de consola en Java con programación orientada a objetos. Incluye CRUD de categorías, alta/búsqueda/edición/baja de productos, pedidos con validación de stock acumulado, interfaces `Identificable` y `Calculable`, repositorio genérico en memoria, menús independientes y utilidades `Validaciones` y `Secuencias`.

## Requisitos
Java JDK 17 o superior. No requiere librerías externas ni base de datos. Los datos se mantienen en memoria mientras se ejecuta.

## Compilar y ejecutar
Desde la carpeta `TechLab`:

```bash
mkdir -p out
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out com.techlab.app.Main
```

En Windows PowerShell:

```powershell
New-Item -ItemType Directory -Force out | Out-Null
Get-ChildItem -Recurse src -Filter *.java | ForEach-Object FullName | Set-Content fuentes.txt
javac -encoding UTF-8 -d out '@fuentes.txt'
java -cp out com.techlab.app.Main
Remove-Item fuentes.txt
```

## Uso
El menú principal separa Productos, Categorías y Pedidos. Hay tres categorías y tres productos de ejemplo. No es posible eliminar una categoría que tenga productos asociados. Al confirmar pedidos se verifica el stock total por producto antes de descontarlo.
