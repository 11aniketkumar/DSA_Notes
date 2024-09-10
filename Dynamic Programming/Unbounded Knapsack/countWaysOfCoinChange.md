# Coin Change - Total no. of ways

[Dynamic Programming](../DynamicProgramming.md)

You are given an integer array `coins` representing coins of different denominations and an integer `amount` representing a total amount of money.

Return the number of combinations that make up that `amount`. If that `amount` of money cannot be made up by any combination of the coins, return `0`.

You may assume that you have an infinite number of each kind of coin.

[LeetCode Problem Link](https://leetcode.com/problems/coin-change-ii/description/)

## Memoization

```java
public int count(int[][] dp, int[] coins, int n, int amount) {
    if(n <= 0 || amount <= 0) {
        if(amount == 0) {
            return 1;
        }
        return 0;
    }

    if(dp[n][amount] != -1) {
        return dp[n][amount];
    }

    if(coins[n-1] <= amount) {
        int include = count(dp, coins, n, amount-coins[n-1]);
        int exclude = count(dp, coins, n-1, amount);
        return dp[n][amount] = include + exclude;
    } else {
        return dp[n][amount] = count(dp, coins, n-1, amount);
    }
}
```

```java
public int change(int amount, int[] coins) {
    int[][] dp = new int[coins.length+1][amount+1];

    for(int i = 0; i < coins.length+1; i++) {
        for(int j = 0; j < amount+1; j++) {
            dp[i][j] = -1;
        }
    }

    return count(dp, coins, coins.length, amount);
}
```

## Tabulation

```java
public int change(int amount, int[] coins) {
    int[][] dp = new int[coins.length+1][amount+1];

    for(int i = 0; i < coins.length+1; i++) {
        dp[i][0] = 1;
    }

    for(int i = 1; i < coins.length+1; i++) {
        for(int j = 1; j < amount+1; j++) {
            if(coins[i-1] <= j) {
                int include = dp[i][j-coins[i-1]];
                int exclude = dp[i-1][j];
                dp[i][j] = include + exclude;
            } else {
                dp[i][j] = dp[i-1][j];
            }
        }
    }

    return dp[coins.length][amount];
}
```
