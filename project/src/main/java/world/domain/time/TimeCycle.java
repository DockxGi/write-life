package world.domain.time;

import java.util.ArrayList;
import java.util.List;

/**
 * Every WorldSession has a hidden cycle. If the cycle ends then cycled events go off.
 */
public class TimeCycle {
    private int ticks;
    private static final int maxTicks = 6;

    private List<CycledEvent> events;

    public TimeCycle() {
        this.ticks = 0;
        events = new ArrayList<>();
    }

    public void addEvent(CycledEvent event){
        this.events.add(event);
    }

    public void tick(){
        if (ticks == maxTicks){
            startNewCycle();
            return;
        }
        ticks += 1;
    }

    private void startNewCycle() {
        ticks = 0;
        notifyEvents();
    }

    private void notifyEvents(){
        for (CycledEvent event : events) {
            event.onNewCycle();
        }
    }
}
