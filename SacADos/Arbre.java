package SacADos;

import java.util.ArrayList;

public class Arbre {

	private ArrayList<Objet> listeObjGarde;
	private Arbre filsSup;
	private Arbre filsInf;
	
	/**
	 * Contructeur d'un arbre
	 */
	public Arbre() {
		this.listeObjGarde = new ArrayList<Objet>();
		this.filsSup = null;
		this.filsInf = null;
	}
	
	/**
	 * Contruteur d'un arbre
	 * @param liste liste d'objet gardé
	 */
	public Arbre(ArrayList<Objet> liste) {
		this.listeObjGarde = liste;
	}

	/**
	 * Retourne la liste d'objet gardé
	 * @return
	 */
	public ArrayList<Objet> getListeObjGarde() {
		return new ArrayList<Objet>(listeObjGarde);
	}

	/**
	 * Met à jour la liste d'objet gardé
	 * @param listeObjGarde
	 */
	public void setListeObjGarde(ArrayList<Objet> listeObjGarde) {
		this.listeObjGarde = listeObjGarde;
	}

	/**
	 * Retourne le fils supérieur
	 * @return
	 */
	public Arbre getFilsSup() {
		return filsSup;
	}

	/**
	 * Met à jour le fils supérieur
	 * @param filsSup
	 */
	public void setFilsSup(Arbre filsSup) {
		this.filsSup = filsSup;
	}

	/**
	 * Retourne le fils inférieur
	 * @return
	 */
	public Arbre getFilsInf() {
		return filsInf;
	}

	/**
	 * Met à jour le fils inférieur
	 * @param filsInf
	 */
	public void setFilsInf(Arbre filsInf) {
		this.filsInf = filsInf;
	}
	
	/**
	 * Ajoute un objet dans la liste d'objet gardé
	 * @param o l'objet
	 */
	public void ajouter(Objet o) {
		this.listeObjGarde.add(o);
	}
	
	/**
	 * Retourne une nouvelle liste d'objet gardé en recopiant tous les objets de la liste d'objet gardé de l'arbre actuel
	 * @return
	 */
	public ArrayList<Objet> getObjets() {
		return new ArrayList<Objet>(this.listeObjGarde);
	}
	
	/**
	 * Affiche tous les noeuds d'un arbre
	 */
	public void afficherArbre() {
	if (listeObjGarde != null)
		System.out.println(getValeurObjGarde());
	if(filsInf != null)
		filsInf.afficherArbre();
	if(filsSup != null)
		filsSup.afficherArbre();
	}
	
	/**
	 * Retourne la somme des valeurs de chaque objet dans la liste d'objet gardé
	 * @return
	 */
	public float getValeurObjGarde() {
		float valeurTotal = 0;
		for(int i = 0; i < listeObjGarde.size(); ++i) {
			valeurTotal += listeObjGarde.get(i).getValeur();
		}
		return valeurTotal;
	}
	
	/**
	 * Retourne la somme des poids de chaque objet dans la liste d'objet gardé
	 * @return
	 */
	public float getPoidsObjGarde() {
		float poidsTotal = 0;
		for(int i = 0; i < listeObjGarde.size(); ++i) {
			poidsTotal += listeObjGarde.get(i).getPoids();
		}
		return poidsTotal;
	}
	
	/**
	 * Vérifie si un noeud est une feuille de l'arbre
	 * @return boolean
	 */
	public boolean isFeuille() {
		if(this.filsInf == null && this.filsSup == null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Récupère toutes les feuilles de l'arbre dans une liste
	 * @param liste
	 */
	public void getFeuilles(ArrayList<ArrayList<Objet>> liste) {
		if(filsInf != null)
			filsInf.getFeuilles(liste);
		if(isFeuille()) {
			liste.add(listeObjGarde);
		}
		if(filsSup != null)
			filsSup.getFeuilles(liste);
	}
}