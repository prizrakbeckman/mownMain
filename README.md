En résolvant ce problème, j'ai suivi plusieurs régles dont les suivantes :
Les directives possibles sont les suivantes :

***SUD + GAUCHE = EST
SUD + DROITE = OUEST
NORD + GAUCHE = OUEST
NORD + DROITE = EST
OUEST + GAUCHE = SUD
OUEST + DROITE = NORD
EST + GAUCHE = NORD
EST + DROITE = SUD,***

****X,Y**** sont respectivement les coordonnées de la position de la tondeuse.

***OUEST + AVANCE = X - 1
EST + AVANCE = X + 1
NORD + AVANCE = Y + 1
SUD + AVANCE = Y - 1***

J'ai créé une énumération pour les direction de la tondeuse: SUD, OUEST, EST, NORD comme valeur.

Les classes modèles sont : ****Position, Tondeuse, Grille****

****J'ai pris en considération que la position de la tondeuse ne pouvait pas se mettre là où il y avait une autre tondeuse. 
(Sachant que l'on ne peut savoir le nombre de tondeuses après avoir lu le contenu du fichier en entré.)****

La lecture du fichier se fait via un ****buffered reader****,au cas où le ****fichier est avec une taille importante****. La lecture se fait ligne par ligne.

*****J'ai alimenté une liste tondeuses depuis le fichier. Le résultat de la liste a été stocké dans un attribut statique, sur lesquel j'ai fait mon test unitaire*****


J'ai créé un fichier properties depuis lequel j'ai lu le nom du fichier des tondeuses.

Pour exécuter le projet, on doit l'importer en tant que projet maven builder le projet maven, pour récupérer les JAR des Log4j dans le pom.xml (j'ai utilisé ça pour le logging au lieu de faire des system.out.println) puis *****lancer la classe MownMain***** ou bien lancer le test unitaire que j'ai créé *****TestMown.testMown()*****

Sinon pour éviter d'utiliser Maven, on peut faire le checkout sur la branche project_without_maven, importer le projet dans eclipse, à condition d'avoir java 8 settée dans le workspace, et lancer directement la classe MownMain. On peut faire le clone du projet, puis faire le checkout sur la branche *****project_without_maven*****
