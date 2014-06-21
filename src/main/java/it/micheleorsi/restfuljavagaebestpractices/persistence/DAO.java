package it.micheleorsi.restfuljavagaebestpractices.persistence;

public interface DAO<T> {

	 T getById(String id);

}