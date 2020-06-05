package org.farmas.model.questions;

import java.util.Iterator;
import java.util.LinkedList;

/* Donner l’implémentation de la classe ListeQuestions contenant :
 * - TODO Une méthode AfficherListe
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
