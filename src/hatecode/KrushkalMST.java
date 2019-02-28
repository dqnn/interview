package hatecode;
import java.util.ArrayList;
import java.util.PriorityQueue;


//min spanning tree is just collecting edges, from small weight to large weight, 
//if we already connect all nodes, then stop, so greedy can help to make sure all weight 
//is min
public class KrushkalMST {
    static class Edge {
        int src;
        int dst;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.src = source;
            this.dst = destination;
            this.weight = weight;
        }
    }

    static class Graph {
        int vertices;
        ArrayList<Edge> edges = new ArrayList<>();

        Graph(int vertices) {
            this.vertices = vertices;
        }

        public void addEgde(int source, int destination, int weight) {
            edges.add(new Edge(source, destination, weight)); //add to total edges
        }
        
        public void kruskalMST(){
            PriorityQueue<Edge> pq = new PriorityQueue<>(edges.size(), 
                    (a, b)->(a.weight - b.weight));

            //add all the edges to priority queue, //sort the edges on weights
            edges.stream().forEach(e->pq.offer(e));

            ArrayList<Edge> minSpanningTree = new ArrayList<>();

            DUS dus = new DUS(vertices);
            //if our size is not same to vertices, then we have to continue
            while(dus.getSize(0) != vertices){
                Edge edge = pq.poll();
                //check if adding this edge creates a cycle
                int x_set = dus.find(edge.src);
                int y_set = dus.find(edge.dst);

                if(x_set!=y_set){
                    //add it to our final result
                    minSpanningTree.add(edge);
                    dus.union(x_set, y_set);
                }
            }
            //print MST
            System.out.println("Minimum Spanning Tree: ");
            printGraph(minSpanningTree);
        }

        public void printGraph(ArrayList<Edge> edgeList){
            for (int i = 0; i <edgeList.size() ; i++) {
                Edge edge = edgeList.get(i);
                System.out.println("Edge-" + i + " source: " + edge.src +
                        " destination: " + edge.dst +
                        " weight: " + edge.weight);
            }
        }
    }
    public static void main(String[] args) {
            int vertices = 6;
            Graph graph = new Graph(vertices);
            graph.addEgde(0, 1, 4);
            graph.addEgde(0, 2, 3);
            graph.addEgde(1, 2, 1);
            graph.addEgde(1, 3, 2);
            graph.addEgde(2, 3, 4);
            graph.addEgde(3, 4, 2);
            graph.addEgde(4, 5, 4);
            graph.addEgde(2, 5, 2);
            graph.addEgde(3, 5, 3);
            graph.kruskalMST();
    }
}