# Grafos-mst: Algoritmos de Grafos MST (Árvore Geradora Mínima)

Este projeto implementa os algoritmos de Prim e Kruskal para encontrar a Árvore Geradora Mínima (MST) em um grafo ponderado e conectado.

## Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Dependências](#dependências)
- [Como Usar](#como-usar)
  - [Guia Passo a Passo](#guia-passo-a-passo)
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

### Guia Passo a Passo

1. **Criar o Grafo:**
   - Inicialize um grafo com o número de vértices desejado.
   - Adicione arestas ao grafo especificando a origem, destino e peso.

```java
Grafo grafo = new Grafo(5);
grafo.adicionarAresta(0, 1, 10);
grafo.adicionarAresta(0, 2, 6);
grafo.adicionarAresta(0, 3, 5);
grafo.adicionarAresta(1, 3, 15);
grafo.adicionarAresta(2, 3, 4);
```

2. **Executar o Algoritmo de Kruskal:**

```java
Kruskal kruskal = new Kruskal();
List<Aresta> mstKruskal = kruskal.executarKruskal(grafo);
```

3. **Executar o Algoritmo de Prim:**

```java
Prim prim = new Prim();
List<Aresta> mstPrim = prim.executarPrim(grafo);
```

### Trechos de Código Relevantes

#### Classe `Grafo`
A classe Grafo representa uma estrutura de dados que armazena um grafo não direcionado. Aqui estão suas principais funcionalidades:
```java
public class Grafo {
   // Número de vértices no grafo
   int vertices;

   // Lista de vértices do grafo
   List<Vertice> verticesList;

   // Lista de arestas do grafo
   List<Aresta> arestas;

   // Construtor que inicializa o grafo com o número especificado de vértices
   public Grafo(int vertices) {
      this.vertices = vertices;
      this.verticesList = new ArrayList<>();
      this.arestas = new ArrayList<>();

      // Adiciona cada vértice à lista de vértices
      for (int i = 0; i < vertices; i++) {
         verticesList.add(new Vertice(i));
      }
   }

   // Método para adicionar uma aresta ao grafo
   public void adicionarAresta(int origem, int destino, int peso) {
      Aresta aresta = new Aresta(origem, destino, peso);

      // Adiciona a aresta ao vértice de origem
      verticesList.get(origem).adicionarAresta(aresta);

      // Adiciona a mesma aresta ao vértice de destino (considerando grafo não direcionado)
      verticesList.get(destino).adicionarAresta(aresta);

      // Adiciona a aresta à lista de arestas do grafo
      arestas.add(aresta);
   }

   // Retorna o número de vértices no grafo
   public int getVertices() {
      return vertices;
   }

   // Retorna a lista de vértices do grafo
   public List<Vertice> getVerticesList() {
      return verticesList;
   }

   // Retorna a lista de arestas do grafo
   public List<Aresta> getArestas() {
      return arestas;
   }
}

```

#### Classe `Kruskal`
A classe Kruskal implementa o algoritmo de Kruskal para encontrar a Árvore Geradora Mínima (MST) em um grafo ponderado não direcionado:
```java
public class Kruskal {
   // Método para executar o algoritmo de Kruskal e encontrar a MST
   public List<Aresta> executarKruskal(Grafo grafo) {
      // Lista para armazenar as arestas da MST
      List<Aresta> mst = new ArrayList<>();

      // Ordena as arestas do grafo por peso
      Collections.sort(grafo.getArestas());

      // Estrutura Union-Find para detectar ciclos
      UnionFind uf = new UnionFind(grafo.getVertices());

      // Itera sobre todas as arestas do grafo, em ordem crescente de peso
      for (Aresta aresta : grafo.getArestas()) {
         int origem = aresta.getOrigem();
         int destino = aresta.getDestino();

         // Verifica se adicionar a aresta não forma um ciclo
         if (uf.find(origem) != uf.find(destino)) {
            // Adiciona a aresta à MST
            mst.add(aresta);
            // Une os conjuntos dos vértices origem e destino na estrutura Union-Find
            uf.union(origem, destino);
         }
      }

      // Retorna a MST encontrada
      return mst;
   }
}
```

#### Classe `Prim`
A classe Prim implementa o algoritmo de Prim para encontrar a Árvore Geradora Mínima (MST) em um grafo ponderado não direcionado:
```java
public class Prim {
   // Vértice inicial para iniciar o algoritmo de Prim
   public static final int verticeInicial = 0;

   // Método para executar o algoritmo de Prim e encontrar a MST
   public List<Aresta> executarPrim(Grafo grafo) {
      // Lista para armazenar as arestas da MST
      List<Aresta> mst = new ArrayList<>();

      // Array para controlar quais vértices estão incluídos na MST
      boolean[] incluido = new boolean[grafo.getVertices()];

      // Fila de prioridade para armazenar e ordenar as arestas, ordenadas pelo peso
      PriorityQueue<Aresta> pq = new PriorityQueue<>(Comparator.comparingInt(Aresta::getPeso));

      // Inicia o algoritmo a partir do vértice inicial
      incluido[verticeInicial] = true;

      // Adiciona todas as arestas que saem do vértice inicial na fila de prioridade
      for (Aresta aresta : grafo.getArestas()) {
         if (aresta.getOrigem() == verticeInicial) {
            pq.add(aresta);
         }
      }

      // Enquanto houver arestas na fila de prioridade
      while (!pq.isEmpty()) {
         // Obtém a aresta de menor peso
         Aresta arestaMenor = pq.poll();
         int destino = arestaMenor.getDestino();

         // Verifica se o vértice de destino já está incluído na MST
         if (!incluido[destino]) {
            // Adiciona a aresta à MST
            mst.add(arestaMenor);
            incluido[destino] = true;

            // Adiciona todas as arestas que saem do vértice destino na fila de prioridade
            for (Aresta aresta : grafo.getArestas()) {
               if (aresta.getOrigem() == destino && !incluido[aresta.getDestino()]) {
                  pq.add(aresta);
               }
            }
         }
      }

      // Retorna a MST encontrada
      return mst;
   }
}
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