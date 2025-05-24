package com.marpe.cht.utils;

import java.time.Duration;

import com.marpe.cht.entities.Atividade;
import com.marpe.cht.entities.Order;

public class CalculadoraHoras {

	public void calcularHorasAPagar(Atividade atividade, Order order) {
		
		Duration periodo = Duration.between(atividade.getHoraInicial(), atividade.getHoraFinal());
		Double totalHorasDiurnas = 0.0;
		Double totalHorasNoturnas = 0.0;
		Double totalDeHoras = 0.0;
		Double minutos = (double) periodo.toMinutes();
		
		if(minutos < 0) {
			minutos += 1440;
		}

		if((minutos % 60) == 0) {
			totalDeHoras = (minutos / 60);
		} else if((minutos % 60) <= 30) {
			totalDeHoras = (int) (minutos / 60) + 0.5;
		} else {
			totalDeHoras = (int) (minutos / 60) + 1.0;
		}
		
		switch(atividade.getHoraInicial().getHour()) {
		
			case 0:
				if(totalDeHoras >= 5) {
					totalHorasNoturnas = 5.0;
					totalHorasDiurnas = (totalDeHoras - totalHorasNoturnas);
					
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					} else {
						if(atividade.getIntervalo()) {
							totalHorasDiurnas = ((totalDeHoras - totalHorasNoturnas) - 1.0);
							if((totalHorasDiurnas + totalHorasNoturnas < order.getRegional().getHorasPadrao())) {
								totalHorasDiurnas = order.getRegional().getHorasPadrao() - totalHorasNoturnas;
							}
							if(totalHorasDiurnas < 0.0) {
								totalHorasDiurnas = 0.0;
							}
						}
					}
					break;
				} else {
					totalHorasNoturnas = totalDeHoras;
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas = (order.getRegional().getHorasPadrao() - totalDeHoras);
					break;
					}
				}
				
			case 1:
				if(totalDeHoras >= 4) {
					totalHorasNoturnas = 4.0;
					totalHorasDiurnas = (totalDeHoras - totalHorasNoturnas);
					
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					} else {
						if(atividade.getIntervalo()) {
							totalHorasDiurnas = ((totalDeHoras - totalHorasNoturnas) - 1.0);
							if((totalHorasDiurnas + totalHorasNoturnas < order.getRegional().getHorasPadrao())) {
								totalHorasDiurnas = order.getRegional().getHorasPadrao() - totalHorasNoturnas;
							}
							if(totalHorasDiurnas < 0.0) {
								totalHorasDiurnas = 0.0;
							}
						}
					}
					break;
				} else {
					totalHorasNoturnas = totalDeHoras;
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas = (order.getRegional().getHorasPadrao() - totalDeHoras);
					break;
					}
				}
				
			case 2:
				if(totalDeHoras >= 3) {
					totalHorasNoturnas = 3.0;
					totalHorasDiurnas = (totalDeHoras - totalHorasNoturnas);
					
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					} else {
						if(atividade.getIntervalo()) {
							totalHorasDiurnas = ((totalDeHoras - totalHorasNoturnas) - 1.0);
							if((totalHorasDiurnas + totalHorasNoturnas < order.getRegional().getHorasPadrao())) {
								totalHorasDiurnas = order.getRegional().getHorasPadrao() - totalHorasNoturnas;
							}
							if(totalHorasDiurnas < 0.0) {
								totalHorasDiurnas = 0.0;
							}
						}
					}
					break;
				} else {
					totalHorasNoturnas = totalDeHoras;
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas = (order.getRegional().getHorasPadrao() - totalDeHoras);
					break;
					}
				}
				
			case 3:
				if(totalDeHoras >= 2) {
					totalHorasNoturnas = 2.0;
					totalHorasDiurnas = (totalDeHoras - totalHorasNoturnas);
					
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					} else {
						if(atividade.getIntervalo()) {
							totalHorasDiurnas = ((totalDeHoras - totalHorasNoturnas) - 1.0);
							if((totalHorasDiurnas + totalHorasNoturnas < order.getRegional().getHorasPadrao())) {
								totalHorasDiurnas = order.getRegional().getHorasPadrao() - totalHorasNoturnas;
							}
							if(totalHorasDiurnas < 0.0) {
								totalHorasDiurnas = 0.0;
							}
						}
					}
					break;
				} else {
					totalHorasNoturnas = totalDeHoras;
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas = (order.getRegional().getHorasPadrao() - totalDeHoras);
					break;
					}
				}
				
			case 4:
				if(totalDeHoras >= 1) {
					totalHorasNoturnas = 1.0;
					totalHorasDiurnas = (totalDeHoras - totalHorasNoturnas);
					
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					} else {
						if(atividade.getIntervalo()) {
							totalHorasDiurnas = ((totalDeHoras - totalHorasNoturnas) - 1.0);
							if((totalHorasDiurnas + totalHorasNoturnas < order.getRegional().getHorasPadrao())) {
								totalHorasDiurnas = order.getRegional().getHorasPadrao() - totalHorasNoturnas;
							}
							if(totalHorasDiurnas < 0.0) {
								totalHorasDiurnas = 0.0;
							}
						}
					}
					break;
				} else {
					totalHorasNoturnas = totalDeHoras;
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas = (order.getRegional().getHorasPadrao() - totalDeHoras);
					break;
					}
				}
				
			case 5:
				if(totalDeHoras >= 18) {
					totalHorasDiurnas = 17.0;
					totalHorasNoturnas = totalDeHoras - totalHorasDiurnas;
					
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					} else {
						if(atividade.getIntervalo()) {
							if(totalHorasDiurnas > 0) {
								totalHorasDiurnas -= 1.0;
							}
						}
						totalDeHoras = totalHorasDiurnas + totalHorasNoturnas;
						if(totalDeHoras < order.getRegional().getHorasPadrao()) {
							totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
						}
						break;
					}
					break;
				} else {
					totalHorasDiurnas = totalDeHoras;
					if(atividade.getIntervalo()) {
						if(totalHorasDiurnas > 0) {
							totalHorasDiurnas -= 1.0;
						}
					}
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas = (order.getRegional().getHorasPadrao() - totalDeHoras);
					}
					break;
				}
				
			case 6:
				if(totalDeHoras >= 17) {
					totalHorasDiurnas = 16.0;
					totalHorasNoturnas = totalDeHoras - totalHorasDiurnas;

					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					} else {
						if(atividade.getIntervalo()) {
							if(totalHorasDiurnas > 0) {
								totalHorasDiurnas -= 1.0;
							}
						}
						totalDeHoras = totalHorasDiurnas + totalHorasNoturnas;
						if(totalDeHoras < order.getRegional().getHorasPadrao()) {
							totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
						}
						break;
					}
					break;
				} else {
					totalHorasDiurnas = totalDeHoras;
					if(atividade.getIntervalo()) {
						if(totalHorasDiurnas > 0) {
							totalHorasDiurnas -= 1.0;
						}
					}
					totalDeHoras = totalHorasDiurnas + totalHorasNoturnas;
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					}
					break;
				}
				
			case 7:
				if(totalDeHoras >= 16) {
					totalHorasDiurnas = 15.0;
					totalHorasNoturnas = totalDeHoras - totalHorasDiurnas;
					
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					} else {
						if(atividade.getIntervalo()) {
							if(totalHorasDiurnas > 0) {
								totalHorasDiurnas -= 1.0;
							}
						}
						totalDeHoras = totalHorasDiurnas + totalHorasNoturnas;
						if(totalDeHoras < order.getRegional().getHorasPadrao()) {
							totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
						}
						break;
					}
					break;
				} else {
					totalHorasDiurnas = totalDeHoras;
					if(atividade.getIntervalo()) {
						if(totalHorasDiurnas > 0) {
							totalHorasDiurnas -= 1.0;
						}
					}
					totalDeHoras = totalHorasDiurnas + totalHorasNoturnas;
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					}
					break;
				}
				
			case 8:
				if(totalDeHoras >= 15) {
					totalHorasDiurnas = 14.0;
					totalHorasNoturnas = totalDeHoras - totalHorasDiurnas;

					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					} else {
						if(atividade.getIntervalo()) {
							if(totalHorasDiurnas > 0) {
								totalHorasDiurnas -= 1.0;
							}
						}
						totalDeHoras = totalHorasDiurnas + totalHorasNoturnas;
						if(totalDeHoras < order.getRegional().getHorasPadrao()) {
							totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
						}
						break;
					}
					break;
				} else {
					totalHorasDiurnas = totalDeHoras;
					if(atividade.getIntervalo()) {
						if(totalHorasDiurnas > 0) {
							totalHorasDiurnas -= 1.0;
						}
					}
					totalDeHoras = totalHorasDiurnas + totalHorasNoturnas;
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					}
					break;
				}
				
			case 9:
				if(totalDeHoras >= 14) {
					totalHorasDiurnas = 13.0;
					totalHorasNoturnas = totalDeHoras - totalHorasDiurnas;

					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					} else {
						if(atividade.getIntervalo()) {
							if(totalHorasDiurnas > 0) {
								totalHorasDiurnas -= 1.0;
							}
						}
						totalDeHoras = totalHorasDiurnas + totalHorasNoturnas;
						if(totalDeHoras < order.getRegional().getHorasPadrao()) {
							totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
						}
						break;
					}
					break;
				} else {
					totalHorasDiurnas = totalDeHoras;
					if(atividade.getIntervalo()) {
						if(totalHorasDiurnas > 0) {
							totalHorasDiurnas -= 1.0;
						}
					}
					totalDeHoras = totalHorasDiurnas + totalHorasNoturnas;
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					}
					break;
				}
				
			case 10:
				if(totalDeHoras >= 13) {
					totalHorasDiurnas = 12.0;
					totalHorasNoturnas = totalDeHoras - totalHorasDiurnas;

					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					} else {
						if(atividade.getIntervalo()) {
							if(totalHorasDiurnas > 0) {
								totalHorasDiurnas -= 1.0;
							}
						}
						totalDeHoras = totalHorasDiurnas + totalHorasNoturnas;
						if(totalDeHoras < order.getRegional().getHorasPadrao()) {
							totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
						}
						break;
					}
					break;
				} else {
					totalHorasDiurnas = totalDeHoras;
					if(atividade.getIntervalo()) {
						if(totalHorasDiurnas > 0) {
							totalHorasDiurnas -= 1.0;
						}
					}
					totalDeHoras = totalHorasDiurnas + totalHorasNoturnas;
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					}
					break;
				}
				
			case 11:
				if(totalDeHoras >= 12) {
					totalHorasDiurnas = 11.0;
					totalHorasNoturnas = totalDeHoras - totalHorasDiurnas;

					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					} else {
						if(atividade.getIntervalo()) {
							if(totalHorasDiurnas > 0) {
								totalHorasDiurnas -= 1.0;
							}
						}
						totalDeHoras = totalHorasDiurnas + totalHorasNoturnas;
						if(totalDeHoras < order.getRegional().getHorasPadrao()) {
							totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
						}
						break;
					}
					break;
				} else {
					totalHorasDiurnas = totalDeHoras;
					if(atividade.getIntervalo()) {
						if(totalHorasDiurnas > 0) {
							totalHorasDiurnas -= 1.0;
						}
					}
					totalDeHoras = totalHorasDiurnas + totalHorasNoturnas;
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					}
					break;
				}
				
			case 12:
				if(totalDeHoras >= 11) {
					totalHorasDiurnas = 10.0;
					totalHorasNoturnas = totalDeHoras - totalHorasDiurnas;

					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					} else {
						if(atividade.getIntervalo()) {
							if(totalHorasDiurnas > 0) {
								totalHorasDiurnas -= 1.0;
							}
						}
						totalDeHoras = totalHorasDiurnas + totalHorasNoturnas;
						if(totalDeHoras < order.getRegional().getHorasPadrao()) {
							totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
						}
						break;
					}
					break;
				} else {
					totalHorasDiurnas = totalDeHoras;
					if(atividade.getIntervalo()) {
						if(totalHorasDiurnas > 0) {
							totalHorasDiurnas -= 1.0;
						}
					}
					totalDeHoras = totalHorasDiurnas + totalHorasNoturnas;
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					}
					break;
				}
				
			case 13:
				if(totalDeHoras >= 10) {
					totalHorasDiurnas = 9.0;
					totalHorasNoturnas = totalDeHoras - totalHorasDiurnas;
					
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					} else {
						if(atividade.getIntervalo()) {
							if(totalHorasDiurnas > 0) {
								totalHorasDiurnas -= 1.0;
							}
						}
						totalDeHoras = totalHorasDiurnas + totalHorasNoturnas;
						if(totalDeHoras < order.getRegional().getHorasPadrao()) {
							totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
						}
						break;
					}
					break;
				} else {
					totalHorasDiurnas = totalDeHoras;
					if(atividade.getIntervalo()) {
						if(totalHorasDiurnas > 0) {
							totalHorasDiurnas -= 1.0;
						}
					}
					totalDeHoras = totalHorasDiurnas + totalHorasNoturnas;
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					}
					break;
				}
				
			case 14:
				if(totalDeHoras >= 9) {
					totalHorasDiurnas = 8.0;
					totalHorasNoturnas = totalDeHoras - totalHorasDiurnas;
					
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					} else {
						if(atividade.getIntervalo()) {
							if(totalHorasDiurnas > 0) {
								totalHorasDiurnas -= 1.0;
							}
						}
						totalDeHoras = totalHorasDiurnas + totalHorasNoturnas;
						if(totalDeHoras < order.getRegional().getHorasPadrao()) {
							totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
						}
						break;
					}
					break;
				} else {
					totalHorasDiurnas = totalDeHoras;
					if(atividade.getIntervalo()) {
						if(totalHorasDiurnas > 0) {
							totalHorasDiurnas -= 1.0;
						}
					}
					totalDeHoras = totalHorasDiurnas + totalHorasNoturnas;
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					}
					break;
				}
				
			case 15:
				if(totalDeHoras >= 8) {
					totalHorasDiurnas = 7.0;
					totalHorasNoturnas = totalDeHoras - totalHorasDiurnas;

					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					} else {
						if(atividade.getIntervalo()) {
							if(totalHorasDiurnas > 0) {
								totalHorasDiurnas -= 1.0;
							}
						}
						totalDeHoras = totalHorasDiurnas + totalHorasNoturnas;
						if(totalDeHoras < order.getRegional().getHorasPadrao()) {
							totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
						}
						break;
					}
					break;
				} else {
					totalHorasDiurnas = totalDeHoras;
					if(atividade.getIntervalo()) {
						if(totalHorasDiurnas > 0) {
							totalHorasDiurnas -= 1.0;
						}
					}
					totalDeHoras = totalHorasDiurnas + totalHorasNoturnas;
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					}
					break;
				}
				
			case 16:
				if(totalDeHoras >= 7) {
					totalHorasDiurnas = 6.0;
					totalHorasNoturnas = totalDeHoras - totalHorasDiurnas;
					
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					} else {
						if(atividade.getIntervalo()) {
							if(totalHorasDiurnas > 0) {
								totalHorasDiurnas -= 1.0;
							}
						}
						totalDeHoras = totalHorasDiurnas + totalHorasNoturnas;
						if(totalDeHoras < order.getRegional().getHorasPadrao()) {
							totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
						}
						break;
					}
					break;
				} else {
					totalHorasDiurnas = totalDeHoras;
					if(atividade.getIntervalo()) {
						if(totalHorasDiurnas > 0) {
							totalHorasDiurnas -= 1.0;
						}
					}
					totalDeHoras = totalHorasDiurnas + totalHorasNoturnas;
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					}
					break;
				}
				
			case 17:
				if(totalDeHoras >= 6) {
					totalHorasDiurnas = 5.0;
					totalHorasNoturnas = totalDeHoras - totalHorasDiurnas;

					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					} else {
						if(atividade.getIntervalo()) {
							if(totalHorasDiurnas > 0) {
								totalHorasDiurnas -= 1.0;
							}
						}
						totalDeHoras = totalHorasDiurnas + totalHorasNoturnas;
						if(totalDeHoras < order.getRegional().getHorasPadrao()) {
							totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
						}
						break;
					}
					break;
				} else {
					totalHorasDiurnas = totalDeHoras;
					if(atividade.getIntervalo()) {
						if(totalHorasDiurnas > 0) {
							totalHorasDiurnas -= 1.0;
						}
					}
					totalDeHoras = totalHorasDiurnas + totalHorasNoturnas;
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					}
					break;
				}
				
			case 18:
				if(totalDeHoras >= 5) {
					totalHorasDiurnas = 4.0;
					totalHorasNoturnas = totalDeHoras - totalHorasDiurnas;

					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					} else {
						if(atividade.getIntervalo()) {
							if(totalHorasDiurnas > 0) {
								totalHorasDiurnas -= 1.0;
							}
						}
						totalDeHoras = totalHorasDiurnas + totalHorasNoturnas;
						if(totalDeHoras < order.getRegional().getHorasPadrao()) {
							totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
						}
						break;
					}
					break;
				} else {
					totalHorasDiurnas = totalDeHoras;
					if(atividade.getIntervalo()) {
						if(totalHorasDiurnas > 0) {
							totalHorasDiurnas -= 1.0;
						}
					}
					totalDeHoras = totalHorasDiurnas + totalHorasNoturnas;
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					}
					break;
				}
				
			case 19:
				if(totalDeHoras >= 4) {
					totalHorasDiurnas = 3.0;
					totalHorasNoturnas = totalDeHoras - totalHorasDiurnas;

					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					} else {
						if(atividade.getIntervalo()) {
							if(totalHorasDiurnas > 0) {
								totalHorasDiurnas -= 1.0;
							}
						}
						totalDeHoras = totalHorasDiurnas + totalHorasNoturnas;
						if(totalDeHoras < order.getRegional().getHorasPadrao()) {
							totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
						}
						break;
					}
					break;
				} else {
					totalHorasDiurnas = totalDeHoras;
					if(atividade.getIntervalo()) {
						if(totalHorasDiurnas > 0) {
							totalHorasDiurnas -= 1.0;
						}
					}
					totalDeHoras = totalHorasDiurnas + totalHorasNoturnas;
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					}
					break;
				}
				
			case 20:
				if(totalDeHoras >= 3) {
					totalHorasDiurnas = 2.0;
					totalHorasNoturnas = totalDeHoras - totalHorasDiurnas;
					
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					} else {
						if(atividade.getIntervalo()) {
							if(totalHorasDiurnas > 0) {
								totalHorasDiurnas -= 1.0;
							}
						}
						totalDeHoras = totalHorasDiurnas + totalHorasNoturnas;
						if(totalDeHoras < order.getRegional().getHorasPadrao()) {
							totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
						}
						break;
					}
					break;
				} else {
					totalHorasDiurnas = totalDeHoras;
					if(atividade.getIntervalo()) {
						if(totalHorasDiurnas > 0) {
							totalHorasDiurnas -= 1.0;
						}
					}
					totalDeHoras = totalHorasDiurnas + totalHorasNoturnas;
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					}
					break;
				}
				
			case 21:
				if(totalDeHoras >= 2) {
					totalHorasDiurnas = 1.0;
					totalHorasNoturnas = totalDeHoras - totalHorasDiurnas;
					
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					} else {
						if(atividade.getIntervalo()) {
							if(totalHorasDiurnas > 0) {
								totalHorasDiurnas -= 1.0;
							}
						}
						totalDeHoras = totalHorasDiurnas + totalHorasNoturnas;
						if(totalDeHoras < order.getRegional().getHorasPadrao()) {
							totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
						}
						break;
					}
					break;
				} else {
					totalHorasDiurnas = totalDeHoras;
					if(atividade.getIntervalo()) {
						if(totalHorasDiurnas > 0) {
							totalHorasDiurnas -= 1.0;
						}
					}
					totalDeHoras = totalHorasDiurnas + totalHorasNoturnas;
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					}
					break;
				}
				
			case 22:
				if(totalDeHoras >= 7) {
					totalHorasNoturnas = 7.0;
					totalHorasDiurnas = (totalDeHoras - totalHorasNoturnas);
					
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					} else {
						if(atividade.getIntervalo()) {
							totalHorasDiurnas = ((totalDeHoras - totalHorasNoturnas) - 1.0);
							if(totalHorasDiurnas < 0.0) {
								totalHorasDiurnas = 0.0;
								totalHorasNoturnas -= 1.0;
							}
						}
					}
					break;
				} else {
					totalHorasNoturnas = totalDeHoras;
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas = (order.getRegional().getHorasPadrao() - totalDeHoras);
					break;
					}
				}
				
			case 23:
				if(totalDeHoras >= 6) {
					totalHorasNoturnas = 6.0;
					totalHorasDiurnas = (totalDeHoras - totalHorasNoturnas);
					
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas += (order.getRegional().getHorasPadrao() - totalDeHoras);
					} else {
						if(atividade.getIntervalo()) {
							totalHorasDiurnas = ((totalDeHoras - totalHorasNoturnas) - 1.0);
							if(totalHorasDiurnas < 0.0) {
								totalHorasDiurnas = 0.0;
							}
						}
					}
					break;
				} else {
					totalHorasNoturnas = totalDeHoras;
					if(totalDeHoras < order.getRegional().getHorasPadrao()) {
						totalHorasDiurnas = (order.getRegional().getHorasPadrao() - totalDeHoras);
					break;
					}
				}
				
			default: //do nothing
					break;
		}
		
		atividade.setTotalHorasDiurnas(totalHorasDiurnas);
		atividade.setTotalHorasNoturnas(totalHorasNoturnas);
		
	}
	
}
