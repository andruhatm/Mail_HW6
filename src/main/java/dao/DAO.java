package dao;

import java.util.List;

public interface DAO<T> {

	List<T> getAll();

	T get(int id);

	void save(T entity);

	void update(T entity);

	void delete(T entity);
}
