# Graph
* Definition...
* Edges are of two types:
  - Uni-Directional
  - Un-Directed or Bi-Directed
* Edges can be either:
  - Weighted
  - Unweighted

## Java Concept: Generic Type
* `ArrayList<Integer> graph[] = new ArrayList[V];`
* Java doesn't allow the creation of arrays with a generic data type because it won't be compatible with previous versions of Java.
* Java treats all generic types as Object, which means `ArrayList<Integer>` will be converted to `ArrayList<Object>`.
* Therefore, there is no point in writing the type of ArrayList. Even if you specify a type like Float, it will be treated as Object.
* A workaround is to create a raw array that can store pointers to ArrayList using `new ArrayList[V]`.
* Then, while storing values in this array, typecast it to `ArrayList<Integer>`.
* `ArrayList<Integer> graph[] = new ArrayList[V];`


## Graph Representations
- Adjacency Matrix
- Adjacency List
    - ArrayList&lt;ArrayList&gt;
    - Array&lt;ArrayList&gt;
    - HashMap&lt;int, lists&gt;
- Edge List
- 2D Matrix (Implicit Graph)

## [Graph Traversal](Traversal.java)
- Depth-First Search (DFS)
- Breadth-First Search (BFS)
- Applications:
  - [PathFinding](pathFinding.md)
  - Connected Components
  - [Cycle Detection](cycleDetection.md)
  - Topological Sorting (for Directed Acyclic Graphs)

## Shortest Path Algorithms
- Single Source Shortest Path:
  - [Dijkstra’s Algorithm](dijkstra.md)
  - Bellman-Ford Algorithm
- All Pairs Shortest Path:
  - Floyd-Warshall Algorithm
  - Johnson’s Algorithm

## Minimum Spanning Tree (MST)
- Kruskal’s Algorithm
- Prim’s Algorithm

## Network Flow Algorithms
- Ford-Fulkerson Method
- Edmonds-Karp Algorithm
- Dinic’s Algorithm
- Applications:
  - Max Flow / Min Cut Theorem
  - Bipartite Matching
  - Circulation Problems

## Graph Coloring
- Vertex Coloring
- Edge Coloring
- Applications:
  - Scheduling Problems
  - Map Coloring

## Connectivity and Components
- Strongly Connected Components (SCC):
  - Kosaraju’s Algorithm
  - Tarjan’s Algorithm
- Biconnected Components
- Articulation Points and Bridges

## Eulerian and Hamiltonian Paths
- Eulerian Path and Circuit
- Hamiltonian Path and Cycle
- Applications:
  - Graph Traversal Problems
  - Traveling Salesman Problem (TSP)

## Graph Matching
- Bipartite Graph Matching:
  - Hopcroft-Karp Algorithm
- General Graph Matching:
  - Blossom Algorithm

## Planar Graphs
- Properties of Planar Graphs
- Planarity Testing
- Graph Drawing

## Graph Problems in Competitive Programming
- Problem-Solving Patterns and Techniques
- Common Problems and Their Solutions
