package com.food.food;

import com.food.food.DTO.PagamentoRequest;
import com.food.food.models.Pagamento;
import com.food.food.models.Pedido;
import com.food.food.repositories.PagamentoRepository;
import com.food.food.repositories.PedidoRepository;
import com.food.food.services.PagamentoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class PagamentoTest {

    @InjectMocks
    private PagamentoService pagamentoService;

    @Mock
    private PagamentoRepository pagamentoRepository;

    @Mock
    private PedidoRepository pedidoRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcessPayment() {
        Pedido pedido = new Pedido();
        pedido.setId(1L);

        when(pedidoRepository.findById(anyLong())).thenReturn(Optional.of(pedido));

        PagamentoRequest pagamentoRequest = new PagamentoRequest();
        pagamentoRequest.setMetodo("credito");
        pagamentoRequest.setBandeiraCartao("Visa");

        when(pagamentoRepository.save(any(Pagamento.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Pagamento pagamento = pagamentoService.processPayment(1L, pagamentoRequest);

        assertEquals(pagamentoRequest.getMetodo(), pagamento.getMetodo());
        assertEquals(pedido, pagamento.getPedido());
        verify(pagamentoRepository, times(1)).save(any(Pagamento.class));
        verify(pedidoRepository, times(1)).save(any(Pedido.class));
    }

    @Test
    public void testProcessPaymentUnsupportedMethod() {
        Pedido pedido = new Pedido();
        pedido.setId(1L);

        when(pedidoRepository.findById(anyLong())).thenReturn(Optional.of(pedido));

        PagamentoRequest pagamentoRequest = new PagamentoRequest();
        pagamentoRequest.setMetodo("boleto");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            pagamentoService.processPayment(1L, pagamentoRequest);
        });
        assertEquals("Método de pagamento não suportado", exception.getMessage());

        verify(pagamentoRepository, never()).save(any());
        verify(pedidoRepository, never()).save(any());
    }

    @Test
    public void testGetPagamentoById() {
        Pagamento pagamento = new Pagamento();
        pagamento.setId(1L);

        when(pagamentoRepository.findById(1L)).thenReturn(Optional.of(pagamento));

        Pagamento result = pagamentoService.getPagamentoById(1L);

        assertEquals(1L, result.getId());
        verify(pagamentoRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAllPagamentos() {
        List<Pagamento> pagamentos = List.of(new Pagamento(), new Pagamento());

        when(pagamentoRepository.findAll()).thenReturn(pagamentos);

        List<Pagamento> result = pagamentoService.getAllPagamentos();

        assertEquals(2, result.size());
        verify(pagamentoRepository, times(1)).findAll();
    }

    @Test
    public void testGetPagamentoByIdNotFound() {
        when(pagamentoRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            pagamentoService.getPagamentoById(1L);
        });

        assertEquals("Pagamento not found with ID: 1", exception.getMessage());
        verify(pagamentoRepository, times(1)).findById(1L);
    }
}
