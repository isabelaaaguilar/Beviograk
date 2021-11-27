import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class GrafoNPD implements Cloneable {

	public GrafoNPD prox;
	public int valor;
	public boolean visitado;

	public GrafoNPD() {
		this(0);
	}

	public GrafoNPD(int v) {
		valor = v;
		prox = null;
		visitado = false;
	}

	public GrafoNPD getClone(){
		try {
            // call clone in Object.
            return (GrafoNPD) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println (" deu ruim. " );
            return this;
		}
	}
}

class DiagramaNPD {

	public int arestas;
	public int vertices;
	public GrafoNPD[] vetGrafos;
	private int origem;
	private int destino;

	public DiagramaNPD() {
		this(0);
	}

	public DiagramaNPD(int vert) {
		this.vetGrafos = new GrafoNPD[vert];

		for (int i = 0; i < vert; i++) {
			vetGrafos[i] = new GrafoNPD(i + 1);
		}

		this.arestas = 0;
		this.vertices = vert;
	}

	public void criarNovaAresta(GrafoNPD origem, int destino) {
		boolean conexao = false;
		GrafoNPD aux = new GrafoNPD(destino);

		for (GrafoNPD i = origem; conexao != true; i = i.prox) {
			if (i.prox == null) {
				i.prox = aux;
				arestas++;
				conexao = true;
			}
		}
	}

	public void imprimeListaAdjacencia() {
		System.out.println("Origem  Destino");

		for (int i = 0; i < this.vertices; i++) {
			// imprime cabeça da lista
			System.out.print(vetGrafos[i].valor + "     >>  ");

			if (vetGrafos[i].prox != null) {
				// imprime até o fim de cada lista de vertices
				for (GrafoNPD g = vetGrafos[i].prox; g != null; g = g.prox) {

					System.out.print(g.valor + " " + g.visitado);
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
	/**
	 * Remove um elemento da primeira posicao da lista.
    * @return resp int elemento a ser removido.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public void removerInicio(GrafoNPD primeiro) {
		primeiro.visitado = true;
		System.out.println(primeiro.valor + " "+ primeiro.visitado);
	}
	// método que recebe origem e destino, criar uma nova lista de adjacencia baseada na do grafo
	public void enumerarCaminhos(int origem, int destino){
		this.origem = origem;
		this.destino = destino;
		enumerarCaminhos(vetGrafos[origem].valor);
	}

	// método de mesmo nome, recursivo passando origem e destino

	public void enumerarCaminhos( int origem){
		if(origem == this.destino){
			System.out.println("batata");
		}else{
			enumerarCaminhos(vetGrafos[origem-1].prox.valor);
			removerInicio(vetGrafos[origem-1].prox);
		}
	}
}

public class Enumera {

	public static void main(String[] args) {
		DiagramaNPD diagrama = new DiagramaNPD();
		try {
			FileReader arq = new FileReader("entradas/entradatp3.txt");
			BufferedReader lerArq = new BufferedReader(arq);

			System.out.println("*********************************************************************");
			System.out.println("                 GrafoNPD Direcionado                    ");
			System.out.println("*********************************************************************");
			int qtde_vertices = Integer.parseInt(lerArq.readLine());
			diagrama = new DiagramaNPD(qtde_vertices);

			String linha = lerArq.readLine(); 
            while (linha != null) {

                String[] dados = linha.split(",");
				diagrama.criarNovaAresta(diagrama.vetGrafos[(dados[0].charAt(0)-64)-1], (dados[1].charAt(0)-64));
				linha = lerArq.readLine(); 
            }
            arq.close();

		} catch (IOException erro) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", erro.getMessage());
		}
		System.out.println("Lista de Adjacência");
		diagrama.imprimeListaAdjacencia();
		diagrama.enumerarCaminhos(0,6);
		diagrama.imprimeListaAdjacencia();
		System.out.println("*********************************************************************");
	}
}