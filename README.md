# java-project-l3
Projet de Java L3 



Phase I :
	-> 10 thèmes choisis
	-> 1 thème par questions
	-> 4 joueurs
	-> 4 questions faciles de types QCM ou VF ou RC
	-> +2 si bonne rep
	-> 4 timers => 1 par joueur

Phase II :
	-> 6 thèmes choisis
	-> 1 thème par questions => joueur choisi le thème à la suite
	-> 3 joueurs
	-> 6 questions moyennes de types QCM ou VF ou RC selon le thème
	-> +3 si bonne rep
	-> 3 timers => 1 par joueur

Phase III :
	-> 3 thèmes choisis aléatoirement
	-> 1 thème par questions
	-> 2 joueurs
	-> 3 questions par joueurs 
	-> +5 si bonne rep
	-> 2 timer => 1 par joueur
	
Conflit : 
	-> si égalité score => comparaison timer
	-> si égalité timer => phase III pour départager
	-> si égalité score phase III => choisi aléaoirement


Features Principal :
	-> afficher les thèmes
	-> créer une liste de questions pour chaque thèmes
	-> afficher les questions d'un niveau N par un thème T
	-> ajouter une question pour un thème T
	-> supprimer question de numero X dans thème T
	-> créer un tableau joueurs + afficher stats (score, etat)
	-> lancer une partie 4 joueurs 
	-> quitter le jeu
	-> gérer les persistances ( si bdd ) 
	-> score board ( bonus ) 
	
Lancement Programme :
	
	A - Lancer une partie 
		I - Initialization des joueurs 
		II - Lancement partie 

			-> Choix de 4 joueurs 

			-> Lancement Phase I (4 Joueurs)
				-> Initialization des 4 questions faciles / 4 thèmes
				-> Chaque joueur répond* + timer incrémenté
				-> Fin => joueur avec + faible score => éliminé => vérifie les conflits

			-> Lancement Phase II (3 Joueurs)
				-> joueur choisi 1 thème à la suite (x6) -> associé les thèmes sélectionnés aux joueurs 
				-> Initialization des 6 questions moyennes
				-> Chaque joueur répond* à leurs deux questions + timer incrémenté
				-> Fin => joueur avec + faible score => éliminé => vérifie les conflits

			-> Lancement Phase III (2 Joueurs)
				-> Initialization des 3 thèmes aléatoirement
				-> Initialization des 6 questions durs selon les thèmes
				-> Chaque joueur répond* + timer incrémenté 
				-> Fin => joueur avec + faible score => éliminé => vérifie les conflit
				-> Classement + scores affichés 
			
			* Politique Round-Robin ( manière circulaire )

			-> Partie enregistrer dans tableaux des scores ( bonus ) 

		



