#include "tools.c"

/*
Autor: Lucas Beluomini
Arvores Binarias de Busca
Funções:
    -criaArvore(Arvore *a)
        cria arvore vazia
    -contaNos(Nodo *raiz, int qtd)
        conta a quantidade de nós
    -imprimeFolhas(Nodo *raiz)
        imprime as folhas da arvore
    -alturaArvore(Nodo *raiz, int *alt)
        imprime a altura da arvore
    -comparaArvores(Arvore *a, Arvore *b)
        compara duas ABB com chave de inteiros
    -insere_elemento(Nodo *raiz, Registro reg)
        insere em arvore binaria de busca deixando ela avl com as rotações
    -imprimirERD(Arvore *a)
        imprime a arvore de chaves char in-ordem
    -minimoABB(Nodo *raiz)
        imprime a o menor valor da arvore
    -maximoABB(Nodo *raiz)
        imprime a o maior valor da arvore
    -remove_elemento(Nodo *raiz, Registro reg)
        Remove item da arvore
*/


int main(){
    Arvore arv;
    criaArvore(&arv);

    struct Registro add;
    struct Nodo *aux;

    arv.Raiz = NULL;
    add.chave = 3;
    arv.Raiz = insereElementoNaoRecursiva(arv.Raiz, add);
    add.chave = 2;
    arv.Raiz = insereElementoNaoRecursiva(arv.Raiz, add);
    add.chave = 4;
    arv.Raiz = insereElementoNaoRecursiva(arv.Raiz, add);
    add.chave = 6;
    arv.Raiz = insereElementoNaoRecursiva(arv.Raiz, add);
    add.chave = 7;
    arv.Raiz = insereElementoNaoRecursiva(arv.Raiz, add);
    add.chave = 1;
    arv.Raiz = insereElementoNaoRecursiva(arv.Raiz, add);
    add.chave = 5;
    arv.Raiz = insereElementoNaoRecursiva(arv.Raiz, add);


    int qtdFolhas = contaNos(arv.Raiz, 0);
    printf("\nA quantidade de folhas da arvore sao: %d\n", qtdFolhas);

    int altura = 0;
    alturaArvore(arv.Raiz, &altura);
    printf("\nA altura da arvore e: %d \n", altura);

    minimoABB(arv.Raiz);
    maximoABB(arv.Raiz);

    imprimirERD(arv);

    add.chave = 4;
    arv.Raiz = remove_elemento(arv.Raiz, add);

    imprimirERD(arv);

    return 0;
}