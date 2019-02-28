package codetemplates;
import java.util.ArrayList;
import java.util.PriorityQueue;

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

            //create a parent []
            int [] parent = new int[vertices];

            for (int i = 0; i <vertices; i++) {
                parent[i] = i;
            }

            ArrayList<Edge> mst = new ArrayList<>();

            //process vertices - 1 edges
            int index = 0;
            while(index<vertices-1){
                Edge edge = pq.poll();
                //check if adding this edge creates a cycle
                int x_set = find(parent, edge.src);
                int y_set = find(parent, edge.dst);

                if(x_set!=y_set){
                    //add it to our final result
                    mst.add(edge);
                    index++;
                    union(parent,x_set,y_set);
                }
            }
            //print MST
            System.out.println("Minimum Spanning Tree: ");
            printGraph(mst);
        }

        public int find(int [] parent, int vertex){
            //chain of parent pointers from x upwards through the tree
            // until an element is reached whose parent is itself
            if(parent[vertex]!=vertex)
                return find(parent, parent[vertex]);;
            return vertex;
        }

        public void union(int [] parent, int x, int y){
            int x_set_parent = find(parent, x);
            int y_set_parent = find(parent, y);
            //make x as parent of y
            parent[y_set_parent] = x_set_parent;
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