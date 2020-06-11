package SacADos;
public class Objet {
	
	private String nomObj;
	private float poidsObj;
	private float valeurObj;
	
	/**
	 * Constructeur d'un objet
	 * @param nom le nom de l'objet
	 * @param poids le poids de l'objet
	 * @param valeur la valeur de l'objet
	 */
	public Objet(String nom, float poids, float valeur) {
		this.nomObj = nom;
		this.poidsObj = poids;
		this.valeurObj = valeur;
	}
	
	/**
	 * Affiche le nom, le poids et la valeur d'un objet
	 */
	public String toString() {
		return this.nomObj + " ; " + this.poidsObj + " ; " + this.valeurObj;
	}
	
	/**
	 * Retourne le poids d'un objet
	 * @return
	 */
	public float getPoids() {
		return this.poidsObj;
	}
	
	/**
	 * Retourne la valeur d'un objet
	 * @return
	 */
	public float getValeur() {
		return this.valeurObj;
	}
	
	/**
	 * Retourne le nom d'un objet
	 * @return
	 */
	public String getNom() {
		return this.nomObj;
	}
	
	/**
	 * Retourne le rapport valeur/poids de l'objet
	 * @return
	 */
	public float getRapport() {
		return (this.valeurObj/this.poidsObj);
	}
}
