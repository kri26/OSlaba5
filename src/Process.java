public class Process implements Comparable<Process>{
	private int name;
	private int time;
	private boolean block;
	private boolean requestIO;

	public Process(int name, int time) {
    	this.name = name;
    	this.time = time;
    	this.block = false;
    	this.requestIO = false;
	}

	public boolean isFinished() {
		if (time <= 0){
			return true;
		}
		return false;
	}

	public int getTime() {
    	return time;
	}

	public boolean getBlock() {
		return block;
	}

	public boolean getRequestIO() {
		return requestIO;
	}

	public void setRequestIO(boolean requestIO) {
		this.requestIO = requestIO;
	}

	public void setBlock(boolean block) {
		this.block = block;
	}

	public int getName() {
		return name;
	}

	public void exec(int time) {
		this.time -= time;
	}

	@Override
	public int compareTo(Process other) {
    	return Integer.compare(this.getTime(), other.getTime());
	}
}
