/**
 * Grafo não-direcionado
 * @author Isabela Aguilar e Lucas Milard
 * @version 2 10/2021
 */

public class Grafo{
    public int arestas;
    public int quantidade_vertices;
    public int ciclos;
    public Vertice[] listaAdjacencia;

    /**
     * Construtor que inicializa todas as variaveis inteiras com 0 e lista de adjacencia com null
     */

    public Grafo() {
        this.arestas = 0;
        this.quantidade_vertices = 0;
        this.ciclos = 0;
        this.listaAdjacencia = null;
    }

    /**
     * COnstrutor que inicializa lista de adjacencia
     * @param nVertices
     */
    public Grafo(int nVertices){ 
        this.listaAdjacencia = new Vertice[nVertices];
        this.arestas = 0;
        this.ciclos = 0;
        this.quantidade_vertices = nVertices;

        for(int i =0; i <nVertices; i++){
            listaAdjacencia[i] = new Vertice(i+1);
        }
    }

    /**
     *  Adiciona aresta na lista de adjacencia de cada vertice passado por paramentro a ->b e b-> a
     * @param a
     * @param b
     */
    public void addNovaAresta(Vertice a, Vertice b){
        boolean conexao = false;
        Vertice aux = new Vertice(b.rotulo);
        
        for(Vertice i = a; conexao != true; i = i.prox){ // criar conexão a -> b
            if(i.prox == null){
                i.prox = aux;
                arestas++;
                conexao = true;
            }
        }
        conexao = false;
        aux = new Vertice(a.rotulo);
        for(Vertice i = b; conexao != true; i = i.prox){ // criar conexao b -> a
            if(i.prox == null){
                i.prox = aux;
                arestas++;
                conexao = true;
            }
        }
    }

    /**
     * Método para imprimir toda a lista de adjacencia do grafo
     */
    public void imprimir(){
        System.out.println("Origem Destino");

        for(int i =0; i < this.quantidade_vertices; i++){
            System.out.print((char) (listaAdjacencia[i].rotulo + 64) + "  >>  ");

            if (listaAdjacencia[i].prox != null) {
				for (Vertice g = listaAdjacencia[i].prox; g != null; g = g.prox) {

					System.out.print((char) (g.rotulo + 64));
					if (g.prox == null) {
						System.out.print("\n");
					} else {
						System.out.print(" ");
					}
				}
			} else {
				System.out.println("_");
			}
        }
    }

}
