package com.eyepax;

import java.util.Random;

class ColorChallenge
{
    static int rows = 6;
    static int columns = 5;
    static int sample[][];
    public static final int[] COLORS = {1, 2, 3, 4};
    static int color_grid[][] = new int [rows][columns];

    static int result[][] = new int [rows][columns];

    static int COUNT;

    // main method
    public static void main(String args[])
    {
        ColorChallenge colorChallenge2 = new ColorChallenge();
        colorChallenge2.initSampleGrid(7, 8);

        System.out.println("All color grid : ");
        print2D(sample);

        findLargestConnectedData(sample);
    }

    public void initSampleGrid(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        sample = new int[rows][columns];
        color_grid = new int [rows][columns];
        result = new int [rows][columns];

        Random random = new Random();
        for (int row = 0; row < sample.length; row++)
        {
            for (int col = 0; col < sample[row].length; col++)
            {
                sample[row][col] = COLORS[random.nextInt(4)]; //Whatever value you want to set them to
            }
        }

    }

    // Find the largest connected grid data
    static void findLargestConnectedData(int sampleGrid[][])
    {

        int current_max = 0;

        for (int i = 0; i < rows; i++){ // rows -- 6
//            System.out.println("i value is : "+i);
//            System.out.println();

            for (int j = 0; j < columns; j++){ // columns -- 5
                // {0,0} = 1
//                System.out.println("j value is : "+j);
//                System.out.println("reset_color_grid");
                reset_color_grid();
                COUNT = 0;

                // checking cell to the right
                // j = 0
                if (j + 1 < columns)
                    setColorGridValues(sampleGrid[i][j], sampleGrid[i][j + 1], i, j, sampleGrid); // setColorGridValues(1, 1, 0, 0, sampleGrid )

                // updating result
                if (COUNT >= current_max){
                    current_max = COUNT;
                    reset_result(sampleGrid[i][j], sampleGrid);
                }
                reset_color_grid();
                COUNT = 0;

                // checking cell downwards
                if (i + 1 < rows)
                    setColorGridValues(sampleGrid[i][j], sampleGrid[i + 1][j], i, j, sampleGrid);

                // updating result
                if (COUNT >= current_max){
                    current_max = COUNT;
                    reset_result(sampleGrid[i][j], sampleGrid);
                }
            }
        }
        print_result(current_max);
    }

    static void setColorGridValues(int x, int y, int i, int j, int sampleGrid[][]){
        // setColorGridValues(x:1, y:1, i: 1, j: 0, sampleGrid)

        // terminating case for setColorGridValues
        if (x != y)
            return;

//        color_grid[i][j] = 1;
        color_grid[i][j] = sampleGrid[i][j];
//        System.out.println("color_grid is : ");
//        print2D(color_grid);
//        System.out.println();
        COUNT++; // 2
//        System.out.println("COUNT IS : "+COUNT);


        int x_move[] = { 0, 0, 1, -1 };
        int y_move[] = { 1, -1, 0, 0 };


        for (int u = 0; u < 4; u++){
//            System.out.println("i + ymove,j + xmove, x "+(i + y_move[u])+" "+ (j + x_move[u])+" " +x+" " );
            if ((is_valid(i + y_move[u],j + x_move[u], x, sampleGrid))){
                setColorGridValues(x, y, i + y_move[u],j + x_move[u], sampleGrid); //  setColorGridValues(x:1, y:1, i + y_move[u] : 1, j + x_move[u] : 0)
            } // is_valid(0, 1, 1, sampleGrid){
        }


    }

    static boolean is_valid(int x, int y, int key, int sampleGrid[][]){
        // is_valid(x:0, y:1, key:1, sampleGrid)
        if (x < rows && y < columns && x >= 0 && y >= 0) {
            if (color_grid[x][y] == 0 && sampleGrid[x][y] == key)
                return true;
            else
                return false;
        }
        else
            return false;
    }

    static void reset_result(int key, int sampleGrid[][])
    {
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                if (color_grid[i][j] == sampleGrid[i][j] && sampleGrid[i][j] == key)
                    result[i][j] = color_grid[i][j];
                else
                    result[i][j] = 0;
            }
        }
    }

    static void reset_color_grid(){
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                color_grid[i][j] = 0;
            /*
            {
                {0,	0,	0,	0,	0},
                {0,	0,	0,	0,	0},
                {0,	0,	0,	0,	0},
                {0,	0,	0,	0,	0},
                {0,	0,	0,	0,	0},
                {0,	0,	0,	0,	0}}
             */
    }

    // function to print the result
    static void print_result(int res)
    {
        System.out.println ("The largest connected nodes of the grid is :" + res );

        // prints the largest component
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                if (result[i][j] != 0)
                    System.out.print(result[i][j] + " ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
    }

    public static void print2D(int matrix[][]){
        for (int i = 0; i < matrix.length; i++) { //this equals to the row in our matrix.
            for (int j = 0; j < matrix[i].length; j++) //this equals to the column in each row.
                System.out.print(matrix[i][j] + " ");
            System.out.println(); //change line on console as row comes to end in the matrix.
        }
    }

}