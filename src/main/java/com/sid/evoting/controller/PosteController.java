package com.sid.evoting.controller;

import com.sid.evoting.entities.Poste;
import com.sid.evoting.metiers.interfaces.IPoste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PosteController {
	@Autowired
	private IPoste iPoste;
	
	@RequestMapping(value="/formPoste",method=RequestMethod.GET)
	public String formPoste(Model model) {
		model.addAttribute("poste", new Poste());
		return "formPoste";
	}
	@RequestMapping(value="/savePoste",method=RequestMethod.POST)
	public String savePoste(Model model, @Valid Poste poste, BindingResult binding) {
		if(binding.hasErrors()) {
			return "postes";
		}
		iPoste.savePoste(poste);
		return "redirect:/postes";
	}
	
	@RequestMapping(value="/postes",method=RequestMethod.GET)
	public String listPoste(Model model) {
		List<Poste> postes=iPoste.listPoste();
		model.addAttribute("postes", postes);
		return "postes";
	}
	
   @RequestMapping(value="/getPoste",method=RequestMethod.GET)
   @ResponseBody
   public Poste getPoste(Long id) {
	   return iPoste.getPoste(id);
   }
}
