package br.com.grafosmst.algorithm;

import br.com.grafosmst.model.Aresta;
import br.com.grafosmst.model.Grafo;
import java.util.*;

public class Prim {

    public static final int verticeInicial = 0;

    public List<Aresta> executarPrim(Grafo grafo) {
        List<Aresta> mst = new ArrayList<>();
        boolean[] incluido = new boolean[grafo.vertices];
        PriorityQueue<Aresta> pq = new PriorityQueue<>();
        incluido[verticeInicial] = true;

        for (Aresta aresta : grafo.arestas) {
            if (aresta.origem == 0) {
                pq.add(aresta);
            }
        }

        while (!pq.isEmpty()) {
            Aresta arestaMenor = pq.poll();
            int destino = arestaMenor.destino;

            if (!incluido[destino]) {
                mst.add(arestaMenor);
                incluido[destino] = true;

                for (Aresta aresta : grafo.arestas) {
                    if (aresta.origem == destino && !incluido[aresta.destino]) {
                        pq.add(aresta);
                    }
                }
            }
        }

        return mst;
    }
}
