LaTeX-Vorlage für Abschlussarbeiten
=======
(English Version below.)

Dies ist die Vorlage für technische Berichte der Forschungsgruppen Software 
Design and Quality (SDQ) am  KASTEL — Institut für Informationssicherheit 
und Verlässlichkeit des Karlsruher Instituts für Technologie (KIT).

Vielen Dank an Markus Kohm (http://www.komascript.de) für die hilfreiche
Unterstützung beim Erstellen dieser Vorlage.

Version
=======
Version: 1.0
Autor: Dr.-Ing. Erik Burger (burger@kit.edu)

Siehe https://sdq.kastel.kit.edu/wiki/Dokumentvorlagen

Verwendung
==========
Das vorliegende Paket dient als Vorlage für eine Abschlussarbeit. Sie können
dazu die bestehende Hauptdatei `techreport.tex` anpassen, indem Sie den
Beispieltext entfernen und durch eigene Inhalte entfernen. 

Die Klasse basiert auf `scrbook` aus dem Paket KOMA-Script. Somit können alle 
Optionen dieser Klasse verwendet werden. 

Sprache
-------
Die Sprache des Dokuments ist standardmäßig auf Englisch eingestellt.
Dies kann in der `\documentclass`-Anweisung am Anfang von `techreport.tex` auf Deutsch 
umgestellt werden.

Einseitig/doppelseitig
----------------------
Das Dokument ist standardmäßig auf doppelseitiges Layout eingestellt, kann aber
durch die Angabe von `oneside` in der `\documentclass`-Anweisung am Anfang von
`techreport.tex` auf einseitiges Layout umgestellt werden.

Draft-Modus
-----------
Der Draft-Modus kann verwendet werden, um eine Entwurfsfassung zu generieren. 
Das kann durch die Option `draft` in der `\documentclass`-Anweisung am Anfang
von `techreport.tex` geschehen, oder durch eine Einstellung innerhalb der
LaTeX-Umgebung.  Die entsprechende Option für das endgültige Dokument lautet
`final`.  Im Draft-Modus werden z.B.  todo-Notizen sowie Platzhalter für
fehlende Abbildungen angezeigt, im `final`-Modus jedoch ausgeblendet.

LaTeX allgemein
---------------
Siehe https://sdq.kastel.kit.edu/wiki/LaTeX

Dateistruktur
============
`techreport.tex`
----------------
Dies ist die Hauptdatei des LaTeX-Dokuments. 

`sdqtechreport.cls`
-------------------
Dies ist die Vorlage, die die Stil-Informationen für das Dokument enthält.

`logos/`
--------
In diesem Verzeichnis befindet sich das SDQ-Logo als PDF und EPS.

`README.md`
-----------
Dieser Text.

English Version
===============
This is a template for technical reports at the research groups of Software Design
and Quality (SDQ) at the  KASTEL — Institute of Information Security and Dependability 
at Karlsruhe Institute of Technology (KIT).

Many thanks to Markus Kohm (http://www.komascript.de) for his support in
creating the template.

Version
=======
Version: 1.0
Author: Dr.-Ing. Erik Burger (burger@kit.edu)

See https://sdq.kastel.kit.edu/wiki/Dokumentvorlagen

Usage
=====
This package is used as a template technical reports. To use it, adapt the main
file `techreport.tex` by removing the example text and replacing it with your
own content.

The class is based on `scrbook` from the KOMA-Script package. All options of
this class can be used here as well.

Language
--------
The standard language of this document is English. You can change the
language in the `\documentclass` command at the beginning of `techreport.tex`.
German and English are available.

One-sided/two-sided layout
--------------------------
The standard format is two-sided layout. You can change this to one-sided
layout in the `\documentclass` command at the beginning of `techreport.tex`.

Draft mode
----------
The draft mode can be activated with the option `draft`
in the `\documentclass` command at the beginning of `techreport.tex`,
or by choosing the appropriate compile mode in the LaTeX IDE.
In `draft` mode, todo-notes and placeholders for missing graphics are displayed,
while they are omitted in `final` mode.

LaTeX
-----
See https://sdq.kastel.kit.edu/wiki/LaTeX


File structure
==============
`techreport.tex`
---------------
This is the main file of your LaTeX document. Please insert your data there.

`sdqtechreport.cls`
------------------
This is the style template for the document. Please do not modify this file,
so that all theses appear in the same style.

`logos/`
--------
This directory contains the KIT and SDQ logo.

`README.md`
-----------
This text.
