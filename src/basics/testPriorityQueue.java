package basics;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Pair {
    String val;
    Integer freq;

    Pair(String val, Integer freq){
        this.val = val;
        this.freq = freq;
    }
}
public class testPriorityQueue {


    public static void priorityQue(){

        // 1, 2, 3

        Queue<Integer> q = new LinkedList<>();

          q.add(1);
          q.add(2);
          q.add(3);



          q.peek();
          q.poll();




        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(1);
        pq.add(3);
        pq.add(2);
        pq.add(3);

       boolean vx =  pq.remove(3);
        pq.remove(3);



        PriorityQueue<Pair> p = new PriorityQueue<Pair>(
                (x,y)-> Integer.compare(x.freq, y.freq ));


        Integer.compare(1,2);






        pq.add(10);pq.add(8);pq.add(4);pq.add(3);pq.add(5);pq.add(7);pq.add(9);pq.add(2);

        Iterator<Integer> iterate = pq.iterator();

        iterate.next();

        Integer x = pq.peek();
        Integer y = pq.poll();

        pq.poll();
        pq.poll();
        pq.poll();
        pq.poll();
        pq.poll();
        pq.poll();





    }

}
