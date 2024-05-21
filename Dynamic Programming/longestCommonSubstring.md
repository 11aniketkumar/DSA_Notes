# Longest Commmon Subsequence (LCS)
[Dynamic Programming](DynamicProgramming.md)
* For any given two strings, find the length of the longest commong substring.

## Tabulation Code
* Time Complexity -> O(n * m).
* Space Complexity -> O(n * m).
* It's similar to lcs problem except for the else part, if the last character doesn't match then instead of `dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1])`, we reset it to zero `dp[i][j]=0`, since substring should be continuous; Once the continuity breaks, start counting again from zero. 
* For more details, check the intiution part in basic recursion code of LCS.
``` java
public static int longestCommonSubstring(String str1, String str2) {
    int n = str1.length();
    int m = str2.length();
    int res = 0;
    int[][] dp = new int[n+1][m+1];

    // initialize i=0 and j=0 as 0 in dp array
    for(int i = 1; i <= n; i++) {
        for(int j = 1; j <= m; j++) {
            if(str1.charAt(i-1) == str2.charAt(j-1)) {  // last character matched
                dp[i][j] = dp[i-1][j-1] + 1;
                res = Math.max(res, dp[i][j]);
            } else {                                    // reset substring length=0
                dp[i][j] = 0;
            }
        }
    }

    return res;
}
```