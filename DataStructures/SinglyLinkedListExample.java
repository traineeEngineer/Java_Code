package DataStructures;


public class SinglyLinkedListExample {
	
	private ListNode head;
	
	//Implementation
	private static class ListNode{
		private int data;
		private ListNode next;
		
		public ListNode(int data) {
			this.data=data;
			this.next=null;
			
		}
	}
	static void printList(ListNode head) {
		ListNode current=head;
		while(current!=null) {
			System.out.print(current.data+"-->");
			current=current.next;
		}
		System.out.print("null");
	}
	
	public  int countElements() {
		if(head==null) {
			return 0;
		}
		int count =0;
		ListNode current=head;
		while(current!=null) {
			count++;
		    current=current.next;
		}
		return count; 
	}
	
	public void insertAtBeggining(int value) {
		ListNode newNode=new ListNode(value);
		newNode=head;
		head=newNode;
		
	}

	public static void main(String[] args) {
	SinglyLinkedListExample list=new SinglyLinkedListExample();
	list.head=new ListNode(10);
	ListNode second=new ListNode(1);
	ListNode third=new ListNode(8);
	ListNode fourth=new ListNode(11);
	
	//form a chain connect all nodes
	 list.head.next=second;
	 second.next=third;
	 third.next=fourth;
     printList(list.head);

     System.out.println("length :"+list.countElements());
     list.insertAtBeggining(12);
     
	}

}
