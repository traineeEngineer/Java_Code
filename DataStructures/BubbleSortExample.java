package DataStructures;

public class BubbleSortExample {
	
	  static void sort(int arr[]) {
		  for(int i=0;i<arr.length-1;i++) {
			  for(int j=0;j<arr.length-1;j++) {
				  if(arr[j]>arr[j+1]) {
					  int temp=arr[j];
					  arr[j]=arr[j+1];
					  arr[j+1]=temp;
				  }
			  }
		  }
	  }
	 public static void main(String[] args) {
		 int arr[] = { 64, 34, 25, 12, 22, 11, 90 };
		 sort(arr);
		 
		 for(int i=0;i<arr.length;i++) {
			 System.out.println(arr[i]+" ");
		 }
	}
}
