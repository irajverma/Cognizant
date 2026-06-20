import java.lang.reflect.Method;

public class Problem_39_ReflectionJava {
    public void secretMethod(String message) {
        System.out.println("Secret method invoked with message: " + message);
    }

    public static void main(String[] args) {
        try {
            // Load class using Reflection API
            Class<?> clazz = Class.forName("Problem_39_ReflectionJava");
            Object instance = clazz.getDeclaredConstructor().newInstance();
            
            // Get method and print names and parameters
            Method[] methods = clazz.getDeclaredMethods();
            for (Method m : methods) {
                System.out.println("Method found: " + m.getName() + ", Parameter Count: " + m.getParameterCount());
            }
            
            // Invoke method dynamically
            Method secret = clazz.getDeclaredMethod("secretMethod", String.class);
            secret.invoke(instance, "Reflection is powerful!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}