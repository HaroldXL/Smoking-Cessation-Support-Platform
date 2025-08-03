package com.smokingcessation.model;

import jakarta.persistence.*;
import lombok.Getter;
<<<<<<< HEAD
=======
import lombok.NoArgsConstructor;
>>>>>>> c40a9f3a4bb380d3fe7bae8efa8d45e45b10bf1f
import lombok.Setter;

import java.sql.Timestamp;

<<<<<<< HEAD
@Entity
@Table(name = "payments")
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;

    @ManyToOne
    @JoinColumn(name = "subscription_id", nullable = false)
    private Subscription subscription;

    private Double amount;

    private String paymentMethod; // ví dụ: "vnpay"

    private String transactionId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status = PaymentStatus.pending;

    private Timestamp paymentDate = new Timestamp(System.currentTimeMillis());

    public enum PaymentStatus {
        pending,
        completed,
        failed,
        refunded
    }
}
=======
    @Entity
    @Table(name = "payments")
    @Getter
    @Setter
    @NoArgsConstructor
    public class Payment {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer paymentId;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "subscription_id", nullable = false)
        private Subscription subscription;

        @Column(nullable = false)
        private Double amount;

        @Column(nullable = false)
        private String paymentMethod; // "vnpay"

        @Column(nullable = false, unique = true, length = 64)
        private String transactionId; // VNPay's transaction id

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private PaymentStatus status = PaymentStatus.pending;

        @Column
        private Timestamp paymentDate;

        public enum PaymentStatus {
            pending,
            completed,
            failed,
            refunded
        }
    }

>>>>>>> c40a9f3a4bb380d3fe7bae8efa8d45e45b10bf1f
