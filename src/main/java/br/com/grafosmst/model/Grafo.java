package br.com.grafosmst.model;

import java.util.ArrayList;
import java.util.List;

public class Grafo {
    int vertices;
    List<Vertice> verticesList;
    List<Aresta> arestas;

    public Grafo(int vertices) {
        this.vertices = vertices;
        this.verticesList = new ArrayList<>();
        this.arestas = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            verticesList.add(new Vertice(i));
        }
    }

    public void adicionarAresta(int origem, int destino, int peso) {
        Aresta aresta = new Aresta(origem, destino, peso);
        verticesList.get(origem).adicionarAresta(aresta); // Adiciona a aresta ao vértice de origem
        verticesList.get(destino).adicionarAresta(aresta); // Adiciona a aresta ao vértice de destino
        arestas.add(aresta);
    }

    public int getVertices() {
        return vertices;
    }

    public List<Vertice> getVerticesList() {
        return verticesList;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }
}
