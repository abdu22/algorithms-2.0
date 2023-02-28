package mid;

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {


   public static void main(String [] args){

       Node h = new Node(7);


       h.next  = new Node(13);


       h.next.next = new Node(11);

       h.next.next.next = new Node(10);
       h.next.next.next.next = new Node(1);

       h.random  = null;
       h.next.random = h;
       h.next.next.random = h.next.next.next.next;
       h.next.next.next.random = h.next.next;
       h.next.next.next.next.random = h;

       Node clone = copyRandomList(h);

   }

   static Map<Node, Node> m = new HashMap<>();

    public static Node copyRandomList(Node head) {
        Node preNew = new Node(0);
        Node preOld = new Node(0);

        if(head == null) return null;

        Node current = new Node(head.val);

        preNew.next = current;
        preOld.next = head;

        head = head.next;

        m.put(head,current);

        while(head != null){
            Node temp  = new Node(head.val);

            m.put(head,temp);

            current.next = temp;
            current = current.next;
            head = head.next;
        }
        current.next = null;

        //find randome
        head = preOld.next;
        current = preNew.next;
        while(head != null) {
            current.random = m.get(head.random);
            current = current.next;
            head = head.next;
        }
        return preNew.next;
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
