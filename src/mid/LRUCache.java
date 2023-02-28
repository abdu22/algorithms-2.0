package mid;


import java.util.HashMap;
import java.util.Map;

class DoublyLinkedList {
    int key;
    int val;
    DoublyLinkedList prev;
    DoublyLinkedList next;

    DoublyLinkedList(int key, int val){
        this.key = key;
        this.val = val;
    }
}
public class LRUCache {

    public static void main(String[] args){
        LRUCache cache = new LRUCache(2);

        cache.put(2,1);
        cache.put(2,2);
       int a =  cache.get(2);

       cache.put(1,1);
       cache.put(4,1);
       int b = cache.get(2);

/*       cache.put(4,4);
        int c = cache.get(1);
        int d = cache.get(3);
        int e = cache.get(4);*/




    }

    int capacity = 0;
    DoublyLinkedList head ;          //= null;
    DoublyLinkedList tail ;          //=null;
    Map<Integer, DoublyLinkedList> m;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.m = new HashMap<>();
    }

    public int get(int key) {
        if(!m.containsKey(key)){
            return -1;
        }
        DoublyLinkedList current = m.get(key);
        // remove it from where it is.
        if(m.size() > 1){
            removeANode(current);
            addToTail(current);
        }
        return current.val;
    }

    public void put(int key, int value) {
        if(head == null && tail == null){
            head = new DoublyLinkedList(key, value);
            tail = head;
            m.put(key, head);
        } else {
            if(m.containsKey(key)){
                DoublyLinkedList current = m.get(key);
                //remove it from where it is
                if(m.size() > 1) {
                    removeANode(current);
                    // add it to the tail.
                    addToTail(current);
                }
            }else{
                DoublyLinkedList current = new DoublyLinkedList(key, value);
                m.put(key, current);
                addToTail(current);

                if( m.size() > this.capacity){
                    // remove head;
                   int headKey = removeHead();
                    m.remove(headKey);
                }
            }
        }
    }

    public int removeHead(){
        int headKey = head.key;
        DoublyLinkedList tempHead = head.prev;
        tempHead.next = null;
        head.prev = null;
        head = tempHead;
        return headKey;
    }

    public void addToTail(DoublyLinkedList current){
        current.next = tail;
        tail.prev = current;
        tail = current;
    }
    //     t h
    //     [1]

    public void removeANode(DoublyLinkedList current){
            DoublyLinkedList prv = current.prev;
            DoublyLinkedList nxt = current.next;
            if(prv != null) prv.next = nxt;
            else tail = current.next; // if current is tail itself, move the tail forward.

            if(nxt != null) nxt.prev = prv;
            else head = current.prev; // if current is head itself, move the head back
    }
}


/*
 capacity = 2;
     put(1,1)
     put(2,2);

   [2,2] [1,1]

       get(1) -> 1

   [1,1] [2,2] [3, 3]

        put(3,3);

   [3,3] [1,1] // evict [2,2];
         get(2) -> return -1

         put(4,4)
   [4,4] [3,3] // evict [1,1]

         get(1) -> -1
         get(3) -> 3

   [3,3] [4,4]
          get(4) -> 4

   [4,4] [4,4]

 */
