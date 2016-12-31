package si.matjazcerkvenik.test.javase.eventListener.example1;

import java.util.EventListener;

/**
 *  The <tt>ClockListener</tt> interface serves for listening to the
 *  clock events that are fired in regular time intervals.
 *
 *  @author  A. Mihev
 */
public interface ClockListener extends EventListener {
    /**
     *  Reports the occurrence of a new clock event.
     *
     *  @param   event
     *           the clock event that has been fired.
     */
    void update(ClockEvent event);
}
