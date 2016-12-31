package si.matjazcerkvenik.test.javase.lists.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  The class <tt>MinMaxList</tt> is used to store a list of
 *  at most 100 objects. It supports methods to find the
 *  minimal and maximal element of this list.
 *
 *  @version  2.0
 *  @author   A. Mihev
 */
public class MinMaxList<T> implements Iterable<T> {
    private Object[] element;  // array of elements
    private int size;          // number of elements

    /**
     *  Constructs a new <tt>MinMaxList</tt> that contains at
     *  most 100 elements.
     */
    public MinMaxList() {
        element = new Object[100];
    }

    /**
     *  Adds a new object to this <tt>MinMaxList</tt>.
     *
     *  @param  obj
     *          the object to be added to this <tt>MinMaxList</tt>.
     */
    public void add(T obj) {
        element[size++] = obj;
    }

    /**
     *  Returns the minimal element of this <tt>MinMaxList</tt>. If
     *  this <tt>MinMaxList</tt> is empty, then the method throws a
     *  <tt>NoSuchElement</tt> exception.
     *
     *  @return  the minimal element of this <tt>MinMaxList</tt>.
     *  @throws  <tt>NoSuchElement</tt> if this <tt>MinMaxList</tt>
     *           is empty.
     */
    @SuppressWarnings("unchecked")
    public T min() {
        if (size == 0)
            throw new NoSuchElementException();
        Object result = element[0];
        for (int k = 1; k < size; ++k)
            if (isLess(element[k], result))
                result = element[k];
        return (T) result;
    }

    /**
     *  Returns the maximal element of this <tt>MinMaxList</tt>. If
     *  this <tt>MinMaxList</tt> is empty, then the method throws a
     *  <tt>NoSuchElement</tt> exception.
     *
     *  @return  the maximal element of this <tt>MinMaxList</tt>.
     *  @throws  <tt>NoSuchElement</tt> if this <tt>MinMaxList</tt>
     *           is empty.
     */
    @SuppressWarnings("unchecked")
    public T max() {
        if (size == 0)
            throw new NoSuchElementException();
        Object result = element[0];
        for (int k = 1; k < size; ++k)
            if (isGreater(element[k], result))
                result = element[k];
        return (T) result;
    }

    /**
     *  Returns the iterator over all elements of this list.
     *
     *  @return  the iterator over all elements of this list.
     */
    public Iterator<T> iterator() {
        return new MinMaxListIterator();
    }

    class MinMaxListIterator implements Iterator<T> {
        private int index;

        public boolean hasNext() {
            return index < size;
        }

        @SuppressWarnings("unchecked")
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return (T) element[index++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     *  Checks if the object <tt>a</tt> is less than the
     *  object <tt>b</tt>.
     *
     *  @param   a
     *           the first object to compare.
     *  @param   b
     *           the second object to compare.
     *  @return  <tt>true</tt> if <tt>a &lt; <tt>b</tt>.
     */
    @SuppressWarnings("unchecked")
    private static boolean isLess(Object a, Object b) {
        return ((Comparable) a).compareTo(b) < 0;
    }

    /**
     *  Checks if the object <tt>a</tt> is greater than the
     *  object <tt>b</tt>.
     *
     *  @param   a
     *           the first object to compare.
     *  @param   b
     *           the second object to compare.
     *  @return  <tt>true</tt> if <tt>a &gt; <tt>b</tt>.
     */
    @SuppressWarnings("unchecked")
    private static boolean isGreater(Object a, Object b) {
        return ((Comparable) a).compareTo(b) > 0;
    }
}
