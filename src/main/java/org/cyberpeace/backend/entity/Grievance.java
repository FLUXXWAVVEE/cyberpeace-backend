package org.cyberpeace.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Random;

@Entity
@Table(name = "grievances")
public class Grievance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    @Column(nullable = false)
    private String email;

    private String phone;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(name = "grievance_type")
    private String grievanceType;

    @Column(name = "area_of_grievance")
    private String areaOfGrievance;

    @Column(columnDefinition = "TEXT")
    private String details;

    @Column(name = "experience_rating")
    private String experienceRating;

    @Column(name = "page_url")
    private String pageUrl;

    @Column(name = "device_used")
    private String deviceUsed;

    private String browser;

    @Column(name = "contact_back")
    private String contactBack;

    @Column(name = "reference_id", unique = true, nullable = false)
    private String referenceId;

    @Column(name = "submitted_at", nullable = false)
    private LocalDateTime submittedAt;

    @PrePersist
    protected void onCreate() {
        this.submittedAt = LocalDateTime.now();
        if (this.referenceId == null) {
            Random rand = new Random();
            int num = 100000 + rand.nextInt(900000);
            this.referenceId = "CP-" + num;
        }
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGrievanceType() {
        return grievanceType;
    }

    public void setGrievanceType(String grievanceType) {
        this.grievanceType = grievanceType;
    }

    public String getAreaOfGrievance() {
        return areaOfGrievance;
    }

    public void setAreaOfGrievance(String areaOfGrievance) {
        this.areaOfGrievance = areaOfGrievance;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getExperienceRating() {
        return experienceRating;
    }

    public void setExperienceRating(String experienceRating) {
        this.experienceRating = experienceRating;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getDeviceUsed() {
        return deviceUsed;
    }

    public void setDeviceUsed(String deviceUsed) {
        this.deviceUsed = deviceUsed;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getContactBack() {
        return contactBack;
    }

    public void setContactBack(String contactBack) {
        this.contactBack = contactBack;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }
}
