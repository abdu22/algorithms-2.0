package mid;
/* 
     
[
    [3,0,1,4,2],
    [5,6,3,2,1],
    [1,2,0,1,5],
    [4,1,0,1,7],
    [1,0,3,0,5]
]
    
    inputs  
    [2,1,4,3],
    [1,1,2,2],
    [1,2,2,4]


    [1,-3]
    [-4,9]

    [1, -2]
    [-3, 3 ]
 */
public class RangeSumQuery2D {
    int [][] matrix;
    int [][] sum;

    public RangeSumQuery2D(int[][] matrix) {
        this.matrix = matrix;
        this.sum = new int [matrix.length][matrix[0].length];
        genSum();
    }

    public static void main(String[] args) {
        int [] [] input = new int [][] {
                                            {3,0,1,4,2},
                                            {5,6,3,2,1},
                                            {1,2,0,1,5},
                                            {4,1,0,1,7},
                                            {1,0,3,0,5}
                                        };

        int [] [] input2 = new int [][] { {1,-3}, {-4,9} };

        //RangeSumQuery2D obj = new RangeSumQuery2D(input);
        //System.out.println(obj.sumRegion(2, 1, 4, 3));
        
        RangeSumQuery2D obj2 = new RangeSumQuery2D(input2);
        System.out.println(obj2.sumRegion(0, 0, 1, 1));
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int rectagleOnSameRow = 0;
        int rectagleOnSameCol = 0;
        int rectagleOnDiag = 0;

        if(col1-1 >= 0 ) 
                 rectagleOnSameRow = sum[row2][col1-1];
        if(row1-1 >= 0)
                 rectagleOnSameCol = sum[row1-1][col2];
        if( col1-1 >=0 && row1-1 >= 0) 
                 rectagleOnDiag = sum[row1-1][col1-1];

         return sum[row2][col2] - rectagleOnSameRow - rectagleOnSameCol + rectagleOnDiag;
        
    }

    private void genSum(){
        

        for(int r=0; r< matrix.length ; r++){
            for(int c = 0; c < matrix[0].length; c++) {
                int sumR = 0; int sumC = 0;  int sumDiag = 0;
                
                if(r-1 >= 0)
                       sumC = sum[r-1][c];
                if(c-1 >= 0)      
                        sumR = sum[r][c-1]; 
                 if(r-1 >=0 && c-1 >=0)
                        sumDiag = sum[r-1][c-1];
                this.sum[r][c] = matrix[r][c]  + sumR + sumC - sumDiag;
            }
        }
    }
}
