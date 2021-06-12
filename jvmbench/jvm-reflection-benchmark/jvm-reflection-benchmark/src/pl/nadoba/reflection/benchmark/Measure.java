package pl.nadoba.reflection.benchmark;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class Measure {

    private static TestClass testClass = new TestClass();

    private static synchronized List<Double> measureBase(int amountOfTurns, int amountOfCalls, Function<TestClass, Boolean> measuredOperations) {

        List<Double> measurements = new LinkedList<>();

        for (int turn = 0; turn < amountOfTurns; turn++) {

            long startNanos = System.nanoTime();
            for (int call = 0; call < amountOfCalls; call++) {
                measuredOperations.apply(testClass);
            }
            long endNanos = System.nanoTime();

            int numberOfOperations = 5;
            long timeDifference = endNanos - startNanos;

            double result = ((double) timeDifference / numberOfOperations) / amountOfCalls;

            measurements.add(result);
        }

        return measurements;
    }

    public static List<Double> measurePrimitiveFieldTraditionalAccess(int amountOfTurns, int amountOfCalls) {
        return measureBase(amountOfTurns, amountOfCalls, new Function<TestClass, Boolean>() {
            public Boolean apply(TestClass testClass) {
                long budget = testClass.budget;
                double density = testClass.density;
                boolean isHealthy = testClass.isHealthy;
                String name = testClass.name;
                int velocity = testClass.velocity;

                return true;
            }
        });
    }

    public static List<Double> measurePrimitiveFieldReflectionAccess(int amountOfTurns, int amountOfCalls) {
        return measureBase(amountOfTurns, amountOfCalls, new Function<TestClass, Boolean>() {
            public Boolean apply(TestClass testClass) {
                for (Field field : testClass.getClass().getDeclaredFields()) {
                    try {
                        if (!field.getName().contains("reference")) {
                            Object o = field.get(testClass);
                        }
                    } catch (IllegalAccessException e) {
                        //e.printStackTrace();
                    }
                }
                return true;
            }
        });
    }

    public static List<Double> measureReferenceTraditionalAccess(int amountOfTurns, int amountOfCalls) {
        return measureBase(amountOfTurns, amountOfCalls, new Function<TestClass, Boolean>() {
            public Boolean apply(TestClass testClass) {
                TestClass obj = testClass.reference;
                obj = testClass.reference2;
                obj = testClass.reference3;
                obj = testClass.reference4;
                obj = testClass.reference5;

                return true;
            }
        });
    }

    public static List<Double> measureReferencesReflectionAccess(int amountOfTurns, int amountOfCalls) {
        return measureBase(amountOfTurns, amountOfCalls, new Function<TestClass, Boolean>() {
            public Boolean apply(TestClass testClass) {
                for (Field field : testClass.getClass().getFields()) {
                    try {
                        if (field.getName().contains("reference")) {
                            Object o = field.get(testClass);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                return true;
            }
        });
    }

    public static List<Double> measureMethodsTraditionalAccess(int amountOfTurns, int amountOfCalls) {
        return measureBase(amountOfTurns, amountOfCalls, new Function<TestClass, Boolean>() {
            @Override
            public Boolean apply(TestClass testClass) {
                Object o = testClass.divideAndMeasure("asdfg");
                o = testClass.square(144);
                o = testClass.logicalOrWithIsHealthy(false);
                o = testClass.addToDensity(45.32);
                testClass.loop(1);

                return true;
            }
        });
    }

    public static List<Double> measureMethodsReflectionAccess(int amountOfTurns, int amountOfCalls) {
        return measureBase(amountOfTurns, amountOfCalls, new Function<TestClass, Boolean>() {
            public Boolean apply(TestClass testClass) {
                for (Method method : testClass.getClass().getMethods()) {
                    try {
                        switch (method.getName()) {
                            case "divideAndMeasure":
                                method.invoke(testClass, "string to divide");
                                break;
                            case "square":
                                method.invoke(testClass, 1324);
                                break;
                            case "logicalOrWithIsHealthy":
                                method.invoke(testClass, false);
                                break;
                            case "addToDensity":
                                method.invoke(testClass, 34.2);
                                break;
                            case "loop":
                                method.invoke(testClass, 1);
                                break;
                        }
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
                return true;
            }
        });
    }

    public static double getMeaningfulAverageFromList(List<Double> measurements) {

        Collections.sort(measurements);

        int length = measurements.size();
        int dropSize = length / 10;

        int beginningIndex = dropSize;
        int endingIndex = length - dropSize;

        List<Double> validResults = measurements.subList(beginningIndex, endingIndex);

        double sum = 0;

        for (Double measurement : validResults) {
            sum += measurement;
        }

        return sum / validResults.size();
    }

}
