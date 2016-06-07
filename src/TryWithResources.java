import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TryWithResources {

	private void doReader() {
		try (BufferedReader br = new BufferedReader(new FileReader(new File(
				"src", this.getClass().getName() + ".java")))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TryWithResources sample = new TryWithResources();
		sample.doReader();
	}
}
