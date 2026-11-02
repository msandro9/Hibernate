import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Ingredient;
import model.Meal;

import java.util.List;

public class Main {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("library");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Meal meal = new Meal("meal 1");
        Ingredient ing1 = new Ingredient("ing 1");
        Ingredient ing2 = new Ingredient("ing 2");
        Ingredient ing3 = new Ingredient("ing 3");

        meal.addIngredient(ing1);
        meal.addIngredient(ing2);
        meal.addIngredient(ing3);

        em.persist(meal);
        em.persist(ing1);
        em.persist(ing2);
        em.persist(ing3);

        em.getTransaction().commit();

        List<Meal> meals = em.createQuery("SELECT m FROM Meal m", Meal.class).getResultList();

        for (Meal m : meals) {
            System.out.println(m.getId() + " " + m.getName());

            for (Ingredient i : m.getIngredients()) {
                System.out.println("---" + " " + i.getId() + " " + i.getName());
            }
        }

        em.close();
        emf.close();
    }
}
