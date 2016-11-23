package com.ponleu.app.generics;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.util.ReflectionUtils;

public class GenericDaoImpl<Entity> implements GenericDao<Entity> {

	@Inject
	private SessionFactory sessionFactory;
	private Class<Entity> domainClass;

	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	private Class<Entity> getDomainClass() {
		if (domainClass == null) {
			ParameterizedType thisType = (ParameterizedType) this.getClass().getGenericSuperclass();
			this.domainClass = (Class<Entity>) thisType.getActualTypeArguments()[0];
		}
		return this.domainClass;
	}

	private String getDomainClassName() {
		return this.getDomainClass().getName();
	}

	@Override
	public void save(Entity entity) {
		try {
			this.invokeDefaultValue(entity, "setCreatedOn", new Date());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
			ex.printStackTrace();
		}
		this.getSession().save(entity);
	}

	@Override
	public void saveOrUpdate(Entity entity) {
		try {
			this.invokeDefaultValue(entity, "setCreatedOn", new Date());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
			ex.printStackTrace();
		}
		this.getSession().saveOrUpdate(entity);
	}

	@Override
	public void update(Entity entity) {
		try {
			this.invokeDefaultValue(entity, "setModifiedOn", new Date());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
			ex.printStackTrace();
		}
		this.getSession().update(entity);
	}

	private void invokeDefaultValue(Entity t, String methodName, Object methodValue)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = ReflectionUtils.findMethod(this.getDomainClass(), methodName,
				new Class[] { methodValue.getClass() });
		if (method != null) {
			method.invoke(t, methodValue);
		}
	}

	@Override
	public Entity get(Serializable id) {
		return (Entity) this.getSession().get(this.getDomainClass(), id);
	}

	@Override
	public Entity load(Serializable id) {
		return (Entity) this.getSession().load(this.getDomainClass(), id);
	}

	@Override
	public List<Entity> getAll() {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Entity> query = builder.createQuery(this.getDomainClass());
		Root<Entity> root = query.from(this.getDomainClass());
		query.select(root);
		return getSession().createQuery(query).getResultList();
	}

	@Override
	public void delete(Entity entity) {
		this.getSession().delete(entity);
	}

	@Override
	public void deleteById(Serializable id) {
		this.delete(this.load(id));
	}

	@Override
	public void deleteAll() {
		this.getSession().createQuery("delete " + this.getDomainClassName()).executeUpdate();
	}

	@Override
	public long count() {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Entity> root = query.from(this.getDomainClass());
		query.select(builder.count(root));
		return this.getSession().createQuery(query).getSingleResult();
	}

	@Override
	public boolean exists(Serializable id) {
		return this.get(id) != null;
	}

	protected Integer calculateFirstResult(Integer page, Integer pageSize) {
		return (page - 1) * pageSize;
	}
}
