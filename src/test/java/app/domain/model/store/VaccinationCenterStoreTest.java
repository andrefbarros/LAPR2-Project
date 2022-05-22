package app.domain.model.store;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import app.domain.model.Employee;
import app.domain.model.VaccinationCenter;
import app.domain.shared.Constants;

/**
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class VaccinationCenterStoreTest {
  VaccinationCenterStore store = new VaccinationCenterStore();
  Employee coordinator;
  VaccinationCenter center;
  VaccinationCenter center2;

  /**
   * Set up for the tests
   */
  @Before
  public void setUp() {
    coordinator = new Employee("Joana", "+351916478865", "email@email.com", "address",
        "000000000ZZ4", Constants.ROLE_COORDINATOR);
  }

  /**
   * Check that saveVaccinationCenter (community mass vaccination center) is working properly
   * 
   */
  @Test
  public void ensureAddCenterIsWorkingCorrectly() {
    center = store.createCommunityMassCenter("name", "address", "email@email.com", "+351961919169",
        "+351961919169", "http://www.google.com", "10:00", "19:00", 5, 5, this.coordinator);

    assertEquals(store.size(), 0);

    store.saveVaccinationCenter(center);

    assertEquals(store.size(), 1);
  }

  /**
   * Check that saveVaccinationCenter (health care center) is working properly
   * 
   */
  @Test
  public void ensureAddCenterIsWorkingCorrectly2() {
    center = store.createHealthCareCenter("name", "address", "email@email.com", "+351961919169",
        "+351961919169", "http://www.google.com", "10:00", "19:00", 5, 5, this.coordinator, "test",
        "test");

    assertEquals(store.size(), 0);

    store.saveVaccinationCenter(center);

    assertEquals(store.size(), 1);
  }

  /**
   * Check that checkDuplicates method is checking duplicated centers properly
   * 
   * @throws Exception IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureCheckDuplicatesIsWorkingCorrectly() {
    store.validateVaccinationCenter(center);
  }

  /**
   * Check that validateVaccinationCenter is working properly
   * 
   * @throws Exception IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureValidateIsWorking() {
    store.validateVaccinationCenter(null);
  }

  /**
   * Check that it is possible to add multiple centers to the system (community mass vaccination center)
   */
  @Test
  public void ensureIsPossibleToAddAnotherCenter() {
    assert store.size() == 0;

    center =
        store.createCommunityMassCenter("name123", "address", "email@email.com", "+351961919169",
            "+351961919169", "http://www.google.com", "10:00", "19:00", 5, 5, this.coordinator);

    store.saveVaccinationCenter(center);

    assertEquals(store.size(), 1);

    center2 =
        store.createCommunityMassCenter("name123", "address", "email@gmail.com", "+351961919168",
            "+351961919179", "http://www.gogle.com", "10:00", "19:00", 5, 5, this.coordinator);

    store.validateVaccinationCenter(center2);

    store.saveVaccinationCenter(center2);

    assertEquals(store.size(), 2);
  }

  /**
   * Check that it is possible to add multiple centers to the system (health care center)
   */
  @Test
  public void ensureIsPossibleToAddAnotherCenter2() {
    assert store.size() == 0;

    center = store.createHealthCareCenter("name123", "address", "email@email.com", "+351961919169",
        "+351961919169", "http://www.google.com", "10:00", "19:00", 5, 5, this.coordinator, "test",
        "test");

    store.saveVaccinationCenter(center);

    assertEquals(store.size(), 1);

    center2 = store.createHealthCareCenter("name123", "address", "email@gmail.com", "+351961919168",
        "+351961919179", "http://www.gogle.com", "10:00", "19:00", 5, 5, this.coordinator, "teste",
        "teste");

    store.validateVaccinationCenter(center2);

    store.saveVaccinationCenter(center2);

    assertEquals(store.size(), 2);
  }

  /**
   * Check that it is possible to add multiple centers to the system (health care center and community mass vaccination
   * center)
   */
  @Test
  public void ensureIsPossibleToAddAnotherCenter3() {
    assert store.size() == 0;

    center = store.createHealthCareCenter("name123", "address", "email@email.com", "+351961919169",
        "+351961919169", "http://www.google.com", "10:00", "19:00", 5, 5, this.coordinator, "test",
        "test");

    store.saveVaccinationCenter(center);

    assertEquals(store.size(), 1);

    center2 =
        store.createCommunityMassCenter("name123", "address", "email@gmail.com", "+351961919168",
            "+351961919179", "http://www.gogle.com", "10:00", "19:00", 5, 5, this.coordinator);

    store.validateVaccinationCenter(center2);

    store.saveVaccinationCenter(center2);

    assertEquals(store.size(), 2);
  }
}
