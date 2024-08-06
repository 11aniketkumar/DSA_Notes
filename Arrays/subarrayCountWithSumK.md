# [Arrays](array.md) / Count of Subarray with Sum equals K

Given an array of integers `nums` and an integer `k`, return the total number of subarrays whose sum equals to `k`.

A subarray is a contiguous non-empty sequence of elements within an array.

-   [LeetCode Problem Link](https://leetcode.com/problems/subarray-sum-equals-k/description/)
-   [Striver Video Link](https://www.youtube.com/watch?v=xvNwoz-ufXA&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=33)

## Brute Force Approach

-   Generate all subarrays and increment count if condition satisfies.

## Better - Hashing Approach

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
