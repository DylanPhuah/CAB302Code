import com.example.main.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User tUser;
    private UserDAO userDao;
    private EnrolmentDAO enrolmentDAO;
    private Enrolment tEnrolment;
    private TextbookDAO textbookDAO;
    private Textbook tTextbook;

    @BeforeEach
    void setUp()
    {
        tUser = new User("a", "b", "c", "d", false);
        tTextbook = new Textbook("The adventures of Zane","EGB101","A healthy sacrifice to the tree and crocodile gods can be a good thing.");
        tEnrolment = new Enrolment("a","EGB101");

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
        assertEquals(tTextbook.GetTitle(),"The adventures of Zane");
        assertEquals(tTextbook.GetUnitCode(),"EGB101");
        assertEquals(tTextbook.GetText(),"A healthy sacrifice to the tree and crocodile gods can be a good thing.");
    }

    @Test
    @Order(0)
    void testEnrolment() {
        assertEquals(tEnrolment.GetUsername(), "a");
        assertEquals(tEnrolment.GetUnitCode(), "EGB101");
    }

    @Test
    @Order(0)
    void testTextbook() {
        {
            assertEquals(tUser.GetFName(), "a");
            assertEquals(tUser.GetUsername(),"b");
            assertEquals(tUser.GetPassword(),"c"); //very secure
            assertEquals(tUser.GetLName(),"d");
            assertEquals(tUser.GetIsTeacher(),false);
        }
    }

    @Test
    @Order(1)
    void testUserInsert()
    {
        userDao.insert(tUser);
    }
//    @Test
//    @Order(1)
//    void testTextBookInsert()
//    {
//        textbookDAO.insert(tTextbook);
//
//    }
//    @Test
//    @Order(1)
//    void testEnrolmentInsert()
//    {
//        enrolmentDAO.insert(tEnrolment);
//
//    }



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
    }
//    @Test
//    @Order(2)
//    void testEnrolmentRetrieve()
//    {
//        List<Enrolment> retrieval = enrolmentDAO.getAllByUnit("EGB101");
//        assertNotEquals(retrieval.size(),0);
//    }
//
//    @Test
//    @Order(2)
//    void testTextBookRetrieve()
//    {
//        List<Enrolment> retrieval = enrolmentDAO.getAllByUnit("EGB101");
//        assertNotEquals(retrieval.size(),0);
//    }

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
        userDao.deleteUser(tUser);
        User retrieval = userDao.getByUser(tUser.GetUsername());
        assertNull(retrieval);

    }

//    @Test
//    @Order(4)
//    void testEnrolmentDelete()
//    {
//        enrolmentDAO.insert(tEnrolment);
//        List<Enrolment> retrieval = enrolmentDAO.getAllByUnit("EGB101");
//        assertNotEquals(retrieval.size(),0);
//    }
//
//
//
//    @Test
//    @Order(4)
//    void testTextBookDelete()
//    {
//        enrolmentDAO.insert(tEnrolment);
//        List<Enrolment> retrieval = enrolmentDAO.getAllByUnit("EGB101");
//        assertNotEquals(retrieval.size(),0);
//    }
}
