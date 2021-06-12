package pl.nadoba.reflection.benchmark;

public class TestClass {

    public String name = "name";
    public int velocity = 11132;
    public boolean isHealthy = true;
    public double density = 12.759;
    public long budget = 7863849;

    public TestClass reference;
    public TestClass reference2;
    public TestClass reference3;
    public TestClass reference4;
    public TestClass reference5;

    public TestClass() {
        this.reference = new TestClass("example", 654, false, 6453.2, 4000);
        this.reference2 = new TestClass("sample", 123, true, 12345.67890, 4300);
        this.reference3 = new TestClass("owample", 44, false, 1, 3000);
        this.reference4 = new TestClass("trample", 110, false, 34.55554, 2000);
        this.reference5 = new TestClass("disample", 75, true, 99.99, 1200);
    }

    public TestClass(String name, int velocity, boolean isHealthy, double density, long budget) {
        this.name = name;
        this.velocity = velocity;
        this.isHealthy = isHealthy;
        this.density = density;
        this.budget = budget;
    }

    public int divideAndMeasure(String what) {
        String[] a = what.split(" ");
        return a.length;
    }

    public int square(int integer) {
        return integer * integer;
    }

    public boolean logicalOrWithIsHealthy(boolean flag) {
        return flag || isHealthy;
    }

    public double addToDensity(double a) {
        return a + density;
    }

    public void loop(int times) {
        for (int i = 0; i < times; i++) {
            int something = i + times;
        }
    }
}
