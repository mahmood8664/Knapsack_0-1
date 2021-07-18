# Packaging Challenge Assignment
## Introduction
Package challenge is about putting some items in a package with weight limit. Each item has
parameters such as index, weight and cost. The goal is to determine which things to put into the 
package so that the total weight is less than or equal to the package limit, and the total cost
is as large as possible.
## Solution Description
This problem is a famous problem in computer science. It is 0-1 
[Knapsack problem](https://en.wikipedia.org/wiki/Knapsack_problem).
You can find more information about it at this [website](https://www.javatpoint.com/0-1-knapsack-problem).
<br/>
There are different kind of solution for this problem but the approach taken into consideration in 
this assignment is [Dynamic Programming](https://en.wikipedia.org/wiki/Dynamic_programming) approach. 
## Complexity
0-1 knapsack problem is NP-complete problem thus there is no known algorithm both correct and fast.
The complexity of 0-1 knapsack problem by dynamic programming approach is O(nW) which n is the 
number of items and W is the total weight limit. 
## Prerequisites
1. JKD version: 11
2. Maven (for building and packaging): version 3+
## How to use
First you have to Build library by using mvn command. For installing it in you local maven repository run following 
command:

```bash
mvn package install
```

For deploying library into maven local repository server run the following command:  

```bash
mvn package deploy
```

After deploying library successfully import library into your project: 

```xml
<dependency>
    <artifactId>implementation</artifactId>
    <groupId>com.mobiquity</groupId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

Finally, after importing library into your project, use static method `pack` in `Packer` class:

```java
String packResponse = Packer.pack("/path/to/file");
```
It takes absolute path to input file and will return solutions as a String.
### Input file format

API accepts as its first argument a path to a filename. The input file contains several lines.
Each line is one test case. Each line contains the weight that the package can take 
(before the colon), and the list of items you need to choose. Each item is enclosed in parentheses 
where the 1st number is an item’s index number, the 2nd is its weight, and the 3rd is its cost. E.g.
```java
81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)
8 : (1,15.3,€34)
75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)
56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)
```

### Output Format
For each set of items that you put into a package provide a new row in the output string (items’ index
numbers are separated by comma). E.g.
```java
4
-
2,7
8,9
```
Method throws an APIException if incorrect parameters are being passed or format of file is not correct.
### Additional Constraints
1. Max weight that a package can take is ≤ 100
2. There might be up to 15 items you need to choose from
3. Max weight and cost of an item is ≤ 100
##Suggestions
This approach is not scalable. It cannot handle large size files because it tries to load all problem at once.
To handle large files, the packer function needs to return Stream of strings (or something like that) and called 
should wait for answers. 