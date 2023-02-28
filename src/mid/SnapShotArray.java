package mid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SnapShotArray {

        /* 
           [ 6 0 0 ] 
     
                   0           1         2            3         4
        List { [ 5, 0, 0], [6, 0, 0], [6, 0, 0], [6, 0, 0], [7, 0, 0]}
     
        m
        ----
        0 : [5, 0, 0]
        3 : [6, 0, 0]
        4 : [7, 0, 0]
     
     
           
        */
        public static void main(String[] args) {
            SnapShotArray o = new SnapShotArray(4);
            o.snap();
            o.snap();
            int get = o.get(3,1);

        }
        int [] array;
        List<int []> snapStore;
         public SnapShotArray(int length) {
             array = new int [length];
             snapStore = new ArrayList<>();
         }
         
         public void set(int index, int val) {
             array[index] = val;
         }
         
         public int snap() {
             int [] copyr = Arrays.copyOf(array, array.length);
             snapStore.add(copyr);
             return snapStore.size()-1;
         }
         
         public int get(int index, int snap_id) {
            return snapStore.get(snap_id)[index];
         }
}
