package com.surfersolution.notices.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surfersolution.notices.domain.Author;
import com.surfersolution.notices.domain.Notice;
import com.surfersolution.notices.repositories.AuthorRepository;
import com.surfersolution.notices.repositories.NoticeRepository;

@Service
public class DBService {

	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private NoticeRepository noticeRepository;
	
//	@Autowired
//	private ReaderRepository readerRepository;
	
	public void instantiateDb() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Author a1 = new Author(null, "Carl Brown", "carl@gmail.com");
		Author a2 = new Author(null, "Maria Green", "maria@gmail.com");
		Author a3 = new Author(null, "Ana Purple", "ana@gmail.com");
		
		Notice n1 = new Notice(null, "Nova Versão PHP", 
				"Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...",
				a1, sdf.parse("20/10/2017"));
		
		Notice n2 = new Notice(null, "IBM vende sua unidade de Watson Health de IA para fundo de investimentos", 
				"A IBM e Francisco Partners, uma empresa global de investimentos especializada em parcerias com empresas de tecnologia...",
				a1, sdf.parse("21/01/2022"));
		
		Notice n3 = new Notice(null, "Nova Versão JAVA", 
				"Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...",
				a2, sdf.parse("23/11/2019"));
		
		Notice n4 = new Notice(null, "Ferramenta Postman", 
				"Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...",
				a2, sdf.parse("27/08/2021"));
		
		Notice n5 = new Notice(null, "Typescript, o futuro do Javascript", 
				"Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...",
				a3, sdf.parse("09/09/2020"));
		
		Notice n6 = new Notice(null, "Netflix e suas tecnologias", 
				"Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...",
				a3, sdf.parse("15/04/2021"));
		
		a1.getNotices().addAll(Arrays.asList(n1, n2));
		a2.getNotices().addAll(Arrays.asList(n3, n4));
		a3.getNotices().addAll(Arrays.asList(n5, n6));
		
		authorRepository.saveAll(Arrays.asList(a1, a2, a3));
		noticeRepository.saveAll(Arrays.asList(n1, n2, n3, n4, n5, n6));
		
	}
}
