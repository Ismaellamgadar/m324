# CI-Plan

## Ziele der CI-Pipeline
1. **Automatisches Kompilieren des Codes:** Sicherstellen, dass der Code bei jedem definierten Ereignis erfolgreich kompiliert wird.
2. **Durchführen von Tests:**
   - Unit-Tests (bereits vorhanden).
   - Integrationstests (werden später hinzugefügt).
3. **Berichterstellung:** Erstellung eines Berichts über die Ergebnisse der Builds und Tests, abrufbar innerhalb jeder Pipeline-Ausführung.
4. **Kontinuierliche Prüfung der Codequalität:** Sicherstellen, dass Änderungen stabil und fehlerfrei sind, bevor sie auf `release`, `develop` oder `main` gemergt werden.

---

## Auslöser der CI-Pipeline
1. **Push-Ereignisse:**  
   Die Pipeline wird bei jedem Push auf Branches mit den Mustern `feature/*` oder `release/*` ausgeführt.
   
2. **Merge-Ereignisse:**  
   - Wenn Änderungen in `feature/*` in `develop` gemergt werden, wird die Pipeline ausgeführt.
   - Beim Merge von `release/*` in `main` oder `develop` wird die Pipeline ebenfalls ausgelöst, um sicherzustellen, dass keine fehlerhaften Änderungen übernommen werden.

---

## Pipeline-Schritte
Die Pipeline besteht aus folgenden Hauptschritten:

### 1. **Code-Checkout**
   - Sicherstellen, dass der neueste Code aus dem Repository heruntergeladen wird.

### 2. **Dependency Management**
   - Überprüfen und Herunterladen aller benötigten Abhängigkeiten mit Maven.

### 3. **Build-Prozess**
   - Kompilieren des Codes mit Maven, um sicherzustellen, dass der Code fehlerfrei kompiliert.

### 4. **Unit-Tests**
   - Ausführen der bestehenden Unit-Tests mit Maven Surefire Plugin.
   - Generieren eines Berichts über die Testergebnisse.

### 5. **Integrationstests** (zukünftig hinzufügen)
   - Führen Sie Integrationstests aus, sobald diese implementiert sind. Diese können die API gegen die H2-Datenbank testen.
   - Erfassen Sie die Testergebnisse und fügen Sie diese in den Bericht ein.

### 6. **Erstellung eines Berichts**
   - Sammeln von Informationen aus allen vorherigen Schritten (Build-Erfolg, Testergebnisse).
   - Generieren eines lesbaren Berichts, der die Ergebnisse zusammenfasst.

### 7. **Feedback an Entwickler**
   - Markieren der Pipeline als "Erfolgreich" oder "Fehlgeschlagen" basierend auf den Ergebnissen.
   - Bereitstellen der Berichte direkt in GitHub Actions.
