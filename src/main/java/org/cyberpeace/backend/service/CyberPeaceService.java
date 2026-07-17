package org.cyberpeace.backend.service;

import org.cyberpeace.backend.entity.*;
import org.cyberpeace.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CyberPeaceService {

    @Autowired
    private NewsletterSubscriberRepository newsletterRepo;

    @Autowired
    private VolunteerRepository volunteerRepo;

    @Autowired
    private InterestRepository interestRepo;

    @Autowired
    private GrievanceRepository grievanceRepo;

    @Autowired
    private KindDonationRepository kindDonationRepo;

    @Autowired
    private SponsorshipRepository sponsorshipRepo;

    @Autowired
    private ContactMessageRepository contactRepo;

    @Autowired
    private BlogRepository blogRepo;

    @Autowired
    private EventRepository eventRepo;

    @Transactional
    public NewsletterSubscriber saveNewsletter(NewsletterSubscriber subscriber) {
        // If email already exists, just return it (no need to crash for re-subscriptions)
        return newsletterRepo.findByEmail(subscriber.getEmail())
                .orElseGet(() -> newsletterRepo.save(subscriber));
    }

    @Transactional
    public Volunteer saveVolunteer(Volunteer volunteer) {
        return volunteerRepo.save(volunteer);
    }

    @Transactional
    public Interest saveInterest(Interest interest) {
        return interestRepo.save(interest);
    }

    @Transactional
    public Grievance saveGrievance(Grievance grievance) {
        return grievanceRepo.save(grievance);
    }

    @Transactional
    public KindDonation saveKindDonation(KindDonation donation) {
        return kindDonationRepo.save(donation);
    }

    @Transactional
    public Sponsorship saveSponsorship(Sponsorship sponsorship) {
        return sponsorshipRepo.save(sponsorship);
    }

    @Transactional
    public ContactMessage saveContact(ContactMessage message) {
        return contactRepo.save(message);
    }

    public List<Blog> getBlogs(String category, String query) {
        if (query != null && !query.trim().isEmpty()) {
            return blogRepo.findByTitleContainingIgnoreCaseOrTagsContainingIgnoreCase(query, query);
        }
        if (category != null && !category.equalsIgnoreCase("All")) {
            return blogRepo.findByCategory(category);
        }
        return blogRepo.findAll();
    }

    public List<Event> getEvents(String type, String query) {
        if (query != null && !query.trim().isEmpty()) {
            return eventRepo.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query);
        }
        if (type != null && !type.equalsIgnoreCase("All")) {
            return eventRepo.findByType(type);
        }
        return eventRepo.findAll();
    }
}
