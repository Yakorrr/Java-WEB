package dao_tests;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import lab2.controller.dao.BillDAO;
import lab2.controller.dao.RequestDAO;
import lab2.controller.dao.RoomDAO;
import lab2.model.entities.Bill;
import lab2.model.entities.Request;
import lab2.model.entities.Room;

import java.util.List;

class BillDAOTest {
    private static final Logger logger = Logger.getLogger(BillDAOTest.class);
    private static List<Bill> before;
    private static BillDAO billDAO;
    private static Room roomTestObject;
    private static Request requestTestObject;
    private static Bill requestBillObject;

    @BeforeAll
    static void setUp() {
        billDAO = new BillDAO();

        roomTestObject = RoomDAOTest.getTestObject();
        TestUtil.insertObject(roomTestObject, new RoomDAO());

        requestTestObject = RequestDAOTest.getTestObject();
        TestUtil.insertObject(requestTestObject, new RequestDAO());

        requestBillObject = BillDAOTest.getTestObject();
        TestUtil.insertObject(requestBillObject, new BillDAO());

        logger.info("Starting test suite: before");
        before = billDAO.selectAll();

        for (Bill b : before) {
            logger.info("Contains entry: " + b);
        }

    }

    static Bill getTestObject() {
        return new Bill(11.11, true,
                requestTestObject,
                roomTestObject);
    }

    @Test
    void selectAllTest() {
        assert (billDAO.selectAll() != null);
        int size = billDAO.selectAll().size();

        Bill test = getTestObject();
        billDAO.insert(test);
        int id = billDAO.findId(test);
        assert (id != -1);

        assert (billDAO.selectAll().size() == (size + 1));

        billDAO.delete(id);
        id = billDAO.findId(test);

        assert (id == -1);
        assert (billDAO.selectAll().size() == (size));
    }

    @Test
    void insertDeleteFindTest() {
        Bill test = getTestObject();
        billDAO.insert(test);

        int id = billDAO.findId(test);
        assert (id != -1);

        billDAO.delete(id);
        id = billDAO.findId(test);
        assert (id == -1);
    }

    @Test
    void updateTest() {
        Bill test = getTestObject();
        billDAO.insert(test);

        int id = billDAO.findId(test);
        assert (id != -1);
        test.setId(id);

        test.setSum(200.11);
        test.setPaid(true);
        billDAO.update(test);

        int id2 = billDAO.findId(test);
        assert (id == id2);

        billDAO.delete(id);
        id = billDAO.findId(test);
        assert (id == -1);
    }

    @Test
    void selectByIdTest() {
        Bill test = getTestObject();
        billDAO.insert(test);

        int id = billDAO.findId(test);
        assert (id != -1);
        test.setId(id);

        Bill testCopy = billDAO.selectById(id);

        assert (test.equals(testCopy));

        billDAO.delete(id);
        id = billDAO.findId(test);
        assert (id == -1);
    }

    @AfterAll
    static void after() {
        logger.info("After executing test suite:");
        List<Bill> after = billDAO.selectAll();
        assert (before.equals(after));
        logger.info("The database hasn't changed!");

        TestUtil.deleteObject(roomTestObject, new RoomDAO());
        TestUtil.deleteObject(requestTestObject, new RequestDAO());
        TestUtil.deleteObject(requestBillObject, new BillDAO());

        for (Bill b : after) {
            logger.info("Contains entry: " + b);
        }
    }
}
