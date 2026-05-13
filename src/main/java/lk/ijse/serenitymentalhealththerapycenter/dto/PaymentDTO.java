package lk.ijse.serenitymentalhealththerapycenter.dto;


import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

// lombok ////////////////
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PaymentDTO {
    private String id;
    private SessionDTO session;
    private BigDecimal total;
    private String status;
    private Set<PaymentDetailDTO> paymentDetails = new HashSet<>();
}
