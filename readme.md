#Readme
---------
###Ejecución

- Ir a la carpeta donde tiene el archivo main.py
- añadir controlador a la carpeta
- instalar java en caso que no este instalado en el computador donde se quiera ejecutar
- ingresar por consola el siguiente comando sin las comillas simples: ***'python3 main.py -g "java -jar k3.jar" '*** donde 
 - python3 main.py llama al programa principal
 - -g muestra el desarrollo de la partida por pantalla
 - "jar -jar k3.jar" llama al controlador

###Descripcion del algoritmo

####A grandes rasgos (en pseudocodigo):

~~~
lectura del String ingresado
transformacion del String a un tablero manejable
generar lista S = todos los knights aliados
lista P = nodos hijos posibles

mientras ->no se recorra P completamente
	Se obtiene al Knight 'K' en el index correspondiente
	Se crea una lista 'M' con los movimientos posibles de K
    mientras ->no se recorra M
    	Evaluacion de movimiento
        Si->el movimiento se puede realizar
        	Se agrega a P


Se ordena P de menor a mayor

Mientras ->el tamaño de P sea mayor al que se busca
	Remover el ultimo index de P

Se desordena la lista P

Se imprime un movimiento aleatorio de P
~~~

---
###Clases


####Main
clase principal la cual recibe el input y ejecuta el programa.
####Eval
clase la cual se encarga de asignar puntaje a cada movimmiento posible
####FrameIn
debug visual
####JsonManagement
clase la cual maneja el String JSON para crear el tablero
####Knight
clase para cada knight y sus datos como ubicación, id, si es aliado o enemigo
####Tablero
matriz de celdas
####Celda
representa cada celda del tablero, posee datos como si esta a rango enemigo o aliado, si tiene un knight interno





