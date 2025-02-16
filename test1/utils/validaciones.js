function coordenadasValidas(matriz, x, y) {
    return x >= 0 && x < matriz.length && y >= 0 && y < matriz[0].length;
  }
  
  function validarEntrada(matriz, start, end) {
    if (!coordenadasValidas(matriz, start[0], start[1])) {
      return "Coordenadas de inicio inválidas.";
    } else if (!coordenadasValidas(matriz, end[0], end[1])) {
      return "Coordenadas de destino inválidas.";
    } else if (matriz[start[0]][start[1]] === 1) {
      return "El punto de inicio es un obstáculo, no se puede comenzar.";
    } else if (matriz[end[0]][end[1]] === 1) {
      return "La meta es un obstáculo, no se puede llegar.";
    }
    return null;
  }
  
  module.exports = { validarEntrada };
  