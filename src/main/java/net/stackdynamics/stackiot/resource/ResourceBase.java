package net.stackdynamics.stackiot.resource;

import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;

@Produces("application/json")
public abstract class ResourceBase<T> {

  protected abstract List getTixQuery() throws SQLException, NamingException;
  protected abstract List getSingleQuery(int id) throws NamingException;
  protected abstract void createQuery(T t) throws SQLException, NamingException;
  protected abstract void deleteQuery(int id) throws SQLException, NamingException;
  protected abstract void updateQuery(T t, int id) throws SQLException, NamingException;

  protected static EntityManager getEntityManager() throws NamingException {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Tix");
    return emf.createEntityManager();
  }

  @GET
  public List<T> getList() throws SQLException, NamingException {
    List records = getTixQuery();
      return records;
  }

  @GET
  @Path("{id}")
  public List<T> getSingle(@PathParam("id") int id) throws NamingException {
    List records = getSingleQuery(id);
      return records;
  }

  @POST
  public void insertTix(T t) throws NamingException, SQLException {
    createQuery(t);
  }

  @DELETE
  @Path("{id}")
  public void deleteTix(@PathParam("id") int id) throws SQLException, NamingException {
    deleteQuery(id);
  }

  @PUT
  @Path("{id}")
  public void updateTix(T t, @PathParam("id") int id) throws SQLException, NamingException {
    updateQuery(t,id);
  }
}