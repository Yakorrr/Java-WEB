package util_test;

import lab2.controller.util.DateUtil;
import org.junit.Test;

import java.sql.Date;

public class DateUtilTest {
    @Test(expected = NullPointerException.class)
    public void startNullTest() {
        DateUtil.coherentDates(null, Date.valueOf("2011-01-01"));
    }

    @Test(expected = NullPointerException.class)
    public void endNullTest() {
        DateUtil.coherentDates(Date.valueOf("2011-01-01"), null);
    }

    @Test(expected = NullPointerException.class)
    public void bothNullTest() {
        assert DateUtil.coherentDates(null, null);
    }

    @Test
    public void sameDatesTest() {
        assert DateUtil.coherentDates(Date.valueOf("2011-01-01"), Date.valueOf("2011-01-01"));
    }

    @Test
    public void validDatesTest() {
        assert DateUtil.coherentDates(Date.valueOf("2011-01-01"), Date.valueOf("2011-01-09"));
    }

    @Test
    public void invalidDatesTest() {
        assert !DateUtil.coherentDates(Date.valueOf("2011-01-09"), Date.valueOf("2011-01-01"));
    }
}
