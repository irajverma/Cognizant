public class Problem_37_JavapInspectBytecode {
    public void calculate() {
        int x = 10;
        int y = 20;
        int sum = x + y;
    }
    
    public static void main(String[] args) {
        System.out.println("Instructions to inspect bytecode:");
        System.out.println("1. Compile class: javac Problem_37_JavapInspectBytecode.java");
        System.out.println("2. Run javap tool: javap -c Problem_37_JavapInspectBytecode");
    }
}