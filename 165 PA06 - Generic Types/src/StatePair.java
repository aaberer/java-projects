public class StatePair <Type1 extends Comparable<Type1>, Type2 extends Comparable<Type2>> {
   private Type1 arbv;
   private Type2 name;
   
   public StatePair(Type1 var1, Type2 var2){
        value1 = var1;
        value2 = var2;
   }

   public Type1 getValue1(){
        return value1;
   }

   public Type2 getValue2(){
        return value2;
   }

   public void setValue1(Type1 var){
        this.value1 = var;
   }

   public void setValue2(Type2 var){
        this.value2 = var;
   }
   
    public void printInfo(){
         System.out.println(value1 + ": " + value2);
    }
}