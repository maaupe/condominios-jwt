package br.com.condominio.app.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.com.condominio.app.modelo.ItemTitulo;
import br.com.condominio.app.modelo.StatusTitulo;
import br.com.condominio.app.modelo.Titulo;
import br.com.condominio.app.modelo.Unidade;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TituloDto {
    private Long id;
    private String nossonumero;
    private LocalDate vencimento;
	private LocalDate criacao;
    private BigDecimal valor;
    private BigDecimal despesa;
    private BigDecimal outrasdespesa;
    private BigDecimal juros;
    private BigDecimal iof;
    private BigDecimal abatimento;
    private BigDecimal desconto;
    private BigDecimal valorpago;
    private LocalDate dtcredito;
    private StatusTitulo status;
    //private List<ItemTitulo> itens;
    //private Unidade unidade;
    //private String razaoSocial;
    //private String numero;
}
