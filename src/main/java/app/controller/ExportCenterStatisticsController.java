package app.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import app.domain.model.Company;
import app.domain.model.VaccinationCenter;
import app.exception.NotAuthorizedException;
import app.service.FileUtils;
import app.service.FullyVaccinatedData;
import app.session.EmployeeSession;

/**
 * ExportCenterStatisticsController
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class ExportCenterStatisticsController {
    private Company company;
    private EmployeeSession session;
    private FullyVaccinatedData exporter;
    private Map<Calendar, Integer> dataMap;
    private FileUtils fileUtils;

    /**
     * ExportCenterStatisticsController constructor
     * 
     * @param company the company
     * @param coordinatorSession the coordinator session
     * @throws NotAuthorizedException exception if coordinator is not logged in
     */
    public ExportCenterStatisticsController(Company company, EmployeeSession coordinatorSession) throws NotAuthorizedException {
        if (!coordinatorSession.hasCenter()) throw new NotAuthorizedException("Coordinator is not logged in.");
        this.session = coordinatorSession;
        this.company = company;
    }

    /**
     * Creates fullyVaccinatedData
     * 
     * @param filePath the file path to save the statistics
     * @param start the start date of statistics
     * @param end the end date of statistics
     * @return fullyVaccinatedData object
     */
    public FullyVaccinatedData createFullyVaccinatedData(String filePath, Calendar start, Calendar end) {
        VaccinationCenter center = session.getVaccinationCenter();

        exporter = new FullyVaccinatedData(filePath, start, end, center);

        return exporter;
    }

    /**
     * Gathers all the information needed to export to a file the statistics
     * 
     * @param exporter -> FullyVaccinatedData Object
     * @return hashMap will all the data needed
     */
    public Map<Calendar, Integer> generateFullyVaccinatedUsersInterval(FullyVaccinatedData exporter) {
        dataMap = exporter.getFullyVaccinatedUsersPerDayMap();
        return dataMap;
    }

    /**
     * Saves data
     * 
     * @param dataMap the hashMap will all information
     */
    public boolean saveData(String fileName, String content) {
        return fileUtils.writeToFile(fileName, content);
    }

    public String dataToString(Map<Calendar, Integer> data) {
        return exporter.toString(data);
    }

    public String exportFileString(Map<Calendar, Integer> data) {
        return exporter.toExportFileString(data);
    }
}