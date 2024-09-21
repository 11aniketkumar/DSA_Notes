# House Robber

[Dynamic Programming](../DynamicProgramming.md)

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

[LeetCode Problem Link](https://leetcode.com/problems/house-robber/description/)

## Memoization

```java
public int calculate(int[] dp, int[] nums, int idx) {
    if(idx == 0) return nums[0];
    if(idx < 0) return 0;

    if(dp[idx] != -1) {
        return dp[idx];
    }

    int include = nums[idx] + calculate(dp, nums, idx-2);
    int exclude = calculate(dp, nums, idx-1);
    return dp[idx] = Math.max(include, exclude);
}
```

## Tabulation

```java
 public int rob(int[] nums) {
    int[] dp = new int[nums.length];
    dp[0] = nums[0];

    for(int i = 1; i < nums.length; i++) {
        int exclude = dp[i-1];

        int include = nums[i];
        if(i - 2 >= 0) {
            include += dp[i-2];
        }
        dp[i] = Math.max(include, exclude);
    }

    return dp[nums.length - 1];
}
```

## Space Optimized Tabulation

```java
public int rob(int[] nums) {
    int first = nums[0];
    int second = 0;
    int cur;
    for(int i = 1; i < nums.length; i++) {
        int exclude = first;
        int include = nums[i] + second;

        cur = Math.max(include, exclude);

        second = first;
        first = cur;
    }

    return first;
}
```
