package ds;

public class Maze {
    public static final int EMPTY = 0;
    public static final int WALL = 1;
    public int[][] data;
    public int rows;
    public int cols;
    public Maze(int rows, int cols) {
        this.data = new int[rows][cols];
        this.rows = rows;
        this.cols = cols;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                data[i][j] = WALL;
    }
    public String toString() {
        StringBuilder x= new StringBuilder();
        for (int i=0; i<rows; i++){
            for (int j=0; j<cols; j++){
                if(data[i][j] == WALL){
                    x.append("#");
                }else{
                    x.append(" ");
                }
            }
            x.append("\n");

        }
        return x.toString();
    }
}
