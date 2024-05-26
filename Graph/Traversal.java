import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

@SuppressWarnings("unchecked")
class Traversal {
    public static ArrayList<Integer>[] readGraph() {
	    Scanner s = new Scanner(System.in);
	    
	    System.out.print("Enter the no. of nodes and edges: ");
	    int n = s.nextInt();
	    int m = s.nextInt();
	    
		ArrayList<Integer> graph[] = new ArrayList[n];
		for(int i = 0; i < n; i++) {
		    graph[i] = new ArrayList<Integer>();
		}
		
		System.out.println("Enter the edges: ");
		int a, b;
		for(int i = 0; i < m; i++) {
		    a = s.nextInt();
		    b = s.nextInt();
		    graph[a].add(b);
		    graph[b].add(a);
		}

		return graph;
	}

    public static ArrayList<Integer> bfs(ArrayList<Integer>[] graph, int start) {
        ArrayList<Integer> res = new ArrayList<>();

        boolean[] visited = new boolean[graph.length];
        Queue<Integer> q = new LinkedList<>();

        q.add(start);
        visited[start] = true;
        while(!q.isEmpty()) {
            int cur = q.remove();
            res.add(cur);

            for(Integer neighbour : graph[cur]) {
                if(!visited[neighbour]) {   // not visited neighbour
                    q.add(neighbour);
                    visited[neighbour] = true;
                }
            }
        }
        return res;
    }

    public static ArrayList<Integer> dfs(ArrayList<Integer>[] graph, boolean visited[], ArrayList<Integer> res, int start) {
		visited[start] = true;
		res.add(start);

		for(int neighbour : graph[start]) {
			if(!visited[neighbour]) {               // not visited neighbour
				dfs(graph, visited, res, neighbour);
			}
		}

		return res;
	}

	public static ArrayList<Integer> dfsDriver(ArrayList<Integer>[] graph, int start) {
		ArrayList<Integer> res = new ArrayList<>();
        boolean visited[] = new boolean[graph.length];

        dfs(graph, visited, res, start);
        for(int i = 0; i < visited.length; i++) {
            if(!visited[i]) {                       // not visited node
                dfs(graph, visited, res, i);
            }
        }

		return res;
	}

    public static void main(String[] args) {
		ArrayList<Integer>[] graph = readGraph();

		ArrayList<Integer> data = bfs(graph, 1);

        System.out.print("\nBFS : ");
		for(int i : data) {
			System.out.print(i + " ");
		}

        data = dfsDriver(graph, 1);
        System.out.print("\nDFS : ");
		for(int i : data) {
			System.out.print(i + " ");
		}
	}
}