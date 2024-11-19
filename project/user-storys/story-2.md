User Story 2: Ticket verwalten (1st-Level-Support)

Beschreibung:
Als 1st-Level-Supporter möchte ich ein bestehendes Ticket einsehen und bearbeiten können, um Kundenprobleme zu lösen.

Akzeptanzkriterien:

	•	Ein Ticket kann aktualisiert werden (z. B. Status ändern, Kommentare hinzufügen).
	•	Der Supporter kann Tickets eskalieren, indem sie an den 2nd-Level-Support übergeben werden.
	•	Beim Eskalieren wird der Status entsprechend aktualisiert (z. B. “in Bearbeitung” → “eskaliert”).
	•	Kunden erhalten eine Benachrichtigung, wenn ihr Ticket eskaliert wurde (z. B. per E-Mail oder Status-Update).

Aufgaben:

	1.	Implementieren eines Endpoints /tickets/{id} , um Tickets zu aktualisieren.
	2.	Validierung der Aktualisierungen (z. B. nur erlaubte Status-Übergänge).
	3.	Implementieren einer Funktion, um ein Ticket an den 2nd-Level-Support zu eskalieren.
	4.	Schreiben von mindestens 2-3 Unit Tests
