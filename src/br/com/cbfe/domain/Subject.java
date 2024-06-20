package br.com.cbfe.domain;

public interface Subject {
	
	void registerObserver(Observer observer);
	
	void removeObserver(Observer observer);
	
	void notifyObservers();

}
