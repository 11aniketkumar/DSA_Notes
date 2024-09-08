# Partition Equal Subset Sum

[Dynamic Programming](DynamicProgramming.md)

-   Given an array arr[] of size N, check if it can be partitioned into two parts such that the sum of elements in both parts is the same.
-   [GFG Problem Link](https://www.geeksforgeeks.org/problems/subset-sum-problem2014/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card)

## Memoization

```java
static int isPossible(int[][] dp, int[] arr, int n, int sum) {
    if(n == 0 || sum <= 0) {
        if(sum == 0) {
            return 1;
        }
        return 0;
    }

    if(dp[n][sum] != -1) {
        return dp[n][sum];
    }

    int include = isPossible(dp, arr, n-1, sum - arr[n-1]);
    int exclude = isPossible(dp, arr, n-1, sum);
    return dp[n][sum] = Math.max(include, exclude);
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
    int[][] dp = new int[N+1][sum + 1];

    for(int i = 0; i < N+1; i++) {
        for(int j = 0; j < sum + 1; j++) {
            dp[i][j] = -1;
        }
    }

    return isPossible(dp, arr, N, sum);
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

    if(sum % 2 != 0) {
        return 0;
    }

    sum /= 2;
    int[][] dp = new int[N+1][sum + 1];

    for(int i = 0; i < N+1; i++) {
        dp[i][0] = 1;
    }

    for(int i = 1; i < N+1; i++) {
        for(int j = 1; j < sum+1; j++) {
            if(arr[i-1] <= j) {
                dp[i][j] = Math.max(dp[i-1][j-arr[i-1]], dp[i-1][j]);
            } else {
                dp[i][j] = dp[i-1][j];
            }
        }
    }

    return dp[N][sum];
}
```
