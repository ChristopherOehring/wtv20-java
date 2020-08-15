# Wissenschaftlich Technische Visualisierung SS 2020
Ergebnisse von Christopher Oehring

Dieses Projekt befindet sich auf GitHub unter https://github.com/ChristopherOehring/wtv20-java

## Generelle Anmerkungen:
Im nächsten Punkt gibt es eine Übersicht über alle Aufgaben und inwiefern sie implementiert sind. Außerdem gibt es im Packet [demo](src/com/wtv/demo) zu einigen Aufgaben eine kleine Demoklasse, die die jeweiligen funktionalitäten demonstriert.  

**Demos Starten**  
Es gibt zwei Möglichkeiten die Demos zu starten: 
1. Das Project per Git clonen und in eine IDE wie IntelliJ oder Eclipse importieren und anschließend den [DemoRunner](src/com/wtv/demo/DemoRunner.java) per IDE starten.
2. Das projekt per Git clonen oder herunterladen und entpacken. In einer commandozeile in das Projekt hinein navigieren, und folgenden befehl ausführen: 
 `java -jar .\out\artifacts\wtv20_java_jar\wtv20-java.jar`

Hinweis: für diesen Befehl wird eine Installation von Java 8 oder höher benötigt.

Weitere Informationen zu den jeweiligen Demos befinden sich unten in der Übersicht. 

**Allgemeine Wertung:**    
Im Allgemeinen hätte der Code deutlich übersichtlicher sein können.   
Alleine, dass ich static methoden statt Objektorientierung verwendet habe hat es deutlich kompliziertert gemacht neue features hinzuzufuegen und könnte im nachhinein auch noch zu Problemen führen, wenn z.B. die Einstellungen im [SVGConverter](src/com/wtv/converter/SVGConverter.java) plötzlich unerwartet von einer anderen Methode geändert werden. Eine Objektorientierte Lösung wie in Anton Wetzels C# projekt aus dem Wiki hätte deutlich mehr sinn gemacht.

Es ist zwar nicht alles dokumentiert, aber die Dokumentation ist meiner Meinung nach ausreichend. Es ist vielleicht suboptimal, dass die Sprache zwischen deutsch und Englisch wechselt.

Die Grundfunktionalitäten wie das Generieren von IsoLinien auf verschiedenen Wegen funktionieren eigentlich ziemlich gut.  
Das Generieren visualisieren von Pfaden ist nicht so gut erkennbar, funktioniert aber grundsätzlich.  

Insgesamt habe ich das Programm nicht systematisch getestet. Von daher gibt es wahrscheinlich noch einige versteckte fehler.

## Ergebnisse anhand der Übungsaufgaben:  
### **Übungsblatt 1**  
#### Aufgabe 2:  
##### `A)`  
**Implementiert mit Zusatz 1,2,3 und teilweise 4**

Diese Aufgabe wurde in der [CSVReader](src/com/wtv/converter/CSVReader.java) Klasse in der Methode `dateiLesen2D` gelöst. Die Methode ist nicht sehr kompliziert und daher gut lesbar.

**Demo:**
Eine Datei wird eingelesen und in der Konsole ausgegeben.
`B)` 

**Implementiert** 

Die erste Lösung dieser Aufgabe wurde zum Lösen anderer Aufgaben weiter modifiziert. Die Dreiecke können nun mit der `triangles` Methode in [Isolinien](/src/com/wtv/processing/Isolinien.java) generiert, und mit der `objCreateTriangles` Methode im [ObjConverter](/src/com/wtv/converter/ObjConverter.java) geschrieben werden.

**Demo:**
Zur Ausgabe wird eine Datei mit demselben Namen und der Endung .obj erstellt oder überschrieben.

### **Übungsblatt 2**  
#### Aufgabe 1:  
##### `A,B)`
**Implementiert**

Die entsprechenden Möglichkeiten aus B sind in der Klasse [Interpolation](/src/com/wtv/processing/Interpolation.java) implementiert.

**Demo:** 
Auf die in A) vorgeschlagenen parameter werden nacheinander die verschiedenen Möglichkeiten aufgerufen.

#### Aufgabe 2:
**Demo:**  
Zu dieser Aufgabe gibt es eine gemeinsame Demo.  
Als Ausgabe werden Dateien mit demselben Namen und den Endungen .eps und .svg erstellt oder überschrieben.
##### `A)`
**Implementiert**  
Das Auslesen der Daten erfolgt über die [CSVReader](src/com/wtv/converter/CSVReader.java) Klasse in der Methode dateiLesen2D (wie bereits in 1a demonstriert). Außerdem kann mit der Methode arrayToSegments eine Liste von [LineSegment](src/com/wtv/structures/LineSegment.java)s generiert werden.

##### `B)`
**Implementiert**   
Diese Methode existiert so auch nicht mehr. Mit ein bisschen tricksen kann aber die für eine Spätere Aufgabe modifizierte Methode `SVGCreateIsoFromSegments` aus [SVGConverter](src/com/wtv/converter/SVGConverter.java) noch verwendet werden.

##### `D)` 
**Implementiert**    
Hierzu gibt es die [EPSConverter](src/com/wtv/converter/EPSConverter.java) Klasse
##### `F)`  
**Nicht Implementiert**


### **Übungsblatt 3**
#### Aufgabe 1:


**Demo:**  
Zu dieser Aufgabe gibt es eine gemeinsame Demo. Als Ausgabe werden Dateien mit demselben Namen und den Endungen .obj und .svg erstellt oder überschrieben.

##### `A)`
**Implementiert**    
Das Auslesen der Daten erfolgt über die [CSVReader](src/com/wtv/converter/CSVReader.java) Klasse in der Methode `dateiLesen2D` (wie bereits in 1a demonstriert). 

##### `B)`
**Implementiert**   
Hierzu gibt es die Methoden `min` und `max` in der [Isolinien](src/com/wtv/processing/Isolinien.java) Klasse  

##### `C)`
**Implementiert**   
Hierzu gibt es die Methoden `scale` und `move` in der [Isolinien](src/com/wtv/processing/Isolinien.java) Klasse  

##### `D)`
**Implementiert**  
Hierzu gibt es die Methode `lineHeights` in der [Isolinien](src/com/wtv/processing/Isolinien.java) Klasse  

##### `E)`
**Implementiert**  
Hierzu gibt es die Methode `triangles` in der [Isolinien](src/com/wtv/processing/Isolinien.java) Klasse  

##### `F)`
**Implementiert**  
Hierzu gibt es die Methode `getIsoLine` in der [Isolinien](src/com/wtv/processing/Isolinien.java) Klasse  

##### `G)`
**Implementiert**  
Hierzu gibt es die [EPSConverter](src/com/wtv/converter/EPSConverter.java) Klasse. Die SVG Implementierung wurde in I) modifiziert

##### `H)`
**Implementiert**  
Hierzu gibt es die `getPathsOfAllIsolines` in der [Isolinien](src/com/wtv/processing/Isolinien.java) Klasse

##### `I)`
**Implementiert**  
Hierzu gibt es die Methode `SVGCreateIsoFromSegments` in der [SVGConverter](src/com/wtv/converter/CSVReader.java) Klasse

#### Aufgabe 2
##### `A)`
**Implementiert**  
In der Klasse [ColorGenerator](src/com/wtv/processing/ColorGenerator.java)

##### `B)`
**Teilweise Implementiert**  
Die Farben wurden direkt in den CSVReader integriert und sind nicht Optional

##### `C-F)` 
**Nicht Implementiert**

#### Übungsblatt 4
**Demo**  
In dieser Demo werden dann, per Pfad, isolinien generiert, wobei die einzelnen Pfade durch die Liniendicke entstehen
##### `A)`
**Implementiert**  
In der Klasse [Isolinien](src/com/wtv/processing/Isolinien.java).

Mein erster Versuch war eine baumartige Struktur mit nur einer [PathNode](src/com/wtv/structures/PathNode.java) Klasse. Diese Methoden sind immernoch im Bereich `//Via Pathnodes` zu finden.
Dies hat allerdings zu einigen Problemen geführt und ich habe mich letztendes dafür entschieden das ganze nochmal anders zu machen.  

Die neue Version befindet sich darunter im Bereich `//Via Path in "Ordered Lists"`

##### `B)`
**Teilweise Implementiert**  
Testbeispiele sind im Ordner [examples](examples) zu finden.

##### `C)`
**Implementiert**  
Über die Methode `SVGCreateIsoFromPath` der Klasse [SVGConverter](src/com/wtv/converter/CSVReader.java) kann die SVG datei erstellt werden, und über das Feld `visualizePaths`  kann die visualisierung der Pfade per Liniendicke eingeschaltet werden.

##### `D)`
**Teilweise Implementiert**  
Diese maßnahmen wurden nur für `SVGCreateIsoFromPath` und nicht für andere Methoden implementiert.

Die Reduzierung der Gleitkommagenauigkeit auf 2 nachkommastellen ist standardmäßig eingestellt und kann durch das Modifizieren des Rounder Objektes im [SVGConverter](src/com/wtv/converter/CSVReader.java) per `setDecimalPlacesString` verändert werden. Dies erhöht grundsätzlich die Lesbarkeit, reduziert die Dateigröße und hat meiner Meinung nach keinen zu großen Einfluss auf das Ergebnis.

Liniensegmente werden per nicht einzeln gespeichert. Hierdurch wird sowohl die Lesbarkeit verbessert, als auch die Dateigröße reduziert.

Es werden Zeilenumbrüche, sowie eine Einrückung mit Tabs vorgenommen.

###  **Übungsblatt 5**

#### Aufgabe 2
##### `A)` 
**Implementiert**  
In die Methode `getControlPoints` der Klasse [Curves](src/com/wtv/processing/Curves.java) kann ein Pfad eingegeben werden, woraufhin in der Rückgabe die Kontrollpunkte zwischen den jeweiligen Punkten eingefügt werden.

##### `B)`
**Teilweise Implementiert**  
Nur für SVGs: kann über die Variable `curvedMode` des [SVGConverter](src/com/wtv/converter/CSVReader.java) aktiviert werden.

##### `C)`
**Implementiert**  
**Demo**  
Hier wird eine entsprechende datei mit 5 abgerundeten Isolinien generiert;

### **Übungsblatt 6**
#### Aufgabe 1)

##### `A)`
**Implementiert**  
In der Methode `interpolateColor` der Klasse [ColorGenerator](src/com/wtv/processing/ColorGenerator.java)

##### `B)`
**Nicht Implementiert**  

#### Aufgabe 2)
**Nicht Implementiert**

### **Übungsblatt 7**
#### Aufgabe 1)

**Demo**  
Das Programm versucht dann entsprechende Datei auszulesen und 5 dateien mit IsoObjekten aus IsoCubes zu generieren. Diese werden durch eine Nummerierung am Ende des Dateinahmen unterschieden.
##### `A)`
**Implementiert**  
In der Methode `dateiLesen3D` der Klasse [CSVReader](src/com/wtv/converter/CSVReader.java)

##### `B)`
**Implementiert**  
In den Methoden `findMin` und `findMax` der Klasse [IsoSurface](src/com/wtv/processing/IsoSurface.java)

##### `C)`
**Implementiert**  
In der Methode `findIso` der Klasse [IsoSurface](src/com/wtv/processing/IsoSurface.java)
 
##### `D)`
**Implementiert**  
In der Methode `objCreateTetrahedrons` der Klasse [ObjConverter](src/com/wtv/converter/ObjConverter.java)

##### `E)`
**Implementiert**  
Durch die Methode `printIsoTetrahedrons` der Klasse [IsoSurface](src/com/wtv/processing/IsoSurface.java)

Ein Ergebnis sieht z.B. so aus: <img src="src\com\wtv\converter\doc-files\tetrahedrons.PNG">
##### `F)`
**Implementiert**  
Siehe die default Einstellung der Demo.

##### `G)`  
**Implementiert**  
Durch die Methode `printIsoCubes` der Klasse [IsoSurface](src/com/wtv/processing/IsoSurface.java)

#### Aufgabe 2)
**Nicht Implementiert**  
