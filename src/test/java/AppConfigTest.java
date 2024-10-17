import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;

@Disabled
public class AppConfigTest {
    private static final String TEST_FILE_PATH = "/home/biscuit/.config/exercism-helper/config.json";
    private File testFile;

    @BeforeEach
    public void setUp() {
        testFile = new File(TEST_FILE_PATH);
    }

    @AfterEach
    public void tearDown() {
        // Delete the test file after each test
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    @Disabled
    public void defaultConfigOnNotConfigFileFound() {

    }

    @Test
    @Disabled
    public void testDeserialization() throws IOException {
    }

    @Test
    @Disabled
    public void testSerialization() throws IOException {
    }
}
