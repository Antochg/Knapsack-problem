package SacADos;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SacADos {

	private float poidsMax;
	private ArrayList<Objet> listeObjPossible;
	private ArrayList<Objet> listeObjGarde;
	
	/**
	 * Constructeur d'un Sac � dos
	 */
	public SacADos() {
		this.poidsMax = 0;
		this.listeObjPossible = new ArrayList<Objet>();
		this.listeObjGarde = new ArrayList<Objet>();
	}
	
	/**
	 * Constructeur d'un sac � dos en cr�ant les objets � partir du chemin et en d�finissant le poidsMax du sac � dos
	 * @param chemin
	 * @param poidsMax
	 */
	public SacADos(String chemin, float poidsMax) {
		this();
		try {
			BufferedReader br = new BufferedReader(new FileReader(chemin));
			String ligne;
			String[] donnee;
			while((ligne = br.readLine()) != null) {
				donnee = ligne.split(";",3);
				listeObjPossible.add(new Objet(donnee[0],Float.parseFloat(donnee[1]), Float.parseFloat(donnee[2])));
			}
			br.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		this.poidsMax = poidsMax;
	}
	
	/**
	 * Tri la liste d'objet possible en fonction du rapport valeur/poids de chaque objet dans l'ordre d�croissant
	 * et r�cup�re les objets � partir du d�but de la liste d'objets possibles tant que le poids des objets ne d�passe
	 * pas le poids max du sac � dos
	 */
	public void resoudreGloutonne() {
		
		//Le tri
		Collections.sort(listeObjPossible, new Comparator<Objet>() {

			@Override
			public int compare(Objet o1, Objet o2) {
				return (int) (o2.getRapport()-o1.getRapport());
			}
		});
		
		//R�cup�re les objets tant que le poids des objets est inf�rieur au poids max du sac � dos
		float poidsActuel = 0;
		
		for(int i = 0; i < listeObjPossible.size(); ++i) {
			if(poidsActuel + listeObjPossible.get(i).getPoids() <= poidsMax) {
				poidsActuel += listeObjPossible.get(i).getPoids();
				listeObjGarde.add(listeObjPossible.get(i));
			}
		}
	}
	
	/**
	 * m�thode dynamique
	 */
	public void resoudreDynamique() {
		//cr�ation matrice
		double[][] matrice = new double[listeObjPossible.size()][(int) ((poidsMax+1)*100)];
		//remplissage de la premi�re ligne
		for(int j=0; j<(int)((poidsMax+1)*100); j++) {
			if((int)(listeObjPossible.get(0).getPoids()*100) > j)
				matrice[0][j] = 0;
			else
				matrice[0][j] = listeObjPossible.get(0).getValeur();
		}
		
		//remplissage du reste des lignes
		for(int j=0; j<(int)((poidsMax+1)*100); j++) {
			for(int i = 1 ; i < matrice.length; i++) {
				if((int)(listeObjPossible.get(i).getPoids()*100)>j)
					matrice[i][j] = matrice[i-1][j];
				else
					matrice[i][j] = Math.max(matrice[i-1][j] , matrice[i-1][(int) (j-listeObjPossible.get(i).getPoids()*100)] + listeObjPossible.get(i).getValeur());
			}
		}
		
		//r�cup�ration des objets a gard�s a parti de la matrice
		int x = matrice[0].length-1;
		int y = matrice.length-1;
		while(matrice[y][x]==matrice[y][x-1]) {
			x--;	
		}
		while(x>0) {
			if(y==0 || matrice[y-1][x]==0) {
				listeObjGarde.add(listeObjPossible.get(y));
				break;
			}
			if(matrice[y][x]==matrice[y-1][x]) {
				y--;
			}
			else {
				x = (int) (x - listeObjPossible.get(y).getPoids()*100);
				if(x>0) {
					listeObjGarde.add(listeObjPossible.get(y));
				}
				y--;
			}
		}
	}
	
	/**
	 * Cr�er un arbre binaire, r�cup�re toutes les feuilles de l'arbre, trie la liste de feuilles et r�cup�re
	 * la feuille ayant la valeur la plus grande dont le poids ne d�passe pas celui du poids max du sac � dos
	 */
	public void resoudrePSE() {
		ArrayList<ArrayList<Objet>> liste = new ArrayList<ArrayList<Objet>>();
		Arbre a = new Arbre();
		creerArbre(a, 0);
		a.getFeuilles(liste);
		
		trierListe(liste);
		
		for(int i = 0; i < liste.size(); ++i) {
			float poids = getPoidsListe(liste.get(i));
			//System.out.println(liste.get(i));
			if(poids <= this.poidsMax && getValeurListe(liste.get(i)) > getValeurListe(this.listeObjGarde)) {
				this.listeObjGarde.addAll(liste.get(i));
			}
		}
	}
	
	/**
	 * Tri une liste de liste dans l'ordre d�croissant en fonction de la valeur des listes d'objets
	 * @param liste
	 */
	public void trierListe(ArrayList<ArrayList<Objet>> liste) {
		
		Collections.sort(liste, new Comparator<ArrayList<Objet>>() {

			@Override
			public int compare(ArrayList<Objet> a1, ArrayList<Objet> a2) {
				return (int) (getValeurListe(a2)-getValeurListe(a1));
			}
		});
	}
	
	/**
	 * Retourne la valeur d'une liste d'objets
	 * @param liste
	 * @return
	 */
	public float getValeurListe(ArrayList<Objet> liste) {
		float valeurTotal = 0;
		for(int i = 0; i < liste.size(); ++i) {
			valeurTotal += liste.get(i).getValeur();
		}
		return valeurTotal;
	}
	
	/**
	 * Retourne le poids d'une liste d'objets
	 * @param liste
	 * @return
	 */
	public float getPoidsListe(ArrayList<Objet> liste) {
		float poidsTotal = 0;
		for(int i = 0; i < liste.size(); ++i) {
			poidsTotal += liste.get(i).getPoids();
		}
		return poidsTotal;
		
	}
	
	/**
	 * Cr�ation de l'arbre de mani�re r�cursive tant que la liste d'objets possibles n'est pas vide
	 * @param a l'arbre
	 * @param i la profondeur de l'arbre
	 */
	public void creerArbre(Arbre a, int i) {
		if(i < listeObjPossible.size()) {
			a.setFilsInf(new Arbre(a.getListeObjGarde()));
			a.setFilsSup(new Arbre(a.getListeObjGarde()));
			a.getFilsSup().ajouter(this.listeObjPossible.get(i));
			creerArbre(a.getFilsInf(), i+1);
			creerArbre(a.getFilsSup(), i+1);
		}
	}
	
	/**
	 * Retourne le poids max du sac � dos
	 * @return
	 */
	public float getPoidsMax() {
		return poidsMax;
	}

	/**
	 * Retourne la liste d'objets possibles du sac � dos
	 * @return
	 */
	public ArrayList<Objet> getListeObjPossible() {
		return listeObjPossible;
	}
	
	/**
	 * Retourne la liste d'objets gard�s
	 * @return
	 */
	public ArrayList<Objet> getListeObjGarde() {
		return this.listeObjGarde;
	}
	
	/**
	 * Met � jour la liste d'objets possibles
	 * @param listeObjPossible
	 */
	public void setListeObjPossible(ArrayList<Objet> listeObjPossible) {
		this.listeObjPossible = listeObjPossible;
	}
	
	/**
	 * Met � jour la liste d'objets gard�s
	 * @param listeObjGarde
	 */
	public void setListeObjGarde(ArrayList<Objet> listeObjGarde) {
		this.listeObjGarde = listeObjGarde;
	}
}
