# Exemplo de Tabela Hash em Java
Este é um exemplo de uma tabela hash genérica em Java. A tabela hash é uma estrutura de dados que mapeia chaves para valores e permite operações eficientes de inserção, busca e remoção. Neste código, você encontrará a implementação de uma tabela de hash que suporta duas estratégias de tratamento de colisões: encadeamento (chaining) e linear probing.

# Visão Geral

**Classe Genérica:** A classe ExemploHashTable é uma classe genérica que aceita dois parâmetros de tipo: C para as chaves (chave) e V para os valores associados a essas chaves.   

**Variáveis de Instância:**  
**hashTable:** Um array de listas para armazenar as entradas (chave, valor).  
**INITIAL_TABLE_SIZE:** Tamanho inicial da tabela.  
**LOAD_FACTOR_THRESHOLD**: Fator de carga que determina quando a tabela deve ser redimensionada.  
**size**: O número de elementos na tabela.  
**useChaining**: Uma variável para indicar se a estratégia de tratamento de colisões é encadeamento (chaining) ou linear probing.  


# Uso

**O código inclui um método main que oferece um menu interativo para realizar as seguintes operações:**

**Inserir Item:** Permite inserir um novo par chave-valor na tabela hash.  
**Remover Item**: Permite remover um item da tabela hash com base em sua chave.  
**Buscar Item**: Permite buscar um item na tabela hash com base em sua chave e exibir o valor associado.  
**Listar Itens**: Exibe todos os itens presentes na tabela.  
**Testar Eficiência:** Avalia a eficiência de inserção para um número de iterações.  
**Alternar Método:** Alterna entre as estratégias de tratamento de colisões (encadeamento ou linear probing).  
**Sair:** Encerra o programa.  

![image](https://github.com/scpjoaoo/HashTable/assets/131673681/20da83f6-ddfe-456d-97fd-e9fd3ecff42b)


# Estratégias de Tratamento de Colisões
**Encadeamento (Chaining):** Quando ocorre uma colisão, os elementos com chaves diferentes são mantidos em uma lista na mesma posição do array. A busca por um elemento envolve percorrer a lista na posição correspondente.  
![image](https://github.com/scpjoaoo/HashTable/assets/131673681/ffd641df-fc9b-446b-ae58-4ddc3571fccc)

**Linear Probing:** Quando ocorre uma colisão, a tabela é percorrida sequencialmente em busca da próxima posição vazia. Isso é feito até que uma posição vazia seja encontrada para inserir o novo elemento.   
![image](https://github.com/scpjoaoo/HashTable/assets/131673681/b4650c4b-caf8-4d1c-8df0-afbda3f3e71c)


# Redimensionamento da Tabela
A tabela hash é redimensionada automaticamente quando o fator de carga (razão entre o número de elementos e o tamanho da tabela) ultrapassa o limite definido em LOAD_FACTOR_THRESHOLD.

# Funções Hash

O código inclui três funções hash:

**hash(key):** Calcula o índice com base na chave e no tamanho da tabela.  
**hashLinearProbing(key):** Usada para a estratégia de linear probing.  
**hash(key, tableSize):** Calcula o índice com base na chave e em um tamanho de tabela específico.  

# Classe Entry  
A classe estática interna Entry representa uma entrada na tabela e contém uma chave e um valor associado a essa chave.  

# Exemplo de Execução
Para usar a tabela hash, execute o programa e siga as opções do menu para inserir, remover, buscar e listar itens. Você também pode alternar entre encadeamento (chaining) e linear probing e testar a eficiência de inserção.  

**INSERÇÃO**   
![image](https://github.com/scpjoaoo/HashTable/assets/131673681/4b800748-e0d4-42e4-ab57-a702571e61ea)

**REMOÇÃO**  
![image](https://github.com/scpjoaoo/HashTable/assets/131673681/00e48cf9-d339-4aed-9d3f-e427f842bce6)

**BUSCA**  
![image](https://github.com/scpjoaoo/HashTable/assets/131673681/ae0f5fc0-cc5b-46b7-815d-1fde1f31418a)

**LISTAGEM**  
![image](https://github.com/scpjoaoo/HashTable/assets/131673681/9bdc85e1-ce55-4774-8773-8997fbc39107)
