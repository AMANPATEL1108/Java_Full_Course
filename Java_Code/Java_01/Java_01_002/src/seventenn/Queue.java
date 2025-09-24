package seventenn;

import java.util.ArrayList;

public class Queue {
    public Queue() {
    }

    static ArrayList arrayList=new ArrayList();

    public void add(Object object){
        arrayList.add(object);
    }

    public Object poll(){
        Object a=arrayList.get(0);
        arrayList.remove(0);
        return a;
    }

    public void remove(){
        arrayList.remove(0);
    }

    public boolean contain(Object object){
       return arrayList.contains(object);
    }

    @Override
    public String toString() {
        return arrayList.toString();
    }

    public void clear(){
        arrayList.clear();
    }

    public Object size(){
        return arrayList.size();
    }

    public Object peek(){
        return arrayList.get(0);
    }



}
