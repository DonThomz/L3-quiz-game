package org.farmas.model.questions;

import java.util.Iterator;
import java.util.LinkedList;

/* Donner l’implémentation de la classe ListeQuestions contenant :
 * - Un constructeur
 * - TODO Une méthode AfficherListe
 * - Une méthode AjouterQuestion à la liste
 * - Une méthode SupprimerQuestion de numéro n de la liste
 * - TODO Une (ou plusieurs) méthode(s) SelectionnerQuestion qui sélectionne une question pour un joueur selon une politique qui sera définie dans les différentes phases du jeu.
 */
public class ListQuestions implements Iterable<Question<?>> {
    private LinkedList<Question<?>> questions;

    public ListQuestions() {
        questions = new LinkedList<>();
    }

    public void addQuestion(Question<?> question) {
        questions.add(question);
    }

    public void delQuestion(int index) {
        questions.remove(index);
    }

    @Override
    public Iterator<Question<?>> iterator() {
        return questions.iterator();
    }

    public LinkedList<Question<?>> getQuestions() {
        return questions;
    }

}
