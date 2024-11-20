import java.util.*;
import java.io.*;

public class LazySegmentTree {
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






    static class LazySGTree {
        static int[] tree, lazy;

        public LazySGTree(int size) {
            tree = new int[4 * size + 1];
            lazy = new int[4 * size + 1];
        }

        public static void build(int idx, int start, int end, int[] arr) {
            if(start == end) {
                tree[idx] = arr[start];
                return;
            }

            int mid = start + (end - start) / 2;
            build(2 * idx + 1, start, mid, arr);
            build(2 * idx + 2, mid + 1, end, arr);

            tree[idx] = tree[2 * idx + 1] + tree[2 * idx + 2];
        }

        public static void update(int idx, int start, int end, int l, int r, int val) {
            if(lazy[idx] != 0) {
                tree[idx] += (end - start + 1) * lazy[idx];
                
                if(start != end) {
                    lazy[2 * idx + 1] += lazy[idx];
                    lazy[2 * idx + 2] += lazy[idx];
                }
                lazy[idx] = 0;
            }

            if(l > end || r < start) return;
            if(l <= start && r >= end) {
                tree[idx] += (end - start + 1) * val;
                if(start != end) {
                    lazy[2 * idx + 1] += val;
                    lazy[2 * idx + 2] += val;
                }
                return;
            }

            int mid = start + (end - start) / 2;
            update(2 * idx + 1, start, mid, l, r, val);
            update(2 * idx + 2, mid + 1, end, l, r, val);

            tree[idx] = tree[2 * idx + 1] + tree[2 * idx + 2];
        }

        public static int query(int idx, int start, int end, int l, int r) {
            if(lazy[idx] != 0) {
                tree[idx] += (end - start + 1) * lazy[idx];

                if((2 * idx + 2 < tree.length) && (start != end)) {
                    lazy[2 * idx + 1] += lazy[idx];
                    lazy[2 * idx + 2] += lazy[idx];
                }
                lazy[idx] = 0;
            }

            if(r < start || l > end) return 0;
            if(l <= start && r >= end) return tree[idx];

            int mid = start + (end - start) / 2;
            int left = query(2 * idx + 1, start, mid, l, r);
            int right = query(2 * idx + 2, mid + 1, end, l, r);

            return left + right;
        }
    }







    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        FastScanner s = new FastScanner();

        int t = s.nextInt();

        while(t-- != 0) {
            int n = s.nextInt();
            
            int[] arr = new int[n];
            for(int i = 0; i < n; i++) {
                arr[i] = s.nextInt();
            }

            LazySGTree seg = new LazySGTree(n);
            seg.build(0, 0, n-1, arr);

            int op = s.nextInt();
            for(int i = 0; i < op; i++) {
                int type = s.nextInt();

                if(type == 1) {
                    int l = s.nextInt();
                    int r = s.nextInt();

                    out.println(seg.query(0, 0, n-1, l, r));
                } else {
                    int l = s.nextInt();
                    int r = s.nextInt();
                    int val = s.nextInt();

                    seg.update(0, 0, n-1, l, r, val);
                }
            }
        }
        out.close();
    }
}


