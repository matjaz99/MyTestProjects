package si.matjazcerkvenik.test.javase.math.example1;


/**
 *  The class <tt>ReverseTransform</tt> is used to reverse elements of a
 *  specified real vector.
 *
 *  @author  A. Mihev
 */
public class ReverseTransform {
    /**
     *  Reverses elements of the real vector <tt>x</tt> and stores the
     *  result into the real vector <tt>y</tt>. The vector <tt>x</tt> can
     *  be the same as the vector <tt>y</tt>. In this case the reversal is
     *  executed in place.
     *
     *  @param   x
     *           the input vector for the reverse transform.
     *  @param   y
     *           the output vector of the reverse transform.
     */
    public void reverse(RealVector x, RealVector y) {
        final int N = x.getDimension();
        if (N != y.getDimension())
            throw new IllegalArgumentException(
                "vectors do not have the same dimension"
            );
        double[] ex = TensorAccess.getElements(x);
        double[] ey = TensorAccess.getElements(y);
        for (int i = 0, j = N - 1; i < j; ++i, --j) {
            double t = ex[i];
            ex[i] = ey[j];
            ey[j] = t;
        }
    }

    /**
     *  The class <tt>TensorAccess</tt> makes it possible to access the
     *  elements of a given tensor.
     */
    static final class TensorAccess extends Tensor.Access {
        /**
         *  Returns the elements of the specified tensor. These elements
         *  are always returned as an array of doubles, which is formed by
         *  concatenating the vectors along all the tensor's dimensions. If
         *  the tensor is complex, then each of its elements is stored in
         *  two consecutive locations of the returned array: the real part
         *  is stored in the first location, and the imaginary part is
         *  stored in the second location.
         *
         *  @param   tensor
         *           the tensor, whose elements are to be obtained.
         *  @return  the elements, contained in the specified tensor.
         */
        protected static double[] getElements(Tensor tensor) {
            return Tensor.Access.getElements(tensor);
        }
    }
}
