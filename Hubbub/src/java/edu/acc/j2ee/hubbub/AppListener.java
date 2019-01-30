package edu.acc.j2ee.hubbub;

import java.util.Date;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        UserDao dao = new UserDaoImpl();
        String u1user = sce.getServletContext().getInitParameter("user.1.name");
        String u1pass = sce.getServletContext().getInitParameter("user.1.pass");
        String u2user = sce.getServletContext().getInitParameter("user.2.name");
        String u2pass = sce.getServletContext().getInitParameter("user.2.pass");
        User u1 = new User(u1user, u1pass, new Date(118, 5, 6));
        User u2 = new User(u2user, u2pass, new Date(119, 0, 28));
        dao.addUser(u1);
        dao.addUser(u2);
        sce.getServletContext().setAttribute("userDao", dao);
        
        PostDao postDao = new PostDaoImpl();
        Post p1 = new Post("My first Hubbub Post!", u1);
        Post p2 = new Post("Joined 'cause johndoe told me to!", u2);
        postDao.addPost(p1);
        postDao.addPost(p2);
        sce.getServletContext().setAttribute("postDao", postDao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
