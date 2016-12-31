package si.matjazcerkvenik.test.javase.math.example1;

import java.io.Serializable;

/**
 *  The class <tt>Complex</tt> implements the basic arithmetical operations
 *  and functions on complex numbers.
 *  <p>
 *  @author  Ales Mihev
 *  @version 1.0
 */
public final class Complex implements Cloneable, Serializable {
    /**
     *  The class fingerprint for serialization compatibility
     */
    private static final long serialVersionUID = 8052955611011499069L;

    /**
     *  The real part of a complex number
     */
    public double real;

    /**
     *  The imaginary part of a complex number
     */
    public double imag;

    /**
     *  Constructs a new complex number with zero value.
     */
    public Complex() {
    }

    /**
     *  Constructs a new complex number with the specified real part and
     *  the imaginary part equals to zero.
     *
     *  @param   real
     *           the real part of the complex number to be created.
     */
    public Complex(double real) {
        this.real = real;
    }

    /**
     *  Constructs a new complex number with the specified real and
     *  imaginary part.
     *
     *  @param   real
     *           the real part of the complex number to be created.
     *  @param   imag
     *           the imaginary part of the complex number to be created.
     */
    public Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    /**
     *  Sets this complex number to the specified value.
     *
     *  @param   re
     *           the real part of the new value for this complex number.
     *  @param   im
     *           the imaginary part of the new value for this complex
     *           number.
     */
    public void set(double re, double im) {
        real = re;
        imag = im;
    }

    /**
     *  Returns a hash code for this <tt>Complex</tt> object. The result is
     *  the exclusive OR of two hash codes that are computed for the
     *  <tt>Double</tt> objects that represent the real and the imaginary
     *  value of this <tt>Complex</tt> object: <blockquote><pre> (new
     *  Double(real)).hashCode() ^ (new Double(imag)).hashCode()
     *  </pre></blockquote>
     *
     *  @return  the hash code value of this <tt>Complex</tt> object.
     */
    @Override
    public int hashCode() {
        long bits = Double.doubleToLongBits(real);
        bits ^= Double.doubleToLongBits(imag);
        return (int)(bits ^ (bits >>> 32));
    }

    /**
     *  Determines whether the specified object is equal to this complex
     *  number. The method returns <tt>true</tt> if the compared object is
     *  of the type <tt>Complex</tt> and has the same real and imaginary
     *  part as this complex number.
     *
     *  @param   obj
     *           an object to be compared to this complex number.
     *  @return  <tt>true</tt> if the compared object is equal to this
     *           complex number.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Complex) {
            Complex z = (Complex) obj;
            return real == z.real && imag == z.imag;
        }
        return false;
    }

    /**
     *  Creates a copy of this complex number.
     *
     *  @return  a copy of this complex number.
     */
    @Override
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
        //  never occurs
            throw new InternalError();
        }
    }

    /**
     *  Returns a <tt>String</tt> object representing this complex number.
     *
     *  @return  a string representation of this <tt>Complex</tt> object.
     */
    @Override
    public String toString() {
        return toString(real, imag);
    }

    /**
     *  Returns a <tt>String</tt> object representing the complex number
     *  with the specified real and imaginary part.
     *
     *  @param   real
     *           the real part of a complex number.
     *  @param   imag
     *           the imaginary part of a complex number.
     *  @return  a <tt>String</tt> representation of a complex number with
     *           the specified real and imaginary part.
     */
    public static String toString(double real, double imag) {
        if (real != 0.0 || imag == 0.0) {
            if (imag > 0.0) return real + "+" + imag + "i";
            if (imag < 0.0) return real + "" + imag + "i";
            return Double.toString(real);
        }
        else return imag + "i";
    }

    /**
     *  Returns <tt>true</tt> if this complex number has an infinite value.
     *  A complex number is considered infinite if any of its two
     *  components is infinite, even if the other component is NaN.
     *
     *  @return  <tt>true</tt> if this complex number has an infinite
     *           value.
     */
    public boolean isInfinite() {
        return Double.isInfinite(real) || Double.isInfinite(imag);
    }

    /**
     *  Returns <tt>true</tt> if the specified complex number has an
     *  infinite value. A complex number is considered infinite if any of
     *  its two component is infinite, even if the other component is NaN.
     *
     *  @param   z
     *           a complex number
     *  @return  <tt>true</tt> if the complex number <tt>a</tt> has an
     *           infinite value.
     */
    public static boolean isInfinite(Complex z) {
        return Double.isInfinite(z.real) || Double.isInfinite(z.imag);
    }

    /**
     *  Returns <tt>true</tt> if this complex number has a NaN value. A
     *  complex number has a NaN value if any of its two components is NaN.
     *
     *  @return  <tt>true</tt> if this complex number has a NaN value.
     */
    public boolean isNaN() {
        return Double.isNaN(real) || Double.isNaN(imag);
    }

    /**
     *  Returns <tt>true</tt> if the specified complex number has a NaN
     *  value. A complex number has a NaN value if any of its two
     *  components is NaN.
     *
     *  @param   z
     *           a complex number
     *  @return  <tt>true</tt> if the complex number <tt>z</tt> has a NaN
     *           value.
     */
    public static boolean isNaN(Complex z) {
        return Double.isNaN(z.real) || Double.isNaN(z.imag);
    }

    /**
     *  Returns the complex number whose value is <tt>(this + z)</tt>.
     *
     *  @param   z
     *           the value to be added to this complex number.
     *  @return  <tt>this + z</tt>.
     */
    public Complex add(Complex z) {
        return new Complex(real + z.real, imag + z.imag);
    }

    /**
     *  Returns the complex number whose value is <tt>(this - z)</tt>.
     *
     *  @param   z
     *           the value to be subtracted from this complex number.
     *  @return  <tt>this - z</tt>.
     */
    public Complex subtract(Complex z) {
        return new Complex(real - z.real, imag - z.imag);
    }

    /**
     *  Returns the complex number whose value is <tt>(this * z)</tt>.
     *
     *  @param   z
     *           the value by which this complex number is to be
     *           multiplied.
     *  @return  <tt>this * z</tt>.
     */
    public Complex multiply(Complex z) {
        double re = this.real * z.real - this.imag * z.imag;
        double im = this.real * z.imag + this.imag * z.real;
        return new Complex(re, im);
    }

    /**
     *  Returns the complex number whose value is <tt>(this / z)</tt>.
     *
     *  @param   z
     *           the value by which this complex number is to be divided.
     *  @return  <tt>this / z</tt>.
     */
    public Complex divide(Complex z) {
        if (z.imag == 0.0)
            return new Complex(this.real / z.real, this.imag / z.real);
        else if (Math.abs(z.real) < Math.abs(z.imag)) {
            double p = z.real / z.imag, q = z.imag + p * z.real;
            double re = (p * this.real + this.imag) / q;
            double im = (p * this.imag - this.real) / q;
            return new Complex(re, im);
        }
        else {
            double p = z.imag / z.real, q = z.real + p * z.imag;
            double re = (this.real + p * this.imag) / q;
            double im = (this.imag - p * this.real) / q;
            return new Complex(re, im);
        }
    }

    /**
     *  Returns the absolute value (modulus) of the specified complex
     *  number.
     *
     *  @param   z
     *           a complex number.
     *  @return  the absolute value of the complex number <tt>z</tt>.
     */
    public static double abs(Complex z) {
        return Math.hypot(z.real, z.imag);
    }

    /**
     *  Returns the phase angle (argument) of the specified complex number.
     *
     *  @param   z
     *           a complex number.
     *  @return  the phase angle of the complex number <tt>z</tt>. The
     *           angle is measured in radians and its value lies in the
     *           interval (-&pi;, &pi;).
     */
    public static double angle(Complex z) {
        return Math.atan2(z.imag, z.real);
    }

    /**
     *  Returns the complex number with the specified absolute value and
     *  phase angle.
     *
     *  @param   abs
     *           the absolute value of the complex number.
     *  @param   angle
     *           the phase angle of the complex number.
     *  @return  the complex number, obtained from the specified polar
     *           coordinates.
     */
    public static Complex polar(double abs, double angle) {
        return new Complex(abs * Math.cos(angle), abs * Math.sin(angle));
    }

    /**
     *  Returns the complex conjugate of the specified complex number.
     *
     *  @param   z
     *           a complex number.
     *  @return  the complex conjugate of the complex number <tt>z</tt>.
     */
    public static Complex conj(Complex z) {
        return new Complex(z.real, -z.imag);
    }

    /**
     *  Returns the negative value of the specified complex number.
     *
     *  @param   z
     *           a complex number.
     *  @return  <tt>-z</tt>.
     */
    public static Complex minus(Complex z) {
        return new Complex(-z.real, -z.imag);
    }

    /**
     *  Returns the square root of the specified complex number.
     *
     *  @param   z
     *           a complex number.
     *  @return  the square root of the complex number <tt>z</tt>.
     */
    public static Complex sqrt(Complex z) {
        if (z.imag == 0.0) {
            if (z.real >= 0.0)
                return new Complex(Math.sqrt(z.real), 0.0);
            else
                return new Complex(0.0, Math.sqrt(-z.real));
        }
        else {
            double az = Math.hypot(z.real, z.imag);
            if (z.real >= 0.0) {
                double real = Math.sqrt(0.5 * (az + z.real));
                return new Complex(real, 0.5 * z.imag / real);
            }
            else {
                double imag = Math.sqrt(0.5 * (az - z.real));
                if (z.imag < 0.0) imag = -imag;
                return new Complex(0.5 * z.imag / imag, imag);
            }
        }
    }
}
