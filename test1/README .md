# Test 1: Resolución de un Problema de Ruteo (Pathfinding)

Este directorio contiene el código que implementa la **búsqueda de la ruta más corta** en una cuadrícula, respetando las restricciones de movimiento (arriba, abajo, izquierda, derecha) y evitando obstáculos. Se basa en un enfoque de **Búsqueda en Anchura (BFS)** para encontrar el camino más corto, tal como se solicitó en la primera parte de la prueba técnica.

---

## Estructura de Archivos

- **config/casosPrueba.js**  
  Contiene distintos escenarios de prueba (matrices, coordenadas de inicio y fin, etc.).

- **utils/bfs.js**  
  Implementa la lógica principal de BFS (búsqueda en anchura) para encontrar el camino.

- **utils/validaciones.js**  
  Incluye verificaciones o validaciones (por ejemplo, asegurarse de que las coordenadas sean válidas, etc.).

- **app.js**  
  Punto de entrada que orquesta la lectura de los casos de prueba y la ejecución de la función BFS.

- **index.js**  
  Archivo que puede ser de ejemplo, con lo datos de las imagenes de la prueba

---

## Instrucciones de Uso

1. **Clonar o descargar** este repositorio y ubicarte en la carpeta `test1/`.
2. **Abrir** una terminal en dicha carpeta.
3. **Ejecutar** el archivo principal  `app.js`, así: node app.js, caso contrario si se desea el node index.js para el ejemplo de la prueba

