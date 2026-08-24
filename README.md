# HospitalPatientManagementSystem
# Hospital Patient Management System

## PROG6112 Practical Assignment 1

A console-based Java application developed to manage patient admissions, hospital beds, patient records, and ward occupancy.

## Project Overview

The Hospital Patient Management System allows hospital staff to:

* Register patients
* Search for patient records
* Update patient information
* Delete patient records
* Allocate hospital beds
* Release hospital beds
* Display occupied beds
* Display available beds
* Display the ward layout
* Sort patients
* Generate ward reports
* Validate patient and bed information

The system contains one hospital ward with 20 beds.

## Features

### 1. Patient Management

The system supports complete patient record management:

* Patient registration
* Duplicate Patient ID prevention
* Patient searching
* Patient information updates
* Patient deletion
* Displaying all registered patients

Each patient contains:

* Patient ID
* First Name
* Last Name
* Age
* Gender
* Medical Condition
* Patient Category

Patient categories are:

* Inpatient
* Outpatient
* Emergency

### 2. Bed Management

The system manages 20 beds in Ward 1.

Bed management includes:

* Bed allocation
* Bed release
* Occupied bed display
* Available bed display
* Ward layout display
* Prevention of duplicate bed allocation
* Prevention of allocating beds to non-inpatients
* Prevention of allocating more than one bed to the same patient

### 3. Reports and Data Processing

The system provides:

* Patient information reporting
* Ward occupancy reporting
* Total registered patient count
* Total bed count
* Occupied bed count
* Available bed count
* Ward occupancy percentage
* Patient sorting by Patient ID
* Patient sorting by Last Name

## Object-Oriented Programming

The project demonstrates core Object-Oriented Programming principles.

### Patient Class

The `Patient` class stores patient information and provides:

* Constructors
* Getters
* Setters
* `displayDetails()` method

### Encapsulation

Patient and bed attributes are declared as `private` and accessed through appropriate getters and setters.

### Inheritance

The `HospitalBed` class extends the `Patient` class:

```java
public class HospitalBed extends Patient
```

### Constructor Chaining

The `HospitalBed` constructor uses:

```java
super(...)
```

to initialise inherited attributes from the `Patient` class.

### Method Overriding

`HospitalBed` overrides the `displayDetails()` method:

```java
@Override
public void displayDetails()
```

The overridden method displays additional ward and bed information.

## Validation

The application includes validation for:

* Duplicate Patient IDs
* Invalid patient categories
* Patients who do not exist
* Non-inpatient bed allocation
* Duplicate bed allocation
* Patients already occupying a bed
* Occupied bed release
* No available beds
* Invalid menu selections

## Testing

The project includes unit testing for:

### CRUD Operations

* Patient registration
* Patient searching
* Patient updating
* Patient deletion

### Bed Management

* Bed allocation
* Bed release
* Occupied bed validation
* Available bed validation
* Duplicate bed allocation

### Validation and Boundary Conditions

* Duplicate Patient IDs
* Invalid patient information
* Occupied beds
* Invalid ward positions
* Sorting functionality
* Bed capacity limits

## Technologies Used

* Java
* NetBeans
* JUnit
* Object-Oriented Programming
* ArrayList
* Comparator

## Project Structure

```text
HospitalPatientManagementSystems/
│
├── src/
│   └── hospitalpatientmanagementsystems/
│       ├── HospitalPatientManagementSystems.java
│       ├── Patient.java
│       └── HospitalBed.java
│
├── test/
│   └── JUnit test classes
│
├── nbproject/
├── build.xml
└── manifest.mf
```

## How to Run

1. Open the project in NetBeans.
2. Build the project.
3. Run `HospitalPatientManagementSystems.java`.
4. Use the console menu to select the required operation.
5. Follow the prompts displayed by the application.

## Example Main Menu

```text
========================================================
 HOSPITAL PATIENT MANAGEMENT SYSTEM
========================================================
1. Register Patient
2. Search Patient
3. Update Patient
4. Delete Patient
5. Allocate Bed
6. Release Bed
7. Display All Patients
8. Display Occupied Beds
9. Display Available Beds
10. Display Ward Layout
11. Sort Patients
12. Generate Ward Report
13. Exit
========================================================
```

## Assessment Alignment

The project is designed around the five assessment features:

| Feature                     |   Marks |
| --------------------------- | ------: |
| Patient Management          |      20 |
| Bed Management              |      20 |
| Reports                     |      15 |
| Object-Oriented Programming |      30 |
| Unit Testing                |      15 |
| **Total**                   | **100** |

## Author

PROG6112 Practical Assignment 1
Hospital Patient Management System
