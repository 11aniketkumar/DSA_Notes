# Longest Commmon Substring

[Dynamic Programming](../DynamicProgramming.md)

You are given two strings str1 and str2. Your task is to find the length of the longest common substring among the given strings.

[GFG Problem Link](https://www.geeksforgeeks.org/problems/longest-common-substring1452/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card)

## Memoization

-   Not recommended since it will require you to carry a third variable.

## Tabulation Code

-   Time Complexity -> O(n \* m).
-   Space Complexity -> O(n \* m).

```java
public int longestCommonSubstr(String str1, String str2) {
    int n = str1.length();
    int m = str2.length();
    char[] s1 = str1.toCharArray();
    char[] s2 = str2.toCharArray();

    int[][] dp = new int[n+1][m+1];

    int maxLen = 0;
    for(int i = 1; i < n+1; i++) {
        for(int j = 1; j < m+1; j++) {
            if(s1[i-1] == s2[j-1]) {
                dp[i][j] = 1 + dp[i-1][j-1];
                maxLen = Math.max(maxLen, dp[i][j]);
            } else {
                dp[i][j] = 0;
            }
        }
    }

    return maxLen;
}
```
