package learning;

import java.util.ArrayList;

/**
 * Created by Phuong Huynh on 5/28/2017.
 */
public class TestPointer {
    static ArrayList<MyClass> myClass = new ArrayList<MyClass>();

    static MyClass class01 = new MyClass("01");
    static MyClass class02 = new MyClass("02");

    public static void main(String args[]){
        class01.addItem(1);
        class01.addItem(2);

        class02.addItem(22);
        class02.addItem(21);

        myClass.add(class01);
        myClass.add(class02);

        MyClass temp = myClass.get(0);
        //temp.addItem(3);

        for (MyClass c : myClass)
            c.print();
    }



}

class MyClass{
    String id;
    ArrayList<Integer> items;

    public MyClass(String id){
        this.id = id;
        this.items = new ArrayList<Integer>();
    }

    public void addItem(int i){
        this.items.add(i);
    }

    public void print(){
        for (int i : this.items){
            System.out.print(i + "\t");
        }
        System.out.println();
    }
}
