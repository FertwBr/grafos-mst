package br.com.grafosmst.model;

import java.util.ArrayList;
import java.util.List;

public class Grafo {
    int vertices;
    List<Aresta> arestas;

    public Grafo(int vertices) {
        this.vertices = vertices;
        this.arestas = new ArrayList<>();
    }

    public void adicionarAresta(int origem, int destino, int peso) {
        arestas.add(new Aresta(origem, destino, peso));
    }
}
