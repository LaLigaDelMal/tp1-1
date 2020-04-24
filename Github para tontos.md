#Ayuda
#Por Terminal

####Primera vez
Esto va a descargar el repositorio en una carpeta con el mismo nombre

	git clone https://github.com/error404concurrente/tp1

Esto va a configurar su mail y nombre de usuario para que cuando hagan el comando *commit* no les de error
	
	git config --global user.email "ACA VA TU EMAIL ENTRE COMILLAS"
	git config --global user.name "ACA VA TU USUARIO ENTRE COMILLAS"

Y si también lo desean para que no pida a cada rato usuario y contraseña cuando hagan un push puede hacer estos dos comandos y ponerle un timeout de por cuanto tiempo quieren que recuerde git su contraseña (*en segundos*)

	git config --global credential.helper cache
	git config --global credential.helper 'cache --timeout="CANTIDAD DE SEGUNDOS SIN COMILLAS"'

####(*Siempre*) Antes de comenzar a trabajar
Esto va a descargar todos los cambios que haya en el repositiorio original. Si hay alguna incompatibilidad va a saltar un error diciendo en que archivo, y en ese archivo se agregarán líneas que mostrarán las diferencias entre un archivo y otro.
 
	git pull -u origin master

####(*Siempre*) Luego de modificar lo deseado
El primer comando revisará todos los archivos modificados/agregados/borrados del repositiorio local (la carpeta), el segundo planteará el commit que debe realizarse con el mensaje correspondiente, y el tercero comenzará a subir a github.

	git add --all
	git commit -m "ACA VA EL MENSAJE DEL COMMIT"
	git push -u origin master

Luego de ese comando se te solicitará el usuario de github, y la contraseña del mismo. Si hay algún conflicto github te avisará y no dejará subir el commit, para solucionarlo deberás mover tus archivos modificados en algún lado, y borrarlos de la carpeta y luego hacer otra vez el comando *"git pull -u origin master"* para recargar los cambios que alguién hizo mientras trabajabas. y deberás modificar manualmente los archivos para que no tengan conflictos.

####Cosas opcionales

- Cabe aclarar que luego de *"git add ."* también pueden introducir el siguiente comando para corroborar que estén haciendo bien los cambios:

		git status

- También si uno ya hizo al menos una vez un pull y un push puede reemplazar esos comandos directamente por 	

		git pull
		git push

- Si uno está modificando archivos o agregando solamente, y no borrando ninguno puede utilizar este comando, el cual no borrará ningún archivo solo modificará los existente y agregará nuevos.

		git add .

--------------------------------------------------------------------------------------------
##Manejo de Ramas
###Cambiar de Ramas
Para cambiar la Ramas en la que trabajamos tendremos que usar la linea que mostraré a continuación, cambiando *NombreBranch* por el nombre de la Rama a la que queremos ir.
```
git checkout NombreBranch
```
###Crear una Rama
Para crear una nueva Rama basta con escribir la siguiente linea, reemplazando *NuevaBranch* por el nombre que queremos que tenga la Rama a crear, y *BranchMadre* por el nombre de la Rama a partir de la cual queremos ramificar.
```
git branch NuevaBranch BranchMadre
```
**No olvides luego cambiarte a la nueva Rama** para trabajar en ella.

Otra forma de crear una Rama es usando, con el comando **checkout**, el parámetro **-b**. De esta forma crearas una Rama nueva y comenzarás a usarla automáticamente.
```
git checkout -b NuevaBranch
```
###Mezclando Ramas
Primero necesitaremos posicionarnos en la rama que queremos que reciba los archivos con el comando **Checkout**.

Ahora, para realizar la mezcla de las dos Ramas utilizaremos el comando **merge**, reemplazando *NombreBranch* por el nombre de la Rama que queremos traer a nuestra **HEAD** (HEAD es el nombre que utiliza GitHub para referirse a la rama actual).
```
git merge NombreBranch
```
###Borrar una Rama
Para borrar una **Rama local** que ya no necesitemos utilizaremos:
```
git branch -d NombreBranch
```
Luego de eso, borraremos la **Rama de GitHub** con la siguiente linea:
```
git push origin :NombreBranch
```
####Resolviendo Conflictos
A la hora de unir dos Ramas, pueden darse conflictos entre ellas. Esto sucede cuando editamos el mismo archivo en ambas ramas y sobre las mismas líneas.

En caso de existir conflictos, GitHub unirá ambas ramas y nos avisara de la siguiente forma donde hubo conflictos:

>Auto-merged ArchivoConflictivo.py

>**CONFLICT (content): Merge conflict in ArchivoConflictivo.py**

>Automatic merge failed; fix conflicts and then commit the result.

En esta ocasión el archivo problemático era *ArchivoConflictivo.py*, si vamos a este archivo veremos que GitHub nos muestra el problema:

```python
def getDatos(perro):
	nombre = perro.getNombre()
&#60; &#60;&#60;&#60;&#60;&#60;&#60; HEAD:ArchivoConflictivo.py
        raza = 'Pitbull'
===
        raza = perro.getRaza()
&#62;&#62;&#62;&#62;&#62;&#62;&#62; Rama1:ArchivoConflictivo.py
	edad = perro.getEdad()
```
En la rama HEAD la raza del perro siempre sera tomada como *Pitbull*, en *Rama1* se obtiene la raza utilizando otra función.

Lo que debemos hacer es seleccionar la linea que queremos utilizar y borrar la otra linea (Junto con las lineas generadas por GitHub)
```python
def getDatos(perro):
	nombre = perro.getNombre()
	raza = perro.getRaza()
	edad = perro.getEdad()
```
Una vez hecho esto, debemos hacer un *Commit* y un *Push* y nuestra HEAD quedará unida y funcional!
