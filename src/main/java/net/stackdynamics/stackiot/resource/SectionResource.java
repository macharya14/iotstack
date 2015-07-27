package net.stackdynamics.stackiot.resource;

import java.sql.SQLException;
import static java.util.Collections.singletonList;
import java.util.List;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.ws.rs.Path;

import net.stackdynamics.stackiot.entity.Section;

@Path("/sections")
public class SectionResource extends ResourceBase<Section>{

  private EntityManager em;
  private List<Section> listSections;

  @Override
  protected List<Section> getTixQuery() throws NamingException {
    em = getEntityManager();
    em.getTransaction().begin();
    listSections = em.createQuery("SELECT e FROM Section e").getResultList();
    em.getTransaction().commit();
    em.close();
    return listSections;
  }

  @Override
  protected List getSingleQuery(int id) throws NamingException {
    em = getEntityManager();
    em.getTransaction().begin();
    listSections = singletonList(em.find(Section.class, id));
    em.getTransaction().commit();
    em.close();
    return listSections;
  }

  @Override
  protected void createQuery(Section t) throws SQLException, NamingException {
    Section event = new Section();
    em = getEntityManager();
    em.getTransaction().begin();
    event.setTitle(t.getTitle());
    event.setEid(1);
    event.setPrice(t.getPrice());
    em.persist(event);
    em.getTransaction().commit();
    em.close();
  }

  @Override
  protected void deleteQuery(int id) throws SQLException, NamingException {
    Section event = new Section();
    em = getEntityManager();
    em.getTransaction().begin();
    event = em.find(Section.class, id);
    em.remove(event);
    em.getTransaction().commit();
    em.close();
  }

  @Override
  protected void updateQuery(Section t, int id) throws SQLException, NamingException {
    Section event = new Section();
    em = getEntityManager();
    em.getTransaction().begin();
    event = em.find(Section.class, id);
    event.setTitle(t.getTitle());
    event.setEid(t.getEid());
    event.setPrice(t.getPrice());
    em.persist(event);
    em.getTransaction().commit();
    em.close();
  }
}
