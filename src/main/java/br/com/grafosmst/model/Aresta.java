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

    public int getOrigem() {
        return origem;
    }

    public int getDestino() {
        return destino;
    }

    public int getPeso() {
        return peso;
    }

    @Override
    public int compareTo(Aresta outraAresta) {
        return this.peso - outraAresta.peso;
    }
}
