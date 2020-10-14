En résolvant ce problème, j'ai suivi plusieurs régles dont les suivantes :
Les directives possibles sont les suivantes :

SUD + GAUCHE = EST
SUD + DROITE = OUEST
NORD + GAUCHE = OUEST
NORD + DROITE = EST
OUEST + GAUCHE = SUD
OUEST + DROITE = NORD
EST + GAUCHE = NORD
EST + DROITE = SUD

X,Y sont respectivement les coordonnées de la position de la tondeuse.

OUEST + AVANCE = X - 1
EST + AVANCE = X + 1
NORD + AVANCE = Y + 1
SUD + AVANCE = Y - 1

J'ai créé une énumération pour les direction de la tondeuse: SUD, OUEST, EST, NORD comme valeur.

Les classes modèles sont : Position, Tondeuse, Grille

J'ai pris en considération que la position de la tondeuse ne pouvait pas se mettre là où il y avait une autre tondeuse. 
(Sachant que l'on ne peut savoir le nombre de tondeuses après avoir lu le contenu du fichier en entré.)

La lecture du fichier se fait via un buffered reader,au cas où le fichier est avec une taille importante. La lecture se fait ligne par ligne.


