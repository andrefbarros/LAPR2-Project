package app.ui.console;


import java.util.ArrayList;
import java.util.List;
import app.controller.RegisterVaccinationCenterController;
import app.domain.model.Employee;
import app.domain.model.VaccinationCenter;
import app.ui.console.utils.Utils;

/**
 * Register a new Vaccination Center
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class VaccinationCenterUI extends RegisterUI<VaccinationCenter> implements Runnable {
  private RegisterVaccinationCenterController ctrl;

  /**
   * VaccinationCenterUI Constructor
   */
  public VaccinationCenterUI() {
    ctrl = new RegisterVaccinationCenterController();
  }

  /**
   * Calls all the methods to successfully create a new Vaccination Center
   */
  @Override
  public void run() {
    try {
      insertData();
    } catch (Exception e) {
      System.out.println("Error: " + e);
    }

    boolean confirmed = confirmData(ctrl.stringifyData());

    if (confirmed) {
      ctrl.save();
      System.out.println("\nVaccination Center successfully registered!");
    }

  }

  /**
   * UI to register a Vaccination Center
   */

  @Override
  public void insertData() {
    System.out.println("\nRegister Vaccination Center: ");

    String name = Utils.readLineFromConsole("Name: ");
    String address = Utils.readLineFromConsole("Address: ");
    String email = Utils.readLineFromConsole("Email: ");
    String phone = Utils.readLineFromConsole("Phone Number: ");
    String fax = Utils.readLineFromConsole("Fax Number: ");
    String website = Utils.readLineFromConsole("Website Address: ");
    String openHours = Utils.readLineFromConsole("Opening hours: ");
    String closHours = Utils.readLineFromConsole("Closing hours: ");
    int slotDur = Utils.readIntegerFromConsole("Slot duration: ");
    int maxVac = Utils.readIntegerFromConsole("Maximum vaccines per slot: ");
    Employee coordinator;

    // select coordinator
    boolean flag = false;
    List<Employee> coordinators = new ArrayList<Employee>();
    coordinators = ctrl.getCoordinators();

    do {
      System.out.println("\nSelect a employee from the list:\n");
      coordinator = (Employee) Utils.showAndSelectOne(coordinators, "Coordinators");
      if (coordinators.contains(coordinator)) {
        flag = true;
      } else {
        System.out.println("\nInvalid coordinator.");
      }
    } while (!flag);

    ctrl.create(name, address, email, phone, fax, website, openHours, closHours, slotDur, maxVac,
        coordinator);
  }
}
