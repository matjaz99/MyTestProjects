package si.matjazcerkvenik.test.javase.math.example1;



public class TestReverse {
    public static void main(String[] args) {
        double[] a = { 1.0, 2.0, 3.0 };
        RealVector x = new RealVector(a);
        ReverseTransform rt = new ReverseTransform();
        rt.reverse(x, x);
        System.out.print("(" + x.get(0));
        for (int k = 1; k < a.length; ++k)
            System.out.print(", " + x.get(k));
        System.out.println(")");
    }
}
