package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.BookDAO;
import lk.ijse.entity.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class BookDAOImpl implements BookDAO {

    @Override
    public String generateNewID() throws IOException {
        return null;
    }

    @Override
    public List<Book> getAll() throws Exception {
        return null;
    }

    @Override
    public boolean add(Book entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(Book entity) throws Exception {
        return false;
    }

    @Override
    public boolean exist(String id) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public Book search(String id) throws SQLException {
        return null;
    }
}
