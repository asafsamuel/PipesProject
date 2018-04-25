package test;

public class MainTrain1 {

	public static void main(String[] args) {
		// design test (40 points)
		DesignTest dt=new DesignTest();
		TestSetter.setClasses(dt);
		dt.testDesign();
		System.out.println("done");
	}

}
