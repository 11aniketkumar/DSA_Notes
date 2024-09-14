# [DSA](./README.md) / Bit Manipulation

| Operation   | Symbol | Description                                      |
| ----------- | ------ | ------------------------------------------------ |
| AND         | &      | Returns 1 only if both operands are 1            |
| OR          | \|     | Returns 1 if at least one of the operands is 1   |
| NEGATION    | ~      | Flips all the bits                               |
| XOR         | ^      | Returns 1 if exactly one of the operands is 1    |
| LEFT SHIFT  | <<     | Shifts the bits of the left operand to the left  |
| RIGHT SHIFT | >>     | Shifts the bits of the left operand to the right |

## Number of Set Bits

```java
int count = Integer.bitCount(7453);
System.out.println(count);          // output - 8

count = Long.bitCount((long)1e12);
System.out.println(count);          // output - 13
```

-   It's better to count number of bits using the libraries but if you still have to count using custom function, use the following method instead of iterating over all bits and checking if they are `1` or not and then incrementing count.
-   This method takes `O(number of set bits)` instead of `O(number of bits)`.

```java
int n = 14; // 1110
int count = 0;
while(n > 0) {
    n = n & (n-1);  // 1110 -> 1100 -> 1000 -> 0000
    count++;
}
System.out.println(count);  // output = 3
```

## Clear the last set bit

```java
int n = 15; // 1111
for(int i = 0; i < 4; i++) {
    n = n & (n-1);          // 1110 -> 1100 -> 1000 -> 0000
    System.out.println(n); //  14  ->  12   -> 8    -> 0
}
```

<br><br>

# Usual Stuff

## Check if number is Even or Odd

```java
if((n & 1) == 0) // even

if((n & 1) != 0) // odd
```

## Swap two numbers

```java
int a = 8;
int b = 3;

a = a ^ b;
b = a ^ b;
a = a ^ b;

System.out.println(a); // 3
System.out.println(b); // 8
```

## Toggle a variable between two values

```java
int n = 3; // n will be either 3 or 7
for(int i = 0; i < 5; i++) {
    System.out.println(n);
    n = n ^ 3 ^ 5;
}
// output :: 3 5 3 5 3
```

For toggling between `1` and `0`, you can simply use `xor` operation `n = n ^ 1`.
<br><br>

# Power of 2

## Check if number is power of 2

```java
// edge case :: won't work for n = 0

int n = 1024;
if((n & (n-1)) == 0) {
    System.out.println("Yes"); // output
} else {
    System.out.println("NO");
}
```

## Multiply by 2<sup>k</sup>

```java
int n = 3;
int k = 2;
n = n << k; // multiply n by 2 power k
System.out.println(n); // output = 12
```

## Divide by 2<sup>k</sup>

```java
int n = 12;
int k = 2;
n = n >> k; // divide n by 2 power k
System.out.println(n); // output = 3
```

## X % 2<sup>k</sup>

```java
int X = 27; // example number
int k = 3;  // example power
int remainder = X & ((1 << k) - 1);
System.out.println(remainder); // output 3 (27 % 8 = 3)
```

<br><br>

# Kth Bit

## Check if kth bit is set

```java
int n = 8; // 1 0 0 0
int k = 3; // 3 2 1 0
if((n & (1 << k)) != 0) {
    System.out.println("Yes"); // output
} else {
    System.out.println("NO");
}
```

## Set the kth bit

```java
int n = 8; // 1 0 0 0
int k = 2; // 3 2 1 0
n = n | (1 << k);
System.out.println(n); // output 12
```

## Unset the kth bit

```java
int n = 8; // 1 0 0 0
int k = 3; // 3 2 1 0
n = n & ~(1 << k);
System.out.println(n); // output 0
```

## Toggle kth bit

```java
int n = 8; // 1 0 0 0
int k = 3; // 3 2 1 0
n = n ^ (1 << k);
System.out.println(n); // output 0
```
