package histoire;

import villagegaulois.Etal;
import histoire.EtalNonOccupeException;

public class ScenarioCasDegrade {

	public static void main(String[] args) {
		Etal etal = new Etal();
		try {
			etal.libererEtal();
		} catch (EtalNonOccupeException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Fin du test");
	}

}
