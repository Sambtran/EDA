package solutions;


import ds.Maze;
import ds.MazeGeneratorDFS;
import ds.MazeSolver;

import java.util.List;

public class PruebaMaze {
    public static void main(String[] args) {
        MazeGeneratorDFS generator = new MazeGeneratorDFS();
        Maze maze = generator.generate(10, 10); // tamaño impar

        int[] start = {1, 1};
        int[] end = {maze.rows - 2, maze.cols - 2};

        MazeSolver solver = new MazeSolver();
        List<int[]> path = solver.solve(maze, start, end);

        // Marcar camino con 2 en la matriz
        for (int[] step : path) {
            int x = step[0], y = step[1];
            if (maze.data[x][y] == Maze.EMPTY)
                maze.data[x][y] = 2;
        }

        printMaze(maze);
    }

    private static void printMaze(Maze maze) {
        for (int i = 0; i < maze.rows; i++) {
            for (int j = 0; j < maze.cols; j++) {
                switch (maze.data[i][j]) {
                    case Maze.WALL -> System.out.print("#");
                    case 2 -> System.out.print("2");
                    default -> System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}

