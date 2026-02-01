# [DSA](../README.md) / Arrays

-   [Longest SubArray with sum K](longestSubarrayWithSumK.md)
-   [Total Subarray count with sum K](subarrayCountWithSumK.md)
-   [Generate all subSequences](./allSubSequences.md)


## Total Subarrays in an Array

A subarray is a contiguous part of an array.  
To form a subarray, we choose a starting index `l` and an ending index `r` such that `0 ≤ l ≤ r < n`.

- For a fixed starting index `l`, there are `(n - l)` possible subarrays.
- Therefore, the total number of subarrays in an array of size `n` is:

$$
\sum_{l=0}^{n-1} (n - l) = \frac{n(n + 1)}{2}
$$

- Hence, an array of length `n` contains exactly **`n(n + 1) / 2`** subarrays.




## Total Number of Subarrays Containing the *i*-th Element

- Let  
  - `a = (i + 1)` be the number of subarrays starting from the left that **end at the i-th index**.  
  - `b = (n - i)` be the number of subarrays starting from the right that **start at the i-th index**.

- There are also subarrays that neither start nor end at the i-th index but still contain it. However, these do not need to be counted separately. You can think of them as the same subarrays counted in `a`, but with a different ending point instead of the i-th position.

- Think of it this way:  
  Every subarray counted in `a` ends exactly at the i-th index. Each of these subarrays can also be extended to end at any index in the range `[i, n - 1]`.  
  Since there are `(n - i)` possible ending positions, each of the `(i + 1)` left subarrays can be paired with these endings.

- Therefore, the total number of subarrays that contain the i-th element is:

$$
(i + 1) \times (n - i) = a \times b
$$


