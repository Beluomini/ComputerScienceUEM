#ifndef ITEM
#define ITEM
#define CELULA
#define LISTA

typedef struct{
    int chave;
    char nome [30];
    int idade;
    //outros campos
}TipoItem;

typedef struct Celula{
    TipoItem Item;
    struct Celula* Prox;
    struct Celula* Ant;
}Celula;

typedef struct{
    struct Celula* Primeiro;
    struct Celula* Ultimo;
}TipoLista;

#endif // PONTO_H_INCLUDED