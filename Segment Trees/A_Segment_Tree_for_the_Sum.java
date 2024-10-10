import java.util.*;
import java.io.*;

public class A_Segment_Tree_for_the_Sum {
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");
        String next() {
            while (!st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {}
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
        long nextLong() {
            return Long.parseLong(next());
        }

        char[] nextCharArray() {
            try {
                String line = br.readLine();
                return line.toCharArray();
            } catch (IOException e) {
                e.printStackTrace();
                return new char[0];
            }
        }
    }

    static long[] tree;

    public static void build(int[] arr, int start, int end, int idx) {
        if(start == end) {
            tree[idx] = arr[start];
            return;
        }

        int mid = start + (end - start) / 2;
        build(arr, start, mid, 2 * idx + 1);
        build(arr, mid + 1, end, 2 * idx + 2);

        tree[idx] = tree[2*idx+1] + tree[2*idx+2];
    }

    public static long query(int idx, int start, int end, int l, int r) {
        if(start > r || end < l) return 0;
        if(start >= l && end <= r) return tree[idx];

        int mid = start + (end - start) / 2;
        long left = query(2*idx+1, start, mid, l, r);
        long right = query(2*idx+2, mid+1, end, l, r);

        return left + right;
    }

    public static void update(int idx, int start, int end, int i, int val) {
        if(start == end) {
            tree[idx] = val;
            return;
        }

        int mid = start + (end - start) / 2;

        if(i <= mid) {
            update(2*idx+1, start, mid, i, val);
        } else {
            update(2*idx+2, mid+1, end, i, val);
        }
        tree[idx] = tree[2*idx+1] + tree[2*idx+2];
    }

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        FastScanner s = new FastScanner();

        int n = s.nextInt();
        int m = s.nextInt();

        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }

        tree = new long[4 * n];
        build(arr, 0, arr.length - 1, 0);

        for(int i = 0; i < m; i++) {
            int op = s.nextInt();
            int a = s.nextInt();
            int b = s.nextInt();
            
            if(op == 1) {
                update(0, 0, arr.length-1, a, b);
            } else {
                out.println(query(0, 0, arr.length -1, a, b-1));
            }
        }
            
        out.close();
    }
}