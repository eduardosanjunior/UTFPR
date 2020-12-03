#include "t2.h"

pilha* inicia_pilha ()
{
	pilha* p = (pilha*) malloc (sizeof(pilha));
	p->topo = NULL;
	p->primeiro = NULL;
}

elemento* inicia_elemento (char* tag)
{
	elemento* e = (elemento*) malloc(sizeof (elemento));
	e->ant = NULL;
	e->prox = NULL;
	e->tag = tag;

	return e;
}

int vazia (pilha *p)
{
	if (p->primeiro == NULL)
		return 1;
	else return 0;
}

void inserir (elemento* e, pilha *p)
{
	if(vazia(p)) //Se a pilha estiver vazia, adiciona como primeiro elemento
	{
		p->primeiro = e;
		p->topo = p->primeiro;
	}
	else //Caso contrário, adiciona no topo
	{
		p->topo->prox = e;
		e->ant = p->topo;

		p->topo = p->topo->prox;
	}
}

void remover (pilha *p) 
{
	elemento* aux = p->topo;
	if(!vazia(p))
	{
		if(p->topo->ant != NULL)
		{
			p->topo = p->topo->ant;
			if(p->topo)
			{
				p->topo->prox = NULL;
				aux->ant = NULL;
			}
		}
		else
		{
			p->primeiro = NULL;
			p->topo = NULL;
		}
	}
}

int compara_tag (char* ch1, char* ch2)
{
	if (strcmp (ch1,ch2) )
		return 0;
	else return 1;
}

char* extrai_tag (char* tag)
{
	int size = strlen(tag) - 1;

	char* aux = NULL;
	int n = 1;
	int i = 0;
	int k = 1;

	int tagSize = size - 2;

	if(tag[1] == '/')
	{
		n++;
		k++;
	}
	aux = (char*) malloc ( sizeof (char*) * (size - n));
	for (; n < size; n++)
	{
		aux[n - k] = tag[n];
		if (n > tagSize)
			break;
	}

	aux[n - k + 1] = '\0';
	return aux;
}

int compara_html (char* diretorio)
{
		FILE* file = fopen(diretorio, "r");
		pilha* p = inicia_pilha();
		elemento* e;
		char x[1024]; //Assumindo que cada palavra terá no máximo 1024 caracteres


		//Lê palavra por palavra do arquivo
		while (fscanf (file, " %1023s", x) == 1)
		{
			if (x[0] == '<') //Se for uma tag
			{
				if (x[1] == '/')//Se for uma tag "fechando"
				{
					e = p->topo;
					if( compara_tag (extrai_tag(x),e->tag))
						remover(p);
					else return 0;
				}
				//Caso contrário é uma tag "abrindo"
				else inserir (inicia_elemento(extrai_tag(x)),p);
			}
		
		}
		if(vazia(p)) //Se a pilha estiver vazia, o arquivo é válido
			return 1;
		else return 0; //Alguma tag não foi fechada
}