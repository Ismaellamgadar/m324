# **CD-Plan**

## **Ziele der CD-Pipeline**
1. **Automatische Bereitstellung von Anwendungen:** Sicherstellen, dass neue Änderungen fehlerfrei und ohne manuelle Eingriffe in die Zielumgebungen ausgeliefert werden.
2. **Verifizierung während der Bereitstellung:** Überprüfen, dass Tests usw. wie erwartet funktionieren.
3. Optional: Implementieren von feature toggles.
4. Optional: Implementieren von Canary Deployment

---

## **Auslöser der CD-Pipeline**

1. **Merge in den `main` Branch:**  
   Automatische Bereitstellung in die **Produktionsumgebung**, wenn die Änderungen in `release/*` validiert wurden.
   
2. **Manuelle Trigger:**  
   Bereitstellungen können auch manuell ausgelöst werden, um spezifische Builds in einer bestimmten Umgebung zu testen.

---

## **Delivery auf Docker-Hub**
<img width="1512" alt="image" src="https://github.com/user-attachments/assets/d9008673-362f-48b0-9d27-51404b685192" />

---

## Mögliche Orte für Deployment
- https://www.heroku.com/java
- https://aws.amazon.com/de/
- https://www.docker.com/
