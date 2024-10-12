import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppConfigTest {
    private static final String TEST_FILE_PATH = "test-config.json";
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
    public void testSerialization() throws IOException {
        // Create a sample AppConfig object
        AppConfig config = new AppConfig();
        config.setSomeProperty("testValue");
        config.setSomeNumber(123);

        // Serialize the AppConfig object to a file
        AppConfig.saveConfig(config, testFile);

        // Assert that the file was created
        assertTrue(testFile.exists());

        // Read the file back and verify its contents
        AppConfig loadedConfig = AppConfig.loadConfig(testFile);
        assertEquals("testValue", loadedConfig.getSomeProperty());
        assertEquals(123, loadedConfig.getSomeNumber());
    }

    @Test
    public void testDeserialization() throws IOException {
        // Create a JSON string that matches AppConfig
        String jsonContent = """
                {
                    "someProperty": "fromJson",
                    "someNumber": 456
                }
                """;

        // Write the JSON string to the test file
        java.nio.file.Files.write(testFile.toPath(), jsonContent.getBytes());

        // Deserialize the JSON file to an AppConfig object
        AppConfig config = AppConfig.loadConfig(testFile);

        // Assert the deserialized values match the expected ones
        assertEquals("fromJson", config.getSomeProperty());
        assertEquals(456, config.getSomeNumber());
    }
}
