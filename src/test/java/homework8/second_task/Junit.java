package homework8.second_task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Junit {


    public static void main(String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException {
        for (Method declaredMethod : SimpleTest.class.getDeclaredMethods()) {
            Test test = declaredMethod.getAnnotation(Test.class);
            if (test != null) {
                try {
                    declaredMethod.invoke(
                            SimpleTest.class.getConstructor().newInstance()
                    );
                } catch (InvocationTargetException e) {
                    if (e.getCause() instanceof AssertionError) {
                        System.out.println("Test " + declaredMethod.getName() + " failed: " + e.getCause().getMessage());
                        continue;
                    } else {
                        System.out.println("Test " + declaredMethod.getName() + " broken: " + e.getCause().getMessage());
                        continue;
                    }
                }
                System.out.println("Test " + declaredMethod.getName() + " passed!");
            }

        }

        for (Method declaredMethod : homework8.second_task.BeforeEach.class.getDeclaredMethods()) {
            BeforeEach beforeEach = declaredMethod.getAnnotation(BeforeEach.class);
            if (beforeEach != null) {
                try {
                    declaredMethod.invoke(
                            SimpleTest.class.getConstructor().newInstance()
                    );
                } catch (InvocationTargetException e) {
                    if (e.getCause() instanceof AssertionError) {
                        System.out.println("BeforeEach " + declaredMethod.getName() + " failed: " + e.getCause().getMessage());
                        continue;
                    } else {
                        System.out.println("BeforeEach " + declaredMethod.getName() + " broken: " + e.getCause().getMessage());
                        continue;
                    }
                }
                System.out.println("BeforeEach " + declaredMethod.getName() + " passed!");
            }
        }
    }
}
