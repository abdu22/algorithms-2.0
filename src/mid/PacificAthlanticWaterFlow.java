package mid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PacificAthlanticWaterFlow {
    /* 
                            ||=================|
                            ||  1, 2, 2, 3, 5  |
                            ||  3, 2, 3, 4, 4  |
                            ||  2, 4, 5, 3, 1  |
                            ||  6, 7, 1, 4, 5  |
                            ||  5, 1, 1, 2, 4  |
                            ||-----------------|
             pasific flow                      atlantic flow
           ||=================|             ||=================|
           ||  1, 1 ,1, 1, 1  |             ||  0, 0, 0, 0, 1  |
           ||  1, 1, 0, 0, 0  |             ||  0, 0, 0, 1, 1  |
           ||  1, 1, 1, 0, 0  |             ||  0, 0, 1, 1, 1  |
           ||  1, 1, 0, 0, 0  |             ||  0, 1, 1, 1, 1  |
           ||  1, 0, 0, 0, 0  |             ||  1, 1, 1, 1, 1  |
           ||-----------------|             ||-----------------|


           intersection of the above two matrix will be the answer.
     */
    public static void main(String[] args) {
        PacificAthlanticWaterFlow o = new PacificAthlanticWaterFlow();

        int [][] heights = new int [][] {
            {1,2,2,3,5},
            {3,2,3,4,4},
            {2,4,5,3,1},
            {6,7,1,4,5},
            {5,1,1,2,4}
        };

        o.pacificAtlantic(heights);
    }

    Integer [][] directions = new Integer[][]{{0,1}, {1,0}, {0,-1}, {-1, 0}};
    
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int row = heights.length;
        int column = heights[0].length;

        int [][] pacificFlow = new int [row][column];
        int [][] atlanticFlow = new int [row][column];


        for(int i=0; i<row ; i++){
            dfs(heights, pacificFlow, i, 0);
            dfs(heights, atlanticFlow, i, column-1);
        }

        for(int j=0; j<column ; j++){
            dfs(heights, pacificFlow, 0, j);
            dfs(heights, atlanticFlow, row-1, j);
        }
        List<List<Integer>> ans = new ArrayList<>();

        for(int i=0 ; i<row; i++) {
            for(int j=0; j<column; j++){
                if(pacificFlow[i][j] == 1 && atlanticFlow[i][j] == 1){
                    ans.add(Arrays.asList(i, j));
                }
            }
        }
        return ans;
    }
    
    public void dfs(int [][] heights, int [][] flow, int r, int c){
          flow[r][c] = 1; // true
          int row = heights.length;
          int col = heights[0].length;

          for(Integer[] direction: directions){
             int newR = r + direction[0];
             int newC = c + direction[1];

             if(newR < 0 || newR >= row || newC < 0 || newC >= col) 
                  continue;
             
             if(flow[newR][newC] == 1) 
                        continue;

             if(heights[newR][newC] < heights[r][c])
                      continue;
                      
              dfs(heights, flow, newR, newC);        
          } 
    }

}
