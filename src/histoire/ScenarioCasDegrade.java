package histoire;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ScenarioCasDegrade {

	public static void main(String[] args) {
		//test 1
		//Etal etal = new Etal();
		//etal.libererEtal();
		//System.out.println("Fin du test 1");
		//test 2
		Village village = new Village("le village des irréductibles", 10, 5);
		Chef abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
		Gaulois bonemine = new Gaulois("Bonemine", 7);
		Gaulois jef = new Gaulois("Jef",5);
		village.ajouterHabitant(bonemine);
		village.ajouterHabitant(jef);
		village.afficherVillageois();
		System.out.println(village.installerVendeur(bonemine, "fleurs", 20));
		Etal etalFleur = village.rechercherEtal(bonemine);
		Etal etal = new Etal();
		System.out.println(etal.acheterProduit(11, jef));
		System.out.println("Fin du test 2");
	}

}
