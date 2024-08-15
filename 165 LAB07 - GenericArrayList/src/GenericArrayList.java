package src;
import java.util.List;
import java.util.ArrayList;


public class GenericArrayList<T>{
    
    private T[] data;
    private int size;

    private void resizeData() {
        T[] newArr = (T[]) new Object[data.length * 2];
        int count = 0;
        for(T x : data){
            newArr[count] = x;
            ++count;
        }
        data = newArr;
    }
    
    public GenericArrayList(int initialCapacity) {
        T[] newArr = (T[]) new Object[initialCapacity];
        data = newArr;
        size = 0;
    }

    public void add(T str) {
        if (size + 1 > data.length){
            resizeData();
        }
        data[size] = str;
        size += 1;
    }

    public void add(int index, T str) {
        if (index > data.length) return;
        if (index < 0) return;
        data[index] = str;
        size += 1;
    }

    public T get(int index) {
        if (index > data.length) return null;
        if (index < 0) return null;
        return data[index];
    }

    public void remove(int index) {
        if (index > data.length) return;
        if (index < 0) return;
        data[index] = null;
        size -= 1;
        for (int i = 0; i < size; ++i){
            data[i] = data[i + 1];
        }
        data[size] = null;
    }

    public int size() {
        return this.size;
    }

    public boolean contains(T str) {
        for (T x : data){
            if (x.equals(str)){
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }
 


    public static void main(String[] args) {
        /* PART 1:
         * Modify the GenericArrayList above so that it can store *any* class,
         * not just strings.
         * When you've done that, uncomment the block of code below, and see if
         * it compiles. If it does, run it. If there are no errors, you did
         * it right!
         */

        GenericArrayList<Point> pointList = new GenericArrayList<Point>(2);

        pointList.add(new Point(0, 0));
        pointList.add(new Point(2, 2));
        pointList.add(new Point(7, 0));
        pointList.add(new Point(19.16f, 22.32f));

        pointList.remove(0);
        Point p = pointList.get(2);

        if (p.x != 19.16f && p.y != 22.32f) {
            throw new AssertionError("Your GenericArrayList compiled properly "
            + "but is not correctly storing things. Make sure you didn't "
            + "accidentally change any of your ArrayStringList code, aside "
            + "from changing types.");
        }

        GenericArrayList<Float> floatList = new GenericArrayList<Float>(2);

        for (float f = 0.0f; f < 100.0f; f += 4.3f) {
            floatList.add(f);
        }

        float f = floatList.get(19);

        System.out.println("Hurray, everything worked!");
    }
}

