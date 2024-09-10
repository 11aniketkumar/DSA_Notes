# Unbounded Knapsack

[Dynamic Programming](../DynamicProgramming.md)

-   You are given weight and value of different items, what is the maximum profit achievable by putting them in capacity W. Note that an item can be included more than once.

```java
public static void main(String[] args) {
    int val[] = {15, 14, 10, 45, 30};
    int wt[] = {2, 5, 1, 3, 4};
    int W = 7;

    System.out.println(unboundedKnapsack(val, wt, W));
}
```

## Tabulation

-   Time Complexity -> O(n \* W)
-   Space Complexity -> O(n \* W)
-   `dp[i][j]` represents maximum profit achievable using `i` items in `j` space.
-   <b><u>Base case</u></b>: Initially we know that when space is 0 (j=0), max profit will be 0. Also, when no items are present (i=0), max profit attainable is still zero.
-   <b><u>Intiution</u></b>:
    The only thing different from 0/1 knapsack is that items can be added again, therefore after including item with value `v`, we won't be filling remaining space `j-w` with `i-1` items but `i` items instead -> `dp[i][j] = Math.max(v + dp[i][j-w], dp[i-1][j]);`

```java
public static int unboundedKnapsack(int[] val, int[] wt, int W) {
    int n = val.length;
    int[][] dp = new int[n+1][W+1];

    // initialize i=0 with 0 and j=0 with 0

    for(int i = 1; i <= n; i++) {
        for(int j = 1; j <= W; j++) {
            int v = val[i-1];       // item value
            int w = wt[i-1];        // item weight
            if(w <= j) {      // valid - max of include or exclude
                dp[i][j] = Math.max(v + dp[i][j-w], dp[i-1][j]);
            } else {                // invalid - exclude
                dp[i][j] = dp[i-1][j];
            }
        }
    }
    return dp[n][W];
}
```
