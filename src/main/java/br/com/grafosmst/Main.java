package br.com.grafosmst;

import br.com.grafosmst.algorithm.Kruskal;
import br.com.grafosmst.algorithm.Prim;
import br.com.grafosmst.model.Aresta;
import br.com.grafosmst.model.Grafo;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java -jar grafos-mst.jar <vertices> <arestas> [<verticeInicial>]");
            return;
        }

        int vertices = Integer.parseInt(args[0]);
        Grafo grafo = new Grafo(vertices);

        int arestas = Integer.parseInt(args[1]);
        Integer verticeInicial = null; // valor padrão como null

        int offset = 2; // índice de início das arestas nos argumentos
        if (args.length > 2 + arestas * 3) {
            verticeInicial = Integer.parseInt(args[2]);
            offset = 3; // ajuste do índice de início das arestas
        }

        for (int i = 0; i < arestas; i++) {
            int origem = Integer.parseInt(args[offset + i * 3]);
            int destino = Integer.parseInt(args[offset + i * 3 + 1]);
            int peso = Integer.parseInt(args[offset + i * 3 + 2]);
            grafo.adicionarAresta(origem, destino, peso);
        }

        if (verticeInicial == null) {
            verticeInicial = encontrarMelhorVerticeInicial(grafo);
        }

        Prim prim = new Prim();
        List<Aresta> mstPrim = prim.executarPrim(grafo, verticeInicial);
        System.out.println("\nArvore Geradora Minima (Prim):");
        imprimirMST(mstPrim);

        Kruskal kruskal = new Kruskal();
        List<Aresta> mstKruskal = kruskal.executarKruskal(grafo);
        System.out.println("\nArvore Geradora Minima (Kruskal):");
        imprimirMST(mstKruskal);
    }


    private static void imprimirMST(List<Aresta> mst) {
        int pesoTotal = 0;
        for (Aresta aresta : mst) {
            System.out.println(aresta.getOrigem() + " - " + aresta.getDestino() + " : " + aresta.getPeso());
            pesoTotal += aresta.getPeso();
        }
        System.out.println("Peso total da MST: " + pesoTotal);
    }

    private static int encontrarMelhorVerticeInicial(Grafo grafo) {
        int melhorVertice = 0;
        int menorPeso = Integer.MAX_VALUE;

        for (int i = 0; i < grafo.getVertices(); i++) {
            for (Aresta aresta : grafo.getAdjacencias().get(i)) {
                if (aresta.getPeso() < menorPeso) {
                    menorPeso = aresta.getPeso();
                    melhorVertice = i;
                }
            }
        }

        return melhorVertice;
    }

}
