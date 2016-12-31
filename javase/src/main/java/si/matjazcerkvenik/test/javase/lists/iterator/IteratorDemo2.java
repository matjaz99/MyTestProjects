package si.matjazcerkvenik.test.javase.lists.iterator;

public class IteratorDemo2 {
    public static void main(String[] args) {
        MinMaxList<Double> list = new MinMaxList<Double>();
        list.add(7.0);
        list.add(4.0);
        list.add(5.0);
        for (double x : list)
            System.out.println(x);
    }
}
