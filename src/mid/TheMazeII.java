package mid;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
class Data {
    int r;
    int c;
    int distance;
    String path;
    Data(int r, int c, int d, String path){
        this.r = r;
        this.c = c;
        this.distance = d;
        this.path = path;
    }
}
public class TheMazeII {
    /* 
     */
    public static void main(String[] args) {
        TheMazeII o = new TheMazeII();
        int [][] m = new int [][] {
            {0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {1, 1, 0, 1, 1},
            {0, 0, 0, 0, 0},
        };

        System.out.println(o.shortestDistance(MazeTest.maze5, MazeTest.start5, MazeTest.end5));

        //System.out.println(o.shortestDistance(m,  new int [] {0,4}, new int[] {3,2}));
        //System.out.println(o.shortestDistance(m, new int [] {0,4}, new int[] {4,4}));
        //System.out.println(o.shortestDistance(MazeTest.maze3, new int [] {0,0}, new int[] {8,6}));
        //System.out.println(o.shortestDistance(MazeTest.maze1, MazeTest.start, MazeTest.end));
        //System.out.println(o.shortestDistance(MazeTest.maze4, MazeTest.start4, MazeTest.end4));
    }

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int[][] memory = new int[maze.length][maze[0].length];
        Arrays.stream(memory).forEach(row -> Arrays.fill(row, -1));
        int [][] directions = new int [] [] {{0,1}, {1,0}, {0,-1}, {-1,0}};
        String [] pathsDirection = new String [] {"r", "d", "l", "u"}; 
        int shortestDistance = Integer.MAX_VALUE;

        Queue<Data> q = new LinkedList<>();
                         //  r , c , distance, path
        q.add( new Data(start[0], start[1], 0, ""));
        while(!q.isEmpty()){
            int size = q.size();
            while(size > 0) {
                size--;
                Data current = q.poll();
                if(current.r == destination[0] && current.c == destination[1]){
                    System.out.println( current.distance + " : " + current.path);
                    shortestDistance = Math.min(shortestDistance, current.distance);
                }
               // maze[r][c] = -2;

                // for all 4 directions
                for(int i=0; i<directions.length; i++){
                    int r = current.r; int c = current.c; 
                    int distance = current.distance; 
                    String path = current.path;
                    int [] direction = directions[i];
                    int countLen = 0;
                    while(isNotBlocked(maze, r+direction[0], c+direction[1])){
                        r += direction[0];
                        c += direction[1];
                        countLen++;
                    }
                    if(r != current.r|| c != current.c){
                        if( (memory[r][c] == -1) || memory[r][c] > distance + countLen ){
                            memory[r][c] = distance + countLen;
                          q.offer(new Data(r, c, distance + countLen, path + pathsDirection[i]));
                          //q.offer(new int[] {r, c, distance + countLen});
                        } 
                    }
                }
            }
        }
        return shortestDistance == Integer.MAX_VALUE ? -1 : shortestDistance; 
    }

    public boolean isNotBlocked(int[][] maze, int x, int y){
         return 
                x >= 0 && y >= 0 &&
                x < maze.length && 
                y < maze[0].length &&
                maze[x][y] != 1 ;
    }

}
