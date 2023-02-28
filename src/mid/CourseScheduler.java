package mid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class GNode {
    public Integer inDegrees = 0;
    public List<Integer> outNodes = new LinkedList<Integer>();
  }

public class CourseScheduler {
    public static void main(String[] args) { 
        CourseScheduler o = new CourseScheduler();

           int [][] prerequi = new int[][]   
   {{1,0},{2,0},{2,1},{3,1},{3,2},{4,2},{4,3},{5,3},{5,4},{6,4},{6,5},{7,5},{7,6},{8,6},{8,7},{9,7},{9,8},{10,8},{10,9},{11,9},{11,10},{12,10},{12,11},{13,11},{13,12},{14,12},{14,13},{15,13},{15,14},{16,14},{16,15},{17,15},{17,16},{18,16},{18,17},{19,17},{19,18},{20,18},{20,19},{21,19},{21,20},{22,20},{22,21},{23,21},{23,22},{24,22},{24,23},{25,23},{25,24},{26,24},{26,25},{27,25},{27,26},{28,26},{28,27},{29,27},{29,28},{30,28},{30,29},
   {31,29},{31,30},{32,30},{32,31},{33,31},{33,32},{34,32},{34,33},{35,33},{35,34},{36,34},{36,35},{37,35},{37,36},{38,36},{38,37},{39,37},{39,38},{40,38},{40,39},{41,39},{41,40},{42,40},{42,41},{43,41},{43,42},{44,42},{44,43},{45,43},{45,44},{46,44},{46,45},{47,45},{47,46},{48,46},{48,47},{49,47},{49,48},{50,48},{50,49},{51,49},{51,50},{52,50},{52,51},{53,51},{53,52},{54,52},{54,53},{55,53},{55,54},{56,54},{56,55},{57,55},{57,56},{58,56},
   {58,57},{59,57},{59,58},{60,58},{60,59},{61,59},{61,60},{62,60},{62,61},{63,61},{63,62},{64,62},{64,63},{65,63},{65,64},{66,64},{66,65},{67,65},{67,66},{68,66},{68,67},{69,67},{69,68},{70,68},{70,69},{71,69},{71,70},{72,70},{72,71},{73,71},{73,72},{74,72},{74,73},{75,73},{75,74},{76,74},{76,75},{77,75},{77,76},{78,76},{78,77},{79,77},{79,78},{80,78},{80,79},{81,79},{81,80},{82,80},{82,81},{83,81},{83,82},{84,82},{84,83},{85,83},{85,84},
   {86,84},{86,85},{87,85},{87,86},{88,86},{88,87},{89,87},{89,88},{90,88},{90,89},{91,89},{91,90},{92,90},{92,91},{93,91},{93,92},{94,92},{94,93},{95,93},{95,94},{96,94},{96,95},{97,95},{97,96},{98,96},{98,97},{99,97}};
         
   System.out.println(o.canFinish(56, prerequi));
    }
     /* m
       0; [1]

        0 -> 1 -> 2
            >------->[3]
          /
         |
        [1] -------> [0]
           \<---------|
        

    check if cycle exist
      */

             // using topological sort as shown. 
             public boolean canFinish(int numCourses, int[][] prerequisites) {

                if (prerequisites.length == 0)
                  return true; // no cycle could be formed in empty graph.
            
                // course -> list of next courses
                HashMap<Integer, GNode> graph = new HashMap<>();
            
                // build the graph first
                for (int[] relation : prerequisites) {
                  // relation[1] -> relation[0]
                  GNode prevCourse = this.getCreateGNode(graph, relation[1]);
                  GNode nextCourse = this.getCreateGNode(graph, relation[0]);
            
                  prevCourse.outNodes.add(relation[0]);
                  nextCourse.inDegrees += 1;
                }
            
                // We start from courses that have no prerequisites.
                int totalDeps = prerequisites.length;
                LinkedList<Integer> nodepCourses = new LinkedList<Integer>();
                for (Map.Entry<Integer, GNode> entry : graph.entrySet()) {
                  GNode node = entry.getValue();
                  if (node.inDegrees == 0)
                    nodepCourses.add(entry.getKey());
                }
            
                int removedEdges = 0;
                while (nodepCourses.size() > 0) {
                  Integer course = nodepCourses.pop();
            
                  for (Integer nextCourse : graph.get(course).outNodes) {
                    GNode childNode = graph.get(nextCourse);
                    childNode.inDegrees -= 1;
                    removedEdges += 1;
                    if (childNode.inDegrees == 0)
                      nodepCourses.add(nextCourse);
                  }
                }
            
                if (removedEdges != totalDeps)
                  // if there are still some edges left, then there exist some cycles
                  // Due to the dead-lock (dependencies), we cannot remove the cyclic edges
                  return false;
                else
                  return true;
              }
            
              /**
               * Retrieve the existing <key, value> from graph, otherwise create a new one.
               */
              protected GNode getCreateGNode(HashMap<Integer, GNode> graph, Integer course) {
                GNode node = null;
                if (graph.containsKey(course)) {
                  node = graph.get(course);
                } else {
                  node = new GNode();
                  graph.put(course, node);
                }
                return node;
              }

      /*
        fo dfs & check if there is a cycle exist or not
        this expensive for the above example, use toplogical sort as shown above 
       */        
      Set<Integer> set;
      Set<Integer> memory;
  
      public boolean canFinish2(int numCourses, int[][] prerequisites) {
          Map<Integer, List<Integer>> g = new HashMap<>();
          set = new HashSet<>();
           memory = new HashSet<>();
  
          // build graph
           for(int i=0; i<prerequisites.length; i++){
               int [] each = prerequisites[i];
  
               if(!g.containsKey(each[1]))
                    g.put(each[1], new ArrayList<>());
  
                g.get(each[1]).add(each[0]);    
           }
           // check for cycle 
           for(Integer key: g.keySet()){
               if(!memory.contains(key)){
                  boolean isCycle = isNotCycledDfs(g, key);
                  if(!isCycle) return false;
               }
           }
  
          return true; 
      }
  
      public boolean isNotCycledDfs(Map<Integer, List<Integer>> g, int n){
         set.add(n);
         memory.add(n);
         List<Integer> edges = g.get(n);
          if(edges != null){
               for(Integer edge:edges) {
                   if(set.contains(edge)){
                       return false;
                   }else{
                     boolean currentResult = isNotCycledDfs(g, edge);
                     if(!currentResult) return false;
                   }
               }
          }
          set.remove(n);
          return true;
      }
   
}
