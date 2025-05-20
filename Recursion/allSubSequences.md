# [Recursion](./recursion%20and%20backtracking.md) / Generate All subSequences

-   [Striver Youtube Link](https://www.youtube.com/watch?v=b7AYbpM5YrE)
-   [Leetcode Problem Link](https://leetcode.com/problems/subsets/)

Generate all subsequences for the given array.

-   Total subsequences => 2 <sup>k</sup>
-   Time and space complexity wise not much difference between both approaches, only difference is one is `recursive` while other is `iterative`.

## Backtracking recursive Approach

-   Time Complexity -> `O(2<sup>n</sup> . n)`
-   Space Complexity -> Recursion stack space -> `O(n)`

```java
public void generate(int[] nums, List<List<Integer>> res, List<Integer> cur, int i) {
    if(i == nums.length) {
        res.add(new ArrayList<>(cur));
        return;
    }

    // exclude condition
    generate(nums, res, cur, i+1);

    // include condition
    cur.add(nums[i]);
    generate(nums, res, cur, i+1);
    cur.remove(cur.size() - 1);
}
```

## Power Set Approach

-   Time Complexity -> `O(2<sup>n</sup> . n)`
-   Space Complexity -> `O(1)`
-   Any given array can have 2<sup>n</sup> subsequences, exactly like using n bits you can form 2<sup>n</sup> unique combinations.
-   Here also imagine, n positions that you need to fill with `0` or `1` i.e (pick that index element or not)

```java
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();

    double n = 1 << nums.length;
    for(int i = 0; i < n; i++) {
        List<Integer> cur = new ArrayList<>();
        for(int j = 0; j < nums.length; j++) {
            if((i & (1 << j)) != 0) {
                cur.add(nums[j]);
            }
        }
        res.add(cur);
    }
    return res;
}
```
