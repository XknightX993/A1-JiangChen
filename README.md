# A1-JiangChen: Health Appointment System

A Java console demonstration of a one-day appointment system for a health service. The program models professionals and appointments, then demonstrates adding, viewing, filtering, sorting, and cancelling bookings.

## Source files

- `HealthProfessional` is the abstract base class for shared ID, name, and working-day information.
- `GeneralPractitioner`, `Specialist`, `Nurse`, and `Surgeon` add type-specific information and behaviour.
- `Appointment` links a patient and a professional to a time.
- `AppointmentManager` stores appointments and applies the clinic schedule and booking rules.
- `AssignmentOne` creates sample objects and demonstrates normal and failed operations.

## Run

Open this folder in Visual Studio Code and run `src/AssignmentOne.java` with the Java extension. The demonstration uses Java's `LocalTime` for appointment times and the daily schedule of 08:00 to 16:00 in 30-minute intervals.

## Demonstrated object-oriented ideas

- Encapsulation: fields are private and constructors validate required data.
- Inheritance: each specific professional extends `HealthProfessional`.
- Abstraction: the generic professional type is abstract and requires a type label.
- Polymorphism: different professional objects are stored and displayed through a `HealthProfessional` array.
