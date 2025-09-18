package Day;

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.LinkedList;

//What is the main difference between the underlying data structures of an ArrayList and a LinkedList?
//->ArrayList get index thre we can get and LinkedList in that we can get from a prev and Next Threw
//How does the time complexity for accessing an element differ between ArrayList and LinkedList?
//->In ArrayList when first then O(1) else O(n)  ->in LinekedList head-tails O(1) else O(n)
//In which situation would you prefer using a LinkedList over an ArrayList?
//Linked List is when insertion and deletion is more then and ArrayList is then when we want to finding
//Why does inserting or deleting elements in the middle of an ArrayList take O(n) time, but in a LinkedList it is O(1) (if the node is already located)?
//->because of this one  Arraylist check one by one and Linked list previous and next threw then
//What happens when an ArrayList exceeds its capacity? How does it handle this situation?
//->in this they are double that value if size
//If you were to frequently add and remove elements from the beginning of a list, which implementation would be better, ArrayList or LinkedList? Why?
//->In first LinkedList
//What is the memory overhead of a LinkedList compared to an ArrayList?
//->LinkedList has more memory overhead because each node stores data + 2 references (next, prev). ArrayList stores only the data, so it’s more memory efficient.
public class Day_Eleven {
    public static void main(String[] args) {
        ArrayList arrayList=new ArrayList();
        LinkedList linkedList=new LinkedList();
        arrayList.add(10);
        arrayList.add(20);
        arrayList.add(30);
        arrayList.add(40);
        arrayList.add(2,12);
        linkedList.remove(3);
        System.out.println(arrayList);
        linkedList.add(10);
        linkedList.add(20);
        linkedList.add(30);
        linkedList.add(40);
        linkedList.add(2,12);
        linkedList.remove(3);
        System.out.println(arrayList);
        System.out.println(linkedList);

    }

    public static void checkTime(){
        ArrayList a=new ArrayList();
        LinkedList l=new LinkedList();
        for(int i=0;i<=1000000;i++){
            a.add(i);
            l.add(i);
        }
        a.get(500000);
        l.get(500000);
    }
}

