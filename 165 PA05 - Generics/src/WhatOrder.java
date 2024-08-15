import java.util.Scanner;

public class WhatOrder <T>{
   // TODO: Define a generic method called checkOrder() that 
   //       takes in four variables of generic type as arguments. 
   //       The return type of the method is integer
   //private T generic;

   public static <T extends Comparable<T>> int checkOrder(T var1, T var2, T var3, T var4){
      // Check the order of the input: return -1 for ascending, 
      // 0 for neither, 1 for descending
      if (var1.compareTo(var2) < 0 && var2.compareTo(var3) < 0 && var3.compareTo(var4) < 0){
         return -1;
      } else if (var1.compareTo(var2) > 0 && var2.compareTo(var3) > 0 && var3.compareTo(var4) > 0){
         return 1;
      } else {
         return 0;
      }
   }


   public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);

      // Check order of four strings
      System.out.println("Order: " + checkOrder(scnr.next(), scnr.next(), scnr.next(), scnr.next())); 

      // Check order of four doubles
      System.out.println("Order: " + checkOrder(scnr.nextDouble(), scnr.nextDouble(), scnr.nextDouble(), scnr.nextDouble()));
   }
}
