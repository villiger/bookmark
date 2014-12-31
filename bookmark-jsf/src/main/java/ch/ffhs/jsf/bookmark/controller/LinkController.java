package ch.ffhs.jsf.bookmark.controller;

import ch.ffhs.jpa.bookmark.dao.LinkDao;
import ch.ffhs.jpa.bookmark.domain.Link;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name="linkCtrl")
@RequestScoped
public class LinkController implements Serializable {
    private static final long serialVersionUID = 1736371230126440L;

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    private Link link = new Link();

    public List<Link> getLinks() {
        if (links == null) {
            links = linkDao.getAll();
        }
        return links;
    }

    private List<Link> links;

    public void setLinkDao(LinkDao linkDao) {
        this.linkDao = linkDao;
    }

    @EJB
    private LinkDao linkDao;

    public void save() {
        String title   = "";
        String message = "";
        FacesMessage.Severity severity = FacesMessage.SEVERITY_INFO;
        try {
            linkDao.save(this.link);

            this.links = linkDao.getAll();

            title = "Link saved";
        } catch (Exception e) {
            title = "Error";
            message = "This link already exists in the database.";
            severity = FacesMessage.SEVERITY_ERROR;
        }

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("growl", new FacesMessage(severity, title, message));
    }

    public void delete(ActionEvent event) {
        int id = Integer.parseInt(event.getComponent().getAttributes().get("data-id").toString());

        linkDao.deleteById(id);

        this.links = linkDao.getAll();

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Link deleted", ""));
    }
}
