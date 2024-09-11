# Longest Common Subsequence

[Dynamic Programming](../DynamicProgramming.md)

Given two strings `text1` and `text2`, return the length of their longest common subsequence. If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, `"ace"` is a subsequence of `"abcde"`.
A common subsequence of two strings is a subsequence that is common to both strings.

## Memoization

```java
public int findLongest(int[][] dp, char[] t1, char[] t2, int n, int m) {
    if(n == 0 || m == 0) {
        return 0;
    }

    if(dp[n][m] != -1) {
        return dp[n][m];
    }

    if(t1[n-1] == t2[m-1]) {
        return dp[n][m] = 1 + findLongest(dp, t1, t2, n-1, m-1);
    } else {
        int sol1 = findLongest(dp, t1, t2, n-1, m);
        int sol2 = findLongest(dp, t1, t2, n, m-1);
        return dp[n][m] = Math.max(sol1, sol2);
    }
}
```

```java
public int longestCommonSubsequence(String text1, String text2) {
    char[] t1 = text1.toCharArray();
    char[] t2 = text2.toCharArray();
    int n = t1.length;
    int m = t2.length;

    int[][] dp = new int[n+1][m+1];
    for(int i = 0; i < n+1; i++) {
        for(int j = 0; j < m+1; j++) {
            dp[i][j] = -1;
        }
    }

    return findLongest(dp, t1, t2, n, m);
}
```

## Tabulation

```java
public int longestCommonSubsequence(String text1, String text2) {
    char[] t1 = text1.toCharArray();
    char[] t2 = text2.toCharArray();
    int n = t1.length;
    int m = t2.length;

    int[][] dp = new int[n+1][m+1];


    for(int i = 1; i < n+1; i++) {
        for(int j = 1; j < m+1; j++) {
            if(t1[i-1] == t2[j-1]) {    // found common character
                dp[i][j] = 1 + dp[i-1][j-1];
            } else {        // else select the best out of both
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
    }

    return dp[n][m];
}
```
