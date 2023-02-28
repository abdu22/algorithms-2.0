package basics;
import java.util.PriorityQueue;

public class PriorityQ {
    public static void main(String [] args){
        PriorityQueue<Pair> p =  new PriorityQueue<Pair>(4, (x, y)->{
            if(x.freq < y.freq){
                return 1;
            }else if(x.freq > y.freq){
                return -1;
            }else {
                return x.val.compareTo(y.val);
            }
        });



  p.add(new Pair("aa", 3));
  p.add(new Pair("a", 3));
  p.add(new Pair("cc", 5));
  p.add(new Pair("ddd", 1));

        Pair x = p.peek();

        p.remove(x);

  System.out.println(p.poll().val);
  System.out.println(p.poll().val);
  System.out.println(p.poll().val);
  System.out.println(p.poll().val);


    }
}
class Pair {
    String val;
    Integer freq;
    Pair(String val, Integer freq){
        this.val = val;
        this.freq = freq;
    }
}