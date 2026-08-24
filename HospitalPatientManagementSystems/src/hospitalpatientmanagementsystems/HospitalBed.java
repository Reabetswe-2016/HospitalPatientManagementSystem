/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalpatientmanagementsystems;


public class HospitalBed extends Patient {
    
    private String wardNumber;
    private String bedNumber;
    private boolean occupied;
    
    public HospitalBed(String wardNumber, String bedNumber){
        super("", "","", 0, "", "", "");
        
        this.wardNumber = wardNumber;
        this.bedNumber = bedNumber;
        this.occupied = false;
    }

    public String getWardNumber() {
        return wardNumber;
    }

    public void setWardNumber(String wardNumber) {
        this.wardNumber = wardNumber;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
    
    @Override
    public void displayDetails(){
        
        System.out.println("Ward Number: " + wardNumber);
        System.out.println("Bed Number: " + bedNumber);
        System.out.println("Occupied: " + occupied);
        
        if (occupied){
        
        System.out.println("Patient ID: " + getPatientId());
        System.out.println("Patient Name: " + getFirstName() + " " + getLastName());
        System.out.println("Patient Category: " + getPatientCategory());
        }
    }
    
}
