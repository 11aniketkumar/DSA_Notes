# [TwoPointer](./twoPointer.md) / Max Consecutive Ones

Given a binary array `nums` and an integer `k`, return the maximum number of consecutive `1`'s in the array if you can flip at most `k` `0`'s.

-   [Youtube Link to Striver](https://www.youtube.com/watch?v=3E4JBHSLpYk&list=PLgUwDviBIf0q7vrFA_HEWcqRqMpCXzYAL&index=4)
-   [Leet Code Problem Link](https://leetcode.com/problems/max-consecutive-ones-iii/description/)

<br>

<b>Trimed Question</b> - Find the max length of the subarray with at most `k` zeroes.

## Brute Force Approach

-   Generate all subarrays and check for the max number of zeroes in it.
-   Time complexity -> O(n<sup>2</sup>)

## Two Pointer approach

-   Time Complexity -> O(2n)
-   Space Complexity -> O(1)

```java
public int longestOnes(int[] nums, int k) {
    int max_len = 0;
    int count = 0;

    int l = 0;
    for(int r = 0; r < nums.length; r++) {
        if(nums[r] == 0) {
            if(count < k) {
                count++;
            } else {
                while(nums[l++] != 0);
                // it will increment once for failing condition also
            }
        }

        max_len = Math.max(max_len, r-l+1);
    }
    return max_len;
}

```

-   Above approach can be further reduced to O(n) time complexity.
-   If `count` has exceeded `k`, we will increase `l` along with `r` in each loop, so as to keep window size constant before calculating `max_len`.
-   If while increasing `l`, we encounter a zero, we will reduce the `count`.

```java
public int longestOnes(int[] nums, int k) {
    int max_len = 0;
    int count = 0;

    int l = 0;
    for(int r = 0; r < nums.length; r++) {
        if(nums[r] == 0) count++;
        if(count > k) {
            if(nums[l++] == 0) count--;
        }
        max_len = Math.max(max_len, r-l+1);
    }
    return max_len;
}
```

-   But why to calculate `max_len` for cases where `count > k`, the length will anyhow remain the same.
-   We already have an `if` statement for checking `count > k`, we will simply put `max_len` calculation in `else` part.

```java
public int longestOnes(int[] nums, int k) {
    int max_len = 0;
    int count = 0;

    int l = 0;
    for(int r = 0; r < nums.length; r++) {
        if(nums[r] == 0) count++;
        if(count > k) {
            if(nums[l++] == 0) count--;
        } else {
            max_len = Math.max(max_len, r-l+1);
        }
    }
    return max_len;
}
```
