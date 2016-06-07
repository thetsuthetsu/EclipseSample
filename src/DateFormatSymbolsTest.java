import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;


/**
 * Java8��DateFormatSymbols��݊��`�F�b�N.<br>
 * Calendar����ja,en���ł̏����ݒ�`��,�Ɨ��`���̏o�͑�����m�F�B-> ja,en���Ɨ��`���������Ȃ��B
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
