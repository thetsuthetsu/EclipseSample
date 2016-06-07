import java.io.File;
import java.io.IOException;

public class TempDir {

	public static void main(String[] args) {
		System.out.println("java.io.tmpdir:"
				+ System.getProperty("java.io.tmpdir"));
		File tmpFile = null;
		try {
			tmpFile = File.createTempFile("test", "tmp");
			System.out.println("tmpFile:" + tmpFile.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
