package org.farmas.model.questions;

import java.util.LinkedList;

/**
 * Pour chaque thème, l’on dispose d’un ensemble de questions dont le nombre et les types sont
 * variables. L’ensemble des questions relatives à un thème est stocké dans une liste chainée de
 * questions. A chaque fois, un indicateur de la question sélectionnée est mis à jour.
 * Donner l’implémentation de la classe ListeQuestions contenant :
 * - Un constructeur
 * - TODO Une méthode AfficherListe
 * - Une méthode AjouterQuestion à la liste
 * - Une méthode SupprimerQuestion de numéro n de la liste
 * - TODO Une (ou plusieurs) méthode(s) SelectionnerQuestion qui sélectionne une question pour un joueur selon une politique qui sera définie dans les différentes phases du jeu.
 */
public class ListQuestions {
    LinkedList<Question<?>> questions = new LinkedList<>();

    public ListQuestions() {

    }

    public void addQuestion(Question<?> question) {
        questions.add(question);
    }

    public void delQuestion(int index) {
        questions.remove(index);
    }
}
