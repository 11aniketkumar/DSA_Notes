# Find if Path Exists in Graph
[Graph](graph.md)
* There is a bi-directional graph with `n` vertices, where each vertex is labeled from `0` to `n - 1` (inclusive). The edges in the graph are represented as a 2D integer array `edges`, where each `edges[i] = [ui, vi]` denotes a bi-directional edge between vertex `ui` and vertex `vi`. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
You want to determine if there is a valid path that exists from vertex source to vertex destination.
Given edges and the integers `n`, `source`, and `destination`, return `true` if there is a valid path from `source` to `destination`, or `false` otherwise.
* [Problem](https://leetcode.com/problems/find-if-path-exists-in-graph/description/)
* Input was given in edge list format, so first convert it into adjacency list.
``` java
class Solution {
    public boolean dfs(ArrayList<Integer>[] graph, boolean[] visited, int source, int destination) {
        if(source == destination) {
            return true;
        }
        visited[source] = true;

        for(Integer neighbour : graph[source]) {
            if(!visited[neighbour]) {
                if(dfs(graph, visited, neighbour, destination)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        ArrayList<Integer>[] graph = new ArrayList[n];

        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        int u, v;
        for(int i = 0; i < edges.length; i++) {
            u = edges[i][0];
            v = edges[i][1];
            graph[u].add(v);
            graph[v].add(u);
        }

        boolean[] visited = new boolean[n];
        return dfs(graph, visited, source, destination);
    }
}
```