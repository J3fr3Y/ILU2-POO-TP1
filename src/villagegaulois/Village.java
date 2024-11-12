package villagegaulois;

import java.util.ArrayList;

import histoire.EtalNonOccupeException;
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
		this.marche=new Marche(nbEtals);
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
			chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom() + " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public static class Marche{
		private Etal[] etals;
		
		private Marche(int nbEtals) {
			etals = new Etal[nbEtals];
			for(int i = 0; i < nbEtals;i++) {
				etals[i] = new Etal();
				}
		}
		
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			if(indiceEtal >=0 && indiceEtal < etals.length) {
				etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
			}
		}
		
		private int trouverEtalLibre() {
			for(int i = 0; i < etals.length; i++) {
				if(etals[i].isEtalOccupe()==false) {
					return i;
				}
			}
			return -1;
		}
		
		private Etal[] trouverEtals(String produit) {
			ArrayList<Etal> etalsTrouves = new ArrayList<Etal>();
			for(Etal etal: etals) {
				if(etal.isEtalOccupe() && etal.contientProduit(produit)) {
					etalsTrouves.add(etal);
				}
			}
			return etalsTrouves.toArray(new Etal[etalsTrouves.size()]);
		}
		
		private Etal trouverVendeur(Gaulois gaulois) {
			for(Etal etal: etals) {
				if(etal.isEtalOccupe() && etal.getVendeur().equals(gaulois)) {
					return etal;
				}
			}
			return null;
		}
		private String afficherMarche() {
			StringBuilder chaine = new StringBuilder();
			int nbEtalVide = 0;
			
			for(int i = 0; i < etals.length; i++) {
				if(etals[i].isEtalOccupe()) {
					chaine.append(etals[i].afficherEtal());
				} else {
					nbEtalVide++;
				}
			}
			if(nbEtalVide > 0) {
				chaine.append("Il reste " + nbEtalVide + " étals non utilisés dans le marché.\n");
			}
			return chaine.toString();
		}
	}
	
	public String installerVendeur(Gaulois vendeur, String produit, int nbProbuit) {
		int index = marche.trouverEtalLibre();
		StringBuilder chaineInstallerVendeur = new StringBuilder();
		chaineInstallerVendeur.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProbuit + " " +produit + ".\n");
		if( index != -1) {
			marche.utiliserEtal(index, vendeur, produit, nbProbuit);
			chaineInstallerVendeur.append("Le vendeur "+ vendeur.getNom() + " vend des " + produit + " à l'étal n# "+ index+1 +".\n");
			
		} else {
			chaineInstallerVendeur.append("Aucun étal libre a été trouvé\n");
		}
		return chaineInstallerVendeur.toString();
	}
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaineRechercherVendeursProduit = new StringBuilder();
		Etal[] etals=marche.trouverEtals(produit);
		if(etals.length==0) {
			chaineRechercherVendeursProduit.append("Il n'y a pas de vendeur qui propose des " + produit + " au marché.\n");
		} else if(etals.length == 1){
			chaineRechercherVendeursProduit.append("Seul le vendeur " + etals[0].getVendeur().getNom() + " propose des " + produit + " au marché.\n");
		} else {
			chaineRechercherVendeursProduit.append("Vendeurs qui proposent des " + produit + " sont:\n");
			for(int i = 0; i < etals.length;i++) {
				if(etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
					chaineRechercherVendeursProduit.append("-" + etals[i].getVendeur().getNom() + "\n");
				}
			}
		}
		return chaineRechercherVendeursProduit.toString();		
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}
	
	public String partirVendeur(Gaulois vendeur) {
		Etal etal = marche.trouverVendeur(vendeur);
		if(etal == null) {
			return "Vendeur non trouvé\n";
		}
		try {
			return etal.libererEtal();
		} catch (EtalNonOccupeException e) {
			e.printStackTrace();
			return "Erreur: l'étal n'était pas occupé\n";
		}
	}
	
	public String afficherMarche() {
		return "Le marché du \""+ nom +"\" possède plusieurs étals :\n"+marche.afficherMarche();
	}
}
