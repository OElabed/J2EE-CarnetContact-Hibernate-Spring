Procédure d'installation:

L'application Web est constituée d'un projet Tomcat. 
Celui-ci est prêt à l'emploi et contient toutes les librairies nécessaires, et peut donc être déployé via Tomcat Manager.
Il est cependant nécessaire de configurer le fichier WebContent/WEB-INF/database.xml pour y fournir les coordonnées
de la base de données MySQL à utiliser.

L'application contient aussi un dossier .project qui permet de l'ouvrir normalement dans l'environnement de développement
Eclipse. Afin de pouvoir exécuter le projet dans cet environnement il sera nécessaire d'indiquer à Eclipse l'emplacement
du dossier d'installation de Tomcat (Window->Preferences->Server->Runtime Environments, puis Project->Properties->Server).

La version de Tomcat testée durant le projet est la version 8.


Grille de notation:

Voici les emplacements dans notre projet où vous pouvez vérifier la bonne implémentation des différents éléments de la grille de notation.

First name, last name: classes métier (package domain.modeles.metier, plus précisément Contact.java)
Fonctions CRUD, recherche: contrôleurs dans domain.controleurs, couche service dans domain.modeles.service, couche persistance dans domain.modeles.persistance

Hibernate (+ intégration Spring): mappings dans domain.modeles.metier, persistance dans domeina.modeles.persistance, configuration dans WebContent/WEB-INF/hibernate.xml

Spring IoC: utilisé dans les couches contrôle et service, beans dans WebContent/WEB-INF/metier.xml, persistance.xml, service.xml
Spring AOP: utilisé pour les logs: classe util.Log, fichier de config WebContent/WEB-INF/aop.xml

JSF: MVC (séparation Facelet / ManagedBean), fichiers .xhtml dans WebContent/jsf, utilisation de required et pattern pour les contrôles simples
et vérification par méthodes du contrôleur pour les contrôles plus complexes (ex: "Au moins un numéro de téléphone").