import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Definição da classe genérica ExemploHashTable com dois parâmetros de tipo (C e V)
public class ExemploHashTable<C, V> {

    // Declaração de variáveis de instância
    private List<Entry<C, V>>[] hashTable; // Array de listas para armazenar entradas (chave, valor)
    private static final int INITIAL_TABLE_SIZE = 10; // Tamanho inicial da tabela
    private static final double LOAD_FACTOR_THRESHOLD = 0.7; // Fator de carga para redimensionamento
    private int size; // Número de elementos na tabela
    private boolean useChaining = true; // Variável para indicar se deve usar chaining ou probing

    // Construtor da classe
    public ExemploHashTable() {
        hashTable = new ArrayList[INITIAL_TABLE_SIZE]; // Inicializa o array de listas
        for (int i = 0; i < INITIAL_TABLE_SIZE; i++) {
            hashTable[i] = new ArrayList<>(); // Inicializa cada lista na posição do array
        }
        size = 0; // Inicializa o tamanho como 0
    }

    // Método principal
    public static void main(String[] args) {
        ExemploHashTable<Object, String> exemploHashTable = new ExemploHashTable<>(); // Instanciação da classe
        int escolha;

        do {
            exibirMenu(exemploHashTable); // Chama o método para exibir o menu
            System.out.print("Escolha a opção desejada: ");
            escolha = scanner().nextInt(); // Lê a escolha do usuário

            // Switch para lidar com as opções do menu
            switch (escolha) {
                case 1:
                    if (exemploHashTable.useChaining) {
                        exemploHashTable.inserirItem();
                    } else {
                        exemploHashTable.inserirItemLinearProbing();
                    }
                    break;
                case 2:
                    exemploHashTable.removerItem();
                    break;
                case 3:
                    exemploHashTable.buscarItem();
                    break;
                case 4:
                    exemploHashTable.listarItens();
                    break;
                case 5:
                    if (exemploHashTable.useChaining) {
                        exemploHashTable.testarEficiencia();
                    } else {
                        exemploHashTable.testarEficiencia();
                    }
                    break;
                case 6:
                    exemploHashTable.alternarMetodo();
                    break;
                case 0:
                    System.out.println("Saindo do programa. Adeus!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (escolha != 0); // Loop até a escolha ser 0 (sair)
    }

    // Método para alternar entre chaining e probing
    private void alternarMetodo() {
        useChaining = !useChaining; // Inverte o valor de useChaining
        System.out.println("Método alterado para: " + (useChaining ? "Chaining" : "Probing"));
    }

    // Método para obter uma instância de Scanner
    private static Scanner scanner() {
        return new Scanner(System.in);
    }

    // Método para exibir o menu
    private static void exibirMenu(ExemploHashTable<Object, String> exemploHashTable) {
        System.out.println("===== Menu =====");

        // Exibe as opções do menu, incluindo se está usando chaining ou probing
        System.out.println("1. Inserir Item (" + (exemploHashTable.useChaining ? "Chaining" : "Probing") + ")");
        System.out.println("2. Remover Item");
        System.out.println("3. Buscar Item");
        System.out.println("4. Listar Itens");
        System.out.println("5. Testar Eficiência (" + (exemploHashTable.useChaining ? "Chaining" : "Probing") + ")");
        System.out.println("6. Alternar Método");
        System.out.println("0. Sair");
    }

    // Método para inserir item usando chaining
    private void inserirItem() {
        System.out.print("Digite a chave: ");
        String chave = scanner().nextLine();
        System.out.print("Digite o valor: ");
        String valor = scanner().nextLine();

        int index = hash(chave); // Calcula o índice usando a função hash

        // Adiciona a entrada na lista correspondente ao índice
        hashTable[index].add(new Entry<C, V>((C) chave, (V) valor));

        size++; // Incrementa o tamanho

        // Verifica se é necessário redimensionar a tabela
        if ((double) size / hashTable.length > LOAD_FACTOR_THRESHOLD) {
            resizeTable();
        }

        System.out.println("Operação realizada com sucesso! (Chaining)");
    }

    // Método para inserir item usando linear probing
    private void inserirItemLinearProbing() {
        System.out.print("Digite a chave: ");
        String chave = scanner().nextLine();
        System.out.print("Digite o valor: ");
        String valor = scanner().nextLine();

        if (hashTable.length == 0) {
            hashTable = new ArrayList[INITIAL_TABLE_SIZE];
        }

        int index = hashLinearProbing(chave);

        // Cria uma nova lista se o índice estiver vazio
        if (hashTable[index] == null) {
            hashTable[index] = new ArrayList<>();
        }

        // Resolve colisões usando linear probing
        while (hashTable[index].contains(new Entry<>((C) chave, (V) valor))) {
            index = (index + 1) % hashTable.length;
        }

        // Adiciona a entrada na lista correspondente ao índice
        hashTable[index].add(new Entry<>((C) chave, (V) valor));
        size++;

        // Verifica se é necessário redimensionar a tabela
        if ((double) size / hashTable.length > LOAD_FACTOR_THRESHOLD) {
            resizeTable();
        }

        System.out.println("Operação realizada com sucesso! (Linear Probing)");
    }

    // Método para remover item
    private void removerItem() {
        System.out.print("Digite a chave do item a ser removido: ");
        String chave = scanner().nextLine();

        int index = hash(chave); // Calcula o índice usando a função hash

        // Percorre a lista no índice e remove a entrada com a chave correspondente
        for (Entry<C, V> entry : hashTable[index]) {
            if (entry.getKey().equals(chave)) {
                hashTable[index].remove(entry);
                size--;
                System.out.println("Operação realizada, objeto:" + chave + " valor: " + entry.getValue() + " removido com sucesso!");
                return;
            }
        }

        System.out.println("Item não encontrado.");
    }

    // Método para buscar item
    private void buscarItem() {
        System.out.print("Digite a chave do item a ser buscado: ");
        String chave = scanner().nextLine();

        int index = hash(chave); // Calcula o índice usando a função hash

        // Percorre a lista no índice e exibe o valor associado à chave
        for (Entry<C, V> entry : hashTable[index]) {
            if (entry.getKey().equals(chave)) {
                System.out.println("Valor associado à chave '" + chave + "': " + entry.getValue());
                return;
            }
        }

        System.out.println("Item não encontrado.");
    }

    // Método para listar todos os itens na tabela
    private void listarItens() {
        System.out.println("===== Itens na Tabela =====");

        // Percorre todas as listas na tabela
        for (List<Entry<C, V>> entries : hashTable) {
            // Percorre todas as entradas na lista e exibe chave e valor
            for (Entry<C, V> entry : entries) {
                System.out.println("Chave: " + entry.getKey() + ", Valor: " + entry.getValue());
            }
        }

        System.out.println("Operação realizada com sucesso!");
    }

    // Método para testar a eficiência de inserção
    private void testarEficiencia() {
        long startTime, endTime;
        int numIterations = 10;

        // Mede o tempo de inserção para um número de iterações
        if (useChaining) {
            System.out.println("===== Teste de Eficiência - Chaining =====");
            startTime = System.currentTimeMillis();
            for (int i = 0; i < numIterations; i++) {
                inserirItem();
            }
            endTime = System.currentTimeMillis();
            System.out.println("Tempo total para " + numIterations + " inserções (Chaining): " + (endTime - startTime) + "ms");
        } else {
            System.out.println("===== Teste de Eficiência - Probing =====");
            startTime = System.currentTimeMillis();
            for (int i = 0; i < numIterations; i++) {
                inserirItemLinearProbing();
            }
            endTime = System.currentTimeMillis();
            System.out.println("Tempo total para " + numIterations + " inserções (Probing): " + (endTime - startTime) + "ms");
        }
    }

    // Método para redimensionar a tabela
    private void resizeTable() {
        int newSize = hashTable.length * 2;
        List<Entry<C, V>>[] newTable = new ArrayList[newSize];

        // Inicializa as novas listas na tabela redimensionada
        for (int i = 0; i < newSize; i++) {
            newTable[i] = new ArrayList<>();
        }

        // Transfere as entradas da tabela antiga para a tabela redimensionada
        for (List<Entry<C, V>> list : hashTable) {
            if (list != null) {
                for (Entry<C, V> entry : list) {
                    int newIndex = hash(entry.getKey(), newSize);
                    newTable[newIndex].add(entry);
                }
            }
        }

        hashTable = newTable; // Atualiza a referência da tabela
    }

    // Função de hash para calcular o índice
    private int hash(Object key) {
        return key.hashCode() % hashTable.length;
    }

    // Função de hash para o linear probing
    private int hashLinearProbing(Object key) {
        int hashcode = key.hashCode();
        hashcode = hashcode ^ (hashcode >>> 20);
        hashcode = hashcode ^ (hashcode >>> 12);
        hashcode = hashcode ^ (hashcode >>> 7);
        return hashcode % hashTable.length;
    }

    // Função de hash com tamanho especificado
    private int hash(Object key, int tableSize) {
        return key.hashCode() % tableSize;
    }

    // Classe estática interna que representa uma entrada na tabela
    static class Entry<C, V> {
        private final C key; // Chave da entrada
        private final V value; // Valor associado à chave

        // Construtor da entrada
        public Entry(C key, V value) {
            this.key = key;
            this.value = value;
        }

        // Getter para a chave
        public C getKey() {
            return key;
        }

        // Getter para o valor
        public V getValue() {
            return value;
        }
    }
}
