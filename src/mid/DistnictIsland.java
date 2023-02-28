package mid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DistnictIsland {
     public static void main(String[] args) {
      
        DistnictIsland o = new DistnictIsland();
         int [] [] grid = new int [][] 
         {{1,1,0,0,0},
          {1,1,0,0,0},
          {0,0,0,1,1},
          {0,0,0,1,1},
         };
        System.out.println(o.numDistinctIslands(grid)); 
     }
        /*   m 
           ----
           4 : [ {(0,0), 2},  ] 
    
           m
           -----
            3 : [ (0,0), (2,4)]
            2 : [ (0,3) ]
         */
        int countOnes; 
        int answer;
        Map<Integer, List<Integer []>> map;
         int [][] m ;
    
        public boolean isWater(int [][] grid, int i, int j){
            if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0) 
                return true;
             else 
               return false;   
        } 
    
        int [][] directions = new int[][] {{0,1}, {1,0}, {0, -1}, {-1,0}};

        public int numDistinctIslands(int[][] grid) {
           m = new int [grid.length][grid[0].length];
           map = new HashMap<>();
           answer = 0;
    
            for(int i=0; i<grid.length; i++)
                for(int j=0; j<grid[0].length; j++) 
                            m[i][j] = grid[i][j];
      
            for(int i=0; i<grid.length; i++){
                for(int j=0; j<grid[0].length; j++){
                   if(grid[i][j] == 0) 
                     continue; 
                      
                    countOnes = 0;
                    dfs(grid, i, j);
                    if(!map.containsKey(countOnes))
                         map.put(countOnes, new ArrayList<>());
    
                    addToMap(i,j, countOnes);
                }
            }
         return answer;
        }
        public void addToMap(int i, int j, int countOnes){
            List<Integer []> list = map.get(countOnes);
            int [][] temp = new int [m.length][m[0].length];
            
    
            for(Integer [] each:list){
                        for(int r=0; r<m.length; r++)
                            for(int c=0; c<m[0].length; c++) 
                                    temp[r][c] = m[r][c];
    
               boolean isSame = compairDFS(i, j, each[0], each[1], temp);
               if(isSame) return;
            }
            list.add(new Integer [] {i, j});
            answer++;
        }
    
        public boolean compairDFS(int i, int j, int x, int y, int[][] temp){
            if( (isWater(temp, i, j) != isWater(temp, x, y))) return false;
            if(isWater(temp, i, j) && isWater(temp, x, y)) return true;
            temp[i][j] = 0;
            temp[x][y] = 0;
    
             for(int []direction:directions){ 
                 int a = direction[0];
                 int b = direction[1];
                boolean isFine = compairDFS(i+a, j+b, x+a, y+b, temp);
                if(!isFine) return false;
             }
    
             return true;
        }
      
    
        public void dfs(int[][] grid, int i, int j){
            if(isWater(grid, i, j)) return;
    
             grid[i][j] = 0;
             countOnes++;
             for(int []direction:directions){
                 dfs(grid, i+direction[0], j+direction[1]);
             }
        }
}
