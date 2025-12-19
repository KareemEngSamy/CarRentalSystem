# Car Rental System

- Console-based car rental application in Java with MySQL persistence. 
- Clean separation between domain models (Model) and operations/UI (Controller) using plain JDBC.

## Key Features
- Client: view cars, rent/return, view own rentals, edit profile, change password
- Admin: add/update/delete cars, view all rentals and user-specific rentals, add admins

## Repository Layout
```
src/
  Controller/         # Console flows and operations (entrypoint: Controller.Main)
  Model/              # Domain models, DB access, operations interface
schema.sql            # MySQL schema bootstrap
```

Generated artifacts like `bin/` are ignored via `.gitignore`.

## Requirements
- Java JDK 8+ (`java -version`)
- MySQL Server (local)
- MySQL Connector/J JAR in `lib/` (download from Oracle/MySQL site)

Default DB connection (edit in `src/Model/Database.java`):
- URL: `jdbc:mysql://localhost:3306/carrentalsystem`
- User: `root`
- Password: empty

## Database Setup
Run the schema to create the DB and tables:
```sql
SOURCE schema.sql;
```
Or copy statements from `schema.sql` into your SQL client.

## Build (manual)
Windows PowerShell:
```powershell
mkdir -Force bin | Out-Null
javac -d bin (Get-ChildItem src -Recurse -Filter *.java | ForEach-Object { $_.FullName })
```

## Run (manual)
Windows:
```powershell
$jar = (Get-ChildItem lib -Filter mysql-connector-*.jar | Select-Object -First 1).FullName
java -cp "bin;$jar" Controller.Main
```


## Notes
- IDs are computed via `COUNT(*)`; avoid manual row deletions to prevent duplicates.
- `Rent` stores date/time as a formatted string; `schema.sql` mirrors that expectation.
- For production-readiness, consider Maven/Gradle and auto-increment primary keys.
