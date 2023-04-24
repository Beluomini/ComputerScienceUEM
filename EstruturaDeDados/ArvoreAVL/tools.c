#include "item.h"

void criaArvore(Arvore *arv){
    arv->Raiz = NULL;
    arv->tam = 0;
}
int arvVazia(Arvore *arv){
    return (arv->Raiz == NULL);
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

void minimoABB(Nodo *q){
    struct Nodo *p;
    p = q;

    if (p->Esq != NULL){
        minimoABB(p->Esq);
    }else{
        printf("\nO menor valor e: %d \n", p->Reg.chave);
    }
}

void maximoABB(Nodo *q){
    struct Nodo *p;
    p = q;

    if (p->Dir != NULL){
        maximoABB(p->Dir);
    }else{
        printf("\nO maior valor e: %d \n", p->Reg.chave);
    }
}

/*-------------------------------- A R V O R E S   A V L -------------------------------------*/

Registro maior(Nodo* Q){
    while (Q->Dir != NULL)
        Q=Q->Dir;
    return(Q->Reg);
}

Nodo* rot_dir(Nodo* N3){
	Nodo *N2;

	N2 = N3-> Esq;

	if (N2->Dir != NULL)
  		N3->Esq = N2->Dir;
	else
		N3->Esq = NULL;

  	N2->Dir = N3;
	return N2;
}

Nodo* rot_esq(Nodo* N3){
	Nodo *N2;

	N2 = N3-> Dir;
	if (N2->Esq != NULL)
  		N3->Dir = N2->Esq;
	else
		N3->Dir = NULL;
	N2->Esq = N3;
	return N2;
}

Nodo* rot_dupla_dir(Nodo* N3){
	Nodo *N1= N3->Esq;
	Nodo *N2= N1->Dir;

	if (N2->Esq != NULL)
  		N1->Dir = N2->Esq;
	else
		N1->Dir = NULL;
	if (N2->Dir != NULL)
  		N3->Esq = N2->Dir;
	else
		N3->Esq = NULL;
	N2->Esq = N1;
	N2->Dir = N3;
	return N2;
}

Nodo* rot_dupla_esq(Nodo* N3){
	Nodo *N1= N3->Dir;
	Nodo *N2= N1->Esq;

	if (N2->Dir != NULL)
  		N1->Esq = N2->Dir;
	else
		N1->Esq = NULL;
	if (N2->Esq != NULL)
  		N3->Dir = N2->Esq;
	else
		N3->Dir = NULL;
	N2->Dir = N1;
	N2->Esq = N3;
	return N2;
}

int conta(Nodo* arv,int x){
	if (arv!=NULL){
		x = conta(arv->Esq,x);
		x = conta(arv->Dir,x);
		x++;

	}
	return x;
}

int altura(Nodo *p){
   int hd=0, he=0;
   if (p == NULL)
      return 0;
   else {
       he = altura (p->Esq);
       hd = altura (p->Dir);
       if (he < hd)
            return hd + 1;
       else
            return he + 1;
    }
}

Nodo* insere_elemento(Nodo *T, Registro X){

    if (T==NULL){
        T=(Nodo*)malloc(sizeof(Nodo));
        T->Reg=X;
        T->Esq=NULL;
        T->Dir=NULL;
    }else{
        if (X.chave < T->Reg.chave){
            T->Esq = insere_elemento (T->Esq, X);
        }else{
            if (X.chave>T->Reg.chave)
                T->Dir = insere_elemento (T->Dir, X);
            else
                T->Reg=X;         //Substitui
        } 
            


        if (abs(altura(T->Esq)-altura(T->Dir))>1){                      //se existe diferenca maior que 1 é preciso rotacionar

            if (altura(T->Dir)>altura(T->Esq)){                         //se a altura for maior pra direita

                    if(altura(T->Dir->Dir)>altura(T->Dir->Esq))         //e do filho tb for maior pra direita
                        T = rot_esq(T);                                 //rotacao simples pra esquerda
                    else
                        T = rot_dupla_esq(T);                           //senão a rotação é dupla pra esquerda

            }else{                                                      //neste caso a altura é maior pra esquerda
                if(altura(T->Esq->Esq)>altura(T->Esq->Dir))             //se para o filho tb for maior pra esquerda
                   T = rot_dir(T);                                      //rotacao simples pra direita
                else
                   T = rot_dupla_dir(T);                                //senão rotação dupla pra direita
            }
        }
    }
    return T;
}

Nodo* remove_elemento(Nodo *T, Registro X){
    Nodo *A;
    if (T==NULL){
        printf ("Elemento nao encontrado na arvore.");
    }else{
        if (X.chave<T->Reg.chave){
            T->Esq = remove_elemento (T->Esq, X);
        }else{
            if (X.chave>T->Reg.chave){
                T->Dir = remove_elemento (T->Dir, X);
            }else{
                if (T->Dir==NULL){
                    A=T;
                    T=T->Esq;
                    free(A);
                }else{
                    if (T->Esq==NULL){
                        A=T;
                        T=T->Dir;
                        free(A);
                    }else{
                        T->Reg = maior(T->Esq);
                        T->Esq = remove_elemento (T->Esq,T->Reg);
                    }
                }
            }    
        }
    }
    return T;
}

Nodo* insereElementoNaoRecursiva(Nodo *T, Registro X){

    Nodo *aux;
    Nodo *aux2;

    if (T==NULL){
        T=(Nodo*)malloc(sizeof(Nodo));
        T->Reg=X;
        T->Esq=NULL;
        T->Dir=NULL;
    }else{
        aux = T;
        aux2 = T;
        while(aux2 != NULL){
            if(X.chave < aux->Reg.chave){
                aux2 = aux->Esq;
                if(aux2 == NULL){
                    aux->Esq =(Nodo*)malloc(sizeof(Nodo));
                    aux->Esq->Reg=X;
                    aux->Esq->Esq=NULL;
                    aux->Esq->Dir=NULL;
                }else{
                    aux = aux2;
                }
            }else if(X.chave > aux->Reg.chave){
                aux2 = aux->Dir;
                if(aux2 == NULL){
                    aux->Dir =(Nodo*)malloc(sizeof(Nodo));
                    aux->Dir->Reg=X;
                    aux->Dir->Esq=NULL;
                    aux->Dir->Dir=NULL;
                }else{
                    aux = aux2;
                }
            }else{
                return T;
            }
        }
        

        if (abs(altura(T->Esq)-altura(T->Dir))>1){                      //se existe diferenca maior que 1 é preciso rotacionar

            if (altura(T->Dir)>altura(T->Esq)){                         //se a altura for maior pra direita

                    if(altura(T->Dir->Dir)>altura(T->Dir->Esq))         //e do filho tb for maior pra direita
                        T = rot_esq(T);                                 //rotacao simples pra esquerda
                    else
                        T = rot_dupla_esq(T);                           //senão a rotação é dupla pra esquerda

            }else{                                                      //neste caso a altura é maior pra esquerda
                if(altura(T->Esq->Esq)>altura(T->Esq->Dir))             //se para o filho tb for maior pra esquerda
                   T = rot_dir(T);                                      //rotacao simples pra direita
                else
                   T = rot_dupla_dir(T);                                //senão rotação dupla pra direita
            }
        }
    }
    return T;
}

/*-------------------------------- I M P R E S S Ã O   D E   A R V O R E S -------------------------------------*/

void imprimeRED(struct Nodo *q){
    struct Nodo *p;
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

void imprimeERD(struct Nodo *q){
    struct Nodo *p;
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