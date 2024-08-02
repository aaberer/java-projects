package src;
import java.util.List;
import java.util.ArrayList;


public class GenericArrayListPt2 <T> {
    
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
    
    public GenericArrayListPt2(int initialCapacity) {
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
       
        GenericArrayListPt2<Point> pointList = new GenericArrayListPt2<Point>(2);
        GenericArrayListPt2<Point3D> pointList3D = new GenericArrayListPt2<Point3D>(3);

        pointList.add(new Point(0, 0));
        pointList.add(new Point(2, 2));
        pointList.add(new Point(7, 0));
        pointList.add(new Point(19.16f, 22.32f));

        pointList3D.add(new Point3D(1.0f, 2.0f, 3.0f));
        pointList3D.add(new Point3D(7.3f, 4, 0));

        Point p = pointList.get(2);
        Point3D p3 = pointList3D.get(0);

        
        //GenericArrayListPt2<Float> floatList = new GenericArrayList<Float>(2);
    }
}

