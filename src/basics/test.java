package basics;

import java.util.*;
import java.util.stream.Collectors;

public class test {
    public static boolean test (int[] sequence) {
        
        boolean oneFound = false;
        for(int i=0; i< sequence.length-1; i++){
                 
             if(sequence[i] > sequence[i+1]){
                 if(oneFound == true) {
                    return false;  
                  }
                 
                 oneFound = true;
             }
        }
        return true;
    }


    public static void main(String[] args) {


        String word = "axxbxxbxxd"; // words [] : axx bxx bxxd

        System.out.println(word);
        int concurency = 3;

        String [] words = new String[concurency];
        int len  = word.length() / 3; 

        int i=0; int j=0;
        while(i + len < word.length()){
            words[j] = word.substring(i , i + len );
            i += len;
            j++;
        }

        if(i < word.length()){
            words[j-1] = words[j-1] + word.substring(i, word.length());
        }

        for(String w: words){
            System.out.println(w);
        }


        test(new int [] {1,3,2,1});

        List<Integer> vl = new ArrayList<>();
        vl.add(0);
        vl.add(1);

        vl.add(1, 9);


        /* 
            6 1 3 2 9 
            6  1, 2, 3,  9
        */
        Deque<Integer> dq = new LinkedList<>();

        //dq.add(1);
        dq.add(2);
        dq.add(3);

        dq.addFirst(9);
        dq.add(1);
        dq.addLast(6);
        
         System.out.println(dq.peek()); // 9
         System.out.println(dq.peekFirst()); //9
         System.out.println(dq.peekLast()); //6
        int a = dq.poll(); // 9
        int b = dq.pollFirst(); // 2    
        int c = dq.pollLast();  // 6




        


        List<String> l1 =  new ArrayList<>( Arrays.asList("9", "1", "8")); //Arrays.asList("0", "1", "8");
        l1.add("00");

        Map<int[], List<Integer []>> m1 = new HashMap<>();

        int [] k = new int[1];
        k[0] = 1;

        


        Integer [] v = new Integer[3];

        

        

        List<Integer []> lx = new ArrayList<>();
        for(Integer [] x:lx){
            
        }
        lx.add(v);

        m1.put(k,lx);
    


        

        // streamAndSorting();
        // map();
        // stack();
        set();
        //TwoDArray();
        //priorityQue();
    }

    static class Pair {
        String val;
        Integer freq;

        Pair(String val, Integer freq){
            this.val = val;
            this.freq = freq;
        }
    }

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

    public static void streamAndSorting(){
        Integer [] i = new Integer[2];

        Pair[] pi = new Pair[2];

        int[] nums =  {-1,0,1,2,-1,-4};
        Arrays.sort(nums);

        List<Integer> l = new LinkedList<>();
        List<Integer> l2  = new ArrayList<>();
        l2.add(0);
        l2.add(1);

        l2.add(0,99);




        l2.sort(Comparator. naturalOrder());

        String concatArray = Arrays.stream(nums)
                .mapToObj(n -> ((Integer)n).toString())
                .collect(Collectors.joining("|"));
        // .reduce("", (a,b) -> (a + "|" + b));

        String concatArrayList = l2.stream()
                .map(n -> String.valueOf(n))
                .reduce("", (a,b) ->  a + "|" + b);
    }

    public static void map(){
        Map<String, Integer> m = new HashMap<>();
        m.put("a", 1);
        m.put("d", 4);

       List<Pair> p =  m.keySet()
                        .stream()
                        .map(key -> new Pair(key, m.get(key)))
                        .collect(Collectors.toList());

    }

    public static void stack(){
        Stack<Integer> stack = new Stack<>();
    }

    public static void set(){
        Set<Integer> set = new LinkedHashSet<>(Arrays.asList(4,9,1,7,2,1));

        

        for(Integer s:set){
            System.out.println(s);
        }

    }

    public static void TwoDArray(){
        int[] [] twoDime =  new int [3] [4];

       Arrays.stream(twoDime)
             .forEach(
                      row -> Arrays.fill(row, -1));


        int row = twoDime.length;
        int col = twoDime[0].length;

        boolean[] [] twoDBoolean =  new boolean[3] [4];
    }
}
