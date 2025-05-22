package com.marpe.cht.resources;

import java.io.ByteArrayInputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marpe.cht.entities.Colaborador;
import com.marpe.cht.entities.OS;
import com.marpe.cht.entities.OSColab;
import com.marpe.cht.services.ReportService;
import com.marpe.cht.services.export.ExcelService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/report")
public class ReportResource {

	DateTimeFormatter dtfmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private ExcelService excelService;
	
	@GetMapping(value="/oscolabPorPeriodo")
	public ResponseEntity<List<OSColab>> OSColabPorPeriodo(@RequestParam String startDate, @RequestParam String endDate, @RequestParam Boolean todosPagos) {
		List<OSColab> obj = reportService.OSColabPorPeriodo(startDate, endDate, todosPagos);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/oscolabSomadoPorPeriodo")
	public ResponseEntity<ByteArrayInputStream> OSColabSomadoPorPeriodo(@RequestParam String startDate, @RequestParam String endDate, @RequestParam Boolean todosPagos) {
		ByteArrayInputStream obj = excelService.OSColabSomadoPorPeriodo(startDate, endDate, todosPagos);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/topFiveOscolabSomadoPorPeriodo")
	public ResponseEntity<List<Object[]>> OSColabPorPeriodoEStatus(@RequestParam String startDate, @RequestParam String endDate) {
		List<Object[]> obj = reportService.topFiveOscolabSomadoPorPeriodo(startDate, endDate);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/os/desc5")
	public ResponseEntity<List<OS>> findAllOSDescOrder5() {
		List<OS> list = reportService.listOSDesc5();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/oscolab/desc5")
	public ResponseEntity<List<OSColab>> findAllOSColabDescOrder5() {
		List<OSColab> list = reportService.listOSColabDesc5();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/colaboradores/desc5")
	public ResponseEntity<List<Colaborador>> findAllColaboradorDescOrder5() {
		List<Colaborador> list = reportService.listColaboradorDesc5();
		return ResponseEntity.ok().body(list);
	}
	

	
}
