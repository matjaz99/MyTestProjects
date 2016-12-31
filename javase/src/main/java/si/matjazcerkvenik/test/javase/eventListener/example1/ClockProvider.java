package si.matjazcerkvenik.test.javase.eventListener.example1;

import java.util.Vector;

/**
 *  The class <tt>ClockProvider</tt> is used to fire clock events in
 *  regular time intervals.
 *
 *  @author  A. Mihev
 */
public class ClockProvider {
    private boolean active;                        // indicator of activity
    private int period;                            // clock period
    private long ticks;                            // number of ticks
    private Vector<ClockListener> clockListeners;  // vector of listeners

    /**
     *  Constructs a new <tt>ClockProvider</tt> with the period of 1
     *  second.
     */
    public ClockProvider() {
        this(1);
    }

    /**
     *  Constructs a new <tt>ClockProvider</tt> with the specified period.
     *
     *  @param   period
     *           the period of <tt>ClockProvider</tt> in milliseconds.
     *  @throws  IllegalArgumentException
     *           if the specified period is not negative.
     */
    public ClockProvider(int period) {
        if (period <= 0)
            throw new IllegalArgumentException(
                "period is not positive: " + period
            );
        clockListeners = new Vector<ClockListener>();
        this.period = period;
    }

    /**
     *  Checks if this <tt>ClockProvider</tt> is active.
     *
     *  @return  <tt>true</tt> if this <tt>ClockProvider</tt> is active;
     *           <tt>false</tt> otherwise.
     */
    public boolean isActive() {
        return active;
    }

    /**
     *  Returns the period of this <tt>ClockProvider</tt>.
     *
     *  @return  the period of this <tt>ClockProvider</tt>.
     */
    public int getPeriod() {
        return period;
    }

    /**
     *  Adds the specified clock listener.
     *
     *  @param   listener
     *           the clock listener to be added.
     */
    public synchronized void addClockListener(ClockListener listener) {
        clockListeners.add(listener);
    }

    /**
     *  Removes the specified clock listener.
     *
     *  @param   listener
     *           the clock listener to be removed.
     */
    public synchronized void removeClockListener(ClockListener listener) {
        clockListeners.remove(listener);
    }

    /**
     *  Starts this <tt>ClockProvider</tt>.
     */
    public void start() {
        ticks = 0;
        if (active) return;
        active = true;
        Thread thread = new Thread() {
            public void run() {
                while (active) {
                    ClockProvider.sleep(period);
                    if (active)
                        fire(new ClockEvent(ClockProvider.this, ++ticks));
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
    }

    /**
     *  Stops this <tt>ClockProvider</tt>.
     */
    public void stop() {
        active = false;
    }

    /**
     *  Sleeps for the specified amount of time.
     *
     *  @param   time
     *           the time to sleep.
     *  @throws  IllegalArgumentException
     *           if the specified time is negative.
     */
    private static void sleep(long time) {
        try {
            Thread.sleep(time);
        }
        catch (InterruptedException exc) {
        //  never occurs
            throw new InternalError();
        }
    }

    /**
     *  Fires the specified clock event to all registerred listeners.
     *
     *  @param   event
     *           the event to be fired.
     */
    @SuppressWarnings("unchecked")
    private void fire(ClockEvent event) {
        Vector<ClockListener> listeners;
        synchronized (this) {
            listeners = (Vector<ClockListener>) clockListeners.clone();
        }
        for (ClockListener listener : listeners) {
            listener.update(event);
        }
    }
}
