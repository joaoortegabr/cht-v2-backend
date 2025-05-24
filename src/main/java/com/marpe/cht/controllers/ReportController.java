package com.marpe.cht.controllers;

import java.io.ByteArrayInputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marpe.cht.entities.Colaborador;
import com.marpe.cht.entities.Order;
import com.marpe.cht.entities.Atividade;
import com.marpe.cht.services.ClienteService;
import com.marpe.cht.services.ReportService;
import com.marpe.cht.services.export.ExcelService;

@CrossOrigin
@RestController
@RequestMapping(value = "/report")
public class ReportController {

	private static final Logger log = LoggerFactory.getLogger(ReportController.class);
	DateTimeFormatter dtfmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final ReportService reportService;
	private final ExcelService excelService;
	
	public ReportController(ReportService reportService, ExcelService excelService) {
		super();
		this.reportService = reportService;
		this.excelService = excelService;
	}

	@GetMapping(value="/atividadePorPeriodo")
	public ResponseEntity<List<Atividade>> AtividadePorPeriodo(@RequestParam String startDate, @RequestParam String endDate, @RequestParam Boolean todosPagos) {
		List<Atividade> obj = reportService.AtividadePorPeriodo(startDate, endDate, todosPagos);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/atividadeSomadoPorPeriodo")
	public ResponseEntity<ByteArrayInputStream> AtividadeSomadoPorPeriodo(@RequestParam String startDate, @RequestParam String endDate, @RequestParam Boolean todosPagos) {
		ByteArrayInputStream obj = excelService.AtividadeSomadoPorPeriodo(startDate, endDate, todosPagos);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/topFiveAtividadeSomadoPorPeriodo")
	public ResponseEntity<List<Object[]>> AtividadePorPeriodoEStatus(@RequestParam String startDate, @RequestParam String endDate) {
		List<Object[]> obj = reportService.topFiveAtividadeSomadoPorPeriodo(startDate, endDate);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/os/desc5")
	public ResponseEntity<List<Order>> findAllOSDescOrder5() {
		List<Order> list = reportService.listOrderDesc5();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/atividade/desc5")
	public ResponseEntity<List<Atividade>> findAllAtividadeDescOrder5() {
		List<Atividade> list = reportService.listAtividadeDesc5();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/colaboradores/desc5")
	public ResponseEntity<List<Colaborador>> findAllColaboradorDescOrder5() {
		List<Colaborador> list = reportService.listColaboradorDesc5();
		return ResponseEntity.ok().body(list);
	}
	

	
}
