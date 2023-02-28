package mid;

import java.util.HashSet;
import java.util.Set;

public class MinimumAreaRectangle {
    /* 
      5 |
      4 |          .   .
      3 |  .       .   .
      2 |      
      1 |  .   .   .   .
        |______________________
           1   2   3   4   5
     
      [[1,1],[1,2],[1,3],[3,1],[3,3],[3, 4],[4,1],[4,3],[4, 4]]
           
     */
    public static void main(String[] args) {
        MinimumAreaRectangle o = new MinimumAreaRectangle();

        int [][] points = new int[] [] {{1,1},{1,3},{3,1},{3,3},{2,2}};
        System.out.println(o.minAreaRect(points));
        
    }
    public int minAreaRect(int[][] points) {
        Set<String> set =new HashSet<>();

        int area = Integer.MAX_VALUE;

        for(int[] point: points){
            set.add(""+point[0]+","+point[1]);
        }

        for(int i=0; i<points.length; i++){
            for(int j = i+1 ; j < points.length; j++){
                // check if two points are not in the same column or row
                if(points[i][0] != points[j][0] && points[i][1] != points[j][1]){
                    String y = ""+points[i][0]+","+points[j][1];
                    String x = ""+points[j][0]+","+points[i][1];

                    if(set.contains(y) && set.contains(x)){
                        int teampArea = Math.abs(points[i][0] - points[j][0]) * Math.abs(points[i][1] - points[j][1]);
                        area =  Math.min(teampArea, area);
                    }
                }
            }
        }
        return area == Integer.MAX_VALUE ?  0 : area;
    }
}
