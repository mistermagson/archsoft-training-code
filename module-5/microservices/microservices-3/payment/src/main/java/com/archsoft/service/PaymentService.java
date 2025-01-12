package com.archsoft.service;

import com.archsoft.exception.RecordNotFoundException;
import com.archsoft.model.payment.Payment;
import com.archsoft.model.payment.StatusPayment;
import com.archsoft.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final MessageBrokerService messageBrokerService;

    public PaymentService(PaymentRepository paymentRepository, MessageBrokerService messageBrokerService) {
        this.paymentRepository = paymentRepository;
        this.messageBrokerService = messageBrokerService;
    }

    public Payment create(Payment payment) throws IOException {
        payment.setDateTime(new Date());
        payment.setStatus(StatusPayment.PENDING.name());

        Payment paymentInserted = paymentRepository.insert(payment);

        // Here it communicates with some real CreditCard service

        messageBrokerService.sendInsertEvent(paymentInserted);

        return paymentInserted;
    }

    public Payment find(String id) throws RecordNotFoundException {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Payment update(Payment payment) throws RecordNotFoundException, IOException {
        paymentRepository.findById(payment.getId())
                .orElseThrow(() -> new RecordNotFoundException(payment.getId()));

        Payment paymentUpdated = paymentRepository.save(payment);
        messageBrokerService.sendUpdateEvent(paymentUpdated);

        return paymentUpdated;
    }

    public void delete(String id) throws RecordNotFoundException, IOException {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));
        paymentRepository.delete(payment);

        messageBrokerService.sendDeleteEvent(payment);
    }

    public List<Payment> list() {
        return paymentRepository.findAll();
    }

}
