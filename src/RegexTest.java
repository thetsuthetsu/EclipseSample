import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
	static final int EXIT_SUCCESS = 0;
	static final int EXIT_ERROR = 1;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("usage: RegexTest pattern src");
			System.exit(EXIT_ERROR);
		}

		// Pattern pattern = Pattern.compile(args[0],
		// Pattern.UNICODE_CHARACTER_CLASS);
		Pattern pattern = Pattern.compile(args[0]);
		Matcher matcher = pattern.matcher(args[1]);
		System.out.println(String.format("matched[%s] pattern[%s] src[%s]",
				matcher.find(), args[0], args[1]));
	}
}
