package lk.ijse.serenitymentalhealththerapycenter.dto;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

// lombok ////////////////
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PaymentDetailDTO {
    private String id;
    private PaymentDTO payment;
    private BigDecimal amount;
    private LocalDate payDate;
}
