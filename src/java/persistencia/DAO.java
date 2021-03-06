package persistencia;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query; 

public class DAO<T>{

    private EntityManager em;
    
    public DAO(String pu) {
        if (this.em == null) {
            this.em = Persistence.createEntityManagerFactory(pu).createEntityManager();
        }
    }
   
    public void insert(T object){
        this.em.getTransaction().begin();
        this.em.persist(object);
        this.em.getTransaction().commit();
    }
    
    public void update(T object){
        this.em.getTransaction().begin();
        this.em.merge(object);
        this.em.getTransaction().commit();        
    }    
    
    public void delete(T object){
        this.em.getTransaction().begin();
        this.em.remove(object);
        this.em.getTransaction().commit();        
    }
    
    public T get(Class<T> c, long id){
        return this.em.find(c, id);
    }

    public T get(Class<T> c, int id){
        return this.em.find(c, id);
    }
    
    public List<T> getAll(Class<T> c, String sql){
        Query query = this.em.createNamedQuery(sql, c);
        return query.getResultList();
    }

    public List<T> getByNamedQuery(Class<T> c,String sql,String parametro1,long valor1,
                                                         String parametro2,String valor2){
        Query query = this.em.createNamedQuery(sql,c);
        query.setParameter(parametro1,valor1);
        query.setParameter(parametro2,valor2);
        return query.getResultList();
    }
    
    public List<T> getByNamedQuery(Class<T> c, String sql, String parametro1, String valor1){
        Query query = this.em.createNamedQuery(sql,c);
        query.setParameter(parametro1,valor1);
        return query.getResultList();
    }

    public List<T> getByNamedQuery(Class<T> c, String sql, String parametro1, Date data){
        Query query = this.em.createNamedQuery(sql,c);
        query.setParameter(parametro1,data);
        return query.getResultList();
    }
    
    public List<T> getByNamedQuery(Class<T> c, String sql){
        Query query = this.em.createNamedQuery(sql,c);
        return query.getResultList();
    }
        
    public List<T> getByNamedQuery(Class<T> c, String sql, String parametro1,int codigo){
        Query query = this.em.createNamedQuery(sql,c);
        query.setParameter(parametro1,codigo);
        return query.getResultList();
    }
    
    public void close(){
        this.em.close();
    }
}
