package br.com.grafosmst.model;

public class Aresta implements Comparable<Aresta> {
    int origem;
    int destino;
    int peso;

    public Aresta(int origem, int destino, int peso) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    @Override
    public int compareTo(Aresta outraAresta) {
        return this.peso - outraAresta.peso;
    }
}
