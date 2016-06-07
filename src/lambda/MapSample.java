package lambda;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class MapSample {
    final Map<String, String> map = new HashMap<String, String>();

    @Before
    public void before() {
        map.clear();
        map.put("japan", "japanese");
        map.put("usa", "english");
        map.put("china", "chinese");
    }

    @Test
    public void testForEach() {
        map.forEach((key, value) -> {
            System.out.println("key:" + key);
            System.out.println("value:" + value);
        });
    }

    public void testAllMatch() {
        Stream<Entry<String, String>> stream = map.entrySet().stream();
        assertThat(stream.allMatch(e -> e.getKey().equalsIgnoreCase("japan")), is(false));
    }

    public void testAnyMatch() {
        Stream<Entry<String, String>> stream = map.entrySet().stream();
        assertThat(stream.anyMatch(e -> e.getKey().equalsIgnoreCase("japan")), is(true));
    }
}
