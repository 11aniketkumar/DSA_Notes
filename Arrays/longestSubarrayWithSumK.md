# [Arrays](array.md) / Longest Subarray with sum K

Find the length of the longest subarray in the array with the sum `K`.

## Brute Force Approach

-   Time Complexity -> O(n<sup>2</sup>)
-   Space Complexity -> O(1)

```java
public static int lenOfLongSubarrBruteForce(int[] A, int N, int K) {
    int max_len = 0;

    for (int i = 0; i < N; i++) {
        int sum = 0;
        for (int j = i; j < N; j++) {
            sum += A[j];

            if (sum == K) {
                max_len = Math.max(max_len, j - i + 1);
            }
        }
    }

    return max_len;
}

```

## Better - Hashing Approach

-   Time Complexity -> O(n)
-   Space Complexity -> O(n)

```java
public static int lenOfLongSubarr (int A[], int N, int K) {
    HashMap<Integer, Integer> hmap = new HashMap<>();
    int max_len = 0;

    int sum = 0;
    for(int i = 0; i < N; i++) {
        sum += A[i];

        if(sum == K) {
            max_len = i+1;
        }

        if(hmap.containsKey(sum - K)) {
            int idx = hmap.get(sum - K);
            int len = i - idx;
            max_len = Math.max(len, max_len);
        }

        if(!hmap.containsKey(sum)) {
            hmap.put(sum, i);
        }
    }
    return max_len;
}
```

## Best - Two Pointer Approach

<b>NOTE : </b> Only valid for positive with zero numbers.

-   Time Complexity : O(n)
-   Space Complexity : O(1)

```java
public static int lenOfLongSubarr (int A[], int N, int K) {
    int max_len = 0;

    int sum = 0;
    int l = 0;
    for(int r = 0; r < N; r++) {
        sum += A[r];

        if (sum > K) {
            sum -= A[l++];
        }
        if(sum == K) {
            max_len = Math.max(max_len, r-l+1);
        }
    }
    return max_len;
}
```
