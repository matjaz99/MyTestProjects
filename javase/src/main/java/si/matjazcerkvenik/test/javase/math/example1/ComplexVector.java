package si.matjazcerkvenik.test.javase.math.example1;

/**
 *  The class <tt>ComplexVector</tt> implements a complex vector.
 *  <p>
 *  This class supports cloning and serialization.
 *  <p>
 *  @author  Ales Mihev
 *  @version 1.0
 */
public class ComplexVector extends Vector {
    /**
     *  The class fingerprint for serialization compatibility
     */
    private static final long serialVersionUID = 2879867230218464298L;

    /**
     *  Constructs a complex vector with the specified dimension. All
     *  elements of the new vector are set to zero.
     *
     *  @param   dimension
     *           the dimension of the vector to be constructed.
     *  @throws  IllegalArgumentException
     *           if the dimension is not a strictly positive number.
     */
    public ComplexVector(int dimension) {
        super(dimension);
    }

    /**
     *  Constructs a complex vector that contains the same elements as the
     *  specified complex array.
     *
     *  @param   array
     *           the array of <tt>Complex</tt> values from which the new
     *           complex vector is to be constructed.
     *  @throws  IllegalArgumentException
     *           if the length of the specified array is zero.
     */
    public ComplexVector(Complex[] array) {
        super(array.length);
        for (int i = 0; i < dimension; ++i) {
            int j = i << 1;
            element[j] = array[i].real;
            element[j + 1] = array[i].imag;
        }
    }

    /**
     *  Gets the element at the specified index of this complex vector.
     *
     *  @param   index
     *           the index of the element to return.
     *  @return  the element at specified index.
     *  @throws  IndexOutOfBoundsException
     *           if the index is negative or not less than the dimension of
     *           this vector.
     */
    public Complex get(int index) {
        if (index < 0 || index >= dimension)
            throw new IndexOutOfBoundsException(
                "index of the vector is out of bounds: " + index
            );
        int k = index << 1;
        return new Complex(element[k], element[k + 1]);
    }

    /**
     *  Sets the element at the specified index of this complex vector to a
     *  new value.
     *
     *  @param   index
     *           the index of the element to change.
     *  @param   z
     *           the new value of the element at the specified index.
     *  @throws  IndexOutOfBoundsException
     *           if the index is negative or not less than the dimension of
     *           this vector.
     */
    public void set(int index, Complex z) {
        if (index < 0 || index >= dimension)
            throw new IndexOutOfBoundsException(
                "index of the vector is out of bounds: " + index
            );
        int k = index << 1;
        element[k] = z.real;
        element[k + 1] = z.imag;
    }

    /**
     *  Adds another complex vector to this complex vector.
     *
     *  @param   vector
     *           the vector to be added to this vector.
     *  @return  the sum of this vector and the specified vector.
     *  @throws  IllegalArgumentException
     *           If the vectors to be added do not have the same dimension.
     */
    public ComplexVector add(ComplexVector vector) {
        if (vector.dimension != dimension)
            throw new IllegalArgumentException(
                "vectors do not have the same dimension"
            );
        return (ComplexVector) super.add(vector);
    }

    /**
     *  Subtracts another complex vector from this complex vector.
     *
     *  @param   vector
     *           the vector to be subtracted from this vector.
     *  @return  the difference between this vector and the specified
     *           vector.
     *  @throws  IllegalArgumentException
     *           If the vectors to be subtracted do not have the same
     *           dimension.
     */
    public ComplexVector subtract(ComplexVector vector) {
        if (vector.dimension != dimension)
            throw new IllegalArgumentException(
                "vectors do not have the same dimension"
            );
        return (ComplexVector) super.subtract(vector);
    }

    /**
     *  Multiplies the elements of this complex vector by the specified
     *  real scalar.
     *
     *  @param   scalar
     *           the real scalar by which the elements of this complex
     *           vector are to be multiplied.
     *  @return  the complex vector whose elements are equal to the
     *           elements of this complex vector, multiplied by the
     *           specified real scalar.
     */
    @Override
    public ComplexVector multiply(double scalar) {
        return (ComplexVector) super.multiply(scalar);
    }

    /**
     *  Multiplies the elements of this complex vector by the specified
     *  complex scalar.
     *
     *  @param   scalar
     *           the complex scalar by which the elements of this vector
     *           are to be multiplied.
     *  @return  a complex vector whose elements are equal to the elements
     *           of this vector, multiplied by the specified complex
     *           scalar.
     */
    public ComplexVector multiply(Complex scalar) {
        ComplexVector v = new ComplexVector(dimension);
        double[] ve = v.element;
        for (int k = 0; k < element.length; k += 2) {
            double re = element[k], im = element[k + 1];
            ve[k] = scalar.real * re - scalar.imag * im;
            ve[k + 1] = scalar.real * im + scalar.imag * re;
        }
        return v;
    }

    /**
     *  Computes the dot (scalar) product of this complex vector and the
     *  specified complex vector.
     *
     *  @param   vector
     *           the complex vector, whose dot product with this vector is
     *           to be computed.
     *  @return  the dot product of this complex vector and the specified
     *           complex vector.
     *  @throws  IllegalArgumentException
     *           If the specified vector does not have the same dimension
     *           as this vector.
     */
    public Complex dot(ComplexVector vector) {
        if (vector.dimension != dimension)
            throw new IllegalArgumentException(
                "vectors do not have the same dimension"
            );
        double zre = 0.0, zim = 0.0;
        double[] ve = vector.element;
        for (int k = 0; k < element.length; k += 2) {
            double re = element[k], im = element[k + 1];
            double vre = ve[k], vim = ve[k + 1];
            zre += re * vre - im * vim;
            zim += re * vim + im * vre;
        }
        return new Complex(zre, zim);
    }

    /**
     *  Calculates the Euclidean (or L<sub>2</sub>) norm of this complex
     *  vector.
     *
     *  @return  the Euclidean norm of this complex vector.
     */
    @Override
    public double norm() {
        double normInf = maxNorm();
        if (normInf == 0.0) return 0.0;
        double s = 0.0;
        for (int k = 0; k < element.length; k += 2) {
            double re = element[k] / normInf;
            double im = element[k + 1] / normInf;
            s += re * re + im * im;
        }
        return normInf * Math.sqrt(s);
    }

    /**
     *  Calculates the maximum (or L<sub>&infin;</sub>) norm of this
     *  complex vector.
     *
     *  @return  the maximum norm of this complex vector.
     */
    public double maxNorm() {
        double norm = 0.0;
        for (int k = 0; k < element.length; k += 2) {
            double m = Math.hypot(element[k], element[k + 1]);
            if (m > norm) norm = m;
        }
        return norm;
    }

    /**
     *  Allocates memory for the elements of this complex vector.
     *
     *  @param   dimension
     *           the dimension of this complex vector.
     *  @return  the array of doubles that can be used to store the
     *           elements of the specified complex vector.
     */
    protected double[] allocate(int dimension) {
        assert dimension > 0;
        return new double[dimension << 1];
    }
}
