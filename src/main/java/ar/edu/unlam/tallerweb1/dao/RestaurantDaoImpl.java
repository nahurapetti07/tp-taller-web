package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.Restaurant;
import ar.edu.unlam.tallerweb1.modelo.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("restaurantDao")
public class RestaurantDaoImpl implements RestaurantDao {

    @Inject
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<Restaurant> obtenerRestaurants() {
        return (List<Restaurant>) sessionFactory.openSession().createCriteria(Restaurant.class).list();
    }

    @Override
    public Restaurant obtenerRestaurantPorNombre(String nombre) throws Exception{
        Restaurant restaurant = (Restaurant) sessionFactory.openSession()
                .createCriteria(Restaurant.class)
                .add(Restrictions.eq("nombre", nombre))
                .uniqueResult();

        if(restaurant == null) throw new Exception("Restaurant not found");
        return restaurant;
    }

    public Restaurant obtenerRestaurantPorId(Long id) throws Exception{
        Restaurant restaurant = (Restaurant) sessionFactory.openSession()
                .createCriteria(Restaurant.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
        if(restaurant == null) throw new Exception("Restaurant not found");
        return restaurant;
    }

    @SuppressWarnings("unchecked")
    public List<Restaurant> obtenerListaDeRestaurantsPorNombre(String nombre){
        return (List<Restaurant>) sessionFactory
                .openSession()
                .createCriteria(Restaurant.class)
                .add(Restrictions.like("nombre", nombre, MatchMode.ANYWHERE))
                .list();
    }

    @SuppressWarnings("unchecked")
    public List<String> obtenerListaDeCategorias(){
        return (List<String>) sessionFactory
                .openSession()
                .createCriteria(Restaurant.class)
                .setProjection(Projections.property("tipo"))
                .setProjection(Projections.distinct(Projections.property("tipo")))
                .list();
    }

    @SuppressWarnings("unchecked")
    public List<Restaurant> obtenerListaDeRestaurantsPorCategoria(String categoria){
        return (List<Restaurant>) sessionFactory
                .openSession()
                .createCriteria(Restaurant.class)
                .add(Restrictions.eq("tipo", categoria))
                .list();
    }
}
