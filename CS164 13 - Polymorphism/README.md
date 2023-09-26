# Lab 13 - Polymorphism
This lab focuses on polymorphism in the Java language, any Java object that can pass more than one IS-A test is polymorphic in Java. There will be an additional section at the bottom of this document to help you in your endeavors with this Tuesday lab!

For ease of access, here is the [javadoc](https://csu-compsci-cs163-4.github.io/Lab13Polymorphism/package-summary.html).

## Pre-Step: Walkthrough & Compilation
You know the drill for Tuesday labs, start in the `main` function, located in `ZooMain`, and follow the program flow to gain an understanding as to what this program is doing. As you briefly view the classes in the Flora and Fauna folders, notice the use of the `extends` keyword in the class signature.

Once you have given your program a proper reading, try to run it and follow along with what is happening before we move on to the implementation and self-explanation section.

## Step 1: Self-Explanation
There are only two self-explanations to complete in the `ZooProcessing` class, after reading the javadocs write out your logic as to what the program should do. If you run into any difficulties during implementation, try to pseudo-code along with your self-explanation logic, this may reveal some useful insight.

## Step 2: Implementation
After your self-explanations, move onto your implementation. I would highly recommend incrementally working through these methods and test often!

## Step 3: Turning In
Once you have completely and thoroughly tested your implementations, present your self-explanations to your TA and turn it into zyBooks! Online students will not have the option to submit their self-explanations but it is still highly recommended that they complete them in order to better understand the code.

# Additional Information
## Polymorphism
Polymorphism in Java refers to how inheritance allows classes to inherit or `extend` the functionality of the specified class.

For example:
``` java
public class Car {
    public void vroom() {
        System.out.println("Vroom!!!");
    }
}

public class Ford extends Car {
    String model = "Mustang";
}

public class MainClass {
    public static void main(String[] args) {
        Ford newWhip = new Ford();
        newWhip.vroom(); //"Vroom!!!" would be printed, even though the Ford class does not have the vroom() method.
    }
}
```

So with the previous example, we can see how the `Ford` class `extends` the `Car` class and is able to use the `vroom()` method.

### instanceof
`instanceof` is a useful keyword that allows one to see if a certain class is an "instance of" a certain class. This allows us to examine an object's degree of polymorphism. Depending upon the comparison made, a `true` or `false` value is returned, making this especially useful in conditional statements.

Following our previous car example:
``` java
    public static void main(String[] args) {
        Ford newWhip = new Ford();
        Car oldWhip = new Car();

        System.out.println((newWhip instanceof Car)); //Prints "true".
        System.out.println((oldWhip instanceof Ford)); //Prints "false".
    }
```
