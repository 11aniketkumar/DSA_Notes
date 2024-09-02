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
        // no. of rectangle that can be fitted along width -> x/w
        // no. of rectangle that can be fitted along height -> x/h
        // total no. of rectangle that can be fitted in x -> (x/w) * (x/h)
        return (x/w)*(x/h) >= n;
    }


    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        FastScanner s = new FastScanner();
        
        long w = s.nextInt();  // width
        long h = s.nextInt(); // height
        long n = s.nextInt(); // no. of rectangles

        // Question : Square of side x, find the smallest len(x) to fit n rectangles in it.

        // -1 to make sure that low always start from false
        long low = Math.max(w,h) - 1;
        long high = 1 << 5;
        while(!isGood(w,h,n,high)) {
            high = high << 1;
        }

        while(high - low > 1) {
            long mid = low + (high - low) / 2;

            if(isGood(w,h,n,mid)) {
                high = mid;
            } else {
                low = mid;
            }
        }
        out.println(high);

        out.close();
    }
}