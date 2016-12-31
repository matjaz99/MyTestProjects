package si.matjazcerkvenik.test.javase.math.example1;

/**
 *  The class <tt>Vector</tt> implements a real or complex vector.
 *  <p>
 *  This class supports cloning and serialization.
 *  <p>
 *  @author  Ales Mihev
 *  @version 1.0
 */
public abstract class Vector extends Tensor {
    /**
     *  The class fingerprint for serialization compatibility
     */
    private static final long serialVersionUID = -2720532033609735561L;

    /**
     *  The dimension of this vector
     */
    protected int dimension;

    /**
     *  Constructs a <tt>Vector</tt> with the specified dimension. All
     *  elements of the new vector are set to zero.
     *
     *  @param   dimension
     *           the dimension of the vector to be constructed.
     *  @throws  IllegalArgumentException
     *           if the dimension is not a strictly positive number.
     */
    protected Vector(int dimension) {
        if (dimension <= 0)
            throw new IllegalArgumentException(
                "dimension is not positive: " + dimension
            );
        this.dimension = dimension;
        element = allocate(dimension);
    }

    /**
     *  Returns the dimension of this vector.
     *
     *  @return  the dimension of this vector.
     */
    public final int getDimension() {
        return dimension;
    }

    /**
     *  Returns a hash code for this <tt>Vector</tt> object. The result is
     *  the exclusive OR of hash codes that are computed for all elements
     *  of this vector, represented as <tt>Double</tt> objects.
     *
     *  @return  the hash code value of this <tt>Vector</tt> object.
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     *  Compares the specified object with this vector for equality.
     *  Returns <tt>true</tt> if and only if the specified object has the
     *  following properties:
     *  <ul>
     *    <li> it is a vector of the same type as this vector
     *    <li> it has the same dimension as this vector
     *    <li> all corresponding elements in the two vectors are equal
     *  </ul>
     *
     *  @param   object
     *           the object to be compared with this vector.
     *  @return  <tt>true</tt> if the specified object is equal to this
     *           vector.
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Vector) {
            Vector vector = (Vector) object;
            if (vector.dimension != dimension)
                return false;
            return super.equals(object);
        }
        return false;
    }

    /**
     *  Calculates the Euclidean (or L<sub>2</sub>) norm of this vector.
     *
     *  @return  the Euclidean norm of this vector.
     */
    public abstract double norm();

    /**
     *  Calculates the maximum (or L<sub>&infin;</sub>) norm of this
     *  vector.
     *
     *  @return  the maximum norm of this vector.
     */
    public abstract double maxNorm();

    /**
     *  Allocates memory for the elements of a new vector.
     *
     *  @param   dimension
     *           the dimension of the new vector.
     *  @return  the array of doubles that can be used to store the
     *           elements of the new vector.
     */
    protected abstract double[] allocate(int dimension);
}
