import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Random;

public class Plan {
	public static final int QUANT = 5;
	public static final int COUNT_PROCESS = 2;
	public static final int TIME_TO_WORK_PROCESS = 10;

	private Deque<Process> processes;
	private Deque<InputOutput> ioSequence;
	private int globalTimeWork = 0;
	private boolean activeBlock;

	public Plan(boolean activeBlock) {
    	this.processes = new ArrayDeque<>();
    	this.ioSequence = new ArrayDeque<>();
    	this.activeBlock = activeBlock;

    	Random random = new Random();
    	List<Process> processesArrayList = new ArrayList<>();
    	int processesCount = COUNT_PROCESS;
    	for (int i = 0; i < processesCount; ++i) {
        	processesArrayList.add(new Process(i, Math.abs(random.nextInt()) % TIME_TO_WORK_PROCESS + TIME_TO_WORK_PROCESS));
    	}
    	Collections.sort(processesArrayList);

    	this.processes.addAll(processesArrayList);
	}

	public void start() {
    	while (!processes.isEmpty() || !ioSequence.isEmpty()) {
        	Process process = processes.poll();
			Random random = new Random();
			int rand = Math.abs(random.nextInt()) % 2;
			if (rand == 1 && process != null && !process.getRequestIO()){
				process.setBlock(true);
				process.setRequestIO(true);
				ioSequence.add(new InputOutput(process, TIME_TO_WORK_PROCESS * 10));
				if(!activeBlock) {
					InputOutput ioWork = ioSequence.poll();
					globalTimeWork += ioWork.getTimeToWork();
					process = ioWork.getProcess();
					process.setBlock(false);
					processes.addFirst(process);
					System.out.printf("Process number %4s terminated with IO resource\n", process.getName());
				}
			}
			else {
				if (!ioSequence.isEmpty()) {
					if (ioSequence.getFirst().getTimeToWork() > QUANT) {
						ioSequence.getFirst().ioWork(QUANT);
					}
					else {
						if (process != null) {
							process.exec(ioSequence.getFirst().getTimeToWork());
						}
						process = ioSequence.poll().getProcess();
						processes.addFirst(process);
						System.out.printf("Process number %4s terminated with IO resource\n", process.getName());
						continue;
					}
				}
				if (process != null) {
					globalTimeWork += Math.min(process.getTime(), QUANT);
					process.exec(QUANT);
					if (!process.isFinished()) {
						processes.add(process);
					} else {
						System.out.printf("Process number %4s finished\n", process.getName());
					}
				}
				else {
					globalTimeWork += QUANT;
				}
			}
		}
    	System.out.printf("Task is complited\n");
	}

	public int getGlobalTimeWork() {
		return globalTimeWork;
	}
}
