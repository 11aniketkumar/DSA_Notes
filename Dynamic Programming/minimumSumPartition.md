# Minimum Sum Partition

[Dynamic Programming](DynamicProgramming.md)

-   Given an array arr of size n containing non-negative integers, the task is to divide it into two sets S1 and S2 such that the absolute difference between their sums is minimum and find the minimum difference
-   [GFG Problem Link](https://www.geeksforgeeks.org/problems/minimum-sum-partition3317/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card)

## Intuition

-   If subset sum `x` is possible than the remaining items will automatically form a subset with sum equal to `sum - x` (let's call it `y`).
-   We need to find the max possible subset sum `x` i.e less than or equal to half of sum of array. The more closer `x` is to the mid point, the smaller will be difference between `x` and `y`.
-   The final difference can be given as:

    $$
    \text{diff} = x - y
    $$

    but,

    $$
    y = \text{sum} - x
    $$

    Therefore,

    $$
    \text{diff} = x - (\text{sum} - x)
    $$

    $$
    \text{diff} = 2x - \text{sum}
    $$

    Since we know \( x \) will always be less than or equal to half of the sum, twice of \( x \) will also be less than or equal to the total sum at most. Therefore, the sum is always bigger than or equal to twice \( x \).

    We need the absolute value of the difference:

    $$
    \text{diff} = \text{sum} - 2x
    $$

    `sum - 2x`, will be minimum for the greatest value of `x`, i.e `mid` point, to minimize `sum - 2x` means to maximize `x` as much as possible then remove `2x` from sum.

## Memoization

-   It doesn't fill entire dp table and this solution uses the last row of dp array.
-   To do it using memoization, I might have to call function manually for each and every column in first half of last row.

## Tabulation

-   Last row will give me all possible value of `x`, basically it means using all elements of the array which `sum` are possible and which are not.
-   Read [target sum](./targetSum.md) code, there a more direct approach is used to update boolean values.

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
