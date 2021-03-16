package com.trade.store.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trade.store.model.Trade;
import com.trade.store.service.TradeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TradeController.class)
public class TradeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TradeService tradeService;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testTradeStore() throws Exception {

        // prepare data and mock's behaviour
        Trade trade = new Trade();
        trade.setId(1);
        trade.setTradeId("T3");
        trade.setVersion(1);
        trade.setCounterPartyId("CP-3");
        trade.setBookId("B3");
        trade.setMaturityDate(LocalDate.now().plusDays(1));
        trade.setCreatedDate(LocalDate.now());
        trade.setExpiredFlag("N");
        when(tradeService.validateAndUpdateStore(trade)).thenReturn(true);

        // execute
        MvcResult result = mockMvc.perform(post("/trade")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(trade))).andReturn();

        // verify
        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status, "Incorrect Response Status");

        // verify that service method was called once
        verify(tradeService).validateAndUpdateStore(any(Trade.class));
    }

    @Test
    public void testGetAllTrades() throws Exception {
// prepare data and mock's behaviour
        List<Trade> tradeList = buildTrades();
        when(tradeService.findAllTrade()).thenReturn(tradeList);

        // execute
        MvcResult result = mockMvc.perform(get("/trade")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        // verify
        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status, "Incorrect Response Status");

        // verify that service method was called once
        verify(tradeService).findAllTrade();
    }

    private List<Trade> buildTrades() {
        Trade t1 = new Trade();
        t1.setId(1);
        t1.setTradeId("T1");
        t1.setVersion(1);
        t1.setCounterPartyId("CP-1");
        t1.setBookId("B1");
        t1.setMaturityDate(LocalDate.now().plusDays(1));
        t1.setCreatedDate(LocalDate.now());
        t1.setExpiredFlag("N");

        Trade t2 = new Trade();
        t2.setId(2);
        t2.setTradeId("T2");
        t2.setVersion(2);
        t2.setCounterPartyId("CP-1");
        t2.setBookId("B2");
        t2.setMaturityDate(LocalDate.now().plusDays(1));
        t2.setCreatedDate(LocalDate.now());
        t2.setExpiredFlag("N");

        List<Trade> tradeList = Arrays.asList(t1, t2);
        return tradeList;
    }

    @Test
    public void testGetUpdateExpiredFlagToY() throws Exception {

        doNothing().when(tradeService).updateExpiredFlagToY();
        // execute
        MvcResult result = mockMvc.perform(get("/trigger-scheduler")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        // verify
        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status, "Incorrect Response Status");

        // verify that service method was called once
        verify(tradeService).updateExpiredFlagToY();
    }
}
