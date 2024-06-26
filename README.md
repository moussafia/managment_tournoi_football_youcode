# [ Titre : Gestion de tournois de football. ](https://github.com/moussafia/managment_tournoi_football_youcode)

Les tournois au sein de notre école YouCode se déroulent annuellement dans le but de développer de manière indirecte 
les compétences en relations interpersonnelles (soft skills) et de favoriser une intégration rapide dans l'école. De plus, 
cela permet d'améliorer les compétences physiques. Le football fait partie de ces tournois, permettant ainsi d'améliorer à 
la fois les compétences physiques et les compétences relationnelles, étant un jeu d'équipe qui promeut l'esprit sportif 
et le respect envers les autres membres de l'équipe et les adversaires. Auparavant, la gestion de ces tournois se faisait 
de manière traditionnelle, mais maintenant, grâce à mon site web dédié à la gestion des tournois de football, cette gestion 
sera numérisée. Ce site web me permettra également, d'une autre part, d'améliorer mes compétences techniques en Spring Boot et en Angular.

# [ Technologies Utilisées ]()

Ce projet utilise plusieurs technologies pour sa mise en œuvre. Voici une liste des principales technologies utilisées :

## Spring Boot
<img src="https://github.com/moussafia/managment_tournoi_football_youcode/blob/main/fakeData/logoTechnologie/Sans%20titre.png" alt="Illustration pour Lombok" width="50" height="50">

Spring Boot est un framework Java qui facilite la création de nouvelles applications Spring. Il offre une configuration par défaut pour démarrer rapidement les projets et simplifie le développement d'applications Java.

## Spring Data

<img src="https://github.com/moussafia/managment_tournoi_football_youcode/blob/main/fakeData/logoTechnologie/jpa.png" alt="Illustration pour Lombok" width="50" height="50">

Spring Data simplifie l'accès aux bases de données en fournissant une abstraction au-dessus de la couche de persistance. Il facilite l'interaction avec les bases de données relationnelles et NoSQL dans les applications Spring.

## Lombok

<img src="https://github.com/moussafia/managment_tournoi_football_youcode/blob/main/fakeData/logoTechnologie/lombook.png" alt="Illustration pour Lombok" width="50" height="50">

Lombok est une bibliothèque Java qui permet de réduire le code boilerplate en générant automatiquement du code pour les getters, les setters, les constructeurs, etc.

## Mail Starter

<img src="https://github.com/moussafia/managment_tournoi_football_youcode/blob/main/fakeData/logoTechnologie/mail.jpg" alt="Illustration pour Lombok" width="50" height="50">

Mail Starter est un module de Spring Boot qui simplifie l'envoi d'e-mails dans les applications. Il fournit des fonctionnalités pour envoyer des e-mails de manière simple et efficace.

## Spring Security

<img src="https://github.com/moussafia/managment_tournoi_football_youcode/blob/main/fakeData/logoTechnologie/cloudinary.png" alt="Illustration pour Lombok" width="50" height="50">

Spring Security est un module de sécurité pour les applications Spring qui gère l'authentification, l'autorisation et d'autres aspects de la sécurité des applications.

## Freemarker

<img src="https://github.com/moussafia/managment_tournoi_football_youcode/blob/main/fakeData/logoTechnologie/cloudinary.png" alt="Illustration pour Lombok" width="50" height="50">

Freemarker est un moteur de template Java utilisé pour générer des pages web dynamiques. Il permet de séparer la logique de présentation du code Java dans les applications web.

## OpenAPI (Swagger)

<img src="https://github.com/moussafia/managment_tournoi_football_youcode/blob/main/fakeData/logoTechnologie/swagger.png" alt="Illustration pour Lombok" width="50" height="50">

OpenAPI, anciennement connu sous le nom de Swagger, est un outil de documentation pour les API RESTful. Il permet de décrire, de documenter et de tester les API de manière cohérente.

## Cloudinary

<img src="https://github.com/moussafia/managment_tournoi_football_youcode/blob/main/fakeData/logoTechnologie/cloudinary.png" alt="Illustration pour Lombok" width="50" height="50">

Cloudinary est un service de gestion des médias basé sur le cloud. Il offre des fonctionnalités telles que le stockage, la gestion et la livraison d'images et de vidéos dans les applications web.

## Angular

<img src="https://github.com/moussafia/managment_tournoi_football_youcode/blob/main/fakeData/logoTechnologie/angular.png" alt="Illustration pour Lombok" width="50" height="50">

Angular est un framework JavaScript open-source développé par Google pour la construction d'applications web monopage (SPA). Il offre une structure pour le développement côté client en utilisant TypeScript et une architecture basée sur des composants.

Angular est souvent utilisé pour créer des applications web interactives et dynamiques. Il fournit des fonctionnalités puissantes telles que la liaison de données bidirectionnelle, la manipulation du DOM, le routage, la gestion des formulaires et bien plus encore.

## ngrx/store

<img src="https://github.com/moussafia/managment_tournoi_football_youcode/blob/main/fakeData/logoTechnologie/angular.png" alt="Illustration pour Lombok" width="50" height="50">

ngrx/store est une bibliothèque de gestion d'état pour les applications Angular, inspirée du modèle Redux. Elle offre un moyen efficace de gérer l'état de l'application de manière prévisible, en utilisant un flux de données unidirectionnel.


# Configuration et Exécution des Applications dans Docker

Ce guide vous explique comment configurer et exécuter des applications Spring Boot et Angular dans Docker à l'aide de Docker Compose.

## Étapes

1. **Cloner le Référentiel GitHub :**
   - Utilisez la commande suivante pour cloner le référentiel GitHub contenant les applications Spring Boot et Angular :
     ```
     git clone git@github.com:moussafia/managment_tournoi_football_youcode.git
     ```

2. **Configuration de Docker Compose :**
   - Assurez-vous d'avoir un fichier `docker-compose.yml` à la racine de votre projet. Voici un exemple de configuration :

     ```yaml
     services:
           y-soccer-backend-service:
             build: ./managment_tournoi_backend
             container_name: y-soccer-backend-service
             ports:
               - '9000:9000'
             expose:
               - 9000
             depends_on:
                y-soccer-database:
                   condition: service_healthy
             healthcheck:
               test: ["CMD" , "curl", "http://localhost:9000/actuator/health"]
               interval: 10s
               timeout: 5s
               retries: 4
             restart: always
             environment:
               spring.datasource.url: jdbc:mysql://y-soccer-database:3306/Ysoccerdb?createDatabaseIfNotExist=true
               spring.datasource.username: root
               spring.datasource.password: 
         
           y-soccer-database:
             container_name: y-soccer-database
             image: mysql:latest
             volumes:
               - mysql-data:/var/mysql
             ports:
               - '3307:3306'
             environment:
               MYSQL_DATABASE: Ysoccerdb
               MYSQL_ROOT_PASSWORD: ''
               MYSQL_ALLOW_EMPTY_PASSWORD: 'yes'
             healthcheck:
               test: ["CMD-SHELL", "mysqladmin ping -h localhost"]
               interval: 10s
               timeout: 5s
               retries: 4
         
         
           phpmyadmin:
             image: phpmyadmin/phpmyadmin
             container_name: phpmyadmin
             ports:
               - '8081:80'
             environment:
               PMA_ARBITRARY: 1
               MYSQL_HOST: y-soccer-database
               MYSQL_PORT: 3307
               MYSQL_USER: root
               MYSQL_PASSWORD: ''
             depends_on:
               y-soccer-database:
                 condition: service_healthy
         
         
         
           y-soccer-frontend:
             build: ./managment_tournoi_frontend
             container_name: y-soccer-frontend
             ports:
               - '4200:80'
             expose:
               - '4200'
             depends_on:
               y-soccer-backend-service:
                 condition: service_healthy
             restart: always

      volumes:
        mysql-data:
     ```


3. **Exécution de Docker Compose :**
   - Exécutez Docker Compose avec la commande suivante :
     ```
        docker-compose up
     ```

4. **Accès aux Applications :**
   - L'application Spring Boot sera accessible à l'adresse : `http://localhost:9000`
   - L'application Angular sera accessible à l'adresse : `http://localhost:4200`

C'est tout ! Vous avez maintenant vos applications Spring Boot et Angular en cours d'exécution dans des conteneurs Docker, prêtes à être utilisées.

---
5. **Comptes utilisés pour accéder à mon projet :**
   - username : ahmed@gmail.com
   - password: 1234

---

