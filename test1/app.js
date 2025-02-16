const encontrarCamino = require("./utils/bfs");
const { validarEntrada } = require("./utils/validaciones");
const casos = require("./config/casosPrueba");

console.log("=== PRUEBA DE RUTEO - PATHFINDING ===");

casos.forEach(({ descripcion, matriz, inicio, meta }) => {
  console.log(`\nPrueba: ${descripcion}`);

  const error = validarEntrada(matriz, inicio, meta);
  if (error) {
    console.log(error);
  } else {
    const resultado = encontrarCamino(matriz, inicio, meta);
    console.log("Resultado:", resultado);
  }
});
