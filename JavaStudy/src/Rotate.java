public class Rotate {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        rotate(matrix);
    }
    public static void rotate(int[][] matrix) {
        if(matrix.length == 0)
        {
            return;
        }
        else
        {
            int row1 = 0, row2 = matrix.length - 1;
            int col1 = 0, col2 = matrix.length - 1;
            for(int i = 0; row1 < row2;)
            {
                int len = row2 - row1;
                int[] save = new int[len];
                for(int j = col1 + 1, num = 1; j < col2; j++, num++)
                {
                    save[num] = matrix[row1][j];
                    matrix[row1][j] = matrix[row2 - num][col1];
                }
                for(int j = row1 + 1, num = 1; j < row2; j++, num++)
                {
                    int p;
                    p = save[num];
                    save[num] = matrix[j][col2];
                    matrix[j][col2] = p;
                }
                for(int j = col2 - 1, num = 1; j > col1; j--, num++)
                {
                    int p;
                    p = save[num];
                    save[num] = matrix[row2][j];
                    matrix[row2][j] = p;
                }
                for(int j = row2 - 1, num = 1; j > row1; j--, num++)
                {
                    matrix[j][col1] = save[num];
                }
                int p = matrix[row1][col1];
                matrix[row1][col1] = matrix[row2][col1];
                matrix[row2][col1] = matrix[row2][col2];
                matrix[row2][col2] = matrix[row1][col2];
                matrix[row1][col2] = p;
                i++;
                row1 = 0 + i; row2 = matrix.length - 1 - i;
                col1 = 0 + i; col2 = matrix.length - 1 - i;
            }
        }
    }
}
