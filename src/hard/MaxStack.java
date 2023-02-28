package hard;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

class StackNode {
    int val;
    StackNode next;
    StackNode prev;
    StackNode(int val){
        this.val = val;
    }
}

// Buid max stack using two data structures. 
// 1. StackNode - to store the values.
// 2. PriorityQueue - to store the max values.
// 3. Map - to store the max values and their count.
// 4. StackNode - to store the max values. 

public class MaxStack {
    public static void main(String [] args) {
        MaxStack obj = new MaxStack();
        /* obj.push(5);
        obj.push(1);
        obj.push(5);

        int x = obj.top();
        int y = obj.popMax();
        int z = obj.top();
        int a = obj.peekMax();
        int b = obj.pop();
        int c = obj.top(); */
       
        obj.push(5);
        obj.push(1);

        int y = obj.popMax();
        int z = obj.peekMax(); 



        System.out.println("Hello visual studio");
    }

    /*
Input
["MaxStack", "push", "push", "push", "top", "popMax", "top", "peekMax", "pop", "top"]
[[], [5], [1], [5], [], [], [], [], [], []]
Output
[null, null, null, null, 5, 5, 1, 5, 1, 5]

     |4
     |5
     |1 |   | 5|
     |5 |   | 5|
     |3 |.  | 3|
     |1 |
     |2 |   | 2|
     |--|.  |--|

     replace 
     with StackNode and PriorityQueue.


      StackNode
      h.                            t
      0  4,  5,  1,  5,  3,  1,  2  0|

      PQ - make sure we have max at the top. 
      1, 1, , 1, 2, 3, 4, 5, 4,

      m 
      2 [2]
      1 [1]
      5 [[5], [5]]
      3 [3]
      4 [4]

["MaxStack","push","push","popMax","peekMax"]
[[],[5],[1],[],[]]

    */

    PriorityQueue<Integer> maxPQ;
    StackNode head;
    StackNode tail;
    Map<Integer, Stack<StackNode>> m;

    public MaxStack() {
        maxPQ = new PriorityQueue<>((x,y) -> Integer.compare(y, x));
        head = new StackNode(0);
        tail = new StackNode(0);
        head.next = tail;
        tail.prev = head;

        m = new HashMap<>();
    }
    
    public void push(int x) {
        // add the node next to head. 
        StackNode temp = new StackNode(x);
        StackNode headNext = head.next;
        headNext.prev = temp;
        temp.next = headNext;
        head.next = temp;
        temp.prev = head;

        // add it to PQ; 
        maxPQ.add(x);

        // add it to the hashMap
        Stack<StackNode> s;
        if(m.containsKey(x)){
            s = m.get(x);
            s.push(temp);
        }else{
            s =  new Stack<>();
            s.push(temp);
        }
        m.put(x,  s);
    }
    
    public int pop() {
        //remove next to the head. 
        StackNode removed =  removeStackNode(head.next);
        return removed.val;
    }
    
    public int top() {
        return head.next.val;
    }
    
    public int peekMax() {
        return maxPQ.peek();
    }
    
    public int popMax() {
        int maxValue =maxPQ.peek();
        StackNode sn = m.get(maxValue).peek(); 
        removeStackNode(sn);
        return maxValue;
    }

    public StackNode removeStackNode(StackNode sn){
         // remove node. 
        StackNode prevNode = sn.prev;
        StackNode nxtNode = sn.next;
        prevNode.next = nxtNode;
        nxtNode.prev = prevNode;

        // remove it from the maxStack
        maxPQ.remove(sn.val);

      // remove from the map
      if(m.containsKey(sn.val)){
        m.get(sn.val).pop();
        if(m.get(sn.val).isEmpty()){
            m.remove(sn.val);
        }
    }
        return sn;
    }

}






