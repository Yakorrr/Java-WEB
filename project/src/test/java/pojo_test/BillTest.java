package pojo_test;

import lab2.model.entities.Bill;
import lab2.model.entities.Request;
import lab2.model.entities.Room;

import static org.junit.jupiter.api.Assertions.fail;

public class BillTest implements PojoTestInterface {
    @Override
    public void constructorsTest() {
        try {
            Bill b = new Bill();
            assert b.getRequest() == null;

            b = new Bill(1000, true, new Request(), new Room());
            assert b.getSum() > 0;
            assert b.getRequest() != null;
            assert b.getRoom() != null;

            b = new Bill(1, 1000, false, new Request(), new Room());
            assert b.getId() >= 1;
            assert b.getSum() > 0;
            assert b.getRequest() != null;
            assert b.getRoom() != null;
        } catch (Exception e) {
            fail("These constructors shouldn't throw the exception");
        }
    }

    @Override
    public void gettersTest() {

    }

    @Override
    public void settersTest() {

    }

    @Override
    public void equalsTest() {

    }
}
