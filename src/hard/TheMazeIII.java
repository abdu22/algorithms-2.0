package hard;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import mid.MazeTest;

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

public class TheMazeIII {
    public static void main(String[] args) {
        TheMazeIII o = new TheMazeIII();
        int [] ball =  new int [] {4, 3};
        int [] holl =  new int [] {0,1} ;
        int [] [] maze = new int[] [] {
            {0,0,0,0,1},
            {1,1,0,0,1},
            {0,0,0,0,0},
            {0,1,0,0,1},
            {0,1,0,0,1}
        };
         //ldldrdr
        int [][] maze2 = new int [][] {
            {0,0,1,0,0},
            {0,0,0,0,0},
            {0,0,0,1,0},
            {1,1,0,1,1},
            {0,0,0,0,0}
            };

        //System.out.println(o.findShortestWay(maze, ball, holl));
        // drdrdrdldl
        System.out.println(o.findShortestWay(MazeTest.maze6, new int[] {2,4}, new int[] {7,6}));
    }
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int[][] memory = new int[maze.length][maze[0].length];
        Arrays.stream(memory).forEach(row -> Arrays.fill(row, -1));
        memory[ball[0]][ball[1]] = 0;
                                   
        String [] pathsDirection = new String [] {"d", "l", "r", "u"}; 
        int [][] directions = new int [] [] {{1,0}, {0,-1}, {0,1}, {-1,0}};
        int shortestDistance = Integer.MAX_VALUE;
        String finalPath = null;

        Queue<Data> q = new LinkedList<>();
                         //  r , c , distance, path
        q.add( new Data(ball[0], ball[1], 0, ""));
        while(!q.isEmpty()){
            int size = q.size();
            while(size > 0) {
                size--;
                Data current = q.poll();

                System.out.println("q poll : " + current.path + ", " + current.distance);

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
                        if(r == hole[0] && c == hole[1]){
                            String holePath = current.path + pathsDirection[i];
                            int holeDistance = current.distance + countLen;
                            if(shortestDistance > holeDistance) {
                                shortestDistance = holeDistance;
                                finalPath = holePath;
                            }else if(shortestDistance == current.distance + countLen && finalPath.compareTo(holePath) > 1){
                                finalPath = holePath;
                            }
                            System.out.println(finalPath + " : " + shortestDistance + " : " + (current.distance + countLen));
                        }
                       
                    }
                    if(r != current.r|| c != current.c){
                        int tempDistance = distance + countLen;
                        /* 
                         *  I am only storing distance on memory but I should have also store 'so far path', 
                         *  in case two path hase same distance, we need the path with lexigriphically minimum
                         */
                        if( (memory[r][c] == -1) || memory[r][c] > tempDistance ){
                            memory[r][c] = distance + countLen;
                          q.offer(new Data(r, c, distance + countLen, path + pathsDirection[i]));
                        } 
                    }
                }
            }
        }
        return shortestDistance == Integer.MAX_VALUE ? "impossible" : finalPath; 
    }
    public boolean isNotBlocked(int[][] maze, int x, int y){
        return 
               x >= 0 && y >= 0 &&
               x < maze.length && 
               y < maze[0].length &&
               maze[x][y] != 1 ;
   }
}
