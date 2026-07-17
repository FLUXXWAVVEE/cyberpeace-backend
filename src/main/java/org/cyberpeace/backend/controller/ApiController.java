package org.cyberpeace.backend.controller;

import jakarta.validation.Valid;
import org.cyberpeace.backend.entity.*;
import org.cyberpeace.backend.service.CyberPeaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private CyberPeaceService cyberPeaceService;

    // Health Check Endpoint
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> getHealth() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("timestamp", java.time.LocalDateTime.now().toString());
        return ResponseEntity.ok(response);
    }

    // 1. Submit Contact Form
    @PostMapping("/contact")
    public ResponseEntity<Map<String, Object>> submitContact(@Valid @RequestBody ContactMessage message) {
        cyberPeaceService.saveContact(message);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Thank you for reaching out! A representative will contact you shortly.");
        return ResponseEntity.ok(response);
    }

    // 2. Submit Newsletter Form
    @PostMapping("/newsletter")
    public ResponseEntity<Map<String, Object>> submitNewsletter(@Valid @RequestBody NewsletterSubscriber subscriber) {
        cyberPeaceService.saveNewsletter(subscriber);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Subscribed successfully! Welcome to the CyberPeace Newsletter.");
        return ResponseEntity.ok(response);
    }

    // 3. Submit Grievance Form
    @PostMapping("/grievances")
    public ResponseEntity<Map<String, Object>> submitGrievance(@Valid @RequestBody Grievance grievance) {
        Grievance saved = cyberPeaceService.saveGrievance(grievance);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Your grievance report has been successfully recorded. Reference ID: " + saved.getReferenceId());
        response.put("referenceId", saved.getReferenceId());
        return ResponseEntity.ok(response);
    }

    // 4. Submit Volunteer Form
    @PostMapping("/volunteer")
    public ResponseEntity<Map<String, Object>> submitVolunteer(@Valid @RequestBody Volunteer volunteer) {
        cyberPeaceService.saveVolunteer(volunteer);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Thank you for showing interest in the CyberPeace Corps! Our recruitment team will review your application.");
        return ResponseEntity.ok(response);
    }

    // 5. Submit General Interest Form
    @PostMapping("/interest")
    public ResponseEntity<Map<String, Object>> submitInterest(@Valid @RequestBody Interest interest) {
        cyberPeaceService.saveInterest(interest);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Your interest has been logged. We will connect you with our project leads soon.");
        return ResponseEntity.ok(response);
    }

    // 6. Submit Kind Tech Donation Form
    @PostMapping("/donate-kind")
    public ResponseEntity<Map<String, Object>> submitKindDonation(@Valid @RequestBody KindDonation donation) {
        cyberPeaceService.saveKindDonation(donation);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Thank you for your generous tech donation! Our sanitization team will contact you for pickup.");
        return ResponseEntity.ok(response);
    }

    // 7. Submit Sponsorship Form
    @PostMapping("/sponsor")
    public ResponseEntity<Map<String, Object>> submitSponsorship(@Valid @RequestBody Sponsorship sponsorship) {
        cyberPeaceService.saveSponsorship(sponsorship);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Sponsorship interest successfully logged. Our outreach coordinator will email you proposal docs.");
        return ResponseEntity.ok(response);
    }

    // 8. Fetch Blogs
    @GetMapping("/blogs")
    public ResponseEntity<Map<String, Object>> getBlogs(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String query) {
        List<Blog> blogs = cyberPeaceService.getBlogs(category, query);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("blogs", blogs);
        return ResponseEntity.ok(response);
    }

    // 9. Fetch Events
    @GetMapping("/events")
    public ResponseEntity<Map<String, Object>> getEvents(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String query) {
        List<Event> events = cyberPeaceService.getEvents(type, query);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("events", events);
        return ResponseEntity.ok(response);
    }

    // Validation Exception Handler
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        
        String errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        
        response.put("message", "Validation failed: " + errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Generic Exception Handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleExceptions(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
