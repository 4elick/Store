package utility;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class JPAUtility {
    private static EntityManagerFactory entityManagerFactory;

    public static EntityManagerFactory getEntityManagerFactory(){
        return entityManagerFactory = Persistence.createEntityManagerFactory("com.servlets");
    }

    public static void close(){
        entityManagerFactory.close();
    }

}
