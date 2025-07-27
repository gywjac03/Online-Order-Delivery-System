    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    package model;

    import java.util.ArrayList;
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
    public class ProductFacade extends AbstractFacade<Product> {


        @PersistenceContext(unitName = "APU_Online_Order_Delivery_System-ejbPU")
        private EntityManager em;

        @Override
        protected EntityManager getEntityManager() {
            return em;
        }

        public ProductFacade() {
            super(Product.class);
        }

            public void create(Product product) {
            em.persist(product);
        }

        public void edit(Product product) {
            em.merge(product);
        }

        public Product find(Long id) {
            return em.find(Product.class, id);
        }

        public void remove(Product product) {
        em.remove(em.merge(product)); // Ensure entity is managed before removal
        }


        public List<Product> searchProducts(String query) {
        return em.createQuery("SELECT p FROM Product p WHERE LOWER(p.productName) LIKE :query OR LOWER(p.category) LIKE :query", Product.class)
                .setParameter("query", "%" + query.toLowerCase() + "%")
                .getResultList();
        }

        public List<Product> findProductsByCategory(String category) {
        return em.createQuery("SELECT p FROM Product p WHERE p.category = :category", Product.class)
                .setParameter("category", category)
                .getResultList();
        }

        public Product findProductById(int id) {
        return em.find(Product.class, id);
        }

        public void deleteProduct(int id) {
            Product product = em.find(Product.class, id);
            if (product != null) {
                em.remove(product);
            }
        }

    public List<Product> searchProducts(String searchName, String searchCategory) {
        StringBuilder queryBuilder = new StringBuilder("SELECT p FROM Product p WHERE 1=1");

        if (searchName != null && !searchName.trim().isEmpty()) {
            queryBuilder.append(" AND LOWER(p.productName) LIKE :name");
        }
        if (searchCategory != null && !searchCategory.trim().isEmpty() && !"All".equals(searchCategory)) {
            queryBuilder.append(" AND LOWER(p.category) LIKE :category");
        }

        TypedQuery<Product> query = em.createQuery(queryBuilder.toString(), Product.class);

        if (searchName != null && !searchName.trim().isEmpty()) {
            query.setParameter("name", "%" + searchName.toLowerCase() + "%");
        }
        if (searchCategory != null && !searchCategory.trim().isEmpty() && !"All".equals(searchCategory)) {
            query.setParameter("category", "%" + searchCategory.toLowerCase() + "%");
        }

        return query.getResultList();
    }


    public List<Product> findByNameOrCategory(String searchName, String searchCategory) {
        StringBuilder query = new StringBuilder("SELECT p FROM Product p WHERE 1=1");

        if (searchName != null && !searchName.trim().isEmpty()) {
            query.append(" AND LOWER(p.productName) LIKE :name");
        }
        if (searchCategory != null && !searchCategory.trim().isEmpty() && !"All".equals(searchCategory)) {
            query.append(" AND LOWER(p.category) = :category");
        }

        try {
            TypedQuery<Product> q = em.createQuery(query.toString(), Product.class);

            if (searchName != null && !searchName.trim().isEmpty()) {
                q.setParameter("name", "%" + searchName.toLowerCase() + "%");
            }
            if (searchCategory != null && !searchCategory.trim().isEmpty() && !"All".equals(searchCategory)) {
                q.setParameter("category", searchCategory.toLowerCase());
            }

            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
