package PaooGame;


public class Timer {
    long delay;

    public Timer(long time) {
        SetDelay(time);
    }

    public Timer(){
        SetDelay(0);
    }

    public void SetDelay(final long delay) {
        this.delay = System.currentTimeMillis() + delay;
    }

    public boolean TimePassed() {
        if(System.currentTimeMillis() >= this.delay) {
            return true;
        }

        return false;
    }
}

