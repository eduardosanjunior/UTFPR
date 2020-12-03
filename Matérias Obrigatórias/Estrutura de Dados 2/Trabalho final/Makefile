#==============
all: saida
	@echo " " 
	@echo "Copilação concluída!!"
	@echo " " 
	 

#==============
saida: main.o heap.o
	gcc -o saida main.o heap.o


#==============
main.o: main.c
	gcc -o main.o -c main.c 

#==============
heap.o: heap.c  heap.h
	gcc -o heap.o -c heap.c

#==============
clean: 
	rm -f *.o

#==============	
execClean:
	rm -f saida


