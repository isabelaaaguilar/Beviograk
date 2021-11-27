import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class GrafoNPD {

	public GrafoNPD prox;
	public int valor;

	public GrafoNPD() {
		this(0);
	}

	public GrafoNPD(int v) {
		valor = v;
		prox = null;
	}
}

class DiagramaNPD {

	public int arestas;
	public int vertices;
	public GrafoNPD[] vetGrafos;

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
			System.out.print(vetGrafos[i].valor + "     >>  ");

			if (vetGrafos[i].prox != null) {
				for (GrafoNPD g = vetGrafos[i].prox; g != null; g = g.prox) {

					System.out.print(g.valor);
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

public class Enumera {

	public static void main(String[] args) {
		DiagramaNPD diagrama = new DiagramaNPD();
		try {
			FileReader arq = new FileReader("entradas/entradatp3.txt");
			BufferedReader lerArq = new BufferedReader(arq);

			System.out.println("*********************************************************************");
			System.out.println("                 GrafoNPD Direcionado Não Ponderado                     ");
			System.out.println("*********************************************************************");
			int qtde_vertices = Integer.parseInt(lerArq.readLine());
			diagrama = new DiagramaNPD(qtde_vertices);

			String linha = lerArq.readLine(); 
            while (linha != null) {

                String[] dados = linha.split(",");
				diagrama.criarNovaAresta(diagrama.vetGrafos[(dados[0].charAt(0)-64)], (dados[1].charAt(0)-64));
				linha = lerArq.readLine(); 
            }
            arq.close();

		} catch (IOException erro) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", erro.getMessage());
		}
		System.out.println("Lista de Adjacência");
		diagrama.imprimeListaAdjacencia();
        for (int i = 0; i < diagrama.vertices; i++) {
            for (int j = 0; j < diagrama.arestas; j++) {
                GrafoNPD[] caminho = new GrafoNPD[] { diagrama.vetGrafos[i][j] };
                buscaCiclos(caminho);
            }
        }


        public void buscaCiclos(GrafoNPD[] listaAdjascencia) {
            int v = listaAdjascencia[0];
            int prox;
            int[] subCaminho = new int[listaAdjascencia.length + 1];
    
            for (int i = 0; i < grafo.length; i++) {
                for (int j = 0; j <= 1; j++) {
    
                    if (grafo[i][j] == v) { /* Vértice atual */
                        prox = grafo[i][(j + 1) % 2];
    
                        if (!visitado(prox, listaAdjascencia)) { /* Vértice vizinho */
                            subCaminho[0] = prox;
                            System.arraycopy(listaAdjascencia, 0, subCaminho, 1, listaAdjascencia.length);
                            buscaCiclos(subCaminho);
                        } else if ((listaAdjascencia.length > 2)
                                && (prox == listaAdjascencia[listaAdjascencia.length - 1])) { /* Ciclo encontrado */
    
                            int[] vetCaminhos = ordenarVertices(listaAdjascencia);
                            int[] invertido = inverterCaminhos(vetCaminhos); /* Evitar duplicações */
                            if (cicloNovo(vetCaminhos) && cicloNovo(invertido)) {
                                ciclos.add(vetCaminhos);
                            }
                        }
                    }
                }
            }
        }
    
		System.out.println("*********************************************************************");
	}
}