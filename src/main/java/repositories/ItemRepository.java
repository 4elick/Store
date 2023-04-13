package repositories;

import entities.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class ItemRepository {
    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public ItemRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void add(Item item){
        entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(item);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Item getById(long id){
        entityManager = emf.createEntityManager();
        Item item = entityManager.find(Item.class,id);
        entityManager.close();
        return item;
    }

    public List<Item> getAll(){
        entityManager = emf.createEntityManager();
        List<Item> items = entityManager
                .createQuery("Select i from Item i", Item.class)
                .getResultList();
        entityManager.close();

        return items;
    }
    public void removeById(long id){
        entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        Item item = entityManager.find(Item.class,id);
        entityManager.remove(item);
        entityManager.getTransaction().commit();
        entityManager.close();
    }


}
