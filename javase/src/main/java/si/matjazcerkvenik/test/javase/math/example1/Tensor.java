package si.matjazcerkvenik.test.javase.math.example1;

import java.io.Serializable;

/**
 *  The class <tt>Tensor</tt> implements some common functions of a real or
 *  complex tensor.
 *  <p>
 *  This class supports cloning and serialization.
 *  <p>
 *  @author  Ales Mihev
 *  @version 1.0
 */
public abstract class Tensor implements Cloneable, Serializable {
    /**
     *  The class fingerprint for serialization compatibility
     */
    private static final long serialVersionUID = 7525707819512088170L;

    /**
     *  The internal array of elements
     */
    protected double[] element;

    /**
     *  Constructs a new <tt>Tensor</tt>.
     */
    protected Tensor() {
    }

    /**
     *  Returns a hash code for this <tt>Tensor</tt> object. The result is
     *  the exclusive OR of hash codes that are computed for all elements
     *  of this tensor, represented as <tt>Double</tt> objects.
     *
     *  @return  the hash code value of this <tt>Tensor</tt> object.
     */
    @Override
    public int hashCode() {
        int code = 0;
        int n = element.length;
        for (int k = 0; k < n; ++k) {
            long v = Double.doubleToLongBits(element[k]);
            code ^= (int)(v ^ (v >>> 32));
        }
        return code;
    }

    /**
     *  Compares the specified object with this tensor for equality.
     *  Returns <tt>true</tt> if and only if the specified object is a
     *  tensor of the same type as this tensor, if both tensors contain the
     *  same total number of elements, and if all elements in the two
     *  tensors are mathematically identical.
     *
     *  @param   object
     *           an object to be compared with this tensor.
     *  @return  <tt>true</tt> if the specified object is equal to this
     *           tensor.
     */
    @Override
    public boolean equals(Object object) {
        if (getClass() != object.getClass())
            return false;
        final int N = element.length;
        Tensor tensor = (Tensor) object;
        double[] te = tensor.element;
        if (N != te.length)
            return false;
        for (int k = 0; k < N; ++k)
            if (element[k] != te[k])
                return false;
        return true;
    }

    /**
     *  Creates a copy of this tensor.
     *
     *  @return  a copy of this tensor.
     */
    @Override
    public Object clone() {
        try {
            Tensor copy = (Tensor) super.clone();
            copy.element = element.clone();
            return copy;
        }
        catch (CloneNotSupportedException e) {
        //  never occurs
            throw new InternalError();
        }
    }

    /**
     *  Adds another tensor to this tensor.
     *
     *  @param   tensor
     *           the tensor to be added to this tensor.
     *  @return  the sum of this tensor and the specified tensor.
     *  @throws  IllegalArgumentException
     *           If the tensors to be added contain different number of
     *           elements.
     */
    protected Tensor add(Tensor tensor) {
        final int N = element.length;
        if (tensor.element.length != N)
            throw new IllegalArgumentException(
                "tensors have different dimensions"
            );
        Tensor result = (Tensor) clone();
        for (int k = 0; k < N; ++k)
            result.element[k] += tensor.element[k];
        return result;
    }

    /**
     *  Subtracts another tensor from this tensor.
     *
     *  @param   tensor
     *           the tensor to be subtracted from this tensor.
     *  @return  the difference between this tensor and the specified
     *           tensor.
     *  @throws  IllegalArgumentException
     *           If the tensors to be subtracted contain different number
     *           of elements.
     */
    protected Tensor subtract(Tensor tensor) {
        final int N = element.length;
        if (tensor.element.length != N)
            throw new IllegalArgumentException(
                "tensors have different dimensions"
            );
        Tensor result = (Tensor) clone();
        for (int k = 0; k < N; ++k)
            result.element[k] -= tensor.element[k];
        return result;
    }

    /**
     *  Multiplies the elements of this tensor by the specified real
     *  scalar.
     *
     *  @param   scalar
     *           the real scalar by which the elements of this tensor are
     *           to be multiplied.
     *  @return  the tensor whose elements are equal to the elements of
     *           this tensor, multiplied by the specified scalar.
     */
    protected Tensor multiply(double scalar) {
        final int N = element.length;
        Tensor result = (Tensor) clone();
        for (int k = 0; k < N; ++k)
            result.element[k] *= scalar;
        return result;
    }

    /**
     *  The class <tt>Access</tt> allows its subclasses to access the
     *  elements of a given tensor.
     */
    public static abstract class Access {
        /**
         *  Returns the array of elements in the specified tensor. If the
         *  tensor is complex, then each of its elements is stored in two
         *  consecutive locations of the returned array: the real part is
         *  stored in the first location, and the imaginary part is stored
         *  in the second location.
         *
         *  @param   tensor
         *           the tensor, whose elements are to be obtained.
         *  @return  the array of elements, contained in the specified
         *           tensor.
         */
        protected static double[] getElements(Tensor tensor) {
            return tensor.element;
        }
    }
}
