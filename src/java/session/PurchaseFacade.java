/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Purchase;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kasutaja
 */
@Stateless
public class PurchaseFacade extends AbstractFacade<Purchase> {

    @PersistenceContext(unitName = "JKTV21WebShopSubbotinaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PurchaseFacade() {
        super(Purchase.class);
    }
    
    public class PurchaseListResult {
        private List<Purchase> purchases;
        private boolean error;

        public PurchaseListResult(List<Purchase> purchases, boolean error) {
            this.purchases = purchases;
            this.error = error;
        }

        public List<Purchase> getPurchases() {
            return purchases;
        }

        public boolean isError() {
            return error;
        }
    }

    public PurchaseListResult getListPurchase(String selectYearN, String selectMonthN, String selectDayN, String selectYearL, String selectMonthL, String selectDayL) {
        try {
            LocalDate startDate = LocalDate.of(Integer.parseInt(selectYearN), Integer.parseInt(selectMonthN), Integer.parseInt(selectDayN));
            LocalDate endDate = LocalDate.of(Integer.parseInt(selectYearL), Integer.parseInt(selectMonthL), Integer.parseInt(selectDayL));
            LocalDateTime startOfDay = startDate.atStartOfDay();
            LocalDateTime endOfDay = endDate.atStartOfDay().plusDays(1);
            Date beginDatePeriod = Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
            Date endDatePeriod  = Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
            if (startDate.isAfter(endDate) || endDate.isAfter(LocalDate.now())) {
                return new PurchaseListResult(new ArrayList<>(), true);
            }else{
                List<Purchase> purchases = em.createQuery("SELECT p FROM Purchase p WHERE p.takeOfProduct >= :beginDatePeriod AND p.takeOfProduct < :endDatePeriod")
                    .setParameter("beginDatePeriod", beginDatePeriod)
                    .setParameter("endDatePeriod", endDatePeriod)
                    .getResultList();
                return new PurchaseListResult(purchases, false);
            }
           
        } catch (DateTimeException e) {
            return new PurchaseListResult(new ArrayList<>(), true);
        }
    }

    
    
}
