/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hospitalpatientmanagementsystems;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;


public class HospitalPatientManagementSystems {
    
    private static ArrayList<Patient> patients = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in); 
    private static ArrayList<HospitalBed> beds = new ArrayList<>();

    public static void main(String[] args) {
        
        initializeBeds();
        
        int choice;
        
        do {
            
            System.out.println("\n========================================");
            System.out.println(" HOSPITAL PATIENT MANAGEMENT SYSTEM");
            System.out.println("========================================");
            System.out.println("1. Register Patient");
            System.out.println("2. Search Patient");
            System.out.println("3. Update Patient");
            System.out.println("4. Delete Patient");
            System.out.println("5. Allocate Bed");
            System.out.println("6. Release Bed");
            System.out.println("7. Display All Patients");
            System.out.println("8. Display Occupied Beds");
            System.out.println("9. Display Available Beds");
            System.out.println("10. Display Ward Layout");
            System.out.println("11. Sort Patients");
            System.out.println("12. Generate Patient Report");
            System.out.println("13. Generate Ward Report");
            System.out.println("14. Exit");
            System.out.println("========================================================");
            
            System.out.print("Enter your choice: ");
            
            try {
                
                choice = Integer.parseInt(scanner.nextLine());
                
            } catch (NumberFormatException e) {
                
                System.out.println("Invalid input. Please enter a number.");
                choice = 0;
            }
            
            switch (choice) {
                
                case 1: 
                    registerPatient();
                    break;
                    
                case 2:
                    searchPatient();
                    break;
                    
                case 3:
                    updatePatient();
                    break;
                    
                case 4:
                    deletePatient();
                    break;
                    
                case 5:
                    allocateBed();
                    break;
                    
                case 6:
                    releaseBed();
                    break;
                    
                case 7:
                    displayAllPatients();
                    break;
                    
                case 8:
                    displayOccupiedBeds();
                    break;
                    
                case 9:
                    displayAvailableBeds();
                    break;
                    
                case 10:
                    displayWardLayout();
                    break;
                    
                case 11:
                    sortingMenu();
                    break;
                    
                case 12:
                    generatePatientReport();
                    break;
                    
                case 13:
                    generateWardReport();
                    break;
                    
                case 14:
                    System.out.println("Thank you for using the Hospital Patient Management System.");
                    break;
                    
                default:
                    System.out.println("invalid choice. Please select an option from 1 to 14.");
            }
        } while (choice !=14);
    }
    
    public static void registerPatient() {
        
        System.out.println("\n===== REGISTER PATIENT =====");
        
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();
        
        for (Patient patient : patients){
            
            if (patient.getPatientId().equals(patientId)){
                System.out.println("Patient ID already exists. Registration cancelled.");
                return;
            }
        }
        
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        
        System.out.print("Enter Age: ");
        int age = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Enter Gender: ");
        String gender = scanner.nextLine();
        
        System.out.print("Enter Medical Condition: ");
        String medicalCondition = scanner.nextLine();
        
        String patientCategory;
        while (true){
            
            System.out.print("Enter Patient Category (Inpatient/Outpatient/Emergency): ");
            patientCategory = scanner.nextLine();
            
            if (patientCategory.equalsIgnoreCase("Inpatient")
                    || patientCategory.equalsIgnoreCase("Outpatient")
                    || patientCategory.equalsIgnoreCase("Emergency")){
                break;
            } else {
                System.out.println("Invalid category. Please enter Inpatient, Outpatient, or Emergency.");
            }
        }
        
        Patient patient = new Patient (
                patientId,
                firstName,
                lastName,
                age,
                gender,
                medicalCondition,
                patientCategory
        );
        patients.add(patient);
        
        System.out.println("Patient registered successfully!");
    }
    public static void displayAllPatients(){
        
        System.out.println("\n===== ALL REGISTERED PATIENTS  =====");
        
        if (patients.isEmpty()){
            System.out.println("No patients registered.");
            return;
        }
        for (Patient patient : patients){
            patient.displayDetails();
            System.out.println("----------------------------");
        }
    }
    public static void sortPatientsById(){
        
        System.out.println("\n===== PATIENTS SORTED BY PATIENT ID =====");
        
        if (patients.isEmpty()){
            System.out.println("No patients registered.");
            return;
        }
        
        patients.sort(Comparator.comparing(Patient::getPatientId));
        
        for (Patient patient : patients){
            patient.displayDetails();
            System.out.println("----------------------------");
        }
    }
    public static void sortPatientsByLastName(){
        
        System.out.print("\n===== PATIENTS SORTED BY LAST NAME =====");
        
        if (patients.isEmpty()){
            System.out.print("No patients registered.");
            return;
        }
        
        patients.sort(Comparator.comparing(Patient::getLastName));
        
        for (Patient patient : patients){
            patient.displayDetails();
            System.out.println("----------------------------");
        }
    }
            public static void sortingMenu(){
            
            while (true){
                
                System.out.println("\n========== SORT PATIENTS ==========");
                System.out.println("1. Sort by Patient ID");
                System.out.println("2. Sort by Last Name");
                System.out.println("3. Return to Main Menu");
                System.out.println("===================================");
                
                System.out.print("Enter your choice: ");
                String choice = scanner.nextLine();
                
                switch (choice){
                    
                    case "1":
                        sortPatientsById();
                        break;
                        
                    case "2":
                        sortPatientsByLastName();
                        break;
                        
                    case "3":
                        return;
                        
                    default:
                        System.out.println("invalid choice. Please select 1-3.");
                }
            }
            }
    public static void searchPatient(){
            System.out.println("\n===== SEARCH PATIENT =====");
            
            System.out.print("Enter Patient ID: ");
            String patientId = scanner.nextLine();
            
            for (Patient patient : patients){
                
                if (patient.getPatientId().equals(patientId)){
                    
                    System.out.println("\nPatient found!");
                    patient.displayDetails();
                    return;
                }
                    
            }
            System.out.println("Patient not found.");
        }
    public static void updatePatient(){
        
        System.out.print("\n===== UPDATE PATIENT =====");
        
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();
        
        for (Patient patient : patients){
            
            if (patient.getPatientId().equals(patientId)){
                
                System.out.println("Patient found.");
                System.out.println("Enter the new patient details.");
                
                System.out.print("Enter First Name: ");
                patient.setFirstName(scanner.nextLine());
                
                System.out.print("Enter Last Name: ");
                patient.setLastName(scanner.nextLine());
                
                System.out.print("Enter Age: ");
                patient.setAge(Integer.parseInt(scanner.nextLine()));
                
                System.out.print("Enter Gender: ");
                patient.setGender(scanner.nextLine());
                
                System.out.print("Enter Medical Condition: ");
                patient.setMedicalCondition(scanner.nextLine());
                
                System.out.print("Enter Patient Category (Inpatient/Outpatient/Emergency): ");
                patient.setPatientCategory(scanner.nextLine());
                
                System.out.println("Patient details updated succesfully.");
                return;
            }
        }
        System.out.println("Patient not found.");
    }
    public static void deletePatient() {

            System.out.println("\n===== DELETE PATIENT =====");

            System.out.print("Enter Patient ID: ");
            String patientId = scanner.nextLine();

        for (int i = 0; i < patients.size(); i++) {

        Patient patient = patients.get(i);

        if (patient.getPatientId().equals(patientId)) {

            patients.remove(i);

            System.out.println("Patient deleted successfully.");
            return;
        }
    }

    System.out.println("Patient not found.");
}
    public static void initializeBeds(){
        
        for (int i = 1; i <= 20; i++){
            
            String bedNumber = String.format("%03d", i);
            
            HospitalBed bed = new HospitalBed ("Ward 1", bedNumber);
            
            beds.add(bed);
        }
     
    }
    public static void allocateBed() {
        
        System.out.print("\n===== ALLOCATE BED =====");
        
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();
        
        Patient selectedPatient = null;
        
        // Find the patient 
        for (Patient patient : patients) {
            
            if (patient.getPatientId().equals(patientId)){
                selectedPatient = patient;
                break;
            }
        }
        
        // Check if patient exists
        if (selectedPatient == null) {
            System.out.println("Patient not found.");
            return;
        }
        
        // Only inpatient can have a bed
        if (!selectedPatient.getPatientCategory().equalsIgnoreCase("inpatient")){
            System.out.println("Only inpatient can be allocated a hospital bed.");
            return;
        }
        
        // Check if patient already has a bed
        
       for (HospitalBed bed : beds) {
           if (bed.isOccupied()
                   && bed.getPatientId().equals(selectedPatient.getPatientId())){
               System.out.println("This patient already has a bed.");
               return;
  
           }
       }
       
       // Find an available bed
       for (HospitalBed bed : beds) {
           
           if (!bed.isOccupied()){
               bed.setPatientId(selectedPatient.getPatientId());
               bed.setFirstName(selectedPatient.getFirstName());
               bed.setLastName(selectedPatient.getLastName());
               bed.setAge(selectedPatient.getAge());
               bed.setGender(selectedPatient.getGender());
               bed.setMedicalCondition(selectedPatient.getMedicalCondition());
               bed.setPatientCategory(selectedPatient.getPatientCategory());
               
               bed.setOccupied(true);
               
               System.out.println("Bed allocated successfully!");
               System.out.println("Ward: " + bed.getWardNumber());
               System.out.println("Bed: " + bed.getBedNumber());
               
               return;
               
               
           }
           
       }
       
       System.out.println("No beds are available.");
    }
    public static void releaseBed() {
        
        System.out.print("\n===== RELEASE BED =====");
        
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();
        
        for (HospitalBed bed : beds){
            
            if (bed.isOccupied()
                    && bed.getPatientId().equals(patientId)){
                
                bed.setPatientId("");
                bed.setFirstName("");
                bed.setLastName("");
                bed.setAge(0);
                bed.setGender("");
                bed.setMedicalCondition("");
                bed.setPatientCategory("");
                
                bed.setOccupied(false);
                
                System.out.println("Bed " + bed.getBedNumber()
                        + " has been released.");
                return;
            }
        }
        System.out.println("No occupied bed found for this patient.");
    }
    public static void displayAvailableBeds(){
        
        System.out.println("\n===== AVAILABLE BEDS =====");
        
        boolean found = false;
        
        for (HospitalBed bed : beds){
            
            if(!bed.isOccupied()){
                
                System.out.println("Ward: " + bed.getWardNumber()
                        + " | Bed: " + bed.getBedNumber());
                
                found = true;
            }
        }
        if (!found){
            System.out.println("No beds are available.");
        }
    }
    public static void displayOccupiedBeds(){
        
        System.out.println("\n===== OCCUPIED BEDS =====");
        
        boolean found = false;
        
        for (HospitalBed bed : beds) {
            
            if (bed.isOccupied()){
                
                System.out.println("Ward: " + bed.getWardNumber()
                        + " | Bed: " + bed.getBedNumber()
                        + " | Patient ID: " + bed.getPatientId()
                        + " | Patient: " + bed.getFirstName()
                        + " " + bed.getLastName());
                
                found = true;
            }
        }
        if (!found) {
            System.out.println("No beds are currently occupied");
        }
    }
    public static void displayWardLayout(){
        
        System.out.println("\n========== WARD 1 LAYOUT ==========");
        
        for (int i = 0; i < beds.size(); i++){
            
            HospitalBed bed = beds.get(i);
            
            if (bed.isOccupied()){
                
                System.out.print("[" + bed.getBedNumber()
                        + " - OCCUPIED] ");
            } else {
                System.out.print("[" + bed.getBedNumber()
                        + " - AVAILABLE] ");
            }
            
            if ((i + 1) % 5 == 0) {
                System.out.println();
            }
        }
        System.out.println("===================================");
    }
    public static void generatePatientReport() {

        System.out.println("\n========== PATIENT REPORT ==========");

    if (patients.isEmpty()) {
        System.out.println("No patients are currently registered.");
        System.out.println("====================================");
        return;
    }

        System.out.println("Total Registered Patients: " + patients.size());
        System.out.println("====================================");

    int patientNumber = 1;

    for (Patient patient : patients) {

        System.out.println("\nPatient " + patientNumber);
        System.out.println("------------------------------------");
        System.out.println("Patient ID: " + patient.getPatientId());
        System.out.println("First Name: " + patient.getFirstName());
        System.out.println("Last Name: " + patient.getLastName());
        System.out.println("Age: " + patient.getAge());
        System.out.println("Gender: " + patient.getGender());
        System.out.println("Medical Condition: " + patient.getMedicalCondition());
        System.out.println("Patient Category: " + patient.getPatientCategory());

        patientNumber++;
    }

        System.out.println("\n====================================");
        System.out.println("End of Patient Report");
        System.out.println("====================================");
}
    public static void generateWardReport(){
        
         int totalPatients = patients.size();
         int occupiedBeds = 0;
         int totalBeds = beds.size();
         
         for (HospitalBed bed : beds) {
             
             if (bed.isOccupied()){
                 occupiedBeds++;
             }
         }
         int availableBeds = totalBeds - occupiedBeds;
         
         double occupancyPercentage =
                 ((double) occupiedBeds/ totalBeds) * 100;
         
         System.out.println("\n========== WARD REPORT ==========");
         System.out.println("Ward Number: Ward 1");
         System.out.println("Total Registered Patients: " + totalPatients);
         System.out.println("Total Beds: " + totalBeds);
         System.out.println("Occupied Beds: " + occupiedBeds);
         System.out.println("Available Beds: " + availableBeds);
         System.out.printf("Ward Occupancy: %.2f%%%n", occupancyPercentage);
         System.out.println("================================");
    }
    public static void setScannerForTesting(Scanner testScanner){
        scanner = testScanner;
    }

    }
   
    

