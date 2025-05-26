package adt;

import ds.Maze;

public interface MazeSolver {
    List<int[]> solve(Maze maze, int[] origin, int[] destination);
}
