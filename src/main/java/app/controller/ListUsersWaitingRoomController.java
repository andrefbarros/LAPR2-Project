package app.controller;

import app.domain.model.Company;
import app.domain.model.WaitingRoom;
import app.domain.model.dto.VaccinationCenterListDTO;
import app.domain.model.store.VaccinationCenterStore;
import app.exception.NotAuthorizedException;
import app.session.EmployeeSession;

public class ListUsersWaitingRoomController {
  private VaccinationCenterStore vaccinationCenterStore;
  private EmployeeSession nurseSession;

  /**
   * Constructor for ListUsersWaitingRoomController.
   */
  public ListUsersWaitingRoomController(Company company, EmployeeSession nurseSession)
      throws NotAuthorizedException {
    if (!nurseSession.hasCenter()) throw new NotAuthorizedException("Nurse is not logged in");
    this.nurseSession = nurseSession;
    this.vaccinationCenterStore = company.getVaccinationCenterStore();
  }

  public WaitingRoom getWaitingRoomListFromNurseCenter() {
    VaccinationCenterListDTO nurseVaccinationCenter = nurseSession.getVaccinationCenter();

    // TODO refactor using equals
    // TODO refactor using Waiting Room DTO
    return vaccinationCenterStore.getWaitingRoom(nurseVaccinationCenter.getPhone());
  }
}