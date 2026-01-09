package stlsecodingchallengetemplate;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import org.junit.Before;
import org.junit.Test;

public class BankServiceTest {
  List<Account> accounts;
  AccountRepository bankRepo;
  BankService service;

  @Before
  public void setup() {
    Account a = new Account(1, new BigDecimal(1000.0));
    Account b = new Account(2, new BigDecimal(1000.0));
    accounts = new ArrayList<>();
    accounts.add(a);
    accounts.add(b);
    bankRepo = new AccountRepository(accounts);
    service = new BankService(bankRepo);
  }

  @Test
  public void testConcurrentTransfers() throws InterruptedException {
    ExecutorService executor = Executors.newFixedThreadPool(10);

    // Run 1000 concurrent transfers of $1
    for (int i = 0; i < 1000; i++) {
      executor.execute(() -> service.transfer(1, 2, new BigDecimal(1.0)));
    }

    executor.shutdown();
    executor.awaitTermination(10, TimeUnit.SECONDS);

    Account a = bankRepo.getAccountByID(1).get();
    Account b = bankRepo.getAccountByID(2).get();

    // JUnit 4 assertEquals(expected, actual, delta)
    assertEquals(0.0, a.getBalance().doubleValue(), 0.01);
    assertEquals(2000.0, b.getBalance().doubleValue(), 0.01);
  }

  // JUnit 4 uses (timeout = milliseconds) inside the @Test annotation
  @Test(timeout = 5000)
  public void testDeadlockPrevention() throws InterruptedException {
    Thread t1 = new Thread(() -> {
      for (int i = 0; i < 500; i++)
        service.transfer(1, 2, new BigDecimal(1.0));
    });
    Thread t2 = new Thread(() -> {
      for (int i = 0; i < 500; i++)
        service.transfer(2, 1, new BigDecimal(1.0));
    });

    t1.start();
    t2.start();
    t1.join();
    t2.join();

    Account a = bankRepo.getAccountByID(1).get();
    Account b = bankRepo.getAccountByID(2).get();
    
    assertEquals(1000.0, a.getBalance().doubleValue(), 0.01);
    assertEquals(1000.0, b.getBalance().doubleValue(), 0.01);
  }
}