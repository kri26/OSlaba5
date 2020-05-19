public class InputOutput {
    private Process process;
    private int timeToWork;

    public InputOutput(Process process, int timeToWork) {
        this.process = process;
        this.timeToWork = timeToWork;
    }

    public Process getProcess() {
        return process;
    }

    public  int getTimeToWork() {
        return timeToWork;
    }

    public boolean isFinished() {
        if (timeToWork <= 0){
            return true;
        }
        return false;
    }

    public boolean ioWork(int time) {
        if (time > timeToWork){
            return true;
        }
        timeToWork -= time;
        return false;
    }
}
