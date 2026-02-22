class Main 
{
    public static void main(String[] args) 
    {
        int[] arr1 = {1, 3, 5};
        int[] arr2 = {2, 3, 4};

        Node<Integer> L1 = createListFromArray(arr1);
        Node<Integer> L2 = createListFromArray(arr2);

        System.out.println("merge:");
        Node<Integer> merged = ex12_mergeSorted(L1, L2);
        printListLoop(merged);

        int[] arr3 = {5, 1, 4, 2};
        Node<Integer> unsorted = createListFromArray(arr3);

        System.out.println("selection sort:");
        Node<Integer> sorted = ex13_selectionSort(unsorted);
        printListLoop(sorted);
    }

    public static Node<Integer> ex12_mergeSorted(Node<Integer> L1, Node<Integer> L2) 
    {
        if (L1 == null) return L2;
        if (L2 == null) return L1;

        Node<Integer> head;
        Node<Integer> tail;

        if (L1.getValue() <= L2.getValue()) {
            head = L1;
            L1 = L1.getNext();
        } else {
            head = L2;
            L2 = L2.getNext();
        }
        tail = head;

        while (L1 != null && L2 != null) 
        {
            if (L1.getValue() <= L2.getValue()) {
                tail.setNext(L1);
                L1 = L1.getNext();
            } else {
                tail.setNext(L2);
                L2 = L2.getNext();
            }
            tail = tail.getNext();
        }

        tail.setNext(L1 != null ? L1 : L2);
        return head;
    }

    public static Node<Integer> ex13_selectionSort(Node<Integer> head)
    {
        Node<Integer> sortedHead = null;
        Node<Integer> sortedTail = null;

        while (head != null) 
        {
            Node<Integer> min = head;
            Node<Integer> minPrev = null;

            for (Node<Integer> prev = head, curr = head.getNext();
                 curr != null;
                 prev = curr, curr = curr.getNext())
            {
                if (curr.getValue() < min.getValue()) {
                    min = curr;
                    minPrev = prev;
                }
            }

            if (minPrev == null) {
                head = head.getNext();
            } else {
                minPrev.setNext(min.getNext());
            }

            min.setNext(null);

            if (sortedHead == null) {
                sortedHead = min;
                sortedTail = min;
            } else {
                sortedTail.setNext(min);
                sortedTail = min;
            }
        }

        return sortedHead;
    }

    public static int sumDistance(Node <Integer> head, int a)
	{
	    int c1 = 0;
	    int c2 = 0;
	    int ca = 0;
	    while(head != null)
	    {
	        if(head.getValue() != a)
	        {
	            if(ca == 0)
	            {
	                c1++;
	            }
	            
	            if(ca != 0)
	            {
	                c2++;
	            }
	        }
	        if(head.getValue() == a)
	        {
	            if(ca == 0)
	            {
	                ca++;
	            }
	            
	            if(ca != 0)
	            {
	                c2 = 0;
	            }
	        }
	        head = head.getNext();
	    }
	    if(ca==0)
	    {
	        return 0;
	    }
	    return c1 + c2; 
	}

	public static boolean areDiffrent(Node <Integer> head)
	{
	    boolean ok = true; 
	    Node <Integer> num = head;
	    while(num != null)
	    {
	        Node <Integer> l = head;
	        int c = 0;
	        while(l != null)
	        {
	            if(l.getValue() == num.getValue())
	            {
	                c++;
	            }
	            l = l.getNext();
	        }
	        if(c != 1)
	        {
	            return false;
	        }
	        num = num.getNext();
	    }
	    return true;
	}
	
	public static Node<Integer> copyList(Node<Integer> l1)
    {
        if (l1 == null)
            return null;

        Node<Integer> head = new Node<>(l1.getValue());
        Node<Integer> tail = head;
        l1 = l1.getNext();
    
        while (l1 != null)
        {
           Node<Integer> newNode = new Node<>(l1.getValue());
           tail.setNext(newNode);
           tail = newNode;
           l1 = l1.getNext();
        }

        return head;
    }
	
	public static Node<Integer> onlyOnce(Node<Integer> list)
	{
    if (list == null)
        return null;

    Node<Integer> t = copyList(list);

    Node<Integer> newList = new Node<>(t.getValue());
    Node<Integer> tail = newList;

    t = t.getNext();

    while (t != null)
    {
        if (!isIn(newList, t.getValue()))
        {
            Node<Integer> newNode = new Node<>(t.getValue());
            tail.setNext(newNode);
            tail = newNode;
        }
        t = t.getNext();
    }

    return newList;
}

	public static Node<Integer> maxInTriplets(Node<Integer> head) {
    if (head == null)
        return null;

    Node<Integer> resultHead = null;
    Node<Integer> resultTail = null;

    Node<Integer> p = head;
    int index = 0;  

    while (p != null && p.getNext() != null && p.getNext().getNext() != null) 
	{

        Node<Integer> first = p;
        Node<Integer> second = p.getNext();
        Node<Integer> third = second.getNext();

        int maxValue = first.getValue();
        int maxIndex = index;

        if (second.getValue() > maxValue) {
            maxValue = second.getValue();
            maxIndex = index + 1;
        }

        if (third.getValue() > maxValue) {
            maxValue = third.getValue();
            maxIndex = index + 2;
        }

        Node<Integer> newNode = new Node<>(maxIndex);

        if (resultHead == null) {
            resultHead = newNode;
            resultTail = newNode;
        } else {
            resultTail.setNext(newNode);
            resultTail = newNode;
        }

        p = third.getNext();
        index += 3;
    }

    return resultHead;
}

	public static Node<Integer> deleteByPositions(Node<Integer> h, Node<Integer> pList)
	{
    if (h == null || pList == null)
        return h;

    Node<Integer> c = h;       
    Node<Integer> pr = null;   
    Node<Integer> q = pList;   

    int i = 0;  // index

    while (c != null && q != null) {

        if (i == q.getValue()) {   
            if (pr == null) {      
                h = c.getNext();
                c = h;
            } else {
                pr.setNext(c.getNext());
                c = c.getNext();
            }
            q = q.getNext();
        }
        else {
            pr = c;
            c = c.getNext();
        }

        i++;
    }

    return h;
	}	

}
