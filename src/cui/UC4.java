
package cui;

import domein.DomeinController;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 *
 * @author Gebruiker
 */
public class UC4
{

    private final ResourceBundle r;
    private final DomeinController dc;
    private final Scanner sc;
    private final UC3 uc3;

    public UC4(DomeinController dc, ResourceBundle r) {
        this.r = r;
        this.dc = dc;
        this.sc = new Scanner(System.in);
        this.uc3 = new UC3(dc, r);
    }

    private int geefNaamIn() {
        Scanner scan = new Scanner(System.in);
        int keuze=0;
        try {
            System.out.printf("%n%s",r.getString("keuzeInTeLadenSpel"));
            keuze = scan.nextInt();
            
        } catch (InputMismatchException i) {
            System.out.printf("Je moet een geheel getal in geven,You have to enter a whole number,\n"
                    + "votre choix doit être un nombre entier\n");
            scan.next();
        }
        return keuze;
    }

    /*start methode: -laadSpellen geef ze weer, geef naam in, laadSpelIn, als 
    er een spel ingeladen in + uc3 start methode aan roepen*/
    public void start(){
        laadSpellen();
        int keuze = geefNaamIn();
        laadSpelIn(keuze);
    }
    private void laadSpellen() {
        String[][] spellen = this.dc.geefOpgeslagenSpellen();
        int index = 0;
        String res = String.format("%15s %15s", r.getString("spelNaam"), r.getString("moeilijkheidsGraad"));
        for (String[] rij : spellen) {
            index++;
            res += String.format("%n%d) %15s", index, rij[0]);
            switch (rij[1]) {
                case "1":
                    res += String.format("%15s", r.getString("makkelijk"));
                    break;
                case "2":
                    res += String.format("%15s", r.getString("gemiddeld"));
                    break;
                case "3":
                    res += String.format("%15s", r.getString("moeilijk"));
                    break;
            }

        }
        System.out.printf(res);
    }

    public void laadSpelIn(keuze){
        String[][] spellen = this.dc.geefOpgeslagenSpellen();
        
    }
}
