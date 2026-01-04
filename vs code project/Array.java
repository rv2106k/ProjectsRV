 public class Array{
    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5,6};
        // System.out.println(arr[2]);

        // for(int i=0; i<arr.length;i++){
        //     System.out.println(arr[i]);
        // }

        for(int value: arr){
            System.out.print(value+ " ");
        }
    }
}