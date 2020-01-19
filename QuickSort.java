
public class QuickSort implements SortInterface {

    private int count;
    private long time;

    /* I used: https://www.geeksforgeeks.org/quick-sort/
    * for the recursive algorithm although
    * instead of choosing the last element
    * as pivot I used the median of 3
    * method found here: https://examples.javacodegeeks.com/core-java/quicksort-algorithm-in-java-code-example/
    **/
    @Override
    public void recursiveSort(int[] list) throws UnsortedException {
        count = 0;
        time = 0;

        long startTime = System.nanoTime();

        list = recursive(list, 0, list.length-1);

        long endTime = System.nanoTime();
        time = (endTime - startTime);

        if (isNotSorted(list)) {
            throw new UnsortedException();
        }
    }

    private int[] recursive(int[] list, int low, int high) {
        if (low < high) 
        { 
            /* pi is partitioning index, arr[pi] is  
              now at right place */
            int pi = partitionRecurse(list, low, high); 
  
            // Recursively sort elements before 
            // partition and after partition 
            recursive(list, low, pi-1); 
            recursive(list, pi+1, high); 
        } 

        return list;
    }

    private int partitionRecurse(int arr[], int low, int high) { 
        int pivot = getMedian(arr, low, high);  
        int i = (low-1); // index of smaller element 
        for (int j=low; j<high; j++) 
        { 
            // If current element is smaller than the pivot 
            if (arr[j] < pivot) 
            { 
                i++; 
  
                // swap arr[i] and arr[j] 
                int temp = arr[i]; 
                arr[i] = arr[j]; 
                arr[j] = temp;
            }
        } 
  
        // swap arr[i+1] and arr[high] (or pivot) 
        int temp = arr[i+1]; 
        arr[i+1] = arr[high]; 
        arr[high] = temp; 
  
        return i+1; 
    }

    private int getMedian(int arr[], int left, int right) {
        int center = (left+right)/2;
         
        if(arr[left] > arr[center])
            swap(arr, left, center);
         
        if(arr[left] > arr[right])
            swap(arr, left, right);
         
        if(arr[center] > arr[right])
            swap(arr, center, right);
         
        swap(arr, center, right);
        return arr[right];
    }

    private void swap(int arr[], int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
        count++;
    }

    /* I used: https://www.geeksforgeeks.org/iterative-quick-sort/
    * for the iterative algorithm although
    * instead of choosing the last element
    * as pivot I used the median of 3
    * method found here: https://examples.javacodegeeks.com/core-java/quicksort-algorithm-in-java-code-example/
    **/
    @Override
    public void iterativeSort(int[] list) throws UnsortedException {
        count = 0;
        time = 0;

        long startTime = System.nanoTime();

        list = iterative(list, 0, list.length-1);

        long endTime = System.nanoTime();
        time = (endTime - startTime);

        if (isNotSorted(list)) {
            throw new UnsortedException();
        }
    }

    private int[] iterative(int arr[], int l, int h) { 
        // Create an auxiliary stack 
        int[] stack = new int[h - l + 1]; 
  
        // initialize top of stack 
        int top = -1; 
  
        // push initial values of l and h to stack 
        stack[++top] = l; 
        stack[++top] = h; 
  
        // Keep popping from stack while is not empty 
        while (top >= 0) { 
            // Pop h and l 
            h = stack[top--]; 
            l = stack[top--]; 
  
            // Set pivot element at its correct position 
            // in sorted array 
            int p = partitionIterate(arr, l, h); 
  
            // If there are elements on left side of pivot, 
            // then push left side to stack 
            if (p - 1 > l) { 
                stack[++top] = l; 
                stack[++top] = p - 1; 
            } 
  
            // If there are elements on right side of pivot, 
            // then push right side to stack 
            if (p + 1 < h) { 
                stack[++top] = p + 1; 
                stack[++top] = h; 
            } 
        } 
        return arr;
    }

    private int partitionIterate(int arr[], int low, int high) 
    { 
        int pivot = getMedian(arr, low, high);
  
        // index of smaller element 
        int i = (low - 1); 
        for (int j = low; j <= high - 1; j++) { 
            // If current element is smaller than or 
            // equal to pivot 
            if (arr[j] <= pivot) { 
                i++; 
  
                // swap arr[i] and arr[j] 
                int temp = arr[i]; 
                arr[i] = arr[j]; 
                arr[j] = temp;
                //count++;
            } 
        } 
  
        // swap arr[i+1] and arr[high] (or pivot) 
        int temp = arr[i + 1]; 
        arr[i + 1] = arr[high]; 
        arr[high] = temp; 
  
        return i + 1; 
    } 

    /**
     * Checks if the list has been sorted
     */
    private boolean isNotSorted (int[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            if (list[i] <= list[i+1]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public long getTime() {
        return time;
    }
}