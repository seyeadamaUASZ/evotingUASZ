package com.sid.evoting.controller;

import com.sid.evoting.dao.ElecteurRepository;
import com.sid.evoting.entities.Electeur;
import com.sid.evoting.metiers.interfaces.IElecteur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ElecteurController {
	@Autowired
	private IElecteur electeurM;
	@Autowired
	private ElecteurRepository elect;

	@RequestMapping(value="/electeurs",method=RequestMethod.GET)
	public String listElecteur(Model model,@RequestParam(name="page" ,defaultValue = "0")int page,
                               @RequestParam(name="size",defaultValue = "4") int size,
                               @RequestParam(name="mc",defaultValue = "")String mc) {
		Page<Electeur> electeurs=elect.listPages("%"+mc+"%",new PageRequest(page,size));

		int [] pages= new int[electeurs.getTotalPages()];
        model.addAttribute("electeurs", electeurs.getContent());
        model.addAttribute("pages",page);
        model.addAttribute("size",size);
        model.addAttribute("pages",pages);
        model.addAttribute("pageCourante",page);
        model.addAttribute("mc",mc);
		return "electeurs";
	}
	
	@RequestMapping(value="/getElecteur",method=RequestMethod.GET)
    @ResponseBody
	public Electeur getElecteur(Long id,Model model) {
	    return electeurM.getElecteur(id);


		
	}
    @GetMapping("/getElecteurDep")
    @ResponseBody
	public List<Electeur> getElecteurDep(Model model){
	    return electeurM.listElecteurDeps();
    }
    @GetMapping("/nombreVote")
    @ResponseBody
    public Integer nomBreElecteurV(Model model){
	   return electeurM.nombreElecteurVoter();
    }

    @GetMapping("/nombreVoteN")
    @ResponseBody
    public Integer nomBreElecteurVN(Model model){
        return electeurM.nombreElecteurVoterN();
    }
    @GetMapping("/pourcentageVote")
    @ResponseBody
    public Integer evolutionVote(){
	    return (electeurM.nombreElecteurVoter())/(electeurM.nombreTotalElecteurs()) *100;
    }
    @GetMapping("/pourcentageVoteN")
    @ResponseBody
    public Integer evolutionVoteN(){
	    return ((electeurM.nombreElecteurVoterN()/electeurM.nombreTotalElecteurs()))*100;
    }

    //les electeurs avec pageable
    /*@GetMapping("/electeurs")
    public String electeursPage(Model model,@RequestParam(defaultValue = "0") int page){
	    model.addAttribute("electeurs",repository.findAll(new PageRequest(page,4)));
	    model.addAttribute("currentPage",page);
	    return "electeurs";
    }*/

}
