package dao;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface DAO<T> {

	@NotNull
	List<T> getAll();

	@NotNull
	T get(int id);

	boolean save(T entity);

	boolean update(T entity);

	boolean delete(int id);
}
