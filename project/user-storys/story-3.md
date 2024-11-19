User Story 3: Ticketsystem-Integration (2nd-Level-Support)

Beschreibung:
Als 2nd-Level-Supporter möchte ich Tickets bearbeiten können, die an mich eskaliert wurden, um technische Probleme zu lösen und den Gesamtprozess zu verbessern.

Akzeptanzkriterien:

	•	Alle eskalierten Tickets sind im System sichtbar und können bearbeitet werden.
	•	Änderungen und Kommentare werden direkt im Ticket protokolliert und mit Commits verknüpft.
	•	Es gibt eine Möglichkeit, den Status auf “gelöst” zu setzen, sobald das Problem behoben ist.
	•	Kennzahlen wie durchschnittliche Bearbeitungszeit und Anzahl offener Tickets können extrahiert werden.

Aufgaben:

	1.	Implementieren eines Endpoints /tickets/escalated (GET), um alle eskalierten Tickets abzurufen.
	2.	Ergänzen der Möglichkeit, Statusänderungen und Kommentare zu protokollieren.
	3.	Sicherstellen, dass Commits mit der entsprechenden Ticket-ID verknüpft werden können.
	4.	Schreiben von mindestens 2-3 Unit Tests
