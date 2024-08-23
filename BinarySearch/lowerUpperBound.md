# [Binary Search](BinarySearch.md) / Upper and Lower Bound

## Lower Bound

-   Finds the smallest position with a value greater than or equal to `target`.
-   If the `target` exists, it returns the index of the first occurrence of `target`.
-   If the `target` does not exist, it returns the index where it could be inserted.

```java
public static int lowerBound(int[] arr, int num) {
    int low = 0;
    int high = arr.length - 1;

    int mid;
    while(low <= high) {
        mid = (low + high) / 2;

        if(arr[mid] >= num) {
            high = mid - 1;
        } else {
            low = mid + 1;
        }
    }

    return low;
}
```

## Upper Bound

-   Finds the smallest position with a value greater than `target`.
-   If the `target` exists, it returns the index of the first element that is greater than `target`.
-   If the `target` does not exist, it returns the index where a greater element could be inserted.

```java
public static int upperBound(int[] arr, int num) {
    int low = 0;
    int high = arr.length - 1;

    int mid;
    while(low <= high) {
        mid = (low + high) / 2;

        if(arr[mid] > num) {
            high = mid - 1;
        } else {
            low = mid + 1;
        }
    }
    return low;
}
```
