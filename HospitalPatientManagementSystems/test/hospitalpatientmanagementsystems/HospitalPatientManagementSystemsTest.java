/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

package hospitalpatientmanagementsystems;

import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Scanner;


/**
 * JUnit tests for the Hospital Patient Management System.
 *
 * Tests:
 * 1. Patient registration
 * 2. Duplicate Patient ID validation
 * 3. Patient search
 * 4. Patient update
 * 5. Patient deletion
 * 6. Patient sorting by ID
 * 7. Patient sorting by Last Name
 * 8. Bed allocation
 * 9. Duplicate bed allocation
 * 10. Bed release
 * 11. Available bed validation
 * 12. Occupied bed validation
 */
public class HospitalPatientManagementSystemsTest {

    private final PrintStream originalOut = System.out;
    private final java.io.InputStream originalIn = System.in;

    private ByteArrayOutputStream output;

    @Before
    public void setUp() throws Exception {

        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        clearPatients();
        clearBeds();
        HospitalPatientManagementSystems.initializeBeds();
    }

    @After
    public void tearDown() throws Exception {

        System.setOut(originalOut);
        System.setIn(originalIn);

        clearPatients();
        clearBeds();
    }

    /*
     * ============================================================
     * HELPER METHODS
     * ============================================================
     */

    @SuppressWarnings("unchecked")
    private ArrayList<Patient> getPatients() throws Exception {

        Field field =
                HospitalPatientManagementSystems.class
                        .getDeclaredField("patients");

        field.setAccessible(true);

        return (ArrayList<Patient>) field.get(null);
    }

    @SuppressWarnings("unchecked")
    private ArrayList<HospitalBed> getBeds() throws Exception {

        Field field =
                HospitalPatientManagementSystems.class
                        .getDeclaredField("beds");

        field.setAccessible(true);

        return (ArrayList<HospitalBed>) field.get(null);
    }

    private void clearPatients() throws Exception {
        getPatients().clear();
    }

    private void clearBeds() throws Exception {
        getBeds().clear();
    }

    private void provideInput(String input) {

        HospitalPatientManagementSystems.setScannerForTesting(
                new Scanner(
                        new ByteArrayInputStream(input.getBytes())
                )
        );
        
    }

    private String getOutput() {
        return output.toString();
    }

    /*
     * ============================================================
     * FEATURE 5.1 - CRUD TESTS
     * ============================================================
     */

    @Test
    public void testRegisterPatient() throws Exception {

        provideInput(
                "P001\n"
                + "John\n"
                + "Smith\n"
                + "25\n"
                + "Male\n"
                + "Broken Arm\n"
                + "Inpatient\n"
        );

        HospitalPatientManagementSystems.registerPatient();

        ArrayList<Patient> patients = getPatients();

        assertEquals(1, patients.size());
        assertEquals("P001", patients.get(0).getPatientId());
        assertEquals("John", patients.get(0).getFirstName());
        assertEquals("Smith", patients.get(0).getLastName());

        assertTrue(
                getOutput().contains("Patient registered successfully!")
        );
    }

    @Test
    public void testDuplicatePatientId() throws Exception {

        ArrayList<Patient> patients = getPatients();

        patients.add(
                new Patient(
                        "P001",
                        "John",
                        "Smith",
                        25,
                        "Male",
                        "Broken Arm",
                        "Inpatient"
                )
        );

        provideInput("P001\n");

        HospitalPatientManagementSystems.registerPatient();

        assertEquals(1, patients.size());

        assertTrue(
                getOutput().contains("Patient ID already exists")
        );
    }

    @Test
    public void testSearchPatient() throws Exception {

        ArrayList<Patient> patients = getPatients();

        patients.add(
                new Patient(
                        "P001",
                        "John",
                        "Smith",
                        25,
                        "Male",
                        "Broken Arm",
                        "Inpatient"
                )
        );

        provideInput("P001\n");

        HospitalPatientManagementSystems.searchPatient();

        String result = getOutput();

        assertTrue(result.contains("Patient found!"));
        assertTrue(result.contains("P001"));
        assertTrue(result.contains("John"));
        assertTrue(result.contains("Smith"));
    }

    @Test
    public void testSearchPatientNotFound() throws Exception {

        provideInput("P999\n");

        HospitalPatientManagementSystems.searchPatient();

        assertTrue(
                getOutput().contains("Patient not found.")
        );
    }

    @Test
    public void testUpdatePatient() throws Exception {

        ArrayList<Patient> patients = getPatients();

        patients.add(
                new Patient(
                        "P001",
                        "John",
                        "Smith",
                        25,
                        "Male",
                        "Broken Arm",
                        "Inpatient"
                )
        );

        provideInput(
                "P001\n"
                + "Michael\n"
                + "Brown\n"
                + "30\n"
                + "Male\n"
                + "Flu\n"
                + "Outpatient\n"
        );

        HospitalPatientManagementSystems.updatePatient();

        Patient patient = patients.get(0);

        assertEquals("Michael", patient.getFirstName());
        assertEquals("Brown", patient.getLastName());
        assertEquals(30, patient.getAge());
        assertEquals("Flu", patient.getMedicalCondition());
        assertEquals("Outpatient", patient.getPatientCategory());

        assertTrue(
                getOutput().contains("Patient details updated")
        );
    }

    @Test
    public void testDeletePatient() throws Exception {

        ArrayList<Patient> patients = getPatients();

        patients.add(
                new Patient(
                        "P001",
                        "John",
                        "Smith",
                        25,
                        "Male",
                        "Broken Arm",
                        "Inpatient"
                )
        );

        provideInput("P001\n");

        HospitalPatientManagementSystems.deletePatient();

        assertTrue(patients.isEmpty());

        assertTrue(
                getOutput().contains("Patient deleted successfully.")
        );
    }

    /*
     * ============================================================
     * FEATURE 5.2 - BED MANAGEMENT TESTS
     * ============================================================
     */

    @Test
    public void testInitializeBeds() throws Exception {

        ArrayList<HospitalBed> beds = getBeds();

        assertEquals(20, beds.size());

        assertEquals("001", beds.get(0).getBedNumber());
        assertEquals("020", beds.get(19).getBedNumber());

        assertFalse(beds.get(0).isOccupied());
    }

    @Test
    public void testAllocateBed() throws Exception {

        ArrayList<Patient> patients = getPatients();
        ArrayList<HospitalBed> beds = getBeds();

        patients.add(
                new Patient(
                        "P001",
                        "John",
                        "Smith",
                        25,
                        "Male",
                        "Broken Arm",
                        "Inpatient"
                )
        );

        provideInput("P001\n");

        HospitalPatientManagementSystems.allocateBed();

        HospitalBed bed = beds.get(0);

        assertTrue(bed.isOccupied());
        assertEquals("P001", bed.getPatientId());
        assertEquals("John", bed.getFirstName());
        assertEquals("Smith", bed.getLastName());

        assertTrue(
                getOutput().contains("Bed allocated successfully!")
        );
    }

    @Test
    public void testDuplicateBedAllocation() throws Exception {

        ArrayList<Patient> patients = getPatients();
        ArrayList<HospitalBed> beds = getBeds();

        patients.add(
                new Patient(
                        "P001",
                        "John",
                        "Smith",
                        25,
                        "Male",
                        "Broken Arm",
                        "Inpatient"
                )
        );

        beds.get(0).setPatientId("P001");
        beds.get(0).setFirstName("John");
        beds.get(0).setLastName("Smith");
        beds.get(0).setOccupied(true);

        provideInput("P001\n");

        HospitalPatientManagementSystems.allocateBed();

        assertTrue(
                getOutput().contains("This patient already has a bed.")
        );
    }

    @Test
    public void testNonInpatientCannotReceiveBed() throws Exception {

        ArrayList<Patient> patients = getPatients();

        patients.add(
                new Patient(
                        "P001",
                        "Sarah",
                        "Jones",
                        30,
                        "Female",
                        "Flu",
                        "Outpatient"
                )
        );

        provideInput("P001\n");

        HospitalPatientManagementSystems.allocateBed();

        assertTrue(
                getOutput().contains(
                        "Only inpatient can be allocated a hospital bed."
                )
        );
    }

    @Test
    public void testReleaseBed() throws Exception {

        ArrayList<Patient> patients = getPatients();
        ArrayList<HospitalBed> beds = getBeds();

        patients.add(
                new Patient(
                        "P001",
                        "John",
                        "Smith",
                        25,
                        "Male",
                        "Broken Arm",
                        "Inpatient"
                )
        );

        beds.get(0).setPatientId("P001");
        beds.get(0).setFirstName("John");
        beds.get(0).setLastName("Smith");
        beds.get(0).setOccupied(true);

        provideInput("P001\n");

        HospitalPatientManagementSystems.releaseBed();

        assertFalse(beds.get(0).isOccupied());
        assertEquals("", beds.get(0).getPatientId());

        assertTrue(
                getOutput().contains("has been released.")
        );
    }

    @Test
    public void testReleaseOccupiedBedMakesItAvailable() throws Exception {

        ArrayList<Patient> patients = getPatients();
        ArrayList<HospitalBed> beds = getBeds();

        patients.add(
                new Patient(
                        "P001",
                        "John",
                        "Smith",
                        25,
                        "Male",
                        "Broken Arm",
                        "Inpatient"
                )
        );

        beds.get(0).setPatientId("P001");
        beds.get(0).setOccupied(true);

        provideInput("P001\n");

        HospitalPatientManagementSystems.releaseBed();

        assertFalse(
                "Bed should be available after release",
                beds.get(0).isOccupied()
        );
    }

    /*
     * ============================================================
     * FEATURE 5.3 - SORTING TESTS
     * ============================================================
     */

    @Test
    public void testSortPatientsById() throws Exception {

        ArrayList<Patient> patients = getPatients();

        patients.add(
                new Patient(
                        "P003",
                        "John",
                        "Smith",
                        25,
                        "Male",
                        "Condition",
                        "Inpatient"
                )
        );

        patients.add(
                new Patient(
                        "P001",
                        "Sarah",
                        "Adams",
                        30,
                        "Female",
                        "Condition",
                        "Outpatient"
                )
        );

        patients.add(
                new Patient(
                        "P002",
                        "David",
                        "Brown",
                        40,
                        "Male",
                        "Condition",
                        "Emergency"
                )
        );

        HospitalPatientManagementSystems.sortPatientsById();

        assertEquals("P001", patients.get(0).getPatientId());
        assertEquals("P002", patients.get(1).getPatientId());
        assertEquals("P003", patients.get(2).getPatientId());
    }

    @Test
    public void testSortPatientsByLastName() throws Exception {

        ArrayList<Patient> patients = getPatients();

        patients.add(
                new Patient(
                        "P003",
                        "John",
                        "Smith",
                        25,
                        "Male",
                        "Condition",
                        "Inpatient"
                )
        );

        patients.add(
                new Patient(
                        "P001",
                        "Sarah",
                        "Adams",
                        30,
                        "Female",
                        "Condition",
                        "Outpatient"
                )
        );

        patients.add(
                new Patient(
                        "P002",
                        "David",
                        "Brown",
                        40,
                        "Male",
                        "Condition",
                        "Emergency"
                )
        );

        HospitalPatientManagementSystems.sortPatientsByLastName();

        assertEquals("Adams", patients.get(0).getLastName());
        assertEquals("Brown", patients.get(1).getLastName());
        assertEquals("Smith", patients.get(2).getLastName());
    }

    /*
     * ============================================================
     * DISPLAY / OCCUPANCY VALIDATION
     * ============================================================
     */

    @Test
    public void testDisplayOccupiedBeds() throws Exception {

        ArrayList<HospitalBed> beds = getBeds();

        beds.get(0).setPatientId("P001");
        beds.get(0).setFirstName("John");
        beds.get(0).setLastName("Smith");
        beds.get(0).setOccupied(true);

        HospitalPatientManagementSystems.displayOccupiedBeds();

        String result = getOutput();

        assertTrue(result.contains("001"));
        assertTrue(result.contains("P001"));
        assertTrue(result.contains("John Smith"));
    }

    @Test
    public void testDisplayAvailableBeds() throws Exception {

        HospitalPatientManagementSystems.displayAvailableBeds();

        String result = getOutput();

        assertTrue(result.contains("001"));
        assertTrue(result.contains("020"));
    }

    @Test
    public void testDisplayAvailableBedsAfterRelease() throws Exception {

        ArrayList<HospitalBed> beds = getBeds();

        beds.get(0).setPatientId("P001");
        beds.get(0).setOccupied(true);

        provideInput("P001\n");

        HospitalPatientManagementSystems.releaseBed();

        output.reset();

        HospitalPatientManagementSystems.displayAvailableBeds();

        String result = getOutput();

        assertTrue(
                "Released bed 001 should appear as available.",
                result.contains("Bed: 001")
        );
    }
}
