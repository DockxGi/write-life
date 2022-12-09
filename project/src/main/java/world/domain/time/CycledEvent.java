package world.domain.time;

/**
 * An interface for events that happen at every new cycle.
 */
public interface CycledEvent {
    /**
     * Executes whatever needs to happen when a new cycle starts.
     */
    void onNewCycle();
}
