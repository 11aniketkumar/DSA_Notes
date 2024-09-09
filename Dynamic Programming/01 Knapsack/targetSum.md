# Target Sum

[Dynamic Programming](../DynamicProgramming.md)

You are given an integer array `nums` and an integer `target`.

You want to build an expression out of `nums` by adding one of the symbols `'+'` and `'-'` before each integer in `nums` and then concatenate all the integers.

For example, if `nums = [2, 1]`, you can add a `'+'` before 2 and a `'-'` before 1 and concatenate them to build the expression `"+2-1"`.
Return the number of different expressions that you can build, which evaluates to `target`.

[LeetCode Problem Link](https://leetcode.com/problems/target-sum/description/)

## Intuition

Let:

-   $x$ be the sum of the first subset.
-   $y$ be the sum of the second subset.
-   $\text{sum}$ be the sum of the entire array.
-   $\text{diff}$ be the difference between the two subset sums.

The given difference can be expressed as:

$$
x - y = \text{diff} \tag{1}
$$

We also know that:

$$
x + y = \text{sum} \tag{2}
$$

By adding equation (1) and equation (2), we get:

$$
(x - y) + (x + y) = \text{diff} + \text{sum}
$$

Simplifying:

$$
2x = \text{sum} + \text{diff}
$$

Solving for $x$:

$$
x = \frac{\text{sum} + \text{diff}}{2}
$$

Now, we need to count the number of subsets with sum $x$. For every subset with sum $x$, there will be a corresponding subset with sum $y$.

## Memoization

```java
public int count(int[][] dp, int[] nums, int sum, int n) {
    if((n == 0) || (sum < 0)) {
        if(sum == 0) {
            return dp[n][sum] = 1;
        }
        return 0;
    }

    if(dp[n][sum] != -1) {
        return dp[n][sum];
    }

    int include = count(dp, nums, sum - nums[n-1], n-1);
    int exclude = count(dp, nums, sum, n-1);
    return dp[n][sum] = include + exclude;
}
```

Driver function

```java
public int findTargetSumWays(int[] nums, int target) {
    int n = nums.length;
    int sum = 0;
    for(int i = 0; i < n; i++) {
        sum += nums[i];
    }

    if(target < 0) target *= -1;    // s1 - s2 = -1 * (s2 - s1)
    int required_sum = (sum + target) / 2;

    if((sum + target) % 2 != 0) {   // if required_sum is a fraction
        return 0;
    }

    int[][] dp = new int[n + 1][required_sum + 1];
    for(int i = 0; i < n+1; i++) {
        Arrays.fill(dp[i], -1);
    }

    return count(dp, nums, required_sum, n);
}
```

## Tabulation

-   `j` starts from `0` since `nums[i]` can be `0` which means there exists more than `1` way to achieve `sum = 0`, i.e we can either not include anything or include one or more zeroes in empty set.
-   Thus, now ways in which `j = 0` or `sum = 0` can be achieved needs to be re calculated.

```java
public int findTargetSumWays(int[] nums, int target) {
    int n = nums.length;
    int sum = 0;
    for(int i = 0; i < n; i++) {
        sum += nums[i];
    }

    if(target < 0) target *= -1;    // s1 - s2 = -1 * (s2 - s1)
    int required_sum = (sum + target) / 2;

    if((sum + target) % 2 != 0) {   // if required_sum is a fraction
        return 0;
    }

    int[][] dp = new int[n + 1][required_sum + 1];

    // there is always 1 way to achieve sum = 0 by not including anything
    for(int i = 0; i < n+1; i++) {
        dp[i][0] = 1;
    }

    for(int i = 1; i < n+1; i++) {
        for(int j = 0; j < required_sum+1; j++) {
            int val = nums[i-1];
            if(val <= j) {
                dp[i][j] = dp[i-1][j-val] + dp[i-1][j];
            } else {
                dp[i][j] = dp[i-1][j];
            }
        }
    }
    return dp[n][required_sum];
}
```
