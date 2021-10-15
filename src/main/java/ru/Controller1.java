package ru;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
public class Controller1 {

    @PostMapping(value = "/ru/export", produces = "application/zip")
    public ResponseEntity<ByteArrayResource> export(@ModelAttribute("requestDTO") RequestDTO requestDTO, Model model) {
        try {
            MultiValueMap<String, String> heders = new HttpHeaders();
            heders.add("Content-Type", "application/zip");
            heders.add("Content-Disposition", "attachment; filename=\"zipoch.zip\"");
            return new ResponseEntity<>(new ByteArrayResource(exportZip()), heders, HttpStatus.OK);
        } catch (FileNotFoundException e) {
            System.out.println("Error in pFileNotFoundException");
        }
        return null;
    }

    @GetMapping(value = "/ru/export")
    public String export(Model model) {
        model.addAttribute(new RequestDTO());
        return "export";
    }

    @GetMapping(value = "/ru/same", produces = "application/zip")
    public void same(HttpServletResponse response) {

    }


    private byte[] exportZip() throws FileNotFoundException {
        File file = new File("/Users/pomidorka/Downloads/concept.docx");
        FileInputStream inputFile = new FileInputStream(file);
        //
        ByteArrayOutputStream outFileStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outFileStream, StandardCharsets.UTF_8);
        ZipEntry entry = new ZipEntry("concept.docx");
        try {
            zip.putNextEntry(entry);
        } catch (IOException e) {
            System.out.println("Error in put zip name");
        }
        try {
            zip.write(inputFile.readAllBytes());
        } catch (IOException e) {
            System.out.println("Error in put doc file");
        }

        try {
            zip.flush();
            zip.close();
            inputFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outFileStream.toByteArray();
    }
}
