# Minimum Coin Change

[Dynamic Programming](../DynamicProgramming.md)

You are given an integer array `coins` representing coins of different denominations and an integer `amount` representing a total `amount` of money.

Return the fewest number of coins that you need to make up that `amount`. If that `amount` of money cannot be made up by any combination of the coins, return `-1`.

You may assume that you have an infinite number of each kind of coin.

## Intuition

-   Note : We want count of <u>minimum <b>number</b> of coins</u> required, and <b>NOT</b> the <u>number of ways</u>.
-   We can achieve `0` sum using `0` coins, but any other sum cannot be achieved using `0` coins, instead even `infinite` number of coins times `0` won't be enough.
-   Also, since we want to take the `min` of all possible coin combination, we cannot initialize impossible states with `0`. Otherwise, subsequent states would be calculated based on previous ones, and the `min` of any value with `0` would always return `0`, which would be incorrect.

## Memoization

-   In the inclusion condition, we are adding `1` to the values of previous states. If the previous state is `infinity`, adding `1` would cause an overflow, resulting in the smallest possible number. This is why we initialize the state with `infinity - 1`.

```java
public int count(int[][] dp, int[] coins, int n, int amount) {
    if(n <= 0 || amount <= 0) {
        if(amount == 0) {
            return dp[n][amount] = 0;
        }
        return Integer.MAX_VALUE - 1;
    }

    if(dp[n][amount] != -1) {
        return dp[n][amount];
    }

    if(coins[n-1] <= amount) {
        int include = 1 + count(dp, coins, n, amount-coins[n-1]);
        int exclude = count(dp, coins, n-1, amount);
        return dp[n][amount] = Math.min(include, exclude);
    } else {
        return dp[n][amount] = count(dp, coins, n-1, amount);
    }
}
```

```java
public int coinChange(int[] coins, int amount) {
    int[][] dp = new int[coins.length+1][amount+1];

    for(int i = 0; i < coins.length+1; i++) {
        for(int j = 0; j < amount+1; j++) {
            dp[i][j] = -1;
        }
    }

    if(count(dp, coins, coins.length, amount) >= Integer.MAX_VALUE - 1) {
        return -1;
    }
    return dp[coins.length][amount];
}
```

## Tabulation

-   Using `infinity - 1` is not strictly necessary. Any large number beyond the range of the possible number of coins will work. For instance, we can use `amount + 1` because, even if we use the smallest denomination (a coin of value `1`), the maximum number of coins required to achieve the `amount` is `amount` itself. Therefore, the `min` of any valid combination with `amount + 1` will not return `amount + 1`.

```java
public int coinChange(int[] coins, int amount) {
    int[][] dp = new int[coins.length+1][amount+1];

    for(int j = 1; j < amount+1; j++) {
        dp[0][j] = Integer.MAX_VALUE - 1;
    }

    for(int i = 1; i < coins.length+1; i++) {
        for(int j = 1; j < amount+1; j++) {
            if(coins[i-1] <= j) {
                int include = 1 + dp[i][j-coins[i-1]];
                int exclude = dp[i-1][j];
                dp[i][j] = Math.min(include, exclude);
            } else {
                dp[i][j] = dp[i-1][j];
            }
        }
    }

    if(dp[coins.length][amount] >= Integer.MAX_VALUE - 1) {
        return -1;
    }
    return dp[coins.length][amount];
}
```
