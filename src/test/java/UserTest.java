import com.example.main.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserTest {
    private User tUser;
    private UserDAO UserDao;
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

        UserDao = new UserDAO();
        UserDao.createTable();
        enrolmentDAO = new EnrolmentDAO();
        enrolmentDAO.createTable();
        textbookDAO = new TextbookDAO();
        textbookDAO.createTable();
    }
    @Test
    @Order(1)
    void testUserInsert()
    {
        UserDao.insert(tUser);

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
        User retrieval = UserDao.getByUser(tUser.GetUsername());
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
        UserDao.changeName(tUser,"Harvardson");
        User retrieval = UserDao.getByUser(tUser.GetUsername());
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
        UserDao.deleteUser(tUser);
        User retrieval = UserDao.getByUser(tUser.GetUsername());
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
