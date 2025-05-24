package com.marpe.cht.services.export;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marpe.cht.entities.Colaborador;
import com.marpe.cht.entities.Coordenador;
import com.marpe.cht.entities.Order;
import com.marpe.cht.entities.Atividade;
import com.marpe.cht.exceptions.InvalidRequestException;
import com.marpe.cht.export.helper.ExcelHelper;
import com.marpe.cht.repositories.ColaboradorRepository;
import com.marpe.cht.repositories.CoordenadorRepository;
import com.marpe.cht.repositories.AtividadeRepository;
import com.marpe.cht.repositories.OrderRepository;
import com.marpe.cht.repositories.ReportRepository;
import com.marpe.cht.repositories.UserRepository;

@Service
public class ExcelService {
	
  @Autowired
  UserRepository repository;
  
  @Autowired
  ColaboradorRepository colaboradoresRepository;
  
  @Autowired
  CoordenadorRepository coordenadorRepository;
  
  @Autowired
  OrderRepository osRepository;
  
  @Autowired
  AtividadeRepository oscolabRepository;
  
  @Autowired
  ReportRepository reportRepository;
  
  DateTimeFormatter dtfmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");

//  public ByteArrayInputStream listCoordenadores() {
//	    List<Coordenador> coordenadores = coordenadorRepository.findAll();
//
//	    ByteArrayInputStream in = ExcelHelper.coordenadoresToExcel(coordenadores);
//	    return in;
//	  }
//  
//  public ByteArrayInputStream listColaboradores() {
//	    List<Colaborador> colaboradores = colaboradoresRepository.findAll();
//	
//	    ByteArrayInputStream in = ExcelHelper.colaboradoresToExcel(colaboradores);
//	    return in;
//	  }
//  
//  public ByteArrayInputStream listOS() {
//	    List<OS> os = osRepository.findAll();
//
//	    ByteArrayInputStream in = ExcelHelper.osToExcel(os);
//	    return in;
//	  }
//  
//  public ByteArrayInputStream listOSColab() {
//	    List<OSColab> oscolab = oscolabRepository.findAll();
//
//	    ByteArrayInputStream in = ExcelHelper.oscolabToExcel(oscolab);
//	    return in;
//	  }
//  
//  public ByteArrayInputStream listColaboradoresPorCidade(String cidade) {
//	    List<Colaborador> colaboradores = colaboradoresRepository.findAll();
//	
//	    ByteArrayInputStream in = ExcelHelper.colaboradoresPorCidadeToExcel(colaboradores, cidade);
//	    return in;
//	  }
//  
//  public ByteArrayInputStream OSColabPorPeriodo(String startDate, String endDate, Boolean todosPagos) {
//	    
//		LocalDate DataInicio = LocalDate.parse(startDate, dtfmt);
//		LocalDate DataFim = LocalDate.parse(endDate, dtfmt);
//
//		List<OSColab> oscolab = oscolabRepository.findAll();
//		List<OSColab> filtradas = oscolab.stream()
//			.filter(x -> x.getOs().getDataInicio().isEqual(DataInicio) || x.getOs().getDataInicio().isAfter(DataInicio))
//			.filter(x -> x.getOs().getDataInicio().isEqual(DataFim) || x.getOs().getDataInicio().isBefore(DataFim))
//			.filter(x -> x.getOs().getConcluida().equals(true))
//			.filter(x -> x.getPago().equals(todosPagos))
//			.collect(Collectors.toList());
//		
//		if(filtradas.isEmpty()) {
//			throw new InvalidRequestException("Não há atividades no período selecionado.");
//		} else {
//			ByteArrayInputStream in = ExcelHelper.oscolabPorPeriodo(filtradas);
//			return in; 
//		}
//  }
//
//  public ByteArrayInputStream OSColabSomadoPorPeriodo(String startDate, String endDate, Boolean todosPagos) {
//	    
//		LocalDate DataInicio = LocalDate.parse(startDate, dtfmt);
//		LocalDate DataFim = LocalDate.parse(endDate, dtfmt);
//	  
//		List<OSColab> oscolab = oscolabRepository.findAll();
//		List<OSColab> filtradas = oscolab.stream()
//			.filter(x -> x.getOs().getDataInicio().isEqual(DataInicio) || x.getOs().getDataInicio().isAfter(DataInicio))
//			.filter(x -> x.getOs().getDataInicio().isEqual(DataFim) || x.getOs().getDataInicio().isBefore(DataFim))
//			.filter(x -> x.getOs().getConcluida().equals(true))
//			.filter(x -> x.getPago().equals(todosPagos))
//			.collect(Collectors.toList());
//
//		Map<Long, Double> result = filtradas.stream()
//				.collect(Collectors.toMap(
//						x -> x.getColaborador().getId(),
//				        x -> x.getTotalAReceber().doubleValue(),
//				        		Double::sum));
//			
//		List<Colaborador> colaboradores = colaboradoresRepository.findAll();
//		if(result.isEmpty()) {
//			throw new InvalidRequestException("Não há atividades no período selecionado.");
//		} else {
//			ByteArrayInputStream in = ExcelHelper.oscolabSomadoPorPeriodo(colaboradores, result);
//			return in; 
//		}
//  }

}
