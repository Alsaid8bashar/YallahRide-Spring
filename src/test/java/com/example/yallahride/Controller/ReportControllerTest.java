package com.example.yallahride.Controller;

import com.example.yallahride.Entity.Report;
import com.example.yallahride.Service.Interface.ReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReportController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ReportControllerTest {

    @MockBean
    ReportService reportService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateReport() throws Exception {
        Report report = new Report("Report 1", "ride1 report");
        report.setId(1L);

        when(reportService.saveReport(report)).thenReturn(report);

        mockMvc.perform(MockMvcRequestBuilders.post("/report/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(report)))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1L))
//                .andExpect(jsonPath("$.title").value("Test Report"));
                .andDo(print());
    }


    @Test
    public void testUpdateReport() throws Exception {
        Report report = new Report("Report 1", "ride1 report");
        report.setId(1L);
        report.setTitle("Updated Report");

        when(reportService.saveReport(report)).thenReturn(report);

        mockMvc.perform(MockMvcRequestBuilders.put("/report/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(report)))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1L))
//                .andExpect(jsonPath("$.title").value("Updated Report"));
                .andDo(print());

    }


    @Test
    public void testFindReportById() throws Exception {
        Long reportId = 1L;

        Report report = new Report();
        report.setId(reportId);
        report.setTitle("Test Report");
        when(reportService.findReportById(reportId)).thenReturn(report);

        mockMvc.perform(MockMvcRequestBuilders.get("/report/{id}", reportId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(reportId))
                .andExpect(jsonPath("$.title").value("Test Report"))
                .andDo(print());

    }

    @Test
    public void testFindAllReports() throws Exception {
        java.util.List<Report> reports = Arrays.asList(
                new Report("Report 1", "ride1 report"),
                new Report("Report 2", "ride2 report")
        );
        when(reportService.findAllReports()).thenReturn(reports);

        mockMvc.perform(MockMvcRequestBuilders.get("/report/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Report 1"))
                .andExpect(jsonPath("$[0].description").value("ride1 report"))

                .andExpect(jsonPath("$[1].title").value("Report 2"))
                .andExpect(jsonPath("$[1].description").value("ride2 report"))
                .andDo(print());

    }

    @Test
    public void testFindUserReports() throws Exception {
        Long userId = 1L;

        java.util.List<Report> reports = Arrays.asList(
                new Report("Report 1", "ride1 report"),
                new Report("Report 2", "ride2 report")
        );
        when(reportService.findUserReports(userId)).thenReturn(reports);

        mockMvc.perform(MockMvcRequestBuilders.get("/report/user/reports")
                        .param("id", userId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Report 1"))
                .andExpect(jsonPath("$[0].description").value("ride1 report"))
                .andExpect(jsonPath("$[1].title").value("Report 2"))
                .andExpect(jsonPath("$[1].description").value("ride2 report"))
                .andDo(print());
    }

    @Test
    public void testDeleteReportById() throws Exception {
        Long reportId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/report/delete/{id}", reportId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());

        verify(reportService, times(1)).deleteReportById(reportId);
    }

    @Test
    public void testDeleteAllReports() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/report/delete/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());


        verify(reportService, times(1)).deleteAllReports();
    }

    @Test
    public void testGetReportsCount() throws Exception {
        Long count = 10L;
        when(reportService.getNumberOfReport()).thenReturn(count);

        mockMvc.perform(MockMvcRequestBuilders.get("/report/statistics")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(count))
                .andDo(print());
    }
}
