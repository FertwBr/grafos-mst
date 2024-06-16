package br.com.grafosmst;

import br.com.grafosmst.algorithm.Kruskal;
import br.com.grafosmst.algorithm.Prim;
import br.com.grafosmst.model.Aresta;
import br.com.grafosmst.model.Grafo;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Número de vértices: ");
        int vertices = scanner.nextInt();
        Grafo grafo = new Grafo(vertices);

        System.out.print("Número de arestas: ");
        int arestas = scanner.nextInt();

        System.out.println("Digite as arestas (origem destino peso):");
        for (int i = 0; i < arestas; i++) {
            int origem = scanner.nextInt();
            int destino = scanner.nextInt();
            int peso = scanner.nextInt();
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
