package br.com.cbfe.domain;

public abstract class Factory<S> {
	
	public abstract S create(Builder<S> builder);

	public static abstract class Builder<T> {
		
		public abstract T build();

	}
	
}
