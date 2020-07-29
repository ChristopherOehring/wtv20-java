#Wissenschaftlich Technische Visualisierung SS 2020
Ergebnisse von Christopher Oehring

##Generelle Anmerkungen:
Nachfolgend gibt es eine Übersicht über alle Aufgaben und inwiefern sie implementiert sind. Außerdem gibt es im packet demo zu jeder Aufgabe eine kleine demo Klasse, die die jeweiligen funktionalitäten demonstriert.  

Weitere Informationen zu den jeweiligen Demos befinden sich unten in der Übersicht. 

## Ergebnisse anhand der Übungsaufgaben:
### **Übungsblatt 1**
#### Aufgabe 2:
#####`A)` 
**Implementiert mit Zusatz 1,2,3 und teilweise 4**

Diese Aufgabe wurde in der [CSVReader](src/com/wtv/converter/CSVReader.java) Klasse in der Methode `dateiLesen2D` gelöst. Die Methode ist nicht sehr kompliziert und daher gut lesbar.

**Demo:**
In der Demo kann Optional ein Dateipfad und als Optionaler 2. Parameter ein Spaltentrenner übergeben werden. Sonst werden als default "examples\test.csv" als file und "," als Spaltentrenner verwendet.  

`B)` 

**Implementiert** 

Die erste Lösung dieser Aufgabe wurde zum Lösen anderer Aufgaben weiter modifiziert. Die Dreiecke können nun mit der `triangles` Methode in [Isolinien](/src/com/wtv/processing/Isolinien.java) generiert, und mit der `objCreateTriangles` Methode im [ObjConverter](/src/com/wtv/converter/ObjConverter.java) geschrieben werden.

**Demo:**
Die Eingabe funktioniert wie in A.
Zur Ausgabe wird eine Datei mit demselben Namen und der Endung .obj erstellt oder überschrieben.

### **Übungsblatt 2**
#### Aufgabe 1:
##### `A,B)`
**Implementiert**

Die entsprechenden Möglichkeiten aus B sind in der Klasse [Interpolation](/src/com/wtv/processing/Interpolation.java) implementiert.

**Demo:** 
Als Eingabe können die drei double Werte wie in a) vorgeschlagen übergeben werden.

#### Aufgabe 2:
**Demo:** Zu dieser Aufgabe gibt es eine gemeinsame Demo. In der Demo kann Optional ein Dateipfad und als Optionaler 2. Parameter ein Spaltentrenner übergeben werden. Sonst werden als default "examples\U2A2a.csv" als file und "," als Spaltentrenner verwendet. Zur Ausgabe werden Dateien mit demselben Namen und den Endungen .eps und .svg erstellt oder überschrieben.
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
Zu dieser Aufgabe gibt es eine gemeinsame Demo. In der Demo kann Optional ein Dateipfad und als Optionaler 2. Parameter ein Spaltentrenner übergeben werden. Sonst werden als default "examples\map03.csv" als file und "," als Spaltentrenner verwendet. Zur Ausgabe werden Dateien mit demselben Namen und den Endungen .obj und .svg erstellt oder überschrieben.

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

In der Klasse [ColorGenerator](src/com/wtv/converter/ColorGenerator.java)

##### `B)`
**Teilweise Implementiert**

Die Farben wurden direkt in den CSVReader integriert und sind nicht Optional

##### `C-F)` 
**Nicht Implementiert**

#### Übungsblatt 4
##### `A)`
**Implementiert**

In der Methode `getPathOfIsoline` der Klasse [Isolinien](src/com/wtv/processing/Isolinien.java)

##### `B)`

[//]: # (TODO: do stuff)