package adt;

import ds.Maze;

public interface MazeGenerator {
    Maze generate(int rows, int cols);
}
