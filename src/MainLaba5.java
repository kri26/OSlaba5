import java.util.Scanner;

public class MainLaba5 {

	public static void main(String[] args) {
		int NotBlock;
		int Block;
		Plan plan = new Plan(false);
		System.out.print("System start without blocking\n");
		plan.start();
		NotBlock = plan.getGlobalTimeWork();
		plan = new Plan(true);
		System.out.print("System start with blocking\n");
		plan.start();
		Block = plan.getGlobalTimeWork();
		System.out.printf("System work %d processor quant time without blocking\nSystem work %d processor quant time with blocking", NotBlock, Block);
	}

}
