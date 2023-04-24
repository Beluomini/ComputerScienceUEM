#include "item.h"

void criaArvore(Arvore *arv){
    arv->Raiz = NULL;
    arv->tam = 0;
}
int arvVazia(Arvore *arv){
    return (arv->Raiz == NULL);
}

void imprimeFolhas(Nodo *p){
    if (p!=NULL){

        imprimeFolhas(p->Esq);

        if ((p->Dir==NULL)&&(p->Esq==NULL)){
            printf("Codigo: %d\n",p->Reg.chave);
        }

        imprimeFolhas(p->Dir);
    }
}

int contaNos(Nodo* raiz,int x){
    if (raiz!=NULL){
        x = contaNos(raiz->Esq,x);
        x = contaNos(raiz->Dir,x);
        x++;
    }

    return x;
}

void alturaArvore(Nodo *q, int *alt){
    Nodo *p;
    p = q;
    if ((p->Esq != NULL)||(p->Dir != NULL))
        *alt = *alt+1;

    if (p->Esq != NULL){
        alturaArvore(p->Esq, alt);
    }

    if (p->Dir != NULL){
        alturaArvore(p->Dir, alt);
    }
}

void arvoresIguais(Nodo *q, Nodo *q1, int *vef){
    Nodo *p, *p1;
    p = q;
    p1 = q1;

    if(p->Reg.chave != p1->Reg.chave){
        vef+=1;
    }

    if (p->Esq != NULL){
        arvoresIguais(p->Esq, p1->Esq, vef);
    }

    if (p->Dir != NULL){
        arvoresIguais(p->Dir, p1->Dir, vef);
    }
}
void comparaArvores(Arvore *q, Arvore *q1){
    int vef = 0;
    arvoresIguais(q->Raiz, q1->Raiz, &vef);
    if (vef==0){
        printf("\nAs arvores sao iguais \n\n");
    }else{
        printf("\nAs arvores sao diferentes \n\n");
    }
}

void addVetor(Nodo *q, int *vetor, int *posi){

    if (q->Esq != NULL){
        addVetor(q->Esq, vetor, posi);
    }

    vetor[*posi] = q->Reg.chave;
    *posi+=1;

    if (q->Dir != NULL){
        addVetor(q->Dir, vetor, posi);
    }
}
void arvToVetERD(Arvore *arv, int *vetor){
    int *posi = 0;
    addVetor(arv->Raiz, vetor, &posi);
}

/*-------------------------------- A R V O R E S   A B B -------------------------------------*/

void inserindoABB(Arvore *arv, Nodo *aux, Nodo *add){
    Nodo *p;
    
    if(aux == NULL){


        p = (Nodo*)malloc(sizeof(Nodo));
        p->Reg = add->Reg;
        p->Dir = NULL;
        p->Esq = NULL;

        if(arvVazia(arv)){
            //printf(" add raiz\n");
            arv->Raiz = p;
        }

    }else{
        if(add->Reg.chave <= aux->Reg.chave){

            if(aux->Esq == NULL){
                p = (Nodo*)malloc(sizeof(Nodo));
                p->Reg = add->Reg;
                p->Dir = NULL;
                p->Esq = NULL;
                aux->Esq = p;
                //printf(" add pra esquerda\n");
            }else{
                //printf(" fui pra esquerda\n");
                inserindoABB(arv, aux->Esq, add);
            }
            
        }else{

            if(aux->Dir == NULL){
                p = (Nodo*)malloc(sizeof(Nodo));
                p->Reg = add->Reg;
                p->Dir = NULL;
                p->Esq = NULL;
                aux->Dir = p;
                //printf(" add pra direita\n");
            }else{
                //printf(" fui pra direita\n");
                inserindoABB(arv, aux->Dir, add);
            }

        }
    }
}
void inserirABB(Arvore *arv, int chaveAdd){
    Nodo *add;
    Nodo *raiz;
    raiz = arv->Raiz;
    add->Reg.chave = chaveAdd;
    add->Esq = NULL;
    add->Dir = NULL;
    inserindoABB(arv, raiz, add);
    arv->tam+=1;
}


void inserindoABBletra(Arvore *arv, Nodo *aux, Nodo *add){
    Nodo *p;
    
    if(aux == NULL){


        p = (Nodo*)malloc(sizeof(Nodo));
        p->Reg = add->Reg;
        p->Dir = NULL;
        p->Esq = NULL;

        if(arvVazia(arv)){
            //printf(" add raiz\n");
            arv->Raiz = p;
        }

    }else{
        if(strcmp(add->Reg.letra, aux->Reg.letra) <= 0){

            if(aux->Esq == NULL){
                p = (Nodo*)malloc(sizeof(Nodo));
                p->Reg = add->Reg;
                p->Dir = NULL;
                p->Esq = NULL;
                aux->Esq = p;
                //printf(" add pra esquerda\n");
            }else{
                //printf(" fui pra esquerda\n");
                inserindoABBletra(arv, aux->Esq, add);
            }
            
        }else{

            if(aux->Dir == NULL){
                p = (Nodo*)malloc(sizeof(Nodo));
                p->Reg = add->Reg;
                p->Dir = NULL;
                p->Esq = NULL;
                aux->Dir = p;
                //printf(" add pra direita\n");
            }else{
                //printf(" fui pra direita\n");
                inserindoABBletra(arv, aux->Dir, add);
            }

        }
    }
}
void inserirABBletra(Arvore *arv, char *letraAdd){
    Nodo *add;
    Nodo *raiz;
    raiz = arv->Raiz;
    strcpy(add->Reg.letra, letraAdd);
    add->Esq = NULL;
    add->Dir = NULL;
    inserindoABBletra(arv, raiz, add);
}


void minimoABB(Nodo *q){
    Nodo *p;
    p = q;

    if (p->Esq != NULL){
        minimoABB(p->Esq);
    }else{
        printf("\nO menor valor e: %d \n", p->Reg.chave);
    }
}

void maximoABB(Nodo *q){
    Nodo *p;
    p = q;

    if (p->Dir != NULL){
        maximoABB(p->Dir);
    }else{
        printf("\nO maior valor e: %d \n", p->Reg.chave);
    }
}

/*-------------------------------- I M P R E S S Ã O   D E   A R V O R E S -------------------------------------*/

void imprimeRED(Nodo *q){
    Nodo *p;
    p = q;

    printf("%d, ",p->Reg.chave);

    if (p->Esq != NULL){
        imprimeRED(p->Esq);
    }

    if (p->Dir != NULL){
        imprimeRED(p->Dir);
    }
}
void imprimirRED(Arvore arv){
    printf("\n-----------------\n");
    printf("\nPre-ordem (RED)\n");
    imprimeRED(arv.Raiz);
    printf("\n-----------------\n");
}

void imprimeERD(Nodo *q){
    Nodo *p;
    p = q;

    if (p->Esq != NULL){
        imprimeERD(p->Esq);
    }

    printf("%d, ",p->Reg.chave);

    if (p->Dir != NULL){
        imprimeERD(p->Dir);
    }
}
void imprimirERD(Arvore arv){
    printf("\n-----------------\n");
    printf("In-ordem (ERD)\n");
    imprimeERD(arv.Raiz);
    printf("\n-----------------\n");
}

void imprimeERDletra(Nodo *q){
    Nodo *p;
    p = q;

    if (p->Esq != NULL){
        imprimeERDletra(p->Esq);
    }

    printf("%s, ",p->Reg.letra);

    if (p->Dir != NULL){
        imprimeERDletra(p->Dir);
    }
}
void imprimirERDletra(Arvore arv){
    printf("\n-----------------\n");
    printf("In-ordem (ERD)\n");
    imprimeERDletra(arv.Raiz);
    printf("\n-----------------\n");
}

void imprimirVetor(int vetor[], int tam){
    printf("\n---- transformado em vetor -----\n");
    for(int i=0; i<tam; i++){
        printf("[%d]", vetor[i]);
    }
    printf("\n--------------------------------\n");
}