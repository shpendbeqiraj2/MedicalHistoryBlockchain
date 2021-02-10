/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalReport;

/**
 *
 * @author HP
 */
public class MikroReport extends Report{
    
    private String mikro;
    
    public MikroReport(String patientName, String patientSurname, String doctor, String report, String analysis, String mikro) {
        super(patientName, patientSurname, doctor, report, analysis);
        this.mikro = mikro;
    }

    public String getMikro() {
        return mikro;
    }

    public void setMikro(String mikro) {
        this.mikro = mikro;
    }
    
}
