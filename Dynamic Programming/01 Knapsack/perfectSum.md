# Perfect Sum

[Dynamic Programming](../DynamicProgramming.md)

[GFG Problem Link](https://www.geeksforgeeks.org/problems/perfect-sum-problem5633/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card)

-   Given an array arr of size n of non-negative integers and an integer sum, the task is to count all subsets of the given array with a sum equal to a given sum.

Note: Answer can be very large, so, output answer modulo 10<sup>9</sup>+7.

## Memoization

```java
public int count(int[][] dp, int arr[], int sum, int n) {
    // even if sum=0, i want it to try till last element
    // maybe last element is 0, which won't decrease sum
    if((n <= 0) || (sum < 0)) {
        if(sum == 0) {
            return 1;
        }
        return 0;
    }

    if(dp[n][sum] != -1) {
        return dp[n][sum];
    }

    int include = count(dp, arr, sum-arr[n-1], n-1);
    int exclude = count(dp, arr, sum, n-1);

    return dp[n][sum] = (include + exclude) % (int)(1e9 + 7);
}
```

## Tabulation

-   Why starting `j = 0`, - There is always atleast one way to achieve `sum = 0`, but if the array contains one or more `0`s, there can be more than one way to achieve `sum = 0`. The GFG problem range was starting from `0`. So `j = 0` needs to be calculated again.

```java
public int perfectSum(int arr[],int n, int sum)
{
    int[][] dp = new int[n+1][sum+1];

    // for any number of items, there's always a way for sum=0
    for(int i = 0; i < n+1; i++) {
        dp[i][0] = 1; // don't select any item & sum=0
    }

    // using 0 items, 1 way to achieve sum=0
    // but 0 ways to achieve any sum>0 with 0 items
    // can be skipped if you don't initialize the array with -1
    // entire 1st row is 0 except for 1st column

    for(int i = 1; i < n+1; i++) {
        for(int j = 0; j < sum+1; j++) {
            if(arr[i-1] <= j) {
                dp[i][j] = (dp[i-1][j] + dp[i-1][j-arr[i-1]]) % (int)(1e9 + 7);
            } else {
                dp[i][j] = dp[i-1][j];
            }
        }
    }

    return dp[n][sum];
}

```
