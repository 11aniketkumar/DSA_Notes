# Partition Equal Subset Sum

[Dynamic Programming](DynamicProgramming.md)

-   Given an array arr[] of size N, check if it can be partitioned into two parts such that the sum of elements in both parts is the same.
-   [GFG Problem Link](https://www.geeksforgeeks.org/problems/subset-sum-problem2014/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card)

## Memoization

```java
static boolean isPossible(Boolean[][] dp, int[] arr, int n, int sum) {
    if(n == 0 || sum <= 0) {
        if(sum == 0) {
            return true;
        }
        return false;
    }

    if(dp[n][sum] != null) {
        return dp[n][sum];
    }

    boolean include = isPossible(dp, arr, n-1, sum - arr[n-1]);
    boolean exclude = isPossible(dp, arr, n-1, sum);
    return dp[n][sum] = include || exclude;
}
```

-   Check if sum of entire array can be divided into two equal parts i.e `sum % 2 == 0`.
-   If `sum` is even, check if there exists a subset with `sum` equal to half of the entire array `sum`.
-   If there exists such an subset, than it implies the other subset also have the same `sum`.

```java
static int equalPartition(int N, int arr[])
{
    int sum = 0;
    for(int i = 0; i < N; i++) {
        sum += arr[i];
    }

    if(sum % 2 != 0) {
        return 0;
    }

    sum /= 2;
    Boolean[][] dp = new Boolean[N+1][sum + 1];

    return isPossible(dp, arr, N, sum) ? 1 : 0;
}
```

## Tabulation

-   There is always atleast one way to achieve `sum = 0` given any number of items i.e by selecting no items, yet if the array contains `0`, then there is more than one way to get `sum = 0`. In those cases, we have to start the loop from `j = 0`.
-   Here, I have taken `j` ranging from `1 -> sum+1` instead of `0 -> sum+1` because, it was specified in question range that `a[i]` will be greater or equal to `1`.

```java
static int equalPartition(int N, int arr[])
{
    int sum = 0;
    for(int i = 0; i < N; i++) {
        sum += arr[i];
    }

    if(sum % 2 != 0) return 0; // base exit condition

    sum /= 2;

    boolean[][] dp = new boolean[N+1][sum + 1];

    // initializing
    for(int i = 0; i < N+1; i++) {
        dp[i][0] = true;
    }

    // filling the table
    for(int i = 1; i < N+1; i++) {
        for(int j = 1; j < sum+1; j++) {
            if(arr[i-1] <= j) {
                dp[i][j] = dp[i-1][j-arr[i-1]] || dp[i-1][j];
            } else {
                dp[i][j] = dp[i-1][j];
            }
        }
    }

    return dp[N][sum] ? 1 : 0;
}
```

-   You can replace filling table part with the follow approach also

```java
for(int i = 1; i < N+1; i++) {
    for(int j = 1; j < sum+1; j++) {
        if((arr[i-1] <= j) && (dp[i-1][j-arr[i-1]])) {
            dp[i][j] =  true;
        } else {
            dp[i][j] = dp[i-1][j];
        }
    }
}
```
