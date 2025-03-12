package villagegaulois;

import java.util.ArrayList;
import java.util.Iterator;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;


	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.marche = new Marche(nbEtals);
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
	
	//creation de la classe interne Marche
	
	private static class Marche{
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

		//methode trouverEtals
		private Etal[] trouverEtals(String produit) {
			//liste dynamique de type Etal
			ArrayList<Etal> etalsTrouves = new ArrayList<Etal>();
			
			//traverse tous les Etals et ajoute a etalsTouves ce contenant le produit cherhé
			for(Etal etal: etals) {
				if(etal.isEtalOccupe() && etal.contientProduit(produit)) {
					etalsTrouves.add(etal);
				}
			}
			//transforme la liste dynamique en tableau de taille des etals Trouvés
			return etalsTrouves.toArray(new Etal[etalsTrouves.size()]);
		}
		
		//methode trouverVendeur
		private Etal trouverVendeur(Gaulois gaulois) {
			//parcour tous les etals
			for(Etal etal: etals) {
				if(etal.isEtalOccupe() && etal.getVendeur().equals(gaulois)) {
					return etal;
				}
			}
			return null;
		}
		
		//methode afficherMarché
		private String afficherMarche() {
			StringBuilder chaine = new StringBuilder();
			int nbEtalsVide = 0;
			for(Etal etal: etals) {
				if(etal.isEtalOccupe()) {
					chaine.append(etal.afficherEtal());
				}else {
					nbEtalsVide++;
				}
				
			}
			if(nbEtalsVide > 0) {
				chaine.append("Il reste " + nbEtalsVide +" étals non utilisés dans le marché.\n");
			}
			return chaine.toString();
		}
	}
	
	//methodes classe Village
	
	//methode installer vendeur
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder chaineInstallerVendeur = new StringBuilder();
		chaineInstallerVendeur.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit + ".\n");
		int numEtal = marche.trouverEtalLibre();
		if(numEtal != -1) {
			marche.utiliserEtal(numEtal, vendeur, produit, nbProduit);
			chaineInstallerVendeur.append("Le vendeur " + vendeur.getNom() +" vend des " + produit + " à l'étal n°" + (numEtal+1) +".\n");
		} else {
			chaineInstallerVendeur.append("Aucun étal libre a été trouvé.\n");
		}
		return chaineInstallerVendeur.toString();
	}
	
	//methode rechercher Vendeurs Produit
	public String rechercherVendeursProduit(String produit) {
	StringBuilder chaineVendeursProduit = new StringBuilder();
	Etal [] etals = marche.trouverEtals(produit);
	if(etals.length == 0) {
		chaineVendeursProduit.append("Il n'y a pas de vendeur qui propose des " + produit + " au marché.\n");
	} else if(etals.length == 1){
		chaineVendeursProduit.append("Seul le vendeur " + etals[0].getVendeur().getNom() + " propose des " + produit + " au marché.\n");
	} else {
		chaineVendeursProduit.append("Les vendeurs qui proposent des " + produit + " sont :\n");
		for(Etal etal: etals) {
			chaineVendeursProduit.append("- " + etal.getVendeur().getNom() +"\n");
		}
	}
	return chaineVendeursProduit.toString();
	}
	
}