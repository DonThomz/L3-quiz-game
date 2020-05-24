package org.farmas.model.game.phase;


/**
 * Une partie de jeu se déroule en plusieurs phases. A chaque phase, le joueur ayant le plus faible score
 * est éliminé et les autres sont sélectionnés pour la phase qui suit.
 * Implémenter une interface Phase comportant les méthodes suivantes :
 * - SelectionnerJoueurs
 * - PhasedeJeu
 */
public interface Phase {
    public void selectPlayer();

    public void phaseDeJeu();
}
