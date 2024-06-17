package br.com.grafosmst.algorithm;

import br.com.grafosmst.model.Aresta;
import br.com.grafosmst.model.Grafo;

import java.util.*;

public class Prim {

    public List<Aresta> executarPrim(Grafo grafo, int verticeInicial) {
        List<Aresta> mst = new ArrayList<>();
        boolean[] incluido = new boolean[grafo.getVertices()];
        PriorityQueue<Aresta> pq = new PriorityQueue<>(Comparator.comparingInt(Aresta::getPeso));
        incluido[verticeInicial] = true;

        for (Aresta aresta : grafo.getArestas()) {
            if (aresta.getOrigem() == verticeInicial) {
                pq.add(aresta);
            }
        }

        while (!pq.isEmpty()) {
            Aresta arestaMenor = pq.poll();
            int destino = arestaMenor.getDestino();

            if (!incluido[destino]) {
                mst.add(arestaMenor);
                incluido[destino] = true;

                for (Aresta aresta : grafo.getArestas()) {
                    if (aresta.getOrigem() == destino && !incluido[aresta.getDestino()]) {
                        pq.add(aresta);
                    }
                }
            }
        }

        return mst;
    }
}
