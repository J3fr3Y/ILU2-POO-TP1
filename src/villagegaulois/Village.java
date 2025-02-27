package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	//creation de la classe interner Marche
	
	public static class Marche{
		//attribut etals de type tab Etal
		private Etal[] etals;
		
		/*constructeur Marche
		 * INPUT: nb etals du marche*/
		private Marche(int nbEtals) {
			//initialisation Etal[] etals
			etals = new Etal[nbEtals];
		
			for(int i = 0; i < nbEtals; i++) {
				etals[i]= new Etal();
			}
		}
		
		//methode utiliserEtal
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit){
			//verifier si indice de l'etal est valide
			if(indiceEtal >= 0 && indiceEtal < etals.length) {
				etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
			}
		}
		
		//methode trouverEtalLibre
		private int trouverEtalLibre() {
			for(int i = 0; i < etals.length; i++) {
				if(etals[i].isEtalOccupe() == false) {
					return i;
				}
			}
			return -1;
		}
		
	}
}