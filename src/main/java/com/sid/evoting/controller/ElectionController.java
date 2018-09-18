package com.sid.evoting.controller;

import com.sid.evoting.entities.Election;
import com.sid.evoting.entities.Poste;
import com.sid.evoting.entities.Superviseur;
import com.sid.evoting.metiers.interfaces.IElection;
import com.sid.evoting.metiers.interfaces.IPoste;
import com.sid.evoting.metiers.interfaces.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ElectionController {
	@Autowired
	private IElection electionM;
	@Autowired
	private IPoste iPoste;
	@Autowired
	private IUser iUser;
	@Autowired
	public JavaMailSender emailSender;
	
	@RequestMapping(value="/elections",method=RequestMethod.GET)
	public String listElection(Model model){
		List<Election> elections=electionM.listElection();
		List<Poste> postes = iPoste.listPoste();
		model.addAttribute("postes",postes);
		model.addAttribute("elections",elections);
		return "elections";
		
	}
	
	//formulaire election
	@RequestMapping(value="/FormElection",method=RequestMethod.GET)
	public String formElection(Model model) {
		model.addAttribute("election", new Election());
		return "FormElection";
	}
	
	@PostMapping("/saveElection")
	public String saveElection(@Valid Election election, HttpServletRequest request) {
		Superviseur superviseur= (Superviseur) iUser.userLogged(request);
		election.setSuperviseur(superviseur);
		if(election.isOuvert()){
			sendMail(request);
		}
		electionM.saveElection(election);
		return "redirect:/elections";
	}
	@RequestMapping(value="/getElection",method=RequestMethod.GET)
	@ResponseBody
	public Election getElection(Long id,Model model) {
		return electionM.getElection(id);
	}

	//activer une election c'est à dire l'ouvrir au niveau de la plateforme
	@PutMapping("/ouvrirElection")
	public String ouvrirElection(Long id,Election election,Model model){
		election.setOuvert(true);
		electionM.modifierElection(id,election);
		return "redirect:/elections";
	}

	public void sendMail(HttpServletRequest request){
		//create simple mail
		Superviseur superviseur = (Superviseur) iUser.userLogged(request);
		Election election = electionM.getElectionSuper(superviseur.getIdUser());
		String sendTo;
		if(election.getElecteurCible().equals("enseignant"))
		{
			sendTo ="khadim.drame@univ-zig.sn";
		}else if(election.getElecteurCible().equals("etudiant")){
			sendTo ="etudiants@zig.univ.sn";
		}else{
			sendTo ="Pats@zig.univ.sn";
		}
		SimpleMailMessage message=new SimpleMailMessage();
		message.setTo(sendTo);
		message.setSubject("ouverture de l'election du "+election.getPoste().getNomPoste());
		message.setText("l'election du "+ election.getPoste().getNomPoste()+" est ouvert, vous pouvez clicquer sur http://www.evote.zig pour deposer votre candidature, la date du scrutin est prevu le "+election.getDateElection());

		//send message
		this.emailSender.send(message);
	}
}
