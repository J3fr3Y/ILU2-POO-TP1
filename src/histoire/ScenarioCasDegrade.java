package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ScenarioCasDegrade {

	public static void main(String[] args) {
		//test exception étal non occupé
		Etal etal = new Etal();
		try {
			etal.libererEtal();
		} catch (EtalNonOccupeException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		System.out.println("Fin du test 1\n");
		
		//test exception quantite d'achat négative
		Village village = new Village("village test", 3, 1);
		Gaulois asterix = new Gaulois("Asterix", 5);
		village.ajouterHabitant(asterix);
		village.installerVendeur(asterix, "potions", 5);
		
		Etal etal1 = village.rechercherEtal(asterix);
		if(etal1 !=  null) {
			try {
				
				System.out.println(etal1.acheterProduit(-1, asterix));
			} catch(IllegalArgumentException | EtalNonOccupeException e) {
				System.err.println("Erreur lors de l'achat: " + e.getMessage());
			} 
		}
		else {
			System.out.println(asterix.getNom() + " n'a pas trouvé d'étal pour vendre ses produits.");
		}
		System.out.println("Fin du test 2\n");
		
		//test exception acheteur non null
		try {
			System.out.println(etal1.acheterProduit(10,null));
		} catch (IllegalArgumentException | EtalNonOccupeException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		System.out.println("Fin du test 3\n");
		
		//test exception village sans chef
		try {
			System.out.println(village.afficherVillageois());
		} catch(VillageSansChefException e) {
			System.err.println("Erreur lors d'affichage villageois: " +e.getMessage());
		}
		System.out.println("Fin du test 4\n");

	}

}
