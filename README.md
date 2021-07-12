# Graph

## ¿Que es Graph?
									
**Graph** es una libreria que permite la creacion de estructuras de datos en forma de grafo. Un grafo es un conjunto 
de objetos llamados vertices o nodos unidos por enlaces llamados aristas o arcos, que permiten representar relaciones 
binarias entre elementos de un conjunto.
El objetivo fundamental con esta libreria es crear una poderosa herramienta capaz de permitir utilizar y reutilizar 
estructuras de dato en forma de grafo.

## Objetivos Principales 

- Reutilizacion de la libreria y sus clases: 
La libreria permitira ser reutilizable, permitiendo la modificacion de sus clases, subclases y metodos, de esta manera 
es posible la creacion de diferentes tipos de grafos de acuerdo a necesidad.
- Utilizacion de la libreria:
Se establecera la posibilidad de utilizar directamente la libreria sin necesidad de programar especificaciones en los 
grafos. Esto se logra a partir de las diferentes implementaciones de grafos que existiran en la libreria.
- Creacion de los distintos tipos de grafos mas utilizados:
Un grafo es una estructura generica de la cual existen multiples implementaciones de la misma, la implementacion en la 
libreria de estos diferentes subtipos de grafos permitira la utilizacion directa de los mismos y no reimplementar 
demasiado si no es necesario.
- Creacion de metodos mas utilizados y necesarios con respecto a los diferentes tipos de grafos:
Un grafo como estructra de datos posee metodos basicos que permiten su funcionamiento, como la libreria contendra 
direntes subtipos de grafos, tambien contendra multiples metodos utiles para cada uno de estos, permitiendo ser 
una libreria muy util.  
- Compatibilidad entre diferentes estructuras de datos:
Es necesario que la libreria posea un mecanismo basico y seguro de compatibilidad con otras estructuras de datos 
existentes como pueden ser las colecciones, listas, arboles, etc... hasta la actualidad no se posee la necesidad de 
establecer compatibilidad entre librerias existentes, solo mantendra una compatibilidad con los mecanismos 
fundamentales de la API de Java.

## Ultimas Modificaciones
	
### Cambios Realizados

- [x] Creacion de las clases necesarias para el entendimiento de un grafo.
- [x] Creacion de clases de manera tal que puedan ser utilizadas y reutilizadas.
- [x] Creacion de las principales funciones de grafos, vertices y aristas.
- [x] Creacion de clases que permitan abstraer el concepto de grafo, el concepto de 
vertice y el concepto de arista, permitiendo la reutilizacion de las clases. 
- [x] Creacion de vertices y aristas que sirvan como clases predeterminadas para ser utiliadas como instancias.
- [x] Creacion de los distintos tipos de grafos mas utilizados (grafos dirigidos y no dirigidos, grafos con 
ponderados y no ponderados), asi como otros subtipos de grafos.
- [x] Creacion de las funcionalidades mas necesarias de un grafo.
- [x] Creacion de las clases necesarias que permitan la utilizacion del grafo para multiples hilos de ejecucion.
- [x] Creacion de la libreria bajo la plataforma Apache Maven.
- [x] Creacion de la documentacion necesaria.

### Modificaciones Pendientes

- [ ] Estableciento de las pruebas unitarias con *JUnit*.
- [ ] Creacion de los diferentes subtipos de grafos, como son grafos lexicograficos, diccionario, balanceado, B, RojiNegro.
- [ ] Creacion de metodos utiles de busqueda de vertices y/o aristas y calculo de caminos.
- [ ] Creacion de una documentacion mas amplia que permita conocer a fondo el funcionamiento de la libreria.
		
## Requerimientos del Sistema:

**JDK:**
16.0.1 o una version mas actual.
La libreria fue realizada bajo la version 16.0.1 de la maquina virtual de Java.

**Maven:**
La libreria fue realizada a partir de [Apache Maven](https://maven.apache.org/) en su version 3.8.1	

**Memoria:**
No existen requisitos minimos de memoria para su utilizacion.

**Espacio en disco:**
No existen requisitos minimos en cuanto a capacidad de almacenamiento.

**Sistemas Operativos:**
Cualquier Sistema Operativo compatible com la ejecucion de aplicaciones realizadas bajo Java.
Windows 2000 o versiones mas actuales.
Sistemas Operativos basados en Unix (Linux, Solaris o Mac OS X) y otros.
		
## Pruebas:
		
La libreria no posee pruebas unitarias.
		
## Licencia:

Por favor dirijase al archivo [LICENSE](LICENSE) para mas informacion sobre la licencia.
