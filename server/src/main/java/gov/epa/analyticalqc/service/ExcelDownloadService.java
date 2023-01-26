package gov.epa.analyticalqc.service;

import gov.epa.analyticalqc.dto.ExcelDownloadDto;
import gov.epa.analyticalqc.entity.Substance;
import gov.epa.analyticalqc.repository.CustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelDownloadService {
    @Autowired
    private CustomRepository repository;

    public List<ExcelDownloadDto> getSingleResult(String id) {

        List<Object[]> items = repository.getSingleResult(id);
        List<ExcelDownloadDto> customResponseList = new ArrayList();
        for (Object[] item: items) {
            ExcelDownloadDto s = new ExcelDownloadDto();
            s.setId((Integer) item[0]);
            s.setDtxsid(String.valueOf(item[1]));
            s.setCasrn(String.valueOf(item[2]));
            s.setPreferredName(String.valueOf(item[3]));
            s.setT0(String.valueOf(item[4]));
            s.setT4(String.valueOf(item[5]));
            s.setCall(String.valueOf(item[6]));


            customResponseList.add(s);
        }

        return customResponseList;
    }


    public List<ExcelDownloadDto> getAllResults(List<String> ids) {

        List<Object[]> items = repository.getAllResult(ids);
        List<ExcelDownloadDto> customResponseList = new ArrayList();
        for (Object[] item: items) {
            ExcelDownloadDto s = new ExcelDownloadDto();
            s.setId((Integer) item[0]);
            s.setDtxsid(String.valueOf(item[1]));
            s.setCasrn(String.valueOf(item[2]));
            s.setPreferredName(String.valueOf(item[3]));
            s.setT0(String.valueOf(item[4]));
            s.setT4(String.valueOf(item[5]));
            s.setCall(String.valueOf(item[6]));

            customResponseList.add(s);
        }

        return customResponseList;
    }


}
