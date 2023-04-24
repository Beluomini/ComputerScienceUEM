#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>

typedef struct Registro{
  int chave;
  char letra[1];
  //Outros Campos
 }Registro;


typedef struct Nodo{
  struct Registro Reg;
  struct Nodo* Dir;
  struct Nodo* Esq;
 }Nodo;



typedef struct Arvore{
    struct Nodo* Raiz;
    int tam;
}Arvore;