package gov.epa.analyticalqc.controller;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import gov.epa.analyticalqc.dto.CreateSubstanceFilesRequest;
import gov.epa.analyticalqc.dto.DashboardResponse;
import gov.epa.analyticalqc.entity.File;
import gov.epa.analyticalqc.entity.ListSubstance;
import gov.epa.analyticalqc.entity.Substance;
import gov.epa.analyticalqc.entity.SubstanceFile;
import gov.epa.analyticalqc.repository.FileRepository;
import gov.epa.analyticalqc.repository.ListRepository;
import gov.epa.analyticalqc.repository.ListSubstanceRepository;
import gov.epa.analyticalqc.repository.SubstanceFileRepository;
import gov.epa.analyticalqc.repository.SubstanceRepository;
import gov.epa.analyticalqc.web_client.DashboardWebClient;

@RestController
@RequestMapping("/api/substance-files")
public class SubstanceFileController {
    
    @Autowired SubstanceRepository substanceRepository;
    @Autowired FileRepository fileRepository;
    @Autowired SubstanceFileRepository substanceFileRepository;
    @Autowired ListRepository listRepository;
    @Autowired ListSubstanceRepository listSubstanceRepository;

    private static final CSVParser parser = new CSVParserBuilder().withSeparator('\t').withIgnoreQuotations(true).build();
    @Autowired DashboardWebClient dashboardWebClient = new DashboardWebClient();

    @PostMapping()
    public ResponseEntity<String> createSubstanceFiles(@RequestBody CreateSubstanceFilesRequest request) {
        Boolean createSubstances = request.getCreateSubstances();
        String name = request.getName();
        gov.epa.analyticalqc.entity.List list = listRepository.findByName(name).orElse(null);
        if (list == null && name != null && !name.isBlank()) {
            String description = request.getDescription();
            list = listRepository.save(new gov.epa.analyticalqc.entity.List(null, name, description, true));
        }

        CSVReader reader = new CSVReaderBuilder(new StringReader(request.getFiles())).withCSVParser(parser).build();
        String[] record = null;
        try {
            while ((record = reader.readNext()) != null) {
                String dtxsid = record[0];
                String fileName = record[1];
                String note = record.length > 2 ? record[2] : null;

                Substance substance = substanceRepository.findByDtxsid(dtxsid).orElse(null);
                if (substance == null && !createSubstances) {
                    continue;
                } else if (substance == null) {
                    DashboardResponse[] responses = dashboardWebClient.get(dtxsid);
                    Substance newSubstance = null;
                    if (responses == null || responses.length == 0) {
                        newSubstance = new Substance(null, 
                            dtxsid, 
                            "NONAME_" + dtxsid, 
                            "NOCAS_" + dtxsid, 
                            null, 
                            null,
                            null,
                            null);
                    } else {
                        DashboardResponse response = responses[0];
                        Integer pubchemCidInt = response.getPubchemCid();
                        Long pubchemCid = null;
                        if (pubchemCidInt != null) {
                            pubchemCid = Long.valueOf(pubchemCidInt);
                        }
                        newSubstance = new Substance(null, 
                            response.getDtxsid(), 
                            response.getPreferredName(), 
                            response.getCasrn(), 
                            response.getMolFormula(), 
                            response.getAverageMass(),
                            pubchemCid,
                            null);
                    }
                    substance = substanceRepository.save(newSubstance);
                }

                File file = fileRepository.findByFileName(fileName).orElse(null);
                if (file == null) {
                    File newFile = new File(null, fileName, null);
                    file = fileRepository.save(newFile);
                }

                if (substance == null || file == null) {
                    continue;
                } else {
                    SubstanceFile substanceFile = substanceFileRepository.findBySubstanceDtxsidAndFileFileName(dtxsid, fileName).orElse(null);
                    if (substanceFile != null) {
                        substanceFile.setNote(note);
                        substanceFileRepository.save(substanceFile);
                    } else {
                        substanceFileRepository.save(new SubstanceFile(null, substance, file, note));
                    }

                    if (list != null && !listSubstanceRepository.existsByListIdAndSubstanceId(list.getId(), substance.getId())) {
                        listSubstanceRepository.save(new ListSubstance(null, substance, list));
                    }
                }
            }

            return new ResponseEntity<>("Created", HttpStatus.CREATED);
        } catch (CsvValidationException | IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid input");
        } 
    }

}