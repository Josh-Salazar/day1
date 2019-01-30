package edu.acc.j2ee.hubbub;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.length() == 0)
            action = "timeline";
        String destination;
        switch (action) {
            default:
            case "login": destination = login(request); break;
            case "logout": destination = logout(request); break;
            case "timeline": destination = timeline(request); break;
            case "join": destination = join(request); break;
            case "post": destination = post(request); break;
        }
        String redirect = this.getServletConfig().getInitParameter("redirect.tag");
        if (destination.startsWith(redirect)) {
            response.sendRedirect("main?action=" + destination.substring(
                destination.indexOf(redirect) + redirect.length()));
            return;
        }
        String viewDir = this.getServletConfig().getInitParameter("view.dir");
        String viewType = this.getServletConfig().getInitParameter("view.type");
        request.getRequestDispatcher(viewDir + destination + viewType)
                .forward(request, response);
    }
    
    private String login(HttpServletRequest request) {
        
        if (request.getSession().getAttribute("user") != null) return "timeline";
        if (request.getMethod().equalsIgnoreCase("GET")) return "login";
        
        String userText = request.getParameter("username");
        String passText = request.getParameter("password");
        
        String destination = "login";
        @SuppressWarnings("unchecked")
        UserDao dao = (UserDao)this.getServletContext().getAttribute("userDao");
        if (!dao.validate(userText, passText))
            request.setAttribute("flash", "Invalid Credentials");
        else {
            User user = dao.authenticate(userText, passText);
            if (user == null)
                request.setAttribute("flash", "Access Denied");
            else {
                request.getSession().setAttribute("user", user);
                destination = "redirect:timeline";
            }
        }
        return destination;
    }
    
    private String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:timeline";
    }
    
    private String timeline(HttpServletRequest request) {
        String pageText = request.getParameter("page");
        int page = pageText == null ? 0 : Integer.parseInt(pageText);
        String sizeText = request.getParameter("size");
        int size = Integer.parseInt(this.getServletContext().getInitParameter("page.size"));
        if (sizeText != null)
            size = Integer.parseInt(sizeText);
        List<Post> posts = this.getPostDao().findByRange(page,size);
        request.setAttribute("lastPage", posts.size() / size );
        request.setAttribute("posts", posts);
        request.setAttribute("page", page);
        request.setAttribute("size", size);
        return "timeline";
    }
    
    private String join(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null)
            return "redirect:timeline";
        if (request.getMethod().equalsIgnoreCase("GET"))
            return "join";

        String username = request.getParameter("username");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        if (!password1.equals(password2)) {
            request.setAttribute("flash", "Passwords don't match");
            return "join";
        }

        @SuppressWarnings("unchecked")
        UserDao dao = (UserDao)this.getServletContext().getAttribute("userDao");
 
        if (!dao.validate(username, password2)) {
            request.setAttribute("flash", "Username or password invalid");
            return "join";
        }

        if (dao.findByUsername(username) != null) {
            request.setAttribute("flash", "That username is taken");
            return "join";
        }
        
        User user = new User(username, password1);
        dao.addUser(user);
        request.getSession().setAttribute("user", user);
        return "redirect:timeline";
    }
    
    private String post(HttpServletRequest request) {
        @SuppressWarnings("unchecked")
        User author = (User)request.getSession().getAttribute("user");
        if (author == null)
            return "redirect:timeline";
        if (request.getMethod().equalsIgnoreCase("GET"))
            return "post";
        String content = request.getParameter("content");
        if (content == null || content.length() < 1) {
            request.setAttribute("flash", "Your post was empty");
            return "post";
        }
        if (content.length() > 255) {
            request.setAttribute("flash", "Too much post in yer post");
            request.setAttribute("content", content);
            return "post";
        }
        Post post = new Post(content, author);
        @SuppressWarnings("unchecked")
        PostDao postDao = (PostDao)this.getServletContext().getAttribute("postDao");
        postDao.addPost(post);
        return "redirect:timeline";
    }
    
    private PostDao getPostDao() {
        @SuppressWarnings("unchecked")
        PostDao dao = (PostDao)this.getServletContext().getAttribute("postDao");
        return dao;
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
