package com.dxc.ppm.service;

import com.dxc.ppm.api.model.*;
import com.dxc.ppm.model.*;
import com.dxc.ppm.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public String upsertMultiPatients(Patient patient) {

        PatientEntity patientEntity = new PatientEntity();

        PersonalInfoEntity personalInfoEntity = new PersonalInfoEntity();
        personalInfoEntity.setFullName(patient.getPersonalInfo().getFullName());
        personalInfoEntity.setAddress(patient.getPersonalInfo().getAddress());
        personalInfoEntity.setSex(patient.getPersonalInfo().getSex());
        personalInfoEntity.setPob(patient.getPersonalInfo().getPob());
        personalInfoEntity.setDob(new Date());

        patientEntity.setPersonalInfoEntity(personalInfoEntity);


        List<MedicalTreatmentProfile> medicalTreatmentProfileList = patient.getMedicalTreatmentProfile();
        List<MedicalTreatmentProfileEntity> medicalTreatmentProfileEntityList = new ArrayList<>();
        for (int i = 0; i < medicalTreatmentProfileList.size(); i++) {

            MedicalTreatmentProfileEntity medicalTreatmentProfileEntity = new MedicalTreatmentProfileEntity();

            List<MedicineEntity> beingUsedEntityList = new ArrayList<>();
            List<MedicineEntity> recentlyUsedEntityList = new ArrayList<>();


            for(int j = 0; j < medicalTreatmentProfileList.get(i).getPrescription().getBeingUsed().size(); j++) {
                MedicineEntity medicineEntity = new MedicineEntity();
                medicineEntity.setName(medicalTreatmentProfileList.get(i).getPrescription().getBeingUsed().get(j).getName());
                medicineEntity.setQuantity(medicalTreatmentProfileList.get(i).getPrescription().getBeingUsed().get(j).getQuantity());
                beingUsedEntityList.add(medicineEntity);
            }


            for(int j = 0; j < medicalTreatmentProfileList.get(i).getPrescription().getRecentUsed().size(); j++) {
                MedicineEntity medicineEntity = new MedicineEntity();
                medicineEntity.setName(medicalTreatmentProfileList.get(i).getPrescription().getRecentUsed().get(j).getName());
                medicineEntity.setQuantity(medicalTreatmentProfileList.get(i).getPrescription().getRecentUsed().get(j).getQuantity());
                recentlyUsedEntityList.add(medicineEntity);
            }

            PrescriptionEntity prescriptionEntity = new PrescriptionEntity();
//            prescriptionEntity.setBeingUsed(beingUsedEntityList);
//            prescriptionEntity.setRecentlyUsed(recentlyUsedEntityList);

//            medicalTreatmentProfileEntity.setPrescription(prescriptionEntity);

            medicalTreatmentProfileEntity.setCreatedDate(new Date());
            medicalTreatmentProfileEntity.setModifiedDate(new Date());
            medicalTreatmentProfileEntity.setDoctor(patient.getMedicalTreatmentProfile().get(i).getDoctor());
            medicalTreatmentProfileEntity.setDiseasesHistory(patient.getMedicalTreatmentProfile().get(i).getDiseasesHistory().toString());

            MedicalTestResultEntity medicalTestResultEntity = new MedicalTestResultEntity();
            medicalTestResultEntity.setBloodType(patient.getMedicalTreatmentProfile().get(i).getMedicalTestResult().getBloodType());
            medicalTestResultEntity.setUltraSound(patient.getMedicalTreatmentProfile().get(i).getMedicalTestResult().getUltraSound());
            medicalTestResultEntity.setXray(patient.getMedicalTreatmentProfile().get(i).getMedicalTestResult().getXRay());
            medicalTestResultEntity.setAllergicMedicines(patient.getMedicalTreatmentProfile().get(i).getMedicalTestResult().getAllergicMedicines().toString());

            medicalTreatmentProfileEntity.setMedicalTestResultEntity(medicalTestResultEntity);

            medicalTreatmentProfileEntityList.add(medicalTreatmentProfileEntity);

        }

        patientEntity.setMedicalTreatmentProfileEntities(medicalTreatmentProfileEntityList);
        patientRepository.saveAndFlush(patientEntity);

        return "OK";
    }
}
