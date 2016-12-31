package si.matjazcerkvenik.test.javase.eventListener.example1;


public class ClockConsumer {
    public static void main(String[] args) throws Exception {
        ClockProvider provider = new ClockProvider(2000);
        provider.addClockListener(new ClockHandler());
        provider.start();
        System.out.println("provider started ...");
        Thread.sleep(7000);
        provider.stop();
        System.out.println("provider stopped ...");
        Thread.sleep(5000);
        provider.start();
        System.out.println("provider started ...");
        Thread.sleep(5000);
    }

    static class ClockHandler implements ClockListener {
        public void update(ClockEvent event) {
            System.out.println(event.getTicks());
        }
    }
}
