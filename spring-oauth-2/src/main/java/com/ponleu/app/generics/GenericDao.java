package com.ponleu.app.generics;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<Entity> {

	public void save(Entity entity);

	public void saveOrUpdate(Entity entity);
	
	public Entity get(Serializable entityId);

	public Entity load(Serializable entityId);

	public List<Entity> getAll();

	public void update(Entity entity);

	public void delete(Entity entity);

	public void deleteById(Serializable entityId);

	public void deleteAll();

	public long count();

	public boolean exists(Serializable entityId);
}
