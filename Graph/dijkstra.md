# Dijkstra
[Graph](graph.md)

Given a weighted, undirected and connected graph of V vertices and an adjacency list adj where adj[i] is a list of lists containing two integers where the first integer of each list j denotes there is edge between i and j , second integers corresponds to the weight of that  edge . You are given the source vertex S and You to Find the shortest distance of all the vertex's from the source vertex S. You have to return a list of integers denoting shortest distance between each node and Source vertex S.
 

Note: The Graph doesn't contain any negative weight cycle.
[Problem](https://www.geeksforgeeks.org/problems/implementing-dijkstra-set-1-adjacency-matrix/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=bottom_sticky_on_article)
## Pair class
``` java
static class Pair implements Comparable<Pair> {
    int node;
    int dist;
    
    public Pair(int node, int dist) {
        this.node = node;
        this.dist = dist;
    }
    
    @Override
    public int compareTo(Pair other) {
        return this.dist - other.dist;
    }
}
```
## GeekforGeeks input format
* Implemented using `ArrayList<ArrayList<ArrayList<Integer>>> adj`.
``` java
static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S)
{
    boolean[] visited = new boolean[V];
    int[] dist = new int[V];
    PriorityQueue<Pair> pq = new PriorityQueue<>();
    
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[S] = 0;
    pq.add(new Pair(S, 0));
    
    while(!pq.isEmpty()) {
        Pair cur = pq.remove();
        
        if(!visited[cur.node]) {
            visited[cur.node] = true;
            
            for(ArrayList<Integer> neighbour : adj.get(cur.node)) {
                int vertex = neighbour.get(0);
                int weight = neighbour.get(1);
                if(!visited[vertex] && cur.dist+weight<dist[vertex]) {
                    dist[vertex] = cur.dist+weight;
                    pq.add(new Pair(vertex, dist[vertex]));
                }
            }
        }
    }
    return dist;
}
```
## ArrayList&lt;Edge&gt;[] graph
``` java
public class DijkstraAlgorithm {
    // Pair class

    static class Edge {
        int src, dest, wt;
        Edge(int src, int dest, int wt) {
            this.src = src;
            this.dest = dest;
            this.wt = wt;
        }
    }

    public static int[] dijkstra(ArrayList<Edge>[] graph, int src) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int[] dist = new int[graph.length];
        boolean[] vis = new boolean[graph.length];
        
        // Initialize distances
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        
        pq.add(new Pair(src, 0));
        
        while (!pq.isEmpty()) {
            Pair curr = pq.remove();
            
            if (vis[curr.node]) continue; // Skip already visited nodes
            
            vis[curr.node] = true;
            
            for (Edge e : graph[curr.node]) {
                int u = e.src;
                int v = e.dest;
                int newDist = dist[u] + e.wt;
                
                if (!vis[v] && newDist < dist[v]) {
                    dist[v] = newDist;
                    pq.add(new Pair(v, newDist));
                }
            }
        }
        
        return dist;
    }

    public static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        
        graph[0].add(new Edge(0, 1, 2));
        graph[0].add(new Edge(0, 2, 4));
        graph[1].add(new Edge(1, 2, 1));
        graph[1].add(new Edge(1, 3, 7));
        graph[2].add(new Edge(2, 4, 3));
        graph[3].add(new Edge(3, 5, 1));
        graph[4].add(new Edge(4, 3, 2));
        graph[4].add(new Edge(4, 5, 5));
    }

    public static void main(String[] args) {
        int V = 6;
        ArrayList<Edge>[] graph = new ArrayList[V];
        createGraph(graph);
        int src = 0;
        int[] dist = dijkstra(graph, src);
        
        System.out.println("Shortest distances from source " + src + ":");
        for (int i = 0; i < dist.length; i++) {
            System.out.println("Node " + i + " : " + dist[i]);
        }
    }
}
```