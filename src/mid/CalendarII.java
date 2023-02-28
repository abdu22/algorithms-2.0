package mid;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class CalendarII {
    public static void main(String[] args) {
        CalendarII o =  new CalendarII();
     boolean ans = false;
     ans = o.book(10, 20);
     ans =   o.book(50, 60);
     ans =   o.book(10, 40);
     ans =   o.book(5, 15);
     ans =   o.book(30,40);
     ans = o.book(45, 55);

     int hold;
    }


        TreeMap<Integer, Integer> calender1 ;
        TreeMap<Integer, Integer> calender2 ;

        List<Integer []>  temp1;
        List<Integer []>  temp2;
    
    
        public CalendarII() {
            this.calender1 = new TreeMap<>();
            this.calender2 = new TreeMap<>();
           
        }
        
        public boolean book(int start, int end) {
            this.temp1 = new ArrayList<>();
            this.temp2 = new ArrayList<>(); 
            int newStart = start;
            while(newStart < end){
               int startCal1 = help(newStart, end, calender1, temp1);
                  
                 if(startCal1 == end) {
                    newStart =  startCal1;
                    break;
                 }else if(startCal1 != -1) 
                     newStart = startCal1;

               int startCal2 =  help(newStart, end, calender2, temp2); 

                 if(startCal2 == end) {
                    newStart =  startCal2;
                    break;
                 }else if (startCal2 != -1) 
                      newStart = startCal2;


               if(startCal1 == -1 && startCal2 == -1)
                 return false; 

            }
         if(newStart != end) {
            return false;
         }
          for(Integer[] cal1:temp1){
                calender1.put(cal1[0], cal1[1]);
          }
          for(Integer[] cal2:temp2){
            calender2.put(cal2[0], cal2[1]);
          }
          
          return true;
        }
    
        public int help(int start, int end, TreeMap<Integer, Integer> calender, List<Integer []>  temp1) {
            Integer prev = calender.floorKey(start);
            Integer next = calender.ceilingKey(start);
    
            if (
                (prev == null || calender.get(prev) <= start) &&
                (next == null || next >= end)
              ){
                  //all good no new interval left
                  temp1.add(new Integer[]{start, end});
                  // new start is out of bordor.
                  return end;
              }
              if(prev == null || calender.get(prev) <= start) {
                // at least update the start to newStart, chunk of it putted in this tree. 
                temp1.add(new Integer[] {start, next});
                return next;
              }
              return -1;
        }
}
