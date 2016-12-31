package si.matjazcerkvenik.test.javase.eventListener.example1;

import java.util.EventObject;

/**
 *  The class <tt>ClockEvent</tt> describes the clock events that are
 *  fired in regular time intervals.
 *
 *  @author  A. Mihev
 */
public class ClockEvent extends EventObject {
    private long ticks;  // current number of ticks

    /**
     *  The class fingerprint for serialization compatibility
     */
    private static final long serialVersionUID = 615066435473350628L;

    /**
     *  Constructs a new <tt>ClockEvent</tt> of the specified
     *  <tt>ClockProvider</tt> and with the current number of ticks, which
     *  may be negative.
     *
     *  @param   provider
     *           the clock provider.
     *  @param   ticks
     *           the number of ticks.
     */
    public ClockEvent(ClockProvider provider, long ticks) {
        super(provider);
        this.ticks = ticks;
    }

    /**
     *  Returns the current number of ticks.
     *
     *  @return  the current number of ticks.
     */
    public long getTicks() {
        return ticks;
    }
}
