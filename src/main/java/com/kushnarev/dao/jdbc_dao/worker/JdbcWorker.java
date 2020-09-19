package com.kushnarev.dao.jdbc_dao.worker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public interface JdbcWorker {
    Connection getConnection() throws IOException, SQLException;
}
