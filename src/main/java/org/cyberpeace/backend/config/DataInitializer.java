package org.cyberpeace.backend.config;

import org.cyberpeace.backend.entity.Blog;
import org.cyberpeace.backend.entity.Event;
import org.cyberpeace.backend.repository.BlogRepository;
import org.cyberpeace.backend.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private BlogRepository blogRepo;

    @Autowired
    private EventRepository eventRepo;

    @Override
    public void run(String... args) throws Exception {
        // 1. Seed Blogs if empty
        if (blogRepo.count() == 0) {
            blogRepo.save(new Blog(
                "Exploring the Dark Web: Truths & Myths",
                "Detailed guide exploring what the dark web is, how it operates, and addressing common security misconceptions.",
                "Safety",
                "safety,web,darkweb",
                "https://images.unsplash.com/photo-1550751827-4bd374c3f58b?auto=format&fit=crop&w=800&q=80"
            ));

            blogRepo.save(new Blog(
                "The Rise of Generative AI in Cybersecurity",
                "Analysing how generative artificial intelligence models are changing defense mechanisms and modern threat landscapes.",
                "AI",
                "ai,tech,intelligence",
                "https://images.unsplash.com/photo-1526374965328-7f61d4dc18c5?auto=format&fit=crop&w=800&q=80"
            ));

            blogRepo.save(new Blog(
                "Understanding the Digital Personal Data Protection Act",
                "An overview of the new DPDP compliance rules, citizen data rights, and organizational responsibilities in India.",
                "Policy",
                "policy,dpdp,compliance",
                "https://images.unsplash.com/photo-1589829545856-d10d557cf95f?auto=format&fit=crop&w=800&q=80"
            ));

            System.out.println("Seeded mock blogs successfully!");
        }

        // 2. Seed Events if empty
        if (eventRepo.count() == 0) {
            eventRepo.save(new Event(
                "CyberPeace Summit 2.0",
                "Global assembly of policy makers, tech leads, and cybersecurity defenders discussing digital peace.",
                "event",
                "New Delhi, India",
                "summit,peace,policy",
                "https://images.unsplash.com/photo-1540575467063-178a50c2df87?auto=format&fit=crop&w=800&q=80",
                LocalDateTime.now().plusDays(30)
            ));

            eventRepo.save(new Event(
                "eRaksha Competition 2024",
                "National level cyber security challenge for students and professionals to showcase cyber defensive skills.",
                "campaign",
                "Online (Hybrid)",
                "eraksha,competition,students",
                "https://images.unsplash.com/photo-1504384308090-c894fdcc538d?auto=format&fit=crop&w=800&q=80",
                LocalDateTime.now().plusDays(15)
            ));

            eventRepo.save(new Event(
                "Global Cybersecurity Hackathon",
                "Collaborate with international teams to build solutions resolving real-world security loopholes.",
                "event",
                "Online",
                "hackathon,dev,security",
                "https://images.unsplash.com/photo-1515378791036-0648a3ef77b2?auto=format&fit=crop&w=800&q=80",
                LocalDateTime.now().plusDays(45)
            ));

            System.out.println("Seeded mock events successfully!");
        }
    }
}
