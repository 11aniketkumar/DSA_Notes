import java.util.*;
import java.io.*;

public class Ropes{
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
    }

    public static boolean isGood(int[] arr, int k, double mid) {
        int count = 0;
        for(int i = 0; i < arr.length; i++) {
            count += arr[i] / mid;
        }
        return count >= k;
    }


    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        FastScanner s = new FastScanner();
        
        int n = s.nextInt();
        int k = s.nextInt();

        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }

        Arrays.sort(arr);

        double low = 0;
        double high = arr[n-1];

        for(int i = 0; i < 45; i++) {
            double mid = (low + high) / 2;

            if(isGood(arr, k, mid)) {
                low = mid;
            } else {
                high = mid;
            }
        }
        out.println(low);

        out.close();
    }
}