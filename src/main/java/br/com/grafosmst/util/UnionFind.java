package br.com.grafosmst.util;

public class UnionFind {
    private int[] pai;
    private int[] rank;

    public UnionFind(int tamanho) {
        pai = new int[tamanho];
        rank = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            pai[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int x) {
        if (pai[x] != x) {
            pai[x] = find(pai[x]);
        }
        return pai[x];
    }

    public void union(int x, int y) {
        int raizX = find(x);
        int raizY = find(y);

        if (raizX != raizY) {
            if (rank[raizX] < rank[raizY]) {
                pai[raizX] = raizY;
            } else if (rank[raizX] > rank[raizY]) {
                pai[raizY] = raizX;
            } else {
                pai[raizY] = raizX;
                rank[raizX]++;
            }
        }
    }
}
