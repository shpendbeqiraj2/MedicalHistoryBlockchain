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
public class Report {
    protected String patientName;
    protected String patientSurname;
    protected String doctor;
    protected String report;
    protected String analysis;
    
    public Report(String patientName, String patientSurname, String doctor, String report, String analysis)
    {
        this.analysis = analysis;
        this.doctor = doctor;
        this.patientName = patientName;
        this.report = report;
        this.patientSurname = patientSurname;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPatientSurname() {
        return patientSurname;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getReport() {
        return report;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setPatientSurname(String patientSurname) {
        this.patientSurname = patientSurname;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }
    
    
    
}
