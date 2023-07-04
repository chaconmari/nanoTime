package selection;
/**
 * @author Luc Longpre
 */

public class Selection {
    static int threshold = 500000;
    static int size = 1000000;

    public static void main(String[] args) {
        int[] a = new int[size];
        for (int i=0;i<size;i++)
            a[i]=(int)(Math.random()*1000);
        int index = (int)(Math.random()*size);
        System.out.println("input array");
        for (int i=0;i<size;i++)
            System.out.println(a[i]);
        long lStartTime = System.nanoTime();
        //int result = linearSelection(a,0,size-1,index);
        mergeSort(a, 0, size-1);
        long lEndTime = System.nanoTime();
        long output = lEndTime - lStartTime;
        System.out.println("Elapsed time in milliseconds: " + output / 1000000);

        //System.out.println("result: index "+index+" value "+a[index]);
        //System.out.println("final array");
        //for (int i=0;i<size;i++)
            //System.out.println(a[i]);
    }
    public static void merge(int[]a, int p, int q, int r){
        // merges subarrays a[p..q] and a[q+1..r]
        int n1=q-p+1;
        int n2=r-q;
        int[] left = new int[n1];
        int[] right = new int[n2];
        for (int i=0; i<n1; i++)
            left[i]=a[p+i];
        for (int i=0; i<n2; i++)
            right[i]=a[q+i+1];
        int i=0;
        int j=0;
        int k=p;
        while (i<n1 && j<n2)
            if (left[i]<=right[j])
                a[k++]=left[i++];
            else
                a[k++]=right[j++];
        while (i<n1)
            a[k++]=left[i++];
        while (j<n2)
            a[k++]=right[j++];
    }
    public static void insertionSort(int[] a, int p, int r){
        // sorts subarray a[p..r]
        for (int j=p+1; j<=r; j++){
            int k=a[j];
            int i=j-1;
            while (i>=p && a[i]>k){
                a[i+1]=a[i];
                i--;
            }
            a[i+1]=k;
        }
    }
    public static void mergeSort(int[] a, int p, int r){
        // sorts subarray a[p..r]
        if (p<r){
            int q=(p+r)/2;
            mergeSort(a,p,q);
            mergeSort(a,q+1,r);
            merge(a,p,q,r);
        }
    }
    public static int selectBySorting(int[] a, int p, int r, int i){
        // sorts subarray a[p..r] and returns i
        mergeSort(a,p,r);
        return i;
    }
    public static int partition(int[] a, int p, int r){
        // partitions array a[p..r] using pivot in a[r]
        // returns ending position of pivot
        int x=a[r];
        int i=p-1;
        int temp; // for exchanging
        for (int j=p; j<r; j++)
            if (a[j]<=x){
                i++;
                //exchange a[i] and a[j]
                temp = a[i];
                a[i]=a[j];
                a[j]=temp;
            }
        i++;
        //exchange a[i] and a[r]
        temp = a[i];
        a[i]=a[r];
        a[r]=temp;
        return i;
    }
    public static int linearSelection(int[] a, int p, int q, int index) {
        // performs selection in linear time
        // sets a[index] to the value it would be if subarray a[p..q] were sorted
        // returns index
        int n=q-p+1;
        if (n < threshold)
            return selectBySorting(a,p,q,index);
        else {
            int ngroups = n/5;
            int[] medians = new int[ngroups];
            for (int i=0; i<ngroups; i++){
                insertionSort(a,p+i*5,p+i*5+4);
                medians[i]=a[p+i*5+2];
            }
            int pivot = linearSelection(medians,0,ngroups-1,ngroups/2);
            int value=medians[pivot];
            int k=p;
            while (value!=a[k])
                k++;
            //exchange to put pivot at the end
            int temp=a[k];
            a[k]=a[q];
            a[q]=temp;
            k=partition(a,p,q);
            if (index==k)
                return k;
            if (index<k)
                return linearSelection(a,p,k-1,index);
            else
                return linearSelection(a,k+1,q,index);
        }
    }
}
