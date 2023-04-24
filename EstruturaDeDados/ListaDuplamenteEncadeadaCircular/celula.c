#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include "item.h"

void FLVazia (TipoLista *Lista){
    Lista -> Primeiro = (Celula*) malloc(sizeof(struct Celula));
    Lista -> Ultimo = Lista -> Primeiro;
    Lista -> Ultimo -> Prox = Lista -> Primeiro;
    Lista -> Primeiro -> Ant = Lista -> Ultimo;
}

int Vazia (TipoLista Lista){
    return (Lista.Primeiro == Lista.Ultimo);
}

void Insere (TipoItem x, TipoLista *Lista){
    Celula* pins;
    Celula* paux;
    pins = (Celula*) malloc(sizeof(struct Celula));
    Lista -> Ultimo -> Prox = pins;
    Lista -> Ultimo -> Ant = Lista->Ultimo;
    Lista -> Ultimo = Lista->Ultimo->Prox;
    pins->Item = x;
}

void InsereInicio (TipoItem x, TipoLista *Lista){
    Celula* pins;
    Celula* paux;
    pins = (Celula*) malloc(sizeof(struct Celula));
    pins->Prox = Lista->Primeiro->Prox;
    Lista->Primeiro->Prox->Ant = pins;
    Lista -> Primeiro = pins;
    pins->Item = x;
}

Celula* Localiza (int cod, TipoLista *Lista){
    Celula* paux;
    paux = Lista->Primeiro;
    while ((paux->Prox != NULL) && (paux->Prox->Item.chave != cod)){
        paux = paux->Prox;
    }
    return (paux);
}

int Conta (TipoLista Lista){
    Celula* paux;
    int contador=1;
    if(Vazia(Lista)){
        return 0;
    }else{
        paux = Lista.Primeiro;
        while ((paux->Prox != Lista.Primeiro)){
            contador++;
            paux = paux->Prox;
        }
        return (contador);
    }
}

void Retira (Celula* p, TipoLista *Lista){
    Celula* q;
    if (Vazia(*Lista)||p->Prox==NULL){
        printf ("Erro: lista vazia ou posicao inexistente.\n");
        getch();
    }else{
        q = p->Prox;
        p->Prox = q->Prox;
        if (p->Prox == NULL)
            Lista->Ultimo = p;
        free(q);
    }
}

void RemoveFim (TipoLista *Lista){
    Celula* q;
    if (Vazia(*Lista)){
        printf ("Erro: lista vazia ou posicao inexistente.\n");
        getch();
    }else{
        q = Lista->Ultimo;
        Lista->Ultimo = q->Ant;
        Lista->Ultimo->Prox = Lista->Primeiro;
        free(q);
    }
}

void RemoveIni (TipoLista *Lista){
    Celula* q;
    if (Vazia(*Lista)){
        printf ("Erro: lista vazia ou posicao inexistente.\n");
        getch();
    }else{
        q = Lista->Primeiro->Prox;
        Lista->Primeiro->Prox = q->Prox;
        q->Prox->Ant = Lista->Primeiro;
        free(q);
    }
}

TipoLista Copia (TipoLista Lista){
    TipoLista* Copia;
    Celula* Aux;
    Celula* AuxCop;
    Aux = Lista.Primeiro;
    AuxCop = Copia->Primeiro;
    if(Aux==NULL){
        Copia->Primeiro = NULL;
        printf("\n\nLista vazia\n");
        return *Copia;
    }else{
        do{
            AuxCop = (Celula*) malloc(sizeof(struct Celula));
            AuxCop->Item.chave =  Aux->Item.chave;
            strcpy(AuxCop->Item.nome, Aux->Item.nome);
            AuxCop->Item.idade =  Aux->Item.idade;
            Aux=Aux->Prox;
            if(Aux == Lista.Primeiro){
                AuxCop->Prox = Copia->Primeiro;
            }else{
                AuxCop = AuxCop->Prox;
            }
        }while (Aux != Lista.Primeiro);
    }
}

void Imprime (TipoLista Lista){
    Celula* Aux;
    Aux = Lista.Primeiro;
    int i = 1;
    if(Aux==NULL){
        printf("\n\nLista vazia\n");
    }else{
        do{
            printf ("\n\nCodigo do elemento %d: %d", i, Aux->Item.chave);
            printf ("\nNome do elemento %d: %s", i, Aux->Item.nome);
            printf ("\nIdade do elemento %d: %d", i, Aux->Item.idade);
            getch();
            Aux=Aux->Prox;
            i++;
        }while (Aux != Lista.Primeiro);
    }
}

void ImprimeInv (TipoLista Lista){
    Celula* Aux;
    Aux = Lista.Ultimo;
    if(Aux==Lista.Primeiro){
        printf("\n\nLista vazia\n");
    }else{
        do{
            printf ("\n\nCodigo do elemento: %d", Aux->Item.chave);
            printf ("\nNome do elemento: %s", Aux->Item.nome);
            printf ("\nIdade do elemento: %d", Aux->Item.idade);
            getch();
            Aux=Aux->Ant;
        }while (Aux != Lista.Ultimo);
    }
}