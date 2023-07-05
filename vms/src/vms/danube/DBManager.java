/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vms.danube;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Rusty
 */
public class DBManager {
    EntityManagerFactory emf;
    EntityManager em;
    Tblemail c;
    
    //used for inserts
    public DBManager() {
        emf = Persistence.createEntityManagerFactory("VMSPU");
        em = emf.createEntityManager();
    }
    
    //used for update or delete
    public DBManager(Tblemail c) {
        emf = Persistence.createEntityManagerFactory("VMSPU");
        em = emf.createEntityManager();
        
        this.c = c;
    }
    
    //clean up all instances of this class
    public void close() {
        em.close();
        emf.close();
    }
    
    //insert this record
    public void insert(Tblemail c) {
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
    }
    
    //find a record by it's id
    public Tblemail findById(int id) {
        Tblemail c = em.find(Tblemail.class, id);
        
        return c;
    }
    
    //delete this record
    public void delete(Tblemail c) {
        em.getTransaction().begin();
        //this is a little weird as we have to find the object once again
        //before its deleted - required because the object has to be in
        //the same context - too complicated to explain fully here
        Tblemail contact = findById(c.getId());
        em.remove(contact);
        em.getTransaction().commit();
    }
    
    //update this record
    public void update (Tblemail c) {
        Tblemail e = em.find(Tblemail.class, c.getId());
        em.getTransaction().begin();
                
        e.setId(c.getId());
        e.setEmpid(c.getEmpid());
        e.setEmailadd(c.getEmailadd());
        
        em.getTransaction().commit();
    }
}