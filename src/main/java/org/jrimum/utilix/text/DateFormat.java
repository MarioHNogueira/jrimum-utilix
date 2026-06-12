/*
 * Copyright 2010 JRimum Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * Created at: 01/08/2010 - 17:31:00
 * 
 * ================================================================================
 * 
 * Direitos autorais 2010 JRimum Project
 * 
 * Licenciado sob a Licença Apache, Versão 2.0 ("LICENÇA"); você não pode usar
 * esse arquivo exceto em conformidade com a esta LICENÇA. Você pode obter uma
 * cópia desta LICENÇA em http://www.apache.org/licenses/LICENSE-2.0 A menos que
 * haja exigência legal ou acordo por escrito, a distribuição de software sob
 * esta LICENÇA se dará “COMO ESTÁ”, SEM GARANTIAS OU CONDIÇÕES DE QUALQUER
 * TIPO, sejam expressas ou tácitas. Veja a LICENÇA para a redação específica a
 * reger permissões e limitações sob esta LICENÇA.
 * 
 * Criado em: 01/08/2010 - 17:31:00
 * 
 */

package org.jrimum.utilix.text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jrimum.utilix.Exceptions;

public enum DateFormat implements Format<Date, SimpleDateFormat>{

	DDMMYY("ddMMyy"),
	DDMMYY_B("dd/MM/yy"),
	DDMMYY_H("dd-MM-yy"),
	DDMMYY_U("dd_MM_yy"),
	DDMMYYYY("ddMMyyyy"),
	DDMMYYYY_B("dd/MM/yyyy"),
	DDMMYYYY_H("dd-MM-yyyy"),
	DDMMYYYY_U("dd_MM_yyyy"),
	YYMMDD("yyMMdd"),
	YYMMDD_B("yy/MM/dd"),
	YYMMDD_H("yy-MM-dd"),
	YYMMDD_U("yy_MM_dd"),
	YYYYMMDD("yyyyMMdd"),
	YYYYMMDD_B("yyyy/MM/dd"),
	YYYYMMDD_H("yyyy-MM-dd"),
	YYYYMMDD_U("yyyy_MM_dd"),
	HHMMSS("hhmmss"),
	HHMMSS_24("HHmmss"),
	HHMMSS_C("hh:mm:ss"),
	HHMMSS_24C("HH:mm:ss"),
	;
	
	private final ThreadLocalFormat<SimpleDateFormat> DATE_FORMAT;

	private DateFormat(String format) {
	
		DATE_FORMAT = new ThreadLocalFormat<SimpleDateFormat>(format){

			@Override
			protected SimpleDateFormat initialValue() {
				
		       return new SimpleDateFormat(format);
			}
	        
	    };
	}
	public String format(Date obj) {
	
		return DATE_FORMAT.get().format(obj);
	}
	public Date parse(String text) {
		
		try {
			
			return DATE_FORMAT.get().parse(text);
			
		} catch (ParseException e) {
			
			return Exceptions.throwIllegalArgumentException("DateFormat Exception!", e);
		}
	}

	public SimpleDateFormat copy(){
			
		return (SimpleDateFormat) DATE_FORMAT.get().clone();
	}
}
