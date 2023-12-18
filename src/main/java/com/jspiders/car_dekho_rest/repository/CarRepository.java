package com.jspiders.car_dekho_rest.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.jspiders.car_dekho_rest.pojo.CarPOJO;

@Repository
public class CarRepository {
	
	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;
	private static Query query;

	private static void openConnection() {
		factory = Persistence.createEntityManagerFactory("CarRest");
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();
	}

	private static void closeConnection() {
		if (factory != null) {
			factory.close();
		}
		if (manager != null) {
			manager.close();
		}
		if (transaction != null) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
		}
	}

	public CarPOJO addCar(CarPOJO pojo) {
		openConnection();
		transaction.begin();
		
		manager.persist(pojo);
		transaction.commit();
		closeConnection();
		return pojo;
	}

	public CarPOJO searchCar(int id) {
		openConnection();
		transaction.begin();
		
		CarPOJO car=manager.find(CarPOJO.class, id);
		transaction.commit();
		closeConnection();
		return car;
	}

	public List<CarPOJO> searchAllcars() {
		openConnection();
		transaction.begin();
		String jpql="from CarPOJO";
		query=manager.createQuery(jpql);
		List<CarPOJO> cars=query.getResultList();		
		transaction.commit();
		closeConnection();
		return cars;
	}

	public CarPOJO removeCar(int id) {
		openConnection();
		transaction.begin();
		
		CarPOJO car = manager.find(CarPOJO.class, id);
		if (car != null) {
			manager.remove(car);
			transaction.commit();
			closeConnection();
			return car;
		}
		transaction.commit();
		closeConnection();
		return null;
	}

	public CarPOJO updateCar(CarPOJO pojo) {
		openConnection();
		transaction.begin();
		CarPOJO car=manager.find(CarPOJO.class, pojo.getId());
		car.setName(pojo.getName());
		car.setBrand(pojo.getBrand());
		car.setFueltype(pojo.getFueltype());
		car.setPrice(pojo.getPrice());
		
		manager.persist(car);
		transaction.commit();
		closeConnection();
		return car;
	}
	
	


}
