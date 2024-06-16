package br.com.grafosmst;

import br.com.grafosmst.algorithm.Kruskal;
import br.com.grafosmst.algorithm.Prim;
import br.com.grafosmst.model.Aresta;
import br.com.grafosmst.model.Grafo;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java -jar grafos-mst.jar <vertices> <arestas>");
            return;
        }

        int vertices = Integer.parseInt(args[0]);
        Grafo grafo = new Grafo(vertices);

        int arestas = Integer.parseInt(args[1]);
        System.out.println("Digite as arestas (origem destino peso):");

        for (int i = 0; i < arestas; i++) {
            int origem = Integer.parseInt(args[2 + i * 3]);
            int destino = Integer.parseInt(args[2 + i * 3 + 1]);
            int peso = Integer.parseInt(args[2 + i * 3 + 2]);
            grafo.adicionarAresta(origem, destino, peso);
        }

        Prim prim = new Prim();
        List<Aresta> mstPrim = prim.executarPrim(grafo);
        System.out.println("\nÁrvore Geradora Mínima (Prim):");
        imprimirMST(mstPrim);

        Kruskal kruskal = new Kruskal();
        List<Aresta> mstKruskal = kruskal.executarKruskal(grafo);
        System.out.println("\nÁrvore Geradora Mínima (Kruskal):");
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
}
