package DataStructures;

public class InsertionSortExample {
	
	static void insertionSort(int arr[], int n) {
		for(int i=1;i<n;i++) {
			int temp=arr[i];
			int j=i-1;
			while(j>=0 && temp <arr[j]) {
			   arr[j+1]=arr[j];
			   j--;
			}
			arr[j+1]=temp;
		}
	}
	public static void main(String[] args) {
		 int[] data = { 9, 5, 1, 4, 3 };
		 insertionSort(data, 5);
		 for(int i=0;i<data.length;i++) {
			 System.out.println(data[i]+" ");
		 }
		 
	}

}
