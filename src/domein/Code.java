package domein;
import java.util.List;

public class Code {
    private final List<CodePin> code;
    private MoeilijkheidsGraad moeilijkheidsGraad;
    

     /**
     * setMoeilijkheidsGraad wijzigt de waarde van de moeilijkheidsgraad van een
     * code object.
     *
     * @param moeilijkheidsgraad de nieuwe waarde voor moeilijkheidsgraad.
     */
    private void setMoeiljkheidsGraad(int moeilijkheidsgraad){
        switch(moeilijkheidsgraad){
            case 1:
                this.moeilijkheidsGraad = new Gemakkelijk();
                break;
            case 2:
                this.moeilijkheidsGraad = new Gemiddeld();
                break;
            case 3:
                this.moeilijkheidsGraad = new Moeilijk();
                break;
        }
    }
    
     /**
     * Constructor voor een code object. aan de hand van de meegegeven
     * moeilijkheisdgraad wordt een random code gegenereerd.
     *
     * @param moeilijkheidsGraad moeilijkheidsgraad waarvoor een code wirdt
     * gegenereerd.
     */
    public Code(int moeilijkheidsGraad){
        setMoeiljkheidsGraad(moeilijkheidsGraad);
        this.code = this.moeilijkheidsGraad.genereerCode();
    }
    
     /**
     * Constructor voor een code object waarbij de code als List CodePin wordt meegegeven.
     * Gebruikt bij het laden van een spel waarbij eerste de codepinnen worden gegenereerd
     * en vervolgens de rest van het spel.
     * @param code de code
     * @param mg moeilijkheidsgraad van de code.
     */
    public Code(List<CodePin> code,MoeilijkheidsGraad mg){
        this.code = code;
        this.moeilijkheidsGraad = mg;
    }

    /**
     * getCode geeft een lijst van codePin's weer
     * Methode retourneerd een lijst van codepin's is later belangerijk voor
     * kleur opvraag, vergelijking,...
     * @return List CodePin
     */
    public List<CodePin> getCode() {
        return code;
    }

     /**
     *Geeft de moeilijkheids Graad van het code object weer
     *
     * @return MoeilijkheidsGraad (Object)
     */
    public MoeilijkheidsGraad getMoeilijkheidsGraad() {
        return moeilijkheidsGraad;
    }
    

    
    /**
     * Geeft het aantal posities van de code
     * maakte gebruik van de moeilijkheidsGraad om het aantal posities in de code
     * weer te geven.
     * @return het aantal posities (Int)
     */
    public int geefAantalPosities(){
        return this.moeilijkheidsGraad.getAantalPosities();
    }
    
}
