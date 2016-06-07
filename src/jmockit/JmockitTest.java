package jmockit;

import org.junit.Test;

import mockit.Mocked;

public class JmockitTest {
	@Mocked
	private Child child;

	@Test
	public void test() {
		System.out.println("instanceMethod() = " + child.instanceMethod());
		System.out.println("parentInstanceMethod() = "
				+ child.parentInstanceMethod());
		System.out.println("classMethod() = " + Child.classMethod());
		System.out.println("Child.parentClassMethod() = "
				+ Child.parentClassMethod());
		System.out.println("Parent.parentClassMethod() = "
				+ Parent.parentClassMethod());
	}
}
