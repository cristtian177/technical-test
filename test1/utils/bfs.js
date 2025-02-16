// Implementación de BFS para encontrar el camino más corto
function encontrarCamino(matriz, start, end) {
  const filas = matriz.length;
  const columnas = matriz[0].length;

  // Se usa una matriz booleana para indicar si una celda fue visitada
  const visitados = Array.from({ length: filas }, () =>
    Array(columnas).fill(false)
  );

  // Se utiliza otra matriz para registrar el origen de cada celda
  const origenes = Array.from({ length: filas }, () =>
    Array(columnas).fill(null)
  );

  // Cola para almacenar celdas que se procesarán con BFS
  const cola = [];

  // Se marca el inicio como visitado y se encola
  const startx = start[0];
  const starty = start[1];
  visitados[startx][starty] = true;
  cola.push([startx, starty]);

  // Desplazamientos posibles (arriba, abajo, derecha, izquierda)
  const direcciones = [
    [-1, 0],
    [1, 0],
    [0, 1],
    [0, -1],
  ];

  // Variable booleana para saber si se alcanzó la meta
  let encontrado = false;

  // Búsqueda en anchura (BFS)
  while (cola.length > 0) {
    // Se obtiene la primera celda de la cola
    const actual = cola.shift();
    const x = actual[0];
    const y = actual[1];

    // Verifica si la celda actual corresponde a la meta
    if (x === end[0] && y === end[1]) {
      encontrado = true;
      break;
    }

    // Se exploran los vecinos en las cuatro direcciones
    for (const desplazamiento of direcciones) {
      const nuevaX = x + desplazamiento[0];
      const nuevaY = y + desplazamiento[1];

      // Validación de límites, obstáculo y si la celda fue visitada antes
      if (
        nuevaX >= 0 && nuevaX < filas &&
        nuevaY >= 0 && nuevaY < columnas &&
        matriz[nuevaX][nuevaY] === 0 &&
        !visitados[nuevaX][nuevaY]
      ) {
        visitados[nuevaX][nuevaY] = true;
        origenes[nuevaX][nuevaY] = [x, y];
        cola.push([nuevaX, nuevaY]);
      }
    }
  }

  // Reconstrucción del camino si se encontró la meta
  if (encontrado) {
    const camino = [];
    let [actualX, actualY] = end;

    // Se recorre hacia atrás desde la meta hasta el inicio
    while (actualX !== null && actualY !== null) {
      camino.unshift([actualX, actualY]);
      const origen = origenes[actualX][actualY];
      if (!origen) {
        break;
      }
      [actualX, actualY] = origen;
    }

    return camino;
  } else {
    return "No hay camino posible.";
  }
}

module.exports = encontrarCamino;
