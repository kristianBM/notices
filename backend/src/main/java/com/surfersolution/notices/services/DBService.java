package com.surfersolution.notices.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.surfersolution.notices.domain.Notice;
import com.surfersolution.notices.domain.User;
import com.surfersolution.notices.domain.enums.Profile;
import com.surfersolution.notices.repositories.NoticeRepository;
import com.surfersolution.notices.repositories.UserRepository;

@Service
public class DBService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private NoticeRepository noticeRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public void instantiateDb() throws ParseException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		User u1 = new User(null, "kristian@dev.com", "Kristian", pe.encode("123"));
		u1.addProfiles(Profile.ADMIN);
		u1.addProfiles(Profile.AUTHOR);
		User u2 = new User(null, "kristina@dev.com", "Kristina", pe.encode("123"));
		User u3 = new User(null, "teste@dev.com", "Teste", pe.encode("123"));
		User u4 = new User(null, "test@dev.com", "Test", pe.encode("123"));
		
		Notice n1 = new Notice(null, "News", "project is getting closer to the end", u1, sdf.parse("25/05/1997 10:15"));
		Notice n2 = new Notice(null, "News2", "tomorrow is a new day", u1, sdf.parse("05/08/2000 09:49"));
		Notice n3 = new Notice(null, "News3", "just trying to do better", u2, sdf.parse("31/10/2002 10:15"));
		Notice n4 = new Notice(null, "News", "just trying to make it better", u3, sdf.parse("07/08/1997 10:15"));
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3, u4));
		noticeRepository.saveAll(Arrays.asList(n1, n2, n3, n4));
	}
}
