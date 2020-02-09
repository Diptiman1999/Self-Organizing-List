/*Program To design that uses count method to reorder the elements present in the list based on 
  some self-organizing heuristic to improve average access time.
	Submitted By:-Diptiman Senapati(1741012062)
				  Deepkalyan Priyadarshi(1741012167)
				  Jeeban Prakash Nayak(1741012155)
				  Rajesh Ranjan Rath(1741012330)

*/

import java.util.*;

class Node
{
	//Node Members Instance
	int data;               //To store the data
	int count;
	Node prev;
	Node next;

	//Node Constructor
	Node(int d)
	{
		data=d;
		count=0;
		prev=next=null;
	}
	int get_count()
	{
		return count;		//Return Count
	}
}

class SelfList
{
	Node start;				//To Refer to The starting node 
	int totalnode;			//For Total Number Of Nodes
	SelfList()
	{
		start=null;			//Initializing To Null
		totalnode=0;		//Initializing totalnode
	}

	//Inserting Node
	void insertnode(int d)
	{
		Node new1=new Node(d);
		Node current=start;
		if(current==null)
			start=new1;
		else
		{
			while(current.next!=null)
			{
				current=current.next;
			}
			current.next=new1;
			new1.prev=current;

		}		
		totalnode++;
	}

	//Searching Of Node and incrementing the count variable
	void search(int key)
	{
		Node current=start;
		if(current==null)
			System.out.println("Sorry no element is present to search.....");
		else
		{
			while(current!=null)
			{
				if(key==current.data)
				{	
					current.count++;
					countmethod(); 			//Calling the Count Method
					break;
				}
				current=current.next;
			}
			if(current ==null)
				System.out.println("Element is Not Found");
			else
				System.out.println(key+" is found successfully");
		}
	}
	
	//Deleting the Node by using their Position
	void deletenode(int pos)
	{
		if(start==null)
			System.out.println("Nothing in The List to be deleted");
		else
		{
			if(pos==1)
			{
				System.out.println(start.data+" was deleted");
				if(start.next!=null)
					start.next.prev=null;
				start=start.next;
				totalnode--;  					//Decrementing totalnode after deleting
			}
			else if(pos==totalnode)
			{
				Node current=start;
				while(current.next!=null)
				{
					current=current.next;
				}
				System.out.println(current.data+" was deleted");
				current.prev.next=null;
				current.prev=null;
				totalnode--; 					//Decrementing totalnode after deleting
			}
			else if(pos>1 && pos<totalnode)
			{
				Node current=start;
				
				for(int i=1;i<pos-1;i++)
					current=current.next;
				current.next=current.next.next;
				current.next.prev=current;
				totalnode--;					//Decrementing totalnode after deleting
			}
			else
				System.out.println("Wrong position Entered");

		}
	}

	//Returning the size
	int get_size()
	{
		return totalnode;
	}

	//Checking whether list is empty or not
	boolean checkempty()
	{
		return (start==null);
	}

	//Checking whether list is full or not
	boolean isFull(int size){
		return (totalnode==size);
	}

	//Displaying The list
	void display()
	{
		System.out.println();
		Node current=start;
		if(current==null)
			System.out.println("No Element is there to display");
		else
		{
			System.out.println("Displaying the Element");
			while(current.next!=null)
			{
				System.out.print(current.data+"---->>");
				current=current.next;
			}
			System.out.print(current.data+" ");
			System.out.println();
			current=start;
		}
	}

	//Count Method To Arrange the Element
	void countmethod()
	{
		Node current=start;Node temp;int tempdata=0;int tempcount=0;
		for(int out=totalnode-1;out>0;out--)
		{
			for (int in=0;in<out ;in++ )
			 {
				if(current.count<current.next.count)
				{
					tempdata=current.data;
					tempcount=current.count;
					current.data=current.next.data;
					current.count=current.next.count;
					current.next.data=tempdata;
					current.next.count=tempcount;
				}
				current=current.next;
			}
			current=start;
		}

	}
}

//Self Organizing App
class SelfOrganizingList
{
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		int set =1;
		SelfList sl=new SelfList();
		System.out.println("Enter the Size of the List");
		int size=sc.nextInt();		
		do
		{
			System.out.println();
			System.out.println("*********SELF ORGANIZING LIST********");
			System.out.println("Self Organizing List Operations:");
			System.out.println("Enter your choice>>");
			System.out.println("1.Enter element to insert");	
			System.out.println("2.Enter position to delete");
			System.out.println("3.Enter the Element to be search");
			System.out.println("4.Check Empty");
			System.out.println("5.Check Full");
			System.out.println("6.Get Size");
			System.out.println("7.Display");
			System.out.println("8.Exit");
			int ch=sc.nextInt();					//Entering the choice
		
			switch(ch)
			{
				//For Inserting
				case 1:
					if(!sl.isFull(size))
					{
						System.out.println("Enter the element to insert");
						int d=sc.nextInt();
						sl.insertnode(d);
						sl.display();
					}
					else
						System.out.println("List Is Full");
					System.out.println();
				break;

				//For Deleting
				case 2:
					if(!sl.checkempty())
					{
						System.out.println("Enter the position");
						int pos=sc.nextInt();
						sl.deletenode(pos);
						sl.display();
					}
					else
						System.out.println("List Is Empty");
					System.out.println();				
				break;

				//For Searching
				case 3:
					if(!sl.checkempty())
					{
						System.out.println("Enter the element to search");
						int element=sc.nextInt();
						sl.search(element);
						sl.display();
					}
					else
						System.out.println("List Is Empty,so no search operation can perform");
					System.out.println();
				break;

				//Check whether list is empty or not
				case 4:
					if(sl.checkempty())
						System.out.println("Yes the List is Empty");
					else
						System.out.println("No the List is Not Empty");
					System.out.println();
				break;

				//Check whether list is full or not
				case 5:
					if(sl.isFull(size))
						System.out.println("Yes the List is Full");
					else
						System.out.println("No the List is Not Full");
					System.out.println();				
				break;

				//Displaying the Actual Size of List
				case 6:
					int s=sl.get_size();
					System.out.println("Size of the List :"+s);
					System.out.println();
				break;

				//Displaying The List
				case 7:
					sl.display();
				break;

				//For Exiting
				case 8:
					set=0;
				break;

				//For Wrong Choice
				default:
				System.out.println("Wrong Choice Please Enter once More");
			}


		}while(set!=0);				//Exiting if set==0 otherwise run once again
	}

}//End Of Code