package easy;

/*
*  i
*  1   2   2   3
*  1   3   4   5   6   7
*  j
*
* 0    1   1  2  2 3  3 4 5 6 7
* c ->
* */

public class MergeTwoSortedList {
   public static void main(String[] args) {

       ListNode n = new ListNode(1);
       n.next = new ListNode(2);
       n.next.next = new ListNode(2);
       n.next.next.next = new ListNode(3);

       ListNode m = new ListNode(1);
       m.next = new ListNode(3);
       m.next.next = new ListNode(4);
       m.next.next.next = new ListNode(5);
       m.next.next.next.next = new ListNode(6);
       m.next.next.next.next.next = new ListNode(7);

       ListNode ans = mergeTwoLists(n, m);

       while(ans != null) {
           System.out.print(ans.val + ", ");
           ans = ans.next;
       }


   }

   public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
       ListNode dummy = new ListNode(0);
       ListNode current = dummy;

       while(l1 != null && l2 != null) {
           if(l1.val < l2.val) {
               current.next = new ListNode(l1.val);
               l1 = l1.next;
           }else {
               current.next = new ListNode(l2.val);
               l2 = l2.next;
           }
           current = current.next;
       }

       while(l1 != null){
           current.next = new ListNode(l1.val);
           current = current.next;
           l1 = l1.next;
       }
       while(l2 != null){
           current.next = new ListNode(l2.val);
           current = current.next;
           l2 = l2.next;
       }
   return dummy.next;
   }

}



