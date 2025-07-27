/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ganye
 */
@Stateless
public class UsersFacade extends AbstractFacade<UsersInfo> {

    @PersistenceContext(unitName = "APU_Online_Order_Delivery_System-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(UsersInfo.class);
    }
    
    public UsersInfo findById(String id) {
    if (id == null || id.trim().isEmpty()) {
        return null; // No valid ID provided
    }

    try {
        return em.find(UsersInfo.class, id);
    } catch (Exception e) {
        e.printStackTrace();
        return null; // Handle exceptions gracefully
    }
}
    public List<UsersInfo> findByRole(String role) {
        return em.createQuery("SELECT u FROM UsersInfo u WHERE u.roles = :role", UsersInfo.class)
                 .setParameter("role", role)
                 .getResultList();
    }  
}
