import com.example.main.User;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User tUser;

    @BeforeEach
    void setUp()
    {
        tUser = new User("a", "b", "c", "d", false);
    }

    @Test
    void testUserCreation() {
        String expectedUser = "a";
        String actualUser = tUser.username;
        assertEquals(actualUser, expectedUser);

        String expectedPass = "b";
        String actualPass = tUser.password;
        assertEquals(actualPass, expectedPass);
    }
}
