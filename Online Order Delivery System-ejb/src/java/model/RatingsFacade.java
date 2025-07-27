/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ganye
 */
@Stateless
public class RatingsFacade extends AbstractFacade<Ratings> {

    @PersistenceContext(unitName = "APU_Online_Order_Delivery_System-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RatingsFacade() {
        super(Ratings.class);
    }
    
}
