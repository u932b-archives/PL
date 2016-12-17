import java.util.*;
import java.util.List;
import java.io.*;

@SuppressWarnings("serial")
class ComparableList<T extends Comparable<T>> extends ArrayList<T> implements Comparable<ComparableList<T>>{
// class ComparableList<T extends Comparable<? super T>> extends ArrayList<T> implements Comparable<ComparableList<T>>{
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
        if(this.a > other.a){
            return 1;
        }
        else if (this.a == other.a){
            return 0;
        }
        else{
            return -1;
        }
    }
    public String toString() {
        return "B<" + this.a + "," + this.b + ">";
    }
}


public class part1{
    static <T> void addToCList(T attachment, ComparableList<? super T> target_list){
        target_list.add(attachment);
    }

    static void test(){
        ComparableList<A> c1 = new ComparableList<A>();
        ComparableList<A> c2 = new ComparableList<A>();
        for(int i = 0; i < 10; i++) {
			addToCList(new A(i), c1);
            addToCList(new A(i), c2);
        }

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
    }

}
