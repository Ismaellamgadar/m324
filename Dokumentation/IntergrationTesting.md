# Microservices
## Gründe für die Verwendung von "@AutoConfigureMockRestServiceServer" als Integrationstest-Methode

In unserem Projekt haben wir uns entschieden, die Annotation "@AutoConfigureMockRestServiceServer" zu verwenden, um die Integrationstests zwischen unseren beiden Microservices effizient und effektiv zu testen. Diese Entscheidung basiert auf den folgenden Hauptgründen:

1. **Einfache Konfiguration und Integration:**  
   Die Annotation "@AutoConfigureMockRestServiceServer" macht es uns leicht, den MockRestServiceServer in Spring Boot Tests zu verwenden. Dadurch sparen wir uns die aufwendige manuelle Einrichtung und können schneller mit den Tests beginnen.   


2. **Isolierung von REST-Aufrufen:**  
   Mit dieser Methode können wir externe REST-Schnittstellen vollständig nachstellen. Dadurch sind unsere Tests unabhängig von der Verfügbarkeit oder Stabilität anderer Systeme, was zuverlässigere Ergebnisse liefert.


3. **Gute Zusammenarbeit mit Spring-Tools:**
   Die Annotation funktioniert gut mit anderen Spring-Test-Werkzeugen wie @RestClientTest oder MockMvc. So können wir unsere Tests in einer vertrauten Umgebung umsetzen.

Insgesamt gibt uns "@AutoConfigureMockRestServiceServer" eine einfache und flexible Möglichkeit, die Integrationstests zwischen unseren beiden Microservices durchzuführen. Es hilft uns, die Qualität unseres Codes zu verbessern, ohne viel zusätzliche Zeit zu investieren.

----

# Database

### Dokumentation: Integrationstests für TicketService

#### **Ziele der Integrationstests**
Die Integrationstests für den `TicketService` haben das Ziel, die Interaktion zwischen den verschiedenen Komponenten des Systems (Datenbank, Services und externe REST-Schnittstellen) zu testen und sicherzustellen, dass die Funktionalität des Services in einer realistischen Umgebung korrekt funktioniert. Hierbei werden sowohl die Datenbank (H2) als auch die externe Kommunikation zu einem User-Service getestet.

---

#### **Gründe für die Verwendung von `@SpringBootTest` und `@AutoConfigureMockRestServiceServer`**

In unserem Projekt setzen wir `@SpringBootTest` und `@AutoConfigureMockRestServiceServer` ein, um Integrationstests effizient und zuverlässig durchzuführen. Die Entscheidung für diese Ansätze basiert auf den folgenden Aspekten:

---

### **1. Verwendung von `@SpringBootTest`**
- **Realistische Testumgebung**: Mit `@SpringBootTest` können wir die gesamte Spring-Anwendung starten und alle Beans sowie Konfigurationen laden. Dadurch testen wir den Service in einer Umgebung, die der Produktionsumgebung nahekommt.
- **Integration der H2-Datenbank**: Die H2-Datenbank wird als In-Memory-Datenbank verwendet, um die Integration mit der Datenbank zu testen, ohne die Produktivdatenbank zu beeinflussen.
- **Datenbankbereinigung**: Durch die Verwendung von `ticketRepository.deleteAll()` wird die Datenbank vor jedem Test zurückgesetzt, wodurch sichergestellt wird, dass die Tests unabhängig voneinander bleiben.

---

### **2. Verwendung von `@AutoConfigureMockRestServiceServer`**
- **Mocken externer REST-Aufrufe**: Mit `@AutoConfigureMockRestServiceServer` können wir REST-Aufrufe an externe Systeme (z. B. User-Service) simulieren. Dadurch wird sichergestellt, dass die Tests unabhängig von der Verfügbarkeit des User-Services sind.
- **Hohe Zuverlässigkeit**: Da externe Abhängigkeiten durch Mocking isoliert werden, laufen die Tests stabiler und liefern zuverlässigere Ergebnisse.
- **Exakte Simulation von HTTP-Antworten**: Mit `MockRestServiceServer` können wir verschiedene HTTP-Statuscodes und Antworten simulieren (z. B. `200 OK`, `404 NOT FOUND`). Dies erlaubt uns, verschiedene Szenarien wie "Benutzer existiert" oder "Benutzer existiert nicht" realitätsnah nachzustellen.
- **Vertraute Integration in Spring**: `@AutoConfigureMockRestServiceServer` ist nahtlos in das Spring-Ökosystem integriert und ermöglicht eine schnelle Einrichtung ohne zusätzliche Konfigurationen.

---

#### **Vorgehensweise für die Integrationstests**
1. **Planung**: 
   - Definition der zu testenden Funktionalitäten, z. B.:
     - Erstellung eines Tickets (`createTicket`)
     - Abfrage aller Tickets (`getAllTickets`)
     - Aktualisierung eines Tickets (`updateTicket`)
     - Interaktion mit externem User-Service
   - Sicherstellung, dass Tests unabhängig voneinander ausgeführt werden können.

2. **Umsetzung**:
   - Einsatz von `@SpringBootTest` zur Simulation der Spring-Boot-Anwendung.
   - Verwendung der H2-In-Memory-Datenbank für alle Datenbankoperationen.
   - Einsatz von `@AutoConfigureMockRestServiceServer` und `MockRestServiceServer` für REST-Mocking.

3. **Wiederholbarkeit**:
   - Durch die Verwendung von `ticketRepository.deleteAll()` wird sichergestellt, dass jeder Test mit einer leeren Datenbank startet.
   - Mocking von externen REST-Aufrufen stellt sicher, dass Tests nicht von externen Diensten abhängig sind.

---

#### **Zusammenfassung**
Die Kombination von `@SpringBootTest` und `@AutoConfigureMockRestServiceServer` bietet eine effiziente und zuverlässige Möglichkeit, die Integrationstests für den `TicketService` zu implementieren. Die H2-Datenbank simuliert die Datenbankintegration, während MockRestServiceServer eine präzise Nachbildung der REST-Schnittstellen ermöglicht.
