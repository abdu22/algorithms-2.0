package mid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class InsertDeleteGetRandom {
    /*   ["RandomizedSet","insert","remove","insert","remove","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom"]
          [[],             [0],      [0],     [-1],    [0],      [],          [],         [],          [],       [],          [],        [],            [],        [],         []    ]
          [null,           true,    true,    true,    false,     -1,          -1,         -1,          -1,       -1,         -1,         -1,            -1,        -1,        -1]
     */
    public static void main(String[] args) {
        InsertDeleteGetRandom o = new InsertDeleteGetRandom();
        boolean isIt = o.insert(0);
        isIt = o.remove(0);
        isIt = o.insert(-1);
        isIt = o.remove(0);
        Integer rand = o.getRandom();
        rand = o.getRandom();
        rand = o.getRandom();
        rand = o.getRandom();
        rand = o.getRandom();
        rand = o.getRandom();
        rand = o.getRandom();
        rand = o.getRandom();


    }
    Stack<Integer> stackRemoved;
    Set<Integer> removedSet;
    Map<Integer, Integer> mapExisted;
    List<Integer> list;
    public InsertDeleteGetRandom() {
        stackRemoved = new Stack<>();
        removedSet = new HashSet<>();
        mapExisted = new HashMap<>();
        list = new ArrayList<>();
    }
    
    public boolean insert(int val) {
      if(mapExisted.containsKey(val)) return false;

      if(!stackRemoved.isEmpty()){
          Integer idx = stackRemoved.pop();
          list.set(idx, val);
          mapExisted.put(val, idx);
          removedSet.remove(idx);
      }else{
          list.add(val);
          mapExisted.put(val, list.size()-1);
      }
     return true;
    }
    
    public boolean remove(int val) {
        if(!mapExisted.containsKey(val)) return false;
        Integer indx = mapExisted.get(val);
        stackRemoved.push(indx);
        removedSet.add(indx);
        mapExisted.remove(val);
        return true;
    }
    
    public int getRandom() {
        while(true){
            Integer rand = (int) Math.random() * list.size();
            if(!removedSet.contains(rand)){
                return list.get(rand);
            }
        }
    }
}
