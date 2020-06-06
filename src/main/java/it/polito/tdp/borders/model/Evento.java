package it.polito.tdp.borders.model;

public class Evento implements Comparable<Evento>{

	private int t; //tempo dell'evento
	private Country stato; //stato in cui arrivano i migranti
	private int nMigranti; //num migranti che arrivano al tempo t di cui la metà si sposterà
	
	public Evento(int t, Country stato, int nMigranti) {
		super();
		this.t = t;
		this.stato = stato;
		this.nMigranti = nMigranti;
	}

	public int getT() {
		return t;
	}

	public void setT(int t) {
		this.t = t;
	}

	public Country getStato() {
		return stato;
	}

	public void setStato(Country stato) {
		this.stato = stato;
	}

	public int getnMigranti() {
		return nMigranti;
	}

	public void setnMigranti(int nMigranti) {
		this.nMigranti = nMigranti;
	}

	@Override
	public int compareTo(Evento other) {
		return this.t-other.t;
	}
	
	
	
}
