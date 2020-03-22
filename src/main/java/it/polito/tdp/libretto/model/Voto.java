package it.polito.tdp.libretto.model;

import java.time.LocalDate;

/**
 * Classe Voto, contiene le informazioni su un esame superato
 * 
 * @author Rizzi
 *
 */
public class Voto implements Comparable<Voto> {

	private String corso; // "Tecniche di Programmazione"
	private int voto; // 28
	private LocalDate dataEsame; // 15/06/2020

	/**
	 * Costruisce un nuovo Voto.
	 * 
	 * @param corso     nome del corso superato
	 * @param voto      valore del voto acquisito
	 * @param dataEsame data di superamento dell'esame
	 * 
	 */
	public Voto(String corso, int voto, LocalDate dataEsame) {
		super();
		this.corso = corso;
		this.voto = voto;
		this.dataEsame = dataEsame;
	}

	/**
	 * Copy constructor di {@ink Voto}: crea un nuovo {@ink Voto}, copiando il
	 * contenuto del parametro {@code v}
	 * 
	 * @param v il voto da copiare
	 */
	public Voto(Voto v) { // copio i riferimenti degli attributi già esistenti, ma non è un problema
							// in quanto sia corso che data sono oggetti 'immutabili', non c'è nessun metodo
							// per
							// modificarli. int è un tipo primitivo e se ne copia il valore e non il
							// riferimento.
		this.corso = v.getCorso(); // v.corso
		this.dataEsame = v.getDataEsame();
		this.voto = v.getVoto();
	}

	public String getCorso() {
		return corso;
	}

	public void setCorso(String corso) {
		this.corso = corso;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	public LocalDate getDataEsame() {
		return dataEsame;
	}

	public void setDataEsame(LocalDate dataEsame) {
		this.dataEsame = dataEsame;
	}

	@Override
	public String toString() {
		return corso + ": " + voto + " (" + dataEsame + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((corso == null) ? 0 : corso.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voto other = (Voto) obj;
		if (corso == null) {
			if (other.corso != null)
				return false;
		} else if (!corso.equals(other.corso))
			return false;
		return true;
	}

	/**
	 * crea una copia (clone) dell'oggetto presente (this), come nuovo oggetto
	 */
	public Voto clone() {
		Voto v = new Voto(this.corso, this.voto, this.dataEsame);
		return v;
	}

	@Override
	public int compareTo(Voto other) {
		/*
		 * <0 se this < other 
		 * ==0 se this == other 
		 * >0 se this > other
		 */
		return this.corso.compareTo(other.corso);
	}

}
