# Rod Cutting

[Dynamic Programming](../DynamicProgramming.md)

Given a rod of length N inches and an array of prices, price[]. price[i] denotes the value of a piece of length i. Determine the maximum value obtainable by cutting up the rod and selling the pieces.

Note: Consider 1-based indexing.

[GFG Problem Link](https://www.geeksforgeeks.org/problems/rod-cutting0840/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card)

## Memoization

```java
public int maxProfit(int[][] dp, int[] price, int n, int rem_len) {
    if(n == 0 ||  rem_len == 0) {
        return 0;
    }

    if(dp[n][rem_len] != -1) {
        return dp[n][rem_len];
    }


    int include = 0;
    if(rem_len >= n) {
        include = price[n-1] + maxProfit(dp, price, n, rem_len-n);
    }
    int exclude = maxProfit(dp, price, n-1, rem_len);

    return dp[n][rem_len] = Math.max(include, exclude);
}
```

```java
public int cutRod(int price[], int l) {
    int n = price.length;
    int[][] dp = new int[n+1][l+1];

    for(int i = 0; i < n+1; i++) {
        for(int j = 0; j < l+1; j++) {
            dp[i][j] = -1;
        }
    }

    return maxProfit(dp, price, n, l);
}
```

## Tabulation

-   Time Complexity -> O(n \* L)
-   Space Complexity -> O(n \* L)

```java
public int cutRod(int price[], int l) {
    int n = price.length;
    int[][] dp = new int[n+1][l+1];


    for(int i = 1; i < n+1; i++) {
        for(int j = 1; j < l+1; j++) {
            if(i <= j) {
                int include = dp[i][j-i] + price[i-1];
                int exclude = dp[i-1][j];
                dp[i][j] = Math.max(include, exclude);
            } else {
                dp[i][j] = dp[i-1][j];
            }
        }
    }

    return dp[n][l];
}
```
