#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include "item.h"

void FFVazia (TipoFila *Fila){
    Fila->Inicio = NULL;
    Fila->Fim = Fila->Inicio;
}

int VaziaFila (TipoFila Fila){
    return (Fila.Inicio == NULL);
}

void Enfileira(TipoItem x, TipoFila *Fila){
    if (Fila->Fim == NULL){
        Fila->Fim = (Celula *) malloc(sizeof(Celula));
    }else{
        Fila->Fim->Prox = (Celula *) malloc(sizeof(Celula));
        Fila->Fim = Fila->Fim->Prox;
    }
    Fila->Fim->Item = x;
    Fila->Fim->Prox = NULL;
    if(Fila->Inicio==NULL){
        Fila->Inicio=Fila->Fim;
    }
}

void Desenfileira(TipoFila *Fila){
    Celula *q;

    if (VaziaFila(*Fila)){
        printf(" Erro: fila está vazia\n");
        return;
    }

    q = Fila->Inicio;
    Fila->Inicio = Fila->Inicio->Prox;
    free(q);

    if(Fila->Inicio==NULL){
        Fila->Fim = NULL;
    }
}

void ImprimeFila (TipoFila Fila){
    Celula* Aux;
    int i=1;
    Aux = Fila.Inicio;
    while (Aux != NULL){
        printf ("\n\nCodigo do elemento %d: %d", i, Aux->Item.chave);
        printf ("\nNome do elemento %d: %s", i, Aux->Item.nome);
        printf ("\nIdade do elemento %d: %d", i, Aux->Item.idade);
        getch();
        Aux=Aux->Prox;
        i++;
    }
}

void FPVazia (TipoPilha *Pilha){
    Pilha->Topo = (struct Celula*) malloc(sizeof(Celula));
    Pilha->Fim = Pilha->Topo;
    Pilha->Fim->Prox = NULL;
    Pilha->Tam = 0;
}

int VaziaPilha (TipoPilha Pilha){
    return (Pilha.Tam == 0);
}

void Empilha(TipoItem x, TipoPilha *Pilha){
    struct Celula *Aux;
    Aux = (struct Celula*) malloc(sizeof(Celula));
    Aux->Item = x;
    if(VaziaPilha(*Pilha)){
        Pilha->Fim=Aux;
    }
    Aux->Prox = Pilha->Topo;
    Pilha->Topo = Aux;
    Pilha->Tam++;
}

void Desempilha(TipoPilha *Pilha){
    Celula *q;
    if (VaziaPilha(*Pilha)){
        printf(" Erro: pilha vazia no desempilha\n");
        return;
    }
    q = Pilha->Topo;
    Pilha->Topo = q->Prox;
    free(q);
    Pilha->Tam--;
    if(Pilha->Tam==0){
        Pilha->Fim=Pilha->Topo;
    }
}

void ImprimePilha (TipoPilha Pilha){
    Celula* Aux;
    int i=1;
    Aux = Pilha.Topo;
    while (Aux != Pilha.Fim->Prox){
        printf ("\n\nCodigo do elemento %d: %d", i, Aux->Item.chave);
        printf ("\nNome do elemento %d: %s", i, Aux->Item.nome);
        printf ("\nIdade do elemento %d: %d", i, Aux->Item.idade);
        getch();
        Aux=Aux->Prox;
        i++;
    }
}

void RetiraPilha (int chave, TipoPilha *Pilha){
    TipoPilha pilhaAux;
    FPVazia(&pilhaAux);

    if (VaziaPilha(*Pilha)){
        printf ("Erro: Pilha vazia.\n");
        getch();
    }else{
        while (Pilha->Tam != 0){
            if(Pilha->Topo->Item.chave != chave){
                Empilha(Pilha->Topo->Item, &pilhaAux);
            }
            Desempilha(Pilha);
        }
    }

    if (VaziaPilha(pilhaAux)){
        printf ("Erro: Pilha vazia.\n");
        getch();
    }else{
        while (pilhaAux.Tam != 0){
            Empilha(pilhaAux.Topo->Item, Pilha);
            Desempilha(&pilhaAux);
        }
    }
}

void ImprimeOrd (TipoPilha Pilha){
    TipoPilha pilhaInv;
    FPVazia(&pilhaInv);

    Celula *Aux1;
    Celula *Aux;
    Aux = Pilha.Topo;

    while (Aux->Prox != NULL){
        Aux1 = (Celula*) malloc(sizeof(Celula));
        Aux1->Item = Aux->Item;
        if(VaziaPilha(pilhaInv)){
            pilhaInv.Fim = Aux;
        }
        Aux1->Prox = pilhaInv.Topo;
        pilhaInv.Topo = Aux1;
        pilhaInv.Tam++;

        Aux=Aux->Prox;
    }
    ImprimePilha(pilhaInv);
}

void InverteFila (TipoPilha *Fila){
    TipoPilha pilhaInv;
    FPVazia(&pilhaInv);

    while (Fila->Topo != NULL){
        Empilha(Fila->Topo->Item, &pilhaInv);
        Desenfileira(Fila);
    }

    while (pilhaInv.Tam != 0){
        Enfileira(pilhaInv.Topo->Item, Fila);
        Desempilha(&pilhaInv);
    }
}