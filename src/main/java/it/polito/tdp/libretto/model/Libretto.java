package it.polito.tdp.libretto.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Memorizza e gestisce un insieme di voti superati
 * 
 * @author Rizzi
 *
 */
public class Libretto {

	private List<Voto> voti = new ArrayList<>();

	/**
	 * Crea un libretto nuovo, vuoto
	 */
	public Libretto() {
		super();
	}
	
	/**
	 * Copy constructor
	 * "Shallow" (copia superficiale)
	 * @param lib
	 */
	public Libretto(Libretto lib) {
		this.voti.addAll(lib.voti);
	}

	/**
	 * Aggiunge un nuovo voto al libretto
	 * 
	 * @param v Voto da aggiungere
	 * @return {@code true} se ha inserito il voto, {@code false} se non l'ha
	 *         inserito perché era in conflitto oppure era duplicato.
	 */
	public boolean add(Voto v) { // Se la classe voto un domani cambia, aggiungendo attributi
									// in questo metodo ci sono meno dipendenze rispetto a quello di sotto, dunque è
									// preferibile questo
		if (this.isConflitto(v) || this.isDuplicato(v)) {
			// non inserire il voto
			return false; // segnala al chiamante che non ha avuto successo
		} else {
			// inserisci il voto
			this.voti.add(v);
			return true;
		}
	}

	// Quindi quando possibile usare sempre oggetti e mai dati sparsi come il metodo
	// sottostante
	public void add(String corso, int voto, LocalDate data) {

	}

	/**
	 * Dato un Libretto, restituisce una stringa nella quale vi sono solamente i
	 * voti pari al valore specificato
	 * 
	 * @param voto Valore specificato
	 * @return Stringa formattata per visualizzare il sotto-libretto
	 */
	public String stampaVotiUguali(int voto) {
		String s = "";
		for (Voto v : this.voti) {
			if (v.getVoto() == voto)
				s += v.toString() + "\n";
		}
		return s;
	}

	/**
	 * Genera un nuovo libretto, a partire da quello esistente, che conterrà
	 * esclusivamente i voti con votazione pari a quella specificata.
	 * 
	 * @param voto Votazione specificata
	 * @return Nuovo Libretto "ridotto"
	 */
	public Libretto estraiVotiUguali(int voto) {
		Libretto nuovo = new Libretto();
		for (Voto v : this.voti) {
			if (v.getVoto() == voto)
				nuovo.add(v);
		}
		return nuovo;
	}

	@Override
	public String toString() {
		String s = "";
		for (Voto v : this.voti) {
			s += v.toString() + "\n";
		}
		return s;
	}

	/**
	 * Dato il nome di un corso, ricerca se quell'esame esiste nel libretto e, in
	 * caso affermativo, restituisce l'oggetto {@link Voto} corrispondente. Se
	 * l'esame non esiste, restituisce {@code null}.
	 * 
	 * @param nomeCorso nome esame da cercare
	 * @return il {@link Voto} corrispondente oppure {@code null} se non esiste
	 */
	public Voto cercaNomeCorso(String nomeCorso) {
		/*
		 * Voto vTemp = null; for (Voto v : this.voti) { if
		 * (nomeCorso.equals(v.getCorso())) { vTemp = v; } } return vTemp;
		 */

		int pos = this.voti.indexOf(new Voto(nomeCorso, 0, null));
		if (pos != -1) {
			return this.voti.get(pos);
		} else
			return null;
	}

	/**
	 * Ritorna {@code true} se il corso specificato da {@code v} esiste nel libretto
	 * con la stessa valutazione. Se non esiste, o se la valutazione è diversa,
	 * ritorna {@code false}
	 * 
	 * @param v il {@link Voto} da ricercare
	 * @return l'esistenza di un duplicato
	 */
	public boolean isDuplicato(Voto v) {
		Voto esiste = this.cercaNomeCorso(v.getCorso());
		if (esiste == null) // non l'ho trovato --> non è un duplicato
			return false;
		/*
		 * if(esiste.getVoto() == v.getVoto()) return true; else return false;
		 */

		return (esiste.getVoto() == v.getVoto());
	}

	/**
	 * Determina se esiste un voto con lo stesso nome corso ma valutazione diversa
	 * 
	 * @param v
	 * @return
	 */
	public boolean isConflitto(Voto v) {
		Voto esiste = this.cercaNomeCorso(v.getCorso());
		if (esiste == null) // non l'ho trovato --> non è un duplicato
			return false;
		return (esiste.getVoto() != v.getVoto());
	}

	/**
	 * Restituisce un NUOVO libretto, migliorando i voti del libretto attuale.
	 * 
	 * @return
	 */
	public Libretto creaLibrettoMigliorato() {
		Libretto nuovo = new Libretto();

		for (Voto v : this.voti) {
			Voto vTemp = new Voto(v);
		//	Voto vTemp = v.clone();
			
			if (vTemp.getVoto() >= 24) {
				vTemp.setVoto(vTemp.getVoto() + 2);
				if (vTemp.getVoto() > 30)
					vTemp.setVoto(30);
			} else if (vTemp.getVoto() >= 18 && vTemp.getVoto() <= 23) {
				vTemp.setVoto(vTemp.getVoto() + 1);
			}
			nuovo.add(vTemp);
		}

		return nuovo;
	}
	
	/**
	 * Riordina i voti presenti nel libretto corrente
	 * alfabeticamente per corso.
	 */
	public void ordinaPerCorso() {
		Collections.sort(this.voti);
	}
	
	public void ordinaPerVoto() {
		Collections.sort(this.voti, new ConfrontaVotiPerValutazione());
	}
	
	/**
	 * Elimina dal libretto tutti i voti <24
	 */
	public void cancellaVotiScarsi() {
		List<Voto> daRimuovere = new ArrayList<>();
		for(Voto v : this.voti)
			if(v.getVoto() < 24)
				daRimuovere.add(v);
		
		this.voti.removeAll(daRimuovere);
//		for (Voto v : daRimuovere)
//			this.voti.remove(v);
	}

}
