package runtime_check;

public class Main {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("runtime.availableProcessors(): " + runtime.availableProcessors());
        System.out.println("runtime.totalMemory(): " + runtime.totalMemory());
    }
}
