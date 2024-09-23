import java.util.Scanner;

class wildCardMatching {
    public static boolean wildCard(Boolean[][] dp, char[] pattern, char[] str, int p, int n) {
        if(p == 0 && n == 0) return true;
        if(p == 0) return false;
        if(n == 0) {
            if(pattern[p-1] == '*') {
                return wildCard(dp, pattern, str, p-1, n);
            } else {
                return dp[p][n] = false;
            }
        }

        if(dp[p][n] != null) {
            return dp[p][n];
        }

        if(pattern[p-1] == '?') {
            return dp[p][n] = wildCard(dp, pattern, str, p-1, n-1);
        } else if(pattern[p-1] == '*') {
            return dp[p][n] = wildCard(dp, pattern, str, p-1, n) || wildCard(dp, pattern, str, p, n-1);
        } else {
            if(pattern[p-1] == str[n-1]) {
                return dp[p][n] = wildCard(dp, pattern, str, p-1, n-1);
            } else {
                return dp[p][n] = false;
            }
        }
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        // String text = s.next();
        // String pattern = s.next();

        // Boolean[][] dp = new Boolean[pattern.length()+1][text.length()+1];

        // System.out.println(wildCard(dp, pattern.toCharArray(), text.toCharArray(), pattern.length(), text.length()));



        // tabulation
        String t = s.next();
        char[] text = t.toCharArray();
        String p = s.next();
        char[] pattern = p.toCharArray();

        Boolean[][] dp = new Boolean[pattern.length+1][text.length+1];

        dp[0][0] = true;
        for(int j = 1; j <= text.length; j++) {
            dp[0][j] = false;
        }

        for(int i = 1; i <= pattern.length; i++) {
            if(pattern[i-1] == '*') {
                dp[i][0] = dp[i-1][0];
            } else {
                dp[i][0] = false;
            }
        }

        for(int i = 1; i <= pattern.length; i++) {
            for(int j = 1; j <= text.length; j++) {
                if(pattern[i-1] == '?' || pattern[i-1] == text[j-1]) {
                    dp[i][j] = dp[i-1][j-1];
                } else if(pattern[i-1] == '*') {
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                } else {
                    dp[i][j] = false;
                }
            }
        }

        System.out.println(dp[pattern.length][text.length]);
    }
}