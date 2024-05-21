# Longest Commmon Subsequence (LCS)
[Dynamic Programming](DynamicProgramming.md)
* For any given two strings, find the length of the longest commong subsequence.

``` java
public static void main(String[] args) {
    String str1 = "abcdge";
    String str2 = "abedg";

    int[][] dp = new int[str1.length() + 1][str2.length() + 1];
    for(int i = 0; i < str1.length() + 1; i++) {
        for(int j = 0; j < str2.length() + 1; j++) {
            dp[i][j] = -1;
        }
    }

    // Basic Recursion
    System.out.println(lcs(str1, str2, str1.length(), str2.length()));

    // Memoization
    System.out.println(lcs(dp, str1, str2, str1.length(), str2.length()));

    // Tabulation
    System.out.println(lcsTab(str1, str2));
}
```

## Basic Recursion Code 
* Time Complexity -> O( 2<sup>n+m</sup> ) -> O( 2<sup>max(n,m)</sup> ).
* Space Complexity -> Number of nodes in recursion binary tree -> O(n+m).
* When `str1` or `str2` is empty, the length of LCS will be 0.
### Intiution: 
* Check the last characters of both strings. If they match, add 1 to the length of the LCS and call the function for the strings without the last characters.
* But, if the last character doesn't match then we get two cases:
    - Remove the last character from `str1` and check again.
    - Remove the last character from `str2` and check again.
* The final answer will be the maximum of both the above cases.
``` java
public static int lcs(String str1, String str2, int n, int m) {
    if(n == 0 || m == 0) {
        return 0;
    }

    if(str1.charAt(n-1) == str2.charAt(m-1)) {
        return 1 + lcs(str1, str2, n-1, m-1);
    } else {
        int ans1 = lcs(str1, str2, n-1, m);
        int ans2 = lcs(str1, str2, n, m-1);
        return Math.max(ans1, ans2);
    }
}
```

## Memoization Code
* Time Complexity -> O(n * m).
* Space Complexity -> Array space + recursion stack space -> O(n * m) + O(n + m).
* `dp[i][j]` represents the length of LCS when `str1` is of length `i` and `str2` is of length `j`.
* Note: `dp[0][0]` doesn't store the result of comparing `str1[0]` and `str2[0]`; instead, it indicates the LCS length when `str1` and `str2` are both of length 0 or empty.
``` java
public static int lcs(int[][] dp, String str1, String str2, int n, int m) {
    if(n == 0 || m == 0) {
        return 0;
    }

    if(dp[n][m] != -1) {
        return dp[n][m];
    }

    if(str1.charAt(n-1) == str2.charAt(m-1)) {
        return dp[n][m] = 1 + lcs(dp, str1, str2, n-1, m-1);
    } else {
        int ans1 = lcs(dp, str1, str2, n-1, m);
        int ans2 = lcs(dp, str1, str2, n, m-1);
        return dp[n][m] = Math.max(ans1, ans2);
    }
}
```

## Tabulation Code
* Time Complexity -> O(n * m).
* Space Complexity -> O(n * m).
* Initialization: `dp[i][j]=0` for `i=0` or `j=0`. It was skipped because default value of array in java is 0.
``` java
public static int lcsTab(String str1, String str2) {
    int n = str1.length();
    int m = str2.length();
    int[][] dp = new int[n+1][m+1];

    // when str1=0 or str2=0, then dp[i][j]=0

    for(int i = 1; i < n+1; i++) {
        for(int j = 1; j < m+1; j++) {
            if(str1.charAt(i-1) == str2.charAt(j-1)) {
                dp[i][j] = 1 + dp[i-1][j-1];
            } else {
                int ans1 = dp[i-1][j];
                int ans2 = dp[i][j-1];
                dp[i][j] = Math.max(ans1, ans2);
            }
        }
    }

    return dp[n][m];
}
```