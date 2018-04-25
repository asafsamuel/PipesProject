package test;

public class MainTest1 {

	public static void main(String[] args) {
		// design test (40 points)
		DesignTest dt=new DesignTest();
		TestSetter.setClasses(dt);
		dt.testDesign4test();
		
		System.out.println("done");
	}

}
