# Träningsapplikation – Grupparbete i teknisk projektledning

Detta projekt är en träningsapp utvecklad i grupp, där jag (Anni) har fokuserat på att ta fram innehållsmodellen för övningar och träningspass samt struktur för affärslogik i backend.

---

## Funktionalitet

- Modell för övningar (`Exercise`) och träningspass (`WorkoutPlan`)
- Lista med 14 hårdkodade övningar (muskelgrupp, utrustning, svårighetsgrad)
- Tre färdiga träningspass (helkropp, core, rygg/armar)
- REST-endpoints:
  - `GET /api/exercises` – lista alla övningar
  - `GET /api/exercises/by-muscle` – filtrera övningar på muskelgrupp
  - `GET /api/workouts` – visa färdiga träningspass
- Förberedda metoder för `addExercise()`, `updateExercise()`, `deleteExercise()` i service-lagret

---

## Teknikstack

- Java 21
- Spring Boot
- Maven
- Git & GitHub


---

## Min roll i projektet

Jag ansvarade för:

- Att bygga upp hela innehållsmodellen (övningar och träningspass)
- Strukturera koden med tydlig uppdelning mellan model, service och controller
- Implementera affärslogik, filterfunktioner och testdata
- Kommunicera med teamet för att anpassa koden till vidare integration (CRUD, auth och DB)
- Förbereda metoder som teamet kan koppla databaslogik till

---

## Starta applikationen

1. Klona repot
2. Navigera till mappen
3. Kör:
   ```bash
   mvn spring-boot:run

   Testa endpoints via Postman eller webbläsare:

http://localhost:8080/api/exercises

http://localhost:8080/api/exercises/by-muscle?muscleGroup=Ben

http://localhost:8080/api/workouts




Trots tekniska hinder och olika förutsättningar har vi haft ett bra samarbete och stöttat varandra där vi kunnat. 


