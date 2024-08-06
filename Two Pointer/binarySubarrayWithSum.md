# [TwoPointer](./twoPointer.md) / Binary Subarray with given Sum

Given a binary array `nums` and an integer `goal`, return the number of non-empty subarrays with a sum `goal`.

A subarray is a contiguous part of the array.

-   [Youtube Link to Striver](https://www.youtube.com/watch?v=XnMdNUkX6VM)
-   [Leet Code Problem Link](https://leetcode.com/problems/binary-subarrays-with-sum/description/)

## Brute Force Approach

-   Generate all subarrays and check if the sum is equal to the given goal.

## Better - Hashing Approach

-   Arrays / [Count of Subarray with Sum equals K](../Arrays/subarrayCountWithSumK.md)
-   Use a hashmap to store the sum and number of times it appeared -> `{sum : count}`.
-   We know the `sum` till `r` pointer, we want to find a subarray with sum `k`, or we want to find the subarray with `sum - k` as `sum`.
-   `sum = k + (sum - k)`
-   The total number of subarrays with `sum` as `sum - k`, is also the total number of subarrays with `sum = k`.

```java
public int subarraySum(int[] nums, int k) {
    HashMap<Integer, Integer> hmap = new HashMap<>();
    hmap.put(0, 1); // 0 sum appeared 1 time i.e if no element is selected

    int count = 0;
    int sum = 0;
    for(int i = 0; i < nums.length; i++) {
        sum += nums[i];

        int req = sum - k;
        if(hmap.containsKey(req)) {
            count += hmap.get(req);
        }

        int c = hmap.getOrDefault(sum, 0);
        hmap.put(sum, c+1);
    }
    return count;
}
```

## Optimal - Two Pointer Approach

-   Write a function to find the `count` of all subarrays with sum less than or equal to `goal`.
-   Now the count of all the subarrays with sum as goal can be found out by using `count(nums, goal) = count(nums, goal) - count(nums, goal-1)`.

```java
public int sumLessOrEqual(int[] nums, int goal) {
    if(goal < 0) return 0;

    int count = 0;
    int sum = 0;
    int l = 0;
    for(int r = 0; r < nums.length; r++) {
        sum += nums[r];

        while(sum > goal) {
            sum -= nums[l++];
        }

        count += r - l + 1;
    }
    return count;
}
```
