package jmockit;

public class Parent {
	public static String PARENT_CLASS_FIELD = "parent class field";
	public String parentInstanceField = "parent instance field";

	public static String parentClassMethod() {
		return "Parent#parentClassMethod()";
	}

	public String parentInstanceMethod() {
		return "Parent#parentInstanceMethod()";
	}
}
