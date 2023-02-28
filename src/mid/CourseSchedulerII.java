package mid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseSchedulerII {
    public static void main(String[] args) {
     CourseSchedulerII o  = new CourseSchedulerII();
     int [][] prerequi = new int[][]  {{1,0},{2,0}, {2, 3}, {4,1}, {4, 2}};
     //int [] ans = o.findOrder(6, prerequi);

     int [] ans = o.findOrder(1, new int[][] {});

      for(int i=0; i<ans.length; i++){
        System.out.print(ans[i] + ", ");
      }
    }

   

    public int[] findOrder(int numCourses, int[][] prerequisites) {
       
        // build graph
        Map<Integer, NodeG> g = new HashMap<>();
        for(int i=0; i<prerequisites.length; i++){
          int parent = prerequisites[i][1];
          int child = prerequisites[i][0];
          NodeG parentCour = getOrCeateNode(g, parent);
          parentCour.edges.add(child);
          NodeG currCou = getOrCeateNode(g, child);
          currCou.parentCount++;
        }
        
        // for N number of course, if the cource is not int the prerequisite, we still need to add it 
        // to the graph.
        for(int i=0; i<numCourses ; i++){
          if(!g.containsKey(i)){
            getOrCeateNode(g, i);
          }
        }
       
        // get free nodes without parent ==  first courses to take
        List<NodeG> freeParentNodes = new LinkedList<>(); // higher orders 

        for(Integer key: g.keySet()){
          if(g.get(key).parentCount == 0){
            freeParentNodes.add(g.get(key));
          }
        }

        // cut parents to child & traverese through , collect possible new parent
         int i=0; 
          while(i < freeParentNodes.size()){
            NodeG currentParent = freeParentNodes.get(i);
             List<Integer> edges = currentParent.edges;
             for(Integer edge:edges){
              NodeG currentChild = g.get(edge);
                currentChild.parentCount--;
                if(currentChild.parentCount == 0){
                  freeParentNodes.add(currentChild);
                }
             }
             i++;
          }

         if(freeParentNodes.size() < numCourses) return new int[] {}; // CYCLE exsted
         
         int[] ans = new int [freeParentNodes.size()];
         for(int j=0; j<ans.length; j++){
          ans[j] = freeParentNodes.get(j).key;
         }

        return ans;
    }


    public NodeG getOrCeateNode(Map<Integer, NodeG> g, Integer c){
       if(!g.containsKey(c)){
          g.put(c, new NodeG(c));
       }
       return g.get(c);
    }
}

class NodeG{
  Integer key;
  int parentCount;
  List<Integer> edges;

  NodeG (Integer key){
    this.key = key;
    this.parentCount = 0;
    this.edges = new LinkedList<>();
  }
}


