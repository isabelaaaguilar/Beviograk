/**
 * Vertice
 * @author Isabela Aguilar e Lucas Milard
 * @version 2 10/2021
 */
public class Vertice {
    public Vertice  prox;
    public int rotulo;

    public Vertice(){
        prox = null;
        rotulo = 0;
    }

    public Vertice(int rotulo){
        this.rotulo = rotulo;
        this.prox = null;
    } 
}
