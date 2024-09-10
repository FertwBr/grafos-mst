---

# Grafos-mst: Algoritmos de Grafos MST (Árvore Geradora Mínima)

Este projeto implementa os algoritmos de Prim e Kruskal para encontrar a Árvore Geradora Mínima (MST) em um grafo ponderado e conectado.

## Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Dependências](#dependências)
- [Como Usar](#como-usar)
  - [Execução a partir da Linha de Comando](#execução-a-partir-da-linha-de-comando)
  - [Trechos de Código Relevantes](#trechos-de-código-relevantes)
- [Instruções de Instalação](#instruções-de-instalação)
- [Contribuição](#contribuição)
- [Licença](#licença)

## Sobre o Projeto

Este projeto foi desenvolvido para estudar e aplicar os algoritmos de Kruskal e Prim em grafos, visando encontrar a Árvore Geradora Mínima (MST). Os grafos são representados usando classes de vértices e arestas, e os algoritmos utilizam técnicas clássicas de teoria dos grafos.

## Dependências

- Java 8 ou superior
- Maven (opcional, para gerenciamento de dependências)

## Como Usar

### Execução a partir da Linha de Comando

Para executar o projeto a partir da linha de comando, siga os passos abaixo:

1. **Compilar o Código:**

Se estiver usando Maven, navegue até o diretório do projeto e execute:

```sh
mvn clean install
```

Se não estiver usando Maven, compile os arquivos `.java` manualmente:

```sh
javac -d bin src/main/java/br/com/grafosmst/model/*.java src/main/java/br/com/grafosmst/algorithm/*.java
```

2. **Executar o JAR com Argumentos:**

Para executar o programa, utilize o seguinte comando:

```sh
java -jar grafos-mst.jar <vertices> <arestas> [<verticeInicial>]
```

- `<vertices>`: Número de vértices no grafo.
- `<arestas>`: Número de arestas no grafo.
- `[<verticeInicial>]` (opcional): Vértice inicial para o algoritmo de Prim. Se não especificado, será escolhido automaticamente.

Exemplo de uso:

```sh
java -jar grafos-mst.jar 5 5 0 1 10 0 2 6 0 3 5 1 3 15 2 3 4
```

### Trechos de Código Relevantes

#### Classe `Grafo`

A classe `Grafo` representa uma estrutura de dados que armazena um grafo não direcionado:

```java
package br.com.grafosmst.model;

import java.util.ArrayList;
import java.util.List;

public class Grafo {
    int vertices; // Número de vértices no grafo
    List<Vertice> verticesList; // Lista de vértices
    List<Aresta> arestas; // Lista de arestas

    // Construtor da classe Grafo
    public Grafo(int vertices) {
        this.vertices = vertices;
        this.verticesList = new ArrayList<>();
        this.arestas = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            verticesList.add(new Vertice(i)); // Adiciona um novo vértice à lista de vértices
        }
    }

    // Método para adicionar uma aresta ao grafo
    public void adicionarAresta(int origem, int destino, int peso) {
        Aresta aresta = new Aresta(origem, destino, peso); // Cria uma nova aresta
        verticesList.get(origem).adicionarAresta(aresta); // Adiciona a aresta ao vértice de origem
        verticesList.get(destino).adicionarAresta(aresta); // Adiciona a aresta ao vértice de destino
        arestas.add(aresta); // Adiciona a aresta à lista de arestas
    }

    // Método para obter o número de vértices no grafo
    public int getVertices() {
        return vertices;
    }

    // Método para obter a lista de vértices
    public List<Vertice> getVerticesList() {
        return verticesList;
    }

    // Método para obter a lista de arestas
    public List<Aresta> getArestas() {
        return arestas;
    }

    // Método para obter a lista de adjacências
    public List<List<Aresta>> getAdjacencias() {
        List<List<Aresta>> adjacencias = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjacencias.add(new ArrayList<>(verticesList.get(i).getArestas())); // Adiciona a lista de arestas de cada vértice à lista de adjacências
        }
        return adjacencias;
    }
}
```

#### Classe `Kruskal`

A classe `Kruskal` implementa o algoritmo de Kruskal para encontrar a Árvore Geradora Mínima (MST) em um grafo ponderado não direcionado:

```java
public class Kruskal {
   public List<Aresta> executarKruskal(Grafo grafo) {
      List<Aresta> mst = new ArrayList<>();

      Collections.sort(grafo.getArestas()); // Ordenar as arestas por peso

      UnionFind uf = new UnionFind(grafo.getVertices());

      for (Aresta aresta : grafo.getArestas()) {
         int origem = aresta.getOrigem();
         int destino = aresta.getDestino();

         if (uf.find(origem) != uf.find(destino)) {
            mst.add(aresta);
            uf.union(origem, destino);
         }
      }

      return mst;
   }
}
```

#### Classe `Prim`

A classe `Prim` implementa o algoritmo de Prim para encontrar a Árvore Geradora Mínima (MST) em um grafo ponderado não direcionado:

```java
public class Prim {
    // Método para executar o algoritmo de Prim
    public List<Aresta> executarPrim(Grafo grafo, Integer verticeInicial) {
        List<Aresta> mst = new ArrayList<>(); // Lista para armazenar a MST
        boolean[] incluido = new boolean[grafo.getVertices()]; // Array para verificar se um vértice já foi incluído na MST
        PriorityQueue<Aresta> pq = new PriorityQueue<>(Comparator.comparingInt(Aresta::getPeso)); // Fila de prioridade para armazenar as arestas pelo peso

        // Se o vértice inicial não for especificado, encontre o melhor vértice inicial
        if (verticeInicial == null) {
            verticeInicial = encontrarMelhorVerticeInicial(grafo);
        }

        incluido[verticeInicial] = true; // Marque o vértice inicial como incluído

        // Adicione todas as arestas do vértice inicial à fila de prioridade
        for (Aresta aresta : grafo.getArestas()) {
            if (aresta.getOrigem() == verticeInicial) {
                pq.add(aresta);
            }
        }

        // Enquanto a fila de prioridade não estiver vazia
        while (!pq.isEmpty()) {
            Aresta arestaMenor = pq.poll(); // Remova a aresta com o menor peso
            int destino = arestaMenor.getDestino(); // Obtenha o vértice de destino da aresta

            // Se o vértice de destino não estiver incluído na MST
            if (!incluido[destino]) {
                mst.add(arestaMenor); // Adicione a aresta à MST
                incluido[destino] = true; // Marque o vértice de destino como incluído

                // Adicione todas as arestas do vértice de destino à fila de prioridade
                for (Aresta aresta : grafo.getArestas()) {
                    if (aresta.getOrigem() == destino && !incluido[aresta.getDestino()]) {
                        pq.add(aresta);
                    }
                }
            }
        }

        return mst; // Retorne a MST
    }

    // Método para encontrar o melhor vértice inicial
    private int encontrarMelhorVerticeInicial(Grafo grafo) {
        int melhorVertice = 0; // Variável para armazenar o melhor vértice
        int menorPeso = Integer.MAX_VALUE; // Variável para armazenar o menor peso

        // Para cada vértice no grafo
        for (int i = 0; i < grafo.getVertices(); i++) {
```

## Instruções de Instalação

1. **Clonar o Repositório:**

```sh
git clone https://github.com/seu-usuario/seu-repositorio.git
```

2. **Compilar o Projeto:**

Se estiver usando Maven, navegue até o diretório do projeto e execute:

```sh
mvn clean install
```

Se não estiver usando Maven, compile os arquivos `.java` manualmente:

```sh
javac -d bin src/main/java/br/com/grafosmst/model/*.java src/main/java/br/com/grafosmst/algorithm/*.java
```

3. **Executar os Testes ou a Aplicação Principal:**

```sh
java -cp bin br.com.grafosmst.Main
```

## Contribuição

1. **Fork o Repositório:**

Clique no botão "Fork" no canto superior direito da página do repositório.

2. **Clone o Repositório Forkado:**

```sh
git clone https://github.com/seu-usuario/seu-fork.git
```

3. **Crie uma Branch para sua Contribuição:**

```sh
git checkout -b minha-contribuicao
```

4. **Faça as Mudanças Desejadas e Commite:**

```sh
git commit -m "feat: Minha contribuição"
```

5. **Envie suas Mudanças para o Repositório Remoto:**

```sh
git push origin minha-contribuicao
```

6. **Abra um Pull Request:**

Vá até o repositório original e clique no botão "New Pull Request".

## Licença

Este projeto está licenciado sob a Licença MIT. Veja o arquivo `LICENSE` para mais detalhes.
