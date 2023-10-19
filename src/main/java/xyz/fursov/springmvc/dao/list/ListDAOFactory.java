package xyz.fursov.springmvc.dao.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.fursov.springmvc.dao.BookDAO;
import xyz.fursov.springmvc.dao.DAOFactory;

@Component
public class ListDAOFactory implements DAOFactory {
    private final ListBookDAO listBookDAO;

    @Autowired
    public ListDAOFactory(ListBookDAO listBookDAO) {
        this.listBookDAO = listBookDAO;
    }

    @Override
    public BookDAO getBookDAO() {
        return listBookDAO;
    }
}
