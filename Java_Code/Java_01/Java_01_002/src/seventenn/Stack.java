package seventenn;

import java.util.ArrayList;
import java.util.List;

public class Stack {



    public Stack() {
    }

    static  ArrayList arrayList=new ArrayList();

    public  void push(Object element){
        arrayList.add(element);
    }

    public  void pop(){
        arrayList.remove(arrayList.size()-1);
    }

    public Object peek(){
        return arrayList.get(arrayList.size()-1);
    }

    public void add(int index,int object){
        arrayList.add(index,object);
    }

    public Object search(Object object){
        return arrayList.indexOf(object);
    }

    public boolean isEmpty(){
        return  arrayList.isEmpty();
    }

    @Override
    public String toString() {
        return arrayList.toString();  // This will return a string representation of the ArrayList
    }

}
