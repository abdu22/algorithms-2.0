package mid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
    /* 
 
     * Total share = 6
     * [.  
     *   u.   sc   bp   ts
     *
      
         [3   5    4.   6]
         [1   2    5    0]
         [2   1.   4    2]
         [4   1    5    7]
         [5   2    5.   6]
         [6   10   1.   1]

         // after sorting
        
         [1   2    5    0],
         [5   2    5.   6]
         [4   1    5    7]
         [2   1.   4    2]
         [3   5    4.   6]
         [6   10   1.   1]
         
     Map 
     ---------------------------------------
     int : SumListPair
     ---------------------------------------
      
   
     
      5  : { sum=5,  list <[1,0], [5,6], [4,7]> }
      4  : { sum=6,  list <[2,2], [3,6]>  }
      1  : { sum=10, list <[6,1]> }
      
     ----------------------------------------

    PQ key = [ 5, 4, 1 ]

    poll() : 5 -> sumSC = 5 -> 6-5 = 1;
    
      if(share > sumSC)
          check the next pq key 
      else if(share >= list.size()) // to check if all gets one
          all the remaining PQ are not getting it
      
      else 
         Iterate through the list count ++ till share exaustes & 
         all the remaining PQ are not getting it
     * 
     */
public class IPO {
    public static void main(String[] args){
        List<List<Integer>> bids = new ArrayList<>();

        List<Integer> each1 = new ArrayList<>();
        List<Integer> each2 = new ArrayList<>();
        List<Integer> each3 = new ArrayList<>();
        List<Integer> each4 = new ArrayList<>();
        List<Integer> each5 = new ArrayList<>();
        List<Integer> each6 = new ArrayList<>();

        each1.add(3); each1.add(5); each1.add(4); each1.add(6);
        each2.add(1); each2.add(2); each2.add(5); each2.add(0);
        each3.add(2); each3.add(1); each3.add(4); each3.add(2);
        each4.add(4); each4.add(1); each4.add(5); each4.add(7);
        each5.add(5); each5.add(2); each5.add(5); each5.add(6);
        each6.add(6); each6.add(10); each6.add(1); each6.add(1);


        bids.add(each1);
        bids.add(each2);
        bids.add(each3);
        bids.add(each4);
        bids.add(each5);
        bids.add(each6);

       List<Integer> ans = getResults(bids, 3);

       ans.stream().forEach(x -> System.out.println(x + ", "));
    }

    public static List<Integer> getResults(List<List<Integer>> bids, int totalshares){
      
        Map<Integer, SumListPair> map = new  HashMap<>();
        PriorityQueue<Integer> q = new PriorityQueue<>((x,y) -> (y-x));
        
        for(List<Integer> bid: bids){
            Integer id = bid.get(0);
            Integer share = bid.get(1);
            Integer bidVal = bid.get(2);
            Integer timestamp = bid.get(3);

            SumListPair currentPair = map.get(bidVal);
            if(map.get(bidVal) == null){
                currentPair = new SumListPair(0, new ArrayList<>());
                q.add(bidVal);
            }
            currentPair.sumShare = currentPair.sumShare + share;
            currentPair.listId.add(new IDTimeStampPair(id, timestamp));

            map.put(bidVal, currentPair);
        }

        List<Integer> ans = new ArrayList<>();

        while(q.size() > 0) {
            Integer poll = q.poll();
            List<IDTimeStampPair> listIDTimstp = map.get(poll).listId;
            int sumShares = map.get(poll).sumShare; 

            if(totalshares > sumShares){
                totalshares = totalshares - sumShares;
                continue;
            }else if( totalshares >= listIDTimstp.size()){
                // all the remaning ids are not in the bid 
                ans = convertPQtoList(q, map);
                
            }else {
                // find which id doesn't get at least one.
                List<Integer> firstPortion = getListAfterTheIdx(listIDTimstp, totalshares);
                // all the remaning ids are not in the bid 
                List<Integer> lastPortion = convertPQtoList(q, map);
                ans.addAll(firstPortion);
                ans.addAll(lastPortion);
            }

        }
        return ans;
    }

    public static List<Integer> getListAfterTheIdx(List<IDTimeStampPair> listPair, int i){
        listPair.sort((x,y) -> x.timestamp - y.timestamp);
        List<Integer> ans = new ArrayList<>();

        for(IDTimeStampPair eachPair:listPair ){
            ans.add(eachPair.id);
        }
        return ans;
    }

    public static List<Integer> convertPQtoList(PriorityQueue<Integer> pq, Map<Integer,SumListPair> map){
        List<Integer> ans = new ArrayList<>();
        while(pq.size() > 0) {
          Integer current = pq.poll();
           List<Integer> currentList =  map.get(current).listId.stream()
            .map( x -> x.id)
            .collect(Collectors.toList());

            ans.addAll(currentList);
        }
        return ans;
    }
 }

class IDTimeStampPair {
    int id;
    int timestamp;
    IDTimeStampPair(int id, int timestamp){
        this.id = id;
        this.timestamp = timestamp;
    }
}

class SumListPair {
    List<IDTimeStampPair> listId;
    int sumShare;
    SumListPair(int sumShare, List<IDTimeStampPair> listId){
        this.listId = listId;
        this.sumShare = sumShare;
    }
}
