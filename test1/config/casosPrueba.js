const casos = [
    {
      descripcion: "Camino posible en una cuadrícula estándar",
      matriz: [
        [0, 1, 0, 0, 0],
        [0, 1, 0, 1, 0],
        [0, 0, 0, 1, 0],
        [0, 1, 1, 1, 0],
        [0, 0, 0, 0, 0],
      ],
      inicio: [0, 0],
      meta: [4, 4],
    },
    {
      descripcion: "Sin camino posible (bloqueado por obstáculos)",
      matriz: [
        [0, 1, 0, 0, 0],
        [0, 1, 0, 1, 0],
        [0, 0, 0, 1, 1],
        [0, 1, 1, 1, 1],
        [0, 0, 0, 1, 4],
      ],
      inicio: [0, 0],
      meta: [4, 4],
    },
    {
      descripcion: "Cuadrícula vacía (sin obstáculos)",
      matriz: Array(5).fill(Array(5).fill(0)), // Matriz de 5x5 libre
      inicio: [0, 0],
      meta: [4, 4],
    },
    {
      descripcion: "Coordenadas fuera de rango",
      matriz: [
        [0, 0, 0],
        [0, 1, 0],
        [0, 0, 0],
      ],
      inicio: [-1, 0],
      meta: [2, 2],
    },
  ];
  
  module.exports = casos;
  