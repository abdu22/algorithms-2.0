package mid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Tripple {
    int x;
    int y;
    Double d;
    Tripple(int x, int y, Double d){
        this.x = x;
        this.y = y;
        this.d = d;
    }
}
public class KthClosetPointToTheOrigin {
    public static void main(String[] args) {
        KthClosetPointToTheOrigin o = new KthClosetPointToTheOrigin();

        int [][] coordinates = new int[][] {{3,3},{5,-1},{-2,4}};

        System.out.println(o.kClosest(coordinates, 2));

    }
    public int[][] kClosest(int[][] points, int k) {
        List<Tripple> list = new ArrayList<>();
        for(int i=0; i<points.length; i++){
            list.add(new Tripple(points[i][0],points[i][1], getDistanceFromOrigin(points[i][0], points[i][1])));
        }
        Collections.sort(list, (a, b) -> a.d.compareTo(b.d));;
         int [][] ans = new int [k][2];

         for(int i=0; i<ans.length; i++){
            ans[i][0] = list.get(i).x;
            ans[i][1] = list.get(i).y;
         }
        return ans;
    }

    public Double getDistanceFromOrigin(int x, int y){
        return Math.sqrt((Math.pow(x, 2) + Math.pow(y, 2)));
    }
}
