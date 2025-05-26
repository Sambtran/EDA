package ds;

import adt.MazeGenerator;

import java.util.*;

public class MazeGeneratorDFS implements MazeGenerator {
    private static final int[][] DIRECTIONS = {
            {0, 2}, {0, -2}, {2, 0}, {-2, 0} // derecha, izquierda, abajo, arriba
    };

    private Random random = new Random();

    @Override
    public Maze generate(int rows, int cols) {
        if (rows % 2 == 0) rows++; // Aseguramos número impar de filas
        if (cols % 2 == 0) cols++; // Aseguramos número impar de columnas

        Maze maze = new Maze(rows, cols);

        // Inicializar todas las celdas como paredes
        for (int i = 0; i < rows; i++)
            Arrays.fill(maze.data[i], Maze.WALL);

        // Iniciar DFS desde (1,1)
        dfs(maze, 1, 1, new boolean[rows][cols]);

        return maze;
    }

    private void dfs(Maze maze, int row, int col, boolean[][] visited) {
        visited[row][col] = true;
        maze.data[row][col] = Maze.EMPTY;

        List<int[]> dirs = new ArrayList<>(Arrays.asList(DIRECTIONS));
        Collections.shuffle(dirs); // Orden aleatorio

        for (int[] dir : dirs) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (isValid(maze, newRow, newCol) && !visited[newRow][newCol]) {
                // Eliminar pared intermedia
                maze.data[row + dir[0] / 2][col + dir[1] / 2] = Maze.EMPTY;
                dfs(maze, newRow, newCol, visited);
            }
        }
    }

    private boolean isValid(Maze maze, int row, int col) {
        return row > 0 && col > 0 && row < maze.rows - 1 && col < maze.cols - 1;
    }
}
