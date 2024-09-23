import java.util.*;

public class CatalanNumber {
    public static int generateCatalan(int[] dp, int n) {
        if(n == 0 || n == 1) return 1;

        if(dp[n] != -1) {
            return dp[n];
        }

        int res = 0;
        for(int i = 0; i < n; i++) {
            res += generateCatalan(dp, i) * generateCatalan(dp, n - i - 1);
        }
        return dp[n] = res;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        int[] dp = new int[n+1];
        // Arrays.fill(dp, -1);

        // System.out.println(generateCatalan(dp, n));

        // for(int i : dp) {
        //     System.out.print(i + " ");
        // }

        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i < n+1; i++) {
            int res = 0;
            for(int j = 0; j < i; j++) {
                res += dp[j]* dp[i-j-1];
            }
            dp[i] = res;
        }

        System.out.println(dp[n]);

        for(int i : dp) {
            System.out.print(i + " ");
        }
    }
}