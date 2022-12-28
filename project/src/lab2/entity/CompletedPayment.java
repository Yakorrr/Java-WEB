package lab2.entity;

import lab2.entity.embeddable.PendingPayment;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.YearMonth;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;

/**
 * Only a successful {@code PendingPayment} will result in a {@code CompletedPayment} which eliminates
 * the need to do validation twice.
 */
@Entity
public class CompletedPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Assume from payment provider
    @Column(nullable = false)
    private UUID transactionId = UUID.randomUUID();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PendingPayment.CreditCardType creditCardType;

    @Column(nullable = false)
    @Size(min = 4, max = 4)
    @Pattern(regexp = "^[0-9]{4}", message = "Last 4 credit card number digits expected")
    private String last4CreditCardDigits;

    @Column(nullable = false)
    private String cvv;

    @Column(nullable = false)
    private YearMonth cardExpiry;

    public CompletedPayment() {
    }

    public CompletedPayment(PendingPayment.CreditCardType creditCardType,
                            String last4CreditCardDigits,
                            String cvv,
                            YearMonth cardExpiry) {
        this.creditCardType = creditCardType;
        this.last4CreditCardDigits = last4CreditCardDigits;
        this.cvv = cvv;
        this.cardExpiry = cardExpiry;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public PendingPayment.CreditCardType getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(PendingPayment.CreditCardType creditCardType) {
        this.creditCardType = creditCardType;
    }

    public String getLast4CreditCardDigits() {
        return last4CreditCardDigits;
    }

    public void setLast4CreditCardDigits(String last4CreditCardDigits) {
        this.last4CreditCardDigits = last4CreditCardDigits;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public YearMonth getCardExpiry() {
        return cardExpiry;
    }

    public void setCardExpiry(YearMonth cardExpiry) {
        this.cardExpiry = cardExpiry;
    }

    private String creditCardNumberOutput() {
        return String.join("", Collections.nCopies(3, "**** ")) +
                last4CreditCardDigits;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        CompletedPayment that = (CompletedPayment) obj;

        return Objects.equals(transactionId, that.transactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId);
    }

    @Override
    public String toString() {
        return "Completed payment:" +
                "\nPayment ID: " + id +
                "\nTransaction ID: " + transactionId +
                "\nCredit card type: " + creditCardType +
                "\nCredit card number: " + creditCardNumberOutput() +
                "\nExpire date: " + cardExpiry;
    }
}
