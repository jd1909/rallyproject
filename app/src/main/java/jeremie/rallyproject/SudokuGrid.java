package jeremie.rallyproject;

/**
 * Created by Jeremie on 30/12/2014.
 */
public class SudokuGrid {
    private Cell[][] grid;

    public SudokuGrid(){
        grid = new Cell[6][6];
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 6; j++){
                grid[i][j] = new Cell();
            }
        }
    }

    public Cell[][] getGrid(){
        return grid;
    }

    public boolean isNotInRow(int d, int r){
        for(int i = 0; i < 6; i++){
            if(grid[i][r].getDigit() == d)
                return false;
        }
        return true;
    }

    public boolean isNotInColumn(int d, int c){
        for(int i = 0; i < 6; i++){
            if(grid[c][i].getDigit() == d)
                return false;
        }
        return true;
    }

    public boolean isNotInSquare(int d, SquareCoordinates coord){
        for(int i = 0; i < 3; i++){
            int r = coord.lat*3 + i;
            for(int j = 0; j < 2; j++){
                if(grid[coord.lng*2+j][r].getDigit() == d)
                    return false;
            }
        }
        return true;
    }

    public SquareCoordinates getSquareCoordinates(int r, int c){
        return new SquareCoordinates(r/3,c/2);
    }

    //return an array of size 6 with each entry representing whether the corresponding digit is a valid move (1-6)
    public int[] getValidMoves(int r, int c){
        int[] validMoves = new int[6];
        SquareCoordinates coord = getSquareCoordinates(r,c);
        for(int i = 0; i<6; i++){
            if(isNotInRow(i+1,r) && isNotInColumn(i+1,c) && isNotInSquare(i+1, coord))
                validMoves[i] = 1;
            else
                validMoves[i] = 0;
        }
        return validMoves;
    }

    public boolean isFinished(){
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                if(grid[i][j].getDigit() == 0)
                    return false;
            }
        }
        return true;
    }


}
