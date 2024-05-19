package kz.edu.astanait.qarzhytracker.service;

import kz.edu.astanait.qarzhytracker.domain.FinancialAdvice;
import kz.edu.astanait.qarzhytracker.domain.FinancialAdviceRequest;

import java.util.UUID;

public interface FinancialAdviser {

    FinancialAdvice askForAdvice(FinancialAdviceRequest request, UUID userId);
}
