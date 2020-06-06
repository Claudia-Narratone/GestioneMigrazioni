package it.polito.tdp.borders.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;

public class Simulatore {
	//MODELLO -> Stato del sistema ad ogni passo
	private Graph<Country, DefaultEdge> grafo;
	
	//Tipi di veento? -> coda prioritaria
	private PriorityQueue<Evento> queue;
	
	//pararmetri dellla simulazione
	private int N_MIGRANTI=1000;
	private Country partenzaCountry;
	
	//Valori di output
	private int T=-1; //numero di passi
	private Map<Country, Integer> stanziali;
	
	//metodo che inizializza il simulatore
	public void init(Country partenza, Graph<Country, DefaultEdge> grafo) {
		this.partenzaCountry=partenza;
		this.grafo=grafo;
		
		//impostazione dello stato iniziale
		this.T=1;
		stanziali=new HashMap<>();
		for(Country c: this.grafo.vertexSet()) {
			stanziali.put(c, 0);
		}
		//creo la coda
		this.queue=new PriorityQueue<Evento>();
		//inserisco primo evento
		this.queue.add(new Evento(T, partenzaCountry, N_MIGRANTI));
	}
	
	public void run() {
		//finchè c'è un evento nella coda lo estraggo e lo eseguo
		Evento e;
		while((e=this.queue.poll()) != null) {
			this.T=e.getT();
			int nPersone= e.getnMigranti();
			Country stato= e.getStato();
			//cerco i vicini di "stato"
			List<Country> vicini=Graphs.neighborListOf(grafo, stato);
			
			int migranti=(nPersone/2)/vicini.size();
			
			if(migranti >0) {
				//le persone si possono muovere
				for(Country confinante:vicini) {
					//aggiungo un evento
					queue.add(new Evento(e.getT()+1, confinante, migranti));
				}
			}
			
			int stanziali=nPersone-migranti*vicini.size();
			this.stanziali.put(stato, this.stanziali.get(stato)+stanziali);
		}
	}
	
	public Map<Country, Integer> getStanziali(){
		return this.stanziali;
	}
	
	public Integer getT() {
		return this.T;
	}
}
