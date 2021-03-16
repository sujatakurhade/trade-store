package com.trade.store.service.impl;

import com.trade.store.exception.EntityViolationException;
import com.trade.store.exception.InvalidTradeException;
import com.trade.store.model.Trade;
import com.trade.store.repository.TradeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TradeServiceImplTest {

    @Mock
    private TradeRepository tradeRepository;

    @InjectMocks
    private TradeServiceImpl tradeService;

    private List<Trade> buildStore() {
        Trade trade1 = new Trade();
        trade1.setId(1);
        trade1.setTradeId("T1");
        trade1.setVersion(1);
        trade1.setCounterPartyId("CP-3");
        trade1.setBookId("B3");
        trade1.setMaturityDate(LocalDate.now().plusDays(1));
        trade1.setCreatedDate(LocalDate.now());
        trade1.setExpiredFlag("N");
        //Stored one trade
        List<Trade> tradeList = Arrays.asList(trade1);
        return tradeList;
    }

    @Test
    public void testValidateAndUpdateStoreWithSameVersion() throws Exception {

        List<Trade> tradeList = buildStore();

        Trade trade = new Trade();
        trade.setId(2);
        trade.setTradeId("T1");
        trade.setVersion(1);
        trade.setCounterPartyId("CP-3");
        trade.setBookId("B1");
        trade.setMaturityDate(LocalDate.now().plusDays(2));
        trade.setCreatedDate(LocalDate.now());
        trade.setExpiredFlag("N");

        when(tradeRepository.findByTradeId(trade.getTradeId())).thenReturn(tradeList);
        when(tradeRepository.updateTradeByTradeIdAndVersion(trade.getTradeId(), trade.getVersion(), trade.getCounterPartyId(), trade.getBookId(),
                trade.getMaturityDate(), trade.getExpiredFlag())).thenReturn(1);

        Boolean isSuccess = tradeService.validateAndUpdateStore(trade);
        assertEquals(true, isSuccess);
    }

    @Test
    public void testValidateAndUpdateStoreWithHigherVersion() throws Exception {

        List<Trade> tradeList = buildStore();

        Trade trade = new Trade();
        trade.setId(3);
        trade.setTradeId("T1");
        trade.setVersion(2);
        trade.setCounterPartyId("CP-1");
        trade.setBookId("B1");
        trade.setMaturityDate(LocalDate.now().plusDays(5));
        trade.setCreatedDate(LocalDate.now());
        trade.setExpiredFlag("N");

        when(tradeRepository.findByTradeId(trade.getTradeId())).thenReturn(tradeList);
        Boolean isSuccess = tradeService.validateAndUpdateStore(trade);
        assertEquals(true, isSuccess);
    }

    @Test
    public void testValidateAndUpdateStoreWithCreateTradeWhenNoRecordInStore() throws Exception {

        Trade trade = new Trade();
        trade.setId(1);
        trade.setTradeId("T1");
        trade.setVersion(1);
        trade.setCounterPartyId("CP-1");
        trade.setBookId("B1");
        trade.setMaturityDate(LocalDate.now().plusDays(2));
        trade.setCreatedDate(LocalDate.now());
        trade.setExpiredFlag("N");

        when(tradeRepository.findByTradeId(trade.getTradeId())).thenReturn(anyList());
        Boolean isSuccess = tradeService.validateAndUpdateStore(trade);
        assertEquals(true, isSuccess);
    }

    @Test
    public void testValidateAndUpdateStoreWithCreateTradeWithInvalidMaturityDate() throws Exception {

        Trade trade = new Trade();
        trade.setId(1);
        trade.setTradeId("T1");
        trade.setVersion(1);
        trade.setCounterPartyId("CP-1");
        trade.setBookId("B1");
        trade.setMaturityDate(LocalDate.now().minusDays(1));
        trade.setCreatedDate(LocalDate.now());
        trade.setExpiredFlag("N");

        when(tradeRepository.findByTradeId(trade.getTradeId())).thenReturn(anyList());
        assertThrows(InvalidTradeException.class, () -> Optional
                .of(tradeService.validateAndUpdateStore(trade))
                .orElseThrow(InvalidTradeException::new));
    }

    @Test
    public void testValidateAndUpdateStoreWithInvalidOrLowerVersion() throws Exception {

        List<Trade> tradeList = buildStore();

        Trade trade = new Trade();
        trade.setId(2);
        trade.setTradeId("T1");
        trade.setVersion(0);
        trade.setCounterPartyId("CP-1");
        trade.setBookId("B1");
        trade.setMaturityDate(LocalDate.now().plusDays(2));
        trade.setCreatedDate(LocalDate.now());
        trade.setExpiredFlag("N");

        when(tradeRepository.findByTradeId(trade.getTradeId())).thenReturn(tradeList);
        assertThrows(InvalidTradeException.class, () -> Optional
                .of(tradeService.validateAndUpdateStore(trade))
                .orElseThrow(InvalidTradeException::new));
    }

    @Test
    public void testFindAllTrade() {
        List getTradeList = tradeService.findAllTrade();
        assertEquals(0, getTradeList.size());
    }

    @Test
    public void testEntityViolationException() {
        List<Trade> tradeList = buildStore();

        Trade trade = new Trade();
        trade.setId(2);
        trade.setTradeId("T1");
        trade.setVersion(1);
        trade.setCounterPartyId("CP-3");
        trade.setBookId("B1");
        trade.setMaturityDate(LocalDate.now().plusDays(2));
        trade.setCreatedDate(LocalDate.now());
        trade.setExpiredFlag("N");

        when(tradeRepository.findByTradeId(trade.getTradeId())).thenReturn(tradeList);
        when(tradeRepository.updateTradeByTradeIdAndVersion(trade.getTradeId(), trade.getVersion(), trade.getCounterPartyId(), trade.getBookId(),
                trade.getMaturityDate(), trade.getExpiredFlag())).thenReturn(0);
        assertThrows(EntityViolationException.class, () -> Optional
                .of(tradeService.validateAndUpdateStore(trade))
                .orElseThrow(EntityViolationException::new));
    }
}
