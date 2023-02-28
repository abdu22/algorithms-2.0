package mid;

import java.sql.Timestamp;
import java.util.Date;

public class TheMaze {
    
    public static void main(String[] args) {
        TheMaze o = new TheMaze();
        int [][] m = new int [][] {
            {0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {1, 1, 0, 1, 1},
            {0, 0, 0, 0, 0}
        };
        
       System.out.println(o.hasPath2(MazeTest.maze1, MazeTest.start, MazeTest.end));
       
       //System.out.println(o.hasPath2(m, new int [] {0,4}, new int[] {4,4}));
       //System.out.println(o.hasPath(m, new int [] {0,4}, new int[] {4,4}));

    } 

   public boolean hasPath(int[][] maze, int[] start, int[] destination) { 
       int [][] directions = new int [][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
       int r = start[0]; int c = start[1];

       if(r == destination[0] && c == destination[1])  return true;
       
       if(maze[r][c] == -2) return false;
       maze[r][c] = -2; 

       boolean goal = false;

       for(int i=0; i<directions.length; i++){
           int [] direction = directions[i];
            r = start[0];  c = start[1];
            // for all direction
           while(isNotBlocked(maze, r+direction[0], c + direction[1])){
               r  +=direction[0];
               c  +=direction[1];
           }
           //if moved an inch, deserver to do DFS
           if(r != start[0] || c != start[1]){
               goal =  hasPath(maze, new int [] {r, c}, destination);
               if(goal) return true;
           }
       }
       return false;
   }

   public boolean isNotBlocked(int[][] maze, int x, int y){
    return 
           x >= 0 && y >= 0 &&
           x < maze.length && 
           y < maze[0].length &&
           maze[x][y] != 1 ;
}

    public boolean hasPath2(int[][] maze, int[] start, int[] destination) {
       
        int r = start[0]; int c = start[1];

        if(r == destination[0] && c == destination[1]) 
          return true;
        
        if(maze[r][c] == -2) {
            return false;
        }   
        maze[r][c] = -2; // already used as a starting position.   

        boolean goal = false;
        // go right
        int i = c;
        while(isNotBlocked(maze, r, i+1)){
            //maze[r][i] = 1;     
            i++;
        }
        if(i!=c) { // if i moved, recurisvely check the next chance
            goal = hasPath2(maze, new int [] {r, i}, destination);
            if(goal) return true;
        }
        
        // go down
        i = r ;
        while(isNotBlocked(maze, i+1, c)){
          //  maze[i][c] = 1;     
            i++;
        }
        if(i!=r) { // if i moved, recurisvely check the next chance
            goal = hasPath2(maze, new int [] {i, c}, destination);
            if(goal) return true;
        }

        // go left
         i = c;
        while(isNotBlocked(maze, r, i-1)){
          // maze[r][i] = 1;     
            i--;
        }
        if(i!=c) { // if i moved, recurisvely check the next chance
            goal = hasPath2(maze, new int [] {r, i}, destination);
            if(goal) return true;
        }

        // go up
        i = r ;
        while(isNotBlocked(maze, i-1, c)){
           // maze[i][c] = 1;     
            i--;
        }
        if(i!=r) { // if i moved, recurisvely check the next chance
            goal = hasPath2(maze, new int [] {i, c}, destination);
            if(goal) return true;
        }
        //maze[r][c] = 0;
        return false;
    }
}
