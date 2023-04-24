#ifndef ELEMENTO

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>

typedef struct{
    char letra;
    TipoElemento* Prox;
}TipoElemento;

typedef struct{ 
    TipoElemento* Primeiro;
    TipoElemento* Ultimo;
}TipoLista;

#endif
