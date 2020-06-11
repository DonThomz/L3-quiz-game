# java-project-l3
Projet de Java L3 

## Exécuter le projet

Prérequis : avoir Java 11 comme version de Java par défaut sur le système. Sinon impossible de run l'executable .jar

Pour lancer le programme,
Dans le dossier `shade`, lancer le fichier .jar avec la commande `java -jar java-project-l3.jar`
  
## Build le projet

1. Ouvrir le projet depuis votre IDE (nous avons utilisé Intellij 2020.1)
2. Spécifier le JDK à utiliser (JDK 11.*)
3. (Si besoin, faire charger les plugins et librairies Maven depuis le ***pom.xml***)
4. Lancer les tâches Maven suivantes :

   ```shell
   javafx:compile
   javafx:run
   ```

Sur certains systèmes, il peut être nécessaire de préciser
au plugin javafx-maven-plugin l'emplacement de Java 11, grâce au fichier de configuration pom.xml

Il suffit de rajouter la balise `<executable>` contenant l'emplacement de votre JDK 11.
Autour de la ligne 60, rajouter cette balise dans la balise `<configuration>` du plugin comme
le montre l'exemple ci-dessous.

```xml
<plugin>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-maven-plugin</artifactId>
    <version>0.0.1</version>
    <configuration>
        <mainClass>org.farmas.App</mainClass>
        <executable>C:/Program Files/Java/jdk-11.0.7/bin/java.exe</executable>
```




