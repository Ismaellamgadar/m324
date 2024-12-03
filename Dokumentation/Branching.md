# **Branching Strategie und Semantic Versioning**

---

## **Branching Strategie**

### **Visuelle Übersicht**
```plaintext
main <--------- (Produktionscode, stabil)
      \
       develop <---- (aktiver Entwicklungszweig)
            \
             feature/<feature-name> ---- (Feature Branches)
```

---

### **Branch-Typen**

#### **1. Develop Branch (`develop`)**
- **Verwendungszweck:** Hauptzweig für die Entwicklung. Hier werden alle neuen Features integriert.
- **Naming Convention:** `develop`

#### **2. Feature Branches (`feature/*`)**
- **Verwendungszweck:** Entwicklung von neuen Funktionen oder Anpassungen.
- **Naming Convention:** `feature/<ticket-id>-<feature-name>` (z. B. `feature/login-page`)

---

### **Merge-Wege**
- **Feature -> Develop:** Nach Abschluss eines Features wird der Code durch einen Pull Request in den `develop` Branch gemergt.

### **Ablaufprozess**
```plaintext
1. Erstelle Branch: feature/<feature-name>
2. Entwickle das Feature.
3. Erstelle den Pull request und lass ihn reviewen.
  a. Falls notwendig, gehe auf Pull request comments ein.
5. Merge den Feature branch in den Develop branch. 
5. Lösche den Feature Branch.
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
