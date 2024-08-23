import java.util.*;
import java.io.*;

public class NRectangle{
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

    public static boolean isGood(long w, long h, long n, long x) {
        return (x/w)*(x/h) >= n;
    }


    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        FastScanner s = new FastScanner();
        
        long w = s.nextInt();
        long h = s.nextInt();
        long n = s.nextInt();

        long low = Math.max(w,h);
        long high = 1;

        while(!isGood(w,h,n,high)) {
            high = high << 1;
        }

        while(low <= high) {
            long mid = low + (high - low) / 2;

            if(isGood(w,h,n,mid)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        out.println(low);

        out.close();
    }
}