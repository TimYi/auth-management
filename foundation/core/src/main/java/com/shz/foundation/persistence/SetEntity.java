package com.shz.foundation.persistence;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public abstract class SetEntity<T> extends UUIDBaseModel implements Set<T> {

	protected abstract Set<T> getInternalSet();
	
	@Override
	public int size() {
		return getInternalSet().size();
	}

	@Override
	public boolean isEmpty() {
		return getInternalSet().isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return getInternalSet().contains(o);
	}

	@Override
	public Iterator<T> iterator() {
		return getInternalSet().iterator();
	}

	@Override
	public Object[] toArray() {
		return getInternalSet().toArray();
	}

	@Override
	public <E> E[] toArray(E[] a) {
		return getInternalSet().toArray(a);
	}

	@Override
	public boolean add(T e) {
		return getInternalSet().add(e);
	}

	@Override
	public boolean remove(Object o) {
		return getInternalSet().remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return getInternalSet().containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		return getInternalSet().addAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return getInternalSet().retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return getInternalSet().removeAll(c);
	}

	@Override
	public void clear() {
		getInternalSet().clear();
	}

}
