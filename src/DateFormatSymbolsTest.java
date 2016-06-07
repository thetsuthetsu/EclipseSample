import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;


/**
 * Java8のDateFormatSymbols非互換チェック.<br>
 * Calendarからja,en環境での書式設定形式,独立形式の出力相違を確認。-> ja,en共独立形式を持たない。
 * @author hino
 */
public class DateFormatSymbolsTest {
	private static void testDisplayName(Locale locale) {
		Calendar cal = Calendar.getInstance();
		System.out.println(
			cal.getDisplayName(Calendar.MONTH, Calendar.SHORT, locale) + ":" +
			cal.getDisplayName(Calendar.MONTH, Calendar.SHORT_STANDALONE, locale) 
		);
		
		System.out.println(
				cal.getDisplayName(Calendar.MONTH, Calendar.LONG, locale) + ":" +
				cal.getDisplayName(Calendar.MONTH, Calendar.LONG_STANDALONE, locale) 
			);
		System.out.println(
				cal.getDisplayName(Calendar.MONTH, Calendar.NARROW_FORMAT, locale) + ":" +
				cal.getDisplayName(Calendar.MONTH, Calendar.NARROW_STANDALONE, locale) 
			);
	}
	
	private static void testDateFormatSymbols(Locale locale) {
		DateFormatSymbols dfs = new DateFormatSymbols(locale);
		System.out.println("Long format---");
		for(String m : dfs.getMonths()) {
			System.out.print(m + ",");
		}
		System.out.println();
		
		System.out.println("Short format---");
		for(String m : dfs.getShortMonths()) {
			System.out.print(m + ",");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		testDisplayName(Locale.JAPANESE);
		testDisplayName(Locale.ENGLISH);
		
		testDateFormatSymbols(Locale.JAPANESE);
		testDateFormatSymbols(Locale.ENGLISH);
	}
}
