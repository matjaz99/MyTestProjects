package si.matjazcerkvenik.test.javase.math.example1;

/**
 *  The class <tt>RealVector</tt> implements a real vector.
 *  <p>
 *  This class supports cloning and serialization.
 *  <p>
 *  @author  Ales Mihev
 *  @version 1.0
 */
public class RealVector extends Vector {
    /**
     *  The class fingerprint for serialization compatibility
     */
    private static final long serialVersionUID = -8236801306081445062L;

    /**
     *  Constructs a real vector with the specified dimension. All elements
     *  of the new vector are set to zero.
     *
     *  @param   dimension
     *           the dimension of the vector to be constructed.
     *  @throws  IllegalArgumentException
     *           if the dimension is not a strictly positive number.
     */
    public RealVector(int dimension) {
        super(dimension);
    }

    /**
     *  Constructs a real vector that contains the same elements as the
     *  specified real array.
     *
     *  @param   array
     *           the array of <tt>double</tt> values from which the new
     *           real vector is constructed.
     *  @throws  IllegalArgumentException
     *           if the length of the specified array is zero.
     */
    public RealVector(double[] array) {
        super(array.length);
        System.arraycopy(array, 0, element, 0, dimension);
    }

    /**
     *  Gets the element at the specified index of this real vector.
     *
     *  @param   index
     *           the index of the element to return.
     *  @return  the element at the specified index.
     *  @throws  IndexOutOfBoundsException
     *           if the index is negative or not less than the dimension of
     *           this vector.
     */
    public double get(int index) {
        if (index < 0 || index >= dimension)
            throw new IndexOutOfBoundsException(
                "index of the vector is out of bounds: " + index
            );
        return element[index];
    }

    /**
     *  Sets the element at the specified index of this real vector to a
     *  new value.
     *
     *  @param   index
     *           the index of the element to change.
     *  @param   value
     *           the new value of the element at the specified index.
     *  @throws  IndexOutOfBoundsException
     *           if the index is negative or not less than the dimension of
     *           this vector.
     */
    public void set(int index, double value) {
        if (index < 0 || index >= dimension)
            throw new IndexOutOfBoundsException(
                "index of the vector is out of bounds: " + index
            );
        element[index] = value;
    }

    /**
     *  Adds another real vector to this real vector.
     *
     *  @param   vector
     *           the vector to be added to this vector.
     *  @return  the sum of this vector and the specified vector.
     *  @throws  IllegalArgumentException
     *           If the vectors to be added do not have the same dimension.
     */
    public RealVector add(RealVector vector) {
        if (vector.dimension != dimension)
            throw new IllegalArgumentException(
                "vectors do not have the same dimension"
            );
        return (RealVector) super.add(vector);
    }

    /**
     *  Subtracts another real vector from this real vector.
     *
     *  @param   vector
     *           the vector to be subtracted from this vector.
     *  @return  the difference between this vector and the specified
     *           vector.
     *  @throws  IllegalArgumentException
     *           If the vectors to be subtracted do not have the same
     *           dimension.
     */
    public RealVector subtract(RealVector vector) {
        if (vector.dimension != dimension)
            throw new IllegalArgumentException(
                "vectors do not have the same dimension"
            );
        return (RealVector) super.subtract(vector);
    }

    /**
     *  Multiplies the elements of this real vector by the specified
     *  scalar.
     *
     *  @param   scalar
     *           the scalar by which the elements of this real vector are
     *           to be multiplied.
     *  @return  the real vector whose elements are equal to the elements
     *           of this real vector, multiplied by the specified scalar.
     */
    @Override
    public RealVector multiply(double scalar) {
        return (RealVector) super.multiply(scalar);
    }

    /**
     *  Computes the dot (scalar) product of this real vector and the
     *  specified real vector.
     *
     *  @param   vector
     *           the real vector, whose dot product with this vector is to
     *           be computed.
     *  @return  the dot product of this real vector and the specified real
     *           vector.
     *  @throws  IllegalArgumentException
     *           If the specified vector does not have the same dimension
     *           as this vector.
     */
    public double dot(RealVector vector) {
        if (vector.dimension != dimension)
            throw new IllegalArgumentException(
                "vectors do not have the same dimension"
            );
        double result = 0.0;
        for (int k = 0; k < dimension; ++k)
            result += element[k] * vector.element[k];
        return result;
    }

    /**
     *  Calculates the Euclidean norm (or L<sub>2</sub>) norm of this real
     *  vector.
     *
     *  @return  the Euclidean norm of this real vector.
     */
    @Override
    public double norm() {
        double normInf = maxNorm();
        if (normInf == 0.0) return 0.0;
        double s = 0.0;
        for (int k = 0; k < element.length; ++k) {
            double re = element[k] / normInf;
            s += re * re;
        }
        return normInf * Math.sqrt(s);
    }

    /**
     *  Calculates the maximum (or L<sub>&infin;</sub>) norm of this real
     *  vector.
     *
     *  @return  the maximum norm of this real vector.
     */
    public double maxNorm() {
        double norm = 0.0;
        for (int k = 0; k < element.length; ++k) {
            double a = Math.abs(element[k]);
            if (a > norm) norm = a;
        }
        return norm;
    }

    /**
     *  Allocates memory for the elements of this real vector.
     *
     *  @param   dimension
     *           the dimension of this real vector.
     *  @return  the array of doubles that can be used to store the
     *           elements of the specified real vector.
     */
    protected double[] allocate(int dimension) {
        assert dimension > 0;
        return new double[dimension];
    }
}
