public class Problem_34_JavaModules {
    public static void main(String[] args) {
        System.out.println("Java Modules Setup Guide:");
        System.out.println("-------------------------");
        System.out.println("1. Create directory structure:");
        System.out.println("   src/com.utils/module-info.java");
        System.out.println("   src/com.utils/com/utils/Utility.java");
        System.out.println("   src/com.greetings/module-info.java");
        System.out.println("   src/com.greetings/com/greetings/Main.java");
        
        System.out.println("\n2. com.utils/module-info.java:");
        System.out.println("   module com.utils { exports com.utils; }");
        
        System.out.println("\n3. com.greetings/module-info.java:");
        System.out.println("   module com.greetings { requires com.utils; }");
        
        System.out.println("\n4. Compile command:");
        System.out.println("   javac -d mods --module-source-path src --module com.greetings,com.utils");
        
        System.out.println("\n5. Run command:");
        System.out.println("   java --module-path mods -m com.greetings/com.greetings.Main");
    }
}