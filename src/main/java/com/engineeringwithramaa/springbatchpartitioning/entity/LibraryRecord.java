package com.engineeringwithramaa.springbatchpartitioning.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="Library_Record_Partitions")
public class LibraryRecord {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long recordId;
    private String recordType;
    private String bnbNumber;
    private String name;
    private String country;
    private String place;
    private String publisher;
    private String dateOfPublication;
    private String physicalDescription;
    private String deweyClassification;
    private String languages;

    public LibraryRecord() {
    }

    public LibraryRecord(Long recordId, String recordType, String bnbNumber,
                         String name, String country, String place,
                         String publisher, String dateOfPublication,
                         String physicalDescription, String deweyClassification, String languages) {
        this.id = Long.valueOf(0);
        this.recordId = recordId;
        this.recordType = recordType;
        this.bnbNumber = bnbNumber;
        this.name = name;
        this.country = country;
        this.place = place;
        this.publisher = publisher;
        this.dateOfPublication = dateOfPublication;
        this.physicalDescription = physicalDescription;
        this.deweyClassification = deweyClassification;
        this.languages = languages;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getBnbNumber() {
        return bnbNumber;
    }

    public void setBnbNumber(String bnbNumber) {
        this.bnbNumber = bnbNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(String dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    public String getPhysicalDescription() {
        return physicalDescription;
    }

    public void setPhysicalDescription(String physicalDescription) {
        this.physicalDescription = physicalDescription;
    }

    public String getDeweyClassification() {
        return deweyClassification;
    }

    public void setDeweyClassification(String deweyClassification) {
        this.deweyClassification = deweyClassification;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }


    @Override
    public String toString() {
        return "LibraryRecord{" +
                "UUID=" + id +
                "recordId=" + recordId +
                ", recordType='" + recordType + '\'' +
                ", bnbNumber='" + bnbNumber + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", place='" + place + '\'' +
                ", publisher='" + publisher + '\'' +
                ", dateOfPublication='" + dateOfPublication + '\'' +
                ", physicalDescription='" + physicalDescription + '\'' +
                ", deweyClassification='" + deweyClassification + '\'' +
                ", languages='" + languages + '\'' +
                '}';
    }
}
