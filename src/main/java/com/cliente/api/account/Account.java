package com.cliente.api.account;

import com.cliente.api.client.Cliente;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long accountId;
  @ManyToOne
  @JoinColumn(name = "client_id", nullable = false)
  private Cliente cliente;
  private String accountNur;
  private String type;
  private String currency;
  private double balance;
  @Enumerated(EnumType.STRING)
  private AccountStatus status;
  private LocalDate createdAt;


}
