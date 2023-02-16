#Sujet du projet

Ce projet a pour but la réalisation d’un programme mettant en œuvre la plupart des points du cours vus
ce semestre. Il représente un travail devant prendre environ 5 à 6 heures.
Le travail demandé est de proposer et d’implémenter un programme qui réalise une simulation se déroulant
sur un terrain représenté sous la forme d’un tableau à 2 dimensions contenant des ressources et sur lequel
des agents peuvent se déplacer et agir.

Le thème à respecter pour votre programme est "la Nature". Tout programme sur ce thème est donc
réalisable.

Le programme à réaliser doit respecter le cahier des charges suivant :
— la simulation se déroule sur un terrain de ressources représenté à l’aide de la classe Terrain qui est
fournie. Remarque : contrairement aux ressources, les agents ne sont pas mémorisés sur le terrain.
— la simulation gére des Ressource (classe qui est aussi fournie) placées sur le terrain, celles-ci ne
peuvent pas être déplacées d’elles-mêmes mais peuvent être retirées du terrain, et des agents qui
ont la capacité de se déplacer et de réaliser des actions sur le terrain (prendre des ressources par
exemple, ou en déposer).
— votre simulation devra utiliser au moins 2 types de ressources : des ressources dont l’état interne
ne change pas (leur quantité reste constante et ne peut être modifiée que par un agent) et des
ressources dont l’état interne peut évoluer sans l’intervention d’un agent (par exemple, par vieillissement (à chaque étape), la quantité peut évoluer au fil du temps, etc., ou la ressource peut se
transformer en un autre type (par exemple un bourgeon devient une fleur puis devient un fruit).
Les classes Terrain et Ressource sont fournies, accompagnées de leur documentation. Leur représentation
UML client est donnée en Annexe.


Nous avons choisi comme thème le cycle de vie d’un papillon, qui ponds des œufs, se
transforment en chenilles puis en papillons, nos agents doivent se nourrir pour survivre et les œufs
sont menacés par les araignées.
