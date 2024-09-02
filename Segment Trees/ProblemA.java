// Calculate the sum of of a given range

import java.util.*;
import java.io.*;

public class ProblemA {
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

    public static void createTreeUtil(int[] arr, int idx, int start, int end) {
        if(start == end) {
            tree[idx] = arr[start];
            return;
        }

        int mid = start + (end - start) / 2;
        createTreeUtil(arr, 2*idx+1, start, mid);
        createTreeUtil(arr, 2*idx+2, mid+1, end);

        tree[idx] = tree[2*idx+1] + tree[2*idx+2];
    }

    public static void createTree(int[] arr) {
        createTreeUtil(arr, 0, 0, arr.length - 1);
    }

    public static long query(int idx, int start, int end, int l, int r) {
        if((l > end) || (r < start)) {
            return 0;
        } else if((start >= l) && (end <= r)) {
            return tree[idx];
        } else {
            int mid = start + (end - start) / 2;

            long left = query(2*idx+1, start, mid, l, r);
            long right = query(2*idx+2, mid+1, end, l, r);
            return left + right;
        }
    }

    public static long getSum(int l, int r) {
        return query(0, 0, (tree.length / 4) -1, l, r);
    }


    public static void update(int[] arr, int idx, int start, int end, int i, int diff) {
        if((i < start) || (i > end)) {
            return;
        }

        tree[idx] += diff;

        if(start != end) {
            int mid = start + (end - start) / 2;
            update(arr, 2*idx+1, start, mid, i, diff);
            update(arr, 2*idx+2, mid+1, end, i, diff);
        }
    }

    public static void updateTree(int[] arr, int i, int value) {
        int diff = value - arr[i];
        arr[i] = value;
        update(arr, 0, 0, arr.length-1, i, diff);
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
        createTree(arr);

        for(int i = 0; i < m; i++) {
            int operation = s.nextInt();
            int a = s.nextInt();
            int b = s.nextInt();

            if(operation == 1) {
                updateTree(arr, a, b);
            } else {
                out.println(getSum(a, b-1));
                out.flush();
            }
        }

        out.close();
    }
}
