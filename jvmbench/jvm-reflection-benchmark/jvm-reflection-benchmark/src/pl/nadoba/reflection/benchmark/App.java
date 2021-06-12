package pl.nadoba.reflection.benchmark;

import java.util.List;
import static pl.nadoba.reflection.benchmark.Measure.*;

public class App {

    public static void main(String[] args) {

        if (args.length != 3) {
            throw new RuntimeException("There should be 3 arguments specified:\n - amount of turns \n - amount of calls in a turn \n - printing switch [false/true]]\nFor example `run 1 10000 false`");
        }

        int amountOfTurns = Integer.valueOf(args[0]);
        int amountOfCalls = Integer.valueOf(args[1]);
        boolean isPrintEnabled = Boolean.valueOf(args[2]);

        measure(amountOfTurns, amountOfCalls, isPrintEnabled);
    }

    private static void measure(int amountOfTurns, int amountOfCalls, boolean isPrintEnabled) {

        List<Double> primitiveFieldTraditional = measurePrimitiveFieldTraditionalAccess(amountOfTurns, amountOfCalls);
        List<Double> primitiveFieldReflection = measurePrimitiveFieldReflectionAccess(amountOfTurns, amountOfCalls);

        List<Double> referenceTraditional = measureReferenceTraditionalAccess(amountOfTurns, amountOfCalls);
        List<Double> referenceReflection = measureReferencesReflectionAccess(amountOfTurns, amountOfCalls);

        List<Double> methodTraditional = measureMethodsTraditionalAccess(amountOfTurns, amountOfCalls);
        List<Double> methodReflection = measureMethodsReflectionAccess(amountOfTurns, amountOfCalls);

        if (isPrintEnabled) {
            System.out.println("\tJava reflection benchmark results\n");

            System.out.println("Accessing public field in a traditional way: \t" + getMeaningfulAverageFromList(primitiveFieldTraditional) + " ns");
            System.out.println("Accessing public field via reflection: \t" + getMeaningfulAverageFromList(primitiveFieldReflection) + " ns");

            System.out.println("Accessing public reference in a traditional way: \t" + getMeaningfulAverageFromList(referenceTraditional) + " ns");
            System.out.println("Accessing public reference via reflection: \t" + getMeaningfulAverageFromList(referenceReflection) + " ns");

            System.out.println("Calling public method classically: \t" + getMeaningfulAverageFromList(methodTraditional) + " ns");
            System.out.println("Calling public method using reflection: \t" + getMeaningfulAverageFromList(methodReflection) + " ns");
        }
    }

}
