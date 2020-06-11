package SacADos;

public class Appli {

	public static void main(String[] args) {

		SacADos sac = new SacADos(args[0], Float.parseFloat(args[1]));
	
		if(Float.parseFloat(args[1])==0)
			System.out.println("Votre sac a une capacité de 0 !");
		else {
			switch(args[2].toLowerCase()) {
				case "gloutonne":
					System.out.println("Vous avez sélectionné la méthode gloutonne.");
					sac.resoudreGloutonne();
					break;
				case "dynamique":
					System.out.println("Vous avez sélectionné la méthode dynamique.");
					sac.resoudreDynamique();
					break;
				case "pse":
					System.out.println("Vous avez sélectionné la méthode PSE.");
					sac.resoudrePSE();
					break;
				default:
					System.out.println("La méthode de résolution n'existe pas !");
					return;
				
			}
			System.out.println("-------------------------------------");
			System.out.println("La liste des objets possibles : \n");
			for(int i = 0; i < sac.getListeObjPossible().size(); ++i)
				System.out.println(sac.getListeObjPossible().get(i));
			
			System.out.println("-------------------------------------");
			System.out.println("Pour un sac de capacité " + args[1] + " la solution est : \n");
			float poidsTotal = 0;
			float valTotal = 0;
			if(sac.getListeObjGarde().size() != 0) {
				for(int i = 0; i < sac.getListeObjGarde().size(); ++i) {
					System.out.println(sac.getListeObjGarde().get(i));	
					poidsTotal += ((Objet) sac.getListeObjGarde().get(i)).getPoids();
					valTotal += ((Objet) sac.getListeObjGarde().get(i)).getValeur();
				}
				System.out.println("-------------------------------------");
				System.out.println("Le poids total vaut : " + poidsTotal);
				System.out.println("La valeur totale vaut : " + valTotal);
			}
			else {
				System.out.println("Aucun objet n'est gardé !");
			}
		}
	}	
}
