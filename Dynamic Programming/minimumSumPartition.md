# Minimum Sum Partition

[Dynamic Programming](DynamicProgramming.md)

-   Given an array arr of size n containing non-negative integers, the task is to divide it into two sets S1 and S2 such that the absolute difference between their sums is minimum and find the minimum difference
-   [GFG Problem Link](https://www.geeksforgeeks.org/problems/minimum-sum-partition3317/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card)

## Memoization

-   It doesn't fill entire dp table and this solution uses the last row of dp array.
-   To do it using memoization, I might have to call function manually for each and every column in first half of last row.

## Tabulation

```java
public int minDifference(int arr[], int n)	{
    int sum = 0;
    for(int i = 0; i < n; i++) {
        sum += arr[i];
    }

    int range = sum / 2;

    boolean[][] dp = new boolean[n+1][range+1];


    // initialization
    for(int i = 0; i < n+1; i++) {
        for(int j = 0; j < range + 1; j++) {
            if(i == 0) {
                dp[i][j] = false;
            }
            if(j == 0) {
                dp[i][j] = true;
            }
        }
    }

    // filling the dp table
    for(int i = 1; i < n+1; i++) {
        for(int j = 0; j < range + 1; j++) {
            if(arr[i - 1] <= j) {
                dp[i][j] = dp[i-1][j - arr[i-1]] || dp[i-1][j];
            } else {
                dp[i][j] = dp[i-1][j];
            }
        }
    }

    // finding the max index in first half of last row
    int max = -1;
    for(int j = 0; j < range + 1; j++) {
        if(dp[n][j]) {
            max = j;
        }
    }

    return sum - (2 * max);
}
```
