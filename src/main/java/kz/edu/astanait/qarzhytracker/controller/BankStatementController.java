package kz.edu.astanait.qarzhytracker.controller;

import kz.edu.astanait.qarzhytracker.domain.BankStatementType;
import kz.edu.astanait.qarzhytracker.domain.Finance;
import kz.edu.astanait.qarzhytracker.service.BankStatementReader;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/statements")
@RequiredArgsConstructor
public class BankStatementController {

    private final BankStatementReader bankStatementReader;

    @PostMapping(path = "/{type}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @SneakyThrows
    public List<Finance> uploadBankStatement(final @RequestParam("statement") MultipartFile statement,
                                             final @PathVariable("type") BankStatementType type) {
        return bankStatementReader.read(statement, type);
    }
}
