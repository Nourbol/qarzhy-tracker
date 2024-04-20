package kz.edu.astanait.qarzhytracker.controller;

import kz.edu.astanait.qarzhytracker.domain.BankStatementType;
import kz.edu.astanait.qarzhytracker.service.BankStatementReader;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class DemoController {

    private final BankStatementReader bankStatementReader;

    @GetMapping("/upload-form")
    public String showUploadForm() {
        return "uploadForm";
    }

    @PostMapping("/upload-file")
    @SneakyThrows
    public String handleFileUpload(final @RequestParam("file") MultipartFile file,
                                   final Model model) {
        var extractedFinances = bankStatementReader.read(file, BankStatementType.KASPI);
        model.addAttribute("financeList", extractedFinances);
        return "financeList";
    }
}
