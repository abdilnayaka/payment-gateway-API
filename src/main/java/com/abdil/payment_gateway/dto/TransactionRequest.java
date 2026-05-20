package com.abdil.payment_gateway.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class TransactionRequest {

    // @NotBlank memastikan data tidak boleh kosong atau hanya berisi spasi
    @NotBlank(message = "Merchant ID tidak boleh kosong!")
    private String merchantId;

    // @NotNull memastikan data harus dikirim
    // @Min memastikan nominal terkecil, misalnya minimal transaksi adalah Rp 10.000
    @NotNull(message = "Nominal (amount) wajib diisi!")
    @Min(value = 10000, message = "Minimal transaksi adalah Rp 10.000")
    private BigDecimal amount;

    // Getter dan Setter
    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}