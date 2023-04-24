#include "tools.c"

/*
Autor: Lucas Beluomini
Arvores Binarias de Busca
Funções:
    -criaArvore(Arvore *a)
        cria arvore vazia
    -insere(Arvore *a)
        insere itens em uma arvore manualmente 
    -contaNos(Nodo *raiz, int qtd)
        conta a quantidade de nós
    -imprimeFolhas(Nodo *raiz)
        imprime as folhas da arvore
    -alturaArvore(Nodo *raiz, int *alt)
        imprime a altura da arvore
    -comparaArvores(Arvore *a, Arvore *b)
        compara duas ABB com chave de inteiros
    -inserirABBletra(Arvore *a, char letra)
        insere em arvore binaria de busca com a chave sendo string
    -imprimirERDletra(Arvore *a)
        imprime a arvore de chaves char in-ordem
    -minimoABB(Nodo *raiz)
        imprime a o menor valor da arvore
    -maximoABB(Nodo *raiz)
        imprime a o maior valor da arvore
    -arvToVet(&arv, &vetor)
        transforma ABB em vetor ordenado pela chave
*/

void insere(Arvore *arv){
    Nodo *p;
    
    p = (Nodo*)malloc(sizeof(Nodo));
    p->Reg.chave = 2;
    
    arv->Raiz = p;

    p->Dir = (Nodo*)malloc(sizeof(Nodo));
    p->Dir->Reg.chave = 1;
    p->Dir->Esq=NULL;
    p->Dir->Dir=NULL;

    p->Esq = (Nodo*)malloc(sizeof(Nodo));
    p->Esq->Reg.chave = 3;
    p->Esq->Dir=NULL;
    p->Esq->Esq=NULL;

    p = p->Esq;

    p->Esq = (Nodo*)malloc(sizeof(Nodo));
    p->Esq->Reg.chave = 4;
    p->Esq->Dir=NULL;
    p->Esq->Esq=NULL;

    p = p->Esq;

    p->Esq = (Nodo*)malloc(sizeof(Nodo));
    p->Esq->Reg.chave = 5;
    p->Esq->Dir=NULL;
    p->Esq->Esq=NULL;

}


int main(){
    Arvore arv, arv1, arv2;
    criaArvore(&arv);
    criaArvore(&arv1);
    criaArvore(&arv2);

    insere(&arv);

    int qtdFolhas = contaNos(arv.Raiz, 0);
    printf("\nA quantidade de folhas da arvore sao: %d\n", qtdFolhas);

    printf("\nAs folhas da arvore sao: \n");
    imprimeFolhas(arv.Raiz);

    int altura = 0;
    alturaArvore(arv.Raiz, &altura);
    printf("\nA altura da arvore e: %d \n", altura);

    inserirABBletra(&arv1, "M");
    inserirABBletra(&arv1, "F");
    inserirABBletra(&arv1, "S");
    inserirABBletra(&arv1, "D");
    inserirABBletra(&arv1, "J");
    inserirABBletra(&arv1, "P");
    inserirABBletra(&arv1, "U");
    inserirABBletra(&arv1, "A");
    inserirABBletra(&arv1, "E");
    inserirABBletra(&arv1, "H");
    inserirABBletra(&arv1, "Q");
    inserirABBletra(&arv1, "T");
    inserirABBletra(&arv1, "W");
    inserirABBletra(&arv1, "K");

    inserirABB(&arv2, 3);
    inserirABB(&arv2, 1);
    inserirABB(&arv2, 2);
    inserirABB(&arv2, 4);
    inserirABB(&arv2, 6);
    inserirABB(&arv2, 7);
    inserirABB(&arv2, 8);
    
    imprimirERDletra(arv1);

    imprimirERD(arv2);

    minimoABB(arv2.Raiz);
    maximoABB(arv2.Raiz);

    int vetor[arv2.tam];
    arvToVetERD(&arv2, &vetor);
    imprimirVetor(vetor, arv2.tam);

    return 0;
}