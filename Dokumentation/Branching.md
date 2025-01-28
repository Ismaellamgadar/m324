# **Branching Strategie und Semantic Versioning**

---

## **Branching Strategie**

### **Visuelle Übersicht**
<img width="765" alt="image" src="https://github.com/user-attachments/assets/3d2b3044-df84-40ac-8de6-3c406c4dd5b3">

---

### **Branch-Typen**

#### **1. Main Branch (`main`)**
- **Verwendungszweck:** Hier ist der Produktionscode. Es wird nie direkt auf dem main Branch gepusht, sonder nur aus dem release Branch gemerged.
- **Naming Convention:** `main`

#### **2. Release Branch (`release`)**
- **Verwendungszweck:** Fork von dem develop Branch, wenn der develop Branch genug features für einen Release hat. Das ist der Start eines Release Zyklus. Dann sollten keine neuen Features auf dem Branch gemerged werden. Nur Bugfixes dürfen dann noch drauf.
- **Achtung:** In diesem Projekt wurden keine Release Branches erstellt, da wir nicht zu einem ersten Release der Applikation gekommen sind.
- **Naming Convention:** `release`

#### **3. Develop Branch (`develop`)**
- **Verwendungszweck:** Hauptzweig für die Entwicklung. Hier werden alle neuen Features integriert. Dient als Integrationszweig für Features.
- **Naming Convention:** `develop`

#### **4. Feature Branches (`feature/*`)**
- **Verwendungszweck:** Entwicklung von neuen Funktionen oder Anpassungen. Jedes neue Feature befindet sich auf einen neuen Branch.
- **Naming Convention:** `feature/<ticket-id>-<feature-name>` (z. B. `feature/16/login-page`)

---

### **Merge-Wege**
- **Feature -> Develop:** Nach Abschluss eines Features wird der Code durch einen Pull Request in den `develop` Branch gemergt.
- **Develop -> Release:** Sobald Develop genug Features für einen Release hat würde der Code durch einen Fork in den `release` Branch dupliziert werden. Leider konnte dies nicht umgesetzt werden aufgrund von limitierter Zeit.
- **Release -> main:** Sobald im Release Branch grüne Tests hat und der Build funktioniert, kann er auf main installiert werden, sodass es auf Produktion angepasst wird.

### **Ablaufprozess: neues Feature**
```plaintext
1. Erstelle Branch: feature/<feature-name>
2. Entwickle das Feature.
3. Erstelle den Pull request und lass ihn reviewen.
  a. Falls notwendig, gehe auf Pull request comments ein.
5. Merge den Feature branch in den Develop branch. 
5. Lösche den Feature Branch.
```
### **Ablaufprozess: Neuer Release**
```plaintext
1. Forke den Release Branch aus dem develop
2. Kontrolliere ob alle Builds und tests grün sind.
3. Falls nötig, führe manuelle Tests durch.
4. Falls nötig, pushe bugfixes.
5. Merge den Release Branch auf main.
5. Falls du bugfixes hast, mach ein backmerge auf develop.
6. Lösche den Release Branch
```
---

## **Semantic Versioning**

### **Visuelle Übersicht**
```plaintext
Versionierung: MAJOR.MINOR.PATCH
Beispiel: 1.0.0
```

- **MAJOR:** Inkompatible Änderungen (z. B. komplett neuer Release).
- **MINOR:** Neue Features, die abwärtskompatibel sind.
- **PATCH:** Fehlerbehebungen.

---

### **Anwendungsbeispiele**

1. **Feature hinzugefügt (MINOR):**
   - Von `1.0.0` → `1.1.0`

2. **Bugfix (PATCH):**
   - Von `1.1.0` → `1.1.1`

---

### **Integration mit Branches**
1. Feature abgeschlossen: Erhöhe die **MINOR** Version.
2. Bugfix implementiert: Erhöhe die **PATCH** Version.
