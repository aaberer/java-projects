public class MyFunctions implements TestingFunctions{

    @Override
    public int greatestCommonDivisor(int a, int b) {
        if (a < 0){return -1;} // check for negative values
        if (b < 0){return -1;}
        a = Math.abs(a);
        b = Math.abs(b);
        if (b == 0) {return a;} // check if vars are 0, then return other var bc no gcd
        else if (a == 0){return b;}
        int divisor = 0;
        int remainder = 0;
        do {
            remainder = a % b; 
            divisor = b;
            b = remainder;
            a = divisor;
        }
        while (remainder != 0);
        return a;
    }
    /**
	 * This method reverses the subsection of the passed array defined by index1 
	 * and index2. index2 is non-inclusive and will not be swapped with index1. If
	 * index1 > index2 then the two indexes will be swapped before they are checked
	 * for validity.
	 * @param arr - the array to reverse
	 * @param index1 - the first index of the subsection
	 * @param index2 - the non-inclusive upper bound of the subsection, 
	 * last element swapped will be at the index (index2 - 1)
	 * @throws IndexOutOfBoundsException if index1 or (index2-1) are not valid indexes of arr
	 */
    @Override
    public void reverseWindow(int[] arr, int index1, int index2) throws IndexOutOfBoundsException {
        if (index1 > index2){
            int temp;
            temp = index1;
            index1 = index2;
            index2 = temp;
        }
        if (index2 > arr.length) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (arr.length < 0 || index1 < 0 || index2 < 0){
            throw new NegativeArraySizeException("Negative index or array size");
        }
        if (index1 - 1 > index2){
            return;
        }
        if (index1 == index2){return;}
        int[] newArr = arr;
        if (arr.length != 0){ 
            for (int i = index1, j = index2 - 1; i < newArr.length - 2; i++){
                int temp;
                temp = newArr[i];
                arr[i] = newArr[j];
                arr[j] = temp;
                j--;
            }
        } 
        return;
    }
}
