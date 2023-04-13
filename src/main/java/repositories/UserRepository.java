package repositories;

import entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;

import java.util.List;

public class UserRepository {
    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public UserRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void add(User user) {
        entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public User getById(long id) {
        entityManager = emf.createEntityManager();
        User user = entityManager.find(User.class, id);
        entityManager.close();
        return user;
    }

    public List<User> getAll() {
        entityManager = emf.createEntityManager();
        List<User> users = entityManager
                .createQuery("Select u from User u", User.class)
                .getResultList();
        entityManager.close();

        return users;
    }

    public User getByPassword(String encPassword){
        entityManager = emf.createEntityManager();
        User user = entityManager
                .createQuery("SELECT u from User u " +
                        "where u.password =" + encPassword,User.class)
                .getSingleResult();
        entityManager.close();
        return user;
    }

    public User getByPasswordAndEmail(String email,String encPassword){
        try {
            entityManager = emf.createEntityManager();
            User user = entityManager
                    .createQuery(String.format("SELECT u from User u where" +
                            " u.password ='%s' and u.email = '%s'", encPassword, email),User.class)
                    .getSingleResult();
            entityManager.close();
            return user;
        }catch (NoResultException e){
            return null;
        }


    }

    public void removeById(long id){
        entityManager = emf.createEntityManager();
        User user = entityManager.find(User.class,id);
        entityManager.remove(user);
        entityManager.close();
    }
}
