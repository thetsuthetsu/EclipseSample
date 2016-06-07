package jmockit;

public class Child extends Parent {
	public static int CLASS_FIELD = -1;
	public int instanceField = -1;

	public static int classMethod() {
		return -1;
	}

	public int instanceMethod() {
		return -1;
	}

	public final int finalInstanceMethod() {
		return -1;
	}
}
