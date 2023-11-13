cahier des charges CS3-v1:
	Jeux:
		deux equipes
		T vs CT

		les parties se jouent en manches la premiere equipes a a voir gagnee N manches a gagner la partie.
		Au debut de chaque manche tout les joueurs sont tp aleatoirement dans la zones de leur de team. Un des T spawn avec la bombe.
		Pendant 20 secondes c'est le shop-time CAD que les joueurs ne peuvent pas bouger mais uniquement acheter de l'equipement pour la manche. Ils commencent la partie avec 800 credits;
		La manche se termine quand tout les joueurs d'une equipes sont morts ou que les T ont reussis a poser la bombe.
		Pour poser la bombe un T doit poser la bombe au point B ce qui lui prend 5 secondes. Les T gagnent ensuite la manche dans les 40 sec.
		Pour gagner les CT doivent tuer toutes l'equipes T ou  desamorcer la bombe en restant dessus pendant 10 secondes (ou 5 en achetant l'item pendant le shop-time) avant le temps imparti.
		A la fin de chaque manche chaque joueur recoit 1500 credits \
								+ 700 si victoire  \
								+ 350 par kills \
								+ 600 si t'as poser/desamorcer la bombe \
								+ 350 si ton equipes a poser/desamorcer la bombe et que c'est pas toi qui l'a fais












	outils:
		Un outils pour set les variables dans une config:
			- nombre max de joueur dans chaque team (5)
			- nombre de mancher pour gagner (8)
			- ItemStack bombe
			- ShopTimeDuration (20)
			- Credit de depart
			- Le shop
			- Le point B
			- Temps requis pour poser la bombe (3)
			- Temps requis pour que la bombre explose (40)
			- Temps requis pour desamorcer la bombe a la main (10)
			- Temps requis pour desamorcer la bombe avec l'outils (5)
			- ItemStack outils
			- Pris outils (400)
			- Credit a la fin de la manche (1500)
			- Credit en cas de victoire d'une manche (700)
			- Credit par kill (350)
			- Credit pour avoir poser/desamrocer la bombe (600)
			- Credit en cas de bombe pose/desamorcee par un autre joueur de l'equipe (350)
			- zone de spawn T
			- zone de spawn CT

	Pour utiliser l'outil il faut faire la commande /gameconfig -n <Nom de la config>
	Apres avoir fais ca par defaut toutes ses utilisation de la commande sans repreciser le tag '-n' seront pour la config avec ce nom.
	La commande gameconfig a les tags suivents:
			name: 
				- permet de choisir sur quelle config on va travailler
				example: /gameconfig -name call_of_duty
				aliases: n
			save:
				- permet de sauvegarder la config
				examples:
					- /gameconfig -save # Cette commande s'appliquera a la derniere config utilisee
					- /gameconfig -n Une_autre_config -save
				aliases: s
			shop:
				- permet d'ouvrir le GUI du shop
				examples:
					- /gameconfig -shop
					- /gameconfig -n Une_autre_config -shop -s # Cette commande sauvegarde la config avant d'ouvrir le GUI
					aliases: sh
			const:
				- permet d'ouvrire le GUI pour modifier les variables (ex: le temps de shop-time)
				examples:
					- /gameconfig -const
				aliases: c
