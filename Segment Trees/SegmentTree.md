
```java
import java.util.*;
import java.io.*;

public class SegmentTree {
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

    static class SGTree {
        int[] tree;

        public SGTree(int size) {
            this.tree = new int[4 * size + 1];
        }

        public void build(int idx, int start, int end, int[] arr) {
            if(start == end) {
                tree[idx] = arr[start];
                return;
            }

            int mid = start + (end - start) / 2;
            build(2 * idx + 1, start, mid, arr);
            build(2 * idx + 2, mid + 1, end, arr);

            tree[idx] = Math.min(tree[2*idx+1], tree[2*idx+2]);
        }

        public int query(int idx, int start, int end, int l, int r) {
            if(start > r || end < l) return Integer.MAX_VALUE;
            if(start >= l && end <= r) return tree[idx];

            int mid = start + (end - start) / 2;
            int left = query(2*idx+1, start, mid, l, r);
            int right = query(2*idx+2, mid+1, end, l, r);

            return Math.min(left, right);
        }

        public void update(int idx, int start, int end, int i, int val) {
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
            tree[idx] = Math.min(tree[2*idx+1], tree[2*idx+2]);
        }
    }

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        FastScanner s = new FastScanner();


        // taking first array input and building first segment tree
        int n1 = s.nextInt();

        int[] arr1 = new int[n1];
        for(int i = 0; i < n1; i++) {
            arr1[i] = s.nextInt();
        }

        SGTree seg1 = new SGTree(n1);
        seg1.build(0, 0, n1-1, arr1);



        // taking second array input and building second segment tree
        int n2 = s.nextInt();
        int[] arr2 = new int[n2];
        for(int i = 0; i < n2; i++) {
            arr2[i] = s.nextInt();
        }

        SGTree seg2 = new SGTree(n2);
        seg2.build(0, 0, n2-1, arr2);


        // number of queries
        int q = s.nextInt();
        for(int j = 0; j < q; j++) {
            int type = s.nextInt();

            if(type == 1) {
                // range in first array
                int l1 = s.nextInt();
                int r1 = s.nextInt();

                // range in second array
                int l2 = s.nextInt();
                int r2 = s.nextInt();

                int min1 = seg1.query(0, 0, n1-1, l1, r2);
                int min2 = seg2.query(0, 0, n2-1, l2, r2);
                out.println(Math.min(min1, min2));
            } else {
                // which array to update
                int arrNo = s.nextInt();
                int i = s.nextInt();
                int val = s.nextInt();

                if(arrNo == 1) {
                    seg1.update(0, 0, n1-1, i, val);
                    arr1[i] = val;
                } else {
                    seg2.update(0, 0, n2-1, i, val);
                    arr2[i] =val;
                }
            }
        }

        out.close();
    }
}
```
