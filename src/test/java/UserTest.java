import com.example.main.*;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User tUser;
    private UserDAO userDao;
    private EnrolmentDAO enrolmentDAO;
    private Enrolment tEnrolment;
    private TextbookDAO textbookDAO;
    private Textbook tTextbook;
    String userName = getRandomString(5);
    String userPassword = getRandomString(5);
    String userFirstName = getRandomString(5);
    String userLastName = getRandomString(5);
    int userTextPreference = 14;
    String textBookTitle = getRandomString(5);
    String textBookUnitCode = getRandomString(5);
    String textBookText = getRandomString(5);
    String enrolmentUserName = getRandomString(5);
    String enrolmentUnitCode = getRandomString(5);

    public static String getRandomString(int length) { //Random string generator for tests
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();

        return random.ints(length, 0, characters.length())
                .mapToObj(characters::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    @BeforeEach
    void setUp()
    {
        tUser = new User(userName, userPassword, userFirstName, userLastName, false, userTextPreference);
        tTextbook = new Textbook(textBookTitle,textBookUnitCode,textBookText);
        tEnrolment = new Enrolment(enrolmentUserName,enrolmentUnitCode);

        userDao = new UserDAO();
        userDao.createTable();
        enrolmentDAO = new EnrolmentDAO();
        enrolmentDAO.createTable();
        textbookDAO = new TextbookDAO();
        textbookDAO.createTable();
    }

    @Test
    @Order(0)
    void testUser() {
        {
            assertEquals(tUser.GetFName(), userFirstName);
            assertEquals(tUser.GetUsername(),userName);
            assertEquals(tUser.GetPassword(),userPassword); //very secure
            assertEquals(tUser.GetLName(),userLastName);
            assertEquals(tUser.GetIsTeacher(),false);
            assertEquals(tUser.GetTextPreference(), userTextPreference);
        }
    }

    @Test
    @Order(0)
    void testTextbook() {
        assertEquals(tTextbook.GetTitle(),textBookTitle);
        assertEquals(tTextbook.GetUnitCode(),textBookUnitCode);
        assertEquals(tTextbook.GetText(),textBookText);
    }

    @Test
    @Order(0)
    void testEnrolment() {
        assertEquals(tEnrolment.GetUsername(), enrolmentUserName);
        assertEquals(tEnrolment.GetUnitCode(), enrolmentUnitCode);
    }

    @Test
    @Order(1)
    void testUserInsert()
    {
        userDao.insert(tUser);
    }

    @Test
    @Order(1)
    void testTextBookInsert()
    {
        textbookDAO.insert(tTextbook);
    }

    @Test
    @Order(1)
    void testEnrolmentInsert()
    {
        enrolmentDAO.insert(tEnrolment);

    }

    @Test
    @Order(2)
    void testUserRetrieve()
    {
        User retrieval = userDao.getByUser(tUser.GetUsername());
        assertEquals(tUser.GetFName(),retrieval.GetFName());
        assertEquals(tUser.GetUsername(),retrieval.GetUsername());
        assertEquals(tUser.GetPassword(),retrieval.GetPassword()); //very secure
        assertEquals(tUser.GetLName(),retrieval.GetLName());
        assertEquals(tUser.GetIsTeacher(),retrieval.GetIsTeacher());
        assertEquals(tUser.GetTextPreference(), retrieval.GetTextPreference());
    }

    @Test
    @Order(2)
    void testEnrolmentRetrieve()
    {
        List<Enrolment> retrieval = enrolmentDAO.getAllByUnit(enrolmentUnitCode);
        assertEquals(retrieval.size(),1);
        Enrolment RetrievedEnrolment = retrieval.getFirst();
        assertEquals(RetrievedEnrolment.GetUnitCode(),enrolmentUnitCode);
        assertEquals(RetrievedEnrolment.GetUsername(),enrolmentUserName);
    }

    @Test
    @Order(2)
    void testTextBookRetrieve()
    {
        List<Textbook> retrieval = textbookDAO.getAllByUnit(textBookUnitCode);
        assertEquals(retrieval.size(),1);
        Textbook RetrievedTextBook = retrieval.getFirst();
        assertEquals(RetrievedTextBook.GetText(),textBookText);
        assertEquals(RetrievedTextBook.GetTitle(),textBookTitle);
        assertEquals(RetrievedTextBook.GetUnitCode(),textBookUnitCode);

    }

    @Test
    @Order(3)
    void testUserUpdate()
    {
        userDao.changeName(tUser,"Harvardson");
        User retrieval = userDao.getByUser(tUser.GetUsername());
        assertEquals(retrieval.GetFName(),"Harvardson");
    }

//    @Test
//    @Order(3)
//    void testEnrolmentUpdate()
//    {
//        enrolmentDAO.insert(tEnrolment);
//        List<Enrolment> retrieval = enrolmentDAO.getAllByUnit("EGB101");
//        assertNotEquals(retrieval.size(),0);
//    }
//
//
//
//    @Test
//    @Order(3)
//    void testTextBookUpdate()
//    {
//        enrolmentDAO.insert(tEnrolment);
//        List<Enrolment> retrieval = enrolmentDAO.getAllByUnit("EGB101");
//        assertNotEquals(retrieval.size(),0);
//    }

    @Test
    @Order(4)
    void testUserDelete()
    {
        userDao.delete(tUser);
        User retrieval = userDao.getByUser(tUser.GetUsername());
        assertNull(retrieval);
    }

    @Test
    @Order(4)
    void testEnrolmentDelete()
    {
        enrolmentDAO.delete(tEnrolment);
        List<Enrolment> retrieval = enrolmentDAO.getAllByUnit(enrolmentUnitCode);
        assertTrue(retrieval.isEmpty());
    }

    @Test
    @Order(4)
    void testTextBookDelete()
    {
        textbookDAO.delete(tTextbook);
        List<Textbook> retrieval = textbookDAO.getAllByUnit(textBookUnitCode);
        assertTrue(retrieval.isEmpty());
    }
}
