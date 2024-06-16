package br.com.grafosmst.algorithm;

import br.com.grafosmst.model.Aresta;
import br.com.grafosmst.model.Grafo;
import br.com.grafosmst.util.UnionFind;
import java.util.*;

public class Kruskal {
    public List<Aresta> executarKruskal(Grafo grafo) {
        List<Aresta> mst = new ArrayList<>();
        Collections.sort(grafo.arestas); // Ordenar as arestas por peso
        UnionFind uf = new UnionFind(grafo.vertices);

        for (Aresta aresta : grafo.arestas) {
            int origem = aresta.origem;
            int destino = aresta.destino;

            if (uf.find(origem) != uf.find(destino)) {
                mst.add(aresta);
                uf.union(origem, destino);
            }
        }

        return mst;
    }
}
