package com.sid.evoting.controller;

import com.sid.evoting.dao.CandidatRepository;
import com.sid.evoting.dao.RoleRepository;
import com.sid.evoting.entities.*;
import com.sid.evoting.metiers.interfaces.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class CandidatController {
    @Autowired
	private IBulletin bulletinM;
	@Autowired
    private ICandidat candidatM;
    @Autowired
    private IElection iElection;
    @Autowired
    private IDepartement iDepartement;
    @Autowired
    private IFiliere iFiliere;
    @Autowired
    private INiveau iNiveau;
    @Autowired
    private IService iService;
    @Autowired
    private CandidatRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private IAccountService iAccountService;


	
   @RequestMapping(value="/candidats",method=RequestMethod.GET)
   public String listCandidat(Model model,@RequestParam(name="page",defaultValue = "0") int page,
                              @RequestParam(name="size",defaultValue = "2") int size,
                              @RequestParam(name="mc",defaultValue = "") String mc) {
	   Page<Candidat> candidats=repository.pageCandidats("%"+mc+"%",new PageRequest(page,size));
       int [] pages= new int[candidats.getTotalPages()];
	   model.addAttribute("candidats", candidats.getContent());
	   model.addAttribute("pages",pages);
	   model.addAttribute("pageCourante",page);
	   model.addAttribute("size",size);
	   model.addAttribute("mc",mc);
	   return "candidats";
   }
   
   @RequestMapping(value="saveCandidat",method=RequestMethod.POST)
   public String saveCandidat(Model model, @Valid Candidat c, BindingResult binding, MultipartFile file)
   throws IOException {
	   if(binding.hasErrors()) {
		   return "formCandidat";
	   }
	   if(!file.isEmpty()){
           BufferedImage bi=ImageIO.read(file.getInputStream());
           c.setPhoto(file.getBytes());
           c.setNomPhoto(file.getOriginalFilename());
       }
       Bulletin bulletin=new Bulletin();
	   bulletin.setCandidat(c);
	   c.setActive(true);
       String passH = encoder.encode(c.getPassword());
       c.setPassword(passH);
       candidatM.saveCandidat(c);
	   bulletinM.saveBulletin(bulletin);
       iAccountService.addRoleToUser(c.getUsername(),"CANDIDAT");
	   return "redirect:/Confirmation";
   }

   //saveFlush
    @PostMapping("/saveC")
    public String saveCandidatM(Model model,@Valid Candidat candidat,BindingResult result,MultipartFile file)
    throws Exception{
       if(result.hasErrors()){
           return "menu_candidat";
       }
        if(!file.isEmpty()){
            BufferedImage bi=ImageIO.read(file.getInputStream());
            candidat.setPhoto(file.getBytes());
            candidat.setNomPhoto(file.getOriginalFilename());
        }
           String passH=encoder.encode(candidat.getPassword());
           candidat.setPassword(passH);
           candidat.setActive(true);
           candidatM.updateCandidat(candidat.getIdUser(),candidat);
           iAccountService.addRoleToUser(candidat.getUsername(),"CANDIDAT");
           return "redirect:/menu_candidat";

    }

   //fichier image
    @RequestMapping(value = "photoCat",produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public byte[] photoCandidat(Long id)throws IOException{
       Candidat c=candidatM.getCandidat(id);
       return IOUtils.toByteArray(new ByteArrayInputStream(c.getPhoto()));
    }

   @RequestMapping(value="/getCandidat/{idUser}")
   public String getCandidat(Model model,@PathVariable("idUser") Long id) {
       Candidat candidat=candidatM.getCandidat(id);
       model.addAttribute("candidat",candidat);
       return "infosCandidat";
   }

   @RequestMapping(value="/formCandidat",method=RequestMethod.GET)
   public String formCandidat(Model model) {
       List<Election> elections=iElection.listElection();
       List<Departement> departements=iDepartement.listDepartement();
	   model.addAttribute("candidat", new Candidat());
	   model.addAttribute("elections",elections);
	   model.addAttribute("departements",departements);
	   return "formCandidat";
   }



   
   @RequestMapping(value="/deleteCandidat",method=RequestMethod.GET)
   public String deleteCandidat(Model model, Long id) {
	   candidatM.deleteCandidat(id);
	   return "redirect:/candidats";
   }

   //delete candidat à partir de son profil
    @DeleteMapping("/delete")
    public String delete(Model model,Long id){
       candidatM.deleteCandidat(id);
       return "redirect:/login";
    }

   //redirect confirmation
    @GetMapping("/Confirmation")
    public String confirmation(){
       return "Confirmation";
    }
   
	//formulaire de candidature etudiant

    @GetMapping("/formCandidatEtudiant")
    public String formCandidatE(Model model){
        List<Election> elections=iElection.listElection();
        List<Departement> departements=iDepartement.listDepartement();
        List<Filiere> filieres=iFiliere.listFiliere();
        List<Niveau> niveaux=iNiveau.listNiveaux();
        List<Service> services=iService.listService();
        model.addAttribute("candidat", new Candidat());
        model.addAttribute("elections",elections);
        model.addAttribute("departements",departements);
        model.addAttribute("filieres",filieres);
        model.addAttribute("niveaux",niveaux);
        model.addAttribute("services",services);
        return "formCandidatEtudiant";
    }
    @GetMapping("/formCandidatPats")
    public String formCandidatPats(Model model){
       List<Election> elections=iElection.listElection();
       List<Service> services=iService.listService();
       model.addAttribute("candidat",new Candidat());
       model.addAttribute("elections",elections);
       model.addAttribute("services",services);
       return "formCandidatPats";
    }

    //candidats print
    @GetMapping("/candidatsPrint")
    public String candidatsPrint(Model model){
       List<Candidat> candidats = candidatM.listCandidat();
       model.addAttribute("candidats",candidats);
       return "candidatsPrint";
    }

    //nombre candidats inscrits
    @GetMapping("/nombreInscrits")
    @ResponseBody
    public Integer nombreInscrit(){
       return candidatM.nombreCandidatInscrit();
    }

}
