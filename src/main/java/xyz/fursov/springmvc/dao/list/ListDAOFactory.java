package xyz.fursov.springmvc.dao.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import xyz.fursov.springmvc.dao.BookDAO;
import xyz.fursov.springmvc.dao.BookDAOFactory;

@Component
public class ListBookDAOFactory implements BookDAOFactory {

    private final ApplicationContext applicationContext;

    @Autowired
    public ListBookDAOFactory() {
        this.applicationContext = applicationContext;
    }

    @Override
    public BookDAO getBookDAO() {
        return applicationContext.getBean(ListBookDAO.class);
    }
}
