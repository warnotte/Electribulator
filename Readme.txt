For better informations please visit my website on http://renaud.warnotte.be and search for electribulator...

								E L E C T R I B U L A T O R 
								- - - - - - - - - - - - - - 

FR/
- Polyphonisateur et transformateur d'electribe EMX-1 100% ecrit en Java et utilisant le protocole MIDI. 
Utilisant un controleur type clavier ou sequenceur externe a l'electribe pour jouer les notes et les recevoir dans le logiciel 
et les envoyer sur les 5 cannaux de l'electribe afin de polyphonis� tout cela. 
- Permet �galement de rajouter un systeme complexe d'eneveloppe (VCAs), ainsi que plusieur modulateur (VCOs)assignable a tout les
controle disponible en midi sur l'electribe (filtre cutoff, level, pan, resonnance, ...).
- Systeme de presets pour creer et parametres d'un clique les sons de l'electribe avec un interface photorealiste ;)
- Chords systeme : Renvoyer plusieurs notes au lieu d'une seule.
- Arpegiator 
- Il n'est plus n�cessaire de toucher a l'electribe tout peut se faire maintenant de votre clavier midi et de votre pc a l'aide de quelques cliques.
- Il est possible aussi de piloter le programme (au niveau preset ainsi que pour tester le son en monophonique) avec les roulettes et keyboard de l'electribe.
- Possibilit� de sauver/charger les sons et les modulateurs.

EN/
- Enhancer and polyphonisator for electribe EMX-1, 100% written in Java and using the MIDI protocol to achieve it. 
Using a keyboard or external sequencer electribe to play the notes and get in the software then dispatched to
 the 5 channels of electribe. 
- Also allows to add a complex system of enveloppe (VCAs), as well as several modules (VCOs) assigned to all the 
control available (in midi CC or NRPN) on electribe (filter cutoff, level, pan, resonance, ...). 
- System presets and settings to create in a click, sound of electribe inside a nice interface.
- Arpegiator & MultiChords system.
- There is no need to touch the electribe and u can now be on your MIDI keyboard and on your PC. 
- It is also possible to operate the program (preset level and to test the sound in mono) with the wheels and the keyboard electribe. 
- Ability to save / load the sounds and modulators.




Utilisation/Usage:
==================

Electribe:
----------

FR/
- Configur� les synth part de l'electribe chacune sur un canal midi (de 1 a 5).
- Branchez le cable midi ;) etc.
- En th�orie maintenant, vous ne devrez plus toucher a l'electribe et vous mettre uniquement sur le pc, et le clavier midi (ou sequenceur?).
- En pratique vous pouvez jouer si vous avez configure l'entr�e midi de l'electribe sur le programme afin de recevoir les notes du keyboard et les changement de valeur venant de l'electribe (optionnel)

EN/
- Configure electribe's synth part each on a midi channel (1 to 5).
- Put midi cable from PC to Electribe (bidirectionnal or not).
- In theory now, u haven't to touch electribe anymore and concentrate on the PC.
- You can of course, use the electribe as a remote controle for the software.

PC:
---

FR/
Editer le fichier config.properties afin de configurer les cannaux midi.
Executer Electribulator.jar (ou go.bat/go.sh) pour lancer le programme.


Il vous demande sure quelle entr�e midi est branch� votre clavier midi (ou autres) configur� sur le canal 1.
Il vous demande ensuite sur quelle sortie midi est mise votre electribe. (ou autres synth� qui serait pareil?).
Il vous demande ensuite sur quelle entr�e midi est mise votre electribe. (pour recevoir les event de l'electribe, chg de valeur, notes on-off). (pas obligatoire)
Le programme se lance et la fenetre s'ouvre. 
Vous pouvez utiliser le programme ou charger un preset general et un preset de modulation.
EN/
Edit config.properties file to edit midi channel.
Execute Electribulator.jar (or go.bat/go.sh) to launch the software

It ask for MIDI IN (where notes come from, not the electribe).
It ask for MIDI OUT (where is electribe).
It ask for MINI IN (where electribe note and control will be sent if needed).
Software launch and window appear
U can use software or load a general/modulator presets.


Usage/Way of work:
=================

Polyphonie:
-----------

FR/
Vous avez maintenant la possiblit� de jouer sur votre clavier midi et de normalement constat� que l'electribe 
reagis a au moins une note.
Si ca fonctionne essayez avec plusieur notes en meme temps. Elle devrait normalement active toutes les voix (si 5 notes appuy�es)

La vous pouvez jouer en mode polyphonique. (j 'espere).

EN/
You have now the way to play on your midi keyboard and normally electribe should react to one or more note.
If it's try to play multiple notes :)

If work, everything went find.


Modulations:
------------

Pour se faire, choisiser un des 4 element modulateur disponible (VCA, VCOs).

	Enveloppe:
		Si on veux une modulation pour faire une enveloppe par exemple on va prendre le VCA.
		Cliquez sur configreceiver et choisissez la sortie midi ou se trouve l'electribe.
		Ouvre ensuite le collapsible panel CCDest pour avoir la config du canal (1 par defaut) et le CC associ�
		On va mettre 7 (pour le LEVEL) histoire d'avoir une enveloppe sur l'amplitude du son. (on pourrait mettre 74 pour le filtre, etc).
		En theorie vous devriez entendre une attack de 1000 ms, avec sustain.
		
		Vous pouvez ensuite parametrer l'enveloppe.
		Bypass pour annuler la modulation temporairement.
		(Il faut mettre les bon params dans chacun des 5 panels)
		
	Oscillation:
		Plusieur modulateurs disponible.
		Meme procedure que pour le VCA.
		
		Vous pouvez ensuite configurer la frequence.
		(Il faut mettre les bon params dans chacun des 5 panels)

Notes:
=======

- La fr�quence theorique d'envoi des commande midi pour les modulations et des +-100 hz actuelement. (Modifie la "bande passante midi")
- !!! Il faut se mefier que parfois si on coupe les modulateur ou le programme, le volume ou le filtre soit mis 0 donc on n'entends plus rien et il faut remettre le settings sur l'electribe!!! vu que le programme n'envoye pas encore le preset du son sur chaque synth
- Attention ne mettez pas plusieurs VCO du meme canal avec le meme CC sinon vous allez avoir d'horribles sons :). (logique me direz vous...)
- Le programme tente une connection sur renaud.warnotte.be port 80 pour tenter de recuperer la listes des updates disponible ! (pour les firewall paranoids).
- WARNING !!! IF YOUR ELECTRIBE BURN, EXPLODE, OR SOMETHING HORRIBLE HAPPENS, THIS PROGRAM, AND ME COULD NOT BE HELD AS RESPONSIBLE ;) (i'm just sending midi, make a DUMP of you current pattern and song in case of) !!!!
- When load multiple time preset and modulator u may experience some strange sounds, preset [Send presets] button 1 or 2 times to be sure emx have receive all informations. (there some midi message lost if too much troughtput).


Configuration PC:
=================

Mini
----

Java >=1.6.
>= 128 MB. 
CPU 1 Ghz. 
Midi interface convenablement rapide.

Conseill�
---------

>=256 MB.
6 CPU (1 cpu pour chaque cannaux pour generer le signal, 1 cpu pour la thread midi, 1 cpu pour l'affichage). 

Greetz:
=======

Donald Baynes aka FSK1138 for the first background made by users. 

Contact:
========

Warnotte Renaud

renaud@warnotte.be
fa313704@skynet.be
mulphex78@hotmail.com (msn).
https://www.facebook.com/Electribulator

------------------------------------------------------------------------
This software is property of Renaud Warnotte.
All commercial usage is forbidden.
------------------------------------------------------------------------
