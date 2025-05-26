package ds;

import java.util.*;

public class MazeSolver {

    private static final int[][] directions = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    public List<int[]> solve(Maze maze, int[] origin, int[] destination) {
        Hashtable<Par, Boolean> visitado = new Hashtable<>();
        List<Par> caminoActual = new ArrayList<>();
        List<Par> caminoCorto = new ArrayList<>();

        algor(origin[0], origin[1], destination, maze, visitado, caminoActual, caminoCorto);

        // Convertir de Par a int[]
        List<int[]> resultado = new ArrayList<>();
        for (Par p : caminoCorto) {
            resultado.add(new int[]{p.x, p.y});
        }

        return resultado;
    }

    private void algor(int x, int y, int[] destino, Maze maze,
                       Hashtable<Par, Boolean> visitado,
                       List<Par> caminoActual,
                       List<Par> caminoCorto) {

        Par actual = new Par(x, y);
        if (!esValido(maze, x, y) || visitado.getOrDefault(actual, false))
            return;

        if (maze.data[x][y] == Maze.WALL) return;

        visitado.put(actual, true);
        caminoActual.add(actual);

        if (x == destino[0] && y == destino[1]) {
            if (caminoCorto.isEmpty() || caminoActual.size() < caminoCorto.size()) {
                caminoCorto.clear();
                caminoCorto.addAll(caminoActual);
            }
        } else {
            for (int[] dir : directions) {
                algor(x + dir[0], y + dir[1], destino, maze, visitado, caminoActual, caminoCorto);
            }
        }

        caminoActual.remove(caminoActual.size() - 1);
        visitado.remove(actual);
    }

    private boolean esValido(Maze maze, int x, int y) {
        return x >= 0 && y >= 0 && x < maze.rows && y < maze.cols;
    }

    class Par {
        int x, y;

        public Par(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Par)) return false;
            Par par = (Par) o;
            return x == par.x && y == par.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
