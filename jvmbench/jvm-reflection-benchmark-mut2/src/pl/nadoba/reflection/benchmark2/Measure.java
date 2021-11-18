package pl.nadoba.reflection.benchmark2;

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
    
    public static int cnt() {
    	int count=0;
    	boolean b=false;
    	for (int i=0;i<99;i++) {
    		b=!b;
    		if (App.arr[i] ^ App.arr[i+1]) {
    			count++;
    		}
    		//True, False, True, False, True, ...
    	}
    	return count;
    }

    public static List<Double> measureReferencesReflectionAccess(int amountOfTurns, int amountOfCalls) {
        return measureBase(amountOfTurns, amountOfCalls, new Function<TestClass, Boolean>() {
            public Boolean apply(TestClass testClass) {
            	int count=cnt();
            	
	            if (count==99) {
	                for (Field field : testClass.getClass().getFields()) {
                    	System.out.println("We are at the place for fields.");
                    	//Target Point
                    	//Without config, GraalVM will skip this for loop
                    	//Because testClass.getClass().getFields() is empty
	                    try {
	                        if (field.getName().contains("reference")) {
	                            Object o = field.get(testClass);
	                        }
	                    } catch (IllegalAccessException e) {
	                        e.printStackTrace();
	            		}
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
            	if(!App.arr[0] && App.arr[1] && App.arr[2] && App.arr[3] &&
            			!App.arr[4] && App.arr[5] && App.arr[6] && App.arr[7] &&
            			!App.arr[8] && App.arr[9] && App.arr[10] && App.arr[11] &&
            			!App.arr[12] && App.arr[13] && App.arr[14] && App.arr[15] &&
            			!App.arr[16] && App.arr[17] && App.arr[18] && App.arr[19] &&
            			!App.arr[20] && App.arr[21] && App.arr[22] && App.arr[23] &&
            			!App.arr[24] && App.arr[25] && App.arr[26] && App.arr[27] &&
            			!App.arr[28] && App.arr[29] && App.arr[30] && App.arr[31] &&
            			!App.arr[32] && App.arr[33] && App.arr[34] && App.arr[35] &&
            			!App.arr[36] && App.arr[37] && App.arr[38] && App.arr[39] &&
            			!App.arr[40] && App.arr[41] && App.arr[42] && App.arr[43] &&
            			!App.arr[44] && App.arr[45] && App.arr[46] && App.arr[47] &&
            			!App.arr[48] && App.arr[49] && App.arr[50] && App.arr[51] &&
            			!App.arr[52] && App.arr[53] && App.arr[54] && App.arr[55] &&
            			!App.arr[56] && App.arr[57] && App.arr[58] && App.arr[59] &&
            			!App.arr[60] && App.arr[61] && App.arr[62] && App.arr[63] &&
            			!App.arr[64] && App.arr[65] && App.arr[66] && App.arr[67] &&
            			!App.arr[68] && App.arr[69] && App.arr[70] && App.arr[71] &&
            			!App.arr[72] && App.arr[73] && App.arr[74] && App.arr[75] &&
            			!App.arr[76] && App.arr[77] && App.arr[78] && App.arr[79] &&
            			!App.arr[80] && App.arr[81] && App.arr[82] && App.arr[83] &&
            			!App.arr[84] && App.arr[85] && App.arr[86] && App.arr[87] &&
            			!App.arr[88] && App.arr[89] && App.arr[90] && App.arr[91] &&
            			!App.arr[92] && App.arr[93] && App.arr[94] && App.arr[95] &&
            			!App.arr[96] && App.arr[97] && App.arr[98] && App.arr[99]) {
            		//False, True, True, True, ...
			                for (Method method : testClass.getClass().getMethods()) {
			                    try {
			                    	System.out.println("We are at the place for methods.");
			                    	//Target Point
			                    	//Without config, GraalVM will skip this for loop
			                    	//Because testClass.getClass().getMethods() is empty
			                    	
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