# 0/1 Knapsack

[Dynamic Programming](../DynamicProgramming.md)

-   You are given weights and values of N items, put these items in a knapsack of capacity W to get the maximum total value in the knapsack. Note that we have only one quantity of each item.
    In other words, given two integer arrays val[0..N-1] and wt[0..N-1] which represent values and weights associated with N items respectively. Also given an integer W which represents knapsack capacity, find out the maximum value subset of val[] such that sum of the weights of this subset is smaller than or equal to W. You cannot break an item, either pick the complete item or dont pick it (0-1 property).
-   [Problem Link](https://www.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=bottom_sticky_on_article)

## Basic Recursion Code

-   Time Complexity -> O(2<sup>n</sup>)
-   Space Complexity -> recursion stack space -> n i.e max calls is equal to array size.
-   If the max space is zero or the max items given is zero, then the max profit is zero by default.
-   `val[n-1]` represents val of nth element, 0 based indexing.
-   `wt[n-1] <= W` -> Item can only be included if weight of item is less than or equal to available weight.

```java
static int knapSackD(int W, int wt[], int val[], int n) {
        if(W == 0 || n == 0) {  //base case - if space full or no item remaining
            return 0;
        }

        if(wt[n-1] <= W) {      // valid condtion - include or exclude items
            int include = val[n-1] + knapSackD(W-wt[n-1], wt, val, n-1);
            int exclude = knapSackD(W, wt, val, n-1);
            return Math.max(include, exclude);
        } else {                // invalid condition - exclude only possible
            return knapSackD(W, wt, val, n-1);
        }
}
```

## Memoization Code

-   Time Complexity -> O(n \* W).
-   Space Complexity -> O(n \* W) + recursion stack space.
-   `dp[i][j]` represents the max profit possible with `i` items if `j` is max space.
-   <b><u>intiution</u></b>: If max weight is 5 and cur item is of 2 weight, then cur will fill 2 out of 5 and give some profit, I need to check what is the max profit that the rest of the items can give for remaining 3 weight space. -> `int include = val[n-1] + knapSackD(W-wt[n-1], wt, val, n-1, dp);`.

```java
static int knapSackD(int W, int wt[], int val[], int n, int[][] dp) {
        if(W == 0 || n == 0) {  //base case - if space full or no item remaining
            return 0;
        }

        if(dp[n][W] != -1) {    // check if already solved
            return dp[n][W];
        }

        if(wt[n-1] <= W) {  // valid condtion - include or exclude items
            int include = val[n-1] + knapSackD(W-wt[n-1], wt, val, n-1, dp);
            int exclude = knapSackD(W, wt, val, n-1, dp);
            dp[n][W] = Math.max(include, exclude); // add the solution to dp
            return dp[n][W];
        } else {            // invalid condition - exclude only possible
            dp[n][W] = knapSackD(W, wt, val, n-1, dp); // add the solution to dp
            return dp[n][W];
        }
    }
```

Driver method which will initialize the array and called the memoization method

```java
static int knapSack(int W, int wt[], int val[], int n) {
    int[][] dp = new int[n+1][W+1];
    for(int i=0; i<n+1; i++) {
        for(int j=0; j<W+1; j++) {
            dp[i][j] = -1;
        }
    }
    return knapSackD(W, wt, val, n, dp);
}
```

## Tabulation Code

-   Time Complexity -> O(n \* W)
-   Space Complexity -> O(n \* W)
-   Often, the base condition of memoization becomes the initialization condition of tabulation.
-   First row and first column should be initialized with zero, since the max profit is zero in case no items are available or max space is zero.
-   Here, the above step is skipped because java initializes arrays with zero by default.
-   `w <= j` is used instead of `wt[n-1] <= W` because W remains constant and j changes according to iteration.

```java
static int knapSackTab(int W, int wt[], int val[], int n)
{
    int[][] dp = new int[n+1][W+1]; // Max profit = 0 for (items = 0 or max_space = 0)

    for(int i = 1; i < n+1; i++) {
        for(int j = 1; j < W+1; j++) {
            int v = val[i-1];   // item value
            int w = wt[i-1];    // item weight

            if(w <= j) {  // valid case -> include or exclude
                int include_profit = v + dp[i-1][j-w];
                int exclude_profit = dp[i-1][j];
                dp[i][j] = Math.max(include_profit, exclude_profit);
            } else {            // invalid case -> only exclude possible
                dp[i][j] = dp[i-1][j];
            }
        }
    }

    return dp[n][W];
}
```
