package hard;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class CalendarIII {
    public static void main(String[] args) {
        CalendarIII o = new CalendarIII();
       int size = o.book(10, 20);
       System.out.println(size);
       size = o.book(50,60);
       System.out.println(size);
       size =o.book(10, 40);
       System.out.println(size);
       size = o.book(5, 15);
       System.out.println(size);
       size = o.book(5, 10);
       System.out.println(size);
       size = o.book(25, 55);

        System.out.println(size);
    }

    List<TreeMap<Integer, Integer>> home;

    public CalendarIII() {
        this.home = new ArrayList<>();
    }
    
    public int book(int startTime, int endTime) {
        
        int currentStart = startTime;
        while(currentStart < endTime) {
            for( int i=0; i<home.size();  i++){

                TreeMap<Integer, Integer> room = home.get(i);
                 currentStart = bookOnRoom(room, currentStart, endTime);
                if(currentStart == endTime){
                    // all good. no need new room
                    return home.size();
                }
            }
            if(currentStart == startTime){
                break;
            }
            startTime = currentStart;
    }

        TreeMap<Integer, Integer> newMap = new TreeMap<>();
         newMap.put(currentStart, endTime);
         home.add(newMap);

        return home.size();
    }

    public int bookOnRoom(TreeMap<Integer, Integer> room, int start, int end ){
        Integer prev = room.floorKey(start);
        Integer next = room.ceilingKey(start);

         if((prev == null || room.get(prev) <= start) && (next == null || next >= end)){
            // good to book all here
            room.put(start, end);
            return end;
         }else if(prev == null || room.get(prev) <= start) {
            // we got spot for some parts & return a news start == next
            room.put(start, next);
            return next;
         }
         return start;
    }



  /*   
      m
    ------ 
    10, 0
    20, 0
    


    private TreeMap<Integer, Integer> starts;
    private int res;

   public MyCalendarThree() {
        starts = new TreeMap<>();
        starts.put(0, 0);
        res = 0;
    }
    public void split(int x) {
        Integer prev = starts.floorKey(x);
        Integer next = starts.ceilingKey(x);
        if (next != null && next == x)
            return;
        starts.put(x, starts.get(prev));
    }
    public int book(int start, int end) {
        split(start);
        split(end);
        for (var interval : starts.subMap(start, true, end, false).entrySet()) {
            res = Math.max(res, interval.setValue(interval.getValue() + 1) + 1);
        }
        return res;
    } 
    
    */
}
