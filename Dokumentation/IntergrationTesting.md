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