package com.sid.evoting.controller;

import com.sid.evoting.entities.Superviseur;
import com.sid.evoting.metiers.interfaces.IAccountService;
import com.sid.evoting.metiers.interfaces.ISuperviseur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SuperviseurController {
	@Autowired
	private ISuperviseur iSuperviseur;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private IAccountService iAccountService;
	
	@RequestMapping(value="/superviseurs",method=RequestMethod.GET)
	public String listsuperviseur(Model model) {
		List<Superviseur> superviseurs=iSuperviseur.listSuperviseur();
		model.addAttribute("superviseurs", superviseurs);
		return "superviseur";
	}

	//pour sauvegarder les infos superviseur
	@PostMapping("/saveSuper")
	public String saveSuperviseur(Model model, @Valid Superviseur superviseur){
		  String passH = encoder.encode(superviseur.getPassword());
		  superviseur.setPassword(passH);
		  superviseur.setActive(true);
          iSuperviseur.saveSuperviseur(superviseur);
          iAccountService.addRoleToUser(superviseur.getUsername(),"SUPERVISEUR");
          return "redirect:/menu_superviseur";
	}

	

}
