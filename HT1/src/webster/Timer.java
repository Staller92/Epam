package webster;

public class Timer {

    private long startTime;
    private long stopTime;

    public void start() {
        this.startTime = System.nanoTime();
    }

    public void stop() {
        this.stopTime = System.nanoTime();
    }
    public double getTime() {
        return (double) (this.stopTime - this.startTime) / 1000000000; 	// convert from nanoseconds to seconds
    }

}