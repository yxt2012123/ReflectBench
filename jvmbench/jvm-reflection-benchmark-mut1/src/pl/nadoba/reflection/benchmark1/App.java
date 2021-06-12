package pl.nadoba.reflection.benchmark1;

import java.io.*;
import java.util.List;
import java.util.Scanner;

import static pl.nadoba.reflection.benchmark1.Measure.*;

public class App {
	
	public static String s="";

    public static void main(String[] args) {
    	String is="";
		try {
			is=args[0];
		}
		catch(Exception e) {System.out.println("1 arg needed: String");}
		main (is);
    }
    public static void main(String is) {
    	s=is;
        if (s.length()>8) {

	        int amountOfTurns = 1;
	        int amountOfCalls = ((int) s.toCharArray()[0]-120>10?10:(int) s.toCharArray()[0]-120);
	        boolean isPrintEnabled = false;
	
	        measure(amountOfTurns, amountOfCalls, isPrintEnabled);
        }
    }

    private static void measure(int amountOfTurns, int amountOfCalls, boolean isPrintEnabled) {

        //List<Double> primitiveFieldTraditional = measurePrimitiveFieldTraditionalAccess(amountOfTurns, amountOfCalls);
        //List<Double> primitiveFieldReflection = measurePrimitiveFieldReflectionAccess(amountOfTurns, amountOfCalls);

        List<Double> referenceTraditional = measureReferenceTraditionalAccess(amountOfTurns, amountOfCalls);
        List<Double> referenceReflection = measureReferencesReflectionAccess(amountOfTurns, amountOfCalls);

        List<Double> methodTraditional = measureMethodsTraditionalAccess(amountOfTurns, amountOfCalls);
        List<Double> methodReflection = measureMethodsReflectionAccess(amountOfTurns, amountOfCalls);

        if (isPrintEnabled) {
            System.out.println("\tJava reflection benchmark results\n");

            //System.out.println("Accessing public field in a traditional way: \t" + getMeaningfulAverageFromList(primitiveFieldTraditional) + " ns");
            //System.out.println("Accessing public field via reflection: \t" + getMeaningfulAverageFromList(primitiveFieldReflection) + " ns");

            System.out.println("Accessing public reference in a traditional way: \t" + getMeaningfulAverageFromList(referenceTraditional) + " ns");
            System.out.println("Accessing public reference via reflection: \t" + getMeaningfulAverageFromList(referenceReflection) + " ns");

            System.out.println("Calling public method classically: \t" + getMeaningfulAverageFromList(methodTraditional) + " ns");
            System.out.println("Calling public method using reflection: \t" + getMeaningfulAverageFromList(methodReflection) + " ns");
        }
    }

}
