# **CD-Plan**

## **Ziele der CD-Pipeline**
1. **Automatische Bereitstellung von Anwendungen:** Sicherstellen, dass neue Änderungen fehlerfrei und ohne manuelle Eingriffe in die Zielumgebungen ausgeliefert werden.
2. **Umgebungsmanagement:** Bereitstellung in verschiedenen Umgebungen (z. B. `Staging`, `Production`) basierend auf definierten Regeln.
3. **Verifizierung nach der Bereitstellung:** Überprüfen, dass die Anwendung nach der Bereitstellung wie erwartet funktioniert.

---

## **Auslöser der CD-Pipeline**

1. **Merge in den `main` Branch:**  
   Automatische Bereitstellung in die **Produktionsumgebung**, wenn die Änderungen in `release/*` validiert wurden.
   
2. **Manuelle Trigger:**  
   Bereitstellungen können auch manuell ausgelöst werden, um spezifische Builds in einer bestimmten Umgebung zu testen.

---

## Mögliche Orte für Deployment
- https://www.heroku.com/java
- https://aws.amazon.com/de/
- https://www.docker.com/
