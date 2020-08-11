package com.driver;

public class DLL {

	public class ShopNode {
		public int itemCode;
		public String name;
		public double price;
		public ShopNode next, prev;
		public ShopNode(int i, String s, double d, ShopNode n, ShopNode p) { itemCode = i; name = s; price = d; next = n; prev = p;
		}
		public ShopNode(int i, String s, double d) {
			this(i, s, d, null, null);
		} 
	}

	private ShopNode head;
	private ShopNode tail;

	public void addNote(int itemCode,String name,double price) {
		if(head==null) {
			head=new ShopNode(itemCode,name,price);
			tail=head;
		} else {
			ShopNode temp=new ShopNode(itemCode,name,price);
			temp.prev=tail;
			tail.next=temp;
			tail=temp;
		}

	}

	public void printDLL() {
		if(head!=null) {
			ShopNode temp=head;
			while(temp!=null) {
				System.out.print(temp.itemCode+",");
				temp=temp.next;
			}
			System.out.println();
		}

	}

	public void printDLLReverse() {
		if(head!=null) {
			ShopNode temp=tail;
			while(temp!=null) {
				System.out.print(temp.itemCode+",");
				temp=temp.prev;
			}
			System.out.println();
		}

	}


	
	private ShopNode split(ShopNode head) { 
		ShopNode fast = head, slow = head; 
		while (fast.next != null && fast.next.next != null) { 
			fast = fast.next.next; 
			slow = slow.next; 
		} 
		ShopNode temp = slow.next; 
		slow.next = null; 
		return temp; 
	} 

	private ShopNode mergeSort(ShopNode node) { 
		if (node == null || node.next == null) { 
			return node; 
		} 
		ShopNode second = split(node); 

		
		node = mergeSort(node); 
		second = mergeSort(second); 

		
		return merge(node, second); 
	} 
	
	public void sort() {
		if(head!=null) {
			head=mergeSort(head);
			ShopNode temp=head;
			while(temp.next!=null) {
				temp=temp.next;
			}
			tail=temp;
		}
		
		
	}

	
	private ShopNode merge(ShopNode first, ShopNode second) { 
		
		if (first == null) { 
			return second; 
		} 

		
		if (second == null) { 
			return first; 
		} 

		
		if (first.itemCode < second.itemCode) { 
			first.next = merge(first.next, second); 
			first.next.prev = first; 
			first.prev = null; 
			return first; 
		} else { 
			second.next = merge(first, second.next); 
			second.next.prev = second; 
			second.prev = null; 
			return second; 
		} 
	} 

	// Ans 1 - The algorithm used to sort the DLL is merge sort.
	// Ans 2 - With merge sort the DLL is broken into 2 parts from the mid.
	//         Then we recursively break the halves until each half is of a single element (i.e. already sorted), 
	//         After that we have to merge sorted halves back into one DLL.
	//		   In the merge step we iterate both halves once and place in sorted manner. taking N steps
	//         Since we divided the DLL in half on each step we have log n steps
	// 		   On each step it takes n steps This
	// Ans 3 - The time complexity is n * log n
	// Ans 4 - Since we split into half and then solve only that part at each step i.e. depth first out space complexity it O(n).

	public static void main(String[] args) {
		DLL DLLobj=new DLL();
		DLLobj.addNote(2, "abc", 0);
		DLLobj.addNote(1, "abc", 0);
		DLLobj.addNote(3, "abc", 0);
		DLLobj.addNote(0, "abc", 0);
		DLLobj.addNote(7, "abc", 0);
		DLLobj.addNote(-1, "abc", 0);
		
		DLLobj.printDLL();
	
		DLLobj.sort();
		
		DLLobj.printDLL();
		
	}

}
