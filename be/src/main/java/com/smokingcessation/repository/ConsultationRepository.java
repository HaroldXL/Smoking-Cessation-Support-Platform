package com.smokingcessation.repository;

import com.smokingcessation.model.Consultation;
import com.smokingcessation.model.ConsultationSlot;
import com.smokingcessation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ConsultationRepository extends JpaRepository<Consultation, Integer> {
    List<Consultation> findByUser(User user);
    List<Consultation> findByMentor(User mentor);
<<<<<<< HEAD
=======
    Optional<Consultation> findBySlot(ConsultationSlot slot);
>>>>>>> c40a9f3a4bb380d3fe7bae8efa8d45e45b10bf1f
    List<Consultation> findByMentorAndUser(User mentor, User user);
    List<Consultation> findByMentorAndSlot_SlotDate(User mentor, LocalDate slotDate);
    List<Consultation> findByMentor_Email(String email);
    List<Consultation> findByMentorAndStatus(User mentor, Consultation.Status status);
    Optional<Consultation> findByConsultationId(Integer consultationId);
}