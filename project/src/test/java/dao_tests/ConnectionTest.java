package dao_tests;

import org.junit.jupiter.api.Test;
import lab2.controller.dao.JDBCVars;

public class ConnectionTest {
    @Test
    void JDBCConnects() {
        assert (JDBCVars.testConnection());
    }
}
