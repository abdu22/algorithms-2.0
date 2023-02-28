package mid;

import java.util.TreeMap;

public class Calendar {
    public static void main(String[] args) {
        
    }

    TreeMap<Integer, Integer> calender;

    Calendar() {
        this.calender = new TreeMap<>();
    }

    public boolean book(int start, int end) { 
        Integer prev = calender.floorKey(start);
        Integer next = calender.ceilingKey(start);

        if((prev == null || calender.get(prev) <= start) && (next == null || next >= end)){
            calender.put(start, end);
            return true;
        }
        return false;
    }
}
