package ch.ffhs.jsf.bookmark.controller;

import ch.ffhs.jpa.bookmark.dao.LinkDao;
import ch.ffhs.jpa.bookmark.dao.UserDao;
import ch.ffhs.jpa.bookmark.domain.Link;
import ch.ffhs.jpa.bookmark.domain.User;
import ch.ffhs.jpa.bookmark.util.Hash;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name="userCtrl")
@RequestScoped
public class UserController implements Serializable {
    private static final long serialVersionUID = 1736371230126440L;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user = new User();

    public List<User> getUsers() {
        if (users == null) {
            users = userDao.getAll();
        }
        return users;
    }

    private List<User> users;

    public void setUserDao(UserDao linkDao) {
        this.userDao = linkDao;
    }

    @EJB
    private UserDao userDao;

    public void save() {
        String title   = "";
        String message = "";
        FacesMessage.Severity severity = FacesMessage.SEVERITY_INFO;
        try {
            this.user.setPassword(Hash.sha1(this.user.getPassword()));
            userDao.save(this.user);

            this.users = userDao.getAll();

            title = "User saved";
        } catch (Exception e) {
            title = "Error";
            message = "This user already exists in the database.";
            severity = FacesMessage.SEVERITY_ERROR;
        }

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("growl", new FacesMessage(severity, title, message));
    }

    public void toggleAdmin(ActionEvent event) {
        int id = Integer.parseInt(event.getComponent().getAttributes().get("data-id").toString());

        User user = userDao.getById(id);
        user.setAdmin(!user.isAdmin());
        userDao.save(user);

        this.users = userDao.getAll();

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Admin state changed", ""));
    }

    public void delete(ActionEvent event) {
        int id = Integer.parseInt(event.getComponent().getAttributes().get("data-id").toString());

        userDao.deleteById(id);

        this.users = userDao.getAll();

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "User deleted", ""));
    }
}
