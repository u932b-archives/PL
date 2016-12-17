import java.util.*;
import java.util.List;
import java.io.*;

@SuppressWarnings("serial")
// class ComparableList implements Comparable<ComparableList>{ //extends ArrayList<T>{
class ComparableList<T extends Comparable<T>> implements Comparable<ComparableList<T>>{
// class ComparableList<T extends Comparable<T>> extends ArrayList<T> implements Comparable<ComparableList<T>>{
//class ComparableList<T extends Comparable<? super T>> extends ArrayList<T> implements Comparable<ComparableList<T>>{
    public int compareTo(ComparableList<T> s2){
        for (int i=1; i<=Math.min(this.size(), s2.size()); i++){
            int compare_result = this.get(i).compareTo(s2.get(i));
            if (compare_result >0){
                return 1;
            }
            else if(compare_result <0){
                return -1;
            }
        }
        if (this.size() > s2.size()){
            return 1;
        }
        else if (this.size()< s2.size()){
            return -1;
        }
        else{
            return 0;
        }
    }
}

class A implements Comparable<A>{
    Integer a =0;
    //protected Integer a =0;
    public A(Integer x){
        this.a = x;
    }
    public String toString(){
        return "A<" + this.a + ">";
    }
    public int compareTo(A that){
        if(this.a > that.a){
            return 1;
        }
        else if (this.a == that.a){
            return 0;
        }
        else{
            return -1;
        }
    }
}

class B extends A{
    private Integer a;
    private Integer b;
    public B(Integer x, Integer y){
        super(x+y);
        this.a = x;
        this.b = y;
    }

    public int compareTo(B other){
        if(this.a > other.a)
                return 1;
        else if (this.a == other.a)
            return 0;
        else
            return -1;
    }
    public String toString() {
        return "B<" + this.a + "," + this.b + ">";
    }
}


public class part1{
    static <T> void addToCList(T test1, ComparableList<? super T> test2){
    // static <T extends Comparable<T>> void addToCList(T test1, ComparableList<T> test2){
    // public static <E, T> void addToCList(E test, T test2){
        test2.add(test1);
    }

    static void test(){
        // ComparableList2 list1 = new ComparableList2<ComparableList2<Integer>>(Arrays.asList(1,2,3));
        // ArrayList<Integer> a = new ArrayList<Integer>();
        // ComparableList<Integer> a = new ComparableList<Integer>();
        // a.add(1);
        ComparableList<A> c1 = new ComparableList<A>();
        ComparableList<A> c2 = new ComparableList<A>();
        for(int i = 0; i < 10; i++) {
			addToCList(new A(i), c1);
            addToCList(new A(i), c2);
        }
        System.out.print("a B: ");
        System.out.println(new B(2,2));

        addToCList(new A(12), c1);
        addToCList(new B(6,6), c2);

        addToCList(new B(7,11), c1);
        addToCList(new A(13), c2);

        System.out.print("c1: ");
        System.out.println(c1);

        System.out.print("c2: ");
        System.out.println(c2);

        switch (c1.compareTo(c2)) {
        case -1:
            System.out.println("c1 < c2");
            break;
        case 0:
            System.out.println("c1 = c2");
            break;
        case 1:
            System.out.println("c1 > c2");
            break;
        default:
            System.out.println("Uh Oh");
            break;
        }
    }
    public static void main(String [] args){
        test();
        /*
        A a1 = new A(6);
        B b1 = new B(2,4);
        B b2 = new B(3,5);
        System.out.println(a1.toString());
        System.out.println(b2.toString());
        System.out.println(a1.compareTo(b1));
        System.out.println(a1.compareTo(b2));
        System.out.println(b1.compareTo(a1));
        System.out.println(b2.compareTo(a1));
        System.out.println(b1.compareTo(b2));
        */
    }

}
