package hard;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import easy.ListNode;

public class MergeKsortedList {
    /* 

        Input: lists = [[1,4,5],[1,3,4],[2,6]]
        Output: [1,1,2,3,4,4,5,6]
        Explanation: The linked-lists are:
        [
        1->4->5,
        1->3->4,
        2->6
        ]
        merging them into one sorted list:
        1->1->2->3->4->4->5->6

      PQ : {[1]x [1]x [2]x [3]x [4]x [4]x [5]x [6]}

      head
                                       current
      [0] -> [1] -> [1] -> [2] -> [3] -> [4] -> [4]


     */
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        n1.next = new ListNode(4);
        n1.next.next = new ListNode(5);

        ListNode n2 = new ListNode(1);
        n2.next = new ListNode(3);
        n2.next.next = new ListNode(4);

        ListNode n3 = new ListNode(2);
        n3.next = new ListNode(6);

        ListNode [] l = new ListNode [] {n1, n2, n3, null};

        MergeKsortedList obj = new MergeKsortedList();

        ListNode ans = obj.mergeKLists(l);

         while (ans != null) {
            System.out.print(ans.val + " -> ");
            ans = ans.next;
         }
        System.out.println();
    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode(0);
        ListNode current = head;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((x,y) -> x.val - y.val );

        if(lists.length == 0) return head.next;
        
        for(ListNode node:lists){
            if(node != null) {
                pq.add(node);
            }
        }

        while(!pq.isEmpty()){

            ListNode min = pq.poll();
            current.next = min;
            if(min.next !=null){
                pq.add(min.next);
            }
            current = current.next;

        }


        return head.next;
    }
}
