package domein;

import java.util.List;
import java.util.ArrayList;
import exceptions.OngeldigePogingException;
/**
 *
 * @author Michiel S.
 */
public class Spelbord {
    private final Code code;
    private final Rij[] rijen;
    private boolean codeGeraden = false;
    
    public Spelbord(int moeilijkheidsGraad){
        this.code = new Code(moeilijkheidsGraad);
        this.rijen = new Rij[12];
        int lengte = this.code.getCode().size();
        vulRijenOp(this.code.getCode().size());
    }
    
    public List<CodePin> getCode(){
       return this.code.getCode();
    }

    private void vulRijenOp(int lengte){
        for(int i=0;i<rijen.length;i++){
            this.rijen[i] = new Rij(lengte);
        }
    }
    
    public Rij[] getRijen(){
        return this.rijen;
    }

    public String[] geefGeldigeKleuren(){
      return this.code.geefGeldigeKleuren();
    }
    
    public boolean getCodeGeraden(){
        return this.codeGeraden;
    }
    
    public void voegPogingToe(int[] poging , int rij){
        geldigePoging(poging);
        this.rijen[rij].doePoging(poging);
        evalueerPoging(rij);
    }
    
    private void geldigePoging(int[] poging){
        if(poging.length!= this.code.geefAantalPosities())
            throw new OngeldigePogingException("Het aantal posities van de "
                    + "poging is ontoereikend.");
    }
    
    private CodePin[] zetKleurOm(int[] poging){
        CodePin[] omzet = new CodePin[poging.length];
        int teller =0;
        for(int i: poging){
            omzet[teller] = new CodePin(i);
            teller++;
        }
        return omzet;
    }
    
    
    public void evalueerPoging(int rij){
        /*krijg de kleuren terug van de poging*/
        List<CodePin> pogingPinnen = this.rijen[rij].getPoging();
        String[] kleurenPoging = new String[pogingPinnen.size()];
    
        for(int i=0;i<pogingPinnen.size();i++){
            kleurenPoging[i] = pogingPinnen.get(i).getKleur();
        }

        /*krijg de kleuren van de code terug en steek ze in een array*/
        List<CodePin> codePinnen = this.code.getCode();
        List<String> codeKleuren = new ArrayList<>();
        
        for(CodePin cp: codePinnen){
            codeKleuren.add(cp.getKleur());
        }
        
        int[] evaluatie = new int[codeKleuren.size()];
        /*geef moeilijkheidsgraad evaluatie is anders per moeilijkheidsgraad*/
        MoeilijkheidsGraad graad = this.code.getMoeilijkheidsGraad();
        
        /*gemakelijke evaluatie*/
            int i =0,zwart=0,rood,wit=0;
            
            for(String kleur:kleurenPoging){
                if(kleur.equals(codeKleuren.get(i))){
                    evaluatie[i] = 0;
                    zwart++;
                }
                else{
                    if(codeKleuren.indexOf(kleur)!=-1){
                        evaluatie[i] = 1;
                        wit++;
                    }
                    else
                        evaluatie[i] = 2;
                }
                i++;
            }
            
            rood = evaluatie.length -zwart - wit;
            
        
       /*sorteert de gemakelijke evaluatie*/
       if(graad instanceof Gemiddeld||graad instanceof Moeilijk){
           for(int j=0;j<zwart;j++){
               evaluatie[j] = 0;
           }
           for(int x=zwart;x<evaluatie.length-rood;x++){
               evaluatie[x] = 1;
           }
           for(int y=evaluatie.length-rood;y<evaluatie.length;y++){
               evaluatie[y]=2;
           }
       }
       
       if(zwart == evaluatie.length){
           codeGeraden = true;
       }
        
        this.rijen[rij].stelEvaluatieIn(evaluatie);
    }
    

}
