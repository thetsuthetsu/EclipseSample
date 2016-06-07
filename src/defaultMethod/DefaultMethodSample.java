package defaultMethod;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class DefaultMethodSample {
	interface iA {
		default String myNameIs() {
			return "iA";
		}
	};

	interface iB extends iA {
		default String myNameIs() {
			return "iB";
		}
	};

	interface iC extends iA {
		default String myNameIs() {
			return "iC";
		}
	};

	interface iD extends iC {
		default String myNameIs() {
			return "iD";
		}
	};

	interface iZ {
		default String myNameIs() {
			return "iZ";
		}
	};

	class cA {
		public String myNameIs() {
			return "cA";
		}
	}

	class cB implements iA {
		public String myNameIs() {
			return "cB";
		}
	}

	@Test
	public void priorClassDefined() {
		class cB extends cA implements iB {
			String test() {
				return myNameIs();
			}
		}
		// 親classがinterfaceに優先する。
		cB cb = new cB();
		assertThat(cb.test(), is("cA"));
	}

	@Test
	public void priorMostDefined() {
		class A implements iA, iB {
			String test() {
				return myNameIs();
			}
		}
		// 最も特定的な実装が優先される。
		A a = new A();
		assertThat(a.test(), is("iB"));
	}

	@Test
	public void complieErrorWhenSamePrior() {
		/**
		 * class A implements iA, iZ { }
		 */
	}

	@Test
	public void priorDiamond() {

		// 同一優先度のダイアモンド参照はコンパイルエラー
		// class Diamond implements iB, iC {
		// }

		// class優先で、ダイアモンド問題は回避される。
		class Diamond1 extends cB implements iB {
			String test() {
				return myNameIs();
			}
		}
		Diamond1 c1 = new Diamond1();
		assertThat(c1.test(), is("cB"));

		// 特定度優先で、ダイアモンド問題は回避される。
		class Diamond2 implements iC, iD {
			String test() {
				return myNameIs();
			}
		}
		Diamond2 c2 = new Diamond2();
		assertThat(c2.test(), is("iD"));
	}
}
