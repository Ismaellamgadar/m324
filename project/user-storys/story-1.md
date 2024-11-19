User Story 1: Ticket erfassen (Kunde)

Beschreibung:
Als Kunde möchte ich ein neues Ticket erfassen können, um ein Problem oder eine Anfrage an den 1st-Level-Support zu melden.

Akzeptanzkriterien:

	•	Ein Ticket muss die folgenden Attribute haben: Titel, Beschreibung, Priorität (niedrig, mittel, hoch), Status (offen, in Bearbeitung, abgeschlossen), Erstellungsdatum und Kundendaten (z. B. Name, E-Mail).
	•	Nach dem Erfassen erhält der Kunde eine Ticket-ID zur Nachverfolgung.
	•	Wenn erforderliche Informationen fehlen, wird eine Fehlermeldung zurückgegeben.

Aufgaben:

	1.	Implementieren eines Endpoints /tickets (POST), um ein Ticket zu erstellen.
	2.	Validierung der Eingabedaten (z. B. kein leeres Titel- oder Beschreibungsfeld).
	3.	Speicherung des Tickets in der Datenbank und Verknüpfung mit der Ticket-ID.
	4.	Schreiben von mindestens 2-3 Unit Tests
