package net.stackdynamics.stackiot.resource;

import java.sql.SQLException;
import static java.util.Collections.singletonList;
import java.util.List;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import net.stackdynamics.stackiot.entity.Event;

@Path("/event")
@Produces("application/json")
public class EventResource extends ResourceBase<Event> {

  private EntityManager em;
  private List<Event> listEvents;

  @Override
  protected List<Event> getTixQuery() throws NamingException {
    em = getEntityManager();
    em.getTransaction().begin();
    listEvents = em.createQuery("SELECT e FROM Event e").getResultList();
    em.getTransaction().commit();
    em.close();
    return listEvents;
  }

  @Override
  protected List getSingleQuery(int id) throws NamingException {
    em = getEntityManager();
    em.getTransaction().begin();
    listEvents = singletonList(em.find(Event.class, id));
    em.getTransaction().commit();
    em.close();
    return listEvents;
  }

  @Override
  protected void createQuery(Event t) throws SQLException, NamingException {
    Event event = new Event();
    em = getEntityManager();
    em.getTransaction().begin();
    event.setTitle(t.getTitle());
    event.setDesc("asdfghjkl");
    event.setUid(t.getUid());
    event.setCreated("1410876904");
    em.persist(event);
    em.getTransaction().commit();
    em.close();
  }

  @Override
  protected void deleteQuery(int id) throws SQLException, NamingException {
    Event event = new Event();
    em = getEntityManager();
    em.getTransaction().begin();
    event = em.find(Event.class, id);
    em.remove(event);
    em.getTransaction().commit();
    em.close();
  }

  @Override
  protected void updateQuery(Event t, int id) throws SQLException, NamingException {
    Event event = new Event();
    em = getEntityManager();
    em.getTransaction().begin();
    event = em.find(Event.class, id);
    event.setTitle(t.getTitle());
    event.setDesc(t.getDesc());
    event.setUid(t.getUid());
    event.setCreated(t.getCreated());
    em.persist(event);
    em.getTransaction().commit();
    em.close();
  }
}