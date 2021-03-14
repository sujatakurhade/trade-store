package com.trade.store.repository;

import com.trade.store.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

/**
 * Trade Repository
 */
@Repository
public interface TradeRepository extends JpaRepository<Trade, String> {

    /**
     * findByTradeId
     *
     * @param tradeId
     * @return List<Trade>
     */
    List<Trade> findByTradeId(String tradeId);

    /**
     * findByExpiredFlag
     *
     * @param expiredFlag
     * @return
     */
    List<Trade> findByExpiredFlag(String expiredFlag);

    /**
     * updateTradeByTradeIdAndVersion
     *
     * @param tradeId
     * @param version
     * @param counterPartyId
     * @param bookId
     * @param maturityDate
     * @param expiredFlag
     * @return int
     */
    @Modifying
    @Transactional
    @Query(value =
            "UPDATE Trade st " +
                    "SET st.counterPartyId= :counterPartyId," +
                    "st.bookId= :bookId," +
                    "st.maturityDate= :maturityDate," +
                    "st.expiredFlag= :expiredFlag " +
                    "WHERE st.tradeId= :tradeId AND st.version= :version")
    int updateTradeByTradeIdAndVersion(@Param("tradeId") String tradeId, @Param("version") int version, @Param("counterPartyId") String counterPartyId,
                                       @Param("bookId") String bookId, @Param("maturityDate") LocalDate maturityDate, @Param("expiredFlag") String expiredFlag);

    /**
     * updateExpirationFlagIfMaturityDateIsExpired
     *
     * @param currentDate
     * @return int
     */
    @Modifying
    @Transactional
    @Query(value =
            "UPDATE Trade st " +
                    "SET st.expiredFlag= 'Y' " +
                    "WHERE st.maturityDate < :currentDate")
    int updateExpirationFlagIfMaturityDateIsExpired(@Param("currentDate") LocalDate currentDate);

}
