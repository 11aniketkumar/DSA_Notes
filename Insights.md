## Number of Subarrays

-   How many subarrays of size `1` can exist in an array of size `n`? There are exactly `n` such subarrays.
-   For subarrays of size `2`, there are `n - 1` possible subarrays. Similarly, there are `n - 2` subarrays of size `3`.

So, we can keep decrementing `n` till `0`, i.e., how many subarrays of size `n + 1` exist in the array, the answer is `n - n` or `0`, its obvious after all the size of the array is `n`, it can have zero subarray with size greater than `n`.

The total number of subarrays is given by:

$$n + (n - 1) + (n - 2) + (n - 3) + \ldots + (n - n)$$

In other words, the count is:

$$n + (n + n + n + \ldots + n) - (1 + 2 + 3 + \ldots + n)$$

For each term in the second set of parentheses, we have an `n` in the first set of parentheses, and one additional `n` at the beginning. Thus, we have `n + 1` occurrences of `n`.

So,

$$\text{count} = n(n + 1) - (1 + 2 + 3 + \ldots + n)$$

We know the formula for the sum of the first `n` positive integers is:

$$1 + 2 + 3 + \ldots + n = \frac{n(n + 1)}{2}$$

Therefore,

$$\text{count} = n(n + 1) - \frac{n(n + 1)}{2}$$

Combining terms and simplifying gives us:

$$\text{Number of subarrays} = \frac{n(n + 1)}{2}$$

<br><br><br>


## Bezout's Theorem

Bezout's Theorem states that if `a` and `b` are integers and they are coprime (i.e., their greatest common divisor is 1), then for any integer `m`, there exist integers `x` and `y` such that:

$$ ax + by = m $$

In other words, every integer `m` can be expressed as a linear combination of `a` and `b` if and only if `a` and `b` are coprime.

## Chicken McNugget Theorem

The Chicken McNugget Theorem applies to two positive integers `m` and `n` that are coprime. It states that the greatest number that cannot be expressed as a non-negative integer combination of `m` and `n` is given by:

$$ mn - m - n $$

This theorem specifically requires that `m` and `n` be coprime. If `m` and `n` are not coprime, there are infinitely many numbers that cannot be expressed as a combination of `m` and `n`.

<br><br><br>


## LCM and GCD

-   The product of the least common multiple (LCM) of `a` and `b` and the greatest common divisor (GCD) of `a` and `b` is equal to the product of `a` and `b`.
-   The GCD of any number and `1` is always `1`.
-   The LCM of any number and `1` is always that number itself.

<br>

- **THEOREM:** If `d` divides both `a` and `b`, then `d` will also divide both `a + b` and `a - b` (assuming `a > b`).

  **Proof:** Let `a` and `b` be integers such that `d` divides both `a` and `b`. This means there exist integers `k` and `l` such that:
<br>
  
  $$a = d \cdot k \quad \text{and} \quad b = d \cdot l$$

  $$a + b => (d \cdot k + d \cdot l) => d \cdot (k + l)$$
  
  $$a - b => (d \cdot k - d \cdot l) => d \cdot (k - l)$$
  

  Thus, if \( d \) divides both \( a \) and \( b \), it will also divide both their sum and their difference (when \( a > b \)).



<br><br><br>


## Prime Numbers

## Factors

-   To find all factors of a number, you only need to check up to the square root of the number.
-   When trying to determine the highest number of consecutive factors, start checking from `1`. The point where consecutive factors break will be the answer. There can't be consecutive factors greater than the current count.

## Remainder

-   If two numbers `a` and `b` give the same remainder when divided by a divisor, and if `a >= b`, then the remainder when `a - b` is divided by the same divisor will be the same as the original remainder.
