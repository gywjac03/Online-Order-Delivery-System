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
    import java.util.Date;

    
    /**
     *
     * @author ganye
     */
    @Stateless
    public class OrderItemsFacade extends AbstractFacade<OrderItems> {

        @PersistenceContext(unitName = "APU_Online_Order_Delivery_System-ejbPU")
        private EntityManager em;

        @Override
        protected EntityManager getEntityManager() {
            return em;
        }

        public OrderItemsFacade() {
            super(OrderItems.class);
        }

        public List<OrderItems> getOrdersForUser(String userID) {
            return em.createQuery("SELECT o FROM OrderItems o WHERE o.user.id = :userID", OrderItems.class)
                    .setParameter("userID", userID)
                    .getResultList();
        }

        public List<OrderItems> getOrdersByOrderIdAndUser(Long orderId, String username) {
        TypedQuery<OrderItems> query = em.createQuery("SELECT o FROM OrderItems o WHERE o.id = :orderId AND o.user.id = :username", OrderItems.class);
        query.setParameter("orderId", orderId);
        query.setParameter("username", username);
        return query.getResultList();
        }

        public List<OrderItems> getOrdersByProductNameAndUser(String productName, String username) {
            TypedQuery<OrderItems> query = em.createQuery("SELECT o FROM OrderItems o WHERE o.product.productName LIKE :productName AND o.user.id = :username", OrderItems.class);
            query.setParameter("productName", "%" + productName + "%");
            query.setParameter("username", username);
            return query.getResultList();
        }

        public void remove(OrderItems order) {
            em.remove(em.merge(order));
        }

        public List<OrderItems> getOrdersByDeliverer(String delivererId) {
            return em.createQuery("SELECT o FROM OrderItems o WHERE o.deliverer = :deliverer", OrderItems.class)
                    .setParameter("deliverer", delivererId)
                    .getResultList();
        }

        public void markAsDelivered(Long orderId) {
            OrderItems order = em.find(OrderItems.class, orderId);
            if (order != null) {
                order.setDeliveryStatus("Delivered");
                order.setDeliveryDate(new Date()); // Set current time as delivery date
                em.merge(order);
            }
        }
        
        


    }




