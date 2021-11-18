package pl.nadoba.reflection.benchmark2;

import java.io.*;
import java.util.List;
import java.util.Scanner;

import static pl.nadoba.reflection.benchmark2.Measure.*;

public class App {
	
	public static boolean arr[];

    public static void main(String[] args) {
    	int index = 0;
    	while (index<args.length) {
	    	boolean[] iarr=new boolean[0];
			try {
				iarr=new boolean[100];
				for (int i=0;i<100;i++) {
					iarr[i]=Boolean.parseBoolean(args[index++]);
				}
			}
			catch(Exception e) {System.out.println("100 args needed: Boolean*100");}
			main(iarr);
    	}
    }
    
    public static void main(boolean[] iarr) {
    	arr=iarr;
	    int amountOfTurns = 1;
	    int amountOfCalls = 1;
	    boolean isPrintEnabled = false;
	
	    measure(amountOfTurns, amountOfCalls, isPrintEnabled);
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
