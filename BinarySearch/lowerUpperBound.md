# [Binary Search](BinarySearch.md) / Upper and Lower Bound

## Lower Bound

-   Finds the smallest position with a value greater than or equal to `target`.
-   If the `target` exists, it returns the index of the first occurrence of `target`.
-   If the `target` does not exist, it returns the index where it could be inserted.

```java
public static int lowerBound(int[] arr, int x) {
    int low = 0, high = arr.length;
    while (low < high) {
        int mid = low + (high - low) / 2;
        if (arr[mid] < x)
            low = mid + 1;
        else
            high = mid;
    }
    return low;
}
```

## Upper Bound

-   Finds the smallest position with a value greater than `target`.
-   If the `target` exists, it returns the index of the first element that is greater than `target`.
-   If the `target` does not exist, it returns the index where a greater element could be inserted.

```java
public static int upperBound(int[] arr, int x) {
    int low = 0, high = arr.length;
    while (low < high) {
        int mid = low + (high - low) / 2;
        if (arr[mid] <= x)
            low = mid + 1;
        else
            high = mid;
    }
    return low;
}
```
